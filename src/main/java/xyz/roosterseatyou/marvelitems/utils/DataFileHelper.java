package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import xyz.roosterseatyou.marvelitems.MarvelItems;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataFileHelper {
    public String fileName;
    public String filePath;
    private static File file;
    private static YamlConfiguration data;
    private Plugin plugin;

    public DataFileHelper(String fileName, String filePath, Plugin plugin) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.plugin = plugin;
        file = new File(filePath + fileName);
        data = YamlConfiguration.loadConfiguration(file);
    }

    public boolean dataContainsPlayer(Player player) {
        return data.contains(player.getUniqueId().toString());
    }

    public void setData(Player player, String key, Object value) {
        data.set(player.getUniqueId().toString() + "." + key, value);
    }

    public void setData(String path, Object value) {
        data.set(path, value);
    }

    public Object getData(Player player, String key) {
        return data.get(player.getUniqueId().toString() + "." + key);
    }

    public Object getData(String path) {
        return data.get(path);
    }

    public void saveData() {
        try {
            data.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save data to data file " + fileName + "! Check the error below:");
            e.printStackTrace();
        }
    }

    public void saveInventory(String path, Inventory inventory) {
        data.set(path, inventory.getContents());
        MarvelItems.logger().info("Saved inventory to " + data.getName());
    }

    @SuppressWarnings("unchecked")
    public void loadInventory(String path, final Inventory inventory) {
        ItemStack[] content;
        content = ((List<ItemStack>) data.get("inventory")).toArray(new ItemStack[0]);
        inventory.setContents(content);
        MarvelItems.logger().info("Loaded inventory from " + data.getName());
    }

    public void createPath(String path) {
        data.createSection(path);
    }

    public void removePath(String path) {
        data.set(path, null);
    }
}
