package challenge.menus

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

class SettingsMenu : Listener {

    private var inventory: Inventory = Bukkit.createInventory(null, InventoryType.BARREL, "Einstellungen")

    constructor(){
        
    }

}