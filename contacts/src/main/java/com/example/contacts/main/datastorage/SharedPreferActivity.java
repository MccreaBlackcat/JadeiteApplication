package com.example.contacts.main.datastorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.main.BaseActivity;

/**
 * @author ShenGuiqiang
 * @descrition Shared Preferences 的使用
 */
public class SharedPreferActivity extends BaseActivity implements View.OnClickListener{
    Button btn_writeSp;//更改SharePreferences的值
    TextView tv_spValue;//SharePreferences 获取的值
    Button btn_internal; //点击跳转到InternalStorege页面
    //SharePreferences 的文件名
    private final String SPFILE_NAME = "spfile";
    //SharePreferences 存入的布尔值key
    private final String BOOL_NAME = "myboolean";

    @Override
    protected int setContent() {
        return R.layout.activity_shared_prefer;
    }

    @Override
    protected void initView() {
        tv_spValue  = (TextView)findViewById(R.id.tv_spValue);
        btn_writeSp = (Button)findViewById(R.id.btn_writeSp);
        btn_internal = (Button)findViewById(R.id.btn_internal);
        readSpValue();
    }

    @Override
    protected void setListener() {
        btn_writeSp.setOnClickListener(this);
        btn_internal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_writeSp://Wirte a true value
                writeSpValue();
                break;
            case R.id.btn_internal://跳转
                Intent intent = new Intent(SharedPreferActivity.this,InternalStoActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * @description 读取出SharePreferences 的值
     */
    private void readSpValue(){
        //获取 SharePreferences 的实例，参数1为文件名，参数2为文件模式
        //默认模式为0，即MODE_PRIVATE,有对应文件则读取，无则创建
        SharedPreferences sp = getSharedPreferences(SPFILE_NAME,0);
        //读取 SharePreferences 中对应的boolean数据
        boolean temp = sp.getBoolean(BOOL_NAME,false);
        //设置到UI中去展示
        tv_spValue.setText(temp+"");
    }
    /**
     * @description 向 SharePreferences 中写入值
     */
    private void writeSpValue(){
        //获取 SharePreferences 的实例，参数1为文件名，参数2为文件模式
        //默认模式为0，即MODE_PRIVATE,有对应文件则读取，无则创建
        SharedPreferences sp = getSharedPreferences(SPFILE_NAME,0);
        //获得 SharePreferences 的编辑器
        SharedPreferences.Editor editor = sp.edit();
        //向 SharePreferences 中添加一个key为"myboolean"的布尔值
        editor.putBoolean(BOOL_NAME,true);
        //提交数据
        editor.commit();
        //读取sp中对应的boolean数据
        boolean temp = sp.getBoolean(BOOL_NAME,false);
        //设置到UI中的展示
        tv_spValue.setText(temp+"");
    }
}
