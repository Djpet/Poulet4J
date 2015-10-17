package fr.poulet4j.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageRepo {

    public static BufferedImage pouletSprite() {
        return read("chicken_sprite.png");
    }

    public static BufferedImage elfeSprite() {
        return read("knight_sprite.png");
    }

    public static BufferedImage nainSprite() {
        return read("dwarf_sprite.png");
    }

    public static BufferedImage alchimisteSprite() {
        return read("sorcerer_sprite.png");
    }

    public static BufferedImage parfumSprite() {
        return read("parfum_sprite.png");
    }

    public static BufferedImage potionSprite() {
        return read("potion_sprite.png");
    }

    public static BufferedImage trapSprite() {
        return read("trap_sprite.png");
    }

    private static BufferedImage read(String image) {
        try {
            return ImageIO.read(ImageRepo.class.getResource("/fr/poulet4j/gui/img/" + image));
        } catch (IOException e) {
            throw new RuntimeException("Imposible de charger l'image : " + image, e);
        }
    }

}
