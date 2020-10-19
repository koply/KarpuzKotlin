package me.koply.karpuzkotlin.commando

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class Command(val name : ArrayList<String>, val desc : String) {
    abstract fun handle(e: MessageReceivedEvent)

    lateinit var group : String
    var ownerOnly : Boolean = false
    var guildOnly : Boolean = false
}