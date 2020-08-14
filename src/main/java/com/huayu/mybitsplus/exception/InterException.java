package com.huayu.mybitsplus.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String msg="";
        if (e instanceof MyException){
            MyException myException=(MyException) e;
            msg=myException.getMsg();
        }
        //发送短信或邮件通知

        //跳转异常类
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("error",msg);
        modelAndView.setViewName("/layulview/login.html");
        return modelAndView;
    }

}
