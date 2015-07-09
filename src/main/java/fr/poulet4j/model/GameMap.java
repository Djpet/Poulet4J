package fr.poulet4j.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GameMap implements Serializable {

    private static final long serialVersionUID = 1L;

    public long id;

    public Cell[][] cells;

    public int currentTurn;

    public ArrayList<IAInfo> iaList;

    @JsonIgnore
    public IAInfo me;
    @JsonIgnore
    public IAInfo chiken;
    @JsonIgnore
    public IAInfo enemy;

}
