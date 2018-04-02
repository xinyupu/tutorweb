package com.pxy.tutor.web.controller;

import com.pxy.tutor.application.ApiService;
import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.web.ApiBaseController;
import com.pxy.tutor.web.vo.request.RequestStudent;
import com.pxy.tutor.web.vo.request.RequestStudentLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@RestController
public class ApiController extends ApiBaseController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getStudents")
    public Object getStudent(@RequestParam("page") String page,@RequestParam("city")String city) {
        List<MStudent> student = apiService.getStudent(Integer.valueOf(page),city);
        return successJson(student);
    }

    @PostMapping("/registerStudent")
    public Object registerStudent(@RequestBody RequestStudent requestStudent) {
        MStudent student=new MStudent();
        BeanUtils.copyProperties(requestStudent,student);
        MStudent register = apiService.register(student);
        return successJson(register);
    }


    @PostMapping("/studentLogin")
    public Object studentLogin(RequestStudentLogin studentLogin) {
        MStudent student = apiService.getStudent(studentLogin.getNO(), studentLogin.getPwd());
        return successJson(student);
    }

    @GetMapping("/getTutors")
    public Object getTutors(@RequestParam("page")String page,@RequestParam("city")String city){
        List<MTutor> tutors = apiService.getTutors(Integer.parseInt(page), city);
        return successJson(tutors);
    }
}
