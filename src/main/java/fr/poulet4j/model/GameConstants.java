package fr.poulet4j.model;

/**
 * Constantes du jeu.
 */
public class GameConstants {

    /** Nombre de tour max de la game : {@value} */
    public static final int GAME_MAX_NUM_TURN = 200;

    /** Durée d'un tour : {@value} ms */
    public static final int TIMEOUT_DURATION = 2 * 1000;

    /** Nombre de tour où le poulet est invisible pour l'adversaire : {@value} tours */
    public static final int INVISIBILITY_DURATION = 10;

    /** Durée pendant laquelle un joueur ou le poulet est bloqué s'il tombe dans le piège : {@value} tours */
    public static final int TRAPED_DURATION = 10;

    /** Nombre maximum de PM cumulable : {@value} PMs max */
    public static final int MAX_PM = 5;

    /** Nombre de PM de offert au poulet (pour un boost) : {@value} PMs */
    public static final int PARFUM_PM_BOOST = 10;

}
