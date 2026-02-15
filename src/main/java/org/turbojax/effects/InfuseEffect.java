package org.turbojax.effects;

import java.util.List;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.turbojax.EffectId;
import org.turbojax.Infuse;

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

    @Override
    public String toString() {
        return (augmented ? "aug_" : "") + name;
    }

    public static InfuseEffect fromString(String serializedEffect) {
        boolean augmented = serializedEffect.startsWith("aug_");
        if (augmented) {
            serializedEffect = serializedEffect.substring(4);
        }

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

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        // TODO: Set item name
        // TODO: Set item lore
        // TODO: Set potion color
        if (augmented) {
            CustomModelDataComponent customModelData = meta.getCustomModelDataComponent();
            customModelData.setFloats(List.of((float) serialize()));
            meta.setCustomModelDataComponent(customModelData);
        }

        return item;
    }

    public static InfuseEffect fromItem(ItemStack item) {
        if (item.getType() != Material.POTION) return null;
        List<Float> floats = item.getItemMeta().getCustomModelDataComponent().getFloats();

        if (floats.size() != 1) return null;

        return deserialize(floats.get(0).intValue());
    }

    /** Serializes an InfuseEffect into an int */
    public int serialize() {
        return (augmented ? 100 : 0) + id.ordinal();
    }

    /**
     * Deserializes an InfuseEffect from an int
     * 
     * @param serialized The serialized int
     */
    public static InfuseEffect deserialize(int serialized) {
        if (EffectId.values().length <= serialized % 100) {
            Infuse.getInstance().getLogger().log(Level.SEVERE, "Effect id " + serialized + " out of bounds");
            return null;
        }

        boolean augmented = serialized > 99;
        EffectId id = EffectId.values()[serialized % 100];

        return switch (id) {
            case EMERALD -> new Emerald(augmented);
            case ENDER -> new Ender(augmented);
            case FEATHER -> new Feather(augmented);
            case FIRE -> new Fire(augmented);
            case FROST -> new Frost(augmented);
            case HASTE -> new Haste(augmented);
            case HEART -> new Heart(augmented);
            case INVIS -> new Invis(augmented);
            case OCEAN -> new Ocean(augmented);
            case REGEN -> new Regen(augmented);
            case SPEED -> new Speed(augmented);
            case STRENGTH -> new Strength(augmented);
            case THUNDER -> new Thunder(augmented);
        };
    }
}