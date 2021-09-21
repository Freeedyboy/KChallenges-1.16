import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class Game(private val plugin: Plugin) {
    private var currentTime: Int = 0
    private var running: Boolean = false

    private val runnable : BukkitRunnable = object : BukkitRunnable(){
        override fun run() {
            for(player in Bukkit.getOnlinePlayers()){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("§l"+shortInteger(currentTime)))
            }

            currentTime += 1
        }
    }

    fun start(): Boolean{
        return if(!running) {
            runnable.runTaskTimer(plugin, 1, 20)
            true
        }else{
            false
        }
    }

    fun stop(): Boolean{
        return if(running) {
            runnable.cancel()
            true
        }else{
            false
        }
    }
}