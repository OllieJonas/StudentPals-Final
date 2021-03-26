package com.igpgroup17.studentpals.services;


import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.EventPreview;
import com.igpgroup17.studentpals.models.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student readStudent(String id);

    Student updateStudent(Student student);

    Student deleteStudent(String id);

    List<Event> getLikedEvents(String id);

    Event addLike(String studentId, String eventId);

    List<Event> getGoingEvents(String id);

    Event addGoing(String studentId, String eventId);

    List<Event> getInterestedEvents(String id);

    Event addInterested(String studentId, String eventId);
}
