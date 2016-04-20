package com.example.design.service;

import com.example.design.mapper.CompanyMapper;
import com.example.design.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxh on 4/13/16.
 */
@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    public List<Company> getAll() {
        return companyMapper.getAll();
    }

    public List<Company> getByIs500() {
        return companyMapper.getThoseIs500();
    }

    public int count() {
        return companyMapper.count();
    }

    public Company getById(int id) {
        return companyMapper.getById(id);
    }
}
