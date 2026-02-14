package org.turbojax.effects;

import org.bukkit.entity.Player;

public class SpeedEffect extends InfuseEffect {
    public SpeedEffect(boolean augmented) {
        super(11, "speed", augmented);
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