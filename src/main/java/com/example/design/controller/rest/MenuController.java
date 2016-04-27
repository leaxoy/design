package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.constant.ResponseData;
import com.example.design.constant.Role;
import com.example.design.model.Menu;
import com.example.design.model.User;
import com.example.design.service.impl.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * menu rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/menu")
public class MenuController {

  @Autowired
  private MenuService menuService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  @Authorization({Role.ADMIN, Role.ROOT})
  public ResponseData all() {
    List<Menu> list = menuService.all();
    return new ResponseData(100, "获取所有菜单", "success", list);
  }


  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseData findById(@PathVariable long id, @CurrentUser User user) {
    Menu menu = menuService.findByID(id);
    return ResponseData.ok(100, "获取菜单", "success", menu);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseData add(@CurrentUser long id, @RequestBody Menu menu) {
    return ResponseData.ok(100, "新建菜单", "成功", menu);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  public ResponseData update(@RequestBody Menu menu) {
    return ResponseData.ok(100, "更新菜单", "成功", menu);
  }

}
