package org.turbojax.effects;

public abstract class InfuseEffect {
    protected final String name;
    protected final boolean augmented;

    public InfuseEffect(String name, boolean augmented) {
        this.name = name;
        this.augmented = augmented;
    }

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

        switch (serializedEffect) {
            case "frost":
                return new FrostEffect(augmented);
        }
        return null;
    }
}
