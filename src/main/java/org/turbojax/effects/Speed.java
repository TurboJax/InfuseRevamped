package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Speed extends InfuseEffect {
    public Speed(boolean augmented) {
        super(EffectId.SPEED, "speed", augmented);
    }
    
    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Speed(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Speed(false);
    }
}