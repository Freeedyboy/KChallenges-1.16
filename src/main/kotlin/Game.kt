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
            if(isRunning()) {
                for (player in Bukkit.getOnlinePlayers()) {
                    player.spigot()
                        .sendMessage(ChatMessageType.ACTION_BAR, TextComponent("§l" + shortInteger(currentTime)))
                }

                currentTime += 1
            }else{
                for(player in Bukkit.getOnlinePlayers()){
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("Der Timer ist gerade pausiert"))
                }
            }
        }
    }

    fun onReload(){
        runnable.runTaskTimer(plugin, 1, 20)
    }

    fun start(): Boolean{
        return if(!running) {
            running = true
            true
        }else{
            false
        }
    }

    fun stop(): Boolean{
        return if(running) {
            running = false
            true
        }else{
            false
        }
    }

    fun isRunning(): Boolean{
        return running
    }

    private fun shortInteger(duration: Int): String {
        var duration = duration
        var string = ""
        var hours = 0
        var minutes = 0
        var seconds = 0

        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60
            duration -= duration / 60 / 60 * 60 * 60
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60
            duration -= duration / 60 * 60
        }
        if (duration >= 1) seconds = duration
        string = if (hours <= 9) {
            string + "0" + hours + ":"
        } else {
            "$string$hours:"
        }
        string = if (minutes <= 9) {
            string + "0" + minutes + ":"
        } else {
            "$string$minutes:"
        }
        string = if (seconds <= 9) {
            string + "0" + seconds
        } else {
            string + seconds
        }
        return string
    }
}