package me.koply.karpuzkotlin

import me.koply.karpuzkotlin.commando.CommandHandler
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

object App {

    @Suppress("DEPRECATION", "UNUSED_VARIABLE")
    @JvmStatic
    fun main(args : Array<String>) {
        println("Welcome to Karpuz Kotlin!")
        println("Bot launching!")

        val jda : JDA = JDABuilder(AccountType.BOT)
            .setToken(Ref.BOT_TOKEN)
            .setAutoReconnect(true)
            .addEventListeners(CommandHandler)
            .build()

        println("Karpuz is flying into to Mars!")
    }
}