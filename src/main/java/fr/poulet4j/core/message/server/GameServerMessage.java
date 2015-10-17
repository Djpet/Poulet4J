package fr.poulet4j.core.message.server;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.poulet4j.core.message.SocketMessage;

public abstract class GameServerMessage extends SocketMessage {

    @JsonIgnore
    public String json;

    public GameServerMessage(String type) {
        super(type);
    }

}
