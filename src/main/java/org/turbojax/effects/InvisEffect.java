package org.turbojax.effects;

import org.bukkit.entity.Player;

public class InvisEffect extends InfuseEffect {
    public InvisEffect(boolean augmented) {
        super(8, "invis", augmented);
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