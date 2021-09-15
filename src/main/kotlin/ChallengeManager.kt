import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.*

private var plugin: Plugin? = null
private var currentTime: Int = 0

private val runnable : BukkitRunnable = object : BukkitRunnable(){
                                            override fun run() {
                                                for(player in Bukkit.getOnlinePlayers()){
                                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(shortInteger(currentTime)))
                                                }

                                                currentTime += 1
                                            }
                                        }

fun setPlugin(plugin1: Plugin){
    plugin = plugin1
}

fun startTimer(){
    runnable.runTaskTimer(plugin!!, 1, 20)
}

fun shortInteger(duration: Int): String {
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