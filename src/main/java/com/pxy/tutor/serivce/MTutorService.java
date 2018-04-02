package com.pxy.tutor.serivce;

import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.domain.moudel.IMTutorRepository;
import com.pxy.tutor.web.filter.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MTutorService {

    @Autowired
    public IMTutorRepository tutorRepository;

    public MTutor saveTutor(MTutor mTutor) {
        MTutor tutor = this.tutorRepository.findByTutorNO(mTutor.getTutorNO());
        if (tutor == null) {
            return this.tutorRepository.save(mTutor);
        }
        return tutor;
    }

    public void updateTutor(MTutor mTutor) {
        if (mTutor.getId() != 0) {
            this.tutorRepository.save(mTutor);
        }
    }

    public List<MTutor> getTutor(int page, String city) {
        Pageable pageable = new PageRequest(page, 10);
        List<MTutor> content = tutorRepository.findByAddressContainsOrderByIdDesc(city, pageable).getContent();
        if (content.size() == 0) throw new WebException("暂无该城市数据");
        return content;
    }

    public MTutor findByTutorNOAndPwd(String no, String pwd) {
        MTutor byTutorNOAndPwd = tutorRepository.findByTutorNOAndPwd(no, pwd);
        if (byTutorNOAndPwd == null) throw new WebException("编号或密码错误");
        return byTutorNOAndPwd;
    }

    public  MTutor findByTutorNO(String teachNo){
        return tutorRepository.findByTutorNO(teachNo);
    }

    public List<MTutor> getAll() {
        return tutorRepository.findAll();
    }
}
