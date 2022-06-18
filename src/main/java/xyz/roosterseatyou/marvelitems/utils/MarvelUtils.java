package xyz.roosterseatyou.marvelitems.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
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

    public static boolean isInfStone(ItemStack item) {
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        return item != null && item.lore() != null && serializer.serialize(item.lore().get(1)).endsWith("_STONE");
    }

    public static Component getID(ItemStack item) {
        if(item == null) return null;
        if(item.lore() == null) return null;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        for(Component c : item.lore()) {
            if(serializer.serialize(c).contains("SERVER_ID")) return c;
        }
        return null;
    }

    public static boolean isIronManArmor(ItemStack item) {
        if (item == null) return false;
        if (item.lore() == null) return false;
        Component id = getID(item);
        if (id == null) return false;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String idString = serializer.serialize(id);
        return idString.contains("SERVER_ID: IRON_MAN");
    }
}
