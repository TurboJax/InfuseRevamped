package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Fire extends InfuseEffect {
    public Fire(boolean augmented) {
        super(EffectId.FIRE, "fire", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Fire(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Fire(false);
    }
}