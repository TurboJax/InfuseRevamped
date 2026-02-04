package org.turbojax.data;

import org.bukkit.plugin.Plugin;

public class DataManager extends TurboConfig {
    public DataManager(Plugin plugin) {
        super(plugin, "data/playerdata.yml");
    }
}