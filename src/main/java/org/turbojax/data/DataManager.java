package org.turbojax.data;

import org.bukkit.entity.Player;
import org.turbojax.Infuse;
import org.turbojax.effects.InfuseEffect;

public class DataManager extends TurboConfig {
    public DataManager() {
        super(Infuse.getInstance(), "playerdata.yml");
    }

    public InfuseEffect getLeftEffect(Player player) {
        return InfuseEffect.deserialize(config.getInt(player.getUniqueId() + ".left", -1));
    }

    public void setLeftEffect(Player player, InfuseEffect effect) {
        config.set(player.getUniqueId() + ".left", effect.serialize());
    }

    public InfuseEffect getRightEffect(Player player) {
        return InfuseEffect.deserialize(config.getInt(player.getUniqueId() + ".right", -1));
    }

    public void setRightEffect(Player player, InfuseEffect effect) {
        config.set(player.getUniqueId() + ".right", effect.serialize());
    }

    public boolean hasEffect(Player player, InfuseEffect effect) {
        return hasEffect(player, effect, false);
    }

    public boolean hasEffect(Player player, InfuseEffect effect, boolean differentiateAugmented) {
        InfuseEffect leftEffect = getLeftEffect(player);
        InfuseEffect rightEffect = getRightEffect(player);

        if (!differentiateAugmented) {
            leftEffect = leftEffect.getRegularVersion();
            rightEffect = rightEffect.getRegularVersion();
            effect = effect.getRegularVersion();
        }

        return effect.equals(leftEffect) || effect.equals(rightEffect);
    }
}