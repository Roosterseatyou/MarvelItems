package xyz.roosterseatyou.marvelitems.api.events;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InfinityStoneEvent {
    private ItemStack gauntlet;
    private Player user;
    enum StoneType {
        MIND,
        POWER,
        REALITY,
        TIME,
        SPACE,
        SOUL
    }
    private StoneType type;

    public InfinityStoneEvent(ItemStack gauntlet, Player user, StoneType type) {
        this.gauntlet = gauntlet;
        this.user = user;
        this.type = type;
    }

    public ItemStack getGauntlet() {
        return gauntlet;
    }

    public Player getUser() {
        return user;
    }

    public StoneType getType() {
        return type;
    }
}
