package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class EnderEffect extends InfuseEffect {
    public EnderEffect(boolean augmented) {
        super(EffectId.ENDER, "ender", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new EnderEffect(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new EnderEffect(false);
    }
}