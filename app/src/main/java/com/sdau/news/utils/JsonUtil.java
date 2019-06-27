package com.sdau.news.utils;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdau.news.beans.News;
import com.sdau.news.beans.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtil {

    /**   json包解析工具
     *    返回被解析类型
     */

    private static ObjectMapper objectMapper;

    public static <T> T readValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Result parseJSON(String response){
        Result result=new Result();
        ArrayList<News> newsArrayList=new ArrayList<News>();
        if(!TextUtils.isEmpty(response)){
            try {
                JSONObject obj= new JSONObject(response);
                JSONObject res=obj.getJSONObject("result");

                JSONArray allNews = res.getJSONArray("data");

                for(int i=0;i<allNews.length();i++){
                    JSONObject newsObject = allNews.getJSONObject(i);
                    News news=new News();
                    news.setAuthor_name(newsObject.getString("author_name"));

                    news.setCategory(newsObject.getString("category"));

                    news.setDate(newsObject.getString("date"));
                    news.setThumbnail_pic_s(newsObject.getString("thumbnail_pic_s"));

                    news.setTitle(newsObject.getString("title"));
                    news.setUniquekey(newsObject.getString("uniquekey"));
                    news.setUrl(newsObject.getString("url"));
                   newsArrayList.add(news);


                }
                result.setNews(newsArrayList);

            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return result;
    }

}
