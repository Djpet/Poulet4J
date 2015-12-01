package fr.poulet4j.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** Objet du model représentant une IA **/
public class IAInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** id de l'IA **/
    public long id;

    /** nom de l'IA **/
    public String name;

    /** url de son avatar **/
    public String avatar;

    /** Points de mouvement **/
    public int pm;

    /** Liste des items récupérés **/
    public ArrayList<Item> items;

    /** Durée en nombre de tour de l'invisibilité **/
    public int invisibilityDuration = 0;

    /** le profil de l'ia */
    public Profil profil;

    /** le token pour controler l'identité de l'IA. www.codeofwar.net pour obtenir un token. */
    public String token;

    /**
     * Constructeur.
     */
    public IAInfo() {
        items = new ArrayList<Item>();
    }

    /**
     * Constructeur.
     * @param name : nom de l'IA
     * @param avatar : url de l'avatar
     * @param profil : profil de l'IA
     */
    public IAInfo(String name, String avatar, Profil profil, String token) {
        this.name = name;
        this.avatar = avatar;
        this.profil = profil;
        this.token = token;
        items = new ArrayList<Item>();
    }

    /** Position de l'IA sur la map */
    @JsonIgnore
    public Cell position;
}
