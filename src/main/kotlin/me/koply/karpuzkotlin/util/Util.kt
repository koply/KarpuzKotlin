package me.koply.karpuzkotlin.util

import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color
import java.util.*

object Util {

    private val random : Random = Random()

    @JvmStatic
    fun basicEmbed(text : String) : EmbedBuilder{
        return EmbedBuilder().setDescription(text).setColor(randomColor())
    }

    @JvmStatic
    fun randomColor() : Color {
        return Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))
    }

}