package commands

import getEnabledChallengesAsString
import org.bukkit.Bukkit
import org.bukkit.GameMode
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
                    player.sendMessage("Das Spiel wurde mit den Challenges ${getEnabledChallengesAsString()} gestartet")

                    player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 5f, 5f)

                    player.foodLevel = 20
                    player.health = 20.0
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