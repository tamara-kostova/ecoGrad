package com.ecograd.ecograd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Base64;

import jakarta.persistence.Lob;

@Data
@Entity
public class Litter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReported;
    private double longitude;
    private double latitude;
    private LitterType litterType;
    private LitterSeverity litterSeverity;
    @ManyToOne
    private Region region;
    @Lob
    @Column(name = "picture")
    private byte[] imageData;
    private Double score;
    @ManyToOne
    private User user;

    public Litter(LocalDate dateReported, double longitude, double latitude, LitterType litterType, LitterSeverity litterSeverity, byte[] imageData, Region region) {
        this.dateReported = dateReported;
        this.longitude = longitude;
        this.latitude = latitude;
        this.litterType = litterType;
        this.litterSeverity = litterSeverity;
        this.imageData = imageData;
        this.region = region;
        score = 0d;
        if (litterType.equals(LitterType.PLASTIC))
            score+=5;
        else if (litterType.equals(LitterType.OTHER))
            score+=4;
        if (litterType.equals(LitterType.GLASS))
            score+=3;
        if (litterType.equals(LitterType.PAPER))
            score+=2;
        if (litterSeverity.equals(LitterSeverity.SEVERE))
            score+=5;
        else if (litterSeverity.equals(LitterSeverity.MEDIUM))
            score+=4;
        if (litterSeverity.equals(LitterSeverity.SMALL))
            score+=3;
    }

    public Litter() {
    }

    public static String encodeChartImage(byte[] chartImage) {
        return new String(Base64.getEncoder().encode(chartImage));
    }
}
