package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class HeartEffect extends InfuseEffect {
    public HeartEffect(boolean augmented) {
        super(EffectId.HEART, "heart", augmented);
    }
    
    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new HeartEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new HeartEffect(false);
    }
}