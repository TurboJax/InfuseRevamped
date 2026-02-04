package org.turbojax.data;

import org.bukkit.plugin.Plugin;

public class Messages extends TurboConfig {
    public Messages(Plugin plugin) {
        super(plugin, "messages.yml");
    }
}