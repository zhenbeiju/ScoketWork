package main.util;

import main.json.me.JSONObject;

public interface IRoomJsonUtil {
    /**
     * 加入房间的json字符串
     * 
     * @param roomName
     * @return 返回json格式的
     */

    public String joinRoom(String roomName);

    /**
     * quit a room
     * 
     * @return quit a room json
     * 
     */
    public String quitRoom(String roomName);
    
    

}
