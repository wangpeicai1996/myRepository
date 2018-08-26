package com.pcwang.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user").roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin").roles("ADMIN");
        System.out.println("权限初始化完毕");
//        System.out.println("数据库对象="+dataSource);
//        User.UserBuilder users=User.withDefaultPasswordEncoder();
//        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
//                .withUser(users.username("lisi").password("lisi").roles("USER"))
//                .withUser(users.username("guanliyuan").password("guanliyuan").roles("ADMIN"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register","/index");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')" )
                .antMatchers("/welcome").access("hasRole('USER') or hasRole('ADMIN')")
        .and()
                .authorizeRequests().antMatchers("/login").permitAll()
        .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/welcome")
        .and()
                .logout().logoutSuccessUrl("/index")
        .and()
                .httpBasic();
        System.out.println("路径设置完毕");
    }
}
