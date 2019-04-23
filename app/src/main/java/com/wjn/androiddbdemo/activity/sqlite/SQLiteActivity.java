package com.wjn.androiddbdemo.activity.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();
    }

    /**
     * 初始化各种View
     * */

    private void initView(){
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false,false, R.color.colorPrimary);

        //简单增删改查
        TextView textView1=findViewById(R.id.activity_sqlite_textview1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SQLiteActivity.this,SimpleSQLiteActivity.class);
                startActivity(intent);
            }
        });

        //AsyncTask封装
        TextView textView2=findViewById(R.id.activity_sqlite_textview2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SQLiteActivity.this,AsyncTaskSQLiteActivity.class);
                startActivity(intent);
            }
        });

        //SQLite数据库升级
        TextView textView3=findViewById(R.id.activity_sqlite_textview3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SQLiteActivity.this,SQLiteUpdateActivity.class);
                startActivity(intent);
            }
        });

    }

}
