package com.example.contacts.main.main;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.contacts.R;
import com.example.contacts.main.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView lv_main;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();//绑定控件
        String[] str = {"本地电话","订餐电话","公共服务","运营商","快餐服务","机票酒店","银行证券"};
        //添加数据
        for (String s:str){
            list.add(s);
        }
        //创建适配器对象
        MyAdapter myAdapter = new MyAdapter(this,list);
        //给ListView设置适配器
        lv_main.setAdapter(myAdapter);
    }

    /**
     * 加载控件
     */
    public void initView(){
        //获取绑定控件
        lv_main = (ListView)findViewById(R.id.lv_main);

    }
}
