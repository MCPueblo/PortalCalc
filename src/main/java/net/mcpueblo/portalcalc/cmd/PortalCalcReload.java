package net.mcpueblo.portalcalc.cmd;

import de.themoep.minedown.MineDown;
import net.mcpueblo.portalcalc.PortalCalc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class PortalCalcReload implements CommandExecutor {

    private final PortalCalc plugin;
    public PortalCalcReload(PortalCalc instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("portalcalcreload") && sender.hasPermission("portalcalc.reload")) {
            plugin.reloadConfig();
            ConfigurationSection config = plugin.getConfig();
            String reloadMessage = config.getString("reload-message");
            sender.spigot().sendMessage(new MineDown(reloadMessage).toComponent());
            return true;
        }
        return false;
    }
}
