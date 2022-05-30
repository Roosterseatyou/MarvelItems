package xyz.roosterseatyou.marvelitems.events.infinity;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
import java.util.HashMap;
import java.util.Map;

public class GauntletEvents implements Listener {
    private static final Map<Inventory, String> inventories = new HashMap<>();

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack item = e.getRecipe().getResult();
        if (MarvelUtils.isInfGauntlet(item)) {
            InfinityGauntlet.setUUID(item);
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            File gauntletFile = FileIOHelper.createYMLFile(serializer.serialize(item.lore().get(1)) + ".yml", "inventory: ");
            DataFileHelper dataFileHelper = new DataFileHelper(gauntletFile.getName(), MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
            Inventory inventory = Bukkit.createInventory(e.getWhoClicked(), 9, InfinityGauntlet.NAME);
            dataFileHelper.saveInventory("inventory", inventory);
            dataFileHelper.saveData();
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!MarvelUtils.isInfGauntlet(e.getCurrentItem())) return;
        MarvelItems.logger().info("Clicked with type: " + e.getClick().name());
        if(e.getClick().isRightClick()) {
            MarvelItems.logger().info("Right click");
            e.setCancelled(true);
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            File gauntletFile = FileIOHelper.getGauntletFile(e.getCurrentItem());
            DataFileHelper dataFileHelper = new DataFileHelper(gauntletFile.getName(), MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
            Inventory gauntletInv = Bukkit.createInventory(e.getWhoClicked(), 9, InfinityGauntlet.NAME);
            try {
                dataFileHelper.loadInventory("infinity-gauntlets." + e.getCurrentItem().lore().get(1) + ".inventory", gauntletInv);
            } catch (NullPointerException ex) {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(gauntletInv);
                MarvelItems.logger().severe("Could not load inventory for infinity gauntlet: " + serializer.serialize(e.getCurrentItem().lore().get(1)));
            }
            e.getWhoClicked().closeInventory();
            e.getWhoClicked().openInventory(gauntletInv);
            inventories.put(gauntletInv, serializer.serialize(e.getCurrentItem().lore().get(1)));
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if(!(e.getView().title().equals(InfinityGauntlet.NAME))) return;
        String uuid = inventories.get(e.getInventory());
        if(uuid == null) return;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        DataFileHelper dataFileHelper = new DataFileHelper(uuid + ".yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        dataFileHelper.saveInventory("inventory", e.getInventory());
        dataFileHelper.saveData();
        MarvelItems.logger().info("Saved inventory for " + uuid);
    }
}
