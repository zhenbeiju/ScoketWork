package main;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class CustomWebsocketClient extends WebSocketClient {

    public CustomWebsocketClient(URI serverURI) {
        super(serverURI);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onError(Exception arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMessage(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        // TODO Auto-generated method stub
        // TODO 加入房间，检查更新的相关逻辑

    }

}
