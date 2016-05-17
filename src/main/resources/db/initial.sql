/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : design

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-05-17 09:44:41
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId`    INT(11)     NOT NULL AUTO_INCREMENT,
  `userId`       INT(11)     NOT NULL,
  `commentDate`  DATETIME    NOT NULL,
  `comment`      TEXT        NOT NULL
  COMMENT '评论内容',
  `commentType`  VARCHAR(10) NOT NULL
  COMMENT 'menu表示菜单，cooking表示菜谱，show表示作品',
  `commentForId` INT(11)     NOT NULL,
  `state`        INT(1)      NOT NULL DEFAULT '0'
  COMMENT '评论状态，0为正常，1为删除',
  PRIMARY KEY (`commentId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '4', '2016-04-21 15:42:55', '要吃死人了', 'cooking', '2', '0');
INSERT INTO `comment` VALUES ('2', '7', '2016-05-05 09:05:28', '很好，很不错', 'menu', '2', '0');
INSERT INTO `comment` VALUES ('3', '3', '2016-05-05 19:07:02', '茄子很下饭', 'cooking', '6', '0');
INSERT INTO `comment`
VALUES ('4', '4', '2016-05-05 20:07:51', '茄子很合胃口，我吃了三大碗', 'cooking', '6', '0');
INSERT INTO `comment` VALUES ('5', '7', '2016-05-06 09:08:32', '最喜欢茄子了', 'cooking', '6', '0');

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
  AUTO_INCREMENT = 7
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
INSERT INTO `cooking` VALUES ('春饼蒸饼皮', '3', '辅料', '2016-05-03 10:12:51', '6', 'qq',
                                       '属于自己饼皮的方子。卷上京酱肉丝，葱丝，黄瓜段，酸辣土豆丝，豆芽菜，韭菜鸡蛋味道都是一流的。', '油要多', '4',
                                       '1.面粉加开水用筷子搅拌，再加凉水一起揉至面团光滑。#2.醒面30分钟，这时候可以炒菜。#3.面团排气后揉成30克一个个小面团。按扁涂上食用油再上面重叠一个小面团，开始擀皮～薄薄的（其实相当于一次擀2张皮）。#\r\n4.蒸锅上水开锅后放入擀好的皮，擀一张皮放一张，只要发现锅里的皮鼓包了就算是好了。#5.吃至少把一张皮撕成两张皮。这时你会发现皮是又薄又透明，筋道又好的饼皮！',
                                       '普通面粉 300克\r\n 开水 110克 凉水 50克', '0');
INSERT INTO `cooking` VALUES
  ('爱心早餐', '4', '早餐', '2016-05-04 06:09:27', '6', 'cooking\\爱心早餐.jpg', '爱心早餐 新的一天满满正能量 健康又营养～',
           '早餐很重要哦~大家一定要记得吃早餐。', '5',
           '火腿肠去肠衣 一分为二&C:\\Users\\Administrator\\Desktop\\资源图片\\steps\\爱心早餐1#切好的火腿再一分为二 平面在下 弧面在上 用刀平均切开 尾部留2-3公分不切&C:\\Users\\Administrator\\Desktop\\资源图片\\steps\\爱心早餐2#锅内放油 待油热 火腿热后变软 将两端慢慢弯曲&C:\\Users\\Administrator\\Desktop\\资源图片\\steps\\爱心早餐3#用牙签 将心尖固定&C:\\Users\\Administrator\\Desktop\\资源图片\\steps\\爱心早餐4#做好爱心后 开火 火不要大 锅热后 将鸡蛋打入心内 还可以将蛋黄轻轻挑破 把蛋黄浇均匀 这样颜色更漂亮&C:\\Users\\Administrator\\Desktop\\资源图片\\steps\\爱心早餐5',
           '火腿1～2。鸡蛋2～4', '0');
INSERT INTO `cooking` VALUES
  ('长沙口味虾（啤酒版）', '5', '湘菜', '2016-05-04 16:38:09', '5', 'cooking\\长沙口味虾（啤酒版）.jpg',
                 '饭店里的配方，超辣超爽口，再也不用去吃地沟油了，自己做的更健康！\r\n这个配方做的龙虾，虾壳多汁，肉质鲜嫩,该配方还可以用来做口味牛蛙和口味蛇\r\n重口味次货们一起享受味觉盛宴吧',
                 '如何清洗虾子？这是很重的，虾子有多脏不用多说，饭店很多虾子肚子都是黑呼呼的，绝对是没有洗的。1，首先，用剪刀夹住虾头，一只手抓住虾的头中间，这样做是以免被夹伤，直接把头壳拔掉，里面黄黄黑黑的就自己掉了，头两边有些络腮一并清理干净。2,抓住虾尾的中间，尾巴有三片，抓住中间一片，上下摇晃，扯出虾肠很简单的。3，最后就是用牙刷刷刷虾肚子，虾钳太脏也一并刷刷。\r\n如果要制作虾尾就更加简单，头一起掰掉，只用刷刷肚子拔掉虾肠',
                 '3',
                 '活虾清洗干净（具体清洗方法见备注）洗干净后用盐腌制15分钟，然后控干水份#冷油爆香桂皮，大火炒虾至收水#大茴、花椒、白果、草果、香叶、姜、蒜果入锅翻炒30秒！倒入2两啤酒，焖3分钟！#\r\n辣椒入锅翻炒，3两啤酒入锅，盖锅盖中火焖5～10分钟，期间注意锅内，糊锅可以外适量添加啤酒！#加入鸡精翻炒，最后加入紫苏，蚝油，快速翻炒起锅。～一定要最后加蚝油，蚝油容易糊锅！最后加紫苏这个步骤不要犹豫，快速出锅即可',
                 '桂皮，越薄的桂皮越香。大茴，菜市场都有卖。花椒，青色花椒麻，红色花椒香，黑色花椒次品。白果，草果，香叶，姜，老姜\r\n蒜果，辣椒，越辣越好。啤酒，一听装就可以了，玻璃瓶大概半瓶。紫苏',
   '0');
INSERT INTO `cooking` VALUES
  ('肉末茄子', '6', '下饭菜', '2016-05-05 11:10:02', '6', 'cooking\\肉末茄子.jpg', '超下饭的肉末茄子',
           '茄子是个废油的家伙~挑茄子也是个技术活，但将茄子切丁，就算是挑到了老茄子也还能将就吃', '2',
           '茄子切丁用少许盐腌一会，猪肉剁成末，用料酒、糖、生抽拌匀备用#茄子腌出的水倒掉，用清水冲一下，滤干水，起油锅，将茄子炒软盛起备用。#锅里再加点油，加入豆瓣酱和姜、葱、蒜末爆香，倒入肉末，加点料酒炒至肉末变白后倒入茄子，炒匀，加入白砂糖、生抽和盐，上锅前用淀粉勾个芡，汁水收干即可熄心。盛到碟子里再撒点葱花，臭美一下~',
           '中等身形的茄子,2条。猪肉末，至少要100g啦。姜末、葱末、蒜末各适量。生抽少许。料酒少许。白砂糖少许。盐少许。淀粉少许。豆瓣酱,1大勺。', '0');

-- ----------------------------
-- Table structure for cooking_like
-- ----------------------------
DROP TABLE IF EXISTS `cooking_like`;
CREATE TABLE `cooking_like` (
  `cookingId` INT(11) NOT NULL,
  `userId`    INT(11) NOT NULL,
  `state`     INT(1)  NOT NULL DEFAULT '0'
  COMMENT '点赞状态，取消点赞更改为1',
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
INSERT INTO `cooking_like` VALUES ('3', '2', '0');
INSERT INTO `cooking_like` VALUES ('3', '3', '0');
INSERT INTO `cooking_like` VALUES ('3', '4', '0');
INSERT INTO `cooking_like` VALUES ('3', '7', '0');
INSERT INTO `cooking_like` VALUES ('4', '2', '0');
INSERT INTO `cooking_like` VALUES ('4', '3', '0');
INSERT INTO `cooking_like` VALUES ('4', '4', '0');
INSERT INTO `cooking_like` VALUES ('4', '5', '0');
INSERT INTO `cooking_like` VALUES ('4', '7', '0');
INSERT INTO `cooking_like` VALUES ('5', '2', '0');
INSERT INTO `cooking_like` VALUES ('5', '4', '0');
INSERT INTO `cooking_like` VALUES ('5', '7', '0');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `userId`   INT(11) NOT NULL,
  `friendId` INT(11) NOT NULL
  COMMENT '被关注的好友',
  `state`    INT(1) DEFAULT '0'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('2', '3', '0');
INSERT INTO `friend` VALUES ('3', '4', '0');
INSERT INTO `friend` VALUES ('1', '2', '0');

-- ----------------------------
-- Table structure for headline
-- ----------------------------
DROP TABLE IF EXISTS `headline`;
CREATE TABLE `headline` (
  `headlineId`    INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '头条推荐',
  `cookingId`     INT(11) NOT NULL,
  `headlineIntro` VARCHAR(255)     DEFAULT NULL
  COMMENT '管理员书写简介',
  PRIMARY KEY (`headlineId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of headline
-- ----------------------------
INSERT INTO `headline` VALUES ('1', '4', '特有爱的早餐');
INSERT INTO `headline` VALUES ('2', '5', '夏日超赞小龙虾');
INSERT INTO `headline` VALUES ('3', '6', '便宜方便又下饭');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId`      INT(11)     NOT NULL AUTO_INCREMENT,
  `authorId`    INT(11)     NOT NULL
  COMMENT '作者Id',
  `menuName`    VARCHAR(20) NOT NULL,
  `menuPicture` VARCHAR(50) NOT NULL,
  `menuLikeNum` INT(10)     NOT NULL DEFAULT '0'
  COMMENT '点赞数',
  `menuDate`    DATETIME    NOT NULL,
  `state`       INT(1)      NOT NULL DEFAULT '0'
  COMMENT '状态，0为正常,1表示删除',
  PRIMARY KEY (`menuId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '4', '所有人都过来', 'bugei ', '1', '2016-04-23 15:44:48', '0');
INSERT INTO `menu` VALUES ('2', '6', '夏日精选', 'cooking\\爱心早餐.jpg', '3', '2016-05-05 17:16:22', '0');

-- ----------------------------
-- Table structure for menu_cooking
-- ----------------------------
DROP TABLE IF EXISTS `menu_cooking`;
CREATE TABLE `menu_cooking` (
  `menuId`    INT(11) NOT NULL,
  `cookingId` INT(11) NOT NULL,
  PRIMARY KEY (`menuId`, `cookingId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of menu_cooking
-- ----------------------------
INSERT INTO `menu_cooking` VALUES ('1', '1');
INSERT INTO `menu_cooking` VALUES ('1', '2');
INSERT INTO `menu_cooking` VALUES ('2', '4');
INSERT INTO `menu_cooking` VALUES ('2', '5');
INSERT INTO `menu_cooking` VALUES ('2', '6');

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
INSERT INTO `menu_like` VALUES ('2', '3', '0');
INSERT INTO `menu_like` VALUES ('2', '5', '0');
INSERT INTO `menu_like` VALUES ('2', '7', '0');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId`     INT(11)  NOT NULL AUTO_INCREMENT,
  `messageUserId` INT(11)  NOT NULL
  COMMENT '留言用户',
  `messageDate`   DATETIME NOT NULL,
  `message`       LONGTEXT NOT NULL,
  `userId`        INT(11)  NOT NULL
  COMMENT '被留言用户',
  `shareId`       INT(11)           DEFAULT NULL
  COMMENT '分享Id',
  `showId`        INT(11)           DEFAULT NULL
  COMMENT '作品Id',
  `state`         INT(1)            DEFAULT '0'
  COMMENT '留言状态,0为正常，1已删除',
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
  `reportId`       INT(11)      NOT NULL AUTO_INCREMENT,
  `userId`         INT(11)      NOT NULL
  COMMENT '发布举报的用户Id',
  `reportType`     VARCHAR(255) NOT NULL
  COMMENT '被举报的类型，user，menu，cooking，show',
  `reportedItemId` INT(11)      NOT NULL
  COMMENT '被举报的对象Id',
  `reportDate`     DATETIME     NOT NULL
  COMMENT '举报时间',
  `reportReason`   TEXT         NOT NULL
  COMMENT '举报原因',
  `state`          INT(1)       NOT NULL DEFAULT '0'
  COMMENT '举报处理状态，0未处理，1已处理',
  `result`         VARCHAR(255)          DEFAULT NULL
  COMMENT '处理结果，deleteItem表示删除被举报对象，nil表示举报不符，直接关闭举报',
  `closeDate`      DATETIME              DEFAULT NULL
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
  `shareId`     INT(11)  NOT NULL,
  `shareUserId` INT(11)  NOT NULL
  COMMENT '发布分享用户Id',
  `shareDate`   DATETIME NOT NULL
  COMMENT '分享发布时间',
  `shareType`   VARCHAR(10) DEFAULT NULL
  COMMENT '分享类型，cooking，menu',
  `itemId`      INT(11)     DEFAULT NULL
  COMMENT '分享的cooking或menu的Id',
  `shareConent` TEXT     NOT NULL
  COMMENT '分享配文',
  `state`       INT(0)      DEFAULT '0',
  PRIMARY KEY (`shareId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', '4', '2016-04-23 15:49:41', 'cooking', '2', '123', '0');

-- ----------------------------
-- Table structure for show
-- ----------------------------
DROP TABLE IF EXISTS `show`;
CREATE TABLE `show` (
  `showId`      INT(11)     NOT NULL AUTO_INCREMENT,
  `showIntro`   VARCHAR(255)         DEFAULT NULL
  COMMENT '介绍',
  `showPicture` VARCHAR(50) NOT NULL,
  `cookingId`   INT(11)              DEFAULT NULL
  COMMENT '关联菜谱Id',
  `showDate`    DATETIME    NOT NULL,
  `userId`      INT(11)     NOT NULL,
  `showLikeNum` INT(10)     NOT NULL DEFAULT '0',
  `state`       INT(1)      NOT NULL DEFAULT '0'
  COMMENT '作品状态，0为正常',
  PRIMARY KEY (`showId`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of show
-- ----------------------------
INSERT INTO `show` VALUES ('1', '红烧肉很不错', 'dd', '1', '2016-04-20 15:52:28', '4', '1', '0');
INSERT INTO `show`
VALUES ('2', '唯一做好的一个！可是火腿肠糊啦！', '作品\\爱心早餐1.jpg', '4', '2016-05-05 07:24:59', '4', '0', '0');

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
  `starId`   INT(11)     NOT NULL
  COMMENT '收藏ID',
  `userId`   INT(11)     NOT NULL,
  `starType` VARCHAR(10) NOT NULL
  COMMENT '收藏类型，cooking代表菜谱,menu代表菜单',
  `itemId`   INT(11) DEFAULT NULL
  COMMENT '具体收藏的menu或者cooking的Id',
  `starDate` DATETIME    NOT NULL
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
  `account`     VARCHAR(11) NOT NULL
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
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES
  ('1', 'ADMIN', '1111111111', 'xiuchuwang', '', '管理员', NULL, 'admin', 'male', NULL, NULL, NULL,
   NULL, '0');
INSERT INTO `user` VALUES
  ('2', 'USER', '1337315561', '123456', '123456@qq.com', '队长', NULL, '杨永宁', 'male', '2012-09-22',
        '学生', '武汉', '老子日~', '0');
INSERT INTO `user` VALUES
  ('3', 'USER', '1283399465', '987654', '666666@162.com', '研', NULL, '石开', 'male', '2014-08-07',
        'S6传奇选手', '武汉', '带不动啊', '0');
INSERT INTO `user` VALUES
  ('4', 'USER', '1888888888', 'cyw', '188888@115.com', '瓜伟', NULL, '陈宜伟', 'female', '2016-04-12',
        '夜店王子', '武汉', '给我来十个', '1');
INSERT INTO `user` VALUES
  ('5', 'USER', '13588935789', 'a111111', '13588935789@qq.com', '二蛋', NULL, '李三', 'male',
        '2016-05-01', '厨师', '哈尔滨', '你瞅啥', '0');
INSERT INTO `user` VALUES
  ('6', 'USER', '882112111', 'aqqqqqq', '672581@qq.com', '行家', NULL, '王丽', 'female', '2016-05-02',
        '家庭主妇', '青岛', '想吃啥，我做啥', '0');
INSERT INTO `user` VALUES
  ('7', 'USER', '959595', '959595', '2294345@zair.com', 'King', NULL, 'gelir', 'meal', '2016-05-03',
        '国王', '柏格卜', '我在吃人的时候喜欢放点调料', '0');
