package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.Student;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<Payment> getAllPayment();
    Payment findPaymentById(UUID id);
    Payment getPaymentByStudent(Student student);
}
