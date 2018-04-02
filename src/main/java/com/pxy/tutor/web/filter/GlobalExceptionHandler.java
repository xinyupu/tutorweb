package com.pxy.tutor.web.filter;

import com.pxy.tutor.web.vo.VOBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        VOBase result = new VOBase();
        result.setMsg(e.getMessage());
        result.setSuccess(false);
        result.setResultCode(0);
        return result;
    }
}
