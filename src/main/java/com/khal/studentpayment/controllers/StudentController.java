package com.khal.studentpayment.controllers;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.PaymentType;
import com.khal.studentpayment.entities.Student;
import com.khal.studentpayment.services.PaymentServiceImpl;
import com.khal.studentpayment.services.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {
    private StudentServiceImpl studentService;
    private PaymentServiceImpl paymentService;

    public StudentController(StudentServiceImpl studentService, PaymentServiceImpl paymentService) {
        this.studentService = studentService;
        this.paymentService = paymentService;
    }
    @GetMapping("/student")
    public List<Student> allStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/student/{id}")
    public Student  getStudent(@PathVariable("id")UUID id){
        return studentService.findStudentById(id).get();
    }
    @PostMapping("/student")
    public Student  saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @PutMapping("/student/{id}")
    public Student  updateStudent(@PathVariable("id") UUID studentId,@RequestBody Student student){
        return studentService.udpateStudent(studentId,student);
    }
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") UUID studentId){
         studentService.deleteStudentById(studentId);
         return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
    @GetMapping("/payment")
    public List<Payment> allPayment(){
        return paymentService.getAllPayment();
    }
    @GetMapping("/payment/{id}/student")
    public List<Payment> getPaymentByStudentCode(@PathVariable("id") String id){
        return paymentService.getPaymentByStudentCode(id);
    }
    @PostMapping("/payment")
    public Payment savePayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }
    @PostMapping(value = "/payment/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePaymentWithUpload(@RequestParam MultipartFile file,
                                         LocalDate date,
                                         PaymentType type,
                                         Double amount,
                                         String studentCode) throws IOException {
        return paymentService.savePaymentWithUpload(file,date,type,amount,studentCode);
    }
    @GetMapping(value = "/paymentFile/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte [] getPaymentFile(@PathVariable("id") UUID paymentId){
return paymentService.getPaymetFile(paymentId);
    }

}
