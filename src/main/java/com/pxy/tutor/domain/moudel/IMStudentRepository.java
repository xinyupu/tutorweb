package com.pxy.tutor.domain.moudel;

import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMStudentRepository extends JpaRepository<MStudent,Long> {
    MStudent findByStudentNO(String studentNO );

    MStudent findByStudentNOAndPwd(String studentNo,String pwd);

    Page<MStudent> findByAreaTeachContains(String city, Pageable pageable);

}
