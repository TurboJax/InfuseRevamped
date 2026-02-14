package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Invis extends InfuseEffect {
    public Invis(boolean augmented) {
        super(EffectId.INVIS, "invis", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Invis(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Invis(false);
    }
}