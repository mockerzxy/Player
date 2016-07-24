package com.example.xueyuanzhang.player.ui.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xueyuanzhang.player.R;

/**
 * Created by xueyuanzhang on 16/7/23.
 */
public class StartActivity extends AppCompatActivity{
    private final static long WHEN_TO_JUMP = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        jumpToMain();
    }

    private void jumpToMain(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(StartActivity.this).toBundle());
                finish();
            }
        },WHEN_TO_JUMP);
    }
}
