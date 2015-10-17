package fr.poulet4j.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Une cellule du labyrinth.
 */
public class Cell implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Id de la cellule (ça sert à rien) */
    public long id;

    /** Id de la cellule en haut (ça sert à rien, vaut mieux utilisé {@link Cell#topCell}) */
    public long top;

    /** Id de la cellule du bas (ça sert à rien, vaut mieux utilisé {@link Cell#bottomCell}) */
    public long bottom;

    /** Id de la cellule de gauche (ça sert à rien, vaut mieux utilisé {@link Cell#leftCell}) */
    public long left;

    /** Id de la cellule de droite (ça sert à rien, vaut mieux utilisé {@link Cell#rightCell}) */
    public long right;

    /** Occupant de la cellule */
    public IAInfo occupant;

    /** Item présent sur la cellule */
    public Item item;

    /** Cellule en haut si accessible (null = mur) */
    @JsonIgnore
    public Cell topCell;

    /** Cellule en bas si accessible (null = mur) */
    @JsonIgnore
    public Cell bottomCell;

    /** Cellule de gauche si accessible (null = mur) */
    @JsonIgnore
    public Cell leftCell;

    /** Cellule de droite si accessible (null = mur) */
    @JsonIgnore
    public Cell rightCell;

    /** Je suis sur la cellule */
    @JsonIgnore
    public boolean moi;

    /** L'ennemi est sur la cellule */
    @JsonIgnore
    public boolean ennemi;

    /** Le poulet est sur la cellule */
    @JsonIgnore
    public boolean poulet;

    /** position horizontale de la cellule */
    public int x;

    /** position verticale de la cellule */
    public int y;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

}
