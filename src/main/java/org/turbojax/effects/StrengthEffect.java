package org.turbojax.effects;

import org.bukkit.entity.Player;

public class StrengthEffect extends InfuseEffect {
    public StrengthEffect(boolean augmented) {
        super("strength", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new StrengthEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new StrengthEffect(false);
    }
}