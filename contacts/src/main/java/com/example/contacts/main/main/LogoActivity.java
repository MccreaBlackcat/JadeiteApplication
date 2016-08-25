package com.example.contacts.main.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.contacts.R;

public class LogoActivity extends Activity {
    ImageView im_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        initView();//绑定控件
        initAnim();//绑定动画
    }

    /**
     * 装载控件
     */
    public void initView(){
        im_logo = (ImageView)findViewById(R.id.im_logo);
    }

    public void initAnim(){
        //xml实现ViewAnimation
        //加载xml中的Animation
        final Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        //设置监听
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //设置Intent，实现动画结束后跳转到主界面
                Intent intent = new Intent();
                //设置从LogoActivity跳转到MainActivity
                intent.setClass(LogoActivity.this,MainActivity.class);
                //跳转界面
                startActivity(intent);
                //跳转结束后结束动画界面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //开启动画：方式1
        im_logo.setAnimation(anim);
        anim.start();
        //开启动画:方式2
//        im_logo.startAnimation(anim);
    }
}
