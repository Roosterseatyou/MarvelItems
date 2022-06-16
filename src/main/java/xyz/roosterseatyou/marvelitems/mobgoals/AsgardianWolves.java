package xyz.roosterseatyou.marvelitems.mobgoals;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;
import xyz.roosterseatyou.marvelitems.MarvelItems;
import xyz.roosterseatyou.marvelitems.utils.ListContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

public class AsgardianWolves implements Goal<Wolf> {
    private final GoalKey<Wolf> goalKey;
    private final Mob mob;
    private int cooldown;

    private static Creature target;

    public AsgardianWolves(Mob mob) {
        this.mob = mob;
        goalKey = GoalKey.of(Wolf.class, new NamespacedKey(MarvelItems.getInstance(), "asgardian_wolves"));
        mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue() * 1.5);
    }

    @Override
    public void tick() {
        target = locateTarget();
        if(target == null) return;
        mob.setTarget(target);
        if(mob.getLocation().distanceSquared(target.getLocation()) <= 2.0) {
            mob.getPathfinder().stopPathfinding();
            if(cooldown <= 0) {
                cooldown = 10;
                target.damage(4.0);
            }
        } else {
            mob.getPathfinder().moveTo(target.getLocation());
        }
    }

    public Creature locateTarget() {
        Collection<Entity> entities = mob.getNearbyEntities(20, 20, 20);
        for(Entity e : entities) {
            if(ListContainer.getAsgardianWolfTargets().contains(e.getType())) {
                target = (Creature) e;
                break;
            }
        }
        return null;
    }

    @Override
    public @NotNull GoalKey<Wolf> getKey() {
        return goalKey;
    }

    @Override
    public @NotNull EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.UNKNOWN_BEHAVIOR);
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
        if(locateTarget() == null) return false;
        return true;
    }
}
