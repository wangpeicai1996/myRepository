package com.example.app.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.User;

/**
 * 此接口继承JpaRepsitory类，可以试想调用hibernate的封装方法
 * 还可以在此接口中自定义其他方法，可以自定义实现
 * @author Administrator
 *
 */
public interface IUserRepsitory extends JpaRepository<User,Integer>{

}
