package xyz.roosterseatyou.marvelitems.events.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

public class LootPopulation implements Listener {

    @EventHandler
    public void populateMetals(LootGenerateEvent e) {
        if(!ListContainer.getMetalLootTableWhitelist().contains(e.getLootTable().getKey())) return;
        if(!MathUtils.rngHelper(3)) return;
        e.getInventoryHolder().getInventory().addItem((ItemStack) ListContainer.getRand(ListContainer.getMetals()));
    }
}
