package com.yourname.service;


import com.yourname.controller.PagingObject;
import com.yourname.controller.StudentForm;
import com.yourname.controller.SubjectsForm;
import com.yourname.domain.Student;
import com.yourname.domain.Student_;
import com.yourname.domain.Subjects;
import com.yourname.model.StudentModel;
import com.yourname.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
        if (pageable.getPageSize() > 500) throw new RuntimeException("Page size too big");

        PagingObject<StudentModel> rs = new PagingObject<>();
        Page<Student> studentPage;
        if (StringUtils.hasText(name) || StringUtils.hasText(course)) {
            studentPage = studentRepository.findAll((root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(name)) {
                    predicates.add(cb.like(root.get(Student_.name), "%" + name + "%"));
                }
                if (StringUtils.hasText(course)) {
                    predicates.add(cb.like(root.get(Student_.course), "%" + course + "%"));
                }

                return cb.or(predicates.toArray(new Predicate[predicates.size()]));

            }, pageable);
        } else {
            studentPage = studentRepository.findAll(pageable);
        }


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
        if (std.getSubjects() != null) std.getSubjects().clear();
        for (SubjectsForm subjectsForm : form.getSubjectsForms()) {
            Subjects subjects = new Subjects();
            subjects.setStudent(std);
            subjects.setName_subjects(subjectsForm.getName_subjects());
            System.out.println(subjects.getName_subjects());
            std.getSubjects().add(subjects);
        }

        System.out.println(std.getSubjects().size());

        studentRepository.save(std);
    }

    public void delete(Integer id) {
        Student st = studentRepository.findOne(id);
        studentRepository.delete(st);
    }

    public StudentModel getStudent(Integer id) {
        return studentRepository.getOne(id).toModel();
    }

}
