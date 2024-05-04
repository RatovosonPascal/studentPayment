package com.khal.studentpayment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private LocalDate date;
    private Double amount;
    private PaymentType paymentType;
    private StatusPayment  statusPayment = StatusPayment.CREATED;
    private String file;
    @ManyToOne
    private Student student;
}
