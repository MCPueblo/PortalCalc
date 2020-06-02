package net.mcpueblo.portalcalc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class PortalCalc extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("portalcalc").setExecutor(new PortalCalcCommand(this));
    }

    @Override
    public void onDisable() {
    }
}
