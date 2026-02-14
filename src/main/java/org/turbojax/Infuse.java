package org.turbojax;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.turbojax.data.DataManager;
import org.turbojax.data.MainConfig;
import org.turbojax.data.Messages;
import org.turbojax.effects.InfuseEffect;

public class Infuse extends JavaPlugin {
    private static Infuse instance;

    private final DataManager dataManager;
    private final MainConfig mainConfig;
    private final Messages messages;
    private final BukkitRunnable passiveLoop;

    @NotNull
    public static Infuse getInstance() {
        if (instance == null) {
            throw new IllegalStateException("The infuse plugin has not been instantiated by the paper plugin yet!  Cannot use Infuse#getInstance.");
        }

        return instance;
    }

    public Infuse() {
        if (instance != null) {
            throw new IllegalStateException("Two instances of the infuse plugin cannot exist at the same time!");
        }

        instance = this;

        this.dataManager = new DataManager();
        this.mainConfig = new MainConfig();
        this.messages = new Messages();

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

    public DataManager getDataManager() {
        return dataManager;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }
}