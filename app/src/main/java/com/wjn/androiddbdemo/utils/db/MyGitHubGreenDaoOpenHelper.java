package com.wjn.androiddbdemo.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.wjn.androiddbdemo.greendao.DaoMaster;
import com.wjn.androiddbdemo.greendao.UserInfoDao;

import org.greenrobot.greendao.database.Database;

public class MyGitHubGreenDaoOpenHelper  extends DaoMaster.OpenHelper{

    /**
     * 构造方法
     * */

    public MyGitHubGreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * onUpgrade方法
     * 使用GitHub MigrationHelper 将老表的数据复制到新表
     * */

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },  UserInfoDao.class);
    }
}
