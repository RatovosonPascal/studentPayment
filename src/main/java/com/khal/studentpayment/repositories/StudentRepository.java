package com.khal.studentpayment.repositories;

import com.khal.studentpayment.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Student findByCode(String code);
}
