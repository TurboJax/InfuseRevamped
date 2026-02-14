package org.turbojax;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.turbojax.data.DataManager;
import org.turbojax.data.MainConfig;
import org.turbojax.data.Messages;
import org.turbojax.effects.InfuseEffect;

public class Infuse extends JavaPlugin {
    private final DataManager dataManager;
    private final MainConfig mainConfig;
    private final Messages messages;
    private final BukkitRunnable passiveLoop;

    public Infuse() {
        this.dataManager = new DataManager(this);
        this.mainConfig = new MainConfig(this);
        this.messages = new Messages(this);

        this.passiveLoop = new BukkitRunnable() {
            @Override
            public void run() {
                // Looping over every online player
                for (Player player : Bukkit.getOnlinePlayers()) {
                    // Activating passive abilities for the player's left effect
                    InfuseEffect leftEffect = dataManager.getLeftEffect(player);
                    if (leftEffect != null) leftEffect.applyPassives(player);
                    
                    // Activating passive abilities for the player's right effect
                    InfuseEffect rightEffect = dataManager.getLeftEffect(player);
                    if (rightEffect != null) rightEffect.applyPassives(player);
                }
            }
        };
    }

    @Override
    public void onEnable() {
        // Loading configs
        dataManager.load();
        mainConfig.load();
        messages.load();

        // Starting the task
        passiveLoop.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        // Saving configs
        dataManager.save();
        mainConfig.save();
        messages.save();

        // Stopping the effectLoop task
        passiveLoop.cancel();
    }
}