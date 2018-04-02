package com.pxy.tutor.domain.moudel;

import com.pxy.tutor.domain.entities.MTutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMTutorRepository extends JpaRepository<MTutor,Long> {
    MTutor findByTutorNO(String teachNo);
    MTutor findByTutorNOAndPwd(String no,String pwd);
    Page<MTutor> findByAddressContainsOrderByIdDesc(String city, Pageable pageable);
}
