package fr.poulet4j.save;

import java.io.Serializable;

import fr.poulet4j.model.GameMap;

public class SaveTurn implements Serializable {

    private static final long serialVersionUID = 1L;

    public int duration;

    public GameMap data;

}
