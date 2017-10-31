package com.yourname.repository;

import com.yourname.domain.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
}
