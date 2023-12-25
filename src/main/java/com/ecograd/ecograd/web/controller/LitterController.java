package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.LitterSeverity;
import com.ecograd.ecograd.model.LitterType;
import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.service.LitterService;
import com.ecograd.ecograd.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping()
public class LitterController {
    private final LitterService litterService;
    private final UserService userService;

    public LitterController(LitterService litterService, UserService userService) {
        this.litterService = litterService;
        this.userService = userService;
    }

    @GetMapping({"/home", "/"})
    public String getHomePage(Model model){
        model.addAttribute("longitudes",litterService.findAll().stream().mapToDouble(Litter::getLongitude));
        model.addAttribute("latitudes",litterService.findAll().stream().mapToDouble(Litter::getLatitude));
        return "home-page";
    }
    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("date",LocalDate.now());
        model.addAttribute("types",LitterType.values());
        model.addAttribute("severities",LitterSeverity.values());
        return "add";
    }
    @PostMapping("/add")
    public String addLitter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReported,
                            @RequestParam String location,
                            @RequestParam LitterType litterType,
                            @RequestParam LitterSeverity litterSeverity,
                            @RequestParam(required = false) byte[] image,
                            HttpServletRequest req
                            ){
        Double latitude = Double.parseDouble(location.split(",")[0]);
        Double longitude = Double.parseDouble(location.split(",")[1]);
        Litter litter = new Litter(dateReported,longitude,latitude,litterType,litterSeverity,image, null);
        String currentUser = req.getRemoteUser();
        userService.addPointsToUser(litter.getScore(),currentUser);
        User user = userService.findByUsername(currentUser);
        litter.setUser(user);
        litterService.addLitter(litter);
        return "redirect:/home";
    }
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        model.addAttribute("litter",litterService.findById(id));
        return "add";
    }
    @PostMapping("/edit/{id}")
    public String editLitter(@PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReported,
                            @RequestParam Double longitude,
                            @RequestParam Double latitude,
                            @RequestParam LitterType litterType,
                            @RequestParam LitterSeverity litterSeverity,
                            @RequestParam byte[] imageData
    ){
        Litter litter = litterService.findById(id);
        litter.setDateReported(dateReported);
        litter.setLongitude(longitude);
        litter.setLatitude(latitude);
        litter.setLitterType(litterType);
        litter.setLitterSeverity(litterSeverity);
        litter.setImageData(imageData);
        litterService.addLitter(litter);
        return "redirect:/home";
    }
    @GetMapping("/getLitters")
    public List<Litter> getLitters(){
        return litterService.findAll();
    }
}
