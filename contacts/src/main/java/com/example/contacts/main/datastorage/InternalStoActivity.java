package com.example.contacts.main.datastorage;


import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.main.BaseActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author Shenguiqiang
 * @descrition 内部存储器的demo类
 */
public class InternalStoActivity extends BaseActivity {
    TextView tv_internalFile;
    TextView tv_rowFile;
    String FILE_NAME = "internal file";
    String FILE_DATA = "Hello internal file!";

    @Override
    protected int setContent() {
        return R.layout.activity_internal_sto;
    }

    @Override
    protected void initView() {
        tv_internalFile = (TextView)findViewById(R.id.tv_internalFile);
        tv_rowFile = (TextView)findViewById(R.id.tv_rowfile);
        writeFile(FILE_NAME);
        readFile(FILE_NAME);

}

    @Override
    protected void setListener() {

    }

    /**
     * @description 向内部存储器中写入文件
     * @param fileName 写入文件的文件名
     */
    private void writeFile(String fileName){
        //创建一个替换文件，第1个参数为文件名，第2个参数为文件模式
        try {
            FileOutputStream fop = openFileOutput(fileName,MODE_PRIVATE);
            //向文件中写入数据
            fop.write(FILE_DATA.getBytes("UTF-8"));
            //关闭数据流
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @descrition 向内部存储器读取文件
     * @param fileName
     */
    private void readFile(String fileName){

        try {
            //运行期文件
            //创建读取文件的输入流
            FileInputStream fip = openFileInput(fileName);
            //创建保存文件的临时字符串
            String temp = "";
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //向缓冲区中循环读入数据，直到数据读完（读到-1）
            while (fip.read(buffer) != -1){
                //通过byte数组来拼接字符串
                temp += new String(buffer);
            }
            //关闭数据流
            fip.close();
            //更新到UI上
            tv_internalFile.setText(temp);


            //编译期文件
            //创建读取row文件夹下文件的输入流
            InputStream ip = getResources().openRawResource(R.raw.rawfile);
            //重新设置临时字符串为空
            temp = "";
            //重新设置缓冲区为空
            buffer = new byte[1024];
            //想缓冲区中循环读入数据，直到数据读完（读到-1）
            while (ip.read(buffer)!=-1){
                //通过byte数据来拼接字符串
                temp += new String(buffer);
            }
            //关闭数据流
            ip.close();
            //将数据更新到UI上
            tv_rowFile.setText(temp);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
