package com.pcwang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class WebInitializer implements WebApplicationInitializer  {

    public void onStartup(ServletContext servletContext) throws ServletException {
        //初始化spring容器
        initSpringConfig(servletContext);
        //初始化springmvc
        initSpringMvcConfig(servletContext);
        //初始化过滤器
        initFilterConfig(servletContext);

    }

    protected void initSpringConfig(ServletContext servletContext){
        AnnotationConfigWebApplicationContext springConfig=new AnnotationConfigWebApplicationContext();
        springConfig.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(springConfig));
        System.out.println("spring容器初始化了");
    }
    protected void initSpringMvcConfig(ServletContext servletContext){
        AnnotationConfigWebApplicationContext springMvcConfig=new AnnotationConfigWebApplicationContext();
        springMvcConfig.register(DispatcherConfig.class);
        ServletRegistration.Dynamic dispatcher=servletContext.addServlet("dispatcherServlet",new DispatcherServlet(springMvcConfig));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        System.out.println("springMvc初始化完毕");
    }
    protected void initFilterConfig(ServletContext servletContext){
        FilterRegistration.Dynamic encodingFilter=servletContext.addFilter("encodingFilter",CharacterEncodingFilter.class);
        encodingFilter.setAsyncSupported(true);
        encodingFilter.setInitParameter("encoding","UTF-8");
        encodingFilter.setInitParameter("forceEncoding","true");
        encodingFilter.addMappingForUrlPatterns(null,true,"/*");
        System.out.println("编码过滤器初始化完毕");
    }


}
