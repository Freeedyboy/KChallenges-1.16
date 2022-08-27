package commands

import challenge.menus.ChallengeMenu
import challenge.menus.SettingsMenu
import isRunning
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class SettingsCommand(private val plugin: Plugin): CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(!isRunning()){
            val player: Player = p0 as Player

            val settingsMenu: SettingsMenu = SettingsMenu()

            plugin.server.pluginManager.registerEvents(settingsMenu,plugin)
            player.openInventory(settingsMenu.inventory)

        }else{
            p0.sendMessage("§cDu kannst während die Challenge läuft das Menu nicht öffnen")
        }

        return true
    }
}