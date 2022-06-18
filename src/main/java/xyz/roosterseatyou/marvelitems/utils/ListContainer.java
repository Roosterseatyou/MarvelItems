package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.items.infinitystones.*;

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

    public static ArrayList<NamespacedKey> getLootTableWhitelist() {
        ArrayList<NamespacedKey> list = new ArrayList<>();
        list.add(LootTables.BASTION_BRIDGE.getKey());
        list.add(LootTables.BASTION_TREASURE.getKey());
        list.add(LootTables.BASTION_OTHER.getKey());
        list.add(LootTables.BASTION_HOGLIN_STABLE.getKey());
        list.add(LootTables.BURIED_TREASURE.getKey());
        list.add(LootTables.FISHING_TREASURE.getKey());
        list.add(LootTables.VILLAGE_TEMPLE.getKey());
        list.add(LootTables.WOODLAND_MANSION.getKey());
        list.add(LootTables.JUNGLE_TEMPLE.getKey());
        list.add(LootTables.VILLAGE_WEAPONSMITH.getKey());
        list.add(LootTables.VILLAGE_ARMORER.getKey());
        list.add(LootTables.VILLAGE_BUTCHER.getKey());
        list.add(LootTables.VILLAGE_CARTOGRAPHER.getKey());
        list.add(LootTables.VILLAGE_FISHER.getKey());
        list.add(LootTables.VILLAGE_DESERT_HOUSE.getKey());
        list.add(LootTables.VILLAGE_SAVANNA_HOUSE.getKey());
        list.add(LootTables.VILLAGE_TAIGA_HOUSE.getKey());
        list.add(LootTables.VILLAGE_PLAINS_HOUSE.getKey());
        list.add(LootTables.VILLAGE_FLETCHER.getKey());
        list.add(LootTables.VILLAGE_MASON.getKey());
        list.add(LootTables.VILLAGE_SHEPHERD.getKey());
        list.add(LootTables.VILLAGE_TANNERY.getKey());
        list.add(LootTables.VILLAGE_TOOLSMITH.getKey());
        list.add(LootTables.UNDERWATER_RUIN_SMALL.getKey());
        list.add(LootTables.UNDERWATER_RUIN_BIG.getKey());
        list.add(LootTables.STRONGHOLD_LIBRARY.getKey());
        list.add(LootTables.STRONGHOLD_CROSSING.getKey());
        list.add(LootTables.STRONGHOLD_CORRIDOR.getKey());
        list.add(LootTables.SIMPLE_DUNGEON.getKey());
        list.add(LootTables.ANCIENT_CITY.getKey());
        list.add(LootTables.DESERT_PYRAMID.getKey());
        list.add(LootTables.ABANDONED_MINESHAFT.getKey());
        list.add(LootTables.END_CITY_TREASURE.getKey());
        list.add(LootTables.SHIPWRECK_TREASURE.getKey());
        list.add(LootTables.SHIPWRECK_MAP.getKey());
        list.add(LootTables.SHIPWRECK_SUPPLY.getKey());
        return list;
    }

    public static ArrayList<ItemStack> getStones() {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(SoulStone.SOUL_STONE);
        list.add(PowerStone.POWER_STONE);
        list.add(RealityStone.REALITY_STONE);
        list.add(TimeStone.TIME_STONE);
        list.add(MindStone.MIND_STONE);
        return list;
    }

    public static Object getRand(ArrayList list) {
        return list.get((int) (Math.random() * list.size()));
    }

    public static ArrayList<ItemStack> getMissingStones() {
        ArrayList<ItemStack> list = new ArrayList<>();
        DataFileHelper d = new DataFileHelper("data.yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());

        for (ItemStack item : getStones()) {
            StoneType type = MarvelUtils.getStoneTypeFromItem(item);
            if (!d.getYaml().getBoolean("infinity-stones." + type.toString().toLowerCase() + ".has-been-found")) {
                list.add(item);
            }

        }
        return list;
    }

    public static ArrayList<EntityType> getAsgardianWolfTargets() {
        ArrayList<EntityType> list = new ArrayList<>();
        list.add(EntityType.ZOMBIE);
        list.add(EntityType.ZOMBIE_VILLAGER);
        list.add(EntityType.SKELETON);
        list.add(EntityType.STRAY);
        list.add(EntityType.HUSK);
        list.add(EntityType.WITCH);
        list.add(EntityType.VILLAGER);
        list.add(EntityType.ENDERMAN);
        list.add(EntityType.BLAZE);
        list.add(EntityType.PLAYER);
        list.add(EntityType.WITHER_SKELETON);
        return list;
    }

    public static ArrayList<EntityType> getHostiles() {
        ArrayList<EntityType> list = new ArrayList<>();
        list.add(EntityType.ZOMBIE);
        list.add(EntityType.ZOMBIE_VILLAGER);
        list.add(EntityType.SKELETON);
        list.add(EntityType.HUSK);
        list.add(EntityType.WITCH);
        list.add(EntityType.ENDERMAN);
        list.add(EntityType.BLAZE);
        list.add(EntityType.PLAYER);
        list.add(EntityType.WITHER_SKELETON);
        list.add(EntityType.WITHER);
        list.add(EntityType.ENDER_DRAGON);
        list.add(EntityType.CREEPER);
        list.add(EntityType.SILVERFISH);
        list.add(EntityType.ENDERMITE);
        list.add(EntityType.GHAST);
        list.add(EntityType.MAGMA_CUBE);
        list.add(EntityType.SLIME);
        list.add(EntityType.WARDEN);
        list.add(EntityType.SHULKER);
        list.add(EntityType.SHULKER_BULLET);
        list.add(EntityType.SPIDER);
        list.add(EntityType.CAVE_SPIDER);
        list.add(EntityType.RAVAGER);
        list.add(EntityType.PIGLIN_BRUTE);
        list.add(EntityType.PIGLIN);
        list.add(EntityType.STRAY);
        list.add(EntityType.ENDERMAN);
        return list;
    }
}
