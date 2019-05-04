package com.example.demo.bean;

public class Item {
    private String mTitle;
    private Class mClass;

    public Item(String mTitle, Class mClass) {
        this.mTitle = mTitle;
        this.mClass = mClass;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
}
