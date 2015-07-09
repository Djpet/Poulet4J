package fr.poulet4j.core.message.server;

import fr.poulet4j.core.message.SocketMessage;

public abstract class GameServerMessage extends SocketMessage {

    public GameServerMessage(String type) {
        super(type);
    }

}
