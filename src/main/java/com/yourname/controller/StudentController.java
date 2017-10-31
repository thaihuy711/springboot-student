package com.yourname.controller;

import com.yourname.domain.Student;
import com.yourname.model.StudentModel;
import com.yourname.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public PagingObject<StudentModel> getAllStudents(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size) {
        return studentService.getAllStudents(page, size);
    }

    @PostMapping
    public Student create(@RequestBody StudentForm form) {
        return studentService.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody StudentForm form) {
        studentService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }

    @GetMapping("/dataTest")
    public void insertDataTest() {
        studentService.insertDataTest();
    }
}
