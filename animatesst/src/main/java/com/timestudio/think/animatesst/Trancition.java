package com.timestudio.think.animatesst;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @author ShenGqiang
 * @Description Trancition动画的展示类
 */
public class Trancition extends Activity {

    protected LinearLayout ll_container; // 添加的view 的容器
    protected int i_count; //被添加的按钮的数量
    LayoutTransition transition = new LayoutTransition();//自定义布局过渡动画
    ListView lv_transition; //展示item的过渡动画列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trancition);

        initView();
    }

    /**
     * @Desciption 装载控件
     */
    private void initView(){
        ll_container = (LinearLayout)findViewById(R.id.ll_container);
        lv_transition = (ListView)findViewById(R.id.lv_transition);
        //设置数据列表
        setListView();
        //装载过渡动画
        setTransition();
    }

    /**
     * @description 设置数据列表
     */
    private void setListView() {
        //设置列表适配器
        lv_transition.setAdapter(new TransitionAdapter(this));
        //加载单个的item动画
        Animation rightin = AnimationUtils.loadAnimation(this,R.anim.righrin);
        //根据单个的item动画创建布局过渡动画控制器
        LayoutAnimationController lc = new LayoutAnimationController(rightin);
        //设置动画延迟时间
        lc.setDelay(0.2f);
        //设置动画播放次序
        lc.setOrder(LayoutAnimationController.ORDER_RANDOM);//随机播放
        //给目标ListView设置item布局过渡动画
        lv_transition.setLayoutAnimation(lc);
    }

    /**
     * @Description 向容器中添加按钮
     * @param view 监听的View
     */
    public void addButton(View view){
        //View数量递增
        i_count++;
        //实例化一个按钮
        Button button = new Button(this);
        //按钮设置文本
        button.setText("button"+i_count);
        //实例化一个布局参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //给待添加的按钮设置布局参数
        button.setLayoutParams(params);
        //将按钮添加到容器中
        if(i_count > 1){
            ll_container.addView(button,0);
        }else{
            ll_container.addView(button);
        }

    }
    /**
     * @Description 向容器中删除按钮
     * @param view 监听的View容器
     */
    public void removeButton(View view){
        //判断当前是否存在按钮
        if (i_count>0){
            //数量递减
            i_count--;
            //删除下标为0的控件
            ll_container.removeViewAt(0);

        }
    }
    /**
     * @Description  设置布局的过度动画
     */
    private void setTransition(){
        //给指定容器设置过度动画
        ll_container.setLayoutTransition(transition);

        //自定义添加view动画
        //target参数填null，最终setAnimator的时候会自动设置
//        ValueAnimator appearAnim = ObjectAnimator.ofFloat(null,"rotationX",90f,0f);
//        //设置一个默认的播放时间
//        appearAnim.setDuration(transition.getDuration(LayoutTransition.APPEARING));
//        //给过度动画设置指定动画类型和动画效果
//        transition.setAnimator(LayoutTransition.APPEARING,appearAnim);




        //自动以删除view动画
//        ValueAnimator disapearAnim = ObjectAnimator.ofFloat(null,"width",100f,00);
//        //设置一个默认的播放时间
//        disapearAnim.setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
//        //给过度动画设置指定动画类型和动画效果
//        transition.setAnimator(LayoutTransition.DISAPPEARING,disapearAnim);

        //对于CHANGE_APPEARING 和 CHANGE_DISAPPEARING 类型的过度动画
        //1.必须使用 propertyValuesHolder来构造动画
        //2.必须同时指定left top right bottom属性，否则无法正确显示
        //3.动画结束后也需要将view改为原样，否则会出现bug
        //4.构建ObjectAnimator时需要的对象

        //设置CHANGE_APPEARING类型的动画
        PropertyValuesHolder left = PropertyValuesHolder.ofInt("left",0,0);
        PropertyValuesHolder right = PropertyValuesHolder.ofInt("right",0,0);
        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top",0,0);
        PropertyValuesHolder bottom = PropertyValuesHolder.ofInt("bottom",0,0);
        //编写自定义的CHANGE_APPEARING动画
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1f,2f,1f);
        //通过propertyValuesHolder构建动画
        ValueAnimator cgapAnim = ObjectAnimator.ofPropertyValuesHolder(
                ll_container,left,right,top,bottom,scaleX
        );

        //为view复原
        cgapAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ((View)((ObjectAnimator)animation).getTarget()).setScaleX(1f);
            }
        });
        //给过度动画指定动画类型和动画类型
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,cgapAnim);

        //删除控件借用CHANGE_APPEARING的效果
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,cgapAnim);

    }
}
