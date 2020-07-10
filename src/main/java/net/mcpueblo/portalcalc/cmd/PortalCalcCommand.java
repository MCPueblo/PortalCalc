package net.mcpueblo.portalcalc.cmd;

import com.google.common.primitives.Doubles;
import de.themoep.minedown.MineDown;
import net.mcpueblo.portalcalc.PortalCalc;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PortalCalcCommand implements CommandExecutor {

    private final PortalCalc plugin;

    public PortalCalcCommand(PortalCalc instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        ConfigurationSection config = plugin.getConfig();
        int calcBy = (int) Math.round(config.getDouble("calculate-coord-by"));
        String calcMessage = config.getString("calc-message");
        String resultMessage = config.getString("result-message");
        String wrongDimensionError = config.getString("wrong-dimension-error");
        String noPermissionError = config.getString("no-permission-error");
        String runFromConsoleError = ChatColor.RED + "You must run this command with coordinates in the console!";
        String wrongArgError = ChatColor.RED + "/portalcalc [dimension] [x] [y] [z]\nValues for [dimension]: overworld/world, nether";
        String coordDisplay = config.getString("coord-display");

        if (cmd.getName().equalsIgnoreCase("portalcalc") && args.length == 0 && sender instanceof Player) {

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
                    String calcCoords = coordDisplay.replace("<x>", String.valueOf(x))
                            .replace("<y>", String.valueOf(y))
                            .replace("<z>", String.valueOf(z));
                    String resultCoords = coordDisplay.replace("<x>", String.valueOf(portalx))
                            .replace("<y>", String.valueOf(y))
                            .replace("<z>", String.valueOf(portalz));

                    sender.spigot().sendMessage(new MineDown(netherCalcMessage).toComponent());
                    sender.spigot().sendMessage(new MineDown(calcCoords).toComponent());
                    sender.spigot().sendMessage(new MineDown(netherResultMessage).toComponent());
                    sender.spigot().sendMessage(new MineDown(resultCoords).toComponent());

                    return true;
                } else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {

                    int portalx = x * calcBy;
                    int portalz = z * calcBy;
                    String overworldCalcMessage = calcMessage.replace("<dimension>", "overworld");
                    String overworldResultMessage = resultMessage.replace("<dimension>", "overworld");
                    String calcCoords = coordDisplay.replace("<x>", String.valueOf(x))
                            .replace("<y>", String.valueOf(y))
                            .replace("<z>", String.valueOf(z));
                    String resultCoords = coordDisplay.replace("<x>", String.valueOf(portalx))
                            .replace("<y>", String.valueOf(y))
                            .replace("<z>", String.valueOf(portalz));

                    sender.spigot().sendMessage(new MineDown(overworldCalcMessage).toComponent());
                    sender.spigot().sendMessage(new MineDown(calcCoords).toComponent());
                    sender.spigot().sendMessage(new MineDown(overworldResultMessage).toComponent());
                    sender.spigot().sendMessage(new MineDown(resultCoords).toComponent());

                    return true;
                } else {
                    sender.spigot().sendMessage(new MineDown(wrongDimensionError).toComponent());
                    return true;
                }
            } else {
                sender.spigot().sendMessage(new MineDown(noPermissionError).toComponent());
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("portalcalc") && args.length == 0 && sender instanceof ConsoleCommandSender) {
            sender.sendMessage(runFromConsoleError);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("portalcalc")
                && args.length == 4
                && args[0].equalsIgnoreCase("nether")
                && sender.hasPermission("portalcalc.calc")) {

            final Double inputX = Doubles.tryParse(args[1]);
            final Double inputY = Doubles.tryParse(args[2]);
            final Double inputZ = Doubles.tryParse(args[3]);

            if (inputX == null || inputY == null || inputZ == null) {
                sender.sendMessage(wrongArgError);
                return true;
            }

            final int x = (int) Math.floor(inputX);
            final int y = (int) Math.floor(inputY);
            final int z = (int) Math.floor(inputZ);
            final int portalx = x / calcBy;
            final int portalz = z / calcBy;
            String netherCalcMessage = calcMessage.replace("<dimension>", "overworld");
            String netherResultMessage = resultMessage.replace("<dimension>", "overworld");
            String calcCoords = coordDisplay.replace("<x>", String.valueOf(x))
                    .replace("<y>", String.valueOf(y))
                    .replace("<z>", String.valueOf(z));
            String resultCoords = coordDisplay.replace("<x>", String.valueOf(portalx))
                    .replace("<y>", String.valueOf(y))
                    .replace("<z>", String.valueOf(portalz));

            sender.spigot().sendMessage(new MineDown(netherCalcMessage).toComponent());
            sender.spigot().sendMessage(new MineDown(calcCoords).toComponent());
            sender.spigot().sendMessage(new MineDown(netherResultMessage).toComponent());
            sender.spigot().sendMessage(new MineDown(resultCoords).toComponent());

            return true;
        } else if (cmd.getName().equalsIgnoreCase("portalcalc")
                && args.length == 4
                && (args[0].equalsIgnoreCase("overworld") || args[0].equalsIgnoreCase("world"))
                && sender.hasPermission("portalcalc.calc")) {

            final Double inputX = Doubles.tryParse(args[1]);
            final Double inputY = Doubles.tryParse(args[2]);
            final Double inputZ = Doubles.tryParse(args[3]);

            if (inputX == null || inputY == null || inputZ == null) {
                sender.sendMessage(wrongArgError);
                return true;
            }

            final int x = (int) Math.floor(inputX);
            final int y = (int) Math.floor(inputY);
            final int z = (int) Math.floor(inputZ);
            final int portalx = x * calcBy;
            final int portalz = z * calcBy;
            String overworldCalcMessage = calcMessage.replace("<dimension>", "overworld");
            String overworldResultMessage = resultMessage.replace("<dimension>", "overworld");
            String calcCoords = coordDisplay.replace("<x>", String.valueOf(x))
                    .replace("<y>", String.valueOf(y))
                    .replace("<z>", String.valueOf(z));
            String resultCoords = coordDisplay.replace("<x>", String.valueOf(portalx))
                    .replace("<y>", String.valueOf(y))
                    .replace("<z>", String.valueOf(portalz));

            sender.spigot().sendMessage(new MineDown(overworldCalcMessage).toComponent());
            sender.spigot().sendMessage(new MineDown(calcCoords).toComponent());
            sender.spigot().sendMessage(new MineDown(overworldResultMessage).toComponent());
            sender.spigot().sendMessage(new MineDown(resultCoords).toComponent());

            return true;
        } else if (cmd.getName().equalsIgnoreCase("portalcalc")
                && args.length < 4
                && sender.hasPermission("portalcalc.calc")) {

            sender.sendMessage(wrongArgError);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("portalcalc")
                && !(sender.hasPermission("portalcalc.calc"))) {

            sender.spigot().sendMessage(new MineDown(noPermissionError).toComponent());
            return true;
        }
        sender.sendMessage(wrongArgError);
        return true;
    }
}
