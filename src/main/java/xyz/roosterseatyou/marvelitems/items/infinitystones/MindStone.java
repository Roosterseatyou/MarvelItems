package xyz.roosterseatyou.marvelitems.items.infinitystones;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MindStone {
    public static final Component NAME = Component.text("Mind Stone");
    public static ItemStack MIND_STONE;

    public static ItemStack init() {
        getItem();
        return null;
    }

    public static void getItem() {
        ItemStack item = new ItemStack(Material.ORANGE_DYE);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(Component.text("A orange gemstone granting the user the ability to control minds."));
        lore.add(Component.text(UUID.randomUUID().toString()).color(TextColor.color(46, 44, 44)));
        lore.add(Component.text("SERVER_ID: MIND_STONE").color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        item.setItemMeta(meta);
        MIND_STONE = item;
    }

    public static ItemStack genItem() {
        ItemStack it = MIND_STONE.clone();
        ItemMeta meta = it.getItemMeta();
        List<Component> lore = meta.lore();
        lore.set(1, Component.text(UUID.randomUUID().toString()).color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        it.setItemMeta(meta);
        return it;
    }
}


