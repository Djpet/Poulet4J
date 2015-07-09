package fr.poulet4j.model;

import java.io.Serializable;

public class Cell implements Serializable {

    private static final long serialVersionUID = 1L;

    public long id;

    public long top;

    public long bottom;

    public long left;

    public long right;

    public IAInfo occupant;

    public Item item;

    public boolean hasTrap;

    public Cell topCell;

    public Cell bottomCell;

    public Cell leftCell;

    public Cell rightCell;

    public boolean moi;

    public boolean ennemi;

    public boolean poulet;

    /** numéro de colonne de la possition de la cellule */
    public int c;

    /** numéro de line de la possition de la cellule */
    public int r;

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
