package com.sdau.news.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sdau.news.R;
import com.sdau.news.adapters.CollectAdapter;
import com.sdau.news.adapters.NewsAdapter;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.List;

public class CollectFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private List<News> mNews;
    //判断收藏夹是否为空
    private boolean isEmpty=false;

    public CollectFragment(){
        mNews=SQLiteNewsImpl.newInstance(getActivity()).queryCollectAll();
    }
    public CollectFragment(boolean isEmpty){
        this.isEmpty=isEmpty;
        mNews=SQLiteNewsImpl.newInstance(getActivity()).queryCollectAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (isEmpty==false) {
            view = inflater.inflate(R.layout.fragment_collect, container, false);
            recyclerView =  view.findViewById(R.id.collect_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

            recyclerView.setAdapter(new CollectAdapter(getActivity()));

        }
        else {
            view = inflater.inflate(R.layout.fragment_collect_empty, container, false);
            TextView emptyText=view.findViewById(R.id.empty_text);
            emptyText.setText("收藏夹为空");
        }

        return view;
    }
}
