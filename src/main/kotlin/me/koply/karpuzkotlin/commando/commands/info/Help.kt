package me.koply.karpuzkotlin.commando.commands.info

import me.koply.karpuzkotlin.commando.Command
import me.koply.karpuzkotlin.util.Util
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Help : Command(arrayListOf("help","yardım"), "Bu komut.") {

    override fun handle(e: MessageReceivedEvent) {
        e.textChannel.sendMessage(Util.embed("Helping melping")).queue()
    }

}