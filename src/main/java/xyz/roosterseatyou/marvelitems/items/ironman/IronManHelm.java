package xyz.roosterseatyou.marvelitems.items.ironman;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.items.metals.TitaniumIngot;

import java.util.ArrayList;
import java.util.List;

public class IronManHelm {
    public static Component NAME = Component.text("Iron Man's Helmet");
    public static Component SERVER_ID = Component.text("SERVER_ID: IRON_MAN_HELM").color(TextColor.color(46, 44, 44));
    public static ItemStack IRON_MAN_HELM;

    public static ItemStack init() {
        load();
        return null;
    }

    public static void load() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(SERVER_ID);
        meta.lore(lore);
        item.setItemMeta(meta);
        IRON_MAN_HELM = item;

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(MarvelItems.getInstance(), "ironman_helm"), item);
        recipe.shape("GGG", "TAT", "   ");
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('T', TitaniumIngot.TITANIUM_INGOT);
        recipe.setIngredient('A', ArcReactor.ARC_REACTOR);
        MarvelItems.getInstance().getServer().addRecipe(recipe);
    }
}
