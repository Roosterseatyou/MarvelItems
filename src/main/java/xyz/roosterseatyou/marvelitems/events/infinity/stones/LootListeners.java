package xyz.roosterseatyou.marvelitems.events.infinity.stones;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.utils.DataFileHelper;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

import java.util.ArrayList;

public class LootListeners implements Listener {
    @EventHandler
    public void onLootGen(LootGenerateEvent e) {
        InventoryHolder holder = e.getInventoryHolder();
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(ListContainer.getLootTableWhitelist().contains(e.getLootTable().getKey())) {
                if(!MathUtils.rngHelper(0.01)) return;
                Bukkit.broadcast(Component.text("[STONE ALERT]: " + p.getName() + " has found a stone...").color(TextColor.color(69, 2, 5)));
                placeStone(holder);
            }
        }
    }

    public static void placeStone(InventoryHolder inv) {
        DataFileHelper d = new DataFileHelper("data.yml", MarvelItems.getInstance().getDataFolder().getPath(), MarvelItems.getInstance());
        ArrayList<ItemStack> stones = ListContainer.getMissingStones();
        if(stones.isEmpty()) return;
        ItemStack i = (ItemStack) ListContainer.getRand(stones);
        StoneType type = MarvelUtils.getStoneTypeFromItem(i);
        if(type == null) return;
        inv.getInventory().addItem(i);
        d.getYaml().set("infinity-stones." + type.toString().toLowerCase() + "has-been-found", true);
        d.saveData();
    }



}
