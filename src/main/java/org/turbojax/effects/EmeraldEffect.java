package org.turbojax.effects;

import org.bukkit.entity.Player;

public class EmeraldEffect extends InfuseEffect {
    public EmeraldEffect(boolean augmented) {
        super("emerald", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

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