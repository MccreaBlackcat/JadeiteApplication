package com.example.contacts.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contacts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShenGuiqiang
 *  主界面的ListView适配器
 * Created by thinkpad on 2016/8/24.
 */
public class MyAdapter extends BaseAdapter {

    protected List<String> listName = new ArrayList<>();//存名字的集合
    protected LayoutInflater inflater; //声明一个布局填充器

    public MyAdapter(Context context, ArrayList<String> list) {
        super();
        inflater = LayoutInflater.from(context); //实例化
        this.listName=list;
    }

    @Override
    public int getCount() {
        return listName.size();
    }

    @Override
    public Object getItem(int position) {
        return listName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载一个界面
        convertView = inflater.inflate(R.layout.activity_main_list_view,null);
        //获取到加载界面的控件
        TextView tv_name = (TextView)convertView.findViewById(R.id.main_lv_tv_name);
        //为控件设置Text
        tv_name.setText(listName.get(position));
        return convertView;
    }
}
