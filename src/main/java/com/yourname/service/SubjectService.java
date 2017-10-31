package com.yourname.service;

import com.yourname.controller.SubjectsForm;
import com.yourname.domain.Subjects;
import com.yourname.model.SubjectModel;
import com.yourname.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SubjectService {
    private final SubjectsRepository subjectsRepository;
    @Autowired
    public SubjectService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }
    /*public List<Subjects> getAll(){
        return subjectsRepository.findAll();
    }*/
    public List<SubjectModel> getAllSubjects() {
        List<SubjectModel> rs = new ArrayList<>();
        List<Subjects> students = subjectsRepository.findAll();
        for(int i = 0; i < students.size(); i++){
            rs.add(students.get(i).toModel());
        }
        return rs;
    }
    public Subjects createSubject(SubjectsForm form){
        Subjects sb = new Subjects();
        sb.setName_subjects(form.getName_subjects());
        return subjectsRepository.save(sb);
    }
    public void update(Integer id, SubjectsForm form){
        Subjects sbt= subjectsRepository.findOne(id);
        sbt.setName_subjects(form.getName_subjects());
        Subjects update = subjectsRepository.save(sbt);

    }
    public void delete(Integer id){
        subjectsRepository.delete(id);
    }
}
