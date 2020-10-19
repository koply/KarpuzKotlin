package me.koply.karpuzkotlin.commando

import me.koply.karpuzkotlin.Ref
import me.koply.karpuzkotlin.commando.annotations.GuildOnly
import me.koply.karpuzkotlin.commando.annotations.OwnerOnly
import me.koply.karpuzkotlin.util.MapCleanerPool
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.reflections.Reflections
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object CommandHandler : ListenerAdapter() {

    private val cooldownList : ConcurrentMap<String, Long> = ConcurrentHashMap()
    private val executor : ExecutorService = Executors.newCachedThreadPool()
    private val commands = HashMap<String, Command>()

    init {
        registerCommands()
        MapCleanerPool(cooldownList).asyncCleaner()
    }

    override fun onMessageReceived(e: MessageReceivedEvent) {
        if (e.author.isBot) {
            return
        }

        val args : List<String> = e.message.contentRaw.split(" ")

        if (!args[0].startsWith(Ref.PREFIX)) {
            return
        }

        val command = args[0].substring(1)
        if (!commands.containsKey(command)) {
            return
        }

        val guild = if (e.isFromGuild) e.guild.name else "PRIVATE"
        println("Command received | User: ${e.author.name} | Guild: $guild | Command: $command")

        val currentMillis : Long = System.currentTimeMillis()
        if (currentMillis - cooldownList.getOrDefault(e.author.id, 0L) <= Ref.COOLDOWN) {
            println("Last command declined due to cooldown.")
            return
        }
        cooldownList[e.author.id] = currentMillis

        try  {
            executor.submit {
                commands[command]?.handle(e)
                println("Last command took ${System.currentTimeMillis() - currentMillis} ms to execute")
            }
        } catch (ex : Exception) {
            println("Last command crashed. ${ex.message}")
        }

    }

    private fun registerCommands() {
        val reflections = Reflections("${CommandHandler::class.java.`package`.name}.commands")
        val classes : MutableSet<Class<out Command>>? = reflections.getSubTypesOf(Command::class.java)

        if (classes != null) {
            for (cl : Class<out Command> in classes) {
                @Suppress("DEPRECATION") val command : Command = cl.newInstance()
                val commandName : ArrayList<String> = command.name

                val groupRawNameSplit = cl.`package`.name.split("\\.")
                val groupRawName = groupRawNameSplit[groupRawNameSplit.size-1]

                val guildOnly : GuildOnly? = cl.getAnnotation(GuildOnly::class.java)
                if (guildOnly != null) command.guildOnly = true

                val ownerOnly : OwnerOnly? = cl.getAnnotation(OwnerOnly::class.java)
                if (ownerOnly != null) command.ownerOnly = true

                command.group = groupRawName
                for (str in commandName) {
                    commands[str] = command
                }
            }
        }
    }

}