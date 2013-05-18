package main.util;

import main.util.vo.ChapterInfo;

public interface IMessageJsonUtile {
    /**
     * 
     * @param chapterInfo
     * @return a json string , content chapter info .
     */
    public String SendChapter(ChapterInfo chapterInfo);

    /**
     * 获得发送消息至某个房间的json
     * 
     * @param message
     * @param roomName
     * @return
     */
    public String SendMessage(String message, String room);
}
