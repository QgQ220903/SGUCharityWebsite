package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class AdminHomeController {
    @Autowired
    private ChartRestController chartRestController;

    @GetMapping("/admin/home")
    public String showChart(Model model) {
        List<Object[]> projectData = chartRestController.getProjectsByCategory();
        model.addAttribute("projectData", projectData);
        return "admin/home/index"; // Tên của file template HTML
    }

}
