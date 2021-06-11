package Client;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/*
 * Create Console Client To connect to WebSocket Server
 * Mahmoud Hamdy - 11/6/2021
 */

public class MainClient {
    public static final String SERVER_URL = "ws://localhost:7770/";

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        CountDownLatch messageLatch = new CountDownLatch(1); //to make sure that main thread does not exit after executing the code
        try {
            container.connectToServer(WSClientHandler.class, new URI(SERVER_URL));
            messageLatch.await();
        } catch (DeploymentException | URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
