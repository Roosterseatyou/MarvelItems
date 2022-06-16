package xyz.roosterseatyou.marvelitems.events.items.ironman;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IronManListeners implements Listener {
    private static final Map<UUID, Boolean> doubleJump = new HashMap<>();

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent e) {
        Player p = e.getPlayer();
        if(!MarvelUtils.isIronManArmor(e.getNewItem())) return;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String id = serializer.serialize(MarvelUtils.getID(e.getNewItem()));
        if(id.endsWith("_BOOTS") || id.endsWith("_CHEST")) {
            if(!MarvelUtils.isIronManArmor(p.getInventory().getBoots()) ||
                    !MarvelUtils.isIronManArmor(p.getInventory().getChestplate())) doubleJump.remove(p.getUniqueId());
            else {
                doubleJump.put(p.getUniqueId(), true);
                p.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(!doubleJump.containsKey(p.getUniqueId())) return;
        if(!doubleJump.get(p.getUniqueId())) return;
        e.setCancelled(true);
        p.setVelocity(p.getVelocity().setY(0.5));
    }
}
