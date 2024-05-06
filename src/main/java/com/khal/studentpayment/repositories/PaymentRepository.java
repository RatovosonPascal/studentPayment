package com.khal.studentpayment.repositories;

import com.khal.studentpayment.entities.Payment;
import com.khal.studentpayment.entities.StatusPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByStudentCode (String code);
    List<Payment> findByStatusPayment (StatusPayment status);

}
