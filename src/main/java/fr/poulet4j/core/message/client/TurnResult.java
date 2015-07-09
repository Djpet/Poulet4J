package fr.poulet4j.core.message.client;

import java.util.ArrayList;
import java.util.List;

import fr.poulet4j.model.IAInfo;
import fr.poulet4j.order.TurnAction;

/**
 * Retour la liste des actions d'un tour.
 */
public class TurnResult extends ClientMessage {

    public static final String TYPE = "turnResult";

    /** la liste des actions */
    public List<TurnAction> actions;

    /** les informations sur l'IA */
    public IAInfo ia;

    public TurnResult() {
        super(TYPE);
        actions = new ArrayList<TurnAction>(5);
    }

}
