package fr.poulet4j.gui.saveturn;

import fr.poulet4j.gui.InfosIaBuilder;
import fr.poulet4j.gui.MapUI;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.model.Item;
import fr.poulet4j.order.GetItemOrder;
import fr.poulet4j.order.MoveOrder;
import fr.poulet4j.order.TurnAction;
import fr.poulet4j.order.UseItemOrder;
import fr.poulet4j.save.SaveTurn;

@SuppressWarnings("deprecation")
public class InfosIaBuilderSaveTurn implements InfosIaBuilder<SaveTurn> {

    public String[] getInfos(SaveTurn saveTurn) {
        String[] infos = new String[3];
        infos[0] = buildInfoIa(saveTurn.data.iaList.get(0), "Ia 1 (bleu) : ", saveTurn);
        infos[1] = buildInfoIa(saveTurn.data.iaList.get(1), "Ia 2 (jaune) : ", saveTurn);
        infos[2] = buildInfoIa(saveTurn.data.iaList.get(2), "Poulet : ", saveTurn);

        return infos;
    }

    private String buildInfoIa(IAInfo iaInfo, String iaTitle, SaveTurn saveTurn) {
        StringBuilder sb = new StringBuilder();
        sb.append(iaTitle).append(iaInfo.name).append("\n");
        if (iaInfo.position != null) {
            sb.append("x : ").append(iaInfo.position.x).append(" / y : ").append(iaInfo.position.y).append("\n");
        } else {
            sb.append("x : ? / y : ?\n");
        }
        sb.append("profil : ").append(iaInfo.profil.name()).append("\n");
        sb.append("pm : ").append(iaInfo.pm).append("\n");
        sb.append("invisibilityDuration : ").append(iaInfo.invisibilityDuration).append("\n");

        int parfum = 0;
        int potion = 0;
        int trap = 0;
        for (Item item : iaInfo.items) {
            switch (item.type) {
                case PARFUM:
                    parfum++;
                    break;
                case POTION:
                    potion++;
                    break;
                case TRAP:
                    trap++;
                    break;
                default:
                    break;
            }
        }

        sb.append("parfum : ").append(parfum).append("\n");
        sb.append("potion : ").append(potion).append("\n");
        sb.append("trap : ").append(trap).append("\n");

        if (saveTurn.turnResult.ia.id == iaInfo.id) {
            sb.append("\n").append("order : ").append("\n");
            for (TurnAction turnAction : saveTurn.turnResult.actions) {
                if (turnAction instanceof MoveOrder) {
                    sb.append(" - move ").append(findXY(((MoveOrder) turnAction).target, saveTurn.data.cells)).append("\n");
                } else if (turnAction instanceof GetItemOrder) {
                    sb.append(" - get item ").append("\n");
                } else if (turnAction instanceof UseItemOrder) {
                    sb.append(" - use item : ").append(((UseItemOrder) turnAction).item.type.getItemType()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String findXY(long target, Cell[][] cells) {
        for (int x = 0; x < MapUI.CELL_NUMBER; x++) {
            for (int y = 0; y < MapUI.CELL_NUMBER; y++) {
                if (cells[y][x].id == target) {
                    return "(x=" + x + ",y=" + y + ")";
                }
            }
        }
        return "";
    }
}
