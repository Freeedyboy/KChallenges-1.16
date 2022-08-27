package listener

import isRunning
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class GeneralListener : Listener{

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        event.joinMessage = "§7>>§kh§r§a${event.player.name}§r§kh"

        event.player.setPlayerListHeaderFooter("", "")
    }

    @EventHandler
    fun onLeave(event: PlayerQuitEvent){
        event.quitMessage = "§7<<§kh§r§4${event.player.name}§kh"
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent){
        if(!isRunning()){
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent){
        if(!isRunning()){
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityTarget(event: EntityTargetEvent){
        if(!isRunning() && event.target is Player){
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent){
        if(!isRunning()){
            event.isCancelled = true
        }
    }
}