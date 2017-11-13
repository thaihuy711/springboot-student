package com.yourname.controller;

import com.yourname.domain.Subjects;

public class SubjectsForm {
    private String name_subjects;

    public String getName_subjects() {
        return name_subjects;
    }

    public void setName_subjects(String name_subjects) {
        this.name_subjects = name_subjects;
    }

    public Subjects toSubject()
    {
        Subjects subjects = new Subjects();
        subjects.setName_subjects(name_subjects);
        return  subjects;
    }
}
