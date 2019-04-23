package com.wjn.androiddbdemo.activity.sqlite;

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

public class SQLiteUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView;

    private SQLiteHelp dbHelp;//帮助类对象
    private SQLiteDatabase db;//用于管理和操作SQLite数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqliteupdate);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_sqliteupdate_textview1);
        textView = findViewById(R.id.activity_sqliteupdate_textview);
        textView1.setOnClickListener(this);
        //提高版本升级数据库
        dbHelp = new SQLiteHelp(this, "Test.db", null, 2);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sqliteupdate_textview1://查询升级后的数据库
                findbtnMethod();
                break;
            default:
                break;
        }
    }

    /**
     * 查询升级后的数据库
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

}