package com.pxy.tutor.domain.moudel;

import com.pxy.tutor.domain.entities.MTutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMTutorRepository extends JpaRepository<MTutor,Long> {
    MTutor findByTutorNO(String teachNo);
}
