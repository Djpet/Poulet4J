package fr.poulet4j;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.poulet4j.core.SocketReader;
import fr.poulet4j.core.SocketWriter;
import fr.poulet4j.core.message.client.Authenticate;
import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.core.message.server.GameServerMessage;
import fr.poulet4j.core.message.server.GetTurnOrder;
import fr.poulet4j.core.message.server.ID;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameMap;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.order.TurnAction;
import fr.poulet4j.save.ISaveGame;
import fr.poulet4j.save.NoSaveGame;
import fr.poulet4j.save.SaveGame;

/**
 * Lanceur de l'IA.
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private IA ia;

    protected Socket socket;

    protected SocketReader reader;

    protected SocketWriter writer;

    private long id;

    private IAInfo iaInfo;

    private ISaveGame saveGame;

    public Runner(final IA ia) {
        this.ia = ia;
        iaInfo = ia.getIAInfo();
        saveGame = new NoSaveGame();
    }

    /**
     * Charge l'IA.
     * @param ia : votre IA.
     * @return le runner
     */
    public static Runner ia(final IA ia) {
        return new Runner(ia);
    }

    /**
     * Sauvegarde la partie (perte environs : 10 ms sur le premier tour et 1-3 ms ensuite)
     * @return le runner
     */
    public Runner saveGame() {
        saveGame = new SaveGame();
        return this;
    }

    /**
     * Sauvegarde la partie
     * @param iSaveGame : votre implémentation de la sauvegarde la partie
     * @return le runner
     */
    public Runner saveGame(final ISaveGame iSaveGame) {
        saveGame = iSaveGame;
        return this;
    }

    /**
     * Connect l'ia au serveur et attend la prochaine partie.
     * @param host : nom ou adresse IP du serveur (port par défault à 8127
     */
    public void connectAndRun(final String host) {
        connectAndRun(host, 8127);
    }

    /**
     * Connect l'ia au serveur et attend la prochaine partie.
     * @param host : nom ou adresse IP du serveur
     * @param port : port de connection
     */
    public void connectAndRun(final String host, final int port) {

        try {
            socket = new Socket(host, port);
            reader = new SocketReader(socket.getInputStream());
            writer = new SocketWriter(socket.getOutputStream());

            authenticate();

            run();
        } catch (UnknownHostException e) {
            LOGGER.error("Serveur '" + host + "' incconu : ", e);
        } catch (IOException e) {
            LOGGER.error("Problème de connexion : ", e);
        }
    }

    private void authenticate() throws IOException {
        Authenticate authenticate = new Authenticate();
        authenticate.name = iaInfo.name;
        authenticate.avatar = iaInfo.avatar;
        authenticate.profil = iaInfo.profil.getCode();
        writer.write(authenticate);

        GameServerMessage message = reader.read();
        if (!(message instanceof ID)) {
            throw new RuntimeException("Message de type 'id' attendu, mais reçu type : " + message.getType());
        }
        id = ((ID) message).getId();
        iaInfo.id = id;

        ia.afterConnect(iaInfo);
        LOGGER.info("Connection OK");
        saveGame.init();
    }

    private void run() throws IOException {
        while (true) {
            GameServerMessage message = reader.read();

            if (message instanceof GetTurnOrder) {
                long start = System.currentTimeMillis();
                GameMap data = ((GetTurnOrder) message).data;
                fillInfo(data);
                iaInfo = data.moi;
                LOGGER.debug("fillInfo {}", System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                List<TurnAction> actions = ia.getOrders(data);
                TurnResult turnResult = new TurnResult();
                turnResult.ia = iaInfo;
                turnResult.actions = actions;
                writer.write(turnResult);
                saveGame.write(data, turnResult, (int) (System.currentTimeMillis() - start), message.json);
            }
        }
    }

    private void fillInfo(final GameMap data) {
        // ia
        if (data.iaList.get(0).id == id) {
            data.moi = data.iaList.get(0);
            data.ennemi = data.iaList.get(1);
        } else {
            data.moi = data.iaList.get(1);
            data.ennemi = data.iaList.get(0);
        }
        data.poulet = data.iaList.get(2);

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
                    if (cell.occupant.id == id) {
                        cell.moi = true;
                        data.moi.position = cell;
                    }
                    if (cell.occupant.id == data.poulet.id) {
                        cell.poulet = true;
                        data.poulet.position = cell;
                    }
                    if (cell.occupant.id == data.ennemi.id) {
                        cell.ennemi = true;
                        data.ennemi.position = cell;
                    }
                }
            }
        }
    }

}
