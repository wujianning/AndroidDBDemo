package com.wjn.androiddbdemo.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class People {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String height;
    private String weight;
    @Generated(hash = 516943684)
    public People(Long id, String name, String height, String weight) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
    @Generated(hash = 1406030881)
    public People() {
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
    public String getHeight() {
        return this.height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

}
