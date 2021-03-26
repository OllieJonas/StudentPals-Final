package com.igpgroup17.studentpals.dao;

        import com.igpgroup17.studentpals.models.Student;

public interface StudentCrudDao {
     Student createStudent(Student user);
     Student readStudent(String studentId);
     Student updateStudent(Student student);
     void deleteStudent(String studentId);
}
