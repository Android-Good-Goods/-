/*
Navicat MySQL Data Transfer

Source Server         : haowu
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : haowu

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-05-31 21:44:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `Aid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `Anumber` varchar(255) DEFAULT NULL COMMENT '账单号',
  `Gid` int(11) unsigned DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '买家id',
  `Guid` int(11) unsigned DEFAULT NULL COMMENT '卖家id',
  `Abill` double(255,2) unsigned DEFAULT NULL COMMENT '交易金额',
  `Atime` varchar(255) DEFAULT NULL COMMENT '交易时间',
  `Astate` int(11) DEFAULT NULL COMMENT '订单状态，1表示等待卖家发货，2表示等待买家收货，3表示交易成功，4表示退款中，5表示交易失败（取消订单），6表示订单删除',
  PRIMARY KEY (`Aid`),
  UNIQUE KEY `aid` (`Aid`) USING BTREE,
  KEY `Agid` (`Gid`),
  KEY `Auid` (`Uid`),
  KEY `Atuid` (`Guid`),
  CONSTRAINT `Agid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Atuid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Auid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '21420191130204752', '4', '2', '1', '89.00', '2022-05-30 20:47:50', '6');
INSERT INTO `account` VALUES ('2', '21420191208172346', '4', '2', '1', '89.00', '2022-05-08 17:23:54', '6');
INSERT INTO `account` VALUES ('3', '21420191208172422', '4', '2', '1', '89.00', '2022-05-08 17:24:31', '3');
INSERT INTO `account` VALUES ('4', '12620191213123550', '6', '1', '2', '56.00', '2022-05-13 12:35:50', '5');
INSERT INTO `account` VALUES ('5', '21520191222155529', '5', '2', '1', '15.00', '2022-05-22 15:55:27', '6');
INSERT INTO `account` VALUES ('6', '12620191225221535', '6', '1', '2', '56.00', '2022-05-25 22:15:38', '5');
INSERT INTO `account` VALUES ('7', '12920191225234826', '9', '1', '2', '0.00', '2022-05-25 23:48:29', '6');
INSERT INTO `account` VALUES ('8', '12620220509235016', '6', '1', '2', '56.00', '2022-05-09 15:50:12', '5');
INSERT INTO `account` VALUES ('9', '12720220509235103', '7', '1', '2', '12.00', '2022-05-09 15:51:00', '5');
INSERT INTO `account` VALUES ('10', 'null2720220528152355', '7', null, '2', '12.00', '2022-05-28 07:23:53', '1');

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `Bid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '求购商品id',
  `Bname` varchar(255) DEFAULT NULL COMMENT '商品名',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `Bdetail` varchar(255) DEFAULT NULL COMMENT '商品细节',
  `Bsprice` double(255,2) unsigned DEFAULT NULL COMMENT '最低价',
  `Bbprice` double(255,2) unsigned DEFAULT NULL COMMENT '最高价',
  `Btype` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `Bhownew` varchar(255) DEFAULT NULL COMMENT '新旧程度',
  `Bgetway` varchar(255) DEFAULT NULL COMMENT '获取方式',
  `Baddress` varchar(255) DEFAULT NULL COMMENT '商品发布地址',
  `Btime` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `Bimage` varchar(255) DEFAULT NULL COMMENT '求购图片',
  `Bstate` int(10) unsigned DEFAULT NULL COMMENT '求购商品的状态，1求购状态，2求购到，3已删除',
  `Bscannum` int(11) unsigned DEFAULT NULL COMMENT '浏览人数',
  PRIMARY KEY (`Bid`),
  UNIQUE KEY `Bid` (`Bid`) USING BTREE,
  KEY `Uid` (`Uid`) USING BTREE,
  CONSTRAINT `Buid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES ('1', '手机', '1', '求购一个手机，价格可议，有的可以私聊我', '10.00', '2800.00', '手机数码', '八五新', '邮寄', '浙江省 杭州市', '2022-05-11 23:59:09', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/buydata/IMG_20190914_102013.jpg', '1', '8');
INSERT INTO `buy` VALUES ('2', '鞋子', '1', '求购一双这样的鞋子，最好是全新的，价格可以商量，有的可以私聊我', '100.00', '200.00', '服饰配件', '十成新', '邮寄', '浙江省 杭州市', '2022-05-12 00:29:41', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/buydata/-498401561.jpg', '1', '1');
INSERT INTO `buy` VALUES ('3', '双肩背包', '1', '求购双肩背包，类似图片这种，有的可以私聊我，价格可议', '80.00', '200.00', '箱包', '十成新', '邮寄', '浙江省 杭州市', '2022-05-13 11:36:02', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/buydata/1874051196.jpg', '1', '1');
INSERT INTO `buy` VALUES ('4', '3', '1', '3', '2.00', '3.00', '服饰配件', '十成新', '邮寄', '', '2022-05-28 07:29:10', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114373821746634126\\appdata/buydata/20220528072901.jpg', '1', '0');

-- ----------------------------
-- Table structure for buyconments
-- ----------------------------
DROP TABLE IF EXISTS `buyconments`;
CREATE TABLE `buyconments` (
  `Bconid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '求购商品的评论id',
  `Bid` int(11) unsigned DEFAULT NULL COMMENT '求购商品id',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '评论人id',
  `Buid` int(11) unsigned DEFAULT NULL COMMENT '求购发起人id',
  `Bcontime` varchar(255) DEFAULT NULL COMMENT '评论时间',
  `Bconcontent` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `Bconstate` int(11) unsigned DEFAULT NULL COMMENT '评论状态，1表示正常，2表示已删除',
  PRIMARY KEY (`Bconid`),
  UNIQUE KEY `bconid` (`Bconid`) USING BTREE,
  KEY `bconuid` (`Uid`) USING BTREE,
  KEY `bconbuid` (`Buid`) USING BTREE,
  KEY `bconbid` (`Bid`) USING BTREE,
  CONSTRAINT `bconbid` FOREIGN KEY (`Bid`) REFERENCES `buy` (`Bid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `bconbuid` FOREIGN KEY (`Buid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `bconuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyconments
-- ----------------------------
INSERT INTO `buyconments` VALUES ('1', '1', '1', '1', '2022-05-26 23:09:57', '我有一个，咱们私聊', '1');

-- ----------------------------
-- Table structure for charity
-- ----------------------------
DROP TABLE IF EXISTS `charity`;
CREATE TABLE `charity` (
  `Cid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '公益id',
  `Cname` varchar(255) DEFAULT NULL COMMENT '公益名称',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '发布公益的用户id',
  `Cimage` varchar(255) DEFAULT NULL COMMENT '公益宣传图片',
  `Cdetail` varchar(1000) DEFAULT NULL COMMENT '公益细节',
  `Cneed` varchar(1000) DEFAULT NULL COMMENT '公益需求',
  `Cnumber` int(11) unsigned DEFAULT NULL COMMENT '所需人数',
  `Ctime` varchar(255) DEFAULT NULL COMMENT '发起时间',
  `Cdeadline` varchar(255) DEFAULT NULL COMMENT '截止时间',
  `Ctype` varchar(255) DEFAULT NULL COMMENT '报名方式',
  `Caddress` varchar(255) DEFAULT NULL COMMENT '发布地址',
  `Cscannum` int(11) unsigned DEFAULT NULL COMMENT '浏览人数',
  `Cjoinnum` int(11) unsigned DEFAULT NULL COMMENT '参加人数',
  `Cstate` int(11) unsigned DEFAULT NULL COMMENT '公益状态，1表示在进行中，2表示失效，3表示用户已删除',
  PRIMARY KEY (`Cid`),
  UNIQUE KEY `cid` (`Cid`) USING BTREE,
  KEY `Cuid` (`Uid`),
  CONSTRAINT `Cuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charity
-- ----------------------------
INSERT INTO `charity` VALUES ('1', '爱公益爱环保', '1', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/charitydata/W020161121593494149380.jpg', '珍爱生命，保护环境，从我们做起，让世界环境变得更加美好。', '需要大家积极宣传保护环境的公益活动', '10', '2022-05-15 20:29:59', '2022-05-30 20:30:01', '线上', '浙江省 杭州市', '15', '0', '1');
INSERT INTO `charity` VALUES ('2', '爱心公益', '2', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/charitydata/d7d8d803b74806d8a6688790bb863874.jpg', '爱心认知世界，乐善提升自我，让我们从自身做起，做好身边的每一件事，关爱身边的人，做一个有爱心的人', '从身边的事情做起', '20', '2022-05-22 14:48:54', '2022-06-06 14:48:56', '线上', '浙江省 嘉兴市', '14', '2', '1');
INSERT INTO `charity` VALUES ('3', '4', '1', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114373821746634126\\appdata/charitydata/20220528072949.jpg', 'gongyi', '4', '4', '2022-05-28 07:29:56', '2022-06-04 07:29:57', '线上', '澳门 澳门', '3', '0', '1');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `Colid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `Gid` int(11) unsigned DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '收藏人的id',
  `Guid` int(11) unsigned DEFAULT NULL COMMENT '商品所属用户的id',
  `Coltime` varchar(100) DEFAULT NULL COMMENT '收藏的时间',
  PRIMARY KEY (`Colid`),
  UNIQUE KEY `colid` (`Colid`) USING BTREE,
  KEY `coluid` (`Uid`),
  KEY `colguid` (`Guid`),
  KEY `colgid` (`Gid`),
  CONSTRAINT `colgid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `colguid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `coluid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('5', '4', '2', '1', '2022-05-30 20:31:12');
INSERT INTO `collect` VALUES ('7', '7', '1', '2', '2022-06-01 14:01:19');
INSERT INTO `collect` VALUES ('9', '6', '1', '2', '2022-05-22 11:51:36');

-- ----------------------------
-- Table structure for conments
-- ----------------------------
DROP TABLE IF EXISTS `conments`;
CREATE TABLE `conments` (
  `Conid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `Gid` int(11) unsigned DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '发布评论的用户id',
  `Guid` int(11) unsigned DEFAULT NULL COMMENT '商品的用户id',
  `Contime` varchar(255) DEFAULT NULL COMMENT '发布时间',
  `Concontent` varchar(255) DEFAULT NULL COMMENT '内容',
  `Constate` int(11) unsigned DEFAULT NULL COMMENT '评论状态，1表示存在，2表示已删除',
  PRIMARY KEY (`Conid`),
  UNIQUE KEY `conid` (`Conid`) USING BTREE,
  KEY `congid` (`Gid`),
  KEY `conuid` (`Uid`),
  KEY `conguid` (`Guid`),
  CONSTRAINT `congid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `conguid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `conuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conments
-- ----------------------------
INSERT INTO `conments` VALUES ('1', '3', '1', '1', '2022-05-29 22:23:09', '还有吗，我想买', '1');
INSERT INTO `conments` VALUES ('2', '6', '1', '2', '2022-05-30 13:54:45', '杯子还能便宜点吗', '2');
INSERT INTO `conments` VALUES ('3', '6', '1', '2', '2022-05-30 13:56:10', '你从哪里发货啊', '1');
INSERT INTO `conments` VALUES ('4', '6', '1', '2', '2022-05-30 18:38:03', '什么时候能发货', '1');
INSERT INTO `conments` VALUES ('5', '4', '2', '1', '2022-05-30 20:19:18', '耳机还在吗', '1');
INSERT INTO `conments` VALUES ('6', '8', '1', '1', '2022-05-30 22:47:30', '一分钱一分货，买到就是赚到。', '1');
INSERT INTO `conments` VALUES ('7', '7', '1', '2', '2022-06-01 14:45:20', '苹果新鲜吗', '1');
INSERT INTO `conments` VALUES ('8', '3', '3', '1', '2022-05-25 22:33:10', '眼睛框还在吗', '1');
INSERT INTO `conments` VALUES ('9', '6', '1', '2', '2022-05-28 20:00:05', '111111111', '1');
INSERT INTO `conments` VALUES ('10', '6', '1', '2', '2022-05-28 20:00:16', '111111111', '2');
INSERT INTO `conments` VALUES ('11', '6', '1', '2', '2022-05-28 20:01:39', '111111111', '2');
INSERT INTO `conments` VALUES ('12', '5', '1', '1', '2022-05-28 20:07:21', '还有吗', '1');
INSERT INTO `conments` VALUES ('13', '5', '2', '1', '2022-05-28 12:08:11', 't11111', '1');
INSERT INTO `conments` VALUES ('14', '10', '1', '1', '2022-05-12 11:12:49', '和', '1');
INSERT INTO `conments` VALUES ('15', '5', '2', '1', '2022-05-19 20:04:39', '还有吗', '1');
INSERT INTO `conments` VALUES ('16', '3', '1', '1', '2022-05-28 07:06:32', '111', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `Gid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `Gname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品名',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '商品所属的用户id',
  `Gtime` varchar(255) DEFAULT NULL COMMENT '商品发布时间',
  `Gtype` varchar(255) DEFAULT NULL COMMENT '商品所属类型',
  `Gdetail` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `Gprice` double unsigned DEFAULT NULL COMMENT '商品价格',
  `Goprice` double unsigned DEFAULT NULL COMMENT '商品原价',
  `Gimage` varchar(255) DEFAULT NULL COMMENT '商品的图片，存储的是图片的储存地址',
  `Gstate` int(10) unsigned DEFAULT NULL COMMENT '商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除',
  `Gemergent` int(10) unsigned DEFAULT NULL COMMENT '商品是否是急售商品，1表示正常，2表示急售',
  `Ggetway` varchar(255) DEFAULT NULL COMMENT '商品交易是以邮寄的方式还是自提的方式',
  `Ghownew` varchar(255) DEFAULT NULL COMMENT '新旧程度',
  `Gscannum` int(11) unsigned DEFAULT NULL COMMENT '浏览人数',
  `Gaddress` varchar(255) DEFAULT NULL COMMENT '商品发布地址',
  PRIMARY KEY (`Gid`),
  UNIQUE KEY `Gid` (`Gid`) USING BTREE,
  KEY `Uid` (`Uid`) USING BTREE,
  CONSTRAINT `Guid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '手机充电器', '1', '2022-04-24 18:14:48', '手机数码', '手机充电器，华为手机充电器，换了新手机这个用不到了，出掉，还是快充', '12', '30', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20190519114751.jpg', '1', '2', '邮寄', '九成新', '8', '杭州市 江干区');
INSERT INTO `goods` VALUES ('2', '鼠标', '1', '2022-04-25 00:22:58', '生活百货', '一个鼠标，无线的，很好用，准备出了', '30', '50', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20190506104407.jpg', '1', '2', '邮寄', '八五新', '10', '杭州市 江干区');
INSERT INTO `goods` VALUES ('3', '眼镜框', '1', '2022-05-25 18:25:40', '生活百货', '新款眼镜框，有需要的直接下单，全新的', '30', '45', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191125182436.jpg', '1', '2', '邮寄', '十成新', '21', '杭州市 江干区');
INSERT INTO `goods` VALUES ('4', '蓝牙耳机', '1', '2022-05-25 18:27:49', '手机数码', '新款蓝牙耳机，用了几次，换新的了，这个还很好用', '89', '99', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191125182633.jpg', '3', '1', '自提', '九成新', '0', '杭州市 江干区');
INSERT INTO `goods` VALUES ('5', '曲奇饼干', '1', '2022-05-26 11:15:22', '生活百货', '莎布蕾曲奇饼干，非常好吃，有人要买吗', '15', '20', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191126111326.jpg', '1', '1', '自提', '十成新', '40', '杭州市 江干区');
INSERT INTO `goods` VALUES ('6', '水杯', '2', '2022-05-26 11:19:04', '服饰配件', '新买的水杯，还没用过几次，有需要的直接下单，杯子里的水一起送了', '56', '99', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191126111723.jpg', '1', '1', '邮寄', '十成新', '22', '杭州市 江干区');
INSERT INTO `goods` VALUES ('7', '苹果', '2', '2022-05-30 20:52:50', '园艺农用', '一个苹果，树上刚摘的，特别新鲜，有需要的可以下单，还有更多，需要的私聊。', '12', '35', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191130205049.jpg', '1', '1', '自提', '十成新', '10', '杭州市 江干区');
INSERT INTO `goods` VALUES ('8', '充电宝', '1', '2022-05-30 22:46:23', '运动户外', '手机充电宝，品胜的，看着旧，用起来新，有喜欢的赶快下单了啊，走过路过不要错过，买到就是赚到了啊', '80', '99', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191130224444.jpg', '1', '1', '邮寄', '九成新', '6', '杭州市 江干区');
INSERT INTO `goods` VALUES ('9', '手撕面包', '2', '2022-05-07 20:12:24', '服饰配件', '新品手撕面包，绝对新鲜，免费试吃，有需要的直接下单就可以', '0', '0', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20191207201042.jpg', '1', '1', '邮寄', '十成新', '15', '杭州市 江干区');
INSERT INTO `goods` VALUES ('10', '111', '1', '2022-05-12 11:12:00', '园艺农用', '1111', '1', '22', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220512111136.jpg', '5', '1', '自提', '七五新', '4', '杭州市 江干区');
INSERT INTO `goods` VALUES ('11', '鼠标', '2', '2022-05-19 19:41:21', '手机数码', '惠普蓝牙鼠标', '20', '50', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194024.jpg', '1', '2', '邮寄', '九成新', '3', '杭州市 江干区');
INSERT INTO `goods` VALUES ('12', '洗衣液', '2', '2022-05-19 19:43:15', '生活百货', '蓝月亮', '20', '50', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194237.jpg', '1', '1', '邮寄', '十成新', '1', '杭州市 江干区');
INSERT INTO `goods` VALUES ('13', '保温杯', '2', '2022-05-19 19:44:29', '其他分类', '新的保温杯，未使用过。', '20', '0', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194327.jpg', '1', '1', '邮寄', '十成新', '2', '杭州市 江干区');
INSERT INTO `goods` VALUES ('14', '口罩', '2', '2022-05-19 19:45:43', '生活百货', '医用口罩，十只装。', '5', '0', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194449.jpg', '1', '1', '邮寄', '十成新', '0', '杭州市 江干区');
INSERT INTO `goods` VALUES ('15', '项链男士', '2', '2022-05-19 19:47:23', '服饰配件', '小项链', '5', '0', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194622.jpg', '1', '1', '邮寄', '十成新', '0', '杭州市 江干区');
INSERT INTO `goods` VALUES ('16', '哑铃', '2', '2022-05-19 19:49:14', '健身器材', '塑料壳，装沙哑铃', '9.99', '0', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/goodsdata/20220519194804.jpg', '1', '1', '邮寄', '八成新', '0', '杭州市 江干区');
INSERT INTO `goods` VALUES ('17', '1', '1', '2022-05-28 07:16:54', '园艺农用', 'test', '1', '2', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114373821746634126\\appdata/goodsdata/20220528071438.jpg', '1', '1', '自提', '七五新', '2', '杭州 杭州');
INSERT INTO `goods` VALUES ('18', '1', '1', '2022-05-28 07:18:44', '园艺农用', 'test', '1', '2', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114373821746634126\\appdata/goodsdata/20220528071438.jpg', '1', '1', '自提', '七五新', '2', '杭州 杭州');
INSERT INTO `goods` VALUES ('19', '2', '1', '2022-05-28 07:28:22', '园艺农用', '2', '0', '0', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114373821746634126\\appdata/goodsdata/20220528072814.jpg', '1', '1', '自提', '七成新', '2', '台北 台北');
INSERT INTO `goods` VALUES ('20', '5', '1', '2022-05-28 08:21:37', '园艺农用', '5', '1', '1', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.7197642003810512658\\appdata/goodsdata/20220528082106.jpg', '1', '2', '邮寄', '六成新', '0', '香港 九龙');
INSERT INTO `goods` VALUES ('21', '5', '1', '2022-05-28 08:25:24', '园艺农用', '5', '1', '1', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.7197642003810512658\\appdata/goodsdata/20220528082106.jpg', '1', '2', '邮寄', '六成新', '0', '香港 九龙');
INSERT INTO `goods` VALUES ('22', '6', '1', '2022-05-28 08:41:30', '运动户外', '6', '1', '3', 'C:\\Users\\Climbe\\AppData\\Local\\Temp\\tomcat-docbase.8080.1114807844244168462\\appdata/goodsdata/20220528084103.jpg', '1', '1', '邮寄', '十成新', '0', '齐齐哈尔 拜泉');

-- ----------------------------
-- Table structure for joincharity
-- ----------------------------
DROP TABLE IF EXISTS `joincharity`;
CREATE TABLE `joincharity` (
  `Jid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '参加公益的id',
  `Cid` int(11) unsigned DEFAULT NULL COMMENT '相应的公益id',
  `Uid` int(11) unsigned DEFAULT NULL COMMENT '参加用户的id',
  `Cuid` int(11) unsigned DEFAULT NULL COMMENT '发起公益人的id',
  `Jtime` varchar(100) DEFAULT NULL COMMENT '参加公益的时间',
  PRIMARY KEY (`Jid`),
  UNIQUE KEY `Jid` (`Jid`) USING BTREE,
  KEY `Jcid` (`Cid`) USING BTREE,
  KEY `Juid` (`Uid`) USING BTREE,
  KEY `Jcuid` (`Cuid`) USING BTREE,
  CONSTRAINT `Jcid` FOREIGN KEY (`Cid`) REFERENCES `charity` (`Cid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Jcuid` FOREIGN KEY (`Cuid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Juid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joincharity
-- ----------------------------
INSERT INTO `joincharity` VALUES ('7', '2', '1', '2', '2022-04-25 20:34:41');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `Mid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `Receiveid` int(11) unsigned DEFAULT NULL COMMENT '接收人id',
  `Mtitle` varchar(255) DEFAULT NULL COMMENT '消息标题',
  `Mcontent` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `Mtime` varchar(255) DEFAULT NULL COMMENT '消息建立时间',
  `Mstate` int(11) unsigned DEFAULT NULL COMMENT '消息状态，1表示未读，2表示已读，3表示该消息已删除',
  PRIMARY KEY (`Mid`),
  UNIQUE KEY `mid` (`Mid`) USING BTREE,
  KEY `rid` (`Receiveid`) USING BTREE,
  CONSTRAINT `rid` FOREIGN KEY (`Receiveid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '【售出通知】', '您的商品【蓝牙耳机】以售出，交易款已进入您的账户！', '2022-05-08 17:24:31', '1');
INSERT INTO `message` VALUES ('2', '1', '【评论通知】', '您的商品【曲奇饼干】有一条新评论！', '2022-04-28 12:08:11', '3');
INSERT INTO `message` VALUES ('3', '2', '【商品售出】', '您的商品【水杯】已有人拍下，请及时发货！', '2022-05-09 15:50:12', '1');
INSERT INTO `message` VALUES ('4', '2', '【取消订单】', '商品【水杯】订单已取消，请与对方联系！', '2022-05-09 15:50:42', '1');
INSERT INTO `message` VALUES ('5', '2', '【商品售出】', '您的商品【苹果】已有人拍下，请及时发货！', '2022-05-09 15:51:00', '1');
INSERT INTO `message` VALUES ('6', '2', '【取消订单】', '商品【苹果】订单已取消，请与对方联系！', '2022-05-09 15:51:09', '1');
INSERT INTO `message` VALUES ('7', '1', '【评论通知】', '您的商品【曲奇饼干】有一条新评论！', '2022-05-19 20:04:39', '1');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` int(100) unsigned NOT NULL AUTO_INCREMENT COMMENT 'token的id',
  `apptoken` varchar(10000) DEFAULT '' COMMENT 'token的字符串',
  `expires` varchar(100) DEFAULT '' COMMENT 'token有效时间，在有效时间内不去获取',
  `application` varchar(100) DEFAULT NULL COMMENT '当前app的UUID值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('1', 'YWMtoHXLYN2XEeyQ3fFm4LJhr4nO2i6DBDATqMrEDvRapzZ8f0UAxy9Is43bdLxU_7eiAgMAAAGBBKXPXzeeSAAh76R38_eO1iDPFBlAuEYsjlEUyC07HhH8fSa5WOdgZw', '1654245185141', '7c7f4500-c72f-48b3-8ddb-74bc54ffb7a2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Uid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `Uaccount` varchar(18) DEFAULT NULL COMMENT '用户账号',
  `Upwd` varchar(18) DEFAULT NULL COMMENT '密码',
  `Uhxid` varchar(18) DEFAULT NULL COMMENT '环信id',
  `Unickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `Uphoto` varchar(1000) DEFAULT NULL COMMENT '头像,储存图片路径',
  `Ubalance` double(100,2) DEFAULT NULL COMMENT '余额',
  `Usex` int(100) unsigned DEFAULT NULL COMMENT '性别，1是男，2是女',
  `Uschool` varchar(100) DEFAULT NULL COMMENT '学校',
  `Uaddress` varchar(100) DEFAULT NULL COMMENT '地址',
  `Ureputation` int(100) DEFAULT NULL COMMENT '信誉值',
  `Utel` varchar(100) DEFAULT NULL COMMENT '电话',
  `Ustate` int(100) unsigned DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`Uid`),
  UNIQUE KEY `index` (`Uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaohong', '123456', 'xiaohong', '小红', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '421.00', '2', '浙江工商大学', '浙江省杭州市江干区浙江工商大学29', '670', '354677897543', '2');
INSERT INTO `user` VALUES ('2', 'xiaodong', '123456', 'xiaodong', '小东', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '1068.00', '1', '浙江工商大学', '浙江省嘉兴市海盐县通元张桥村', '900', '12345678912', '2');
INSERT INTO `user` VALUES ('3', 'xiaoming', '123456', 'xiaoming', '小明', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '888.00', '1', '浙江工商大学', '浙江省嘉兴市海盐县通元张桥村', '0', '123456789', '1');
INSERT INTO `user` VALUES ('4', '1234567', '1234567', '1234567', '123', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '0.00', '2', '浙江工商大学', '浙江省嘉兴市海盐县通元张桥村', '0', '123456789', '1');
INSERT INTO `user` VALUES ('5', 'aaaaaa', '123456', 'aaaaaa', 'aaa', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '0.00', '1', '家里蹲', '浙江省嘉兴市海盐县通元张桥村', '0', '15935726480', '1');
INSERT INTO `user` VALUES ('6', 'xiaoxu', '123456', 'xiaoxu', 'xiaoxu', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '0.00', '1', '浙江工商大学', '浙江省嘉兴市海盐县通元张桥村', '0', '19883119883', '2');
INSERT INTO `user` VALUES ('7', 'test2', '123456', 'test2', 'test2', 'D:\\xampp\\tomcat\\webapps\\QuTaoApplication\\appdata/userdata/IMG_20190914_112012.jpg', '0.00', '1', '浙江工商大学', '浙江省嘉兴市海盐县通元张桥村', '0', '13898767899', '1');
