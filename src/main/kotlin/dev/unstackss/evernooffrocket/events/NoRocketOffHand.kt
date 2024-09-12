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
            event.player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerSwapHandItemsEvent"))))
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val offHandItem = player.inventory.itemInOffHand
        if (offHandItem.type == Material.FIREWORK_ROCKET) {
            event.isCancelled = true
            player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerInteractEvent"))))
        }
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val offHandItem = player.inventory.itemInOffHand
        if (offHandItem.type == Material.FIREWORK_ROCKET) {
            player.inventory.setItemInOffHand(null)
            val leftover = player.inventory.addItem(ItemStack(Material.FIREWORK_ROCKET, offHandItem.amount))
            if (leftover.isNotEmpty()) {
                player.world.dropItemNaturally(player.location, leftover.values.first())
            }
            player.sendMessage(Format.hex(Format.color(plugin.config.getString("messages.PlayerMoveEvent"))))
        }
    }
}
