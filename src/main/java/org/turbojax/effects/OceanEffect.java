package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectIds;

public class OceanEffect extends InfuseEffect {
    public OceanEffect(boolean augmented) {
        super(EffectIds.OCEAN, "ocean", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new OceanEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new OceanEffect(false);
    }
}