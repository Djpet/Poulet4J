package fr.poulet4j.core.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Code d'erreur.
 */
public enum ErrorCode {

    /** Déja authentifié */
    ALREADY_AUTH(1),
    /** Message inconnu */
    UNKNOWN_MESSAGE(2);

    /** Code de l'erreur */
    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    /**
     * Get the code.
     * @return the code
     */
    @JsonValue
    public int getCode() {
        return code;
    }

    /**
     * Set the code.
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    @JsonCreator
    public static ErrorCode forValue(int value) {
        for (ErrorCode ec : values()) {
            if (ec.getCode() == value) {
                return ec;
            }
        }
        return null;
    }
}
