package com.yourname.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yourname.model.StudentModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String course;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Subjects> subjects;

    public StudentModel toModel(){
        StudentModel st = new StudentModel();
        st.setId(id);
        st.setName(name);
        st.setCourse(course);
        //st.setSubjects(getSubjects().stream().map(Subjects::toSubjectsForm).collect(Collectors.toSet()));
        return st;
    }

    @Override
    public String toString() {
        String result = String.format("Student[id=%d, name='%s', course='%s']%n",
                id, name, course);
        if (subjects != null) {
            for (Subjects subject : subjects) {
                result += String.format(
                        "Subjects[id=%d, name='%s']%n",
                        subject.getId(), subject.getName_subjects());
            }
        }
        return result;
    }
}
