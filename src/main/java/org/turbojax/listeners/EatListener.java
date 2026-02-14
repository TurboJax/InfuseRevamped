package org.turbojax.listeners;

import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.turbojax.data.DataManager;
import org.turbojax.effects.Emerald;

public class EatListener {
    private final DataManager dataManager;

    public EatListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void onEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();

        if (dataManager.hasEffect(event.getPlayer(), new Emerald(false))) {
            if (Math.random() < 0.5) {
                item.add(1);
                event.setItem(item);
            }
        }
    }
}
