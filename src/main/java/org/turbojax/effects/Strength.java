package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Strength extends InfuseEffect {
    public Strength(boolean augmented) {
        super(EffectId.STRENGTH, "strength", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Strength(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Strength(false);
    }
}