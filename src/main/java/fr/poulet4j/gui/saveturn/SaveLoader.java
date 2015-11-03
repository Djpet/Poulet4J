package fr.poulet4j.gui.saveturn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.core.message.server.GetTurnOrder;
import fr.poulet4j.gui.GameReload;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameConstants;
import fr.poulet4j.model.GameMap;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.save.SaveTurn;
import fr.poulet4j.save.StringCompressor;

public class SaveLoader implements GameReload<SaveTurn> {

    private ObjectMapper mapper;

    public SaveLoader() {
        mapper = new ObjectMapper();
    }

    public List<SaveTurn> loadFile(final File file) {
        List<SaveTurn> turns = new ArrayList<SaveTurn>(GameConstants.GAME_MAX_NUM_TURN);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                turns.add(readBlock(line));
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return turns;
    }

    private SaveTurn readBlock(final String line) throws UnsupportedEncodingException {
        String decompress = StringCompressor.decompress(line);
        String[] split = decompress.split("#end#");
        SaveTurn saveTurn = new SaveTurn();
        saveTurn.duration = Integer.parseInt(split[0]);
        try {
            saveTurn.data = fill(mapper.readValue(split[1], GetTurnOrder.class).data);
            saveTurn.turnResult = mapper.readValue(split[2], TurnResult.class);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveTurn;
    }

    private GameMap fill(GameMap data) {
        // map
        Cell[][] cells = data.cells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                cell.x = j;
                cell.y = i;
                if (cell.top != 0) {
                    cell.topCell = cells[i - 1][j];
                }
                if (cell.bottom != 0) {
                    cell.bottomCell = cells[i + 1][j];
                }
                if (cell.left != 0) {
                    cell.leftCell = cells[i][j - 1];
                }
                if (cell.right != 0) {
                    cell.rightCell = cells[i][j + 1];
                }
                if (cell.occupant != null) {
                    for (IAInfo iaInfo : data.iaList) {
                        if (cell.occupant.id == iaInfo.id) {
                            iaInfo.position = cell;
                        }
                    }
                }
            }
        }
        return data;
    }

}
