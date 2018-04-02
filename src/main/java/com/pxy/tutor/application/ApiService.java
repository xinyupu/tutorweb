package com.pxy.tutor.application;

import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.serivce.MStudentService;
import com.pxy.tutor.serivce.MTutorService;
import com.pxy.tutor.web.filter.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    public MStudentService studentService;

    @Autowired
    public MTutorService tutorService;

    public List<MStudent> getStudent(int page,String city) {
        return studentService.getStudents(page,city);
    }

    public MStudent getStudent(String NO, String pwd) {
        return studentService.findByStudentNOAndPwd(NO, pwd);
    }

    public MStudent register(MStudent student) {
        MStudent byStudentNO = studentService.findByStudentNO(student.getStudentNO());
        if (byStudentNO != null) throw new WebException("该编号已被注册");
        return studentService.saveStudent(student);
    }

    public List<MTutor> getTutors(int page,String city) {
        return tutorService.getTutor(page,city);
    }

    public MTutor getTutor(String NO, String pwd) {
        return tutorService.findByTutorNOAndPwd(NO, pwd);
    }

}
