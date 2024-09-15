package dev.unstackss.evernooffrocket

import dev.unstackss.evernooffrocket.commands.OffHandCommand
import dev.unstackss.evernooffrocket.events.NoRocketOffHand
import dev.unstackss.evernooffrocket.utils.UnStacksLogger
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.LogRecord
import java.util.logging.Level
import kotlin.properties.Delegates

class EverNoOffRocket : JavaPlugin() {
    private val bstats = config.getBoolean("bStats")

    companion object {
        var plugin : EverNoOffRocket by Delegates.notNull()
    }

    private fun initialize() {
        plugin = this
        saveDefaultConfig()
        config.options().copyDefaults(true)
        config.options().parseComments(true)
        if(bstats) {
            val metrics = Metrics(plugin, 23344)
            metrics.addCustomChart(SimplePie("kotlin_version") { KotlinVersion.CURRENT.toString() })
            UnStacksLogger.log(LogRecord(Level.INFO, "Metrics enabled for EverNoOffRocket"), "EverNoOffRocket")
        }
    }

    private fun events() {
        server.pluginManager.registerEvents(NoRocketOffHand, this)
    }

    private fun commands() {
        getCommand("offhand")!!.setExecutor(OffHandCommand())
    }

    override fun onEnable() {
        initialize()
        events()
        commands()
    }
}
