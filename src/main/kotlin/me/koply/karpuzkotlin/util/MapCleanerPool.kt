package me.koply.karpuzkotlin.util

import me.koply.karpuzkotlin.Ref
import java.util.concurrent.*

class MapCleanerPool(val cooldownList : ConcurrentMap<String, Long>) {

    init {
        println("MapCleanerPool#init")
    }

    val scheduledExecutorService : ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private var cooldown : Int = 0
    fun asyncCleaner() {
        println("MapCleanerPool#asyncCleaner")
        val task = Runnable { cleaner() }
        val scheduledFuture : ScheduledFuture<*> =  scheduledExecutorService.scheduleAtFixedRate(task, 1L, 1L, TimeUnit.MINUTES)
        cooldown = Ref.COOLDOWN
    }

    fun cleaner() {
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