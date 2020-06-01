package net.mcpueblo.portalcalc;

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

        if (!cmdName.equals("portalcalc")) {
            return false;
        }
        sender.sendMessage("You are at X: " + x + " Y: " + y + " Z: " + z);

        return true;
    }
}
