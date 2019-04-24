package com.wjn.androiddbdemo.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class UserInfo {

    @Id(autoincrement = true)
    private Long id;//主键 Long型，可以通过@Id(autoincrement = true)设置自增长
    private String name;
    private String age;
    private String githubdes;
    @Generated(hash = 466573838)
    public UserInfo(Long id, String name, String age, String githubdes) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.githubdes = githubdes;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGithubdes() {
        return this.githubdes;
    }
    public void setGithubdes(String githubdes) {
        this.githubdes = githubdes;
    }
    
    
}
