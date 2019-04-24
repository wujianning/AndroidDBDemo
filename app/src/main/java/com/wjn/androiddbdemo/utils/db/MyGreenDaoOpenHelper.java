package com.wjn.androiddbdemo.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wjn.androiddbdemo.greendao.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class MyGreenDaoOpenHelper  extends DaoMaster.OpenHelper {

    /**
     * 构造方法
     * */

    public MyGreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * onUpgrade方法
     * 将老表的数据复制到新表
     * */

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //1.将旧表改名成临时表
        String sql1 = "ALTER TABLE USER_INFO RENAME TO _USER_INFO";
        //2.创建新表
        String sql2=" CREATE TABLE USER_INFO (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE TEXT,DESCRIBE TEXT);";
        //3.将临时表的数据导入到新表 原表中没有的要自己设个默认值
        String sql3="INSERT INTO USER_INFO SELECT _id,NAME,AGE,\"这是描述\" FROM _USER_INFO";
        //删除临时表
        String sql4="DROP TABLE _USER_INFO";
        //执行SQL语句
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
    }
}
