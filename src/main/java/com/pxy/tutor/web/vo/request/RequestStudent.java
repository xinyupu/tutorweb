package com.pxy.tutor.web.vo.request;

import lombok.Data;


@Data
public class RequestStudent {
    private String studentNO;

    private String name;

    private String pwd;

    private String subjectTeach;

    private String requireTeach;

    private String  teachingTime;

    private String areaTeach;

    private String headUrl;

    private String studentCondition;


    private String studentRequireDesc;

}
