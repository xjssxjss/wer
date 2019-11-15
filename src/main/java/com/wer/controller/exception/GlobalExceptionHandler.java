package com.wer.controller.exception;

import com.wer.common.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)//指定拦截的异常
    public String errorHandler(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Exception e) throws Exception{
        //声明返回的错误对象
        Map<String,Object> modelMap = new HashMap<>();
        logger.info("message>>"+e.getMessage());
        modelMap.put("errMsg",e.getMessage());

        model.addAttribute("error",modelMap);
        return "error/error";
    }
}
