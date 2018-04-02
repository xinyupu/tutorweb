package com.pxy.tutor.web.controller;

import com.pxy.tutor.application.ApiService;
import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.web.ApiBaseController;
import com.pxy.tutor.web.vo.request.RequestStudent;
import com.pxy.tutor.web.vo.request.RequestStudentLogin;
import com.pxy.tutor.web.vo.request.RequestTutorLogin;
import com.pxy.tutor.web.vo.request.RequestTutorRegister;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController extends ApiBaseController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getStudents")
    public Object getStudents(@RequestParam("page") String page, @RequestParam("city") String city) {
        List<MStudent> student = apiService.getStudent(Integer.valueOf(page), city);
        return successJson(student);
    }

    @PostMapping("/registerStudent")
    public Object registerStudent(@RequestBody RequestStudent requestStudent) {
        MStudent student = new MStudent();
        BeanUtils.copyProperties(requestStudent, student);
        MStudent register = apiService.registerStudent(student);
        return successJson(register);
    }


    @PostMapping("/studentLogin")
    public Object studentLogin(@RequestBody RequestStudentLogin studentLogin) {
        MStudent student = apiService.getStudent(studentLogin.getNo(), studentLogin.getPwd());
        return successJson(student);
    }

    @GetMapping("/getTutors")
    public Object getTutors(@RequestParam("page") String page, @RequestParam("city") String city) {
        List<MTutor> tutors = apiService.getTutors(Integer.parseInt(page), city);
        return successJson(tutors);
    }


    @GetMapping("/getTutor")
    public Object getTutor(@RequestParam("no") String no) {
        MTutor tutors = apiService.getTutor(no);
        return successJson(tutors);
    }

    @GetMapping("/getStudent")
    public Object getStudent(@RequestParam("no") String no) {
        MStudent student = apiService.getStudent(no);
        return successJson(student);
    }


    @PostMapping("/registerTutor")
    public Object registerTutor(@RequestBody RequestTutorRegister requestTutor) {
        MTutor tutor = new MTutor();
        BeanUtils.copyProperties(requestTutor, tutor);
        MTutor register = apiService.registerTutor(tutor);
        return successJson(register);
    }


    @PostMapping("/tutorLogin")
    public Object tutorLogin(@RequestBody RequestTutorLogin tutorLogin) {
        MTutor tutor = apiService.getTutor(tutorLogin.getNo(), tutorLogin.getPwd());
        return successJson(tutor);
    }
}
