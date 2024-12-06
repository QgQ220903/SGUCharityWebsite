package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.entity.AppUser;
import com.web.sgucharitywebsite.repository.AppUserRepository;
import com.web.sgucharitywebsite.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminChartController {

    @Autowired
    private CanvasjsChartService canvasjsChartService;
    @Autowired
    private AppUserRepository appUserRepository;
    @RequestMapping("/admin/chart")
    public String showChart(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            AppUser appUser = appUserRepository.findByEmail(email);
            model.addAttribute("user", appUser);
        }

        // Lấy dữ liệu từ Service
        model.addAttribute("dataPointsList", canvasjsChartService.getCanvasjsChartData());
        return "admin/Chart/ColumnChart"; // Tên view
    }
}
