package fr.poulet4j.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GuiControler<T> {

    private List<T> turns;

    private Gui gui;

    private List<TurnImageBuilder<T>> turnBuilders;

    private InfosIaBuilder<T> infosIaBuilder;

    private GameReload<T> gameReload;

    public GuiControler(final Gui gui, GameReload<T> gameReload, InfosIaBuilder<T> infosIaBuilder) {
        this.gui = gui;
        this.gameReload = gameReload;
        this.infosIaBuilder = infosIaBuilder;
        turnBuilders = new LinkedList<TurnImageBuilder<T>>();
    }

    public void changeTurn(final int value) {
        if (value >= turns.size()) {
            return;
        }
        List<BufferedImage> images = new LinkedList<BufferedImage>();

        for (TurnImageBuilder<T> builder : turnBuilders) {
            if (builder instanceof OneTurnImageBuilder) {
                images.add(((OneTurnImageBuilder<T>) builder).build(turns.get(value)));
            } else if (builder instanceof AllTurnsImageBuilder) {
                images.add(((AllTurnsImageBuilder<T>) builder).build(turns, value));
            }
        }

        gui.mapUI.setImages(images);
        String[] infos = infosIaBuilder.getInfos(turns.get(value));

        gui.iaInfo1.setText(infos[0]);
        gui.iaInfo2.setText(infos[1]);
        gui.pouletInfo.setText(infos[2]);
        gui.repaint();
    }

    void loadFile(final File file) {
        turns = gameReload.loadFile(file);

        gui.slider.setValue(0);
        gui.slider.setMaximum(turns.size());
        changeTurn(0);
        gui.slider.grabFocus();
    }

    public void add(TurnImageBuilder<T> turnBuilder) {
        turnBuilders.add(turnBuilder);
    }

}
