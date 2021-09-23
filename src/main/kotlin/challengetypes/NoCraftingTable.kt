package challengetypes

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.plugin.Plugin
import isEnabled
import cancelTimer
import org.bukkit.Bukkit
import org.bukkit.GameMode

open class NoCraftingTable(): Listener {
    @EventHandler
    fun onCrafting(event: CraftItemEvent){
        if(event.clickedInventory != event.whoClicked.inventory){
            if(isEnabled(Challen*geType.NOCRAFTINGTABLE)){
                cancelTimer(event.whoClicked as Player, "hat etwas gecraftet")

                for(player in Bukkit.getOnlinePlayers()){
                    player.gameMode = GameMode.SPECTATOR
                }
            }
        }
    }
}