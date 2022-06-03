package xyz.roosterseatyou.marvelitems;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.roosterseatyou.marvelitems.commands.GetStones;
import xyz.roosterseatyou.marvelitems.events.infinity.GauntletEvents;
import xyz.roosterseatyou.marvelitems.events.infinity.stones.MindStoneListeners;
import xyz.roosterseatyou.marvelitems.events.infinity.stones.RealityStoneListeners;
import xyz.roosterseatyou.marvelitems.events.tests.MindStoneAITest;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;
import xyz.roosterseatyou.marvelitems.items.infinitystones.*;

import java.util.logging.Logger;

public final class MarvelItems extends JavaPlugin {
    private static Plugin instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger = getLogger();
        logger.info("MarvelItems enabled");
        saveResource("data.yml", false);
        loadItems();
        getServer().getPluginManager().registerEvents(new GauntletEvents(), this);
        getServer().getPluginManager().registerEvents(new MindStoneAITest(), this);
        getServer().getPluginManager().registerEvents(new MindStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new RealityStoneListeners(), this);
        getCommand("getstones").setExecutor(new GetStones());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static Logger logger() {
        return logger;
    }

    private static void loadItems() {
        InfinityGauntlet.init();
        MindStone.init();
        PowerStone.init();
        SoulStone.init();
        SpaceStone.init();
        TimeStone.init();
        RealityStone.init();

    }
}
