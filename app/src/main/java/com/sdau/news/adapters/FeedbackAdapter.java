package com.sdau.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.NewsContextActivity;
import com.sdau.news.R;
import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private Context mContext;

    private List<Feedback> feedbacks;
    private FeedbackAdapter.ViewHolder holder;

    public  FeedbackAdapter(Context context){
        mContext=context;
        this.feedbacks= SQLiteNewsImpl.newInstance(context).queryFeedbackAll();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{


        CardView cardView;
        public TextView feedback_news_title;
        public TextView feedbackContext;
        public TextView support;
        public TextView stamp;




        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
            cardView = (CardView) view;
            feedback_news_title = view.findViewById(R.id.feedback_news_title);
            feedbackContext = view.findViewById(R.id.feedbackContent);
            support = view.findViewById(R.id.feedbackSupport);
            stamp = view.findViewById(R.id.feedbackStamp);


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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();

                News news=feedbacks.get(position).getFeedbackNews();
                Intent intent = new Intent(mContext, NewsContextActivity.class);
                intent.putExtra("news_data",news);
                mContext.startActivity(intent);

            };
        });

        holder.support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Feedback feedback=feedbacks.get(position);
                SQLiteNewsImpl.newInstance(mContext).addFeedbackSupport(feedback);

            }
        });
        holder.stamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Feedback feedback=feedbacks.get(position);
                SQLiteNewsImpl.newInstance(mContext).addFeedbackStamp(feedback);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.ViewHolder holder, int position) {
            Feedback feedback=feedbacks.get(position);
            holder.stamp.setText(feedback.getStamp());
            holder.support.setText(feedback.getSupport());
            holder.feedbackContext.setText(feedback.getSubstance());
            holder.feedback_news_title.setText(feedback.getFeedbackNews().getTitle());
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }
}