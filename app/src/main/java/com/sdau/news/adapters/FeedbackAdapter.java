package com.sdau.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.NewsContentActivity;
import com.sdau.news.R;
import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private Context mContext;

    private List<Feedback> feedbacks;
    private FeedbackAdapter.ViewHolder holder;
    private boolean isNewsFeedbackContent=false;

    public  FeedbackAdapter(Context context){
        mContext=context;
        this.feedbacks= SQLiteNewsImpl.newInstance(context).queryFeedbackAll();
    }

    public FeedbackAdapter(Context context,News news){
        mContext=context;
        this.feedbacks=SQLiteNewsImpl.newInstance(context).queryFeedbackByNews(news);
        isNewsFeedbackContent=true;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{


        CardView cardView;
        public TextView feedback_news_title;
        public TextView feedbackContext;
        public TextView support;
        public TextView stamp;
        public Button delete_feedback;




        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
            cardView = (CardView) view;
            feedback_news_title = view.findViewById(R.id.feedback_news_title);
            feedbackContext = view.findViewById(R.id.feedbackContent);
            support = view.findViewById(R.id.feedbackSupport);
            stamp = view.findViewById(R.id.feedbackStamp);
            delete_feedback=view.findViewById(R.id.delete_feedback);

        }
    }

    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.feedback_item,parent,false);



        holder =new FeedbackAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.ViewHolder holder, int position) {
            Feedback feedback=feedbacks.get(position);
            holder.stamp.setText(String.valueOf(feedback.getStamp()));
            holder.support.setText(String.valueOf(feedback.getSupport()));
            holder.feedbackContext.setText(feedback.getSubstance());
        Log.d("TAG", "onBindViewHolder: isNewsFeedbackContent"+isNewsFeedbackContent);
            if(!isNewsFeedbackContent) {
                holder.feedback_news_title.setText(feedback.getFeedbackNews().getTitle());
            }
        if(!isNewsFeedbackContent) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    News news = feedbacks.get(position).getFeedbackNews();

                    Intent intent = new Intent(mContext, NewsContentActivity.class);
                    intent.putExtra("news_data", news);
                    mContext.startActivity(intent);

                }


            });
        }
        holder.delete_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedbacks.isEmpty()) {
                    notifyDataSetChanged();
                } else {
                    int position = holder.getAdapterPosition();
                    if (position >= feedbacks.size()) {
                        position = feedbacks.size() - 1;
                    }
                    if (position <= 0) {
                        position = 0;
                    }


                    Feedback feedback = feedbacks.get(position);
                    if (SQLiteNewsImpl.newInstance(mContext).deleteFeedback(feedback)) {
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();
                        feedbacks.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();

                    }

                }
            }
        });
        holder.support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position =holder.getAdapterPosition();
                Feedback feedback=feedbacks.get(position);
                if(SQLiteNewsImpl.newInstance(mContext).addFeedbackSupport(feedback)){
                    Toast.makeText(mContext, "已点赞", Toast.LENGTH_LONG).show();

                    feedbacks.get(position).setSupport(feedback.getSupport());
                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }

            }
        });
        holder.stamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Feedback feedback=feedbacks.get(position);

                if(SQLiteNewsImpl.newInstance(mContext).addFeedbackStamp(feedback)){
                    Toast.makeText(mContext, "已点踩", Toast.LENGTH_LONG).show();
                    feedbacks.get(position).setStamp(feedback.getStamp());
                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }
}
