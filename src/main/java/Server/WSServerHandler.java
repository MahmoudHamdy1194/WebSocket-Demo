package Server;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.util.Random;
import java.util.TimerTask;

/*
 * Server Update client with random messages every 2 sec
 * Mahmoud Hamdy - 9/6/2021
 */

@WebSocket
public class WSServerHandler {

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        broadcast(session);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        System.out.println("Received Message: " + message);
    }

    public void broadcast(Session session) {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Going To send a new message to the client");
                String generatedMessage = generateRandomMessage();
                session.getRemote().sendStringByFuture(generatedMessage);
            }
        }, 0, 2000);
    }

    public static String generateRandomMessage() {
        String[] randomMessages = {"Hello Client, i'm from WebSocket Server", "What's up Client?", "Hallelujah!", "LOL", "HAHA"};
        int rnd = new Random().nextInt(randomMessages.length);
        return randomMessages[rnd];
    }


}