package xyz.roosterseatyou.marvelitems.events.items;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

public class MetalMineListeners implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getBlock().getType().equals(Material.IRON_ORE)) {
            if(MathUtils.rngHelper(5)) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), (ItemStack) ListContainer.getRand(ListContainer.getMetals()));
            }
        }
    }
}
