package com.pxy.tutor.serivce;

import com.pxy.tutor.domain.entities.MTutor;
import com.pxy.tutor.domain.moudel.IMTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MTutorService {

    @Autowired
    public IMTutorRepository tutorRepository;

    public void saveTutor(MTutor mTutor) {
        MTutor tutor = this.tutorRepository.findByTutorNO(mTutor.getTutorNO());
        if (tutor == null) {
            this.tutorRepository.save(mTutor);
        }

    }
}
