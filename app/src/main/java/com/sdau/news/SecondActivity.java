package com.sdau.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button but1;
    private Button but2,but3,but4,but5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1=findViewById(R.id.button);
        but2=findViewById(R.id.button2);
        but3=findViewById(R.id.button3);
        but4=findViewById(R.id.button4);
        but5=findViewById(R.id.button5);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        but5.setOnClickListener(this);
        Log.d("TAG", "onCreate: 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: 2");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume:2 ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart:2 ");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy:2 ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause:2 ");
    }

    public void onClick(View view){
        Intent intent=null;
        switch (view.getId()) {
            case R.id.button2:
                intent = new Intent(this,MainActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(this,SecondActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this,ThirdActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this,FourthActivity.class);
                break;
            case R.id.button: intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));

        }
        startActivity(intent);
    }
}
