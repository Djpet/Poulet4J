package fr.poulet4j.core.message.client;

/**
 * Message d'authentification d'une IA.
 */
public class Authenticate extends ClientMessage {

    public static final String TYPE = "authenticate";

    /** le nom de l'IA */
    public String name;

    /** l'url vers l'avatar de l'IA */
    public String avatar;

    /** le profil de l'ia. */
    public int profil;

    /**
     * Constructeur.
     */
    public Authenticate() {
        super(TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Authenticate [name=" + name + ", avatar=" + avatar + ", profil=" + profil + "]";
    }
}
