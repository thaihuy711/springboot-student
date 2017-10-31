package com.yourname.model;

import com.yourname.controller.SubjectsForm;
import com.yourname.domain.Subjects;

import java.util.List;
import java.util.Set;

public class StudentModel {
    private Integer id;
    private String name;
    private String course;
    private Set<SubjectsForm> subjects;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Set<SubjectsForm> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectsForm> subjects) {
        this.subjects = subjects;
    }
}
