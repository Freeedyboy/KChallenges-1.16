package commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable
import startTimer
import stopTimer

class StartStopGameCommand: CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p1.name == "startgame") {
            startTimer()
            Bukkit.broadcastMessage("Der Timer wurde gestartet")
        }else if (p1.name == "stopgame"){
            stopTimer()
            Bukkit.broadcastMessage("Der Timer wurde gestoppt")
        }
        return true
    }
}