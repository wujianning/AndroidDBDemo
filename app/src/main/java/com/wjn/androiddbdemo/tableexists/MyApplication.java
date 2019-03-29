package com.wjn.androiddbdemo.tableexists;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //将src下建好的表复制到data/data/应用程序包名/files/xxxx.db
        CopyData copyData = new CopyData(this);
        try {
            if (!copyData.isExsit()) {
                copyData.copyDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
