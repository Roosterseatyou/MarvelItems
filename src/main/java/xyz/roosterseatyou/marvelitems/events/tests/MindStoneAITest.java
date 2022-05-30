package xyz.roosterseatyou.marvelitems.events.tests;

import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.mobgoals.MindStoneControl;

public class MindStoneAITest implements Listener {

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Mob mob = (Mob) event.getEntity();
            MindStoneControl control = new MindStoneControl(mob, player);
            Bukkit.getMobGoals().addGoal(mob, 1, control);
            MarvelItems.logger().info("Added mob goal successfully to mob " + mob.getName());
        }
    }
}
