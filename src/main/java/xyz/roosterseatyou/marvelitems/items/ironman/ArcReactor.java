package xyz.roosterseatyou.marvelitems.items.ironman;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.items.metals.PalladiumIngot;

import java.util.ArrayList;
import java.util.List;

public class ArcReactor {
    public static Component NAME = Component.text("Arc Reactor");
    public static Component SERVER_ID = Component.text("SERVER_ID: ARC_REACTOR").color(TextColor.color(46, 44, 44));

    public static ItemStack ARC_REACTOR;

    public static ItemStack init() {
        load();
        return null;
    }

    public static void load() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        meta.displayName(NAME);
        lore.add(SERVER_ID);
        meta.lore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
        ARC_REACTOR = item;

        //recipes
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(MarvelItems.getInstance(), "arc_reactor"), item);
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('B', PalladiumIngot.PALLADIUM_INGOT);
        MarvelItems.getInstance().getServer().addRecipe(recipe);
    }

}
