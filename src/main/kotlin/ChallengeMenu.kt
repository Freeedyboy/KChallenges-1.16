import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Item
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
        inventory.setItem(10, createGuiItem(Material.CRAFTING_TABLE, "No Crafting Table", "Man darf keinen Crafting Table benutzen"))
        hashmap.put(Material.CRAFTING_TABLE, ChallengeType.NOCRAFTINGTABLE)
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent){
        if(event.inventory != inventory)
            return

        val item: ItemStack? = event.currentItem

        if(item!!.type == Material.CRAFTING_TABLE){
            enableChallenge(hashmap.get(Material.CRAFTING_TABLE)!!)
        }
    }

    private fun createGuiItem(material: Material?, name: String?, vararg lore: String?): ItemStack? {
        val item = ItemStack(material!!, 1)
        val meta = item.itemMeta

        meta!!.setDisplayName(name)

        item.itemMeta = meta
        return item
    }
}