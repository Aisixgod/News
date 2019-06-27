package com.sdau.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.sdau.news.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdau.news.beans.Feedback;
import com.sdau.news.beans.News;
import com.sdau.news.utils.SQLiteNewsImpl;

public class NewsContextActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;


    private  News data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_context);

        WebView webView=findViewById(R.id.web_news);

        FloatingActionButton collectButton=findViewById(R.id.collect_button);
        ImageView imageView=findViewById(R.id.news_image_view);
        Toolbar toolbar=findViewById(R.id.news_content_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);



        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();
        data = (News) bundle.get("news_data");
        Glide.with(this).load(data.getThumbnail_pic_s()).into(imageView);


        toolbar.inflateMenu(R.menu.menu_newscontent_toolbar_item);
        toolbar.setTitle(data.getTitle());
        Button feedbackEditTextButton=findViewById(R.id.feedbackEditTextButton);
        EditText feedbackEditText=findViewById(R.id.feedback_editText);
        feedbackEditTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String substance=feedbackEditText.getText().toString();
                    Feedback feedback=new Feedback();
                    feedback.setSubstance(substance);
                    feedback.setFeedbackNews(data);
                    feedbackEditText.getText().clear();
                    if(SQLiteNewsImpl.newInstance(NewsContextActivity.this).addFeedback(feedback)) {
                        Toast.makeText(NewsContextActivity.this, "发表成功！", Toast.LENGTH_SHORT).show();
                    }

            }
        });


        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteNewsImpl sqLiteNews=SQLiteNewsImpl.newInstance(NewsContextActivity.this);
                if(sqLiteNews.queryCollectNews(data)==null) {
                    sqLiteNews.addCollect(data);

                    Toast.makeText(NewsContextActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                }
                else {
                    sqLiteNews.deleteCollect(data);
                    Toast.makeText(NewsContextActivity.this, "取消收藏！", Toast.LENGTH_SHORT).show();

                }
            }
        });





        initDialog();

        //开启对Js的支持
        webView.getSettings().getJavaScriptEnabled();

        //增加对用户的加载提示和防止外部浏览器的跳转
        webView.setWebViewClient(new WebViewClient(){
            //开始加载一个网页的回调
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(!progressDialog.isShowing())
                    progressDialog.show();
            }

            //结束加载一个网页的回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
        webView.loadUrl(data.getUrl());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("网页加载中...");
    }


}
