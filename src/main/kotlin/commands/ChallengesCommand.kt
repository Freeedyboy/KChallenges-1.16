package commands

import ChallengeMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ChallengesCommand(private val plugin: Plugin): CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        val player: Player = p0 as Player

        val challengeMenu: ChallengeMenu = ChallengeMenu()

        plugin.server.pluginManager.registerEvents(challengeMenu, plugin)
        player.openInventory(challengeMenu.getInventory())

        return true
    }
}