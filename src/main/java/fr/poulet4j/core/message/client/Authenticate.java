package fr.poulet4j.core.message.client;

import fr.poulet4j.model.Profil;

public class Authenticate extends ClientMessage {

    public static final String TYPE = "authenticate";

    public String name;
    public String avatar;

    /**
     * le profil de l'ia.
     */
    public Profil profil;

    public Authenticate() {
        super(TYPE);
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the avatar.
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Set the avatar.
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Authenticate [name=" + name + ", avatar=" + avatar + ", profil=" + profil.name() + "]";
    }
}
