package xyz.roosterseatyou.marvelitems.events.infinity.stones;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.api.events.InfinityStoneEnterGauntletEvent;

public class PowerStoneListeners implements Listener {
    @EventHandler
    public void onPowerEquip(InfinityStoneEnterGauntletEvent event) {
        Player p = event.getPlayer();
        StoneType stoneType = event.getType();
        if(stoneType == StoneType.POWER_STONE) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
        }
    }
}
