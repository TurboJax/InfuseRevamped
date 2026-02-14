package org.turbojax.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.turbojax.Infuse;
import org.turbojax.data.DataManager;
import org.turbojax.data.MainConfig;
import org.turbojax.effects.Emerald;
import org.turbojax.utils.WeightedRandom;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Enchantable;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.registry.tag.Tag;

public class EmeraldListeners implements Listener {
    private final Infuse plugin;
    private final DataManager dataManager;
    private final MainConfig mainConfig;

    private final Map<UUID,Map<UUID,Integer>> emeraldHitTracker = new HashMap<>();

    public EmeraldListeners() {
        this.plugin = Infuse.getInstance();
        this.dataManager = plugin.getDataManager();
        this.mainConfig = plugin.getMainConfig();
    }

    @EventHandler
    public void emeraldPreserveConsumables(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();

        if (!dataManager.hasEffect(event.getPlayer(), new Emerald(false))) return;

        if (Math.random() < 0.5) {
            item.add(1);
            event.setItem(item);
        }
    }

    @EventHandler
    public void emeraldEnchantBonus(PrepareItemEnchantEvent event) {
        if (dataManager.hasEffect(event.getEnchanter(), new Emerald(false))) {
            // Getting the world seed of the player
            long worldSeed = event.getEnchanter().getWorld().getSeed();

            Random rand = new Random();
            EnchantmentOffer[] currentOffers = event.getOffers();
            Registry<Enchantment> enchantRegistry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT);
            Tag<Enchantment> inEnchantingTable = enchantRegistry.getTag(EnchantmentTagKeys.IN_ENCHANTING_TABLE);
            ItemStack item = event.getItem();

            // Getting the enchantability of the item
            Enchantable enchantable = item.getType().getDefaultData(DataComponentTypes.ENCHANTABLE);
            if (enchantable == null) return;

            for (int i = 0; i < 3; i++) {
                // Ofsetting the rng seed
                rand.setSeed(worldSeed + i);

                // Calculating the initial cost of the enchantment with 15 bookshelves
                // The algorithm changes based on i
                int cost = 0;
                if (i == 0) cost = Math.max((rand.nextInt(8) + 8 + rand.nextInt(16)) / 3, 1);
                if (i == 1) cost = (rand.nextInt(8) + 8 + rand.nextInt(16)) * 2 / 3 + 1;
                if (i == 2) cost = Math.max(rand.nextInt(8) + 8 + rand.nextInt(16), 30);

                // Calculating the final cost of the enchantment
                cost += 1 + rand.nextInt(enchantable.value() / 4 + 1) + rand.nextInt(enchantable.value() / 4 + 1);
                float f = (rand.nextFloat() + rand.nextFloat() - 1) * 0.15F;
                cost = Math.clamp(Math.round(cost + cost * f), 1, Integer.MAX_VALUE);
                final int finalCost = cost;
                
                // Overriding the existing enchantment offers
                if (!inEnchantingTable.isEmpty()) {
                    List<EnchantmentOffer> applicableEnchants = inEnchantingTable.resolve(enchantRegistry).stream()
                        .filter(e -> e.getPrimaryItems().contains(TypedKey.create(RegistryKey.ITEM, item.getType().key())) || item.getType() == Material.BOOK)
                        .map(e -> {
                        for (int level = e.getMaxLevel(); level >= e.getStartLevel(); level--) {
                            if (finalCost >= e.getMinModifiedCost(level) && finalCost <= e.getMaxModifiedCost(level)) {
                                return new EnchantmentOffer(e, level, finalCost);
                            }
                        }
                        return null;
                    }).filter(Objects::nonNull).toList();
            
                    // Overriding the current offer with a random one.
                    currentOffers[i] = WeightedRandom.getRandomItem(rand, applicableEnchants, e -> e.getEnchantment().getWeight());
                }
            }
        }
    }

    @EventHandler
    public void onExpGain(PlayerExpChangeEvent event) {
        if (dataManager.hasEffect(event.getPlayer(), new Emerald(false))) {
            event.setAmount(event.getAmount() * 2);
        }
    }

    @EventHandler
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

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());

        if (dataManager.hasEffect(player, new Emerald(false))) {
            try {
                item.addEnchantment(Enchantment.LOOTING, 5);
            } catch (IllegalArgumentException ignored) {}
        }
    }
}
