package com.wjn.androiddbdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.MyApplication;
import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.greendao.UserInfo;
import com.wjn.androiddbdemo.greendao.UserInfoDao;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendaoupdate);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_greendaoupdate_textview1);
        textView2 = findViewById(R.id.activity_greendaoupdate_textview2);
        textView3 = findViewById(R.id.activity_greendaoupdate_textview3);
        textView = findViewById(R.id.activity_greendaoupdate_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_greendaoupdate_textview1://插入数据
                insertUser();
                break;
            case R.id.activity_greendaoupdate_textview2://查询数据
                List<UserInfo> list=queryUserList();
                StringBuilder sbBuilder = new StringBuilder();
                for(int i=0;i<list.size();i++){
                    UserInfo userInfo=list.get(i);
                    Long id=userInfo.getId();
                    String name=userInfo.getName();
                    String age=userInfo.getAge();
                    sbBuilder.append("ID:" + id + "\n");
                    sbBuilder.append("姓名:" + name + "\n\n");
                    sbBuilder.append("年龄:" + age + "\n\n");
                }
                textView.setText(sbBuilder.toString());
                break;
            case R.id.activity_greendaoupdate_textview3://更新后查询数据
                List<UserInfo> lists=queryUserList();
                StringBuilder sbBuilders = new StringBuilder();
                for(int i=0;i<lists.size();i++){
                    UserInfo userInfo=lists.get(i);
                    Long id=userInfo.getId();
                    String name=userInfo.getName();
                    String age=userInfo.getAge();
                    sbBuilders.append("ID:" + id + "\n");
                    sbBuilders.append("姓名:" + name + "\n\n");
                    sbBuilders.append("年龄:" + age + "\n\n");
                }
                textView.setText(sbBuilders.toString());
                break;
            default:
                break;
        }
    }

    /**
     * 插入多条条记录
     */

    public void insertUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setName("张三");
        userInfo.setAge("29");

        UserInfo userInfo1=new UserInfo();
        userInfo1.setName("李四");
        userInfo1.setAge("39");

        UserInfo userInfo2=new UserInfo();
        userInfo2.setName("旺旺");
        userInfo2.setAge("19");

        UserInfo userInfo3=new UserInfo();
        userInfo3.setName("王伟");
        userInfo3.setAge("59");

        List<UserInfo> list=new ArrayList<>();
        list.add(userInfo);
        list.add(userInfo1);
        list.add(userInfo2);
        list.add(userInfo3);

        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        userInfoDao.insertInTx(list);
    }

    /**
     * 查询数据列表 姓名=“张三”
     */

    public List<UserInfo> queryUserList() {
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        QueryBuilder<UserInfo> qb = userInfoDao.queryBuilder();
        List<UserInfo> list = qb.list();
        return list;
    }

}
