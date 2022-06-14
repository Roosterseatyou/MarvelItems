package xyz.roosterseatyou.marvelitems.events.items.infinity.stones;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

import java.util.List;

public class SoulStoneListeners implements Listener {
    @EventHandler
    public void onPlayerLeftClick(PlayerInteractEvent event) {
        if(!event.getAction().isLeftClick()) return;
        if(!MarvelUtils.isWearingInfGauntlet(event.getPlayer())) return;
        Player p = event.getPlayer();
        List<StoneType> types = MarvelUtils.getStonesInGauntlet(p.getInventory().getChestplate());
        if(!types.contains(StoneType.SOUL_STONE)) return;
        event.setCancelled(true);
        for(EntityType type : ListContainer.getUndead()) {
            if (MathUtils.rngHelper(50)) p.getWorld().spawnEntity(p.getLocation(), type);
        }
    }
}

