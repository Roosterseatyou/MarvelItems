package xyz.roosterseatyou.marvelitems.items.infinitygauntlet;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.utils.DataFileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfinityGauntlet {
    public static final Component NAME = Component.text("Infinity Gauntlet");
    public static final Component SERVER_ID = Component.text("SERVER_ID: INF_GAUNTLET").color(TextColor.color(46, 44, 44));
    public static ItemStack INF_GAUNTLET;

    public static ItemStack init() {
        getItem();
        return null;
    }

    public static void getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(Component.text("Perfectly balanced, as all things should be."));
        lore.add(Component.text(0).color(TextColor.color(46, 44, 44)));
        lore.add(SERVER_ID);
        meta.lore(lore);
        item.setItemMeta(meta);
        INF_GAUNTLET = item;

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(MarvelItems.getInstance(), "infinity_gauntlet"), genInfGauntlet());
        recipe.shape("GGG", "GDG", "GGG");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('D', Material.DIAMOND);
        MarvelItems.getInstance().getServer().addRecipe(recipe);
    }

    public static ItemStack genInfGauntlet() {
        ItemStack item = INF_GAUNTLET.clone();
        ItemMeta meta = item.getItemMeta();
        ArrayList<Component> lore = (ArrayList<Component>) meta.lore();
        lore.set(1, Component.text(0).color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static void setID(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<Component> lore = (ArrayList<Component>) meta.lore();
        DataFileHelper dataFileHelper = new DataFileHelper("data.yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        int id = dataFileHelper.getInt("infinity-gauntlets.amount");
        id++;
        lore.set(1, Component.text(id).color(TextColor.color(46, 44, 44)));
        meta.lore(lore);
        item.setItemMeta(meta);
        dataFileHelper.setData("infinity-gauntlets.amount", id);
        dataFileHelper.saveData();
    }
}
