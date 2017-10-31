package com.yourname.controller;

import lombok.Data;

import java.util.List;

@Data
public class StudentForm {
    private String name;
    private String course;
    private List<SubjectsForm> subjectsForms;
}
