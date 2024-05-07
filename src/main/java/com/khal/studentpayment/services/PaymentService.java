package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.PaymentType;
import com.khal.studentpayment.entities.Student;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<Payment> getAllPayment();
    Payment findPaymentById(UUID id);
    List<Payment> getPaymentByStudentCode(String code);
    Payment savePayment(Payment payment);

    Payment savePaymentWithUpload(MultipartFile file, LocalDate date, PaymentType type, Double amount, String studentCode);

    byte[] getPaymetFile(UUID paymentId);
}
