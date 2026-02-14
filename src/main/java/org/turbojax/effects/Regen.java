package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Regen extends InfuseEffect {
    public Regen(boolean augmented) {
        super(EffectId.REGEN, "regen", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Regen(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Regen(false);
    }
}