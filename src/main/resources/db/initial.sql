DROP TABLE IF EXISTS `collection`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `cooking`;
DROP TABLE IF EXISTS `cookinglike`;
DROP TABLE IF EXISTS `cooking-show`;
DROP TABLE IF EXISTS `friends`;
DROP TABLE IF EXISTS `menu`;
DROP TABLE IF EXISTS `menu-cooking`;
DROP TABLE IF EXISTS `menulike`;
DROP TABLE IF EXISTS `message`;
DROP TABLE IF EXISTS `report`;
DROP TABLE IF EXISTS `share`;
DROP TABLE IF EXISTS `sharelike`;
DROP TABLE IF EXISTS `show`;
DROP TABLE IF EXISTS `user`;

# user 表
CREATE TABLE `user` (
  `id`             INT(11)     NOT NULL AUTO_INCREMENT,
  `role`           VARCHAR(20) NOT NULL
  COMMENT '用户权限',
  `username`    INT(11)     NOT NULL
  COMMENT '用户账号名（手机号）',
  `password`       VARCHAR(50) NOT NULL
  COMMENT '密码,应该是加密过的',
  `email`          VARCHAR(50)          DEFAULT NULL
  COMMENT '备用邮箱,找回密码',
  `nickname`       VARCHAR(20)          DEFAULT NULL
  COMMENT '用户昵称',
  `avatar`    VARCHAR(50)          DEFAULT NULL
  COMMENT '用户头像',
  `name`           VARCHAR(20)          DEFAULT NULL
  COMMENT '用户真实姓名',
  `sex`            VARCHAR(6)           DEFAULT NULL
  COMMENT '性别',
  `birth`          DATE                 DEFAULT NULL
  COMMENT '出生日期',
  `job`            VARCHAR(10)          DEFAULT NULL
  COMMENT '工作',
  `city`           VARCHAR(20)          DEFAULT NULL
  COMMENT '居住城市',
  `userIntro`      TEXT                 DEFAULT NULL
  COMMENT '自我介绍',
  `otherAuthority` INT(1)      NOT NULL DEFAULT '0'
  COMMENT '他人浏览权限',

  PRIMARY KEY (id),
  UNIQUE KEY `table_name_userID_uindex` (id),
  UNIQUE KEY `table_name_account_uindex` (username)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `collection` (
  `collectionID` INT(11)  NOT NULL,
  `userID`       INT(11)  NOT NULL,
  `menuID`       INT(11) DEFAULT NULL,
  `cookingID`    INT(11) DEFAULT NULL,
  `collectDate`  DATETIME NOT NULL
  COMMENT '收藏时间',
  PRIMARY KEY (`collectionID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `comment` (
  `commentID`   INT(11)  NOT NULL,
  `userID`      INT(11)  NOT NULL,
  `commentDate` DATETIME NOT NULL,
  `comment`     TEXT     NOT NULL
  COMMENT '评论内容',
  `menuID`      INT(11) DEFAULT NULL,
  `cookingID`   INT(11) DEFAULT NULL,
  `showID`      INT(11) DEFAULT NULL,
  PRIMARY KEY (`commentID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `cooking` (
  `cookingName`    VARCHAR(20)
                   CHARACTER SET utf8
                   COLLATE utf8_bin NOT NULL,
  `cookingID`      INT(11)          NOT NULL,
  `cookingStyle`   VARCHAR(255)     NOT NULL
  COMMENT '菜谱类型',
  `cookingStyleID` INT(11)                   DEFAULT NULL,
  `cookingDate`    DATETIME                  DEFAULT NULL
  COMMENT '菜谱创建时间',
  `authorID`       INT(11)          NOT NULL,
  `cookingPicture` VARCHAR(50)      NOT NULL
  COMMENT '菜谱封面',
  `cookingIntro`   TEXT,
  `tips`           TEXT COMMENT '小贴士',
  `cookingLike`    INT(11)          NOT NULL DEFAULT '0',
  `step`           LONGTEXT         NOT NULL
  COMMENT '步骤，图片',
  `ingredient`     TEXT             NOT NULL,
  `state`          INT(1)           NOT NULL DEFAULT '0'
  COMMENT '菜单状态',
  PRIMARY KEY (`cookingID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `cookinglike` (
  `cookingID` INT(11) NOT NULL,
  `userID`    INT(11) NOT NULL,
  `state`     INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`cookingID`, `userID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `cooking-show` (
  `cookingID` INT(11) NOT NULL,
  `showID`    INT(11) NOT NULL,
  PRIMARY KEY (`cookingID`, `showID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `friends` (
  `userID`   INT(11) NOT NULL,
  `friendID` INT(11) NOT NULL
  COMMENT '被关注的好友'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `menu` (
  `menuID`      INT(11)     NOT NULL,
  `userID`      INT(11)     NOT NULL
  COMMENT '菜单创建人ID',
  `menuName`    VARCHAR(20) NOT NULL,
  `menuPicture` VARCHAR(50) NOT NULL,
  `menuLike`    INT(10)     NOT NULL DEFAULT '0'
  COMMENT '点赞数',
  `menuDate`    DATETIME    NOT NULL,
  `state`       INT(1)      NOT NULL DEFAULT '0'
  COMMENT '状态，0为正常',
  PRIMARY KEY (`menuID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `menu-cooking` (
  `menuID`    INT(11) NOT NULL,
  `cookingID` INT(11) NOT NULL,
  PRIMARY KEY (`menuID`, `cookingID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `menulike` (
  `menuID` INT(11) NOT NULL,
  `userID` INT(11) NOT NULL,
  `state`  INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`menuID`, `userID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `message` (
  `messageID`     INT(11)  NOT NULL AUTO_INCREMENT,
  `messageUserID` INT(11)  NOT NULL
  COMMENT '留言用户',
  `messageDate`   DATETIME NOT NULL,
  `message`       LONGTEXT NOT NULL,
  `userID`        INT(11)  NOT NULL
  COMMENT '被留言用户',
  `shareID`       INT(11)           DEFAULT NULL
  COMMENT '分享ID',
  `showID`        INT(11)           DEFAULT NULL
  COMMENT '作品ID',
  `state`         INT(1)   NOT NULL DEFAULT '0'
  COMMENT '留言状态，0为正常',
  PRIMARY KEY (`messageID`),
  UNIQUE KEY `message_messageID_uindex` (`messageID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `report` (
  `reportID`       INT(11) NOT NULL AUTO_INCREMENT,
  `userID`         INT(11) NOT NULL
  COMMENT '发布举报的用户ID',
  `ReportedUserID` INT(11)          DEFAULT NULL
  COMMENT '被举报的用户ID',
  `menuID`         INT(11)          DEFAULT NULL
  COMMENT '被举报的菜单ID',
  `cookingID`      INT(11)          DEFAULT NULL
  COMMENT '被举报的菜谱ID',
  `showID`         INT(11)          DEFAULT NULL
  COMMENT '被举报的作品ID',
  `reportDate`     DATETIME         DEFAULT NULL
  COMMENT '举报时间',
  `reportReason`   TEXT    NOT NULL
  COMMENT '举报原因',
  `reportState`    INT(1)  NOT NULL DEFAULT '0'
  COMMENT '举报处理状态，',
  `closeDate`      DATETIME         DEFAULT NULL
  COMMENT '举报关闭时间',
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `report_reportID_uindex` (`reportID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `share` (
  `shareID`     INT(11)  NOT NULL,
  `shareUserID` INT(11)  NOT NULL
  COMMENT '分享用户ID',
  `shareDate`   DATETIME NOT NULL
  COMMENT '分享发布时间',
  `menuID`      INT(11) DEFAULT NULL,
  `cookingID`   INT(11) DEFAULT NULL,
  `shareConent` TEXT     NOT NULL
  COMMENT '分享配文',
  PRIMARY KEY (`shareID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# user 表
CREATE TABLE `sharelike` (
  `shareID` INT(11) NOT NULL,
  `userID`  INT(11) NOT NULL,
  `state`   INT(1)  NOT NULL DEFAULT '0'
  COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`shareID`, `userID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

# user 表
CREATE TABLE `show` (
  `showID`      INT(11)      NOT NULL,
  `showName`    VARCHAR(255) NOT NULL,
  `showPicture` VARCHAR(50)  NOT NULL,
  `showDate`    DATETIME     NOT NULL,
  `userID`      INT(11)      NOT NULL,
  `userName`    VARCHAR(20)  NOT NULL,
  `showLike`    INT(10)      NOT NULL DEFAULT '0',
  `state`       INT(1)       NOT NULL DEFAULT '0'
  COMMENT '作品状态，0为正常',
  PRIMARY KEY (`showID`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# mock 数据

# user
INSERT INTO design.user (role, username, password, email, nickname, avatar,
                         name, sex, birthday, job, city, userIntro, visitor) VALUES (
  'USER', '13377871349', '128098', '727615480@qq.com', 'lxh', NULL,
          'lxh', 'male', '1993-08-12', 'engineer', 'xingtai', 'I''m a student', '0');
INSERT INTO design.user (role, username, password, email, nickname, avatar,
                         name, sex, birthday, job, city, userIntro, visitor) VALUES (
  'ADMIN', '12345678902', '123456', '727615480@qq.com', 'lily', NULL,
           'lily', 'female', '1993-09-25', 'engineer', 'chongqing', 'I''m a student', '0');



