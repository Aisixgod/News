package com.sdau.news;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackUsActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_us);
        Button feedbackCommitButton=findViewById(R.id.feedbackCommitButton);
       editText=findViewById(R.id.us_editText);
        feedbackCommitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showNormalDialog();
            }
        });
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher_guanchazhe);

        normalDialog.setMessage("感谢您的反馈！");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                            editText.getText().clear();
                    }
                });
        // 显示
        normalDialog.show();
    }

}
