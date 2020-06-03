package net.mcpueblo.portalcalc.cmd;

import net.mcpueblo.portalcalc.PortalCalc;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PortalCalcReload implements CommandExecutor {

    private final PortalCalc plugin;
    public PortalCalcReload(PortalCalc instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("portalcalcreload") && sender.hasPermission("portalcalc.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GOLD + "Configuration and messages have been reloaded.");
            return true;
        }
        return false;
    }
}
