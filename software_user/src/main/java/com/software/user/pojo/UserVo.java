package com.software.user.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class UserVo {



    private String id;

    private String account;//账号

    private String name;//姓名


    private int sex;//性别 1男  2女

    private int age;//年龄

    private Date createtime;
    private Date updatetime;



    private String collegeName;//学院名称



    public UserVo() {
    }

    public UserVo(String id, String account, String name, Integer sex, Integer age, Timestamp createtime, Timestamp updatetime, String collegeName) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.collegeName = collegeName;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
