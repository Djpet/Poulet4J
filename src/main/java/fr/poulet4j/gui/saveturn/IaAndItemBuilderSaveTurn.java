package fr.poulet4j.gui.saveturn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.poulet4j.gui.Controler;
import fr.poulet4j.gui.ImageRepo;
import fr.poulet4j.gui.MapUI;
import fr.poulet4j.gui.OneTurnImageBuilder;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.save.SaveTurn;

public class IaAndItemBuilderSaveTurn implements OneTurnImageBuilder<SaveTurn> {

    private static final int SIZE_IA_CIRCLE = 3;

    public BufferedImage build(SaveTurn saveTurn) {
        BufferedImage bufferedImage = new BufferedImage(MapUI.MAP_SIZE, MapUI.MAP_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();

        Cell[][] cells = saveTurn.data.cells;
        IAInfo ia1 = saveTurn.data.iaList.get(0);
        IAInfo ia2 = saveTurn.data.iaList.get(1);
        for (int x = 0; x < MapUI.CELL_NUMBER; x++) {
            for (int y = 0; y < MapUI.CELL_NUMBER; y++) {
                Cell cell = cells[y][x];
                if (cell.item != null) {
                    switch (cell.item.type) {
                        case PARFUM:
                            g.drawImage(ImageRepo.potionSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;
                        case POTION:
                            g.drawImage(ImageRepo.parfumSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;
                        case TRAP:
                            g.drawImage(ImageRepo.trapSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;

                        default:
                            break;
                    }
                }
                if (cell.occupant != null) {
                    if (ia1.id == cell.occupant.id) {
                        g.setBackground(new Color(69, 185, 185));
                        g.setColor(new Color(69, 185, 185));
                        g.fillOval(SIZE_IA_CIRCLE + x * MapUI.CELL_SIZE, SIZE_IA_CIRCLE + y * MapUI.CELL_SIZE, MapUI.CELL_SIZE - SIZE_IA_CIRCLE * 2,
                                MapUI.CELL_SIZE - SIZE_IA_CIRCLE * 2);
                    }
                    if (ia2.id == cell.occupant.id) {
                        g.setBackground(new Color(251, 176, 65));
                        g.setColor(new Color(251, 176, 65));
                        g.fillOval(SIZE_IA_CIRCLE + x * MapUI.CELL_SIZE, SIZE_IA_CIRCLE + y * MapUI.CELL_SIZE, MapUI.CELL_SIZE - SIZE_IA_CIRCLE * 2,
                                MapUI.CELL_SIZE - SIZE_IA_CIRCLE * 2);
                    }
                    switch (cell.occupant.profil) {
                        case HAND_OF_THE_KING:
                            g.drawImage(ImageRepo.elfeSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;
                        case MASTER_OF_COINS:
                            g.drawImage(ImageRepo.nainSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;
                        case TECH_WIZARD:
                            g.drawImage(ImageRepo.alchimisteSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE,
                                    null);
                            break;
                        case SHEEP:
                            g.drawImage(ImageRepo.pouletSprite(), x * MapUI.CELL_SIZE, y * MapUI.CELL_SIZE, MapUI.CELL_SIZE, MapUI.CELL_SIZE, null);
                            break;
                        default:
                            break;

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
