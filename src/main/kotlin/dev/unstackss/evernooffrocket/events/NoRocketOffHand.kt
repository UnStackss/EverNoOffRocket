@file:Suppress("SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN")

package dev.unstackss.evernooffrocket.events

import dev.unstackss.evernooffrocket.EverNoOffRocket.Companion.plugin
import dev.unstackss.evernooffrocket.utils.Format
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack

object NoRocketOffHand : Listener {

    @EventHandler
    fun onSwapHandItems(event: PlayerSwapHandItemsEvent) {
        val item = event.offHandItem
        if (item != null && item.type == Material.FIREWORK_ROCKET) {
            event.isCancelled = true
            moveRocketToMainInventory(event.player.inventory.itemInOffHand, event.player)
            event.player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerSwapHandItemsEvent"))))
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val offHandItem = player.inventory.itemInOffHand
        if (offHandItem.type == Material.FIREWORK_ROCKET) {
            event.isCancelled = true
            moveRocketToMainInventory(offHandItem, player)
            player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerInteractEvent"))))
        }
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val offHandItem = player.inventory.itemInOffHand
        if (offHandItem.type == Material.FIREWORK_ROCKET) {
            moveRocketToMainInventory(offHandItem, player)
            player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerMoveEvent"))))
        }
    }

    private fun moveRocketToMainInventory(offHandItem: ItemStack, player: org.bukkit.entity.Player) {
        if (offHandItem.type == Material.FIREWORK_ROCKET) {
            val rocketsToMove = offHandItem.amount
            val rockets = ItemStack(Material.FIREWORK_ROCKET, rocketsToMove)
            val leftover = player.inventory.addItem(rockets)
            if (leftover.isEmpty) {
                player.inventory.setItemInOffHand(null)
            } else {
                val remainingRockets = leftover.values.first().amount
                offHandItem.amount = remainingRockets
                player.inventory.setItemInOffHand(offHandItem)
            }
        }
    }

}
