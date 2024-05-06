package com.khal.studentpayment.controllers;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.Student;
import com.khal.studentpayment.services.PaymentServiceImpl;
import com.khal.studentpayment.services.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/student")
public class StudentController {
    private StudentServiceImpl studentService;
    private PaymentServiceImpl paymentService;

    public StudentController(StudentServiceImpl studentService, PaymentServiceImpl paymentService) {
        this.studentService = studentService;
        this.paymentService = paymentService;
    }
    @GetMapping
    public List<Student> allStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/{id}")
    public Student  getStudent(@PathVariable("id")UUID id){
        return studentService.findStudentById(id).get();
    }
    @PostMapping
    public Student  saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @PutMapping("/{id}")
    public Student  updateStudent(@PathVariable("id") UUID studentId,@RequestBody Student student){
        return studentService.udpateStudent(studentId,student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID studentId){
         studentService.deleteStudentById(studentId);
         return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
    @GetMapping("/payment")
    public List<Payment> allPayment(){
        return paymentService.getAllPayment();
    }
    @GetMapping("/payment/{id}")
    public List<Payment> getPaymentByStudentCode(@PathVariable("id") String id){
        return paymentService.getPaymentByStudentCode(id);
    }

}
