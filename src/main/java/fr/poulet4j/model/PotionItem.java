package fr.poulet4j.model;

/**
 * Item de potion d'invisibilit�. Utilis�e pour masquer sa position aux autres.
 */
public class PotionItem extends Item {

    private static final long serialVersionUID = 1L;

    public PotionItem() {
        super(ItemType.POTION);
    }

}
