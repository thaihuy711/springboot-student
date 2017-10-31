package com.yourname.controller;

import com.yourname.domain.Subjects;
import com.yourname.model.SubjectModel;
import com.yourname.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/subjects")

public class SubjectsController {
    private final SubjectService subjectService;

    public SubjectsController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping
    public List<SubjectModel> getAllSubjects(){
        return subjectService.getAllSubjects();
    }
    @PostMapping
    public Subjects createSubjects(@RequestBody SubjectsForm form){
        return subjectService.createSubject(form);
    }
    @PutMapping
    public void update(@PathVariable Integer id, @RequestBody SubjectsForm form){
        subjectService.update(id,form);
    }
    @DeleteMapping
    public void delete(@PathVariable Integer id){
        subjectService.delete(id);
    }
}
