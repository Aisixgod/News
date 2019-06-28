package com.sdau.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sdau.news.R;
import com.sdau.news.adapters.FeedbackAdapter;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

import static com.sdau.news.R2.id.container;

public class FeedbackContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_content);
        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();
        News data = (News) bundle.get("news_feedback_data");
        RecyclerView recyclerView= findViewById(R.id.feedbackContent_recyclerView);

        if (SQLiteNewsImpl.newInstance(FeedbackContentActivity.this).queryFeedbackByNews(data)!=null) {


            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new FeedbackAdapter(this,data));
        }
        else {
           setContentView(R.layout.activity_empty);
            TextView emptyText=findViewById(R.id.activity_empty_text);
            emptyText.setText("目前无评论");
        }
    }
}
