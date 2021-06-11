package Client;

import javax.websocket.*;
import java.util.logging.Logger;

/*
 * Create Console Client To connect to WebSocket Server
 * Mahmoud Hamdy - 11/6/2021
 */

@ClientEndpoint
public class WSClientHandler {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected to the server with Session ID ===>" + session.getId());
//        // In case the client wanna send message to the server
//        try {
//            session.getBasicRemote().sendText("Hi Server, i'm the client");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Received Message: " + message);
    }


}
