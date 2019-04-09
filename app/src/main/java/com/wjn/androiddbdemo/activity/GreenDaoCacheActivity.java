package com.wjn.androiddbdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.MyApplication;
import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.greendao.DaoSession;
import com.wjn.androiddbdemo.greendao.UserInfo;
import com.wjn.androiddbdemo.greendao.UserInfoDao;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoCacheActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView11;
    private TextView textView22;
    private TextView textView33;
    private UserInfo userInfo;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendaocache);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_greendaocache_textview1);
        textView2 = findViewById(R.id.activity_greendaocache_textview2);
        textView3 = findViewById(R.id.activity_greendaocache_textview3);
        textView11 = findViewById(R.id.activity_greendaocache_textview11);
        textView22 = findViewById(R.id.activity_greendaocache_textview22);
        textView33 = findViewById(R.id.activity_greendaocache_textview33);
        textView = findViewById(R.id.activity_greendaocache_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView11.setOnClickListener(this);
        textView22.setOnClickListener(this);
        textView33.setOnClickListener(this);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_greendaocache_textview1://1.查
                userInfo=queryUserList();
                String result="ID："+userInfo.getId()+"  姓名："+userInfo.getName()+"  "+"年龄："+userInfo.getAge();
                textView.setText(result);
                break;
            case R.id.activity_greendaocache_textview2://2.改
                if(null!=userInfo){
                    //修改
                    userInfo.setAge("1111");
                    userInfo.setName("张三");
                    //将修改的内容更新到数据库
                    UserInfoDao userInfoDao=MyApplication.getDaoInstant().getUserInfoDao();
                    userInfoDao.update(userInfo);
                }
                break;
            case R.id.activity_greendaocache_textview3://3.查
                UserInfo userInfo=queryUserList();
                String results="ID(新)："+userInfo.getId()+"  姓名(新)："+userInfo.getName()+"  "+"年龄(新)："+userInfo.getAge();
                textView.setText(results);
                break;
            case R.id.activity_greendaocache_textview11://1.查(清缓存)

                break;
            case R.id.activity_greendaocache_textview22://2.改(清缓存)

                break;
            case R.id.activity_greendaocache_textview33://3.查(清缓存)

                break;
            default:
                break;
        }
    }

    /**
     * 查询一条数据列表 姓名=“张三”
     */

    public UserInfo queryUserList() {
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        QueryBuilder<UserInfo> qb = userInfoDao.queryBuilder();
        UserInfo userInfo = qb.where(UserInfoDao.Properties.Name.eq("张三")).unique();
        return userInfo;
    }

}
