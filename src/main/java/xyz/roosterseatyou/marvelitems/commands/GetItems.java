package xyz.roosterseatyou.marvelitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.roosterseatyou.marvelitems.items.ironman.IronManBoots;
import xyz.roosterseatyou.marvelitems.items.ironman.IronManChestplate;
import xyz.roosterseatyou.marvelitems.items.ironman.IronManHelm;
import xyz.roosterseatyou.marvelitems.items.ironman.IronManLeggings;

public class GetItems implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().addItem(IronManHelm.IRON_MAN_HELM);
            player.getInventory().addItem(IronManChestplate.IRON_MAN_CHEST);
            player.getInventory().addItem(IronManLeggings.IRON_MAN_LEGS);
            player.getInventory().addItem(IronManBoots.IRON_MAN_BOOTS);
        }
        return false;
    }
}
