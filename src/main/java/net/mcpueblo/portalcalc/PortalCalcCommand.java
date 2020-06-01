package net.mcpueblo.portalcalc;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalCalcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();
        Player player = (Player) sender;
        Location loc = player.getLocation();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        int netherx = x / 8;
        int netherz = z / 8;

        if (!cmdName.equals("portalcalc")) {
            return false;
        }
        sender.sendMessage(ChatColor.GOLD + "Recommended portal location for " + "X: " + x + " Y: " + y + " Z: " + z);
        sender.sendMessage(ChatColor.GOLD + "X: " + netherx + " Y: " + y + " Z: " + netherz);

        return true;
    }
}
