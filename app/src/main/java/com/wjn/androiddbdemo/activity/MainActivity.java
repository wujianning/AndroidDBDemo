package com.wjn.androiddbdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.activity.greendao.GreenDaoActivity;
import com.wjn.androiddbdemo.activity.greendao.GreenDaoComplexActivity;
import com.wjn.androiddbdemo.activity.greendao.GreenDaoUpdateActivity;
import com.wjn.androiddbdemo.activity.sqlite.SQLiteActivity;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化各种View
     * */

    private void initView(){
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false,false, R.color.colorPrimary);

        TextView textView1=findViewById(R.id.activity_main_textview1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

        TextView textView2=findViewById(R.id.activity_main_textview2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, GreenDaoActivity.class);
                startActivity(intent);
            }
        });

        TextView textView3=findViewById(R.id.activity_main_textview3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, GreenDaoComplexActivity.class);
                startActivity(intent);
            }
        });

        TextView textView4=findViewById(R.id.activity_main_textview4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, GreenDaoUpdateActivity.class);
                startActivity(intent);
            }
        });

    }

}
