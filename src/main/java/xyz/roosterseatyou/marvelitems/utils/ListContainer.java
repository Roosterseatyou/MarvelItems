package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListContainer {
    public static List<Material> getRealityStoneBlacklist() {
        List<Material> list = new ArrayList<>();
        list.add(Material.NETHERITE_BLOCK);
        list.add(Material.DIAMOND_BLOCK);
        list.add(Material.EMERALD_BLOCK);
        list.add(Material.GOLD_BLOCK);
        list.add(Material.IRON_BLOCK);
        list.add(Material.LAPIS_BLOCK);
        list.add(Material.REDSTONE_BLOCK);
        list.add(Material.COAL_BLOCK);
        list.add(Material.WHEAT);
        list.add(Material.CARROTS);
        list.add(Material.POTATOES);
        list.add(Material.BEETROOTS);
        list.add(Material.SUGAR_CANE);
        list.add(Material.SWEET_BERRIES);
        list.add(Material.COCOA);
        list.add(Material.PUMPKIN);
        list.add(Material.MELON);
        list.add(Material.CACTUS);
        return list;
    }

    public static ArrayList<EntityType> getUndead() {
        ArrayList<EntityType> list = new ArrayList<>();
        list.add(EntityType.ZOMBIE);
        list.add(EntityType.ZOMBIE_VILLAGER);
        list.add(EntityType.SKELETON);
        list.add(EntityType.STRAY);
        list.add(EntityType.WITHER_SKELETON);
        return list;
    }
}
