package com.web.sgucharitywebsite.repository;

import com.web.sgucharitywebsite.dto.CanvasjsChartData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartRepository {

    /**
     * Lấy dữ liệu biểu đồ từ CanvasjsChartData.
     *
     * @return Dữ liệu biểu đồ dưới dạng danh sách các danh sách Map.
     */
    public List<List<Map<String, Object>>> getCanvasjsChartData() {
        // Gọi phương thức getCanvasjsDataList từ lớp CanvasjsChartData
        return CanvasjsChartData.getCanvasjsDataList();
    }
}
