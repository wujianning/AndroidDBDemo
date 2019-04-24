package com.wjn.androiddbdemo.activity.greendao;

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

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView;
    private String string="123456";
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_greendao_textview1);
        textView2 = findViewById(R.id.activity_greendao_textview2);
        textView3 = findViewById(R.id.activity_greendao_textview3);
        textView4 = findViewById(R.id.activity_greendao_textview4);
        textView = findViewById(R.id.activity_greendao_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        id=Long.parseLong(string);

    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_greendao_textview1://增
                insertUser();
                break;
            case R.id.activity_greendao_textview2://删
                deleteUser();
                break;
            case R.id.activity_greendao_textview3://改
                updateUser();
                break;
            case R.id.activity_greendao_textview4://查
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
            default:
                break;
        }
    }

    /**
     * 插入一条记录
     */

    public void insertUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        userInfo.setName("张三");
        userInfo.setAge("29");
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        userInfoDao.insert(userInfo);
    }

    /**
     * 删除一条记录
     */

    public void deleteUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        userInfo.setName("张三");
        userInfo.setAge("29");
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        userInfoDao.delete(userInfo);
    }

    /**
     * 更新一条记录
     */

    public void updateUser() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        userInfo.setName("张三更新");
        userInfo.setAge("29");
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        userInfoDao.update(userInfo);
    }

    /**
     * 查询数据列表
     */

    public List<UserInfo> queryUserList() {
        UserInfoDao userInfoDao= MyApplication.getDaoInstant().getUserInfoDao();
        QueryBuilder<UserInfo> qb = userInfoDao.queryBuilder();
        List<UserInfo> list = qb.list();
        return list;
    }

}
