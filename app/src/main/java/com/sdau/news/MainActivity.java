package com.sdau.news;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
        ImageView imageView=findViewById(R.id.welcome);
        setAnimate(imageView);

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

    public void setAnimate(ImageView imageView){
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(imageView, "ScaleX", 1.0f,1.4f);
        ObjectAnimator animatory = ObjectAnimator.ofFloat(imageView, "ScaleY", 1.0f,1.4f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(4000).play(animatorx).with(animatory);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationStart(animation);

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                super.onAnimationEnd(animation);
            }
        });
    }



}



