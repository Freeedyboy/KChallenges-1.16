package challenge.menus

import CreateGUIItem
import challenge.ChallengeType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import isEnabled
import disableChallenge
import enableChallenge

class ChallengeMenu : Listener{

    private var inventory: Inventory = Bukkit.createInventory(null, InventoryType.BARREL, "Challenges")
    private val hashmap: HashMap<Material, ChallengeType> = HashMap()

    constructor(){
        inventory.setItem(10,
            CreateGUIItem.createGuiItem(
                Material.CRAFTING_TABLE,
                "§aNo Crafting Table",
                "§7Man darf keinen Crafting Table benutzen",
                "§7Status: " + if (isEnabled(ChallengeType.NOCRAFTINGTABLE)) "§aAn" else "§cAus"
            )
        )
        inventory.setItem(11,
            CreateGUIItem.createGuiItem(
                Material.FEATHER,
                "§aMore Knockback",
                "§7Jeder Spieler hat 20mal mehr Knockback",
                "§7Status: " + if (isEnabled(ChallengeType.MOREKNOCKBACK)) "§aAn" else "§cAus"
            )
        )
        hashmap.put(Material.CRAFTING_TABLE, ChallengeType.NOCRAFTINGTABLE)
        hashmap.put(Material.FEATHER, ChallengeType.MOREKNOCKBACK)
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

            inventory.setItem(10,
                CreateGUIItem.createGuiItem(
                    Material.CRAFTING_TABLE,
                    "§aNo Crafting Table",
                    "§7Man darf keinen Crafting Table benutzen",
                    "§7Status: " + if (isEnabled(ChallengeType.NOCRAFTINGTABLE)) "§aAn" else "§cAus"
                )
            )
            event.whoClicked.sendMessage("Die Challenge wurde "+if(isEnabled(ChallengeType.NOCRAFTINGTABLE)) "An" else {"Aus"}+" gemacht")
        }
        else if(item!!.type == Material.FEATHER){
            if(!isEnabled(ChallengeType.MOREKNOCKBACK))
                enableChallenge(hashmap.get(Material.FEATHER)!!)
            else
                disableChallenge(hashmap.get(Material.FEATHER)!!)

            inventory.setItem(11,
                CreateGUIItem.createGuiItem(
                    Material.FEATHER,
                    "§aMore Knockback",
                    "§7Jeder Spieler hat 20mal mehr Knockback",
                    "§7Status: " + if (isEnabled(ChallengeType.MOREKNOCKBACK)) "§aAn" else "§cAus"
                )
            )
            event.whoClicked.sendMessage("Die Challenge wurde "+if(isEnabled(ChallengeType.MOREKNOCKBACK)) "An" else {"Aus"}+" gemacht")
        }
    }


}