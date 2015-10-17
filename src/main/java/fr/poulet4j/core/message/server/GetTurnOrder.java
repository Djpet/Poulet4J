package fr.poulet4j.core.message.server;

import fr.poulet4j.model.GameMap;

/**
 * Demande le résultat du tour de l'IA.
 */
public class GetTurnOrder extends GameServerMessage {

    public static final String TYPE = "getTurnOrder";

    /** Les données de la partie */
    public GameMap data;

    public GetTurnOrder() {
        super(TYPE);
    }

    /**
     * Get the data.
     * @return the data
     */
    public GameMap getData() {
        return data;
    }

    /**
     * Set the data.
     * @param data the data to set
     */
    public void setData(GameMap data) {
        this.data = data;
    }

}
