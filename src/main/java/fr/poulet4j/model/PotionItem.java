package fr.poulet4j.model;

/**
 * Item de potion d'invisibilité.<br>
 * Utilisé pour masquer sa position aux autres<br>
 * {@link GameConstants#INVISIBILITY_DURATION}
 */
public class PotionItem extends Item {

    private static final long serialVersionUID = 1L;

    public PotionItem() {
        super(ItemType.POTION);
    }

}
