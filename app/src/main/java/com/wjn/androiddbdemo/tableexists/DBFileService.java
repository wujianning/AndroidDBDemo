package com.wjn.androiddbdemo.tableexists;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.File;

public class DBFileService {

    //打开数据库的方法
    public static synchronized SQLiteDatabase OpenDB(Context context) {
        SQLiteDatabase db = null;
        //Context.getFilesDir()可以获取到"/data/data/<package name>/files"
        File dbfile = new File(context.getFilesDir(), "MySqlite.db");
        int n = 0;
        while (n < 6) {//打开数据库时最多尝试3s，数据库可能因并发访问处于锁定状态
            try {
                db = SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
                break;
            } catch (SQLiteException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                n++;
            }
        }
        return db;
    }

}
