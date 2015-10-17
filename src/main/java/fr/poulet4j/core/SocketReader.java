package fr.poulet4j.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.poulet4j.core.message.server.GameServerMessage;
import fr.poulet4j.core.message.server.GetTurnOrder;
import fr.poulet4j.core.message.server.ID;

public class SocketReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketReader.class);

    private BufferedReader reader;
    private HashMap<String, Class<? extends GameServerMessage>> map;
    private ObjectMapper mapper;

    public SocketReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        mapper = new ObjectMapper();
        initMap();
    }

    private void initMap() {
        map = new HashMap<String, Class<? extends GameServerMessage>>();
        map.put(ID.TYPE, ID.class);
        map.put(GetTurnOrder.TYPE, GetTurnOrder.class);
    }

    public void close() throws IOException {
        reader.close();
    }

    public GameServerMessage read() throws IOException {
        MsgJson msgJson = readMsg();
        long start = System.currentTimeMillis();
        GameServerMessage message = mapper.readValue(msgJson.getJson(), map.get(msgJson.getType()));
        message.json = msgJson.getJson();
        LOGGER.debug("Transforme {}", System.currentTimeMillis() - start);
        return message;
    }

    private MsgJson readMsg() throws IOException {
        StringBuilder resp = new StringBuilder();
        StringBuilder tampon = new StringBuilder();
        StringBuilder type = new StringBuilder();

        short isEnd = 0;
        short readType = 0;
        while (isEnd < 5) {
            char c = (char) reader.read();
            if (isEnd == 0) {
                if (c == '#') {
                    isEnd++;
                    tampon.append(c);
                } else {
                    resp.append(c);
                    if (readType < 8) {
                        if ((readType == 0 && c == 't') || (readType == 1 && c == 'y') || (readType == 2 && c == 'p') || (readType == 3 && c == 'e')
                                || (readType == 4 && c == '"') || (readType == 5 && c == ':') || (readType == 6 && c == '"')) {
                            readType++;
                        } else if (readType == 7) {
                            if (c == '"') {
                                readType++;
                            } else {
                                type.append(c);
                            }
                        } else if (c != ' ' && c != '\n' && c != '\r') {
                            readType = 0;
                        }
                    }
                }
            } else {
                if ((isEnd == 1 && c == 'e') || (isEnd == 2 && c == 'n') || (isEnd == 3 && c == 'd') || (isEnd == 4 && c == '#')) {
                    isEnd++;
                    tampon.append(c);
                } else {
                    isEnd = 0;
                    tampon.append(c);
                    resp.append(tampon.toString());
                    tampon = new StringBuilder();
                }
            }
        }
        MsgJson msgJson = new MsgJson(type.toString(), resp.toString());
        if (LOGGER.isDebugEnabled()) {
            if (GetTurnOrder.TYPE.equals(msgJson.getType()) && isEnd == 5) {
                LOGGER.debug("Reception message type '{}' : ", msgJson.getType());
            } else {
                LOGGER.debug("Reception message type '{}' : {}", msgJson.getType(), msgJson.getJson());
            }
        }
        return msgJson;
    }
}
