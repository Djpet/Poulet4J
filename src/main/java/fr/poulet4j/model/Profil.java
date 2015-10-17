package fr.poulet4j.model;

/**
 * Le profil d'une IA
 */
public enum Profil {

    /**
     * Le nain est immunisé contre les pièges.
     */
    MASTER_OF_COINS(0),

    /**
     * L'alchimiste est immunisé contre la potion d'invisibilité (immunise aussi le poulet).
     */
    TECH_WIZARD(1),

    /**
     * L'elfe immunise le poulet contre le parfum de poulette.
     */
    HAND_OF_THE_KING(2),

    /**
     * Le poulet (ou c'est un mouton déguisé en poulet, mystère).
     */
    SHEEP(3);

    private int code;

    private Profil(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
