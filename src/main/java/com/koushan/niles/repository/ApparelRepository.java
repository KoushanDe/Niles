package com.koushan.niles.repository;

import com.koushan.niles.entity.Apparel;
import com.koushan.niles.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApparelRepository extends JpaRepository<Apparel, Long> {
    List<Apparel> findAllBySeason(Season season);
}