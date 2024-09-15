package dev.unstackss.evernooffrocket.commands

import dev.unstackss.evernooffrocket.EverNoOffRocket.Companion.plugin
import dev.unstackss.evernooffrocket.utils.Format
import dev.unstackss.evernooffrocket.utils.UnStacksLogger
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.logging.Level
import java.util.logging.LogRecord

class OffHandCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            UnStacksLogger.log(LogRecord(Level.INFO, plugin.config.getString("messages.onlyInGame")), "EverNoOffRocket")
            return true
        }
        val player = sender

        if (player.hasPermission("evernooffrocket.command.offhand")) {
            val mainHandItem = player.inventory.itemInMainHand
            val offHandItem = player.inventory.itemInOffHand
            if (mainHandItem.type == Material.AIR) {
                player.sendMessage(Format.color(plugin.config.getString("messages.noItemFound")))
                return true
            }
            if (mainHandItem.type == Material.FIREWORK_ROCKET) {
                player.sendMessage(Format.color(plugin.config.getString("messages.cantMoveItem")))
                return true
            }
            if (offHandItem.type != Material.AIR) {
                val leftover = player.inventory.addItem(offHandItem)
                if (leftover.isEmpty()) {
                    player.inventory.setItemInMainHand(ItemStack(Material.AIR))
                } else {
                    player.inventory.setItemInMainHand(offHandItem)
                }
            } else {
                player.inventory.setItemInMainHand(ItemStack(Material.AIR))
            }
            player.inventory.setItemInOffHand(mainHandItem)
        } else {
            player.sendMessage(Format.color(plugin.config.getString("messages.noPermission")))
        }
        return true
    }
}
