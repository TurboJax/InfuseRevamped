package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class FireEffect extends InfuseEffect {
    public FireEffect(boolean augmented) {
        super(EffectId.FIRE, "fire", augmented);
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