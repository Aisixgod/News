package com.sdau.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.RequestBuilder;
import com.sdau.news.NewsContextActivity;
import com.sdau.news.R;
import com.sdau.news.beans.Collect;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private Context mContext;

    private SQLiteNewsImpl collectSQL;

    private List<News> mNews;

    private CollectAdapter.ViewHolder holder;




    public CollectAdapter(Context context){
        mContext=context;
        collectSQL=SQLiteNewsImpl.newInstance(context);
        collectSQL.createTable();
        mNews=collectSQL.queryCollectAll();
        Log.d("TAG", "CollectAdapter: 收藏数据为："+mNews.get(0).getTitle());

    }

    public void setNews(List<News> news){
        mNews=news;
    }

    public List<News> getNews(){
        return mNews;
    }




    public  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ImageView newsPic;
        public TextView newsTitle;
        public TextView newsDate;
        public TextView mewsFeedbackNumber;
        public Button delete_collect;


        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
            cardView = (CardView) view;
            newsTitle = view.findViewById(R.id.collect_newsTitle);
            newsPic = view.findViewById(R.id.collect_newsImage);
            newsDate = view.findViewById(R.id.collect_newsTime);
            mewsFeedbackNumber = view.findViewById(R.id.collect_newsFeedbackNumber);
            delete_collect = view.findViewById(R.id.delete_collect);


        }
    }

    @NonNull
    @Override
    public CollectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.collect_item,parent,false);

         holder =new CollectAdapter.ViewHolder(view);

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
        holder.delete_collect.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * 从上往下删存在position的问题
             */
            @Override
            public void onClick(View view) {
                if (mNews.isEmpty()) {
                    notifyDataSetChanged();
                } else {
                    int position = holder.getAdapterPosition();
                    if(position>=mNews.size()){
                        position=mNews.size()-1;
                    }
                    if(position<=0){
                        position=0;
                    }
                    Log.d("TAG", "onClick: Position为：" + position);
                    News news = mNews.get(position);
                    Log.d("TAG", "onClick:删除的标题为： " + news.getTitle());

                    collectSQL.createTable();
                    if (collectSQL.deleteCollect(news)) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();
                    }
                    mNews.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();

                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectAdapter.ViewHolder holder, int position) {
        News news = mNews.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDate.setText(news.getDate());
        holder.mewsFeedbackNumber.setText(String.valueOf(SQLiteNewsImpl.newInstance(mContext).queryFeedbackByNews(news)));

        Log.d("TAG", "onBindViewHolder: 新闻图片内容为："+news.getThumbnail_pic_s());
         Glide.with(mContext).load(news.getThumbnail_pic_s()).into(holder.newsPic);


    }



    @Override
    public int getItemCount() {
        return mNews.size();
    }
}
