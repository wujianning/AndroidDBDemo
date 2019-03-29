package com.wjn.androiddbdemo.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLiteOpenHelper实现类
 * */

public class SQLiteHelp extends SQLiteOpenHelper {

    /**
     * 构造方法
     * */

    public SQLiteHelp(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    /**
     * onCreate方法,第一次创建时调用---->建表
     * */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id int,name varchar(20))";
        db.execSQL(sql);
    }

    /**
     * onUpgrade方法,更新数据库版本时调用---->删除原来的表,重新执行onCreate方法
     * */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1.将旧表改名成临时表
        String sql1 = "ALTER TABLE user RENAME TO _temp_User";
        //2.创建新表
        String sql2=" CREATE TABLE user (id int,name varchar(20),describe varchar(200));";
        //3.将临时表的数据导入到新表 原表中没有的要自己设个默认值
        String sql3="INSERT INTO User SELECT id,name,\"这是描述\" FROM _temp_User";
        //删除临时表
        String sql4="DROP TABLE _temp_User";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
    }

}
