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
import com.sdau.news.adapters.FeedbackAdapter;
import com.sdau.news.beans.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private boolean isEmpty=false;
    /**
     * 若要实现，暂时编造一个feedback集合
     */
   // public List<Feedback> feedbacks;

    public FeedbackFragment(){}
    public FeedbackFragment(boolean empty){
        isEmpty=empty;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (isEmpty==false) {
            view = inflater.inflate(R.layout.fragment_myfeedback, container, false);
            recyclerView =  view.findViewById(R.id.feedback_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new FeedbackAdapter(getActivity()));
        }
        else {
            view = inflater.inflate(R.layout.fragment_collect_empty, container, false);
            TextView emptyText=view.findViewById(R.id.empty_text);
            emptyText.setText("目前无评论");
        }

        return view;
    }
}
