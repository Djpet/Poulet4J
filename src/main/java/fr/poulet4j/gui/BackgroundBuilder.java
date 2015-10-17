package fr.poulet4j.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.poulet4j.model.Cell;
import fr.poulet4j.save.SaveTurn;

public class BackgroundBuilder implements OneTurnImageBuilder {

    private BufferedImage bufferedImage;

    public BufferedImage build(final SaveTurn saveTurn) {

        if (bufferedImage == null) {
            bufferedImage = new BufferedImage(MapUI.MAP_SIZE, MapUI.MAP_SIZE, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setBackground(Color.WHITE);
            g.fillRect(0, 0, MapUI.MAP_SIZE, MapUI.MAP_SIZE);

            Cell[][] cells = saveTurn.data.cells;
            g.setColor(Color.BLACK);
            for (int x = 0; x < MapUI.CELL_NUMBER; x++) {
                for (int y = 0; y < MapUI.CELL_NUMBER; y++) {
                    Cell cell = cells[y][x];
                    if (cell.top == 0) {
                        g.drawLine(x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, (x + 1) * MapUI.CELL_SIZE - 1, y * MapUI.CELL_SIZE);
                    }
                    if (cell.bottom == 0) {
                        g.drawLine(x * MapUI.CELL_SIZE, (y + 1) * MapUI.CELL_SIZE - 1, (x + 1) * MapUI.CELL_SIZE - 1, (y + 1) * MapUI.CELL_SIZE - 1);
                    }
                    if (cell.left == 0) {
                        g.drawLine(x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, x * MapUI.CELL_SIZE, (y + 1) * MapUI.CELL_SIZE - 1);
                    }
                    if (cell.right == 0) {
                        g.drawLine((x + 1) * MapUI.CELL_SIZE - 1, y * MapUI.CELL_SIZE, (x + 1) * MapUI.CELL_SIZE - 1, (y + 1) * MapUI.CELL_SIZE - 1);
                    }
                }
            }
        }
        return bufferedImage;
    }

}
