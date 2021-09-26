import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class GeneralListener : Listener{

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        event.joinMessage = "§7>>§kh§r§a${event.player.name}§r§kh"
    }

    @EventHandler
    fun onLeave(event: PlayerQuitEvent){
        event.quitMessage = "§7<<§kh§r§4${event.player.name}§kh"
    }
}