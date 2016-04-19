package com.example.design.mapper;

import com.example.design.model.Company;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lxh on 4/13/16.
 */
@CacheNamespace(size = 100)
@Component
public interface CompanyMapper {
    @Select("SELECT * FROM company")
    @ResultType(Company.class)
    List<Company> getAll();

    @Select("")
    List<Company> getThoseIs500();

    @Select("")
    List<Company> getThoseIsCN500();

    @Select("")
    List<Company> getHasCrawled();

    @Update("")
    void updateToCrawled();

    @Update("")
    void updateInfo(Company company);

    @Delete("")
    void deleteById(int id);

    @Delete("")
    void delete(Company company);

    @Select("SELECT COUNT(*) FROM company")
    int count();

    @Select("SELECT * FROM company WHERE id=#{id}")
    Company getById(int id);
}