package fr.poulet4j.model;

/**
 * Item de piège.<br>
 * Utilisé pour placer un piège sur la map.<br>
 * <b>/!\ Vous pouvez tomber dans votre piège /!\</b><br>
 * {@link GameConstants#TRAPED_DURATION}
 */
public class TrapItem extends Item {

    private static final long serialVersionUID = 1L;

    public TrapItem() {
        super(ItemType.TRAP);
    }

}
