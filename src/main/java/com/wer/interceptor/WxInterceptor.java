package com.wer.interceptor;

import com.wer.common.BusinessException;
import com.wer.common.GlobalConstant;
import com.wer.enums.ResultCode;
import com.wer.util.DateUtil;
import com.wer.util.StringUtil;
import com.wer.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @description: TODO
 * @package_name: com.wer.interceptor
 * @data: 2019-11-11 17:17
 * @author: Sean
 * @version: V1.0
 */
@Component
public class WxInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(WxInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //getSign
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");

        System.out.println("请求地址>>>>>>>>>>>>>>>>>:"+request.getRequestURI());
        //GlobalConstant.INTER_URIS.

        System.out.println("判断地址是否存在>>>>>>>>>>>>>>>>>"+GlobalConstant.INTER_URIS_STR.contains(request.getRequestURI()));
        if(GlobalConstant.INTER_URIS_STR.contains(request.getRequestURI())){
            if(!StringUtil.isEmpty(sign)){
                //判断传递的时间戳是否为null
                if(!StringUtil.isEmpty(timestamp)){
                    long stamp = Long.valueOf(timestamp);
                    System.out.println("传递的时间戳:"+(stamp + (60)));
                    System.out.println("当前的时间戳:"+Long.valueOf(DateUtil.getCurrentTimeStamp()));
                    //消息有效期24H
                    if((stamp + (60)) >= Long.valueOf(DateUtil.getCurrentTimeStamp())){
                        //*60*24
                        //判断签名是否有效
                        if(sign.equals(WxUtil.getSign(timestamp))){
                            return true;
                        } else {
                            throw new BusinessException(ResultCode.getResult(401));
                        }
                    } else {
                        //时间戳失效
                        throw new BusinessException(ResultCode.getResult(407));
                    }
                }
            }
        }

        //判断所有需要session存在才能访问的地址
        if(GlobalConstant.NEED_SESSION_URI.contains(request.getRequestURI())){
            //说明需要session才能访问
            if(null==request.getSession().getAttribute("userId")){
                //throw new BusinessException("session 已过期");
            }

        }
//        if(handler instanceof HandlerMethod){
//            HandlerMethod hm = (HandlerMethod) handler;
//            System.out.println("当前执行的对象是"+hm.getMethod());
//        }
//
//        HttpSession session = request.getSession();
//        //判断用户是否登录
//        //User user = (User)session.getAttribute("user");
//
//        String username = (String)session.getAttribute("user");
//        //说明用户还没有登录
//        if(null == username){
//            //跳转到登录界面
//            response.sendRedirect(request.getContextPath() + "/login.html");
//            return false;
//        }


        System.out.println("获取上下文："+request.getContextPath());
//        if(1==1){
//            response.sendRedirect( "http://192.168.0.115:9999/wer/auth/authorize");
//            return false;
//        }

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

        //super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        //super.afterCompletion(request, response, handler, ex);
    }
}
