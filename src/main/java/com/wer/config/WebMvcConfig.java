package com.wer.config;

/**
 * @description: TODO
 * @package_name: com.wer.config
 * @data: 2019-11-28 10:16
 * @author: Sean
 * @version: V1.0
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: Eric
 **/
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        registry.addResourceHandler("/static/**.js").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/**.css").addResourceLocations("classpath:/static/");
        //其他静态资源
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}