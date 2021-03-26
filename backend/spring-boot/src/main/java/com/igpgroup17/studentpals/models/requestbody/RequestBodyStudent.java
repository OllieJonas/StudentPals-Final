package com.igpgroup17.studentpals.models.requestbody;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyStudent {

    private String studentId;

    private String username;

    private String email;

    private String realName;

    private Integer age;

    private String gender;

    private String universityName;

    private String universityEmail;

    private Integer year;

    private String course;
}
