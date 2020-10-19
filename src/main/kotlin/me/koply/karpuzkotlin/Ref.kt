package me.koply.karpuzkotlin

object Ref {
    const val BOT_TOKEN = "TOKEN"

    const val PREFIX = "."

    val OWNERS = listOf("OWNERID")

    private val BOTNAME : String = "Karpuz Kotlin"
        get() = field // example for explain kotlin's getter setter syntax

    const val COOLDOWN = 5000 // 5 sec
}