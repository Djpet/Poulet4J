package fr.poulet4j.order;

import fr.poulet4j.model.Cell;

/**
 * Ordre de déplacement.
 */
@SuppressWarnings("deprecation")
public class MoveOrder extends TurnAction {

    /** id de la cellule cible */
    public long target;

    public MoveOrder() {
        super(Action.MOVE);
    }

    /**
     * Constructeur.
     * @param targetCell : cellule cible
     */
    public MoveOrder(Cell targetCell) {
        super(Action.MOVE);
        target = targetCell.id;
    }

    /**
     * Get the target.
     * @return the target
     */
    public long getTarget() {
        return target;
    }

    /**
     * Set the target.
     * @param target the target to set
     */
    public void setTarget(long target) {
        this.target = target;
    }
}
