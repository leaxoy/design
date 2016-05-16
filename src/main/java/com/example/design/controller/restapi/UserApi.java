package com.example.design.controller.restapi;

import com.example.design.annotation.Authorization;
import com.example.design.annotation.CurrentUser;
import com.example.design.model.Comment;
import com.example.design.model.Cooking;
import com.example.design.model.Menu;
import com.example.design.model.Message;
import com.example.design.model.Show;
import com.example.design.model.User;
import com.example.design.service.impl.CommentService;
import com.example.design.service.impl.CookingService;
import com.example.design.service.impl.FriendService;
import com.example.design.service.impl.MenuService;
import com.example.design.service.impl.MessageService;
import com.example.design.service.impl.ReportService;
import com.example.design.service.impl.ShowService;
import com.example.design.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * user rest api.
 *
 * @author lxh
 * @version 0.1
 */
@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserApi {
  @Autowired
  private UserService userService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private CookingService cookingService;
  @Autowired
  private FriendService friendService;
  @Autowired
  private ShowService showService;
  @Autowired
  private MenuService menuService;
  @Autowired
  private MessageService messageService;
  @Autowired
  private ReportService reportService;

  /**
   * 获取用户信息
   *
   * @param id user findById.
   * @return user.
   */
  @RequestMapping("{id}")
  public ResponseEntity getById(@PathVariable long id) {
    User user = userService.id(id);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
  }

  /**
   * 修改用户个人信息
   */
  @RequestMapping(value = "", method = RequestMethod.PUT)
  @Authorization
  public ResponseEntity changeInfo(@RequestBody User user) {
    /**
     * 修改个人信息.
     */
    int count = userService.updateInfo(user);
    if (1 == count) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.ok("修改失败");

  }

  /**
   * 修改密码
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authorization
  public ResponseEntity changePassword(@CurrentUser User user, String password, String newPasswd) {
    String account = user.getAccount();
    User old = userService.getByAccountName(account);
    Assert.notNull(password, "password cannot be empty");
    Assert.notNull(newPasswd, "new password cannot be empty");
    if (password.equals(old.getPassword())) {
      user.setPassword(password);
      userService.updatePassword(user);
    }
    return ResponseEntity.ok("密码有误");
  }

  /**
   * 获取用户评论
   *
   * @param id user findById.
   * @return user's comments
   */
  @RequestMapping("{id}/comment")
  public ResponseEntity getCommentsByUserId(@PathVariable long id) {
    List<Comment> comments = commentService.byUserId(id);
    if (comments == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /**
   * 获取用户菜谱.
   *
   * @param id 用户id.
   * @return 菜谱列表.
   */
  @RequestMapping("{id}/cooking")
  public ResponseEntity getCookingsByUserId(@PathVariable long id) {
    List<Cooking> cookings = cookingService.findAllCookingByUserId(id);
    if (cookings == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(cookings, HttpStatus.OK);
  }

  /**
   * 获取用户点赞信息.
   *
   * @param id 用户id.
   * @return 点赞数据.
   */
  @RequestMapping("{id}/like")
  public ResponseEntity getLikesInfoByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 获取用户朋友.
   *
   * @param id 用户id.
   * @return 朋友列表.
   */
  @RequestMapping("{id}/friend")
  public ResponseEntity getFriendsByUserId(@PathVariable long id) {
    List<User> friends = friendService.getFriendsByUserId(id);
    if (friends == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(friends, HttpStatus.OK);
  }

  /**
   * 获取菜单信息.
   *
   * @param id 用户id.
   * @return 菜单数据.
   */
  @RequestMapping("{id}/menu")
  public ResponseEntity getMenusByUserId(@PathVariable long id) {
    List<Menu> menus = menuService.findAllMenuByUserId(id);
    if (menus == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(menus, HttpStatus.OK);
  }

  /**
   * 获取关注信息
   *
   * @param id 用户id.
   * @return 关注信息.
   */
  @RequestMapping("{id}/star")
  public ResponseEntity getStarsByUserId(@PathVariable long id) {
    return null;
  }

  @RequestMapping("{id}/follow")
  public ResponseEntity getFollowsByUserId(@PathVariable long id) {
    return null;
  }

  /**
   * 查找用户所发和所接所有message
   *
   * @param id messageUserId
   * @return message
   */
  @RequestMapping("{id}/message")
  public ResponseEntity getMessagesByMessageUserId(@PathVariable long id) {
    List<Message> messages = messageService.getByMessageUserId(id);
    messages.addAll(messageService.getByUserId(id));
    if (messages.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  /**
   * 修改用户个人信息.
   */
  @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
  @Authorization
  public ResponseEntity changeInfo(@PathVariable long userId, @RequestBody User user) {
    int count = userService.updateInfo(user);
    if (1 == count) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.ok("修改失败");

  }

  /**
   * 返回指定用户id 的菜谱列表.
   *
   * @param id 用户 findById.
   * @return 菜谱列表.
   */
  @RequestMapping(value = "{id}/cooking", method = RequestMethod.GET)
  public ResponseEntity cookingByuserId(@PathVariable long id) {
    List<Cooking> list = cookingService.findAllCookingByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 返回指定用户id 的作品列表.
   *
   * @param id 用户 findById.
   * @return 作品列表.
   */
  @RequestMapping(value = "{id}/show", method = RequestMethod.GET)
  public ResponseEntity showByUserId(@PathVariable long id) {
    List<Show> list = showService.findByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }

  /**
   * 返回指定用户id 的菜单列表.
   *
   * @param id 用户 findById.
   * @return 菜单列表.
   */
  @RequestMapping(value = "{id}/menu", method = RequestMethod.GET)
  public ResponseEntity menuByUserId(@PathVariable long id) {
    List<Menu> list = menuService.findAllMenuByUserId(id);
    if (list == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(list);
  }
}
