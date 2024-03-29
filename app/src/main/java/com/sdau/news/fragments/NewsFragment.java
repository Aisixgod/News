package com.sdau.news.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdau.news.MyApplication;
import com.sdau.news.R;
import com.sdau.news.adapters.NewsAdapter;
import com.sdau.news.beans.News;
import com.sdau.news.beans.Result;
import com.sdau.news.utils.HttpUtil;
import com.sdau.news.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class NewsFragment extends Fragment  {


    private static final String TYPE = "news_type";
    private String newsType;
    private RecyclerView recyclerView;
    private FloatingActionButton mFloatBtn;
    private SwipeRefreshLayout swipeRefresh;
    private NewsAdapter newsAdapter;
    //当前视图
    private View view;
    private  Result result;

    public NewsFragment() {
    }
    public NewsFragment(String type,Result result){
        newsType=type;
        newsAdapter = NewsAdapter.getNewsAdapter(getActivity(), result.getNews(), newsType);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView"+"执行了创建newsFragment,type为："+newsType);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_newslist, container, false);
            recyclerView = view.findViewById(R.id.news_recyclerView);
            mFloatBtn=view.findViewById(R.id.news_floatBotton);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(newsAdapter);
            swipeRefresh=view.findViewById(R.id.swipe_refresh_news);
            Log.d("TAG", "run: 执行下拉刷新任务前一步");
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                        new Thread(){
                            @Override
                            public void run() {
                                Log.d("TAG", "run: 我在执行下拉刷新任务");
                                String json = null;
                                try {
                                    json = HttpUtil.getALiYun(newsType);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                result = JsonUtil.parseJSON(json);
                                Log.d("TAG", "刷新的："+result.getNews().get(0).getTitle());

                           getActivity().runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   newsAdapter.refreshNewsAdapter(result.getNews());
                                   swipeRefresh.setRefreshing(false);
                                   newsAdapter.notifyDataSetChanged();
                               }
                           });
                            }
                        }.start();


                        }
            });



            mFloatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerView.scrollToPosition(0);
                }
            });
        }

        return view;
    }



@Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }


}
