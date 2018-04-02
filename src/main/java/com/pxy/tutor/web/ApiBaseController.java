package com.pxy.tutor.web;


import com.pxy.tutor.web.vo.VOBase;

public class ApiBaseController {

    protected Object successJson(Object o) {
        VOBase response = new VOBase();
        response.setSuccess(true);
        response.setMsg("成功");
        response.setData(o);
        response.setResultCode(1);
        return response;
    }

    protected Object successJson() {
        return successJson("");
    }

}
