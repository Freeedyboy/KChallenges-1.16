import commands.ChallengesCommand
import commands.StartStopGameCommand
import listener.GeneralListener
import onReload
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import setPlugin

open class Challenges: JavaPlugin() {

    override fun onEnable() {
        Bukkit.broadcastMessage("Lade gerade ihr Bastarde")

        setPlugin(this)
        onReload()
        this.server.pluginManager.registerEvents(GeneralListener(), this)
        this.getCommand("startgame")?.setExecutor(StartStopGameCommand())
        this.getCommand("stopgame")?.setExecutor(StartStopGameCommand())
        this.getCommand("challenges")?.setExecutor(ChallengesCommand(this))
    }
}