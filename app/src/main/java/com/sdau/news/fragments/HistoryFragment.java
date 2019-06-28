package com.sdau.news.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.R;
import com.sdau.news.adapters.CollectAdapter;
import com.sdau.news.adapters.HistoryAdapter;
import com.sdau.news.beans.News;

import java.util.List;

public class HistoryFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    //判断收藏夹是否为空
    private boolean isEmpty=false;

    private List<News> history;

    public HistoryFragment(Context context,List<News> history){
        historyAdapter=new HistoryAdapter(context,history);
        if (history==null){isEmpty=true;}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (isEmpty==false) {
            view = inflater.inflate(R.layout.fragment_history, container, false);
            recyclerView =  view.findViewById(R.id.history_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(historyAdapter);
        }
        else {
            view = inflater.inflate(R.layout.fragment_collect_empty, container, false);
            TextView emptyText=view.findViewById(R.id.empty_text);
            emptyText.setText("浏览历史为空");
        }

        return view;
    }
}
