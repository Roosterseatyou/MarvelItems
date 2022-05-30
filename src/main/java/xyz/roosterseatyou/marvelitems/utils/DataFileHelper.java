package xyz.roosterseatyou.marvelitems.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.Base64;

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
        try (final BukkitObjectOutputStream stream = new BukkitObjectOutputStream(new ByteArrayOutputStream())) {
            stream.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); i++) {
                stream.writeObject(inventory.getItem(i));
            }
            data.set(path, stream.toString());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadInventory(String path, final Inventory inventory) {
        final String encodedString = data.getString(path);
        try (final BukkitObjectInputStream data = new BukkitObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(encodedString)))) {
            final int invSize = data.readInt();
            for (int i = 0; i < invSize; i++) {
                inventory.setItem(i, (ItemStack) data.readObject());
            }
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createPath(String path) {
        data.createSection(path);
    }

    public void removePath(String path) {
        data.set(path, null);
    }
}
