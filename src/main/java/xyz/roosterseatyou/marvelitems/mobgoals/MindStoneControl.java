package xyz.roosterseatyou.marvelitems.mobgoals;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.roosterseatyou.marvelitems.MarvelItems;

import java.util.Collection;
import java.util.EnumSet;

public class MindStoneControl implements Goal<Mob> {
    private final GoalKey<Mob> goalKey;
    private final Mob mob;
    private final Player playerToIgnore;
    private Player currentAttackTarget;
    private int cooldown;

    public MindStoneControl(@NotNull Mob mob, @NotNull Player playerToIgnore) {
        this.mob = mob;
        this.goalKey = GoalKey.of(Mob.class, new NamespacedKey(MarvelItems.getInstance(), "mind_stone_control"));
        this.playerToIgnore = playerToIgnore;
        this.mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() +0.15);
    }

    @Override
    public void tick() {
        if(currentAttackTarget == null) return;
        mob.setTarget(currentAttackTarget);
        if(mob.getLocation().distanceSquared(currentAttackTarget.getLocation()) <= 2.0) {
            mob.getPathfinder().stopPathfinding();
            if(cooldown <= 0) {
                cooldown = 10;
                currentAttackTarget.damage(4.0);
            }
        } else {
            mob.getPathfinder().moveTo(currentAttackTarget.getLocation());
        }
        if(currentAttackTarget.isDead() || currentAttackTarget.getHealth() <= 0) {
            currentAttackTarget = null;
            mob.setTarget(null);
            mob.getWorld().playSound(Sound.sound(Key.key("entity.ender_dragon.growl"), Sound.Source.HOSTILE, 100f, 0f));
        }
    }

    @Override
    public boolean shouldStayActive() {
        return shouldActivate();
    }

    @Override
    public boolean shouldActivate() {
        if(cooldown > 0) {
            cooldown--;
            return false;
        }
        currentAttackTarget = getNearestPlayer();
        if(currentAttackTarget == null) return false;
        return true;
    }

    @Override
    public @NotNull GoalKey<Mob> getKey() {
        return goalKey;
    }

    @Override
    public @NotNull EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE);
    }

    public Player getNearestPlayer() {
        Collection<Player> near = mob.getWorld().getNearbyPlayers(mob.getLocation(), 20, p -> !p.equals(playerToIgnore));
        if(near.isEmpty()) return null;
        double closestDist = -1.0;
        Player closestPlayer = null;
        for(Player p : near) {
            double dist = p.getLocation().distanceSquared(mob.getLocation());
            if(!(dist < closestDist) && closestDist != -1.0) continue;
            closestDist = dist;
            closestPlayer = p;
        }
        return closestPlayer;
    }
}
