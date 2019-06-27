package com.sdau.news.utils;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;

import java.util.ArrayList;
import java.util.List;

public class SQLiteNewsImpl implements ISQLiteOperation {
    private static final String TAG = "SQLiteImpl";
    private static Integer history_date=0;
    private MyHelper helper;
    private static ISQLiteOperation favouriteOp;

    public static SQLiteNewsImpl newInstance(Context context) {
        Log.d(TAG,"newInstance");
        favouriteOp = favouriteOp == null ? new SQLiteNewsImpl(context) : favouriteOp;
        return (SQLiteNewsImpl) favouriteOp;
    }

    private SQLiteNewsImpl(Context context) {
        helper = new MyHelper(context);

    }

    @Override
    public boolean addCollect(News data) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uniquekey", data.getUniquekey());
        values.put("title", data.getTitle());
        values.put("date", data.getDate());
        values.put("category", data.getCategory());
        values.put("author_name", data.getAuthor_name());
        values.put("url", data.getUrl());
        values.put("thumbnail_pic_s", data.getThumbnail_pic_s());

        long result = db.insert(ISQLiteOperation.TABLE_NAME_COLLECT, null, values);
        db.close();
        if (result != -1) {
            Log.d(TAG,"added Favourite");
        }
        return result != -1;
    }

    @Override
    public boolean deleteCollect(News data) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = db.delete(ISQLiteOperation.TABLE_NAME_COLLECT, "uniquekey=?",
                new String[]{data.getUniquekey()});
        db.close();
        Log.d(TAG,"deleted Favourite");
        return result >= 1 ;
    }

    @Override
    public void createTable() {
        helper.getWritableDatabase().close();
    }

    @Override
    public List<News> queryCollectAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {"uniquekey", "title", "date", "category",
                "author_name", "url", "thumbnail_pic_s"};
        Cursor cursor = db.query(ISQLiteOperation.TABLE_NAME_COLLECT, columns, null, null, null, null, "uniquekey");
        if (cursor != null && cursor.getCount() > 0) {
            List<News> listFavorites = new ArrayList<>();
            while (cursor.moveToNext()) {
                String uniquekey = cursor.getString(cursor
                        .getColumnIndex("uniquekey"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String category = cursor.getString(cursor
                        .getColumnIndex("category"));
                String author_name = cursor.getString(cursor
                        .getColumnIndex("author_name"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String thumbnail_pic_s = cursor.getString(cursor
                        .getColumnIndex("thumbnail_pic_s"));


                listFavorites.add(new News(uniquekey, title, date, category,
                        author_name, url, thumbnail_pic_s));
            }
            cursor.close();
            db.close();
            return listFavorites;
        }
        cursor.close();
        db.close();
        return null;
    }

    @Override
    public News queryCollectNews(News data) {
        News mNews=new News();

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {"uniquekey", "title", "date", "category",
                "author_name", "url", "thumbnail_pic_s"};
        Cursor cursor = db.query(ISQLiteOperation.TABLE_NAME_COLLECT, columns, "uniquekey=?", new String[]{data.getUniquekey()}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                String uniquekey = cursor.getString(cursor
                        .getColumnIndex("uniquekey"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String category = cursor.getString(cursor
                        .getColumnIndex("category"));
                String author_name = cursor.getString(cursor
                        .getColumnIndex("author_name"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String thumbnail_pic_s = cursor.getString(cursor
                        .getColumnIndex("thumbnail_pic_s"));


                mNews= new News(uniquekey, title, date, category,
                        author_name, url, thumbnail_pic_s);
            }
            cursor.close();
            db.close();
            return mNews;
        }
        cursor.close();
        db.close();
        return null;
    }

    @Override
    public boolean deleteALLHistory() {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = db.delete(ISQLiteOperation.TABLE_NAME_HISTORY, null,
               null);
        db.close();
        Log.d(TAG,"deleted AllHistory");
        return result >= 1 ;
    }

    @Override
    public boolean addHistory(News data) {
        history_date+=1;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uniquekey", data.getUniquekey());
        values.put("title", data.getTitle());
        values.put("date", data.getDate());
        values.put("category", data.getCategory());
        values.put("author_name", data.getAuthor_name());
        values.put("url", data.getUrl());
        values.put("thumbnail_pic_s", data.getThumbnail_pic_s());
        values.put("history_date",history_date);
        long result = db.insert(ISQLiteOperation.TABLE_NAME_HISTORY, null, values);
        db.close();
        if (result != -1) {
            Log.d(TAG,"added History");
        }
        return result != -1;

    }

    @Override
    public List<News> queryHistoryAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {"uniquekey", "title", "date", "category",
                "author_name", "url", "thumbnail_pic_s", "history_date"};
        Cursor cursor = db.query(ISQLiteOperation.TABLE_NAME_HISTORY, columns, null, null, null, null, "history__date");
        if (cursor != null && cursor.getCount() > 0) {
            List<News> newsHistory = new ArrayList<>();
            while (cursor.moveToNext()) {
                String uniquekey = cursor.getString(cursor
                        .getColumnIndex("uniquekey"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String category = cursor.getString(cursor
                        .getColumnIndex("category"));
                String author_name = cursor.getString(cursor
                        .getColumnIndex("author_name"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String thumbnail_pic_s = cursor.getString(cursor
                        .getColumnIndex("thumbnail_pic_s"));


                newsHistory.add(new News(uniquekey, title, date, category,
                        author_name, url, thumbnail_pic_s));
            }
            cursor.close();
            db.close();
            return newsHistory;

                }
        cursor.close();
        db.close();
        return null;
    }

    @Override
    public boolean addFeedback( Feedback feedback) {
        News data=feedback.getFeedbackNews();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uniquekey", data.getUniquekey());
        values.put("title", data.getTitle());
        values.put("date", data.getDate());
        values.put("category", data.getCategory());
        values.put("author_name", data.getAuthor_name());
        values.put("url", data.getUrl());
        values.put("thumbnail_pic_s", data.getThumbnail_pic_s());
        values.put("substance",feedback.getSubstance());
        values.put("support",feedback.getSupport());
        values.put("stamp",feedback.getStamp());
        long result = db.insert(ISQLiteOperation.TABLE_NAME_FEEDBACK, null, values);
        db.close();
        if (result != -1) {
            Log.d(TAG,"added Feedback");
        }
        return result != -1;
    }

    @Override
    public boolean deleteFeedback(Feedback feedback) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = db.delete(ISQLiteOperation.TABLE_NAME_FEEDBACK, "substance=?",
                new String[]{feedback.getSubstance()});
        db.close();
        Log.d(TAG,"deleted Feedback");
        return result >= 1 ;
    }

    @Override
    public boolean addFeedbackSupport(Feedback feedback) {
        News data=feedback.getFeedbackNews();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("support",feedback.getSupport()+1);
       long result = db.update(ISQLiteOperation.TABLE_NAME_FEEDBACK, values, "substance=?",new String[] { feedback.getSubstance() });
        db.close();
        if (result != -1) {
            Log.d(TAG,"added Feedback");
        }
        return result != -1;
    }

    @Override
    public boolean addFeedbackStamp(Feedback feedback) {
        News data=feedback.getFeedbackNews();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stamp",feedback.getStamp()+1);
        long result = db.update(ISQLiteOperation.TABLE_NAME_FEEDBACK, values, "substance=?",new String[] { feedback.getSubstance() });
        db.close();
        if (result != -1) {
            Log.d(TAG,"added Feedback");
        }
        return result != -1;
    }

    @Override
    public List<Feedback> queryFeedbackAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {"uniquekey", "title", "date", "category",
                "author_name", "url", "thumbnail_pic_s", "substance","support","stamp"};
        Cursor cursor = db.query(ISQLiteOperation.TABLE_NAME_FEEDBACK, columns, null, null, null, null, "history__date");
        if (cursor != null && cursor.getCount() > 0) {
            List<Feedback> allFeedback = new ArrayList<Feedback>();
            while (cursor.moveToNext()) {
                String uniquekey = cursor.getString(cursor
                        .getColumnIndex("uniquekey"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String category = cursor.getString(cursor
                        .getColumnIndex("category"));
                String author_name = cursor.getString(cursor
                        .getColumnIndex("author_name"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String thumbnail_pic_s = cursor.getString(cursor
                        .getColumnIndex("thumbnail_pic_s"));
                String substance=cursor.getString(cursor.getColumnIndex("substance"));
                int support=cursor.getInt(cursor.getColumnIndex("support"));
                int stamp=cursor.getInt(cursor.getColumnIndex("stamp"));
                Feedback f=new Feedback();
                f.setFeedbackNews(new News(uniquekey, title, date, category,
                        author_name, url, thumbnail_pic_s));
                f.setSubstance(substance);
                f.setSupport(support);
                f.setStamp(stamp);
                allFeedback.add(f);
            }
            cursor.close();
            db.close();
            return allFeedback;

        }
        cursor.close();
        db.close();
        return null;
    }


}
