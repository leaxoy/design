package com.example.design.service.impl;

import com.example.design.mapper.ReportMapper;
import com.example.design.model.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 举报信息的服务接口 Created by lxh on 4/20/16.
 */
@Service
@Transactional
public class ReportService {
  @Autowired
  private ReportMapper reportMapper;

  public List<Report> all() {
    return reportMapper.findAll();
  }

  public Report id(long id) {
    return reportMapper.findById(id);
  }

  public int deleteReport(Report report) {
    return reportMapper.delete(report);
  }

  public int addReport(Report report) {
    return reportMapper.add(report);
  }

  public int dealReport(Report report) {
    return reportMapper.dealReport(report);               //处理举报
  }

  public List<Report> getByState(int state) {
    return reportMapper.findByState(state);               //按举报状态查询举报
  }

  public List<Report> getByReportType(String reportType) {
    return reportMapper.findByReportType(reportType);     //按类型查询举报
  }
}
