package com.example.design.service.impl;

import com.example.design.mapper.ReportMapper;
import com.example.design.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 举报信息的服务接口 Created by lxh on 4/20/16.
 */
@Service
public class ReportService {
    @Autowired
    ReportMapper mapper;

    public List<Report> all() {
        return mapper.listAll();
    }

    public int addReport(Report report) {
        return mapper.addreport(report);
    }

    public int dealReport(Report report) {
        return mapper.dealReport(report);               //处理举报
    }

    public List<Report> getByState(int state) {
        return mapper.selectByState(state);               //按举报状态查询举报
    }

    public List<Report> getByReportType(String reportType) {
        return mapper.selectByReportType(reportType);     //按类型查询举报
    }
}
