package fr.poulet4j.gui;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Construit des images de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE} transparentes pour superposition.<br>
 * Ne traite que le tour courant.
 */
public interface AllTurnsImageBuilder<T> extends TurnImageBuilder<T> {
    /**
     * Construit l'image.
     * @param saveTurns : : liste de tous les tours
     * @param currentTurn : index du tour courant
     * @return image de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE}
     */
    BufferedImage build(List<T> saveTurns, int currentTurn);

}
