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

import com.sdau.news.R;
import com.sdau.news.adapters.CollectAdapter;
import com.sdau.news.adapters.HistoryAdapter;

public class HistoryFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    //判断收藏夹是否为空
    private boolean isEmpty=false;

    public HistoryFragment(){}
    public HistoryFragment(boolean isEmpty){
        this.isEmpty=isEmpty;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (isEmpty==false) {
            view = inflater.inflate(R.layout.fragment_history, container, false);
            recyclerView =  view.findViewById(R.id.history_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new HistoryAdapter(getActivity()));
        }
        else {
            view = inflater.inflate(R.layout.fragment_collect_empty, container, false);
            TextView emptyText=view.findViewById(R.id.empty_text);
            emptyText.setText("浏览历史为空");
        }

        return view;
    }
}
