package xyz.roosterseatyou.marvelitems.api.events;

import org.bukkit.entity.Player;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;

public class InfinityStoneEnterGauntletEvent extends InfinityStoneEvent {
    public InfinityStoneEnterGauntletEvent(Player player, StoneType stoneType) {
        super(player, stoneType);
    }
}
