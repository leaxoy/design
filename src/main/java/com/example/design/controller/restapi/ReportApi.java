package com.example.design.controller.restapi;

import com.example.design.model.Report;
import com.example.design.model.User;
import com.example.design.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CookingService cookingService;

    @Autowired
    private ShowService showService;

    /**
     * 新建举报
     *
     * @param report report
     * @return report
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity addReport(@RequestBody Report report) {
        /**
         * 新建举报
         */
        report.setCloseDate(new Date());
        int count = reportService.addReport(report);
        if (1 == count) {
            return ResponseEntity.ok(report);
        }
        return ResponseEntity.ok("举报失败");
    }

    /**
     * 根据Id查询举报
     *
     * @param id report id
     * @return report
     */
    @RequestMapping(value = "{id}")
    public ResponseEntity getByReportId(@PathVariable long id) {
        Report report = reportService.getByReportId(id);
        if (report == null) {
            return ResponseEntity.ok("no report as this id");
        }
        return ResponseEntity.ok(report);
    }

    /**
     * 处理举报
     *
     * @param reportId result
     * @return report
     */
    @RequestMapping(value = "{reportId}", method = RequestMethod.PUT)
    public ResponseEntity dealReport(@PathVariable long reportId, String result) {
        Report report = reportService.getByReportId(reportId);
        report.setResult(result);
        report.setCloseDate(new Date());
        if (result.equals("delete")) {
            switch (report.getReportType()) {
                case "user":
                    userService.markLimit(report.getReportedItemId());
                    break;
                case "menu":
                    menuService.markMenuDelete(report.getReportedItemId());
                    break;
                case "cooking":
                    cookingService.markCookingDelete(report.getReportedItemId());
                    break;
                case "show":
                    showService.markShowDelete(report.getReportedItemId());
                    break;
            }
        }
        reportService.dealReport(report);
        return ResponseEntity.ok("处理结束");
    }

    /**
     * 按状态查询举报
     *
     * @param state 0 1
     * @return reports
     */
    @RequestMapping(value = "state/{state}", method = RequestMethod.GET)
    public ResponseEntity getByState(@PathVariable int state) {
        List<Report> reports = reportService.getByState(state);
        if (reports.isEmpty()) {
            return ResponseEntity.ok("no report as this state");
        }

        return ResponseEntity.ok(reports);
    }

    /**
     * 修改用户状态
     *
     * @param account account
     * @return users
     */
    @RequestMapping(value = "{account}", method = RequestMethod.POST)
    public ResponseEntity changeUserState(@PathVariable String account) {
        User user = userService.getByAccountName(account);
        if (user != null) {
            userService.markNormal(user.getUserId());
            return ResponseEntity.ok("The user is normal");
        }
        return ResponseEntity.notFound().build();
    }
}
