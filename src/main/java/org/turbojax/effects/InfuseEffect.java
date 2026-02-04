package org.turbojax.effects;

public abstract class InfuseEffect {
    protected final boolean augmented;

    public InfuseEffect(boolean augmented) {
        this.augmented = augmented;
    }

    public boolean isAugmented() {
        return augmented;
    }

    public abstract String serialize();

    public static InfuseEffect deserialize(String serializedEffect) {
        // TODO: Implement
        return null;
    }
}
