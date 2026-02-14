package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Haste extends InfuseEffect {
    public Haste(boolean augmented) {
        super(EffectId.HASTE, "haste", augmented);
    }
    
    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Haste(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Haste(false);
    }
}