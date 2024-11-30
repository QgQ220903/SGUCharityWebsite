package com.web.sgucharitywebsite.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasjsChartData {

    public static List<List<Map<String, Object>>> getCanvasjsDataList() {
        List<List<Map<String, Object>>> list = new ArrayList<>();

        // Danh sách các điểm dữ liệu
        List<Map<String, Object>> dataPoints1 = new ArrayList<>();
        dataPoints1.add(createDataPoint(1609459200000L, 150));  // 01/01/2021
        dataPoints1.add(createDataPoint(1612137600000L, 200));  // 01/02/2021
        dataPoints1.add(createDataPoint(1614556800000L, 175));  // 01/03/2021
        dataPoints1.add(createDataPoint(1617235200000L, 250));  // 01/04/2021
        dataPoints1.add(createDataPoint(1619827200000L, 300));  // 01/05/2021
        dataPoints1.add(createDataPoint(1622505600000L, 350));  // 01/06/2021
        dataPoints1.add(createDataPoint(1625097600000L, 400));  // 01/07/2021
        dataPoints1.add(createDataPoint(1627776000000L, 450));  // 01/08/2021

        // Thêm danh sách vào danh sách chính
        list.add(dataPoints1);

        return list;
    }

    // Phương thức tạo điểm dữ liệu
    private static Map<String, Object> createDataPoint(long x, int y) {
        Map<String, Object> dataPoint = new HashMap<>();
        dataPoint.put("x", x);
        dataPoint.put("y", y);
        return dataPoint;
    }
}
