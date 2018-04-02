package com.pxy.tutor.web.vo.request;

import lombok.Data;


@Data
public class RequestTutorRegister {

    private String tutorNO;

    private String name;

    private String pwd;

    private String birthday;

    private String education;

    private String canTeach;

    private String majorTeach;

    private String descTeach;

    private String headUrl;

    private String address;


}
