package org.turbojax.data;

import org.bukkit.plugin.Plugin;

public class MainConfig extends TurboConfig {
    public MainConfig(Plugin plugin) {
        super(plugin, "config.yml");
    }
}