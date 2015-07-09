package fr.poulet4j.core.message;

/**
 * Message d'erreur.
 */
public class Error extends SocketMessage {

    /** Le code d'erreur */
    public ErrorCode code;

    /** Le message */
    public String message;

    public Error() {
        super("error");
    }

    /**
     * Get the code.
     * @return the code
     */
    public ErrorCode getCode() {
        return code;
    }

    /**
     * Set the code.
     * @param code the code to set
     */
    public void setCode(ErrorCode code) {
        this.code = code;
    }

    /**
     * Get the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
