package com.wjn.androiddbdemo.tableexists;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作本地sqlite数据库的工具类
 */


public class UserService {


    private SQLiteDatabase db;

    /**
     * 构造方法
     */

    public Context context;

    public UserService(Context context) {
        this.context = context;
        db = DBFileService.OpenDB(context);//打开数据库
    }

    /**
     * 向数据表中添加用户 增
     */

    public void addUser(List<User> list) {
        if (db.isOpen()) {//如果数据库是打开的
            db.beginTransaction();//开启事务
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);//获取用户对象
                String sql = "insert into user(id,name, path)" + "VALUES(?,?,?)";//sqlite 插入语句
                db.execSQL(sql, new Object[]{user.getId(), user.getName(), user.getPath()});//执行插入语句
            }
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
    }

    /**
     * 向数据表中删除用户 删
     */

    public void delUesr(User user) {
        if (db.isOpen()) {//如果数据库是打开的
            db.beginTransaction();//开启事务
            String sql = "delete from user where id = " + "'" + user.getId() + "'";//sqlite 删除语句
            db.execSQL(sql);//执行删除语句
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
    }

    /**
     * 向数据表中删除所有用户 删
     */

    public void delAllUesr() {
        if (db.isOpen()) {//如果数据库是打开的
            db.beginTransaction();//开启事务
            String sql = "delete from user";//sqlite 删除语句
            db.execSQL(sql);//执行删除语句
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
    }

    /**
     * 向数据表中修改用户 改
     */

    public void updateUesr(User user) {
        if (db.isOpen()) {//如果数据库是打开的
            db.beginTransaction();//开启事务
            String sql = "update user set name= " + "'" + "张三—>修改" + "'" + "where id = " + "'" + user.getId() + "'";//sqlite 修改语句
            db.execSQL(sql);//执行修改语句
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
    }

    /**
     * 查询表中所有用户
     */

    public List<User> getAllUser() {
        List<User> list = new ArrayList<User>();
        User user = null;
        if (db.isOpen()) {//数据库是否打开
            db.beginTransaction();//开启事务
            String sql = "select * from user ";//查询语句
            Cursor cursor = db.rawQuery(sql, null);//执行查询语句
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setPath(cursor.getString(cursor.getColumnIndex("path")));
                list.add(user);
                cursor.moveToNext();
            }
            cursor.close();//关闭游标
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
        return list;
    }

    /**
     * 查询表中某一条用户
     */

    public User getUser(String id) {
        User user = null;
        if (db.isOpen()) {//数据库是否打开
            db.beginTransaction();//开启事务
            String sql = "select * from user where id=" + "'" + id + "'";//查询语句
            Cursor cursor = db.rawQuery(sql, null);//执行查询语句
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setPath(cursor.getString(cursor.getColumnIndex("path")));
                cursor.moveToNext();
            }
            cursor.close();//关闭游标
            db.setTransactionSuccessful();//事务成功
            db.endTransaction();//结束事务
            db.close();//关闭数据库
        }
        return user;
    }

}
