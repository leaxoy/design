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
  private ReportMapper reportMapper;

  public List<Report> all() {
    return reportMapper.listAll();
  }

  public Report id(long id) {
    return reportMapper.id(id);
  }

  public int deleteReport(long id) {
    return reportMapper.deleteReport(id);
  }

  public int addReport(Report report) {
    return reportMapper.addReport(report);
  }

  public int dealReport(Report report) {
    return reportMapper.dealReport(report);               //处理举报
  }

  public List<Report> getByState(int state) {
    return reportMapper.selectByState(state);               //按举报状态查询举报
  }

  public List<Report> getByReportType(String reportType) {
    return reportMapper.selectByReportType(reportType);     //按类型查询举报
  }
}
