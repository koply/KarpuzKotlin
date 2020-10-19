package me.koply.karpuzkotlin.commando.commands.utility

import me.koply.karpuzkotlin.commando.Command
import me.koply.karpuzkotlin.util.Util
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Ping : Command(arrayListOf("ping"),"Pong!") {

    override fun handle(e: MessageReceivedEvent) {
        e.channel.sendMessage(Util.basicEmbed("Pong!").build()).queue()
    }

}