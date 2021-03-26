package com.igpgroup17.studentpals.controllers;

import com.igpgroup17.studentpals.models.Student;
import com.igpgroup17.studentpals.models.adapters.student.RequestBodyStudentAdapter;
import com.igpgroup17.studentpals.models.requestbody.RequestBodyStudent;
import com.igpgroup17.studentpals.services.StudentService;
import com.igpgroup17.studentpals.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/v1/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable(name = "id") String id) {
        Student student = studentService.readStudent(id);
        return student == null ? new ResponseEntity<>(ErrorMessage.of("Not Found"), HttpStatus.NOT_FOUND) : ResponseEntity.ok(studentService.readStudent(id));
    }

    @PostMapping("/v1/students")
    public ResponseEntity<?> createStudent(@RequestBody RequestBodyStudent student) {
        return ResponseEntity.ok(studentService.createStudent(new RequestBodyStudentAdapter().toStudent(student)));
    }

    @PutMapping("/v1/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/v1/students/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @GetMapping("/v1/students/liked/{id}")
    public ResponseEntity<?> getLikedEvents(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(studentService.getLikedEvents(id));
    }

    @PutMapping("/v1/students/like/{studentId}/{eventId}")
    public ResponseEntity<?> addLikedEvent(@PathVariable(name = "studentId") String studentId, @PathVariable(name = "eventId") String eventId) {
        return ResponseEntity.ok(studentService.addLike(studentId, eventId));
    }

    @GetMapping("/v1/students/going/{id}")
    public ResponseEntity<?> getGoingEvents(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(studentService.getGoingEvents(id));
    }

    @PutMapping("/v1/students/going/{studentId}/{eventId}")
    public ResponseEntity<?> addGoingEvent(@PathVariable(name = "studentId") String studentId, @PathVariable(name = "eventId") String eventId) {
        return ResponseEntity.ok(studentService.addGoing(studentId, eventId));
    }

    @GetMapping("/v1/students/interested/{id}")
    public ResponseEntity<?> getInterestedEvents(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(studentService.getInterestedEvents(id));
    }

    @PutMapping("/v1/students/interested/{studentId}/{eventId}")
    public ResponseEntity<?> addInterestedEvent(@PathVariable(name = "studentId") String studentId, @PathVariable(name = "eventId") String eventId) {
        return ResponseEntity.ok(studentService.addInterested(studentId, eventId));
    }
}
