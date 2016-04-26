package com.example.design.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lxh on 16/4/26.
 */
@RestController
@RequestMapping("upload")
public class UploadController {

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String upload() {

    return "";

  }
}
