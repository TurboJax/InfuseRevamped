package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectIds;

public class SpeedEffect extends InfuseEffect {
    public SpeedEffect(boolean augmented) {
        super(EffectIds.SPEED, "speed", augmented);
    }
    
    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new SpeedEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new SpeedEffect(false);
    }
}