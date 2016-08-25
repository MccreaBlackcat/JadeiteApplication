package com.timestudio.think.animatesst;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class FishActivity extends Activity {
    ImageView iv_fishxml;//通过XML来实现帧动画
    ImageView iv_fishcode;//通过代码来实现帧动画
    AnimationDrawable xmlAnim;//通过xml来实现的实例
    AnimationDrawable codeAnim;//通过代码来实现的实例
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏，必须放在setContentView之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fish);

        //装载view
        initView();
        //装载Animation
        initAnimation();
    }

    /**
     * 装载控件
     */
    public void initView(){
        iv_fishxml = (ImageView)findViewById(R.id.iv_fishxml);
        iv_fishcode = (ImageView)findViewById(R.id.iv_fishcode);
    }

    /**
     * 装载动画
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initAnimation(){
        //XML方式
        //把xml动画设置为imageView的背景，默认情况下显示第一帧
        iv_fishxml.setBackgroundResource(R.drawable.fishanim);
        //获得xmlAnim的实例
        xmlAnim = (AnimationDrawable) iv_fishxml.getBackground();

        //代码方式
        codeAnim = new AnimationDrawable();

        //设置动画的帧
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_01),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_02),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_03),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_04),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_05),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_06),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_07),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_08),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_09),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_10),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_11),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_12),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_13),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_14),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_15),200);
        codeAnim.addFrame(getResources().getDrawable(R.drawable.fish_16),200);
        //把动画设置成ImageView的背景，默认显示为第一帧
        codeAnim.setOneShot(true); //动画播放次数为一次
        iv_fishcode.setBackground(codeAnim);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //当点击屏幕时候
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            xmlAnim.stop(); //停止动画
            xmlAnim.selectDrawable(0);//设置动画的当前帧为第一帧
            xmlAnim.start();//开始动画

            codeAnim.stop();//停止动画
            codeAnim.selectDrawable(0);//设置动画的当前帧为第一帧
            codeAnim.start();//开始动画
        }
        return true;
    }
}
