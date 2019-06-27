package com.sdau.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sdau.news.NewsContextActivity;
import com.sdau.news.R;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private Context mContext;

    private SQLiteNewsImpl historySQL;

    private List<News> mNews;

    private HistoryAdapter.ViewHolder holder;



    public HistoryAdapter(Context context){
        mContext=context;
        historySQL=SQLiteNewsImpl.newInstance(context);

        mNews=historySQL.queryHistoryAll();

    }





    public  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ImageView newsPic;
        public TextView newsTitle;
        public TextView newsDate;
        public TextView mewsFeedbackNumber;
        public Button delete_history;


        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
            cardView = (CardView) view;
            newsTitle = view.findViewById(R.id.history_newsTitle);
            newsPic = view.findViewById(R.id.history_newsImage);
            newsDate = view.findViewById(R.id.history_newsTime);
            mewsFeedbackNumber = view.findViewById(R.id.history_newsFeedbackNumber);
            delete_history = view.findViewById(R.id.delete_history);


        }
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_item,parent,false);

        holder =new HistoryAdapter.ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                if(position<0) return;
                News news=mNews.get(position);
                Intent intent = new Intent(mContext, NewsContextActivity.class);
                intent.putExtra("news_data",news);
                mContext.startActivity(intent);

            };
        });
        holder.delete_history.setOnClickListener(new View.OnClickListener() {
            // 从上往下删存在position的问题

            @Override
            public void onClick(View view) {
                if (mNews.isEmpty()) {
                    notifyDataSetChanged();
                } else {
                    int position = holder.getAdapterPosition();
                    if (position >= mNews.size()) {
                        position = mNews.size() - 1;
                    }
                    if (position <= 0) {
                        position = 0;
                    }

                    News news = mNews.get(position);
                    Log.d("TAG", "onClick:删除的标题为： " + news.getTitle()+position);


                    if (historySQL.deleteHistory(news)) {

                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();
                        mNews.remove(position);
                        notifyItemRemoved(position);

                        notifyItemRangeChanged(position, mNews.size() - position);

                    }
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        News news = mNews.get(position);
        Log.d("TAG", "onBindViewHolder: 标题为："+news.getTitle());

        holder.newsTitle.setText(news.getTitle());
         holder.newsDate.setText(news.getDate());
        holder.mewsFeedbackNumber.setText(String.valueOf(SQLiteNewsImpl.newInstance(mContext).queryFeedbackByNews(news)));

        Glide.with(mContext).load(news.getThumbnail_pic_s()).into(holder.newsPic);


    }



    @Override
    public int getItemCount() {
        return mNews.size();
    }
}
