package main.util;

import java.util.List;

import main.json.me.JSONObject;
import main.util.vo.BookInfo;
import main.util.vo.ChapterInfo;

public interface IJsonParseUtil {
    public List<ChapterInfo> GetChapters(String json);

    public List<BookInfo> GetBooks(String json);

    public ChapterInfo getChapter(String json);

    public List<ChapterInfo> getUnUpdateChapter(String json);
    
    

}
