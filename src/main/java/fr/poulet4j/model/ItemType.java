package fr.poulet4j.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Les types d'items
 */
public enum ItemType {

    /**
     * Une potion d'invisibilité
     */
    POTION("potion"),

    /**
     * Un parfum aux hormones
     */
    PARFUM("parfum"),

    /**
     * Un piège.
     */
    TRAP("trap");

    private String itemType;

    private ItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Get the itemType.
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Set the itemType.
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @JsonCreator
    public static ItemType forValue(String value) {
        for (ItemType it : values()) {
            if (it.getItemType().equals(value)) {
                return it;
            }
        }
        return null;
    }

    @JsonValue
    public String value() {
        return itemType;
    }
}
