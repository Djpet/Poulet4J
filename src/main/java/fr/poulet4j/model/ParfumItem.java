package fr.poulet4j.model;

/**
 * Item de parfum aux hormones de poule.<br>
 * Utilisé pour donner un bonus en PM au Coq.<br>
 * {@link GameConstants#PARFUM_PM_BOOST}
 */
public class ParfumItem extends Item {

    private static final long serialVersionUID = 1L;

    public ParfumItem() {
        super(ItemType.PARFUM);
    }

}
