package fr.poulet4j.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameMap;

/** Utilitaires */
public class MapUtils {

    /**
     * Calcule le chemin le plus court entre deux cellules
     * @param source : cellule source
     * @param destination : cellule de destination
     * @param gameMap : la map
     * @return le chemin sous forme de liste (la première cellule est la source)
     */
    public static Path getPath(Cell source, Cell destination, GameMap gameMap) {
        Cell[][] cells = gameMap.cells;
        CellExtended[][] map = new CellExtended[25][25];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                map[i][j] = new CellExtended(cells[i][j]);
            }
        }

        Queue<CellExtended> queue = new LinkedList<CellExtended>();
        map[source.y][source.x].weight = 0;
        queue.add(map[source.y][source.x]);

        CellExtended dest = null;
        while (!queue.isEmpty() && dest == null) {
            CellExtended ce = queue.poll();

            CellExtended tmp = calcul(queue, map, ce, ce.cell.leftCell, destination);
            if (tmp != null) {
                dest = tmp;
                break;
            }
            tmp = calcul(queue, map, ce, ce.cell.topCell, destination);
            if (tmp != null) {
                dest = tmp;
                break;
            }
            tmp = calcul(queue, map, ce, ce.cell.rightCell, destination);
            if (tmp != null) {
                dest = tmp;
                break;
            }
            tmp = calcul(queue, map, ce, ce.cell.bottomCell, destination);
            if (tmp != null) {
                dest = tmp;
                break;
            }
        }
        Path path = new Path();

        CellExtended tmp = dest;
        while (tmp != null) {
            path.cells.add(tmp.cell);
            tmp = tmp.previousCell;
        }

        Collections.reverse(path.cells);
        return path;
    }

    /**
     * Vérifie si la cellule suivante a un poids : <br />
     * - si oui : on l'oublie car un chemin déjà plus court l'a marquée<br />
     * - si non : on la marque dans le chemin
     * @param queue : file de calcul
     * @param map : la map
     * @param current : cellule en cours d'analyse
     * @param next : cellule suivante de celle en cours d'analyse
     * @param destination : cellule de destination
     * @return la cellule étendue si c'est la destination
     */
    private static CellExtended calcul(Queue<CellExtended> queue, CellExtended[][] map, CellExtended current, Cell next, Cell destination) {
        if (next != null) {
            CellExtended nextCe = map[next.y][next.x];
            if (nextCe.weight == -1) {
                nextCe.weight = current.weight + 1;
                nextCe.previousCell = current;
                if (destination.equals(next)) {
                    return nextCe;
                }
                queue.add(nextCe);
            }
        }
        return null;
    }
}
