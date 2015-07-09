package fr.poulet4j.order;

import fr.poulet4j.model.Cell;

public class MoveOrder extends TurnAction {

    /** id de la cellule ciblée */
    public long target;

    /**
     * Constructor.
     * @param targetCell : cellule ciblée
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

    /**
     * Créer un ordre de déplacement.
     * @param targetCell : cellule voisine visée.
     * @return
     */
    public static MoveOrder create(Cell targetCell) {
        return new MoveOrder(targetCell);
    }

}
