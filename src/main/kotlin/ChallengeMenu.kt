import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

class ChallengeMenu : Listener{

    private var inventory: Inventory = Bukkit.createInventory(null, InventoryType.BARREL, "Challenges")
    private val hashmap: HashMap<Material, ChallengeType> = HashMap()

    constructor(){
        inventory.setItem(10, SLJKAHDBOIJKSAHBNDPIASHBDUI.createGuiItem(Material.CRAFTING_TABLE, "No Crafting Table", "Man darf keinen Crafting Table benutzen", "Status: "+if(isEnabled(ChallengeType.NOCRAFTINGTABLE)) "An" else "Aus"))
        hashmap.put(Material.CRAFTING_TABLE, ChallengeType.NOCRAFTINGTABLE)
    }

    fun getInventory(): Inventory{
        return inventory
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent){
        if(event.inventory != inventory)
            return

        event.isCancelled = true

        val item: ItemStack? = event.currentItem

        if(item!!.type == Material.CRAFTING_TABLE){
            if(!isEnabled(ChallengeType.NOCRAFTINGTABLE))
                enableChallenge(hashmap.get(Material.CRAFTING_TABLE)!!)
            else
                disableChallenge(hashmap.get(Material.CRAFTING_TABLE)!!)

            inventory.setItem(10, SLJKAHDBOIJKSAHBNDPIASHBDUI.createGuiItem(Material.CRAFTING_TABLE, "No Crafting Table", "Man darf keinen Crafting Table benutzen", "Status: "+if(isEnabled(ChallengeType.NOCRAFTINGTABLE)) "An" else "Aus"))
            event.whoClicked.sendMessage("Die Challenge wurde "+if(isEnabled(ChallengeType.NOCRAFTINGTABLE)) "An" else {"Aus"}+" gemacht")
        }
    }


}