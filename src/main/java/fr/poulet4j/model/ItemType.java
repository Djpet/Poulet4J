package fr.poulet4j.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Les types d'items
 */
public enum ItemType {

    /**
     * potion d'invisibilité
     */
    POTION("potion"),

    /**
     * parfum aux hormones
     */
    PARFUM("parfum"),

    /**
     * piege
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
}
