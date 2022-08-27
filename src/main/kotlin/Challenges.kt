import commands.ChallengesCommand
import commands.SettingsCommand
import commands.StartStopGameCommand
import listener.GeneralListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.*


open class Challenges: JavaPlugin() {

    override fun onEnable() {
        Bukkit.broadcastMessage("Lade gerade ihr Bastarde")

        setPlugin(this)
        onReload()
        this.server.pluginManager.registerEvents(GeneralListener(), this)
        this.getCommand("startgame")?.setExecutor(StartStopGameCommand())
        this.getCommand("stopgame")?.setExecutor(StartStopGameCommand())
        this.getCommand("challenges")?.setExecutor(ChallengesCommand(this))
        this.getCommand("settings")?.setExecutor(SettingsCommand(this))

        //this.isValidSpawn()
    }

    override fun onDisable() {
        for(player in Bukkit.getOnlinePlayers()){
            this.bungeeCommand(player, "Lobby")
        }
    }

    fun bungeeCommand(p: Player, sn: String) {
        val b = ByteArrayOutputStream()
        val d = DataOutputStream(b)
        try {
            d.writeUTF("Connect")
            d.writeUTF(sn)
            b.close()
        } catch (e: Exception) {
            e.printStackTrace()
            logger.severe(ChatColor.RED.toString() + "Error connecting to the server " + sn)
        }
        p.sendPluginMessage(this, "BungeeCord", b.toByteArray())
    }

    private fun isValidSpawn(){
        if(Bukkit.getWorld("world")?.spawnLocation?.block?.biome?.name?.contains("OCEAN")!!){
            this.restartServer()
        }
    }

    private fun restartServer(){
        Bukkit.shutdown()

        this.readBashScript()
    }

    private fun readBashScript() {
        try {
            val proc = Runtime.getRuntime()
                .exec("start.sh") //Whatever you want to execute
            val read = BufferedReader(
                InputStreamReader(
                    proc.inputStream
                )
            )
            try {
                proc.waitFor()
            } catch (e: InterruptedException) {
                println(e.message)
            }
            while (read.ready()) {
                println(read.readLine())
            }
        } catch (e: IOException) {
            println(e.message)
        }
    }
}