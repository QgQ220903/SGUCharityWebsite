package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class AdminHomeController {
    @Autowired
    private ChartRestController chartRestController;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @RequestMapping("/admin/home")
    public String home(Model model, Principal principal) {
        List<Object[]> projectData = chartRestController.getProjectsByCategory();
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }
        model.addAttribute("projectData", projectData);
        return "admin/home/index";
    }

}
