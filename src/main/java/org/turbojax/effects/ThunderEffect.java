package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class ThunderEffect extends InfuseEffect {
    public ThunderEffect(boolean augmented) {
        super(EffectId.THUNDER, "thunder", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new ThunderEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new ThunderEffect(false);
    }
}