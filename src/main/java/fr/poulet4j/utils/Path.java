package fr.poulet4j.utils;

import java.util.LinkedList;
import java.util.List;

import fr.poulet4j.model.Cell;

public class Path {

	public List<Cell> cells;

	public Path() {
		cells = new LinkedList<Cell>();
	}

	public boolean isEmpty() {
		return cells.isEmpty();
	}

	public int length() {
		return cells.size();
	}
}
