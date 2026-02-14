package org.turbojax.effects;

import org.bukkit.entity.Player;

public class FeatherEffect extends InfuseEffect {
    public FeatherEffect(boolean augmented) {
        super(3, "feather", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new FeatherEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new FeatherEffect(false);
    }
}