package com.troology.FPO;

import android.app.Application;
import android.content.Context;


import androidx.multidex.MultiDex;

import java.util.HashSet;
import java.util.Set;

public class ApplicationController extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    public static Set set=new HashSet();

    String Lang;
    public static int textSize=0;

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(String lang) {
        Lang = lang;
    }
}
