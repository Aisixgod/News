package com.sdau.news.fragments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.R;
import com.sdau.news.adapters.AttentionAdapter;

import java.util.ArrayList;

public class AttentionFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    public ArrayList<String> name=new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            initName();
            view = inflater.inflate(R.layout.fragment_fans_attention, container, false);
            recyclerView =  view.findViewById(R.id.fans_attention_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new AttentionAdapter(getActivity(),name));


        }

        return view;
    }

    /**
     * 编造的用户名
     */
    public void initName(){

        name.add("freeman");
        name.add("刃而寸心");
        name.add("不如安静吧");
        name.add("未忘散花");
        name.add("天街小雨11");
        name.add("cinet");
        name.add("堂吉诃德");
        name.add("matchboy");
        name.add("北冥之鱼");
        name.add("为梦而战");

    }
}
