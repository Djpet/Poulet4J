package fr.poulet4j.save;

import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.model.GameMap;

/**
 * Interface pour sauvegarder la game.
 */
public interface ISaveGame {

    /** Initialise la classe après l'identification */
    void init();

    /**
     * Appeler entre le l'envoi du tour N et la reception du tour N+1.
     * @param data : la map au tour N
     * @param turnResult : ordres envoyés au tour N
     * @param duration : la durée du tour
     */
    void write(GameMap data, TurnResult turnResult, int duration, String json);
}
