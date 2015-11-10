package fr.poulet4j.gui.gamemap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.poulet4j.gui.Controler;
import fr.poulet4j.gui.MapUI;
import fr.poulet4j.gui.OneTurnImageBuilder;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameMap;

public class BackgroundBuilderGameMap implements OneTurnImageBuilder<GameMap> {

    private BufferedImage bufferedImage;

    public BufferedImage build(GameMap data) {

        if (bufferedImage == null) {
            bufferedImage = new BufferedImage(MapUI.MAP_SIZE, MapUI.MAP_SIZE, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setBackground(Color.WHITE);
            g.fillRect(0, 0, MapUI.MAP_SIZE, MapUI.MAP_SIZE);

            Cell[][] cells = data.cells;
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

    public JPanel getControlPanel(Controler controler) {
        return null;
    }
}
