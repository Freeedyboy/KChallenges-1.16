package listener

import challenge.menus.SettingsMenu
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityRegainHealthEvent

class SettingsListener {

    @EventHandler
    fun onHealthRegenerate(event: EntityRegainHealthEvent){
        if(SettingsMenu.uhc && event.regainReason == EntityRegainHealthEvent.RegainReason.EATING){
            event.isCancelled = true
        }
    }
}