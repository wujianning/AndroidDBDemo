package com.wjn.androiddbdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.androiddbdemo.R;
import com.wjn.androiddbdemo.tableexists.User;
import com.wjn.androiddbdemo.tableexists.UserService;
import com.wjn.androiddbdemo.utils.ui.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class ExistsSQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private List<User> list;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existssqlite);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        //根据状态栏颜色来决定 状态栏背景 用黑色还是白色 true:是否修改状态栏字体颜色
        StatusBarUtil.setStatusBarMode(this, false, false, R.color.colorPrimary);
        textView1 = findViewById(R.id.activity_existssqlite_textview1);
        textView2 = findViewById(R.id.activity_existssqlite_textview2);
        textView3 = findViewById(R.id.activity_existssqlite_textview3);
        textView4 = findViewById(R.id.activity_existssqlite_textview4);
        textView5 = findViewById(R.id.activity_existssqlite_textview5);
        textView6 = findViewById(R.id.activity_existssqlite_textview6);
        textView = findViewById(R.id.activity_existssqlite_textview);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        initList();
    }

    /**
     * 初始化List方法
     */

    private void initList() {
        list = new ArrayList<User>();

        User u1 = new User();
        u1.setId("1");
        u1.setName("张三");
        u1.setPath("http://www.com/11111.png");

        User u2 = new User();
        u2.setId("2");
        u2.setName("李四");
        u2.setPath("http://www.com/22222.png");

        User u3 = new User();
        u3.setId("3");
        u3.setName("王五");
        u3.setPath("http://www.com/33333.png");

        User u4 = new User();
        u4.setId("4");
        u4.setName("赵六");
        u4.setPath("http://.com/44444.png");

        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_existssqlite_textview1://插入数据
                add();
                break;
            case R.id.activity_existssqlite_textview2://删除一条数据
                User u = new User();
                u.setId("1");
                u.setName("张三");
                u.setPath("http://www.com/11111.png");
                del(u);
                break;
            case R.id.activity_existssqlite_textview3://删除全部用户
                delAll();
                break;
            case R.id.activity_existssqlite_textview4://修改一条数据
                User u1 = new User();
                u1.setId("2");
                u1.setName("李四修改后的名字");
                u1.setPath("http://www.com/22222.png");
                update(u1);
                break;
            case R.id.activity_existssqlite_textview5://查询数据库所有数据
                selectAll();
                break;
            case R.id.activity_existssqlite_textview6://查询数据库一条数据
                select("3");
                break;
            default:
                break;
        }
    }

    /**
     * 插入数据
     */

    public void add() {
        if (list.size() > 0) {//有数据
            new UserService(ExistsSQLiteActivity.this).addUser(list);
        }
    }

    /**
     * 删除一条数据
     */

    public void del(final User user) {
        if (null != user) {
            new UserService(ExistsSQLiteActivity.this).delUesr(user);
        }
    }

    /**
     * 删除全部用户
     */

    public void delAll() {
        new UserService(ExistsSQLiteActivity.this).delAllUesr();
    }

    /**
     * 修改一条数据
     */

    public void update(final User user) {
        if (null != user) {//对象正确
            new UserService(ExistsSQLiteActivity.this).updateUesr(user);
        }
    }

    /**
     * 查询数据库所有数据
     */

    public void selectAll() {
        List<User> list = new UserService(ExistsSQLiteActivity.this).getAllUser();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getId();
            String name = list.get(i).getName();
            String path = list.get(i).getPath();
            sb.append("ID:" + id + "\n");
            sb.append("姓名:" + name + "\n");
            sb.append("路径:" + path + "\n\n");
        }
        textView.setText(sb.toString());
    }

    /**
     * 查询数据库一条数据
     */

    public void select(final String id) {
        if (null != id) {
            User user = new UserService(ExistsSQLiteActivity.this).getUser(id);
            String ids = user.getId();
            String names = user.getName();
            String paths = user.getPath();
            StringBuilder sb = new StringBuilder();
            sb.append("ID:" + ids + "\n");
            sb.append("姓名:" + names + "\n");
            sb.append("路径:" + paths + "\n\n");
            textView.setText(sb.toString());
        }
    }


}
