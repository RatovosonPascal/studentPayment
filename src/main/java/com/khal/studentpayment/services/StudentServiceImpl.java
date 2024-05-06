package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Student;
import com.khal.studentpayment.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student udpateStudent(UUID id, Student student) {
        return studentRepository.findById(id)
                .map(
                        student1 -> {
                            student1.setCode(student.getCode());
                            student1.setEmail(student.getEmail());
                            student1.setPhoto(student.getPhoto());
                            student1.setLastName(student.getLastName());
                            student1.setFirstName(student.getFirstName());
                            return studentRepository.save(student1);
                        }

                ).orElseThrow(
                        () -> new IllegalArgumentException("Role not found")
                );
    }
}
