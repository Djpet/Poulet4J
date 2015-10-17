package fr.poulet4j.order;

import fr.poulet4j.model.Cell;
import fr.poulet4j.model.Item;

/**
 * Fabrique des différents ordres.
 */
public final class Orders {

    /**
     * Se déplacer à la cellule cible.
     * @param targetCell : la cellule cible
     * @return l'ordre de déplacement
     */
    public static MoveOrder move(Cell targetCell) {
        return new MoveOrder(targetCell);
    }

    /**
     * Ramassage l'item.
     * @return l'ordre de ramassage
     */
    public static GetItemOrder getItem() {
        return new GetItemOrder();
    }

    /**
     * Utilise l'item.
     * @param item : l'item
     * @return l'ordre de d'utilisation
     */
    public static UseItemOrder useItem(Item item) {
        return new UseItemOrder(item);
    }
}
