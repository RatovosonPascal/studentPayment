package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.PaymentType;
import com.khal.studentpayment.entities.Student;
import com.khal.studentpayment.repositories.PaymentRepository;
import com.khal.studentpayment.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
public class PaymentServiceImpl implements PaymentService{
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findPaymentById(UUID id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public List<Payment> getPaymentByStudentCode(String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment savePaymentWithUpload(MultipartFile file, LocalDate date, PaymentType type, Double amount, String studentCode) {
        Path path = Paths.get(System.getProperty("user.home"), "student-app-files","payments");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String paymentId = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "student-app-files","payments",paymentId +".pdf");
        try {
            Files.copy(file.getInputStream(),filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Student student = studentRepository.findByCode(studentCode);
        Payment  payment = Payment.builder()
                .paymentType(type)
                .amount(amount)
                .date(date)
                .student(student)
                .file(filePath.toUri().toString())
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment;
    }

    @Override
    public byte[] getPaymetFile(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId).get();
        String filepath = payment.getFile();
        try {
            return Files.readAllBytes(Path.of(URI.create(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
