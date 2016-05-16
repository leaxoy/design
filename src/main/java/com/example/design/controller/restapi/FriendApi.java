package com.example.design.controller.restapi;

import com.example.design.annotation.Authorization;
import com.example.design.annotation.CurrentUser;
import com.example.design.model.User;
import com.example.design.service.impl.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * friend rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/follow")
@CrossOrigin("*")
public class FriendApi {

  @Autowired
  private FriendService friendService;

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public ResponseEntity findFriends(@PathVariable("id") long userId, @CurrentUser User user) {
    return new ResponseEntity<>(friendService.getFriendsByUserId(userId), HttpStatus.OK);
  }

  /**
   * 建立用户关系.
   *
   * @param id       用户id
   * @param friendId 朋友id
   * @param user     当前用户
   * @return 是否成功
   */
  @RequestMapping(value = "{id}/{friendId}", method = RequestMethod.POST)
  @Authorization
  public ResponseEntity beFriend(@PathVariable long id,
                                 @PathVariable long friendId,
                                 @CurrentUser User user) {
    int ok = friendService.buildFriendShip(id, friendId);
    if (ok == 1) {
      return ResponseEntity.ok(ok);
    }
    return null;
  }

  /**
   * 移除用户关系.
   *
   * @param id       用户id
   * @param friendId 朋友id
   * @param user     当前用户
   * @return 是否成功
   */
  @RequestMapping(value = "{id}/{friendId}", method = RequestMethod.DELETE)
  @Authorization
  public ResponseEntity beNotFriend(@PathVariable long id,
                                    @PathVariable long friendId,
                                    @CurrentUser User user) {
    int ok = friendService.removeFriend(id, friendId);
    if (ok == 1) {
      return ResponseEntity.ok(ok);
    }
    return null;
  }
}
