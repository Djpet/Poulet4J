package fr.poulet4j.utils;

import fr.poulet4j.model.Cell;

public class CellExtended {

    public Cell cell;
    public CellExtended previousCell;
    public int weight;

    public CellExtended(Cell cell) {
        this.cell = cell;
        weight = -1;
    }

}
