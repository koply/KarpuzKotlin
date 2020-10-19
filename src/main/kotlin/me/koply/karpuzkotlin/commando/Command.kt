package me.koply.karpuzkotlin.commando

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@Suppress("unused")
abstract class Command(val name : ArrayList<String>, val desc : String) {
    abstract fun handle(e: MessageReceivedEvent)

    lateinit var group : String
    var ownerOnly = false
    var guildOnly = false
}