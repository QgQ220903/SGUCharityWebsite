package com.web.sgucharitywebsite.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sgucharitywebsite.service.CanvasjsChartService;
import com.web.sgucharitywebsite.repository.CanvasjsChartRepository;

@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

    @Autowired
    private CanvasjsChartRepository canvasjsChartRepository;

    /**
     * Triển khai phương thức lấy dữ liệu biểu đồ từ Repository.
     *
     * @return Dữ liệu biểu đồ dưới dạng danh sách các danh sách Map.
     */
    @Override
    public List<List<Map<String, Object>>> getCanvasjsChartData() {
        // Gọi dữ liệu từ repository
        return canvasjsChartRepository.getCanvasjsChartData();
    }
}
