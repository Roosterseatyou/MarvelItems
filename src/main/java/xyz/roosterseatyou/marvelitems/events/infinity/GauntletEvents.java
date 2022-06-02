package xyz.roosterseatyou.marvelitems.events.infinity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;
import xyz.roosterseatyou.marvelitems.utils.DataFileHelper;
import xyz.roosterseatyou.marvelitems.utils.FileIOHelper;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GauntletEvents implements Listener {
private static final Map<UUID, Integer> inventories = new HashMap<>();


    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!MarvelUtils.isInfGauntlet(e.getCurrentItem())) return;
        ItemStack item = e.getCurrentItem();
        if(e.getClick().isRightClick()) {
            e.setCancelled(true);
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();

            //File creation and ID setting stuff.
            int id = Integer.parseInt(serializer.serialize(item.lore().get(1)));
            UUID transactionID = UUID.randomUUID();

            if(id == 0) {
                InfinityGauntlet.setID(e.getCurrentItem());
                File gauntletFile = FileIOHelper.createYMLFile(serializer.serialize(item.lore().get(1)) + ".yml", "inventory: ");
                Inventory inventory = Bukkit.createInventory(e.getWhoClicked(), 9, Component.text(transactionID.toString()));
                DataFileHelper dataFileHelper = new DataFileHelper(id + ".yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());

                dataFileHelper.saveInventory("inventory", inventory);
                dataFileHelper.saveData();
            }
            DataFileHelper dataFileHelper = new DataFileHelper(id + ".yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());

            //normal click handling stuff.
            Inventory gauntletInv = Bukkit.createInventory(e.getWhoClicked(), 9, Component.text(transactionID.toString()));
            try {
                e.getWhoClicked().openInventory(gauntletInv);
                dataFileHelper.loadInventory("inventory", gauntletInv);
            } catch (NullPointerException ex) {
                e.getWhoClicked().openInventory(gauntletInv);
                MarvelItems.logger().severe("Could not load inventory for infinity gauntlet: " + serializer.serialize(e.getCurrentItem().lore().get(1)));
            }
            inventories.put(transactionID, Integer.parseInt(serializer.serialize(e.getCurrentItem().lore().get(1))));
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        UUID uuid = null;
        try {
            uuid = UUID.fromString(serializer.serialize(e.getView().title()));
        } catch (IllegalArgumentException ex) {
            return;
        }
        if (inventories.get(uuid) == null) return;
        int id = inventories.get(uuid);

        DataFileHelper dataFileHelper = new DataFileHelper(id + ".yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        dataFileHelper.saveInventory("inventory", e.getInventory());

        dataFileHelper.saveData();
    }


}
