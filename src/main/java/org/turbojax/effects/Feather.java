package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Feather extends InfuseEffect {
    public Feather(boolean augmented) {
        super(EffectId.FEATHER, "feather", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Feather(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Feather(false);
    }
}