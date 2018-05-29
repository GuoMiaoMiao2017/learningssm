package com.guomiaomiao.learningssm.pojo;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer sex;

    private Byte isVip;

    public User(Integer id, String name, String password, Integer sex, Byte isVip) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.isVip = isVip;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Byte getIsVip() {
        return isVip;
    }

    public void setIsVip(Byte isVip) {
        this.isVip = isVip;
    }
}