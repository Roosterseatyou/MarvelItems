package xyz.roosterseatyou.marvelitems.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;

import java.util.ArrayList;

public class MarvelUtils {
    public static boolean isInfGauntlet(ItemStack item) {
        return item != null && item.lore() != null && item.lore().contains(InfinityGauntlet.SERVER_ID);
    }

    public static boolean isWearingInfGauntlet(Player p) {
        return isInfGauntlet(p.getInventory().getChestplate());
    }

    public static ArrayList<StoneType> getStonesInGauntlet(ItemStack item) {
        if(!isInfGauntlet(item)) return null;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String id = serializer.serialize(item.lore().get(1));
        DataFileHelper dataFileHelper = new DataFileHelper(id + ".yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        ItemStack[] invC = dataFileHelper.getInventory("inventory");
        ArrayList<StoneType> stones = new ArrayList<>(invC.length);
        MarvelItems.logger().info("invC.length: " + invC.length);
        MarvelItems.logger().info("Stones: " + stones.size());
        for (ItemStack itemStack : invC) {
            if (itemStack == null) continue;
            stones.add(getStoneTypeFromItem(itemStack));
        }
        return stones;
    }

    public static StoneType getStoneTypeFromItem(ItemStack item) {
        if(item == null) return null;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String id = serializer.serialize(item.lore().get(1));
        id = id.replaceAll("SERVER_ID: ", "");
        return StoneType.valueOf(id);
    }
}
