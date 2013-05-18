package main;

import java.awt.RenderingHints.Key;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import main.util.IJsonParseUtil;
import main.util.IJsonUtil;
import main.util.IMessageManager;
import main.util.IRoomJsonParsUtil;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class CustomWebSocketServer extends WebSocketServer {
    // 房间分类
    public static HashMap<String, List<WebSocket>> mHashMap = new HashMap<String, List<WebSocket>>();

    // 命令分类
    public static final String[] orders = { "/quit", "/join", "/mesg", "/noty", "/eror", "/ofli", "/onli", "/tget",
            "/post", "/seve", "/pter", "/boks", "/ptes", "/upts" };

    public static final String quit = "/quit";
    public static final String join = "/join";
    public static final String mesg = "/mesg";
    public static final String noty = "/noty";
    public static final String eror = "/eror";

    public static final String offline = "/ofli";
    public static final String online = "/onli";
    public static final String tget = "/tget";
    public static final String post = "/post";

    public static final String chpter = "/pter";
    public static final String books = "/boks";
    public static final String chpters = "/ptes";
    public static final String upchapts = "/upts";

    // 命令执行列表
    public static HashMap<String, Excute> excutes = new HashMap<String, CustomWebSocketServer.Excute>();

    private static IRoomJsonParsUtil iRoomJsonParsUtil;
    private static IMessageManager iMessageManager;
    private static IJsonUtil iJsonUtil;

    public static void main(String[] args) {
        final CustomWebSocketServer mCustomWebSocketServer;
        try {
            mCustomWebSocketServer = new CustomWebSocketServer(8887);
            excutes.put(quit, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    String room = iRoomJsonParsUtil.parserJionRoom(arg1);
                    if (mHashMap.containsKey(room)) {
                        mHashMap.get(room).remove(arg0);
                    }
                }
            });
            excutes.put(eror, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub

                }
            });
            excutes.put(join, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    String room = iRoomJsonParsUtil.parserJionRoom(arg1);
                    if (mHashMap.containsKey(room)) {
                        mHashMap.get(room).remove(arg0);
                        mHashMap.get(room).add(arg0);
                    } else {
                        List<WebSocket> mSockets = new ArrayList<WebSocket>();
                        mSockets.add(arg0);
                        mHashMap.put(room, mSockets);
                    }

                }
            });
            excutes.put(mesg, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    String[] rooms = iMessageManager.getMessage(arg1);
                    mCustomWebSocketServer.sendRoomMessage(rooms[0], rooms[1]);

                }
            });

            excutes.put(noty, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    mCustomWebSocketServer.sendToAll(mesg);

                }
            });

            excutes.put(tget, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub

                }
            });

            excutes.put(chpter, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub

                    arg0.send(iJsonUtil.GetChapter(arg1));
                }
            });

            excutes.put(chpters, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    arg0.send(iJsonUtil.GetChapter(arg1));
                }
            });

            excutes.put(books, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    arg0.send(iJsonUtil.GetBooks(arg1));
                }
            });
            excutes.put(upchapts, new Excute() {

                @Override
                public void toExcute(WebSocket arg0, String arg1) {
                    // TODO Auto-generated method stub
                    arg0.send(iJsonUtil.GetUnUpdateChapter(arg1));
                }
            });

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public CustomWebSocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public CustomWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMessage(WebSocket arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub

    }

    public void ParseMessage(WebSocket arg0, String arg1) {
        String order = arg1.substring(0, 5);
        System.out.println(order);
        if (excutes.containsKey(order)) {
            excutes.get(order).toExcute(arg0, arg1);
        } else {
            System.err.println("unKnowMessage:" + arg1);
        }
    }

    /**
     * Sends <var>text</var> to all currently connected WebSocket clients.
     * 
     * @param text
     *            The String to send across the network.
     * @throws InterruptedException
     *             When socket related I/O errors occur.
     */
    public void sendToAll(String text) {
        Collection<WebSocket> con = connections();
        synchronized (con) {
            for (WebSocket c : con) {
                c.send(text);
            }
        }
    }

    public void sendRoomMessage(String room, String message) {
        if (mHashMap.containsKey(room)) {
            List<WebSocket> mList = mHashMap.get(room);
            for (WebSocket ws : mList) {
                ws.send(message);
            }
        }
    }

    public interface Excute {
        public void toExcute(WebSocket arg0, String arg1);
    }

}
