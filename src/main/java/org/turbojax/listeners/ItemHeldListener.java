package org.turbojax.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.turbojax.data.DataManager;
import org.turbojax.effects.EmeraldEffect;

public class ItemHeldListener {
    private final DataManager dataManager;

    public ItemHeldListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());

        if (dataManager.hasEffect(player, new EmeraldEffect(false))) {
            try {
                item.addEnchantment(Enchantment.LOOTING, 5);
            } catch (IllegalArgumentException ignored) {}
        }
    }
}