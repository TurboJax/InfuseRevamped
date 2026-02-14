package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Ocean extends InfuseEffect {
    public Ocean(boolean augmented) {
        super(EffectId.OCEAN, "ocean", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Ocean(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Ocean(false);
    }
}