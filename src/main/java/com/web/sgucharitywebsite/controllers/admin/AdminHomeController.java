package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.repository.ProjectRepository;
import com.web.sgucharitywebsite.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class AdminHomeController {
    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/admin/home")
    public String home() {

        return "admin/home/index";
    }

}
