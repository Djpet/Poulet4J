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

    /** Liste des items récupérées **/
    public ArrayList<Item> items;

    /** Durée en nombre de tour de l'invisibilité **/
    public int invisibilityDuration = 0;

    /** le profil de l'ia */
    public Profil profil;

    @JsonIgnore
    public Cell position;
}
