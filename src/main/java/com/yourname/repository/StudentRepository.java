package com.yourname.repository;

import com.yourname.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository
        extends JpaRepository<Student, Integer>,
            JpaSpecificationExecutor<Student> {
//        Page<Student> findByNameLikeOrCourseLike(String name, String course, Pageable pageable);
    Page<Student> findByNameLike(String name,  Pageable pageable);
}
