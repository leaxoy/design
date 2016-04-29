/*

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : design

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId`    INT(11) AUTO_INCREMENT NOT NULL,
  `userId`       INT(11)                NOT NULL,
  `commentDate`  DATETIME               NOT NULL,
  `comment`      TEXT                   NOT NULL
  COMMENT '评论内容',
  `commentType`  VARCHAR(10)            NOT NULL
  COMMENT 'menu表示菜单，cooking表示菜谱，show表示作品',
  `commentForId` INT(11)                NOT NULL,
  `state`        INT(1)                 NOT NULL DEFAULT '0',
  PRIMARY KEY (`commentId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '4', '2016-04-21 15:42:55', '要吃死人了', 'cooking', '2', '0');

-- ----------------------------
-- Table structure for cooking
-- ----------------------------
DROP TABLE IF EXISTS `cooking`;
CREATE TABLE `cooking` (
  `cookingName`    VARCHAR(20)
                   CHARACTER SET utf8
                   COLLATE utf8_bin NOT NULL,
  `cookingId`      INT(11)          NOT NULL AUTO_INCREMENT,
  `cookingStyle`   VARCHAR(50)      NOT NULL
  COMMENT '菜谱类型',
  `cookingDate`    DATETIME                  DEFAULT NULL
  COMMENT '菜谱创建时间',
  `authorId`       INT(11)          NOT NULL,
  `cookingPicture` VARCHAR(50)      NOT NULL
  COMMENT '菜谱封面',
  `cookingIntro`   TEXT,
  `tips`           TEXT COMMENT '小贴士',
  `cookingLikeNum` INT(11)          NOT NULL DEFAULT '0',
  `step`           LONGTEXT         NOT NULL
  COMMENT '步骤，图片',
  `ingredient`     TEXT             NOT NULL
  COMMENT '原材料',
  `state`          INT(1)           NOT NULL DEFAULT '0'
  COMMENT '菜谱状态 0正常，1已删除',
  PRIMARY KEY (`cookingId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of cooking
-- ----------------------------
INSERT INTO `cooking` VALUES ('红烧肉', '1', '家常菜', '2016-04-14 00:00:00', '2',
                                     'C:\\Users\\Administrator\\Desktop\\秀厨网合集\\秀厨网文档\\图片', '含糖较高',
                                     '糖尿病患者不要吃', '3', '五花肉用大火煮熟，切成小块，加酱油、糖爆炒，加水，姜蒜焖煮', '五花肉-糖-姜-酱油',
                              '0');
INSERT INTO `cooking` VALUES
  ('妙脆角炒辣条', '2', '黑暗料理', '2016-04-19 15:40:57', '3', 'nicai', '可以尝试', '小心', '1', '妙脆角炒辣条',
             '妙脆角，辣条', '0');

-- ----------------------------
-- Table structure for cooking_like
-- ----------------------------
DROP TABLE IF EXISTS `cooking_like`;
CREATE TABLE `cooking_like` (
  `cookingId` INT(11) NOT NULL,
  `userId`    INT(11) NOT NULL,
  `state`     INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`cookingId`, `userId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of cooking_like
-- ----------------------------
INSERT INTO `cooking_like` VALUES ('1', '1', '0');
INSERT INTO `cooking_like` VALUES ('1', '2', '0');
INSERT INTO `cooking_like` VALUES ('1', '4', '0');
INSERT INTO `cooking_like` VALUES ('2', '2', '0');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `userId`   INT(11) NOT NULL,
  `friendId` INT(11) NOT NULL
  COMMENT '被关注的好友',
  `state`    INT(1)  NOT NULL DEFAULT '0'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('2', '3', '0');
INSERT INTO `friend` VALUES ('3', '4', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId`      INT(11) AUTO_INCREMENT NOT NULL,
  `authorId`    INT(11)                NOT NULL
  COMMENT '作者Id',
  `menuName`    VARCHAR(20)            NOT NULL,
  `menuPicture` VARCHAR(50)            NOT NULL,
  `menuLikeNum` INT(10)                NOT NULL DEFAULT '0'
  COMMENT '点赞数',
  `menuDate`    DATETIME               NOT NULL,
  `state`       INT(1)                 NOT NULL DEFAULT '0'
  COMMENT '状态，0为正常,1表示删除',
  PRIMARY KEY (`menuId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '4', '所有人都过来', 'bugei ', '1', '2016-04-23 15:44:48', '0');

-- ----------------------------
-- Table structure for menu_cooking
-- ----------------------------
DROP TABLE IF EXISTS `menu_cooking`;
CREATE TABLE `menu_cooking` (
  `menuId`    INT(11) NOT NULL,
  `cookingId` INT(11) NOT NULL,
  `state`     INT(1)  NOT NULL DEFAULT '0',
  PRIMARY KEY (`menuId`, `cookingId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of menu_cooking
-- ----------------------------
INSERT INTO `menu_cooking` VALUES ('1', '1', '0');
INSERT INTO `menu_cooking` VALUES ('1', '2', '0');

-- ----------------------------
-- Table structure for menu_like
-- ----------------------------
DROP TABLE IF EXISTS `menu_like`;
CREATE TABLE `menu_like` (
  `menuId` INT(11) NOT NULL,
  `userId` INT(11) NOT NULL,
  `state`  INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`menuId`, `userId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of menu_like
-- ----------------------------
INSERT INTO `menu_like` VALUES ('1', '2', '0');
INSERT INTO `menu_like` VALUES ('1', '3', '0');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId`     INT(11)  NOT NULL  AUTO_INCREMENT,
  `messageUserId` INT(11)  NOT NULL
  COMMENT '留言用户',
  `messageDate`   DATETIME NOT NULL,
  `message`       LONGTEXT NOT NULL,
  `userId`        INT(11)  NOT NULL
  COMMENT '被留言用户',
  `shareId`       INT(11)            DEFAULT NULL
  COMMENT '分享Id',
  `showId`        INT(11)            DEFAULT NULL
  COMMENT '作品Id',
  `state`         INT(1)   NOT NULL  DEFAULT '0',
  PRIMARY KEY (`messageId`),
  UNIQUE KEY `message_messageId_uindex` (`messageId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '2', '2016-04-25 09:20:54', '吃了么', '3', NULL, NULL, '0');
INSERT INTO `message` VALUES ('2', '3', '2016-04-25 09:21:24', '没有', '2', NULL, NULL, '0');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `reportId`       INT(11) NOT NULL AUTO_INCREMENT,
  `userId`         INT(11) NOT NULL
  COMMENT '发布举报的用户Id',
  `reportType`     VARCHAR(255)     DEFAULT NULL
  COMMENT '被举报的类型，user，menu，cooking，show',
  `reportedItemId` INT(11)          DEFAULT NULL
  COMMENT '被举报的对象Id',
  `reportDate`     DATETIME         DEFAULT NULL
  COMMENT '举报时间',
  `reportReason`   TEXT    NOT NULL
  COMMENT '举报原因',
  `state`          INT(1)  NOT NULL DEFAULT '0'
  COMMENT '举报处理状态，0未处理，1已处理',
  `result`         VARCHAR(255)     DEFAULT NULL
  COMMENT '处理结果，deleteItem表示删除被举报对象，nil表示举报不符，直接关闭举报',
  `closeDate`      DATETIME         DEFAULT NULL
  COMMENT '举报关闭时间',
  PRIMARY KEY (`reportId`),
  UNIQUE KEY `report_reportId_uindex` (`reportId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report`
VALUES ('1', '3', 'user', '3', '2016-04-11 15:47:26', '发布黑暗料理', '0', NULL, NULL);

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `shareId`     INT(11) AUTO_INCREMENT NOT NULL,
  `shareUserId` INT(11)                NOT NULL
  COMMENT '分享用户Id',
  `shareDate`   DATETIME               NOT NULL
  COMMENT '分享发布时间',
  `shareType`   VARCHAR(10)                      DEFAULT NULL
  COMMENT '分享类型，cooking，menu',
  `itemId`      INT(11)                          DEFAULT NULL
  COMMENT '分享的cooking或menu的Id',
  `shareConent` TEXT                   NOT NULL
  COMMENT '分享配文',
  `state`       INT(1)                 NOT NULL  DEFAULT '0',
  PRIMARY KEY (`shareId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', '4', '2016-04-23 15:49:41', 'cooking', '2', '食我大diao', '0');

-- ----------------------------
-- Table structure for show
-- ----------------------------
DROP TABLE IF EXISTS `show`;
CREATE TABLE `show` (
  `showId`      INT(11) AUTO_INCREMENT NOT NULL,
  `showIntro`   VARCHAR(255)           NOT NULL
  COMMENT '介绍',
  `showPicture` VARCHAR(50)            NOT NULL,
<<<<<<< HEAD
  `cookingId`   INT(11)                NOT NULL
  COMMENT '关联菜谱Id',
  `showDate`    DATETIME               NOT NULL,
  `userId`      INT(11)                NOT NULL,
=======
  `cookingId`   INT(11) DEFAULT '0'    NOT NULL
  COMMENT '关联菜谱Id',
  `showDate`    DATETIME               NOT NULL,
  `authorId`    INT(11)                NOT NULL,
>>>>>>> 13d8d3671c1558c4b0167cd9f5af70b298ce74e8
  `showLikeNum` INT(10)                NOT NULL DEFAULT '0',
  `state`       INT(1)                 NOT NULL DEFAULT '0'
  COMMENT '作品状态，0为正常',
  PRIMARY KEY (`showId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of show
-- ----------------------------
INSERT INTO `show` VALUES ('1', '红烧肉很不错', 'dd', '1', '2016-04-20 15:52:28', '4', '1', '0');

-- ----------------------------
-- Table structure for show_like
-- ----------------------------
DROP TABLE IF EXISTS `show_like`;
CREATE TABLE `show_like` (
  `showId` INT(11) NOT NULL,
  `userId` INT(11) NOT NULL,
  `state`  INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`showId`, `userId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of show_like
-- ----------------------------
INSERT INTO `show_like` VALUES ('1', '3', '0');

-- ----------------------------
-- Table structure for star
-- ----------------------------
DROP TABLE IF EXISTS `star`;
CREATE TABLE `star` (
  `starId`   INT(11) AUTO_INCREMENT NOT NULL,
  `userId`   INT(11)                NOT NULL,
  `starType` VARCHAR(10)            NOT NULL
  COMMENT '收藏类型，cooking代表菜谱,menu代表菜单',
  `itemId`   INT(11) DEFAULT NULL
  COMMENT '具体收藏的menu或者cooking的Id',
  `starDate` DATETIME               NOT NULL
  COMMENT '收藏时间',
  `state`    INT(1)  DEFAULT '0'
  COMMENT '0表示正常，1表示已删除',
  PRIMARY KEY (`starId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of star
-- ----------------------------
INSERT INTO `star` VALUES ('1', '2', 'menu', '1', '2016-04-24 15:49:17', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId`      INT(11)     NOT NULL AUTO_INCREMENT,
  `role`        VARCHAR(10) NOT NULL
  COMMENT '用户权限,user,admin',
  `account`     VARCHAR(15) NOT NULL
  COMMENT '用户账号名（手机号）',
  `password`    VARCHAR(50) NOT NULL,
  `email`       VARCHAR(50)          DEFAULT NULL,
  `nickName`    VARCHAR(20)          DEFAULT NULL
  COMMENT '用户昵称',
  `userPicture` VARCHAR(50)          DEFAULT NULL
  COMMENT '用户头像',
  `name`        VARCHAR(20)          DEFAULT NULL
  COMMENT '用户真实姓名',
  `gender`      VARCHAR(6)           DEFAULT NULL
  COMMENT '性别，',
  `birth`       DATE                 DEFAULT NULL
  COMMENT '出生日期',
  `job`         VARCHAR(10)          DEFAULT NULL,
  `city`        VARCHAR(20)          DEFAULT NULL,
  `userIntro`   TEXT COMMENT '自我介绍',
  `state`       INT(1)               DEFAULT '0'
  COMMENT '用户状态，0表示正常，1表示受限制',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `table_name_userId_uindex` (`userId`),
  UNIQUE KEY `table_name_account_uindex` (`account`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES
  ('1', 'ADMIN', '1111111111', 'xiuchuwang', '', '管理员', NULL, 'admin', 'male', NULL, NULL, NULL,
   NULL,
   '0');
INSERT INTO `user` VALUES
  ('2', 'USER', '1337315561', '123456', '123456@qq.com', '队长', NULL, '杨永宁', 'male', '2012-09-22',
        '学生',
   '武汉', '老子日~', '0');
INSERT INTO `user` VALUES
  ('3', 'USER', '1283399465', '987654', '666666@162.com', '研', NULL, '石开', 'male', '2014-08-07',
        'S6传奇选手', '武汉', '带不动啊', '0');
INSERT INTO `user` VALUES
  ('4', 'USER', '1888888888', 'cyw', '188888@115.com', '瓜伟', NULL, '陈宜伟', 'female', '2016-04-12',
        '夜店王子', '武汉', '给我来十个', '1');
