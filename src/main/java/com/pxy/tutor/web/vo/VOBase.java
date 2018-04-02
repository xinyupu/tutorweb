package com.pxy.tutor.web.vo;

import lombok.Data;

@Data
public class VOBase<T>{
    private boolean isSuccess;
    private String msg;
    private int resultCode;
    private T data;
}
