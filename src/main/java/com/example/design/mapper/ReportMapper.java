package com.example.design.mapper;

import com.example.design.model.Report;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 举报持久化接口. Created by lxh on 4/20/16.
 */
@Repository
@Mapper
public interface ReportMapper {

  @Select("SELECT * FROM `report`")
  List<Report> listAll();

  @Insert("INSERT INTO `message`(`userID`, `reportType`, `reportedItemId`, `reportDate`, `reportReason`)" +
          " VALUES (#{userID}, #{reportType}, #{reportedItemId}, #{reportDate}, #{reportReason})")
  int addreport(Report report);            //添加举报

  @Update("UPDATE `report` SET `state` = 1, `result`=#{result}, `closeDate`=#{closeDate}" +
          "WHERE `messageId`=#{messageId}")
  int dealReport(Report report);          //处理并关闭举报

  @Select("SELECT * FROM `report` WHERE stste=#{state}")
  List<Report> selectByState(int state);           //按处理状态来处理举报

  @Select("SELECT * FROM `report` WHERE reportType=#{reportType}&&stste=0")
  List<Report> selectByReportType(String reportType);           //按被举报类型查询未处理举报

}
