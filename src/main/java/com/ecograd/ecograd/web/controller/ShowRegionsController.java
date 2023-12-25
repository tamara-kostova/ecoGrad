package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.Region;
import com.ecograd.ecograd.service.RegionService;
import com.nimbusds.jose.shaded.gson.JsonObject;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ShowRegionsController {
    @Autowired
    RegionService regionService;

    @GetMapping("/polygons")
    public List<String> getAllLitters() {
        List<String> list = new ArrayList<>();
        for (Region region : regionService.findAll()) {
            if (region.getScore() < 30) {
                StringBuilder sb = new StringBuilder();
                sb.append(region.getName()).append(";").append("green");
                list.add(sb.toString());
            } else if (region.getScore() < 50) {
                StringBuilder sb = new StringBuilder();
                sb.append(region.getName()).append(";").append("orange");
                list.add(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(region.getName()).append(";").append("red");
                list.add(sb.toString());
            }
        }
        return list;
    }

}
