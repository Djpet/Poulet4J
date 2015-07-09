package fr.poulet4j.save;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameMap;

public class SaveGame {

    public LinkedList<SaveTurn> turns;
    public Date time;

    public void init() {
        time = new Date();
        turns = new LinkedList<SaveTurn>();
    }

    public void add(GameMap data, int duration) {
        SaveTurn saveTurn = new SaveTurn();
        saveTurn.data = data;
        saveTurn.duration = duration;
        turns.add(saveTurn);
    }

    public void write() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh_mm_ss");
        try {
            String filename = sdf.format(time) + "-" + turns.get(0).data.iaList.get(0).name + "_vs_" + turns.get(0).data.iaList.get(1).name + ".log";
            FileWriter writer = new FileWriter(filename);
            for (SaveTurn saveTurn : turns) {
                writer.write(formatTurn(saveTurn) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatTurn(SaveTurn turn) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(turn.duration).append("-");
        Cell[][] cells = turn.data.cells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int code = 0;
                Cell cell = cells[i][j];
                if (cell.top != 0) {
                    code |= SaveConstante.TOP;
                }
                if (cell.bottom != 0) {
                    code |= SaveConstante.BOTTOM;
                }
                if (cell.left != 0) {
                    code |= SaveConstante.LEFT;
                }
                if (cell.right != 0) {
                    code |= SaveConstante.RIGHT;
                }
                if (cell.moi) {
                    code |= SaveConstante.MOI;
                }
                if (cell.ennemi) {
                    code |= SaveConstante.ENNEMI;
                }
                if (cell.poulet) {
                    code |= SaveConstante.POULET;
                }
                stringBuffer.append(StringUtils.leftPad(Integer.toHexString(code), SaveConstante.PAD, '0'));
            }
        }
        return stringBuffer.toString();
    }
}
