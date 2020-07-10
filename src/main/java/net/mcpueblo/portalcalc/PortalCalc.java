package net.mcpueblo.portalcalc;

import net.mcpueblo.portalcalc.cmd.PortalCalcReload;
import org.bukkit.plugin.java.JavaPlugin;
import net.mcpueblo.portalcalc.cmd.PortalCalcCommand;

public final class PortalCalc extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("portalcalc").setExecutor(new PortalCalcCommand(this));
        getCommand("portalcalcreload").setExecutor(new PortalCalcReload(this));
        getLogger().info("Made for MCPueblo");
    }
    @Override
    public void onDisable() {
        getLogger().info("Stopping...");
    }
}
