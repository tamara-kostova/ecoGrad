package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.LitterSeverity;
import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.service.LitterService;
import com.ecograd.ecograd.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final LitterService litterService;

    public UserController(UserService userService, LitterService litterService) {
        this.userService = userService;
        this.litterService = litterService;
    }

    @RequestMapping("/profile")
    private String getProfilePage(HttpServletRequest req, Model model){
        String currentUser = req.getRemoteUser();
        User user = userService.findByUsername(currentUser);
        List<Litter> littersByUser = litterService.findByUserUsername(currentUser);
        model.addAttribute("user",user);
        model.addAttribute("litters",littersByUser);
        int severityone = littersByUser.stream().map(Litter::getLitterSeverity).filter(litter->litter.equals(LitterSeverity.SEVERE)).collect(Collectors.toList()).size();
        int severitytwo = littersByUser.stream().map(Litter::getLitterSeverity).filter(litter->litter.equals(LitterSeverity.MEDIUM)).collect(Collectors.toList()).size();
        int severitythree = littersByUser.stream().map(Litter::getLitterSeverity).filter(litter->litter.equals(LitterSeverity.SMALL)).collect(Collectors.toList()).size();
        model.addAttribute("severityone",severityone);
        model.addAttribute("severitytwo",severitytwo);
        model.addAttribute("severitythree",severitythree);
        return "profile";
    }
}
