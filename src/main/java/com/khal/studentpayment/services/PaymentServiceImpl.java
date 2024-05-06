package com.khal.studentpayment.services;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.Student;
import com.khal.studentpayment.repositories.PaymentRepository;

import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService{
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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
    public Payment getPaymentByStudent(Student student) {
        return null;
    }
}
