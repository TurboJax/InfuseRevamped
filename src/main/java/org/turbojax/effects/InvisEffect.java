package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectIds;

public class InvisEffect extends InfuseEffect {
    public InvisEffect(boolean augmented) {
        super(EffectIds.INVIS, "invis", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new InvisEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new InvisEffect(false);
    }
}