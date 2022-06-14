package xyz.roosterseatyou.marvelitems.events.items.infinity.stones;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

import java.util.ArrayList;
import java.util.Collection;

public class RealityStoneListeners implements Listener {
    @EventHandler
    public void onPlayerMine(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isWearingInfGauntlet(p)) return;
        if(ListContainer.getRealityStoneBlacklist().contains(e.getBlock().getType())) return;
        ItemStack gauntlet = p.getInventory().getChestplate();
        ArrayList<StoneType> types = MarvelUtils.getStonesInGauntlet(gauntlet);
        if (types.contains(StoneType.REALITY_STONE)) {
            if (MathUtils.rngHelper(4)) {
                e.setDropItems(false);
                Collection<ItemStack> drops = e.getBlock().getDrops(p.getInventory().getItemInMainHand(), p);
                for (ItemStack drop : drops) {
                    for (int i = 0; i < MathUtils.getRandom(1, 6); i++) {
                        p.getWorld().dropItem(e.getBlock().getLocation(), drop);
                    }
                }
            }
        }
    }
}
