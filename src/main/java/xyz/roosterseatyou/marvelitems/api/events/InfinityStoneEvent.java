package xyz.roosterseatyou.marvelitems.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;

public class InfinityStoneEvent extends Event {
    private Player user;
    private StoneType type;
    private static final HandlerList handlers = new HandlerList();

    public InfinityStoneEvent(Player user, StoneType type) {
        this.user = user;
        this.type = type;
    }

    public Player getPlayer() {
        return user;
    }

    public StoneType getType() {
        return type;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
