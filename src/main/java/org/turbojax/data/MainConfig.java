package org.turbojax.data;

import org.bukkit.plugin.Plugin;

public class MainConfig extends TurboConfig {
    public MainConfig(Plugin plugin) {
        super(plugin, "config.yml");
    }

    public int emeraldExpToSteal() {
        return config.getInt("emerald.exp_to_steal", 5);
    }

    public int emeraldLockDurationSeconds() {
        return config.getInt("emerald.lock_duration_seconds", 15);
    }
}