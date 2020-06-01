package net.mcpueblo.portalcalc;

import org.bukkit.plugin.java.JavaPlugin;

public class PortalCalc extends JavaPlugin {

    @Override
    public void onEnable() {
        /* Plugin startup logic */
        getCommand("portalcalc").setExecutor(new PortalCalcCommand());
    }

    @Override
    public void onDisable() {
        /* Plugin shutdown logic */
    }
}
