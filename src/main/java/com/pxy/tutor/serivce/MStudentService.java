package com.pxy.tutor.serivce;

import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.domain.moudel.IMStudentRepository;
import com.pxy.tutor.domain.moudel.IMTutorRepository;
import com.pxy.tutor.web.filter.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MStudentService {
    @Autowired
    public IMStudentRepository studentRepository;

    public MStudent saveStudent(MStudent student) {
        MStudent mStudent = this.studentRepository.findByStudentNO(student.getStudentNO());
        if (mStudent == null) {
            return this.studentRepository.save(student);
        }
        return mStudent;
    }

    public MStudent findByStudentNO(String studentNO) {
        return this.studentRepository.findByStudentNO(studentNO);

    }

    public List<MStudent> getStudents(int page,String city) {
        Pageable pageable = new PageRequest(page, 10);
        Page<MStudent> all = studentRepository.findByAreaTeachContains(city,pageable);
        if (all.getContent().size()==0)throw new WebException("暂无该城市数据");
        return all.getContent();
    }

    public MStudent findByStudentNOAndPwd(String studentNo, String pwd) {
        MStudent byStudentNOAndPwd = this.studentRepository.findByStudentNOAndPwd(studentNo, pwd);
        if (byStudentNOAndPwd == null) throw new WebException("编号或用户名错误");
        return byStudentNOAndPwd;
    }
}
