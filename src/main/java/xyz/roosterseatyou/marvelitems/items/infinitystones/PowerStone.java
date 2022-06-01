package xyz.roosterseatyou.marvelitems.items.infinitystones;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PowerStone {
    public static final Component NAME = Component.text("Power Stone");
    public static ItemStack POWER_STONE;

    public static ItemStack init() {
        getItem();
        return null;
    }

    public static void getItem() {
        ItemStack item = new ItemStack(Material.PURPLE_DYE);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(Component.text("A purple gemstone granting the user immense power."));
        lore.add(Component.text(UUID.randomUUID().toString()).color(TextColor.color(46, 44, 44)));
        lore.add(Component.text("SERVER_ID: POWER_STONE").color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        item.setItemMeta(meta);
        POWER_STONE = item;
    }

    public static ItemStack genItem() {
        ItemStack it = POWER_STONE.clone();
        ItemMeta meta = it.getItemMeta();
        List<Component> lore = meta.lore();
        lore.set(1, Component.text(UUID.randomUUID().toString()).color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        it.setItemMeta(meta);
        return it;
    }
}
