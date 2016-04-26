package com.example.conga.finaltest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.conga.finaltest.interfaces.ICRUDDatabase;
import com.example.conga.finaltest.models.ContentRss;
import com.example.conga.finaltest.models.RssItem;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RssItemHelper extends SQLiteOpenHelper implements ICRUDDatabase {
    private static String TAG = RssItemHelper.class.getSimpleName();
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
   private static final String DATABASE_NAME = "databaseRss";
    // Table Names
    private static final String TABLE_TIEUDIEM = "tieudiem";
    private static final String TABLE_THONG_TIN_DAO_TAO = "thongtindaotao";
    private static final String TABLE_CONG_TAC_SINH_VIEN = "congtacsinhvien";
    private static final String TABLE_KHAO_THI = "khaothi";
    private static final String TABLE_KHOA_HOC_SAU_DAI_HOC = "khoahocsaudaihoc";
    private static final String TABLE_DANG_UY = "danguy";
    private static final String TABLE_DOAN_THANH_NIEN = "doanthanhnien";
    private static final String TABLE_TIN_SINH_VIEN = "tíninhvien";
    private static final String TABLE_THONG_BAO = "thongbao";
    private static final String TABLE_CO_HOI_NGHE_NGHIEP= "cohoinghenghiep";
    private static final String TABLE_DOAN_THANH_NIEN_IT= "doanthanhnienit";
    private static final String TABLE_OFFLINE= "offline";

    // tên cột chung
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "_title";
    private static final String KEY_LINK = "_link";
    private static final String KEY_DELETE_DAY = "_delete_day";
    private static final String KEY_STATUS_RSS= "_status_rss";
    private static final String KEY_PUBDATE = "_pub_date";
    private static final String KEY_CONTENT_HTML ="_content";
    private static final String KEY_IMAGE ="_content";

    private SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    //bảng tiêu điểm
    public static final String CREATE_TABLE_TIEU_DIEM = "CREATE TABLE " + TABLE_TIEUDIEM + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
            KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT, " + KEY_STATUS_RSS + "INTEGER);";

    // bảng thông tin đào tạo
    public static final String CREATE_TABLE_THONG_TIN_DAO_TAO = "CREATE TABLE " + TABLE_THONG_TIN_DAO_TAO+ "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
            KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";

    //BẢNG CÔNG TÁC SINH VIÊN
    public static final String CREATE_TABLE_CONG_TAC_SINH_VIEN = "CREATE TABLE " + TABLE_CONG_TAC_SINH_VIEN + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
            KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";

  // BẢNG KHẢO THÍ VÀ ĐẢM BẢO CHẤT LƯỢNG
  public static final String CREATE_TABLE_KHAO_THI_VA_DAM_BAO_CHAT_LUONG = "CREATE TABLE " + TABLE_KHAO_THI + "(" + KEY_ID +
          " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
          KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";

    //BẢNG KHÓA HỌC VÀ SAU ĐÀO TẠO
    public static final String CREATE_TABLE_KHOA_HOC_SAU_DAO_TAO= "CREATE TABLE " + TABLE_KHOA_HOC_SAU_DAI_HOC+ "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
            KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";

     //BẢNG ĐẢNG ỦY
    public static final String CREATE_TABLE_DANG_UY= "CREATE TABLE " + TABLE_DANG_UY+ "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
            KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";
//BẢNG ĐOÀN THANH NIÊN
public static final String CREATE_TABLE_DOAN_THANH_NIEN = "CREATE TABLE " + TABLE_DOAN_THANH_NIEN+ "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
        KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE+ " TEXT," + KEY_STATUS_RSS + "INTEGER);";
// BẢNG TIN SINH VIÊN
public static final String CREATE_TABLE_TIN_SINH_VIEN = "CREATE TABLE " + TABLE_TIN_SINH_VIEN+ "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
        KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";
//BẢNG THÔNG BAO
public static final String CREATE_TABLE_THONG_BAO = "CREATE TABLE " + TABLE_THONG_BAO + "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
        KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";
// BẢNG ĐOÀN THANH NIÊN IT
public static final String CREATE_TABLE_DOAN_THANH_NIEN_IT= "CREATE TABLE " + TABLE_DOAN_THANH_NIEN_IT+ "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
        KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";
// BẢNG CƠ HỘI NGHÊ NGHIỆP
public static final String CREATE_TABLE_CO_HOI_NGHE_NGHIEP = "CREATE TABLE " + TABLE_CO_HOI_NGHE_NGHIEP+ "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT," +
        KEY_LINK + " TEXT," + KEY_PUBDATE + " DATE, " + KEY_IMAGE + " TEXT," + KEY_STATUS_RSS + "INTEGER);";
    // offline table
public static final String CREATE_TABLE_OFFLINE_NEWS = "CREATE TABLE " + TABLE_OFFLINE + "(" + KEY_ID +
        " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"  + KEY_PUBDATE + " TEXT," + KEY_CONTENT_HTML + " TEXT);";

    public RssItemHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, " CREATE NEWS DATABASE SUCCESS");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        try {
            // TRƯƠNH
            db.execSQL(CREATE_TABLE_TIEU_DIEM);
            db.execSQL(CREATE_TABLE_THONG_TIN_DAO_TAO);
            db.execSQL(CREATE_TABLE_CONG_TAC_SINH_VIEN);
            db.execSQL(CREATE_TABLE_KHAO_THI_VA_DAM_BAO_CHAT_LUONG);
            db.execSQL(CREATE_TABLE_KHOA_HOC_SAU_DAO_TAO);
            db.execSQL(CREATE_TABLE_DANG_UY);
            db.execSQL(CREATE_TABLE_DOAN_THANH_NIEN);
            db.execSQL(CREATE_TABLE_TIN_SINH_VIEN);
            Log.d(TAG, "CREATE TABLE SUCCESS");
            // KHOA TIN
            db.execSQL(CREATE_TABLE_THONG_BAO);
            db.execSQL(CREATE_TABLE_CO_HOI_NGHE_NGHIEP);
            db.execSQL(CREATE_TABLE_DOAN_THANH_NIEN_IT);
            Log.d(TAG, "CREATE TABLE SUCCESS");
            //OFLINE
           db.execSQL(CREATE_TABLE_OFFLINE_NEWS);
            Log.d(TAG, "CREATE TABLE SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Lỗi rồi");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        try {
            // TRƯỜNG
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CO_HOI_NGHE_NGHIEP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOAN_THANH_NIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_KHAO_THI);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIN_SINH_VIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONG_TAC_SINH_VIEN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANG_UY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIEUDIEM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_TIN_DAO_TAO);

            //KHOA TIN

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOAN_THANH_NIEN_IT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_BAO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CO_HOI_NGHE_NGHIEP);
            // OFFLINE
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFLINE);
            // create new tables
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addNewTieuDiem(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
           sqLiteDatabase.insert(TABLE_TIEUDIEM, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<RssItem> getAllTieuDiem() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_TIEUDIEM +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemTieuDiem(int id_rss) {

    }

    @Override
    public void upDateStatusTieuDiem(int status, int id_rss) {

    }

    @Override
    public int getCountTieuDiem() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_TIEUDIEM;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewThongTinDaoTao(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_THONG_TIN_DAO_TAO, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllThongTinDaoTao() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_THONG_TIN_DAO_TAO +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;

    }

    @Override
    public void deleteItemThongTinDaoTao(int id_rss) {

    }

    @Override
    public void upDateStatusThongTinDaoTao(int status, int id_rss) {

    }

    @Override
    public int getCountThongTinDaoTao() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_THONG_TIN_DAO_TAO;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewCongTacSinhVien(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_CONG_TAC_SINH_VIEN, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllCongTacSinhVien() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_CONG_TAC_SINH_VIEN +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemCongTacSinhVien(int id_rss) {

    }

    @Override
    public void upDateStatusCongTacSinhVien(int status, int id_rss) {

    }

    @Override
    public int getCountCongTacSinhVien() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_CONG_TAC_SINH_VIEN;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewKhaoThi(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_KHAO_THI, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllKhaoThi() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_KHAO_THI +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemKhaoThi(int id_rss) {

    }

    @Override
    public void upDateStatusKhaoThi(int status, int id_rss) {

    }

    @Override
    public int getCountKhaoThi() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_KHAO_THI;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewKhoaHoc(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_KHOA_HOC_SAU_DAI_HOC, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<RssItem> getAllKhoaHoc() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_KHOA_HOC_SAU_DAI_HOC +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemKhoaHoc(int id_rss) {

    }

    @Override
    public void upDateStatusKhoaHoc(int status, int id_rss) {

    }

    @Override
    public int getCountKhoaHoc() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_KHOA_HOC_SAU_DAI_HOC;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;

    }

    @Override
    public void addNewDangUy(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_DANG_UY, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<RssItem> getAllDangUy() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_DANG_UY +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemDangUy(int id_rss) {

    }

    @Override
    public void upDateStatusDangUy(int status, int id_rss) {

    }

    @Override
    public int getCountDangUy() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_DANG_UY;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewDoanThanhNien(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_DOAN_THANH_NIEN, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<RssItem> getAllDoanThanhNien() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_TIN_SINH_VIEN +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;

    }

    @Override
    public void deleteItemDoanThanhNien(int id_rss) {

    }

    @Override
    public void upDateStatusDoanThanhNien(int status, int id_rss) {

    }

    @Override
    public int getCountDoanThanhNien() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_DOAN_THANH_NIEN;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewTinSinhVien(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_TIN_SINH_VIEN, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllTinSinhVien() {

        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_TIN_SINH_VIEN +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setDate2(cursor.getString(3));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    item.setImageUrl(cursor.getString(4));
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemTinSinhVien(int id_rss) {

    }

    @Override
    public void upDateStatusTinSinhVien(int status, int id_rss) {

    }

    @Override
    public int getCountTinSinhVien() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_TIN_SINH_VIEN;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewThongBao(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, format.format(rssItem.getPubDate()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_THONG_BAO, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllThongBao() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_THONG_BAO +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setPubDate(format.parse(cursor.getString(3)));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemThongBao(int id_rss) {

    }

    @Override
    public void upDateStatusThongBao(int status, int id_rss) {

    }

    @Override
    public int getCountThongBao() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_THONG_BAO;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewDoanThanhNienIt(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, format.format(rssItem.getPubDate()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_DOAN_THANH_NIEN_IT, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllDoanThanhNienIt() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_DOAN_THANH_NIEN_IT +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setPubDate(format.parse(cursor.getString(3)));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;

    }

    @Override
    public void deleteItemDoanThanhNienIt(int id_rss) {

    }

    @Override
    public void upDateStatusDoanThanhNienIt(int status, int id_rss) {

    }

    @Override
    public int getCountDoanThanhNienIt() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_DOAN_THANH_NIEN_IT;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewCoHoiNgheNghiep(RssItem rssItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, rssItem.getTitle());
            contentValues.put(KEY_LINK, rssItem.getLink());
            contentValues.put(KEY_PUBDATE, (rssItem.getDate2()));
            contentValues.put(KEY_IMAGE, (rssItem.getImageUrl()));
            sqLiteDatabase.insert(TABLE_CO_HOI_NGHE_NGHIEP, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<RssItem> getAllCoHoiNgheNghiep() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<RssItem> contentRssArrayList = new ArrayList<RssItem>();
        try {

            String QUERY = " select * from " + TABLE_CO_HOI_NGHE_NGHIEP +" order by " + KEY_PUBDATE + " desc ";
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    RssItem item = new RssItem();
                    item.setId_rssItem(cursor.getInt(0));
                    item.setTitle(cursor.getString(1));

                    item.setLink(cursor.getString(2));
                    try {
                        item.setPubDate(format.parse(cursor.getString(3)));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    contentRssArrayList.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;


    }

    @Override
    public void deleteItemCoHoiNgheNghiep(int id_rss) {

    }

    @Override
    public void upDateStatusCoHoiNgheNghiep(int status, int id_rss) {

    }



    @Override
    public int getCountCoHoiNgheNghiep() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int num = 0;
        try {
            String QUERY = "SELECT * FROM " + TABLE_CO_HOI_NGHE_NGHIEP;
            Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
            num = cursor.getCount();
            sqLiteDatabase.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }

    @Override
    public void addNewItemContent(ContentRss contentRss) {
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, contentRss.getTitle());
            contentValues.put(KEY_PUBDATE, contentRss.getPubDate());
            contentValues.put(KEY_CONTENT_HTML, contentRss.getContent());
            database.insert(TABLE_OFFLINE, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("problem", e + "");
        }
    }

    @Override
    public ArrayList<ContentRss> getAllItemsContent() {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        ArrayList<ContentRss> contentRssArrayList = new ArrayList<ContentRss>();
        try {
            String QUERY = " SELECT * FROM " + TABLE_OFFLINE;
            Cursor cursor = mDatabase.rawQuery(QUERY, null);
            if (cursor.moveToFirst() && cursor.getCount() >= 1) {
                do {
                    ContentRss task = new ContentRss();
                    task.setId_content(cursor.getInt(0));
                    task.setTitle(cursor.getString(1));

                    task.setContent(cursor.getString(2));
                    task.setPubDate(cursor.getString(3));

                    contentRssArrayList.add(task);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR LOAD TASK" + e + "");
        }
        // mDatabase.close();
        return contentRssArrayList;
    }

    @Override
    public void deleteItemContent(int id_content) {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        try {
            mDatabase.delete(TABLE_OFFLINE, KEY_ID
                    + " =" + id_content, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public RssItemHelper open() throws SQLException {
        //   SQLiteDatabase.CursorFactory ctx;
        //   TaskDbAdapter  taskdb= new TaskDbAdapter(Context ctx);
        SQLiteDatabase data = this.getWritableDatabase();
        return this;
    }

    // dong ket noi voi CSDL
    public void closeDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

}
