package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectIds;

public class HasteEffect extends InfuseEffect {
    public HasteEffect(boolean augmented) {
        super(EffectIds.HASTE, "haste", augmented);
    }
    
    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new HasteEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new HasteEffect(false);
    }
}