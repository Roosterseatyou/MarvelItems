package xyz.roosterseatyou.marvelitems.items.infinitystones;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RealityStone {
    public static final Component NAME = Component.text("Reality Stone");
    public static ItemStack REAL_STONE;

    public static ItemStack init() {
        getItem();
        return null;
    }

    public static void getItem() {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(Component.text("An ominous red gem that distorts the air around it."));
        lore.add(Component.text("SERVER_ID: REAL_STONE").color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        item.setItemMeta(meta);
        REAL_STONE = item;
    }
}

