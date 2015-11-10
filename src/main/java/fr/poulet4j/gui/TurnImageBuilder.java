package fr.poulet4j.gui;

import javax.swing.JPanel;

/**
 * Construit des images de {@link MapUI#MAP_SIZE} par {@link MapUI#MAP_SIZE} transparentes pour superposition.
 */
public interface TurnImageBuilder<T> {

    JPanel getControlPanel(Controler controler);
}
