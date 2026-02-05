package org.turbojax.effects;

public abstract class InfuseEffect {
    protected final String name;
    protected final boolean augmented;

    public InfuseEffect(String name, boolean augmented) {
        this.name = name;
        this.augmented = augmented;
    }

    public abstract void applyPassives(Player player);

    public abstract void activateSpark(Player player);

    public boolean getAugmented() {
        return augmented;
    }

    public String getName() {
        return name;
    }

    public String serialize() {
        return (augmented ? "aug_" : "") + name;
    }

    public static InfuseEffect deserialize(String serializedEffect) {
        boolean augmented = serializedEffect.startsWith("aug_");
        serializedEffect = serializedEffect.replace("aug_", "");

        return switch (serializedEffect) {
            case "emerald" -> new EmeraldEffect(augmented);
            case "ender" -> new EnderEffect(augmented);
            case "feather" -> new FeatherEffect(augmented);
            case "fire" -> new FireEffect(augmented);
            case "frost" -> new FrostEffect(augmented);
            case "haste" -> new HasteEffect(augmented);
            case "heart" -> new HeartEffect(augmented);
            case "invis" -> new InvisEffect(augmented);
            case "ocean" -> new OceanEffect(augmented);
            case "regen" -> new RegenEffect(augmented);
            case "speed" -> new SpeedEffect(augmented);
            case "strength" -> new StrengthEffect(augmented);
            case "thunder" -> new ThunderEffect(augmented);
            default -> null;
        };
    }
}