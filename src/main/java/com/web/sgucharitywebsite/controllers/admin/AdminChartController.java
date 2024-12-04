package com.web.sgucharitywebsite.controllers.admin;

import com.web.sgucharitywebsite.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminChartController {

    @Autowired
    private CanvasjsChartService canvasjsChartService;

    @RequestMapping("/admin/chart")
    public String showChart(Model model) {

        // Lấy dữ liệu từ Service
        model.addAttribute("dataPointsList", canvasjsChartService.getCanvasjsChartData());
        return "admin/Chart/ColumnChart"; // Tên view
    }
}
