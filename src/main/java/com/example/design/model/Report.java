package com.example.design.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 举报模型 Created by lxh on 4/14/16.
 */
public class Report {
  @Id
  private long reportId;          //举报ID
  private long userId;            //举报者ID
  private String reportType;
  private long reportedItemId;
  private Date reportDate;        //举报状态
  private String reportReason;    //举报原因
  private int state;              //举报状态，“0”为未处理，“1”为已关闭
  private Date closeDate;         //举报关闭时间
  private String result;


  public long getReportId() {
    return reportId;
  }

  public void setReportId(long reportId) {
    this.reportId = reportId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Date getReportDate() {
    return reportDate;
  }

  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }

  public String getReportReason() {
    return reportReason;
  }

  public void setReportReason(String reportReason) {
    this.reportReason = reportReason;
  }


  public Date getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(Date closeDate) {
    this.closeDate = closeDate;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getReportType() {
    return reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }

  public long getReportedItemId() {
    return reportedItemId;
  }

  public void setReportedItemId(long reportedItemId) {
    this.reportedItemId = reportedItemId;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
