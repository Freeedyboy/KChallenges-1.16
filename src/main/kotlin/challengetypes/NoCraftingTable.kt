package challengetypes

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.plugin.Plugin
import isEnabled
import cancelTimer
import challenge.Challenge
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.HandlerList
import challenge.ChallengeType

open class NoCraftingTable: Listener, Challenge() {
    @EventHandler
    fun onCrafting(event: CraftItemEvent){
        if(event.clickedInventory!!.type == org.bukkit.event.inventory.InventoryType.CRAFTING){
            if(isEnabled(ChallengeType.NOCRAFTINGTABLE)){
                cancelTimer(event.whoClicked as Player, "hat etwas gecraftet")

                for(player in Bukkit.getOnlinePlayers()){
                    player.gameMode = GameMode.SPECTATOR
                }

                HandlerList.unregisterAll(this)
            }
        }
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }
}