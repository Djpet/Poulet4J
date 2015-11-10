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

    private int currentTurn;

    private List<Controler> controlers;

    public GuiControler(final Gui gui, GameReload<T> gameReload, InfosIaBuilder<T> infosIaBuilder) {
        this.gui = gui;
        this.gameReload = gameReload;
        this.infosIaBuilder = infosIaBuilder;
        turnBuilders = new LinkedList<TurnImageBuilder<T>>();
        controlers = new LinkedList<Controler>();
    }

    public void changeTurn(final int value) {
        if (value >= turns.size()) {
            return;
        }
        currentTurn = value;
        String[] infos = infosIaBuilder.getInfos(turns.get(value));

        gui.iaInfo1.setText(infos[0]);
        gui.iaInfo2.setText(infos[1]);
        gui.pouletInfo.setText(infos[2]);
        refresh();
    }

    private void refresh() {
        List<BufferedImage> images = new LinkedList<BufferedImage>();

        for (TurnImageBuilder<T> builder : turnBuilders) {
            if (findControler(builder).isShow()) {
                if (builder instanceof OneTurnImageBuilder) {
                    images.add(((OneTurnImageBuilder<T>) builder).build(turns.get(currentTurn)));
                } else if (builder instanceof AllTurnsImageBuilder) {
                    images.add(((AllTurnsImageBuilder<T>) builder).build(turns, currentTurn));
                }
            }
        }

        gui.mapUI.setImages(images);
        gui.repaint();
    }

    private Controler findControler(TurnImageBuilder<T> builder) {
        for (Controler controler : controlers) {
            if (builder == controler.getTurnImageBuilder()) {
                return controler;
            }
        }
        return null;
    }

    void loadFile(final File file) {
        turns = gameReload.loadFile(file);

        gui.slider.setValue(0);
        gui.slider.setMaximum(turns.size());
        changeTurn(0);
        gui.slider.grabFocus();
    }

    public void add(TurnImageBuilder<T> turnBuilder) {
        Controler controler = new Controler(turnBuilder);
        controler.setGuiControler(this);
        gui.controlers.add(controler);
        turnBuilders.add(turnBuilder);
        controlers.add(controler);
    }

    public void refreshAfterChange() {
        refresh();
    }

}
