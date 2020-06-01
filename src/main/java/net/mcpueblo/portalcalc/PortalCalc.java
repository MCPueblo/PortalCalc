package net.mcpueblo.portalcalc;

import org.bukkit.plugin.java.JavaPlugin;

public final class PortalCalc extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("portalcalc").setExecutor(new PortalCalcCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
