package com.wjn.androiddbdemo.activity.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.bean.Person;
import com.wjn.androiddbdemo.utils.db.DBCheckAsyncTask;
import com.wjn.androiddbdemo.utils.db.DBSQLiteOpenHelper;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

public class AsyncTaskSQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView;
    private DBSQLiteOpenHelper dbsqLiteOpenHelper;
    private SQLiteDatabase db;
    private int id=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctasksqlite);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_asynctasksqlite_textview1);
        textView2 = findViewById(R.id.activity_asynctasksqlite_textview2);
        textView3 = findViewById(R.id.activity_asynctasksqlite_textview3);
        textView4 = findViewById(R.id.activity_asynctasksqlite_textview4);
        textView = findViewById(R.id.activity_asynctasksqlite_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        dbsqLiteOpenHelper=new DBSQLiteOpenHelper(this,"wjn.db",null,1);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_asynctasksqlite_textview1://增
                insertbtnMethod();
                break;
            case R.id.activity_asynctasksqlite_textview2://删
                delbtnMethod("100");
                break;
            case R.id.activity_asynctasksqlite_textview3://改
                updatebtnMethod();
                break;
            case R.id.activity_asynctasksqlite_textview4://查
                findbtnMethod();
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
        db=dbsqLiteOpenHelper.getReadableDatabase();
        //初始化对象
        Person person=new Person();
        person.setId(String.valueOf(id));
        person.setName("张三");
        person.setDescribe("本章节讲述getWritableDatabase()和getReadableDatabase()区别");
        //插入语句
        db.execSQL("INSERT INTO mytable(id,name,describe) values(?,?,?)",
                new String[]{person.getId(), person.getName(),person.getDescribe()});
        //关闭数据库
        db.close();
    }

    /**
     * API语句 删  
     */

    public void delbtnMethod(String id) {
        //打开数据库
        db=dbsqLiteOpenHelper.getReadableDatabase();
        //删除语句
        db.execSQL("DELETE FROM mytable WHERE id = ?",
                new String[]{id});
        //关闭数据库
        db.close();
    }

    /**
     * API语句 改  
     */

    public void updatebtnMethod() {
        //打开数据库
        db=dbsqLiteOpenHelper.getReadableDatabase();
        //初始化对象
        Person person = new Person();
        person.setId("100");
        person.setName("修改后的姓名");
        person.setDescribe("修改后的描述");
        //修改语句
        db.execSQL("UPDATE mytable SET name = ?,describe = ? WHERE id = ?",
                new String[]{person.getName(), person.getDescribe(), person.getId()});
        //关闭数据库
        db.close();
    }

    /**
     * API语句 查
     */

    public void findbtnMethod() {
        //打开数据库
        db=dbsqLiteOpenHelper.getReadableDatabase();
        db=dbsqLiteOpenHelper.getReadableDatabase();
        new DBCheckAsyncTask(textView,db).execute("select * from mytable");
    }

}
