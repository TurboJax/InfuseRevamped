package org.turbojax.effects;

import org.bukkit.entity.Player;

public class FireEffect extends InfuseEffect {
    public FireEffect(boolean augmented) {
        super("fire", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new FireEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new FireEffect(false);
    }
}