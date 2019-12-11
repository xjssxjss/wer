package com.wer.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用于取得当前用户登录时的上下文环境，一般用于获取当前登录的用户
 * @package_name: com.wer.util
 * @data: 2019-12-9 11:19
 * @author: Sean
 * @version: V1.0
 */
public class ContextUtil {
    //获取当前登录用户名
    public static String getCurrentUser(){
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println("session:"+request.getSession().getAttribute("userId"));
        return (null == request.getSession().getAttribute("userId")) ? "":request.getSession().getAttribute("userId")+"";
    }

}
