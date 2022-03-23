package com.example.emlakburadapurchase.repository;


import com.example.emlakburadapurchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseDao extends JpaRepository<Purchase, Integer> {

    Purchase findById(int id);
    List<Purchase> findAllByUserId(int userId);
}