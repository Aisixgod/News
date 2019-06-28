package com.sdau.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sdau.news.NewsContentActivity;
import com.sdau.news.R;
import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {



    private Context mContext;

    private List<News> mNews;
    private  String type;

    public NewsAdapter(Context context,List<News> news,String type){
        mContext=context;
        mNews=news;
        this.type=type;
    }

    public void refreshNewsAdapter(ArrayList<News> data){
        mNews=data;
    }
    /**
     * 获取新闻适配器
     *
     * @param context  上下文对象
     * @param dataList 新闻数据列表
     * @param type     新闻数据类型
     * @return 新闻适配器
     */
    public static NewsAdapter getNewsAdapter(Context context, List<News> dataList, String type) {
       Log.d("TAG", "new NewsAdapter 放入类型 " + type);
        NewsAdapter newsAdapter = new NewsAdapter(context, dataList, type);
       // newsAdapter.put(type, newsAdapter);
        return newsAdapter;
    }



    public  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ImageView newsPic;
        public TextView newsTitle;
        public TextView newsDate;
        public TextView mewsFeedbackNumber;

        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
           cardView = (CardView) view;
           newsTitle = view.findViewById(R.id.newsTitle);
           newsPic = view.findViewById(R.id.newsImage);
            newsDate = view.findViewById(R.id.newsTime);
            mewsFeedbackNumber = view.findViewById(R.id.newsFeedbackNumber);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);

        final  ViewHolder holder =new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                if(position<0) return;
                News news=mNews.get(position);
                Intent intent = new Intent(mContext, NewsContentActivity.class);
                intent.putExtra("news_data",news);
                 mContext.startActivity(intent);
                 if(SQLiteNewsImpl.newInstance(mContext).queryHistoryByNews(news)==null) {
                     SQLiteNewsImpl.newInstance(mContext).addHistory(news);
                 }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNews.get(position);
            holder.newsTitle.setText(news.getTitle());
            holder.newsDate.setText(news.getDate());
            List<Feedback> feedbackNumber= SQLiteNewsImpl.newInstance(mContext).queryFeedbackByNews(news);
            if(feedbackNumber==null){
                holder.mewsFeedbackNumber.setText("0");
            }else {
                holder.mewsFeedbackNumber.setText(String.valueOf(feedbackNumber.size()));
            }
        Glide.with(mContext).load(news.getThumbnail_pic_s()).into(holder.newsPic);


    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
}
