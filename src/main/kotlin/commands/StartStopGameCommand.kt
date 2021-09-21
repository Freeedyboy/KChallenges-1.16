package commands

import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable
import startTimer
import stopTimer

class StartStopGameCommand: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p1.name == "startgame") {
            if(startTimer()){
                for(player in Bukkit.getOnlinePlayers()){
                    player.sendTitle("Der Timer", "wurde gestartet", 5, 40, 5)
                    player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 5f, 5f)
                }
            }
        }else if (p1.name == "stopgame"){
            if(stopTimer()){
                Bukkit.broadcastMessage("Der Timer wurde gestoppt")
            }
        }
        return true
    }
}