package com.timestudio.think.animatesst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author  Created by ShenGqiang on 2016/8/31.
 * @description 展示ListView 过渡动画的适配器
 */
public class TransitionAdapter extends BaseAdapter {

    //上下文对象
    Context mcontext;

    /**
     * @description 本适配器构造器
     * @param mcontext 上下文
     */
    public TransitionAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    //注意：此处的动画不能过多，如果超出屏幕范围，产生了滚动的效果的话，那么动画就会消失
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //缺少优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载子布局的item并返回
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(R.layout.item_transition,null);
        return convertView;
    }
}
