package com.igpgroup17.studentpals.models.adapters.student;

import com.igpgroup17.studentpals.models.Student;
import com.igpgroup17.studentpals.models.requestbody.RequestBodyStudent;

import java.util.ArrayList;

public class RequestBodyStudentAdapter {

    public Student toStudent(RequestBodyStudent student) {
        return Student.builder()
                .studentId(student.getStudentId())
                .realName(student.getRealName())
                .username(student.getUsername())
                .email(student.getEmail())
                .age(student.getAge())
                .gender(student.getGender())
                .year(student.getYear())
                .universityName(student.getUniversityName())
                .universityEmail(student.getUniversityEmail())
                .course(student.getCourse())
                .interestedEvents(new ArrayList<>())
                .likedEvents(new ArrayList<>())
                .goingEvents(new ArrayList<>())
                .build();
    }
}
