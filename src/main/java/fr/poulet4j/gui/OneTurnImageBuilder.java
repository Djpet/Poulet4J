package fr.poulet4j.gui;

import java.awt.image.BufferedImage;

/**
 * Construit des images de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE} transparentes pour superposition.<br>
 * Ne traite que le tour courant.
 */
public interface OneTurnImageBuilder<T> extends TurnImageBuilder<T> {

    /**
     * Construit l'image.
     * @param t : objet du tour courant
     * @return image de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE}
     */
    BufferedImage build(final T t);

}
