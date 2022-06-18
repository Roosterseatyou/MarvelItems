package xyz.roosterseatyou.marvelitems.events.items.ironman;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;
import xyz.roosterseatyou.marvelitems.utils.MarvelUtils;
import xyz.roosterseatyou.marvelitems.utils.MathUtils;

import java.util.*;

public class IronManListeners implements Listener {
    private static final Map<UUID, Boolean> doubleJump = new HashMap<>();
    private static final Map<UUID, Integer> helmStuff = new HashMap<>();
    private static final Map<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isIronManArmor(e.getNewItem())) return;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String id = serializer.serialize(MarvelUtils.getID(e.getNewItem()));
        if (id.endsWith("_BOOTS") || id.endsWith("_CHEST")) {
            if (!MarvelUtils.isIronManArmor(p.getInventory().getBoots()) ||
                    !MarvelUtils.isIronManArmor(p.getInventory().getChestplate())) {
                doubleJump.remove(p.getUniqueId());
                p.setAllowFlight(false);
            } else {
                doubleJump.put(p.getUniqueId(), true);
                p.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (!doubleJump.containsKey(p.getUniqueId())) return;
        if (!doubleJump.get(p.getUniqueId())) return;
        e.setCancelled(true);
        if(!MathUtils.cooldownHelper(cooldown, p, 3*1000)) return;
        p.setVelocity(p.getVelocity().setY(2));
    }

    @EventHandler
    public void onHelmEquip(PlayerArmorChangeEvent e) {
        Player p = e.getPlayer();
        if (!MarvelUtils.isIronManArmor(e.getNewItem())) return;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        String id = serializer.serialize(MarvelUtils.getID(e.getNewItem()));
        if (id.endsWith("_HELM")) {
            helmStuff.put(p.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(MarvelItems.getInstance(), () -> {
                if (p.getInventory().getHelmet() == null) return;
                if (!MarvelUtils.isIronManArmor(p.getInventory().getHelmet())) return;
                ArrayList<Entity> nearbyEntities = (ArrayList<Entity>) p.getNearbyEntities(20, 20, 20);
                double closestDist = Double.MAX_VALUE;
                Entity closest = null;
                for (Entity ent : nearbyEntities) {
                    if (ListContainer.getHostiles().contains(ent.getType())) {
                        double dist = p.getLocation().distance(ent.getLocation());
                        if (dist < closestDist) {
                            closestDist = dist;
                            closest = ent;
                        }
                        p.sendActionBar(Component.text("Hostile: " + closest.getType().name() + " located " + Math.round(p.getLocation().distance(closest.getLocation())) + " blocks away")
                                .color(TextColor.color(255, 0, 0)));
                    }
                }
            }, 5L, 5L));
        }
        //remove the task if the player unequips the helm
        if(e.getOldItem() == null) return;
        if(e.getOldItem().lore() == null) return;
        id = serializer.serialize(MarvelUtils.getID(e.getOldItem()));
        if (MarvelUtils.isIronManArmor(e.getOldItem()) && id.endsWith("_HELM")) {
            Bukkit.getScheduler().cancelTask(helmStuff.get(p.getUniqueId()));
            helmStuff.remove(p.getUniqueId());
        }
    }
}
