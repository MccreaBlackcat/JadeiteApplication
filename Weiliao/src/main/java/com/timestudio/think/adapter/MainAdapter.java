package com.timestudio.think.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.timestudio.think.Empty.UserEmpty;
import com.timestudio.think.myapplication.R;

import java.util.ArrayList;

/**
 * Created by thinkpad on 2016/8/19.
 */

public class MainAdapter extends BaseAdapter {
    private ArrayList<UserEmpty> list;
    protected LayoutInflater inflater;
    public MainAdapter(Context context, ArrayList<UserEmpty> list){
        inflater = LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view  = inflater.inflate(R.layout.activity_weixin_lv,null);
        return view;
    }
}
