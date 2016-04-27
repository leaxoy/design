package com.example.design.controller.rest;

import com.example.design.authorization.annotation.Authorization;
import com.example.design.authorization.annotation.CurrentUser;
import com.example.design.constant.ResponseData;
import com.example.design.constant.Role;
import com.example.design.model.User;
import com.example.design.service.impl.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * follow rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/follow")
public class FriendController {

  @Autowired
  private FriendService friendService;

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @Authorization({Role.USER})
  public Iterator<User> findFriends(@PathVariable("id") long userId, @CurrentUser User user) {
    List<Integer> list;
    return null;
  }

  @RequestMapping(value = "{id}/{friendId}", method = RequestMethod.POST)
  public ResponseData beFriend(@PathVariable long id, @PathVariable long friendId,
                               @CurrentUser User user) {
    return null;
  }

  @RequestMapping(value = "{id}/{friendId}", method = RequestMethod.DELETE)
  public ResponseData beNotFriend(@PathVariable long id, @PathVariable long friendId,
                                  @CurrentUser User user) {
    return null;
  }
}
