package fr.poulet4j.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * La map du labyrinth. Elle representant l'ensemble des éléments d'une partie
 */
public class GameMap implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id de la map */
    public long id;

    /** Liste 2d des cases de la map */
    public Cell[][] cells;

    /** Numéro du tour courant */
    public int currentTurn;

    /** Liste des 3 IA */
    public ArrayList<IAInfo> iaList;

    /** Moi */
    @JsonIgnore
    public IAInfo moi;

    /** L'ennemi */
    @JsonIgnore
    public IAInfo ennemi;

    /** Le poulet */
    @JsonIgnore
    public IAInfo poulet;

}
