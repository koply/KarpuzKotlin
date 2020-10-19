package me.koply.karpuzkotlin.util

import me.koply.karpuzkotlin.Ref
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MapCleanerPool(private val cooldownList : ConcurrentMap<String, Long>) {

    init {
        println("MapCleanerPool#init")
    }

    private val scheduledExecutorService : ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private var cooldown : Int = 0
    fun asyncCleaner() {
        println("MapCleanerPool#asyncCleaner")
        val task = Runnable { cleaner() }
        scheduledExecutorService.scheduleAtFixedRate(task, 1L, 1L, TimeUnit.MINUTES)
        cooldown = Ref.COOLDOWN
    }

    private fun cleaner() {
        val currentMillis : Long = System.currentTimeMillis()
        var i = 0
        for (entry : Map.Entry<String, Long> in cooldownList.entries) {
            if (currentMillis - entry.value >= cooldown) {
                cooldownList.remove(entry.key)
                i++
            }
        }
        if (i == 0) return
        println("$i entries deleted from cooldown list.")
    }
}