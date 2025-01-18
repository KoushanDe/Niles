package com.koushan.niles.repository;

import com.koushan.niles.entity.Purchase;
import com.koushan.niles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}