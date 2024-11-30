package com.web.sgucharitywebsite.service;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartService {
    /**
     * Lấy dữ liệu biểu đồ.
     *
     * @return Dữ liệu biểu đồ dưới dạng danh sách các danh sách Map.
     */
    List<List<Map<String, Object>>> getCanvasjsChartData();
}
