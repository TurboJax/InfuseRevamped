package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectIds;

public class RegenEffect extends InfuseEffect {
    public RegenEffect(boolean augmented) {
        super(EffectIds.REGEN, "regen", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new RegenEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new RegenEffect(false);
    }
}