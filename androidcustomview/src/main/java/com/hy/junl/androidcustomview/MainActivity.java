package com.hy.junl.androidcustomview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转到 点击图片变暗的页面
     * @param view
     */
    public void skip2ImageView(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}
