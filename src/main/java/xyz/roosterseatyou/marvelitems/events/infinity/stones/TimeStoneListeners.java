package xyz.roosterseatyou.marvelitems.events.infinity.stones;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.api.events.InfinityStoneEnterGauntletEvent;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeStoneListeners implements Listener {
    private static final Map<UUID, Location> map = new HashMap<>();

    @EventHandler
    public void onStoneEnter(InfinityStoneEnterGauntletEvent e) {
        Player p = e.getPlayer();
        if (e.getType() == StoneType.TIME_STONE) {
            map.put(p.getUniqueId(), p.getLocation());
            Bukkit.getScheduler().scheduleSyncRepeatingTask(MarvelItems.getInstance(), () -> {
                map.put(p.getUniqueId(), p.getLocation());
            }, 10*20, 20);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isWearingInfGauntlet(p)) return;
        ItemStack gauntlet = p.getInventory().getChestplate();
        if(map.get(p.getUniqueId()) == null) {
            map.put(p.getUniqueId(), p.getLocation());
            Bukkit.getScheduler().scheduleSyncRepeatingTask(MarvelItems.getInstance(), () -> {
                map.put(p.getUniqueId(), p.getLocation());
            }, 10*20, 20);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isWearingInfGauntlet(p)) return;
        ItemStack gauntlet = p.getInventory().getChestplate();
        ArrayList<StoneType> types = MarvelUtils.getStonesInGauntlet(gauntlet);
        if(map.get(p.getUniqueId()) == null) MarvelItems.logger().severe("TimeStoneListeners: Player " + p.getName() + " has no location in map!");
        if(p.isSneaking() && types.contains(StoneType.TIME_STONE)) {
            Location loc = map.get(p.getUniqueId());
            p.teleport(loc);
        }
    }
}
