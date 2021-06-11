package Server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/*
 * Server Update client with random messages every 2 sec
 * Don't forget to run websocket server first
 * Mahmoud Hamdy - 9/6/2021
 */

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(7770);
        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(WSServerHandler.class);
            }
        };
        try {
            server.setHandler(wsHandler);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
