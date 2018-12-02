package com.pcwang.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private  String username;

    private String password;

    private int age;

    //private List<Role> roles;

    private List<Role> roles;

    public User() {
        System.out.println("无参构造user");
    }

    public User(String username, String password, int age, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.roles = roles;
        System.out.println("有参构造user");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
