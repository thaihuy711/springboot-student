package com.yourname.controller;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StudentForm {
    private String name;
    private String course;
    private Set<SubjectsForm> subjectsForms;
}
