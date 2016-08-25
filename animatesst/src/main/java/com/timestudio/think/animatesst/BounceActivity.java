package com.timestudio.think.animatesst;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BounceActivity extends AppCompatActivity {
    LinearLayout ll_bounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bounce);

        initView();
    }

    /**
     * 装载控件
     */
    public void initView(){
        ll_bounce = (LinearLayout)findViewById(R.id.ll_bounce);
        MyView   myView = new MyView(this);
        ll_bounce.addView(myView);
    }

    class MyView extends View {
        int RED = 0xffff6060;
        int BLUE = 0xff6060ff;
        ShapeHolder shapeHolder;
        List<ShapeHolder> balls = new ArrayList<ShapeHolder>();

        public MyView(Context context) {
            super(context);
            //创建动画，设置对象的backgroundColor值，在RED和BLUE之间变化
            ValueAnimator anim = ObjectAnimator.ofInt(this, "backgroundColor", RED, BLUE);
            //设置动画播放时间
            anim.setDuration(3000);
            //设置类型估值器
            anim.setEvaluator(new ArgbEvaluator());
            //设置动画重复次数
            anim.setRepeatCount(Animation.INFINITE);
            //设置动画重复模式
            anim.setRepeatMode(Animation.REVERSE);
            //开始动画
            anim.start();
        }


        //自定义触摸逻辑
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //判断事件为点击或者移动
            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                //引用添加的小球
                ShapeHolder ball = addBalls(event.getX(), event.getY());
                //向集合中添加小球
                balls.add(ball);
                //创建小球下落动画
                float currX = event.getX(); //当前点击的X坐标
                float currY = event.getY();//当前点击的Y坐标
                //整个view的高度
                int h = getHeight();
                //获取到当前小球的宽
                float ballWidth = ball.getWidth();
                //获取的当前小球的高
                float ballHeight = ball.getHeight();
                //设置小球落到底部的高度,-50f是因为小球要进行压缩动画
                float endY = h - 50f;
                //设置从屏幕顶部到底部所用的时间1000ms
                long duration = 3500;
                //从屏幕任意位置下落的小球所需要的真实时间
                long realDuration = (long) ((h - shapeHolder.getY()) / h * duration);

                //创建小球下落的动画
                ValueAnimator downAnim = ObjectAnimator.ofFloat(shapeHolder, "y", currY, endY);
                downAnim.setDuration(realDuration); //动画持续的时间
                downAnim.setInterpolator(new AccelerateInterpolator()); //小球下落的速度为加速下落，设置一个加速插值
                //创建小球压缩的动画
                //1.增加小球的宽度
                ValueAnimator Widthanim = ObjectAnimator.ofFloat(shapeHolder, "width", ballWidth, ballWidth + 50f);
                Widthanim.setDuration(realDuration / 4);//压缩动画持续时间
                Widthanim.setInterpolator(new DecelerateInterpolator());//减速插值(越来越难压缩)
                //小球回弹（恢复原形）
                Widthanim.setRepeatCount(1); //动画循环次数
                Widthanim.setRepeatMode(ValueAnimator.REVERSE);//动画循环模式
                //2.压缩小球的高度
                ValueAnimator Heightanim = ObjectAnimator.ofFloat(shapeHolder, "height", ballHeight, ballHeight - 50f);
                Heightanim.setDuration(realDuration / 4);//压缩动画持续时间
                Heightanim.setInterpolator(new DecelerateInterpolator());//设置一个减速的插值器
                //小球回弹（恢复原形）
                Heightanim.setRepeatCount(1);//设置动画循环播放1次
                Heightanim.setRepeatMode(ValueAnimator.REVERSE);//设置动画循环模式
                //3.增加x的坐标
                ValueAnimator Xanim = ObjectAnimator.ofFloat(shapeHolder, "x", currX, currX-25f);
                Xanim.setDuration(realDuration / 4);
                Xanim.setInterpolator(new DecelerateInterpolator());
                Xanim.setRepeatCount(1);
                Xanim.setRepeatMode(ValueAnimator.REVERSE);
                //4.减小y的坐标
                ValueAnimator Yanim = ObjectAnimator.ofFloat(shapeHolder, "y", endY, endY + 50f);
                Yanim.setDuration(realDuration / 4);
                Yanim.setInterpolator(new DecelerateInterpolator());
                Yanim.setRepeatCount(1);
                Yanim.setRepeatMode(ValueAnimator.REVERSE);
                //小球上升的动画
                ValueAnimator upAnim = ObjectAnimator.ofFloat(shapeHolder, "y", endY, currY);
                upAnim.setDuration(realDuration/2);
                upAnim.setInterpolator(new DecelerateInterpolator());
                upAnim.setRepeatCount(1);
                upAnim.setRepeatMode(ValueAnimator.REVERSE);

                //小球消逝的动画
                ValueAnimator alphaAnim = ObjectAnimator.ofFloat(shapeHolder, "alpha", 1.0f, 0.0f);
                alphaAnim.setDuration(realDuration / 4);
                alphaAnim.setInterpolator(new LinearInterpolator());

                //添加一个动画播放后的监听
                alphaAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //把小球移除容器
                        balls.remove(((ObjectAnimator) animation).getTarget());
                    }
                });
                //创建一组动画顺序
                AnimatorSet boucerSet = new AnimatorSet();
                boucerSet.play(downAnim).before(Xanim);
                boucerSet.play(Xanim).with(Widthanim);
                boucerSet.play(Xanim).with(Yanim);
                boucerSet.play(Xanim).with(Heightanim);
                boucerSet.play(upAnim).after(Heightanim);

                //多个动画组嵌套
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(alphaAnim).after(boucerSet);

                //开始动画
                animSet.start();

            }
            return true;
        }

        //绘制自定义内容，重写onDraw方法
        @Override
        protected void onDraw(Canvas canvas) {
            //移动画布到指定区域
            for (ShapeHolder ball:balls){
                canvas.save();
                canvas.translate(ball.getX()-50f,ball.getY()-50f);
                ball.getShape().draw(canvas);
                canvas.restore();
            }
        }

        /**
         * 添加小球
         * @param x
         * @param y
         * @return
         */
        public ShapeHolder addBalls(float x,float y){
            //获得随机的RGB值
            int RED = (int) (Math.random()*255)<<16;
            int GREED = (int) (Math.random()*255)<<8;
            int BLUE = (int) (Math.random()*255);
            //求上面三个的并集
            int COLOR =0xff000000 | RED|GREED|BLUE;
            //计算一个较深的颜色作为球的环形放射（高光效果）
            int DARKCORLOR = 0xff000000;//|(RED/4)|(GREED/4)|(BLUE/4);
            //椭圆
            OvalShape cilcle = new OvalShape();
            //设置椭圆的高度和宽度
            cilcle.resize(100f,100f);
            //创建shapeDrawable
            ShapeDrawable shapeDrawable = new ShapeDrawable(cilcle);
            //创建自己的shapeHolder
            shapeHolder = new ShapeHolder(shapeDrawable);
            //设置绘制图形的颜色
            shapeHolder.setColor(COLOR);
            //设置图形的坐标
            shapeHolder.setX(x);
            shapeHolder.setY(y);
            //设置图形的环形放射颜色
            RadialGradient gradient = new RadialGradient(75f,25f,100,COLOR,DARKCORLOR, Shader.TileMode.CLAMP);
            shapeHolder.setGradient(gradient);
            return shapeHolder;
        }
    }
}
