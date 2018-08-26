package com.pcwang.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {


    @RequestMapping(value = {"/index","/","/home"},method = RequestMethod.GET)
    public String index(){
        System.out.println("进入首页了");
        return "index";
    }

    @RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(@PathVariable(value = "logout" ,required = false) String logout,
                        @PathVariable(value = "error" ,required = false) String error, Model model){
        System.out.println("进入登录页面了");
        if(logout!=null){
            System.out.println("退出成功");
            model.addAttribute("msg","退出成功");
        }
        if(error!=null){
            System.out.println("登录出错");
            model.addAttribute("msg","登录出错");
        }
        return "login";
    }

    @RequestMapping(value = "/admin",method ={ RequestMethod.GET,RequestMethod.POST})
    public String admin(){
        System.out.println("进入管理页面了");
        return "admin";
    }

    @RequestMapping(value="/welcome",method ={ RequestMethod.GET,RequestMethod.POST})
    public String welcome(Model model){
        UserDetails user= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        System.out.println("username="+username);
        model.addAttribute("username",username);
        return "welcome";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("退出请求");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
