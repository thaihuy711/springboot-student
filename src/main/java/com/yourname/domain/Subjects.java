package com.yourname.domain;

import com.yourname.controller.SubjectsForm;
import com.yourname.model.SubjectModel;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String name_subjects;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    public SubjectModel toModel(){
        SubjectModel sb = new SubjectModel();
        sb.setId(id);
        sb.setName_subjects(name_subjects);
        if(student != null) sb.setSubjectModel(student.toModel());
        return sb;
    }

    public Subjects() {
    }

    public String getName_subjects() {
        return name_subjects;
    }

    public void setName_subjects(String name_subjects) {
        this.name_subjects = name_subjects;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SubjectsForm toSubjectsForm() {
        SubjectsForm rs = new SubjectsForm();
        rs.setName_subjects(name_subjects);

        return rs;
    }
}
