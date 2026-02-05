package org.turbojax.listeners;

import org.turbojax.data.DataManager;
import org.turbojax.effects.EmeraldEffect;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExperienceGainListener {
    private final DataManager dataManager;

    public ExperienceGainListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void onExpGain(PlayerExpChangeEvent event) {
        if (dataManager.hasEffect(event.getPlayer(), new EmeraldEffect(false))) {
            event.setAmount(event.getAmount() * 2);
        }
    }
}