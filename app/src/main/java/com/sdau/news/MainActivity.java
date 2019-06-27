package com.sdau.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sdau.news.beans.Result;
import com.sdau.news.fragments.NewsContainerFragment;
import com.sdau.news.utils.HttpUtil;
import com.sdau.news.utils.JsonUtil;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class MainActivity extends AppCompatActivity {

    private MyApplication myApplication;
    private ArrayList<Result> resultArrayList=new ArrayList<Result>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplication=(MyApplication) getApplication();
        SQLiteNewsImpl.newInstance(this);
        //开启线程
        new Thread(){
            @Override
            public void run() {
                //耗时操作交给子线程
                try {

                    initData();
                   //主线程
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                         myApplication.setResultArrayList(resultArrayList);
                            startActivity(intent);
                            finish();
                       }
                   });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }



    public void initData(){

        for(int i=0;i<myApplication.type.size();i++) {
            Log.d("URL",  "https://toutiao-ali.juheapi.com/toutiao/index?type=" + myApplication.type.get(i));
          //  String url = "https://toutiao-ali.juheapi.com/toutiao/index" + myApplication.type.get(i);

            String json = null;
            try {
                json = HttpUtil.getALiYun(myApplication.type.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Result result = JsonUtil.parseJSON(json);
            Log.d("TAG", "initData: 我的json数据"+result.getNews().get(0).getTitle());
            resultArrayList.add(result);
        }
    }


}



