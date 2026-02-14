package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.turbojax.EffectId;

public class EmeraldEffect extends InfuseEffect {
    public EmeraldEffect(boolean augmented) {
        super(EffectId.EMERALD, "emerald", augmented);
    }

    @Override
    public void applyPassives(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 20, 0));
    }

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new EmeraldEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new EmeraldEffect(false);
    }
}