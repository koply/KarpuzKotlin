package me.koply.karpuzkotlin.events

import me.koply.karpuzkotlin.commando.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageReceived(val commands : ArrayList<Command>) : ListenerAdapter() {

    override fun onMessageReceived(e: MessageReceivedEvent) {
        TODO("henüz yapılmadı")
    }
}