package com.pcwang.serializable;

import com.pcwang.entity.Role;
import com.pcwang.entity.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestSerializable {
    public static void main(String[] args) throws IOException {
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role("001","管理员","1");
        Role role2 = new Role("002","普通用户","1");
        Role role3 = new Role("003","普通用户","2");
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        //序列化
        System.out.println("序列化前");
        serializableOut(new User("张三","123",12,roles));
        System.out.println("序列化后");
        //反序列化
        System.out.println("反序列化前");
        User user = serializableIn();
        System.out.println("user = " + user);
        System.out.println("反序列化后");
    }

    /**
     * 序列化
     * @param obj
     * @throws IOException
     */
    public static void serializableOut(Object obj) throws IOException {
        ObjectOutputStream oos = null;
        try {
             oos =  new ObjectOutputStream(new FileOutputStream("output/user.txt"));
            //将对象写出
             oos.writeObject(obj);
             oos.writeObject("hello world");
             oos.writeObject(new Date());
             oos.writeObject(2);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            oos.close();
        }
    }

    /**
     * 反序列化
     * @return
     * @throws IOException
     */
    public static User serializableIn() throws IOException {
        ObjectInputStream ois = null;
        User user = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("output/user.txt"));
            //读入对象
            user = (User) ois.readObject();
            String str = (String)ois.readObject();
            System.out.println("str = " + str);
            Date date = (Date) ois.readObject();
            System.out.println("date = " + date);
            int num = (int) ois.readObject();
            System.out.println("num = " + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
        return user;
    }

}
