package com.wjn.androiddbdemo.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.utils.db.SQLiteHelp;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

public class SimpleSQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView;

    private SQLiteHelp dbHelp;//帮助类对象
    private SQLiteDatabase db;//用于管理和操作SQLite数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplesqlite);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_simplesqlite_textview1);
        textView2 = findViewById(R.id.activity_simplesqlite_textview2);
        textView3 = findViewById(R.id.activity_simplesqlite_textview3);
        textView4 = findViewById(R.id.activity_simplesqlite_textview4);
        textView5 = findViewById(R.id.activity_simplesqlite_textview5);
        textView6 = findViewById(R.id.activity_simplesqlite_textview6);
        textView7 = findViewById(R.id.activity_simplesqlite_textview7);
        textView8 = findViewById(R.id.activity_simplesqlite_textview8);
        textView = findViewById(R.id.activity_simplesqlite_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        dbHelp = new SQLiteHelp(this, "Test.db", null, 2);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_simplesqlite_textview1://API语句 增
                insertbtnMethod();
                break;
            case R.id.activity_simplesqlite_textview2://API语句 删
                delbtnMethod();
                break;
            case R.id.activity_simplesqlite_textview3://API语句 改
                updatebtnMethod();
                break;
            case R.id.activity_simplesqlite_textview4://API语句 查
                findbtnMethod();
                break;
            case R.id.activity_simplesqlite_textview5://SQLite语句 增
                insertbtnMethods();
                break;
            case R.id.activity_simplesqlite_textview6://SQLite语句 删
                delbtnMethods();
                break;
            case R.id.activity_simplesqlite_textview7://SQLite语句 改
                updatebtnMethods();
                break;
            case R.id.activity_simplesqlite_textview8://SQLite语句 查
                findbtnMethods();
                break;
            default:
                break;
        }
    }

    /**
     * API语句 增
     */

    public void insertbtnMethod() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //插入一条数据
        ContentValues values1 = new ContentValues();//类似于map
        values1.put("id", 1);
        values1.put("name", "张三");
        db.insert("user", null, values1);
        //插入一条数据
        ContentValues values2 = new ContentValues();
        values2.put("id", 2);
        values2.put("name", "李四");
        db.insert("user", null, values2);
        //插入一条数据
        ContentValues values3 = new ContentValues();//类似于map
        values3.put("id", 3);
        values3.put("name", "王五");
        db.insert("user", null, values3);
        //插入一条数据
        ContentValues values4 = new ContentValues();
        values4.put("id", 4);
        values4.put("name", "赵六");
        db.insert("user", null, values4);
        //关闭数据库
        db.close();
    }

    /**
     * API语句 删  
     */

    public void delbtnMethod() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //执行删除操作
        db.delete("user", "id=3", null);
        //关闭数据库
        db.close();
    }

    /**
     * API语句 改  
     */

    public void updatebtnMethod() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //类似于map
        ContentValues values = new ContentValues();
        values.put("name", "API修改");
        db.update("user", values, "id=1", null);
        //关闭数据库
        db.close();
    }

    /**
     * API语句 查
     */

    public void findbtnMethod() {
        db = dbHelp.getReadableDatabase();//打开数据库对象
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        //遍历游标对象
        cursor.moveToFirst();
        StringBuilder sb = new StringBuilder();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            sb.append("ID:" + id + "\n");
            String name = cursor.getString(cursor.getColumnIndex("name"));
            sb.append("姓名:" + name + "\n");
            String describe = cursor.getString(cursor.getColumnIndex("describe"));
            sb.append("描述:" + describe + "\n\n");
            cursor.moveToNext();
        }
        textView.setText(sb.toString());
        db.close();
    }

    /**
     * SQL语句 增
     * */

    public void insertbtnMethods() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //SQL语句
        String sql1 = "insert into user values(1,'张三')";
        String sql2 = "insert into user values(2,'李四')";
        String sql3 = "insert into user values(3,'王五')";
        String sql4 = "insert into user values(4,'赵六')";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        //关闭数据库
        db.close();
    }

    /**
     * SQL语句 删
     * */

    public void delbtnMethods() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //SQL语句
        String sql = "delete from user where id=1";
        db.execSQL(sql);
        //关闭数据库
        db.close();
    }

    /**
     * SQL语句 改
     * */

    public void updatebtnMethods() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //SQL语句
        String sql = "update user set name='SQL修改' where id=2";
        db.execSQL(sql);
        //关闭数据库
        db.close();
    }

    /**
     * SQL语句 查
     * */

    public void findbtnMethods() {
        //打开数据库
        db = dbHelp.getReadableDatabase();
        //SQL语句
        String sql = "select * from user";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        StringBuilder sbBuilder = new StringBuilder();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            sbBuilder.append("ID:" + id + "\n");
            String name = cursor.getString(cursor.getColumnIndex("name"));
            sbBuilder.append("姓名:" + name + "\n\n");
            cursor.moveToNext();
        }
        textView.setText(sbBuilder.toString());
        //关闭数据库
        db.close();
    }

}
