package fr.poulet4j.order;


/**
 * Une action à executer pendant le tour.
 */
public class TurnAction {

    /** le type d'action */
    public Action type;

    public TurnAction(Action type) {
        this.type = type;
    }

    /**
     * Get the type.
     * @return the type
     */
    public Action getType() {
        return type;
    }

    /**
     * Set the type.
     * @param type the type to set
     */
    public void setType(Action type) {
        this.type = type;
    }

}
