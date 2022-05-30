package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;

public class MarvelUtils {
    public static boolean isInfGauntlet(ItemStack item) {
        return item != null && item.lore() != null && item.lore().contains(InfinityGauntlet.SERVER_ID);
    }
}
