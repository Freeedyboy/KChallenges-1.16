import commands.StartStopGameCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import setPlugin

open class Challenges: JavaPlugin() {

    override fun onEnable() {
        Bukkit.broadcastMessage("Lade gerade ihr Bastarde")

        setPlugin(this)
        this.getCommand("startgame")?.setExecutor(StartStopGameCommand())
        this.getCommand("stopgame")?.setExecutor(StartStopGameCommand())
    }
}