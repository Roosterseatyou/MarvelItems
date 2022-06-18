package xyz.roosterseatyou.marvelitems;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.roosterseatyou.marvelitems.api.events.EventHandler;
import xyz.roosterseatyou.marvelitems.commands.GetItems;
import xyz.roosterseatyou.marvelitems.commands.GetStones;
import xyz.roosterseatyou.marvelitems.events.items.LootPopulation;
import xyz.roosterseatyou.marvelitems.events.items.MetalMineListeners;
import xyz.roosterseatyou.marvelitems.events.items.infinity.GauntletEvents;
import xyz.roosterseatyou.marvelitems.events.items.infinity.stones.*;
import xyz.roosterseatyou.marvelitems.events.items.ironman.IronManListeners;
import xyz.roosterseatyou.marvelitems.items.infinitygauntlet.InfinityGauntlet;
import xyz.roosterseatyou.marvelitems.items.infinitystones.*;
import xyz.roosterseatyou.marvelitems.items.ironman.*;
import xyz.roosterseatyou.marvelitems.items.metals.PalladiumIngot;
import xyz.roosterseatyou.marvelitems.items.metals.TitaniumIngot;

import java.util.logging.Logger;

public final class MarvelItems extends JavaPlugin {
    private static Plugin instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger = getLogger();
        logger.info("MarvelItems Enabled");
        saveResource("data.yml", false);
        loadItems();
        getServer().getPluginManager().registerEvents(new GauntletEvents(), this);
        getServer().getPluginManager().registerEvents(new MindStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new RealityStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new TimeStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new EventHandler(), this);
        getServer().getPluginManager().registerEvents(new SoulStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new PowerStoneListeners(), this);
        getServer().getPluginManager().registerEvents(new LootListeners(), this);
        //Space Stone coming soon once I get some more ideas...
        getServer().getPluginManager().registerEvents(new IronManListeners(), this);
        getServer().getPluginManager().registerEvents(new LootPopulation(), this);
        getServer().getPluginManager().registerEvents(new MetalMineListeners(), this);
        getCommand("getstones").setExecutor(new GetStones());
        getCommand("getitems").setExecutor(new GetItems());
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
        TitaniumIngot.init();
        PalladiumIngot.init();
        ArcReactor.init();
        
        IronManBoots.init();
        IronManChestplate.init();
        IronManHelm.init();
        IronManLeggings.init();
    }
}
