package com.pxy.tutor.domain.moudel;

import com.pxy.tutor.domain.entities.MStudent;
import com.pxy.tutor.domain.entities.MTutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMStudentRepository extends JpaRepository<MStudent,Long> {
}
