import challenge.ChallengeType
import challenge.Game
import challengetypes.MoreKnockBackChallenge
import challengetypes.NoCraftingTable
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin
import java.util.*

private var plugin: Plugin? = null
private var challenges: HashMap<ChallengeType, Boolean> = HashMap()
private var currentGame: Game? = null
private val noCraftingTable: NoCraftingTable = NoCraftingTable()
private val moreKnockBackChallenge: MoreKnockBackChallenge = MoreKnockBackChallenge()

fun setPlugin(plugin1: Plugin){
    plugin = plugin1
}

fun startTimer(): Boolean{
    retrieveChallenges()
    return currentGame!!.start()
}

fun onReload(){
    currentGame = Game(plugin!!)
    currentGame!!.onReload()
}

fun cancelTimer(player: Player, cause: String): Boolean {
    Bukkit.broadcastMessage("§8------------§9Challenge§8------------\n" +
                            "§cder Timer wurde gestoppt\n" +
                            "§c${player.name} $cause \n" +
                            "§7Ihr habt ${currentGame!!.getTimeAsString()} verschwendet\n" +
                            "§8--------------------------------------")

    for(keys in challenges.keys){
        if(challenges.get(keys) == true){
            challenges.replace(keys, false)
        }
    }

    retrieveChallenges()

    return currentGame!!.stop()
}

fun stopTimer(): Boolean {
    for(keys in challenges.keys){
        if(challenges.get(keys) == true){
            challenges.replace(keys, false)
        }
    }

    retrieveChallenges()

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

fun getEnabledChallengesAsString(): String{
    val rString = "";

    for(key in challenges.keys){
        if(challenges.get(key)!!){
            rString.plus("$key ")
        }
    }
    return rString
}

fun retrieveChallenges(){
    for(challenge in challenges.keys){
        if(challenge == ChallengeType.NOCRAFTINGTABLE){
            if(challenges.get(challenge)!!) {
                plugin!!.server.pluginManager.registerEvents(noCraftingTable, plugin!!)
            }else{
                HandlerList.unregisterAll(noCraftingTable)
            }
        }else if(challenge == ChallengeType.MOREKNOCKBACK) {
            if (challenges.get(challenge)!!) {
                plugin!!.server.pluginManager.registerEvents(moreKnockBackChallenge, plugin!!)
            } else {
                HandlerList.unregisterAll(moreKnockBackChallenge)
            }
        }
    }
}