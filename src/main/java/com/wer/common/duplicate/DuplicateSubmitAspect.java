package com.wer.common.duplicate;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component
@Slf4j
public class DuplicateSubmitAspect {
    public static final String DUPLICATE_TOKEN_KEY = "duplicate_token_key";

    @Pointcut("execution(public * com.wer.controller..*(..))")

    public void webLog() {
    }

    @Before("webLog() && @annotation(token)")
    public void before(final JoinPoint joinPoint, DuplicateSubmitToken token) {
        if (token != null) {
            HttpServletRequest request = null;

            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            boolean isSaveSession = token.save();
            if (isSaveSession) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null == t) {
                    String uuid = UUID.randomUUID().toString();
                    request.getSession().setAttribute(key.toString(), uuid);
                } else {
                    throw new DuplicateSubmitException("重复请求！");
                }
            }

        }
    }

    /**
     * 获取重复提交key-->duplicate_token_key+','+请求方法名
     *
     * @param joinPoint
     * @return
     */
    public String getDuplicateTokenKey(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key = new StringBuilder(DUPLICATE_TOKEN_KEY);
        key.append(",").append(methodName);
        return key.toString();
    }

    @AfterReturning("webLog() && @annotation(token)")
    public void doAfterReturning(JoinPoint joinPoint, DuplicateSubmitToken token) {
        // 处理完请求，返回内容
        if (token != null) {
            HttpServletRequest request = null;
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            boolean isSaveSession = token.save();
            if (isSaveSession) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null != t) {
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                }
            }
        }
    }

    /**
     * 异常
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e, DuplicateSubmitToken token) {
        if (null != token
                && e instanceof DuplicateSubmitException == false) {
            //处理处理重复提交本身之外的异常
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            boolean isSaveSession = token.save();
            //获得方法名称
            if (isSaveSession) {
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null != t) {
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                }
            }
        }
    }
}
