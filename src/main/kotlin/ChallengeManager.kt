import challengetypes.NoCraftingTable
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin
import java.util.*

private var plugin: Plugin? = null
private var challenges:HashMap<ChallengeType, Boolean> = HashMap()
private var currentGame: Game? = null
private val noCraftingTable: NoCraftingTable = NoCraftingTable()

fun setPlugin(plugin1: Plugin){
    plugin = plugin1
}

fun startTimer(): Boolean{
    currentGame = Game(plugin!!)
    retrieveChallenges()
    return currentGame!!.start()
}

fun cancelTimer(player: Player, cause: String): Boolean {
    Bukkit.broadcastMessage("------------Challenge------------\n" +
                            "der Timer wurde gestoppt\n" +
                            "${player.name} $cause" +
                            "---------------------------------")
    return currentGame!!.stop()
}

fun stopTimer(): Boolean {
    return currentGame!!.stop()
}

fun isRunning(): Boolean{
    return currentGame!!.isRunning()
}

fun enableChallenge(challengeType: ChallengeType){
    if(!challenges.containsKey(challengeType)) {
        challenges.put(challengeType, true)
    }else{
        challenges.replace(challengeType, true)
    }
}

fun isEnabled(challengeType: ChallengeType): Boolean{
    try {
        return challenges.get(challengeType)!!
    }catch (e: NullPointerException){
        return false
    }
}

fun disableChallenge(challengeType: ChallengeType){
    if(challenges.containsKey(challengeType)) {
        challenges.put(challengeType, false)
    }else{
        challenges.replace(challengeType, false)
    }
}

fun retrieveChallenges(){
    for(challenge in challenges.keys){
        if(challenge == ChallengeType.NOCRAFTINGTABLE){
            if(challenges.get(challenge)!!) {
                plugin!!.server.pluginManager.registerEvents(noCraftingTable, plugin!!)
            }else{
                HandlerList.unregisterAll(noCraftingTable)
            }
        }
    }
}