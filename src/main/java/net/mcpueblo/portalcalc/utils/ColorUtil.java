package net.mcpueblo.portalcalc.utils;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static ChatColor getColor(String colorCode) {
        if (colorCode == null) {
            return ChatColor.WHITE;
        }
        if (colorCode.isEmpty()) {
            return ChatColor.WHITE;
        }
        return ChatColor.getByChar(colorCode);
    }
}