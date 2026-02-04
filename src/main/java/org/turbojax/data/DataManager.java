package org.turbojax.data;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.turbojax.effects.InfuseEffect;

public class DataManager extends TurboConfig {
    public DataManager(Plugin plugin) {
        super(plugin, "data/playerdata.yml");
    }

    public InfuseEffect getLeftEffect(Player player) {
        return InfuseEffect.deserialize(config.getString(player.getUniqueId() + ".left"));
    }

    public void setLeftEffect(Player player, InfuseEffect effect) {
        config.set(player.getUniqueId() + ".left", effect.serialize());
    }

    public InfuseEffect getRightEffect(Player player) {
        return InfuseEffect.deserialize(config.getString(player.getUniqueId() + ".right"));
    }

    public void setRightEffect(Player player, InfuseEffect effect) {
        config.set(player.getUniqueId() + ".right", effect.serialize());
    }

    public boolean hasEffect(Player player, InfuseEffect effect) {
        InfuseEffect leftEffect = getLeftEffect(player);
        InfuseEffect rightEffect = getRightEffect(player);
        Class<?> effectClass = effect.getClass();

        return effectClass.isInstance(leftEffect) || effectClass.isInstance(rightEffect);
    }
}