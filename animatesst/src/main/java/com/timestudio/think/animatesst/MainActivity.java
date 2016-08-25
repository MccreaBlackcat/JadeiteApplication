package com.timestudio.think.animatesst;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener{

    LinearLayout ll_container;
    Button bt_xml;
    Button bt_view;
    Button bt_bounce;
    Button bt_fish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏和状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //装载控件
        initView();
        //
       // ValueAnimTest();y


    }


    /**
     * 装载控件
     * */
    private void initView(){
        //所有控件的盒子
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        bt_xml = (Button)findViewById(R.id.bt_xml);
        bt_xml.setOnClickListener(this);
        bt_view=(Button)findViewById(R.id.bt_view);
        bt_view.setOnClickListener(this);
        bt_bounce = (Button)findViewById(R.id.bt_bounce);
        bt_bounce.setOnClickListener(this);
        bt_fish = (Button)findViewById(R.id.bt_fish);
        bt_fish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bt_xml:
                intent.setClass(MainActivity.this,XMLActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_view:
                intent.setClass(MainActivity.this,ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_bounce:
                intent.setClass(MainActivity.this,BounceActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fish:
                intent.setClass(MainActivity.this,FishActivity.class);
                startActivity(intent);
                break;
        }
    }
}
