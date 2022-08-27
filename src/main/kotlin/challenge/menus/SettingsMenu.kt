package challenge.menus

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory

import CreateGUIItem.createGuiItem
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent

class SettingsMenu : Listener {

    companion object {
        var uhc: Boolean = false
    }

    var inventory: Inventory = Bukkit.createInventory(null, 36, "Einstellungen")

    constructor(){
        this.inventory.setItem(10, createGuiItem(Material.REDSTONE, "§cMaximale Leben ", "§7Linksklick §7zum §aerhöhen", "§7Rechtsklick §7zum §averringern"))
        this.inventory.setItem(11, createGuiItem(Material.GOLDEN_APPLE, "§6Ultra Hardcore", "§7Ultrahardcore ist ${if(uhc){"§aAn"}else{"§cAus"}}"))
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent){
        if(event.inventory != this.inventory)
            return

        event.isCancelled = true

        var clicker = event.whoClicked
        var item = event.currentItem

        if(event.isLeftClick){
            if(item?.type == Material.REDSTONE){
                for(player in Bukkit.getOnlinePlayers()){
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value)!! + 1.0
                    player.health = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value)!!
                }
            }else if(item?.type == Material.GOLDEN_APPLE){
                uhc = !uhc
                this.inventory.setItem(11, createGuiItem(Material.GOLDEN_APPLE, "§6Ultra Hardcore", "§7Ultrahardcore ist ${if(uhc){"§aAn"}else{"§cAus"}}"))
            }
        }else{
            if(item?.type == Material.REDSTONE){
                for(player in Bukkit.getOnlinePlayers()){
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value)!! - 1.0
                    player.health = (player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value)!!
                }
            }
        }
    }
}