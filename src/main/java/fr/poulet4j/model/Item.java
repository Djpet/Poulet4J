package fr.poulet4j.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Classe de base des items
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ParfumItem.class, name = "parfum"), @Type(value = PotionItem.class, name = "potion"),
        @Type(value = TrapItem.class, name = "trap")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Le type d'item */
    public ItemType type;

    /**
     * Constructeur.
     * @param type : type de l'item
     */
    public Item(ItemType type) {
        this.type = type;
    }

}
