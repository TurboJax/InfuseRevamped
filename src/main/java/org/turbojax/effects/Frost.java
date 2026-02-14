package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public class Frost extends InfuseEffect {
    public Frost(boolean augmented) {
        super(EffectId.FROST, "frost", augmented);
    }

    @Override
    public void applyPassives(Player player) {}

    @Override
    public void activateSpark(Player player) {}

    @Override
    public InfuseEffect getAugmentedVersion() {
        return new Frost(true);
    }

    @Override
    public InfuseEffect getRegularVersion() {
        return new Frost(false);
    }
}