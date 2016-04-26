package com.example.design.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * Created by lxh on 16/4/26.
 */
@RestController
@RequestMapping("upload")
public class UploadController {

  /**
   * 处理文件上传.
   *
   * @param file 文件
   * @return 返回值
   */
  @CrossOrigin
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
    try {
      // Get the filename and build the local file path (be sure that the
      // application have write permissions on such directory)
      String filename = file.getOriginalFilename();
      String directory = "uploads";
      String filepath = Paths.get(directory, filename).toString();

      // Save the file locally
      BufferedOutputStream stream =
              new BufferedOutputStream(new FileOutputStream(new File(filepath)));
      stream.write(file.getBytes());
      stream.close();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
