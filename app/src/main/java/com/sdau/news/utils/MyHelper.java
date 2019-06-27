package com.sdau.news.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyHelper";

    private Context mContext;

    // 上下文
    // 数据库名称
    // 数据库版本1——>2——>3.....
    public MyHelper(Context context) {
        super(context, ISQLiteOperation.DB_NAME, null, 1);
        Log.d(TAG,"newInstance");
        mContext=context;

    }

    // 数据库创建回调方法
    // db 新创建的数据库对象
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 执行SQL语句
        Log.d(TAG,"开始创建数据表");

        /**
         * 新闻收藏表 collect static int date(时间排序
         */
        db.execSQL("create table if not exists "+ISQLiteOperation.TABLE_NAME_COLLECT+"(_id integer primary key autoincrement,uniquekey text,title text,date text,category text,author_name text,url text,thumbnail_pic_s text)");

        /**
         * 浏览记录表history   static int history_date（时间排序）
         */
        db.execSQL("create table if not exists "+ISQLiteOperation.TABLE_NAME_HISTORY+"(_id integer primary key autoincrement,uniquekey text,title text,date text,category text,author_name text,url text,thumbnail_pic_s text,histroy_date INTEGER)");

        /**
         * 新闻回复表feedback substance、support、stamp
         */
        db.execSQL("create table if not exists "+ISQLiteOperation.TABLE_NAME_FEEDBACK+"(_id integer primary key autoincrement,uniquekey text,title text,date text,category text,author_name text,url text,thumbnail_pic_s text,substance text,support INTEGER,stamp INTEGER)");

        Log.d(TAG,"数据表创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
