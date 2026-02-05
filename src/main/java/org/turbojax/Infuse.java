package org.turbojax;

import org.bukkit.plugin.java.JavaPlugin;
import org.turbojax.data.DataManager;
import org.turbojax.data.MainConfig;
import org.turbojax.data.Messages;

public class Infuse extends JavaPlugin {
    private final DataManager dataManager;
    private final MainConfig mainConfig;
    private final Messages messages;

    public Infuse() {
        this.dataManager = new DataManager(this);
        this.mainConfig = new MainConfig(this);
        this.messages = new Messages(this);
    }

    @Override
    public void onEnable() {
        // Loading configs
        dataManager.load();
        mainConfig.load();
        messages.load();
    }

    @Override
    public void onDisable() {
        // Saving configs
        dataManager.save();
        mainConfig.save();
        messages.save();
    }
}