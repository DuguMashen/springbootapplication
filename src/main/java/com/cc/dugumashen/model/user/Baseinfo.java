package com.cc.dugumashen.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Baseinfo {

    private String id;


    private String name;


    private String gender;


    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }


    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public Date getBirthday() {
        return birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Date getCreatetime() {
        return createtime;
    }


    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}