package net.mcpueblo.portalcalc;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PortalCalcCommand implements CommandExecutor {

    private final PortalCalc plugin;

    public PortalCalcCommand(PortalCalc instance) {
        plugin = instance;
    }

    private ChatColor getCoordColor() {
        String colorName = plugin.getConfig().getString("coord-color", null);

        if (colorName == null) {
            return ChatColor.WHITE;
        }
        if (colorName.isEmpty()) {
            return ChatColor.WHITE;
        }

        return ChatColor.getByChar(colorName);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        int calcBy = (int) Math.round(plugin.getConfig().getDouble("calculate-coord-by"));
        String calcMessage = plugin.getConfig().getString("calc-message");
        String resultMessage = plugin.getConfig().getString("result-message");
        String wrongDimensionError = plugin.getConfig().getString("wrong-dimension-error");
        String noPermissionError = plugin.getConfig().getString("no-permission-error");
        String runFromConsoleError = plugin.getConfig().getString("run-from-console-error");
        String coordColor = plugin.getConfig().getString("coord-color");

        if (cmd.getName().equalsIgnoreCase("portalcalc") && sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            if (player.hasPermission("portalcalc.calc")) {
                if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                    int portalx = x / calcBy;
                    int portalz = z / calcBy;
                    String netherCalcMessage = calcMessage.replace("<dimension>", "nether");
                    String netherResultMessage = resultMessage.replace("<dimension>", "nether");
                    String calcCoords = "X: " + x + " Y: " + y + " Z: " + z;
                    String resultCoords = "X: " + portalx + " Y: " + y + " Z: " + portalz;

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', netherCalcMessage));
                    sender.sendMessage(getCoordColor() + calcCoords);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', netherResultMessage));
                    sender.sendMessage(getCoordColor() + resultCoords);

                    return true;
                } else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                    int portalx = x * calcBy;
                    int portalz = z * calcBy;
                    String overworldCalcMessage = calcMessage.replace("<dimension>", "overworld");
                    String overworldResultMessage = resultMessage.replace("<dimension>", "overworld");
                    String calcCoords = "X: " + x + " Y: " + y + " Z: " + z;
                    String resultCoords = "X: " + portalx + " Y: " + y + " Z: " + portalz;

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', coordColor + overworldCalcMessage));
                    sender.sendMessage(getCoordColor() + calcCoords);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', coordColor + overworldResultMessage));
                    sender.sendMessage(getCoordColor() + resultCoords);

                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', wrongDimensionError));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissionError));
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("portalcalc") && sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',runFromConsoleError));
            return true;
        }
        return false;
    }
}
