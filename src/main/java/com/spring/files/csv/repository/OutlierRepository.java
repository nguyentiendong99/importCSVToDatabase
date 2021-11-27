package com.spring.files.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.files.csv.model.Outlier;

public interface OutlierRepository extends JpaRepository<Outlier, Long> {
}
