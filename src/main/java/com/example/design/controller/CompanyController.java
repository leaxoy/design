package com.example.design.controller;

import com.example.design.model.Company;
import com.example.design.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lxh on 4/13/16.
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity all() {
        List<Company> companyList = service.getAll();
        if (companyList == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @RequestMapping("count")
    public int count() {
        return service.count();
    }

    @RequestMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Company c = service.getById(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    @RequestMapping("info")
    public String info() {
        return "info";
    }

}