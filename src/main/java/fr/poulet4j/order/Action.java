package fr.poulet4j.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Listes des types d'action
 */
public enum Action {

    MOVE("move"), GET_ITEM("getItem"), USE_ITEM("useItem"), FAIL("fail"), SUCCESS("success");

    private String action;

    private Action(String action) {
        this.action = action;
    }

    /**
     * Get the action.
     * @return the action
     */
    @JsonValue
    public String getAction() {
        return action;
    }

    /**
     * Set the action.
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    @JsonCreator
    public static Action forValue(String value) {
        for (Action ac : values()) {
            if (ac.getAction().equals(value)) {
                return ac;
            }
        }
        return null;
    }
}
