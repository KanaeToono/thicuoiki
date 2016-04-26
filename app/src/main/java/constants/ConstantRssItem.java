package constants;

/**
 * Created by ConGa on 3/04/2016.
 */
public class ConstantRssItem {
    public static String TAG = ConstantRssItem.class.getSimpleName();
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "RssItem.db";
    public static final String TABLE_NAME = "RssItem_table";
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "_title";
    public static final String KEY_LINK = "_link";
    public static final String KEY_PUBDATE = "_pubDate";
    public static final String KEY_CHECKED_STATUS= "_status";
    public static final String KEY_PIORITY_STATUS ="_piority";
    public static final String KEY_DELETE_DATE ="_delete_date";


    public  static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT(100)," +
            KEY_LINK + " TEXT(100)," + KEY_PUBDATE + " DATE,"+ KEY_CHECKED_STATUS +" INTEGER,"
            + KEY_PIORITY_STATUS +" INTEGER," + KEY_DELETE_DATE + " INTEGER );";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public  static final String USER_AGENT_MOBILE ="Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr;\" +\n" +
            "                                        \" LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
    public  static final String USER_AGENT_WEB="Mozilla/5.0 (Windows NT 6.3; WOW64)\" +\n" +
            "                                                \" AppleWebKit/537.36 (KHTML,\" +\n" +
            "                                                \" like Gecko) Chrome/49.0.2623.112 Safari/537.36";
    public static final String USER_AGENT_WEB2 ="Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36";

}
