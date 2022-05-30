package xyz.roosterseatyou.marvelitems.events.infinity;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;
import xyz.roosterseatyou.marvelitems.utils.DataFileHelper;

public class GauntletEvents implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack item = e.getRecipe().getResult();
        if (e.getRecipe().getResult().lore().get(2).equals(InfinityGauntlet.SERVER_ID)) {
            DataFileHelper dataFileHelper = new DataFileHelper("data.yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            dataFileHelper.createPath("infinity-gauntlets." + serializer.serialize(item.lore().get(1)) + ".inventory");
            Inventory inventory = Bukkit.createInventory(e.getWhoClicked(), 1, InfinityGauntlet.NAME);
            dataFileHelper.saveInventory("infinity-gauntlets." + serializer.serialize(item.lore().get(1)) + ".inventory", inventory);
        }
    }
}
