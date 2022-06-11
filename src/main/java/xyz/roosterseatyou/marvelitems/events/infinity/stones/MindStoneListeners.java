package xyz.roosterseatyou.marvelitems.events.infinity.stones;

import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.mobgoals.MindStoneControl;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.util.ArrayList;

public class MindStoneListeners implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isWearingInfGauntlet(p)) return;
        if (!(e.getRightClicked() instanceof Mob)) return;
        Mob m = (Mob) e.getRightClicked();
        ArrayList<StoneType> types = MarvelUtils.getStonesInGauntlet(p.getInventory().getChestplate());
        if(types.isEmpty()) return;
        if(types.contains(StoneType.MIND_STONE)) {
            MindStoneControl control = new MindStoneControl(m, p);
            Bukkit.getMobGoals().removeAllGoals(m);
            Bukkit.getMobGoals().addGoal(m, 1, control);
            Bukkit.getScheduler().scheduleSyncDelayedTask(MarvelItems.getInstance(), () -> m.setKiller(p), 20 * 60);
        }
    }
}
