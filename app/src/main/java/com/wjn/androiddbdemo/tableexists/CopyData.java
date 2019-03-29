package com.wjn.androiddbdemo.tableexists;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CopyData {

    private static final String DBNAME = "MySqlite.db";
    private Context context;

    /**
     * 构造方法
     * */

    public CopyData(Context context) {
        super();
        this.context = context;
    }

    /**
     * 判断数据库文件是否存在的方法
     */

    public boolean isExsit() {
        File dbfile = new File(context.getFilesDir(), DBNAME);
        return dbfile.exists();
    }

    /**
     * 拷贝数据库文件的方法
     */

    public void copyDatabase() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(DBNAME);
        File dbfile = new File(context.getFilesDir(), DBNAME);
        FileOutputStream outputStream = new FileOutputStream(dbfile);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }


}
