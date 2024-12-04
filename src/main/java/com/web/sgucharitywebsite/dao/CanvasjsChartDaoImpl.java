package com.web.sgucharitywebsite.dao;

import com.web.sgucharitywebsite.entity.CanvasjsChartData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return CanvasjsChartData.getCanvasjsDataList();
    }

}