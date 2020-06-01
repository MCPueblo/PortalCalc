package net.mcpueblo.portalcalc;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalCalcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("portalcalc") && sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                int portalx = x / 8;
                int portalz = z / 8;

                sender.sendMessage(ChatColor.GOLD + "Recommended portal location for " + "X: " + x + " Y: " + y + " Z: " + z + " in the nether:");
                sender.sendMessage(ChatColor.GOLD + "X: " + portalx + " Y: " + y + " Z: " + portalz);

                return true;
            } else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                int portalx = x * 8;
                int portalz = z * 8;

                sender.sendMessage(ChatColor.GOLD + "Recommended portal location for " + "X: " + x + " Y: " + y + " Z: " + z + " in the overworld:");
                sender.sendMessage(ChatColor.GOLD + "X: " + portalx + " Y: " + y + " Z: " + portalz);

                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You cannot create nether portals in this world!");

                return true;
            }
        }
        return false;
    }
}
