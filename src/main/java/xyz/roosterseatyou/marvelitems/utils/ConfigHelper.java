package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.plugin.Plugin;

public class ConfigHelper {
    private Plugin plugin;

    public ConfigHelper(Plugin plugin) {
        this.plugin = plugin;
    }

    public void reloadConfig() {
        plugin.reloadConfig();
    }

    public void saveConfig() {
        plugin.saveConfig();
    }

    public void setData(String path, Object value) {
        plugin.getConfig().set(path, value);
    }

    public Object getData(String path) {
        return plugin.getConfig().get(path);
    }

    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }
}
