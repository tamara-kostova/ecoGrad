package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.service.LitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowMapController {
    @Autowired
    private LitterService litterService;

    @GetMapping("/litters")
    public List<Litter> getAllLitters() {
        return litterService.findAll();
    }
}
