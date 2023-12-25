package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.Region;
import com.ecograd.ecograd.repository.RegionRepository;
import com.ecograd.ecograd.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region save(Region r){
        return regionRepository.save(r);
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }
}
