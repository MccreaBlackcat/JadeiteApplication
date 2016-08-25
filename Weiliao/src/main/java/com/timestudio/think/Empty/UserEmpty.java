package com.timestudio.think.Empty;

/**
 * Created by thinkpad on 2016/8/19.
 */
public class UserEmpty {
    private String name;
    private String message;
    private int mipmap;
    public UserEmpty(String name,String message,int mipmap){
        this.name=name;
        this.message=message;
        this.mipmap=mipmap;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMipmap() {
        return mipmap;
    }

    public void setMipmap(int mipmap) {
        this.mipmap = mipmap;
    }
}
