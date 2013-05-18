package main.staticvalue;

import main.json.me.JSONException;
import main.json.me.JSONObject;

public class MesageName {
    public static final int JoinRoom = 1;
    public static final int getChapter = 2;
    public static final int getUnReadChapter = 3;
    public static final int QuitRoom = 4;
    public static final int getBookList = 5;
    public static final int getChapterList = 6;
    public static final int sendMessage = 7;
    public static final String key = "key";
    public static final String value = "value";

    public class messageType {
        public int type;
        public String value;

        public messageType(String parsevalue) {

            try {
                JSONObject jsonObject = new JSONObject(parsevalue);
                this.value = jsonObject.getString(value);
                this.type = jsonObject.getInt(key);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
