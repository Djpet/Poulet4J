package fr.poulet4j.core.message;

public class SocketMessage {

    /** Le type du message */
    private String type;

    public SocketMessage(String type) {
        this.type = type;
    }

    /**
     * Get the type.
     * @return the type
     */
    public String getType() {
        return type;
    }
}
