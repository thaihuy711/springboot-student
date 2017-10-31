package com.yourname.service;


import com.yourname.controller.PagingObject;
import com.yourname.controller.StudentForm;
import com.yourname.domain.Student;
import com.yourname.model.StudentModel;
import com.yourname.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public PagingObject<StudentModel> getAllStudents(Integer page, Integer size) {
        log.info("Paging size: " + size + "/Page: " + page);
        if (size > 500) size = 10;
        PageRequest pageRequest = new PageRequest(page, size);

        PagingObject<StudentModel> rs = new PagingObject<>();

        Page<Student> studentPage = studentRepository.findAll(pageRequest);
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
       /* if(st == null){
             studentRepository.notFound().build();
        }*/
        studentRepository.delete(st);
    }

}
