package xyz.roosterseatyou.marvelitems.utils;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.marvelitems.MarvelItems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOHelper {

    public static File createYMLFile(String fileName, String firstPath) {
        File resourceFolder = MarvelItems.getInstance().getDataFolder();
        File file = new File(resourceFolder, fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(firstPath);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MarvelItems.logger().info("Creating file: " + file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                MarvelItems.logger().severe("Could not create file: " + fileName);
            }
        }
        MarvelItems.logger().info("File created: " + file.getAbsolutePath());
        return file;
    }

    public static File getGauntletFile(ItemStack item) {
        if(!MarvelUtils.isInfGauntlet(item)) return null;
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        return new File(MarvelItems.getInstance().getDataFolder(), serializer.serialize(item.lore().get(1)) + ".yml");
    }
}
