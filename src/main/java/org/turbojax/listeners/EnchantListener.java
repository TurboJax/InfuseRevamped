package org.turbojax.listeners;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.ItemStack;
import org.turbojax.data.DataManager;
import org.turbojax.effects.Emerald;
import org.turbojax.utils.WeightedRandom;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Enchantable;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.registry.tag.Tag;

public class EnchantListener {
    private final DataManager dataManager;

    public EnchantListener(DataManager dataManager) {
        this.dataManager = dataManager;
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
}
