package com.koushan.niles.repository;

import com.koushan.niles.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DealRepository extends JpaRepository<Deal, Long> {
    Deal findByApparel_Id(Long id);
}