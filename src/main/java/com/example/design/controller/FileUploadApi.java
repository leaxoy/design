package com.example.design.controller;

import com.google.common.io.ByteStreams;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户上传图片.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("file")
public class FileUploadApi {

  /**
   * 用户下载文件.
   *
   * @param fileName 文件名字
   * @param response HTTP响应
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(value = "", method = RequestMethod.GET)
  public void download(@PathVariable String fileName, HttpServletResponse response)
          throws IOException {
    InputStream inputStream = new FileInputStream(fileName);
    ByteStreams.copy(inputStream, response.getOutputStream());
    response.setContentType("");
    response.flushBuffer();
  }

  /**
   * 处理文件上传.
   *
   * @param file 用户上传的文件对象
   * @return 返回值
   */
  @CrossOrigin(origins = {"http://localhost:8080"})
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity upload(@RequestParam("file") MultipartFile file) throws Exception {
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
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
