package fr.poulet4j.order;

import fr.poulet4j.model.Item;

/**
 * Order d'uitlisation d'un item
 */
public class UseItemOrder extends TurnAction {

    /** L'item utilisé */
    public Item item;

    public UseItemOrder() {
        super(Action.USE_ITEM);
    }

    /**
     * Constructeur.
     * @param item : item utilisé
     */
    public UseItemOrder(Item item) {
        super(Action.USE_ITEM);
        this.item = item;
    }
}
