package com.ecograd.ecograd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Point> points = new ArrayList<>();

    private Double score;
    private String name;
    public Region(){}
    public Region(List<Point> points, Double score, String name) {
        this.points = points;
        this.score = score;
        this.name = name;
    }
}
