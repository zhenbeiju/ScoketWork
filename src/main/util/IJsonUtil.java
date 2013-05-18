package main.util;

import main.util.vo.BookInfo;
import main.util.vo.ChapterInfo;

public interface IJsonUtil {
    /**
     * 
     * @param json Mayby contains page
     * @return {@link BookInfo}
     */
    public String GetBooks(String json);

    /**
     * contains book id
     * 
     * @param bookID or Page
     * @return {@link ChapterInfo}
     */
    public String GetAllChapter(String json);

    /**
     * contains bookID, timeline
     * 
     * @param bookID
     * @param timeline
     * @return {@link ChapterInfos}
     */
    public String GetUnUpdateChapter(String json);

    /**
     * contains a chapter bookID,ChapterID
     * 
     * @param info 
     * @return {@link ChapterInfo}
     */
    public String GetChapter(String json);

}
