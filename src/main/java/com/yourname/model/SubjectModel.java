package com.yourname.model;

public class SubjectModel {
    private Integer id;

    private String name_subjects;
    private StudentModel subjectModel;

    public StudentModel getSubjectModel() {
        return subjectModel;
    }

    public void setSubjectModel(StudentModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_subjects() {
        return name_subjects;
    }

    public void setName_subjects(String name_subjects) {
        this.name_subjects = name_subjects;
    }
}
