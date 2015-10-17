package fr.poulet4j.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextPane;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.core.message.server.GetTurnOrder;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameConstants;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.model.Item;
import fr.poulet4j.order.GetItemOrder;
import fr.poulet4j.order.MoveOrder;
import fr.poulet4j.order.TurnAction;
import fr.poulet4j.order.UseItemOrder;
import fr.poulet4j.save.SaveTurn;
import fr.poulet4j.save.StringCompressor;

public class GuiControler {

    private List<SaveTurn> saveTurns;

    private Gui gui;

    private ObjectMapper mapper;

    private OneTurnImageBuilder backgroundBuilder;
    private OneTurnImageBuilder iIaAndItemBuilder;

    private List<TurnImageBuilder> turnBuilders;

    public GuiControler(final Gui gui) {
        this.gui = gui;
        mapper = new ObjectMapper();
        backgroundBuilder = new BackgroundBuilder();
        iIaAndItemBuilder = new IaAndItemBuilder();
        turnBuilders = new LinkedList<TurnImageBuilder>();
    }

    public void changeTurn(final int value) {
        if (value >= saveTurns.size()) {
            return;
        }
        List<BufferedImage> images = new LinkedList<BufferedImage>();
        images.add(backgroundBuilder.build(saveTurns.get(value)));

        for (TurnImageBuilder builder : turnBuilders) {
            if (builder instanceof OneTurnImageBuilder) {
                images.add(((OneTurnImageBuilder) builder).build(saveTurns.get(value)));
            } else if (builder instanceof AllTurnsImageBuilder) {
                images.add(((AllTurnsImageBuilder) builder).build(saveTurns, value));
            }
        }

        images.add(iIaAndItemBuilder.build(saveTurns.get(value)));
        gui.mapUI.setImages(images);

        buildInfoIa(saveTurns.get(value).data.iaList.get(0), "Ia 1 (bleu) : ", gui.iaInfo1, saveTurns.get(value));
        buildInfoIa(saveTurns.get(value).data.iaList.get(1), "Ia 2 (jaune) : ", gui.iaInfo2, saveTurns.get(value));

        gui.repaint();
    }

    private void buildInfoIa(IAInfo iaInfo, String iaTitle, JTextPane iaInfo1, SaveTurn saveTurn) {
        StringBuilder sb = new StringBuilder();
        sb.append(iaTitle).append(iaInfo.name).append("\n");
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
                    sb.append(" - move ").append(findXY(((MoveOrder) turnAction).target, saveTurn.data.cells));
                } else if (turnAction instanceof GetItemOrder) {
                    sb.append(" - get item ");
                } else if (turnAction instanceof UseItemOrder) {
                    sb.append(" - use item : ").append(((UseItemOrder) turnAction).item.type.getItemType());
                }
            }
        }
        iaInfo1.setText(sb.toString());
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

    void loadFile(final File file) {
        saveTurns = new ArrayList<SaveTurn>(GameConstants.GAME_MAX_NUM_TURN);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                readBlock(line);
                line = reader.readLine();
            }
            reader.close();

            gui.slider.setValue(0);
            gui.slider.setMaximum(saveTurns.size());
            changeTurn(0);
            gui.slider.grabFocus();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readBlock(final String line) throws UnsupportedEncodingException {
        String decompress = StringCompressor.decompress(line);
        String[] split = decompress.split("#end#");
        SaveTurn saveTurn = new SaveTurn();
        saveTurn.duration = Integer.parseInt(split[0]);
        try {
            saveTurn.data = mapper.readValue(split[1], GetTurnOrder.class).data;
            saveTurn.turnResult = mapper.readValue(split[2], TurnResult.class);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveTurns.add(saveTurn);
    }

    public void add(TurnImageBuilder turnBuilder) {
        turnBuilders.add(turnBuilder);
    }

}
