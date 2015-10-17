package fr.poulet4j;

import java.util.List;

import fr.poulet4j.model.GameMap;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.model.Profil;
import fr.poulet4j.order.TurnAction;

/**
 * Interface d'une IA.
 */
public interface IA {

    /**
     * Retourne l'{@link IAInfo} de l'IA avec le nom, l'avatar et le {@link Profil}
     * @return l'{@link IAInfo}
     */
    public IAInfo getIAInfo();

    /**
     * Méthode appelée après la connexion.<br>
     * Elle renvoi l'IaInfo complété par l'ID.
     * @param iaInfo : l'IaInfo
     */
    public void afterConnect(IAInfo iaInfo);

    /**
     * Demande des actions à effectuer pour le tour
     * @param gameMap : carte du labyrinthe
     * @return les actions
     */
    public List<TurnAction> getOrders(GameMap gameMap);

}
