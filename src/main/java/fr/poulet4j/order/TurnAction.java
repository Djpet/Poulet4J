package fr.poulet4j.order;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Une action à executer pendant le tour.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = MoveOrder.class, name = "move"), @Type(value = GetItemOrder.class, name = "getItem"),
        @Type(value = UseItemOrder.class, name = "useItem"), @Type(value = Fail.class, name = "fail"),
        @Type(value = Success.class, name = "success")})
public class TurnAction {

    /** le type d'action */
    public Action type;

    public TurnAction() {}

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
