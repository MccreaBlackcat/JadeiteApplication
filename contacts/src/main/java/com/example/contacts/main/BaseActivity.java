package com.example.contacts.main;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(setContent());
        initView();
        setListener();
    }

    protected abstract int setContent();

    protected abstract void initView();

    protected abstract void setListener();
}
