package fr.poulet4j.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class MapUI extends JPanel {

    public static final int CELL_NUMBER = 25;
    public static final int CELL_SIZE = 30;
    public static final int MAP_SIZE = CELL_NUMBER * CELL_SIZE;

    private static final long serialVersionUID = 1L;

    private List<BufferedImage> images;

    public MapUI() {
        setPreferredSize(new Dimension(MAP_SIZE, MAP_SIZE));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        if (images != null) {
            for (BufferedImage bufferedImage : images) {
                g.drawImage(bufferedImage, 0, 0, null);
            }
        }
    }

    public void setImages(final List<BufferedImage> images) {
        this.images = images;
        super.revalidate();
    }
}
