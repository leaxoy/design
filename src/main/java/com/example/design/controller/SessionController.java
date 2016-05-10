package com.example.design.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lxh on 16/5/10.
 */
@RestController
@RequestMapping("session")
public class SessionController {

  @RequestMapping(method = RequestMethod.GET)
  public void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession httpSession = req.getSession();

    if (httpSession.isNew()) {
      httpSession.setAttribute("name", "lily");
    }
    String sessionId = httpSession.getId();

    PrintWriter out = resp.getWriter();

    if (httpSession.isNew()) {
      out.println("Hello,HttpSession! <br>The first response - SeesionId=" + sessionId + " <br>");
    } else {
      out.println("Hello,HttpSession! <br>The second response - SeesionId=" + sessionId + " <br>");
      out.println("The second-response - name: " + httpSession.getAttribute("name"));
    }
  }

}
