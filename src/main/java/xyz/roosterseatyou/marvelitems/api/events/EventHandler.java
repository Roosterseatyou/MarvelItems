package xyz.roosterseatyou.marvelitems.api.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

public class EventHandler implements Listener {

    @org.bukkit.event.EventHandler
    public void handleStoneEnter(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;
        if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            if(e.getCurrentItem().lore() == null) return;
            StoneType type = MarvelUtils.getStoneTypeFromItem(e.getCurrentItem());
            if(type == null) return;
            Bukkit.getPluginManager().callEvent(new InfinityStoneEnterGauntletEvent((Player) e.getWhoClicked(), type));
        }
    }
}
