package com.timestudio.think.animatesst;

import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView tv_xml;
    TextView tv_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //绑定控件
        initView();
        initAnim();

    }

    /**
     * 装载控件
     */
    public void initView(){
        tv_xml = (TextView)findViewById(R.id.tv_xml);
        tv_code = (TextView)findViewById(R.id.tv_code);
    }

    /**
     * 装载动画
     */
    public void initAnim(){
        //通过XML方式实现ViewAnimation
        //加载XML中的ViewAnimation
        Animation anim =  AnimationUtils.loadAnimation(this, R.anim.viewanim);
        //设置动画状态监听
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画播放结束后会重新开始播放
                tv_xml.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //启动动画
        tv_xml.startAnimation(anim);



        //通过代码的方式实现ViewAnimation
        //创建一个旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, //开始角度
                360f,//结束角度
                Animation.RELATIVE_TO_SELF, //旋转点X轴的类型（相对于控件）
                .5f,//旋转点X轴的百分比（自身宽度的50%）
                Animation.RELATIVE_TO_SELF,//旋转点Y轴的类型（相对于控件）
                .5f//旋转点Y轴的百分比（自身高度的50%）
        );
        //设置旋转的时间
        rotateAnimation.setDuration(1000);
        //设置选准的插值器（加速）
        rotateAnimation.setInterpolator(new AccelerateInterpolator());

        //创建一个平移动画
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0f,//起点X的值
                400f,//到达X点的值
                0f,//起始Y点的值
                400f//到达Y点的类型（这里便是相对于控件本身的点）
        );
        //设置旋转的持续时间
        translateAnimation.setDuration(1000);
        //设置旋转的插值（加速）
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        //设置动画播放延迟
        translateAnimation.setStartOffset(1000);

        //创建缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f,   //起始X轴的缩放值
                3.0f,   //目标X轴的缩放值
                1.0f,   //起始Y轴的缩放值
                3.0f,   //目标Y轴的缩放值
                Animation.RELATIVE_TO_SELF,//缩放点X的类型（这里相对于控件自身）
                .5f,    //缩放点X的值（这里为相对于控件自身50%）
                Animation.RELATIVE_TO_SELF,//缩放点Y的类型（这里相对于控件自身）
                .5f     //缩放点Y的值（这里为相对于控件自身50%）
        );
        //设置缩放的持续时间
        scaleAnimation.setDuration(1000);
        //设置缩放的插值器（加速）
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        //设置动画播放延迟
        scaleAnimation.setStartOffset(1000);

        //创建透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        //设置透明度持续时间
        alphaAnimation.setDuration(1000);
        //设置透明度插值器（加速）
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        //设置动画播放延迟
        alphaAnimation.setStartOffset(1000);
        //两个动画集合
        final AnimationSet allSet = new AnimationSet(false);
        AnimationSet childSet = new AnimationSet(false);
        //向子集合中添加动画
        childSet.addAnimation(translateAnimation);//添加平移动画
        childSet.addAnimation(scaleAnimation);//添加缩放动画
        childSet.addAnimation(alphaAnimation);//添加透明度动画
        //向总集中添加动画
        allSet.addAnimation(rotateAnimation);//添加旋转动画
        allSet.addAnimation(childSet);//添加自己动画
        //添加动画播放完毕后的监听以实现动画循环
        allSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //启动方式1
//                tv_code.startAnimation(allSet);

                //启动方式2
                //停止动画后重设置动画的状态
                allSet.reset();
                //重新播放动画
                allSet.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //启动动画的方式1
//        tv_code.startAnimation(allSet);
        //启动动画的方式2
        //吧动画设置给view
        tv_code.setAnimation(allSet);
        //启动动画
        allSet.start();
    }


}