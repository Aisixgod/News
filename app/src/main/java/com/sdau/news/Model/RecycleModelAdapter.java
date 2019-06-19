package com.sdau.news.Model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.R;

import java.util.ArrayList;


public class RecycleModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class VH<List> extends RecyclerView.ViewHolder {

        public final TextView title;
        public VH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            this.mDatas = mDatas;
        }
        private ArrayList<String> mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
