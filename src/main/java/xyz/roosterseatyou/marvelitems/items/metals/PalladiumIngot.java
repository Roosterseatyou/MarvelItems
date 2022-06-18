package xyz.roosterseatyou.marvelitems.items.metals;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PalladiumIngot {
    public static Component NAME = Component.text("Palladium Ingot");
    public static Component SERVER_ID = Component.text("SERVER_ID: PALLADIUM_INGOT").color(TextColor.color(46, 44, 44));

    public static ItemStack PALLADIUM_INGOT;

    public static ItemStack init() {
        load();
        return null;
    }

    public static void load() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(SERVER_ID);
        meta.lore(lore);
        item.setItemMeta(meta);
        PALLADIUM_INGOT = item;
    }
}
