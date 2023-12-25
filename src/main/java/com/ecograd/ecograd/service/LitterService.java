package com.ecograd.ecograd.service;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.Region;

import java.util.List;

public interface LitterService {
    List<Litter> findAll();

    Litter addLitter(Litter litter);

    Litter findById(Long id);
    List<Litter> findByUserUsername(String username);
    List<Litter> findAllByRegion(Region r);

}
