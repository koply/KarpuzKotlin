package me.koply.karpuzkotlin

import me.koply.karpuzkotlin.commando.Command
import me.koply.karpuzkotlin.commando.CommandHandler
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

object App {

    var commands = HashMap<String, Command>()

    @JvmStatic
    fun main(args : Array<String>) {

        println("Welcome to Karpuz Kotlin!")
        println("Bot launching!")

        val jda : JDA = JDABuilder(AccountType.BOT)
            .setToken(Ref.BOT_TOKEN)
            .setAutoReconnect(true)
            .addEventListeners(CommandHandler(commands))
            .build()

        println("Karpuz is flying into to Mars!")
    }
}