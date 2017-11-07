package com.yourname.service;


import com.yourname.controller.PagingObject;
import com.yourname.controller.StudentForm;
import com.yourname.domain.Student;
import com.yourname.domain.Student_;
import com.yourname.model.StudentModel;
import com.yourname.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public PagingObject<StudentModel> getAllStudents(Pageable pageable, String name, String course) {
        log.info("Paging : " + pageable);
        if (pageable.getPageSize() > 500) throw new RuntimeException();

        PagingObject<StudentModel> rs = new PagingObject<>();

        Page<Student> studentPage = studentRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate namePredicate = cb.like(root.get(Student_.name), "%" + name + "%");
            Predicate coursePredicate = cb.like(root.get(Student_.course), "%" + course + "%");
            Predicate idPredicate = cb.equal(root.get(Student_.id), 1);
            /*predicates.add(cb.like(root.get(Student_.name), "%" + name + "%"));
            if(StringUtils.hasText(course)) {
                predicates.add(cb.like(root.get(Student_.course), "%" + course + "%"));
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));*/
            return cb.and(
              cb.or(namePredicate, coursePredicate),
              idPredicate
            );
        }, pageable);

        rs.setTotal(studentPage.getTotalElements());
        rs.setTotalPage(studentPage.getTotalPages());
        rs.setData(studentPage.getContent().stream().map(Student::toModel).collect(Collectors.toList()));

        return rs;
    }
    public Student create(StudentForm form) {
        log.error("Create from : " + form);
        Student s = new Student();
        s.setName(form.getName());
        s.setCourse(form.getCourse());
        return studentRepository.save(s);
    }

    public void insertDataTest() {
        for (long i = 0; i < 1000000; i++) {
            Student s = new Student();
            s.setName("Test 1");
            s.setCourse("Course " + i);
            studentRepository.save(s);
        }
    }

    public void update(Integer id, StudentForm form) {
        Student std = studentRepository.findOne(id);
        std.setName(form.getName());
        std.setCourse(form.getCourse());
        studentRepository.save(std);

    }

    public void delete(Integer id) {
        Student st = studentRepository.findOne(id);
        studentRepository.delete(st);
    }

}
