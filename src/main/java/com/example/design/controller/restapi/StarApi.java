package com.example.design.controller.restapi;

import com.example.design.annotation.Authorization;
import com.example.design.constant.Role;
import com.example.design.model.Star;
import com.example.design.service.impl.StarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * star rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/star")
@CrossOrigin("*")
public class StarApi {

  @Autowired
  private StarService starService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.ADMIN})
  public ResponseEntity all() {
    List<Star> stars = starService.findAll();
    if (stars == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(stars, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity findById(@PathVariable long id) {
    Star star = starService.findByStarId(id);
    return ResponseEntity.ok(star);
  }

  
}
