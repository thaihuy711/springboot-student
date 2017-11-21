package com.yourname.controller;

import com.yourname.domain.Student;
import com.yourname.model.StudentModel;
import com.yourname.service.StudentService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(AbstractController.API + "/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public PagingObject<StudentModel> getAllStudents(Pageable pageable,
                                                     @RequestParam(required = false, defaultValue = "") String name,
                                                     @RequestParam(required = false, defaultValue = "") String course) {
        return studentService.getAllStudents(pageable, name, course);
    }

    @GetMapping("/{id}")
    public StudentModel getStudent(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student create(@RequestBody StudentForm form) {
        return studentService.create(form);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody StudentForm form) {
        studentService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }

}
