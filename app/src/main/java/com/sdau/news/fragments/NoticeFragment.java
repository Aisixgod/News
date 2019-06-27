package com.sdau.news.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sdau.news.FeedbackUsActivity;
import com.sdau.news.NewsActivity;
import com.sdau.news.R;
import com.sdau.news.utils.CacheUtil;
import com.sdau.news.utils.SQLiteNewsImpl;

import java.io.File;

public class NoticeFragment extends Fragment {


        public NoticeFragment(){

        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

       TextView delete_cache=view.findViewById(R.id.delete_cache);
       TextView feedback_us=view.findViewById(R.id.feedback_us);

        //反馈窗口
       TextView about_us=view.findViewById(R.id.about_us);
        TextView cache_file=view.findViewById(R.id.cache_file);
        TextView delete_history=view.findViewById(R.id.delete_history);

        try {
            cache_file.setText(CacheUtil.getTotalCacheSize(getActivity().getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        delete_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CacheUtil.clearAllCache(getActivity().getApplicationContext());
                Toast.makeText(getActivity(), "缓存已清理！", Toast.LENGTH_SHORT).show();

                }


        });

        delete_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SQLiteNewsImpl.newInstance(getActivity()).queryHistoryAll()==null){
                    Toast.makeText(getActivity(), "浏览记录已为空！", Toast.LENGTH_SHORT).show();

                }
                if(SQLiteNewsImpl.newInstance(getActivity()).deleteALLHistory()){
                    Toast.makeText(getActivity(), "浏览记录已清理！", Toast.LENGTH_SHORT).show();
                }


            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });

        feedback_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), FeedbackUsActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }


    private void showNormalDialog(){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setIcon(R.drawable.ic_us);
        normalDialog.setTitle("10组制作");
        normalDialog.setMessage("                                                                                 观察者demo");

        normalDialog.setPositiveButton("谢谢！",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        // 显示
        normalDialog.show();
    }

}
