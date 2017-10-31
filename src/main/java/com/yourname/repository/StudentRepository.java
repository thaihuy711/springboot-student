package com.yourname.repository;

        import com.yourname.domain.Student;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
