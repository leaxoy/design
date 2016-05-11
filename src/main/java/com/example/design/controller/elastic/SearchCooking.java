package com.example.design.controller.elastic;

import com.example.design.model.Cooking;
import com.example.design.repository.CookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * cooking elasticsearch api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("elastic/cooking")
public class SearchCooking {
  @Autowired
  private CookingRepository cookingRepository;

  /**
   * @return cooking list ResponseEntity.
   */
  @RequestMapping("")
  public ResponseEntity findAll() {
    List<Cooking> cookingList = new ArrayList<>();
    for (Cooking cooking : cookingRepository.findAll()) {
      cookingList.add(cooking);
    }
    return new ResponseEntity<>(cookingList, HttpStatus.OK);
  }

  /**
   * @param id cooking's findById
   * @return cooking ResponseEntity.
   */
  @RequestMapping("{id}")
  public ResponseEntity findOne(@PathVariable long id) {
    Cooking cooking = cookingRepository.findOne(id);
    return ResponseEntity.ok(cooking);
  }

  /**
   * @param cooking cooking to add.
   * @return cooking.
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity addOne(@RequestBody Cooking cooking) {
    cookingRepository.save(cooking);
    return ResponseEntity.ok(cooking);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable long id, @RequestBody Cooking cooking) {
    return ResponseEntity.ok(cooking);
  }
}
