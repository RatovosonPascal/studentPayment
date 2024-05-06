package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    List<Student> getAllStudent ();
   Optional<Student>  findStudentById(UUID id);
   Student saveStudent (Student student);
   void deleteStudentById(UUID id);
   Student udpateStudent(UUID id, Student student);

}
