package com.igpgroup17.studentpals.services.impl;

import com.igpgroup17.studentpals.dao.StudentCrudDao;
import com.igpgroup17.studentpals.models.Event;
import com.igpgroup17.studentpals.models.Student;
import com.igpgroup17.studentpals.services.EventService;
import com.igpgroup17.studentpals.services.StudentService;
import com.igpgroup17.studentpals.util.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentCrudDao studentCrudDao;

    private final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final EventService eventService;

    @Autowired
    public StudentServiceImpl(StudentCrudDao studentCrudDao, EventService eventService) {
        this.studentCrudDao = studentCrudDao;
        this.eventService = eventService;
    }

    @Override
    public Student createStudent(Student student) {
        return studentCrudDao.createStudent(student);
    }

    @Override
    public Student readStudent(String id) {
        return studentCrudDao.readStudent(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentCrudDao.updateStudent(student);
    }

    @Override
    public Student deleteStudent(String id) {
        Student student = studentCrudDao.readStudent(id);
        studentCrudDao.deleteStudent(id);
        return student;
    }

    // very inefficient - use batch requests later
    @Override
    public List<Event> getLikedEvents(String id) {
        Student student = studentCrudDao.readStudent(id);
        List<Event> events = student.getLikedEvents().stream()
                .map(eventService::readEvent).filter(Objects::nonNull).collect(Collectors.toList());

        student.setLikedEvents(events.stream().map(Event::getEventID).collect(Collectors.toList()));

        updateStudent(student);

        LOGGER.info(events.stream().map(e -> e.getEventID() == null ? "null" : e.getEventID()).collect(Collectors.joining(", ")));
        return events;
    }

    @Override
    public Event addLike(String studentId, String eventId) {
        Student student = studentCrudDao.readStudent(studentId);
        Event event = eventService.readEvent(eventId);

        List<String> likedEvents = ListUtils.copyOf(student.getLikedEvents());

        event.setLikes(event.getLikes() + 1);

        likedEvents.add(eventId);

        student.setLikedEvents(likedEvents);

        LOGGER.info(student.getLikedEvents().stream().collect(Collectors.joining(", ")));

        studentCrudDao.updateStudent(student);
        eventService.updateEvent(event);
        return eventService.readEvent(eventId);
    }

    @Override
    public List<Event> getGoingEvents(String id) {
        Student student = studentCrudDao.readStudent(id);
        List<Event> events = student.getGoingEvents().stream()
                .map(eventService::readEvent).filter(Objects::nonNull).collect(Collectors.toList());

        student.setGoingEvents(events.stream().map(Event::getEventID).collect(Collectors.toList()));

        updateStudent(student);

        LOGGER.info(events.stream().map(e -> e.getEventID() == null ? "null" : e.getEventID()).collect(Collectors.joining(", ")));
        return events;
    }

    @Override
    public Event addGoing(String studentId, String eventId) {
        Student student = studentCrudDao.readStudent(studentId);
        Event event = eventService.readEvent(eventId);

        List<String> goingEvents = ListUtils.copyOf(student.getGoingEvents());
        List<String> goingUsersIDs = ListUtils.copyOf(event.getGoingUsersIDs());

        goingEvents.add(eventId);
        goingUsersIDs.add(studentId);

        student.setGoingEvents(goingEvents);
        event.setGoingUsersIDs(goingUsersIDs);

        eventService.updateEvent(event);
        studentCrudDao.updateStudent(student);
        return event;
    }

    @Override
    public List<Event> getInterestedEvents(String id) {
        Student student = studentCrudDao.readStudent(id);
        List<Event> events = student.getInterestedEvents().stream()
                .map(eventService::readEvent).filter(Objects::nonNull).collect(Collectors.toList());

        student.setInterestedEvents(events.stream().map(Event::getEventID).collect(Collectors.toList()));

        updateStudent(student);

        LOGGER.info(events.stream().map(e -> e.getEventID() == null ? "null" : e.getEventID()).collect(Collectors.joining(", ")));
        return events;
    }

    @Override
    public Event addInterested(String studentId, String eventId) {
        Student student = studentCrudDao.readStudent(studentId);
        Event event = eventService.readEvent(eventId);

        List<String> interestedEvents = ListUtils.copyOf(student.getInterestedEvents());
        List<String> interestedUsers = ListUtils.copyOf(event.getInterestedUsersIDs());

        interestedEvents.add(eventId);
        interestedUsers.add(studentId);

        student.setInterestedEvents(interestedEvents);
        event.setInterestedUsersIDs(interestedUsers);

        eventService.updateEvent(event);
        studentCrudDao.updateStudent(student);
        return event;
    }
}
