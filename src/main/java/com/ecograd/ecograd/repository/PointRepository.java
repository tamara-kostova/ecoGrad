package com.ecograd.ecograd.repository;

import com.ecograd.ecograd.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
