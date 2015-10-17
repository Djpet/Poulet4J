package fr.poulet4j.save;

import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.model.GameMap;

/** Ne sauvegarde pas la game */
public class NoSaveGame implements ISaveGame {

    public void init() {}

    public void write(GameMap data, TurnResult turnResult, int duration, String json) {}

}
