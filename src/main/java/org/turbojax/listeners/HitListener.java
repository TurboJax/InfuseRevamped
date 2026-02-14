package org.turbojax.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.turbojax.data.DataManager;
import org.turbojax.data.MainConfig;
import org.turbojax.effects.Emerald;

public class HitListener {
    private final Plugin plugin;
    private final DataManager dataManager;
    private final MainConfig mainConfig;

    private final Map<UUID,Map<UUID,Integer>> emeraldHitTracker = new HashMap<>();

    public HitListener(Plugin plugin, DataManager dataManager, MainConfig mainConfig) {
        this.plugin = plugin;
        this.dataManager = dataManager;
        this.mainConfig = mainConfig;
    }

    public void onHit(EntityDamageByEntityEvent event) {
        Entity attackingEntity = event.getDamageSource().getCausingEntity();
        Entity damagedEntity = event.getEntity();

        if (attackingEntity instanceof Player attackingPlayer) {
            if (damagedEntity instanceof Player damagedPlayer) {
                // Stealing xp with emerald effect
                if (dataManager.hasEffect(attackingPlayer, new Emerald(false))) {
                    int stolenExp = Math.min(damagedPlayer.getTotalExperience(), mainConfig.emeraldExpToSteal());

                    // Removing xp from the target
                    damagedPlayer.giveExp(-stolenExp);

                    // Giving xp to the attacker
                    attackingPlayer.giveExp(stolenExp);
                }

                // Defining a BukkitRunnable to reset the hit tracker after a certain period of time.
                BukkitRunnable hitTrackerReset = new BukkitRunnable() {
                    @Override
                    public void run() {
                        emeraldHitTracker.getOrDefault(damagedPlayer.getUniqueId(), new HashMap<>()).remove(attackingPlayer.getUniqueId());
                    }
                };
                
                // Tracking hits against emerald players
                if (dataManager.hasEffect(damagedPlayer, new Emerald(false))) {
                    Map<UUID,Integer> hitsTracked = emeraldHitTracker.computeIfAbsent(damagedPlayer.getUniqueId(), k -> new HashMap<>());
                    int hits = hitsTracked.getOrDefault(attackingPlayer.getUniqueId(), 0);

                    // Incrementing the hit tracker
                    hitsTracked.put(attackingPlayer.getUniqueId(), hits + 1);

                    // Stopping the hit tracker reset timer if it's already been started
                    if (!hitTrackerReset.isCancelled()) {
                        hitTrackerReset.cancel();
                    }

                    // Starting the hit tracker reset timer.
                    hitTrackerReset.runTaskLater(plugin, 200); // requires 10 hits in 10 seconds
                }
            }
        }
    }
}
