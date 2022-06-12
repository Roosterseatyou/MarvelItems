package xyz.roosterseatyou.marvelitems.api.events;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.PlayerInventory;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.api.enums.StoneType;
import xyz.roosterseatyou.marvelitems.events.infinity.GauntletEvents;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.util.UUID;

public class EventHandler implements Listener {

    @org.bukkit.event.EventHandler
    public void handleStoneEnter(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;
        if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            if(e.getCurrentItem().lore() == null) return;
            StoneType type;
            try {
                type = MarvelUtils.getStoneTypeFromItem(e.getCurrentItem());
            } catch (NullPointerException ex) {
                return;
            }

            InventoryView view = e.getView();
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            boolean isGauntlet;
            try {
                 isGauntlet = GauntletEvents.getIsInv().get(UUID.fromString(serializer.serialize(view.title())));
            } catch (IllegalArgumentException ex) {
                return;
            }

            if(!isGauntlet) return;
            if(!(e.getClickedInventory() instanceof PlayerInventory)) return;
            if(type == null) return;
            Bukkit.getPluginManager().callEvent(new InfinityStoneEnterGauntletEvent((Player) e.getWhoClicked(), type));
        }
    }
}
