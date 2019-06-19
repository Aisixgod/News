package com.sdau.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity  {


    private ArrayList<String> mT;
    private RecyclerView mRecycleView;
    private Recycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        initData();
        initAdapter();
        initView();
    }

    public void initView(){
        setContentView(R.layout.recycle_view);
        mListView=findViewById(R.id.ListView);
        mListView.setAdapter(mAdapter);
    }
    public void initAdapter(){
        mAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mT);

    }

    public void initData(){
        mT=new ArrayList<String>();
        mT.add("拿到巨大折扣，终于有人愿买200架737MAX");
        mT.add("已经有了“沪港通”，为何还要“沪伦通” ");
        mT.add("图解：习近平总书记首访朝鲜，意义重大！");
        mT.add("“新一代”战斗机该叫“五代”还是“六代”");
        mT.add("包庇纵容黑老大文烈宏，湖南综治办原主任周符波被判19年");
        mT.add("中国能给美国军火库“断供”吗？ ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: 4");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume:4 ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart:4 ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy:4 ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ");
    }

    /*public void onClick(View view){
        Intent intent=null;
        switch (view.getId()) {
            case R.id.button2:
                intent = new Intent(this,MainActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(this,SecondActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this,ThirdActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this,FourthActivity.class);
                break;
            case R.id.button: intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));

        }
        startActivity(intent);
    }*/
}
