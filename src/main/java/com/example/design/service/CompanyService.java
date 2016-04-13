package com.example.design.service;

import com.example.design.mapper.CompanyMapper;
import com.example.design.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by lxh on 4/13/16.
 */
@Service
public class CompanyService {
    @Autowired
    Optional<CompanyMapper> companyMapper;

    public List<Company> getAll() {
        if (companyMapper.isPresent()) {
            return companyMapper.get().getAll();
        }
        return null;
    }

    public List<Company> getByIs500() {

        if (companyMapper.isPresent()) {
            return companyMapper.get().getThoseIs500();
        }
        return null;
    }

    public int count() {
        if (companyMapper.isPresent()) {
            return companyMapper.get().count();
        }
        return 0;
    }

    public Company getById(int id) {
        if (companyMapper.isPresent()) {
            return companyMapper.get().getById(id);
        }
        return null;
    }
}
