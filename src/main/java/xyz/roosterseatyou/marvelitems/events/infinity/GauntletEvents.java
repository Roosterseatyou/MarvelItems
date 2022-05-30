package xyz.roosterseatyou.marvelitems.events.infinity;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;
import xyz.roosterseatyou.marvelitems.utils.DataFileHelper;
import xyz.roosterseatyou.marvelitems.utils.FileIOHelper;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.io.File;

public class GauntletEvents implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack item = e.getRecipe().getResult();
        if (MarvelUtils.isInfGauntlet(item)) {
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            File gauntletFile = FileIOHelper.createYMLFile(serializer.serialize(item.lore().get(1)) + ".yml", "inventory: ");
            DataFileHelper dataFileHelper = new DataFileHelper(gauntletFile.getName(), MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
            Inventory inventory = Bukkit.createInventory(e.getWhoClicked(), 9, InfinityGauntlet.NAME);
            e.getWhoClicked().openInventory(inventory);
            dataFileHelper.saveInventory("inventory", inventory);
            dataFileHelper.saveData();
        }
    }

    @EventHandler
    public void onChestClick(InventoryClickEvent e) {
        if (!MarvelUtils.isInfGauntlet(e.getCurrentItem())) return;
        if (!e.getClick().isRightClick()) return;
        e.setCancelled(true);
        File gauntletFile = FileIOHelper.getGauntletFile(e.getCurrentItem());
        DataFileHelper dataFileHelper = new DataFileHelper(gauntletFile.getName(), MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        Inventory gauntletInv = Bukkit.createInventory(e.getWhoClicked(), 9, InfinityGauntlet.NAME);
        dataFileHelper.loadInventory("infinity-gauntlets." + e.getCurrentItem().lore().get(1) + ".inventory", gauntletInv);
        e.getWhoClicked().closeInventory();
        e.getWhoClicked().openInventory(gauntletInv);
    }

    //TODO: create listener for InventoryCloseEvent to save inventory
}
