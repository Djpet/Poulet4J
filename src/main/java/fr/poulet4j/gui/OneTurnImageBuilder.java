package fr.poulet4j.gui;

import java.awt.image.BufferedImage;

import fr.poulet4j.save.SaveTurn;

/**
 * Construit des images de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE} transparentes pour superposition.<br>
 * Ne traite que le tour courant.
 */
public interface OneTurnImageBuilder extends TurnImageBuilder {

    /**
     * Construit l'image.
     * @param saveTurn : tour courant
     * @return image de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE}
     */
    BufferedImage build(final SaveTurn saveTurn);

}
