package xyz.roosterseatyou.marvelitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.roosterseatyou.marvelitems.items.infinitystones.*;

public class GetStones implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("You can't use this command from console!");
            return true;
        }
        if(!sender.hasPermission("marvelitems.getstones")) {
            sender.sendMessage("You don't have permission to use this command!");
            return true;
        }
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().addItem(MindStone.MIND_STONE);
            player.getInventory().addItem(PowerStone.POWER_STONE);
            player.getInventory().addItem(RealityStone.REALITY_STONE);
            player.getInventory().addItem(SoulStone.SOUL_STONE);
            player.getInventory().addItem(SpaceStone.SPACE_STONE);
            player.getInventory().addItem(TimeStone.TIME_STONE);
            player.sendMessage("You have received all Infinity Stones! PROCEED WITH CAUTION!");
        }
        return true;
    }
}
