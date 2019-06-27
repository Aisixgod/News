package com.sdau.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sdau.news.R;
import com.sdau.news.beans.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.ViewHolder> {
    private Context mContext;
    /**
     * 此处attention内容应该由登录用户的attention属性获得
     * 暂未开发用户登录功能，仅编一些名字传入以作演示
     * 头像均为默认头像
     *  private ArrayList<User> attention;
     */
    private ArrayList<String> attention;


    public  AttentionAdapter(Context context,ArrayList<String> name){
        mContext=context;

            attention=name;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        CircleImageView fansImage;
         TextView fansName;

        //public Button attention;
        //关注按钮，等待开发


        public ViewHolder(@NonNull View view) {//此处 View参数为 recycleView的子项布局，即item的视图
            super(view);
            cardView = (CardView) view;
            //fansImage = view.findViewById(R.id.user_fans_image);
            fansName = view.findViewById(R.id.user_fans_name);


        }
    }

    @NonNull
    @Override
    public AttentionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.fans_attention_item,parent,false);
        AttentionAdapter.ViewHolder holder =new AttentionAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttentionAdapter.ViewHolder holder, int position) {
        String attentions= attention.get(position);
        holder.fansName.setText(attentions);

    }

    @Override
    public int getItemCount() {
        return attention.size();
    }
}
