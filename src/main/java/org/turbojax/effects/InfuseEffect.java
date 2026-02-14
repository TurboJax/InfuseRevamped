package org.turbojax.effects;

import org.bukkit.entity.Player;
import org.turbojax.EffectId;

public abstract class InfuseEffect {
    protected final EffectId id;
    protected final String name;
    protected final boolean augmented;

    public InfuseEffect(EffectId id, String name, boolean augmented) {
        this.id = id;
        this.name = name;
        this.augmented = augmented;
    }

    public abstract void applyPassives(Player player);

    public abstract void activateSpark(Player player);

    public boolean isAugmented() {
        return augmented;
    }

    public EffectId getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public abstract InfuseEffect getAugmentedVersion();

    public abstract InfuseEffect getRegularVersion();

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof InfuseEffect effect)) return false;

        return effect.augmented == this.augmented && effect.id == this.id;
    }

    public String serialize() {
        return (augmented ? "aug_" : "") + name;
    }

    public static InfuseEffect deserialize(String serializedEffect) {
        boolean augmented = serializedEffect.startsWith("aug_");
        serializedEffect = serializedEffect.replace("aug_", "");

        return switch (serializedEffect) {
            case "emerald" -> new Emerald(augmented);
            case "ender" -> new Ender(augmented);
            case "feather" -> new Feather(augmented);
            case "fire" -> new Fire(augmented);
            case "frost" -> new Frost(augmented);
            case "haste" -> new Haste(augmented);
            case "heart" -> new Heart(augmented);
            case "invis" -> new Invis(augmented);
            case "ocean" -> new Ocean(augmented);
            case "regen" -> new Regen(augmented);
            case "speed" -> new Speed(augmented);
            case "strength" -> new Strength(augmented);
            case "thunder" -> new Thunder(augmented);
            default -> null;
        };
    }
}