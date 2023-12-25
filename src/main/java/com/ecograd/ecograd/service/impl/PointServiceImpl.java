package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.Point;
import com.ecograd.ecograd.repository.PointRepository;
import com.ecograd.ecograd.service.PointService;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Point save(Point p){
        return this.pointRepository.save(p);
    }
}
