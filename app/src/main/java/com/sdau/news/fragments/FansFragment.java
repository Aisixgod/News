package com.sdau.news.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.R;
import com.sdau.news.adapters.FansAdapter;

import java.util.ArrayList;

public class FansFragment extends Fragment {

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
            recyclerView.setAdapter(new FansAdapter(getActivity(),name));
        }

        return view;
    }

    /**
     * 编造的用户名
     */
    public void initName(){

         name.add("远方的家园");
         name.add("糖");
         name.add("猫咪");
         name.add("世界不是非黑即白");
        name.add("魔导书士一坑林");
        name.add("硬盘硬 软件软");
        name.add("普洛克皮乌斯");
        name.add("落雁羞花");
        name.add("nullllllllll");
        name.add("秋秋qiuqiu");

    }

}
