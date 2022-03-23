package com.example.emlakburadapayment.repository;


import com.example.emlakburadapayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

    Payment findById(int id);

}