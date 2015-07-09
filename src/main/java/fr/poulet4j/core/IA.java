package fr.poulet4j.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.poulet4j.core.message.client.Authenticate;
import fr.poulet4j.core.message.client.TurnResult;
import fr.poulet4j.core.message.server.GameServerMessage;
import fr.poulet4j.core.message.server.GetTurnOrder;
import fr.poulet4j.core.message.server.ID;
import fr.poulet4j.model.Cell;
import fr.poulet4j.model.GameMap;
import fr.poulet4j.model.IAInfo;
import fr.poulet4j.order.TurnAction;
import fr.poulet4j.save.SaveGame;

/**
 * Base de l'IA.<br />
 * Se charge des communications réseaux et du déroulement du jeu.
 */
public abstract class IA {

    private static final Logger LOGGER = LoggerFactory.getLogger(IA.class);

    private long id;
    private String name;
    private String avatar;
    private IAInfo iaInfo;

    private Socket socket;
    private SocketReader reader;
    private SocketWriter writer;

    private SaveGame saveGame;

    /**
     * Constructor.
     * @param name : nom de l'IA.
     * @param avatar : avatar de l'IA.
     */
    public IA(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
        iaInfo = new IAInfo();
        iaInfo.name = name;
        iaInfo.avatar = avatar;
        saveGame = new SaveGame();
    }

    public abstract void initialize();

    public abstract List<TurnAction> getOrder(GameMap gameMap);

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            reader = new SocketReader(socket.getInputStream());
            writer = new SocketWriter(socket.getOutputStream());

            Authenticate authenticate = new Authenticate();
            authenticate.name = name;
            authenticate.avatar = avatar;
            writer.write(authenticate);

            GameServerMessage message = reader.read();
            if (!(message instanceof ID)) {
                throw new RuntimeException("Message de type 'id' attendu, mais reçu type : " + message.getType());
            }
            id = ((ID) message).getId();
            iaInfo.id = id;

            saveGame.init();
            run();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        int turn = 0;
        while (true) {
            GameServerMessage message = reader.read();

            if (message instanceof GetTurnOrder) {
                turn++;
                long start = System.currentTimeMillis();
                GameMap data = ((GetTurnOrder) message).data;
                fillInfo(data);
                LOGGER.debug("fillInfo {}", System.currentTimeMillis() - start);
                if (data.currentTurn == 0) {
                    initialize();
                }
                start = System.currentTimeMillis();
                List<TurnAction> actions = getOrder(data);
                TurnResult turnResult = new TurnResult();
                turnResult.ia = iaInfo;
                turnResult.actions = actions;
                saveGame.add(data, (int) (System.currentTimeMillis() - start));
                writer.write(turnResult);
                System.out.println(turn);
                if (turn == 100) {
                    saveGame.write();
                    turn = 0;
                }
            }
        }
    }

    private void fillInfo(GameMap data) {
        // ia
        if (data.iaList.get(0).id == id) {
            data.me = data.iaList.get(0);
            data.enemy = data.iaList.get(1);
        } else {
            data.me = data.iaList.get(1);
            data.enemy = data.iaList.get(0);
        }
        data.chiken = data.iaList.get(2);

        // map
        Cell[][] cells = data.cells;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                cell.c = j;
                cell.r = i;
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
                        data.me.position = cell;
                    }
                    if (cell.occupant.id == data.chiken.id) {
                        cell.poulet = true;
                        data.chiken.position = cell;
                    }
                    if (cell.occupant.id == data.enemy.id) {
                        cell.ennemi = true;
                        data.enemy.position = cell;
                    }
                }

            }
        }
    }

    /**
     * Get the id.
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the avatar.
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Get the iaInfo.
     * @return the iaInfo
     */
    public IAInfo getIaInfo() {
        return iaInfo;
    }

}
