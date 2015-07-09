package fr.poulet4j.core;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.poulet4j.core.message.client.ClientMessage;

public class SocketWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketWriter.class);

    private static final byte[] END_CHAR = "#end#".getBytes();

    private OutputStream outputStream;
    private ObjectMapper mapper;

    public SocketWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
        mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    public void write(ClientMessage message) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Envoi message type '{}' : {}", message.getType(), mapper.writeValueAsString(message));
            }
            mapper.writeValue(outputStream, message);
            outputStream.write(END_CHAR);
            outputStream.flush();
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
