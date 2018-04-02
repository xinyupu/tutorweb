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
    public  MStudent  getStudent(String no) {
        MStudent byStudentNO = studentService.findByStudentNO(no);
        if (byStudentNO==null)throw new WebException("编号不存在");
        return byStudentNO;
    }

    public MStudent getStudent(String NO, String pwd) {
        return studentService.findByStudentNOAndPwd(NO, pwd);
    }

    public MStudent registerStudent(MStudent student) {
        MStudent byStudentNO = studentService.findByStudentNO(student.getStudentNO());
        if (byStudentNO != null) throw new WebException("该编号已被注册");
        return studentService.saveStudent(student);
    }

    public MTutor registerTutor(MTutor tutor) {
        MTutor byTutorNO = tutorService.findByTutorNO(tutor.getTutorNO());
        if (byTutorNO != null) throw new WebException("该编号已被注册");
        return tutorService.saveTutor(tutor);
    }

    public List<MTutor> getTutors(int page,String city) {
        return tutorService.getTutor(page,city);
    }

    public MTutor getTutor(String NO, String pwd) {
        return tutorService.findByTutorNOAndPwd(NO, pwd);
    }

    public MTutor getTutor(String NO) {
        MTutor byTutorNO = tutorService.findByTutorNO(NO);
        if (byTutorNO==null)throw new WebException("编号不存在");
        return byTutorNO;
    }

}
