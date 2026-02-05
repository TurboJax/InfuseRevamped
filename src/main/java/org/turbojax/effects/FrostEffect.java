package org.turbojax.effects;

import org.bukkit.entity.Player;

public class FrostEffect extends InfuseEffect {
    public FrostEffect(boolean augmented) {
        super("frost", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new FrostEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new FrostEffect(false);
    }
}