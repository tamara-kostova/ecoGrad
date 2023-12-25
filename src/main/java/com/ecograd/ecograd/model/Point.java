package com.ecograd.ecograd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double x;
    private Double y;
    public Point() {
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
