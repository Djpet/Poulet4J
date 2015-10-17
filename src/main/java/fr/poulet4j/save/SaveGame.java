package fr.poulet4j.save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.model.GameMap;

/**
 * Sauvegarde la game selon un format spécifique.
 */
public class SaveGame implements ISaveGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveGame.class);

    protected Date time;
    private String filename;
    private ObjectMapper mapper;

    /**
     * {@inheritDoc}
     */
    public void init() {
        time = new Date();
        mapper = new ObjectMapper();

        File saveDir = new File("save");
        if (!saveDir.exists()) {
            try {
                saveDir.mkdir();
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public void write(GameMap data, TurnResult turnResult, int duration, String json) {
        if (data.currentTurn == 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh_mm_ss");
            filename = "save/" + sdf.format(time) + "-" + data.iaList.get(0).name + "_vs_" + data.iaList.get(1).name + ".log";
        }
        SaveTurn saveTurn = new SaveTurn();
        saveTurn.data = data;
        saveTurn.duration = duration;
        saveTurn.turnResult = turnResult;
        saveTurn.json = json;
        try {
            long start = System.currentTimeMillis();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(formatTurn(saveTurn));
            writer.newLine();
            writer.close();

            LOGGER.debug("save {} ms", System.currentTimeMillis() - start);
        } catch (IOException e) {
            LOGGER.error("Erreur ecriture sauvegarde", e);
        }
    }

    private String formatTurn(SaveTurn turn) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(turn.duration).append("#end#");
        try {
            stringBuffer.append(turn.json);
            stringBuffer.append("#end#").append(mapper.writeValueAsString(turn.turnResult));
        } catch (JsonProcessingException e) {
            LOGGER.error("Erreur json : ", e);
        }
        return StringCompressor.compress(stringBuffer.toString());
    }
}
