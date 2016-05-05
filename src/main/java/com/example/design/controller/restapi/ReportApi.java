package com.example.design.controller.restapi;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.constant.Role;
import com.example.design.model.Report;
import com.example.design.model.User;
import com.example.design.service.impl.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * report rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/report")
public class ReportApi {
  @Autowired
  private ReportService reportService;


  @RequestMapping(value = "")
  @Authorization({Role.ADMIN})
  public ResponseEntity listAll() {
    List<Report> reports = reportService.all();
    return new ResponseEntity<>(reports, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}")
  public ResponseEntity id(@PathVariable long id) {
    Report report = reportService.id(id);
    return ResponseEntity.ok(report);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity addReport(@RequestBody Report report, @CurrentUser User user) {
    long userId = user.getUserId();
    report.setUserId(userId);
    int ok = reportService.addReport(report);
    if (ok == 0) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(report);
  }

  @RequestMapping(value = "{reportId}", method = RequestMethod.DELETE)
  public ResponseEntity removeReport(@PathVariable long reportId) {
    int ok = reportService.deleteReport(reportId);
    if (ok == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok("删除成功");
  }
}
