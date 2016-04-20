
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
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `authority` int(1) NOT NULL DEFAULT '0' COMMENT '用户权限',
  `account` int(11) NOT NULL COMMENT '用户账号名（手机号）',
  `password` varchar(50) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `userPicture` varchar(50) DEFAULT NULL COMMENT '用户头像',
  `name` varchar(20) DEFAULT NULL COMMENT '用户真实姓名',
  `sex` varchar(6) DEFAULT NULL,
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `job` varchar(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `userIntro` text COMMENT '自我介绍',
  `otherAuthority` int(1) NOT NULL DEFAULT '0' COMMENT '他人浏览权限',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `table_name_userID_uindex` (`userID`),
  UNIQUE KEY `table_name_account_uindex` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
CREATE TABLE `collection` (
  `collectionID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `menuID` int(11) DEFAULT NULL,
  `cookingID` int(11) DEFAULT NULL,
  `collectDate` datetime NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`collectionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `comment` (
  `commentID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `commentDate` datetime NOT NULL,
  `comment` text NOT NULL COMMENT '评论内容',
  `menuID` int(11) DEFAULT NULL,
  `cookingID` int(11) DEFAULT NULL,
  `showID` int(11) DEFAULT NULL,
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cooking` (
  `cookingName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `cookingID` int(11) NOT NULL,
  `cookingStyle` varchar(255) NOT NULL COMMENT '菜谱类型',
  `cookingStyleID` int(11) DEFAULT NULL,
  `cookingDate` datetime DEFAULT NULL COMMENT '菜谱创建时间',
  `authorID` int(11) NOT NULL,
  `cookingPicture` varchar(50) NOT NULL COMMENT '菜谱封面',
  `cookingIntro` text,
  `tips` text COMMENT '小贴士',
  `cookingLike` int(11) NOT NULL DEFAULT '0',
  `step` longtext NOT NULL COMMENT '步骤，图片',
  `ingredient` text NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '菜单状态',
  PRIMARY KEY (`cookingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cookinglike` (
  `cookingID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`cookingID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cooking-show` (
  `cookingID` int(11) NOT NULL,
  `showID` int(11) NOT NULL,
  PRIMARY KEY (`cookingID`,`showID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `friends` (
  `userID` int(11) NOT NULL,
  `friendID` int(11) NOT NULL COMMENT '被关注的好友'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `menu` (
  `menuID` int(11) NOT NULL,
  `userID` int(11) NOT NULL COMMENT '菜单创建人ID',
  `menuName` varchar(20) NOT NULL,
  `menuPicture` varchar(50) NOT NULL,
  `menuLike` int(10) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `menuDate` datetime NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0为正常',
  PRIMARY KEY (`menuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `menu-cooking` (
  `menuID` int(11) NOT NULL,
  `cookingID` int(11) NOT NULL,
  PRIMARY KEY (`menuID`,`cookingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `menulike` (
  `menuID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`menuID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `message` (
  `messageID` int(11) NOT NULL AUTO_INCREMENT,
  `messageUserID` int(11) NOT NULL COMMENT '留言用户',
  `messageDate` datetime NOT NULL,
  `message` longtext NOT NULL,
  `userID` int(11) NOT NULL COMMENT '被留言用户',
  `shareID` int(11) DEFAULT NULL COMMENT '分享ID',
  `showID` int(11) DEFAULT NULL COMMENT '作品ID',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '留言状态，0为正常',
  PRIMARY KEY (`messageID`),
  UNIQUE KEY `message_messageID_uindex` (`messageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `report` (
  `reportID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL COMMENT '发布举报的用户ID',
  `ReportedUserID` int(11) DEFAULT NULL COMMENT '被举报的用户ID',
  `menuID` int(11) DEFAULT NULL COMMENT '被举报的菜单ID',
  `cookingID` int(11) DEFAULT NULL COMMENT '被举报的菜谱ID',
  `showID` int(11) DEFAULT NULL COMMENT '被举报的作品ID',
  `reportDate` datetime DEFAULT NULL COMMENT '举报时间',
  `reportReason` text NOT NULL COMMENT '举报原因',
  `reportState` int(1) NOT NULL DEFAULT '0' COMMENT '举报处理状态，',
  `closeDate` datetime DEFAULT NULL COMMENT '举报关闭时间',
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `report_reportID_uindex` (`reportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `share` (
  `shareID` int(11) NOT NULL,
  `shareUserID` int(11) NOT NULL COMMENT '分享用户ID',
  `shareDate` datetime NOT NULL COMMENT '分享发布时间',
  `menuID` int(11) DEFAULT NULL,
  `cookingID` int(11) DEFAULT NULL,
  `shareConent` text NOT NULL COMMENT '分享配文',
  PRIMARY KEY (`shareID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sharelike` (
  `shareID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，取消点赞更改为1',
  PRIMARY KEY (`shareID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
CREATE TABLE `show` (
  `showID` int(11) NOT NULL,
  `showName` varchar(255) NOT NULL,
  `showPicture` varchar(50) NOT NULL,
  `showDate` datetime NOT NULL,
  `userID` int(11) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `showLike` int(10) NOT NULL DEFAULT '0',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '作品状态，0为正常',
  PRIMARY KEY (`showID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;