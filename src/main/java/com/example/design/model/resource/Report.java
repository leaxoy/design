package com.example.design.model.resource;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 举报模型
 * Created by lxh on 4/14/16.
 */
public class Report {
    @Id
    private Long reportId;          //举报ID
    private Long userId;            //举报者ID
    private Long reportedUserId;    //被举报的用户ID
    private Long menuId;            //被举报的菜单ID
    private Long cookingId;         //被举报的菜谱ID
    private Long showId;            //被举报的作品ID
    private Date reportDate;        //举报状态
    private String reportReason;    //举报原因
    private Integer reportState;    //举报状态，“0”为未处理，“1”为已关闭
    private Date closeDate;         //举报关闭时间


    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(Long reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getCookingId() {
        return cookingId;
    }

    public void setCookingId(Long cookingId) {
        this.cookingId = cookingId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
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

    public Integer getReportState() {
        return reportState;
    }

    public void setReportState(Integer reportState) {
        this.reportState = reportState;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
