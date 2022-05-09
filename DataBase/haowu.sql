/*
 Navicat Premium Data Transfer

 Source Server         : httptest
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : haowu

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/05/2022 00:02:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `Aid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `Anumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账单号',
  `Gid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '买家id',
  `Guid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '卖家id',
  `Abill` double(255, 2) UNSIGNED NULL DEFAULT NULL COMMENT '交易金额',
  `Atime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易时间',
  `Astate` int(11) NULL DEFAULT NULL COMMENT '订单状态，1表示等待卖家发货，2表示等待买家收货，3表示交易成功，4表示退款中，5表示交易失败（取消订单），6表示订单删除',
  PRIMARY KEY (`Aid`) USING BTREE,
  UNIQUE INDEX `aid`(`Aid`) USING BTREE,
  INDEX `Agid`(`Gid`) USING BTREE,
  INDEX `Auid`(`Uid`) USING BTREE,
  INDEX `Atuid`(`Guid`) USING BTREE,
  CONSTRAINT `Agid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Atuid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Auid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy`  (
  `Bid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '求购商品id',
  `Bname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `Bdetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品细节',
  `Bsprice` double(255, 2) UNSIGNED NULL DEFAULT NULL COMMENT '最低价',
  `Bbprice` double(255, 2) UNSIGNED NULL DEFAULT NULL COMMENT '最高价',
  `Btype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  `Bhownew` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新旧程度',
  `Bgetway` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获取方式',
  `Baddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品发布地址',
  `Btime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `Bimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '求购图片',
  `Bstate` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '求购商品的状态，1求购状态，2求购到，3已删除',
  `Bscannum` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '浏览人数',
  PRIMARY KEY (`Bid`) USING BTREE,
  UNIQUE INDEX `Bid`(`Bid`) USING BTREE,
  INDEX `Uid`(`Uid`) USING BTREE,
  CONSTRAINT `Buid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy
-- ----------------------------

-- ----------------------------
-- Table structure for buyconments
-- ----------------------------
DROP TABLE IF EXISTS `buyconments`;
CREATE TABLE `buyconments`  (
  `Bconid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '求购商品的评论id',
  `Bid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '求购商品id',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '评论人id',
  `Buid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '求购发起人id',
  `Bcontime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时间',
  `Bconcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `Bconstate` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '评论状态，1表示正常，2表示已删除',
  PRIMARY KEY (`Bconid`) USING BTREE,
  UNIQUE INDEX `bconid`(`Bconid`) USING BTREE,
  INDEX `bconuid`(`Uid`) USING BTREE,
  INDEX `bconbuid`(`Buid`) USING BTREE,
  INDEX `bconbid`(`Bid`) USING BTREE,
  CONSTRAINT `bconbid` FOREIGN KEY (`Bid`) REFERENCES `buy` (`Bid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `bconbuid` FOREIGN KEY (`Buid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `bconuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buyconments
-- ----------------------------

-- ----------------------------
-- Table structure for charity
-- ----------------------------
DROP TABLE IF EXISTS `charity`;
CREATE TABLE `charity`  (
  `Cid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公益id',
  `Cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公益名称',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '发布公益的用户id',
  `Cimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公益宣传图片',
  `Cdetail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公益细节',
  `Cneed` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公益需求',
  `Cnumber` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '所需人数',
  `Ctime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起时间',
  `Cdeadline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '截止时间',
  `Ctype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报名方式',
  `Caddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布地址',
  `Cscannum` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '浏览人数',
  `Cjoinnum` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '参加人数',
  `Cstate` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '公益状态，1表示在进行中，2表示失效，3表示用户已删除',
  PRIMARY KEY (`Cid`) USING BTREE,
  UNIQUE INDEX `cid`(`Cid`) USING BTREE,
  INDEX `Cuid`(`Uid`) USING BTREE,
  CONSTRAINT `Cuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charity
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `Colid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `Gid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '收藏人的id',
  `Guid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品所属用户的id',
  `Coltime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏的时间',
  PRIMARY KEY (`Colid`) USING BTREE,
  UNIQUE INDEX `colid`(`Colid`) USING BTREE,
  INDEX `coluid`(`Uid`) USING BTREE,
  INDEX `colguid`(`Guid`) USING BTREE,
  INDEX `colgid`(`Gid`) USING BTREE,
  CONSTRAINT `colgid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `colguid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `coluid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for conments
-- ----------------------------
DROP TABLE IF EXISTS `conments`;
CREATE TABLE `conments`  (
  `Conid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `Gid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品id',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '发布评论的用户id',
  `Guid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品的用户id',
  `Contime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `Concontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `Constate` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '评论状态，1表示存在，2表示已删除',
  PRIMARY KEY (`Conid`) USING BTREE,
  UNIQUE INDEX `conid`(`Conid`) USING BTREE,
  INDEX `congid`(`Gid`) USING BTREE,
  INDEX `conuid`(`Uid`) USING BTREE,
  INDEX `conguid`(`Guid`) USING BTREE,
  CONSTRAINT `congid` FOREIGN KEY (`Gid`) REFERENCES `goods` (`Gid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `conguid` FOREIGN KEY (`Guid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `conuid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conments
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `Gid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `Gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '商品所属的用户id',
  `Gtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品发布时间',
  `Gtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品所属类型',
  `Gdetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `Gprice` double UNSIGNED NULL DEFAULT NULL COMMENT '商品价格',
  `Goprice` double UNSIGNED NULL DEFAULT NULL COMMENT '商品原价',
  `Gimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品的图片，存储的是图片的储存地址',
  `Gstate` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除',
  `Gemergent` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '商品是否是急售商品，1表示正常，2表示急售',
  `Ggetway` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品交易是以邮寄的方式还是自提的方式',
  `Ghownew` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新旧程度',
  `Gscannum` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '浏览人数',
  `Gaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品发布地址',
  PRIMARY KEY (`Gid`) USING BTREE,
  UNIQUE INDEX `Gid`(`Gid`) USING BTREE,
  INDEX `Uid`(`Uid`) USING BTREE,
  CONSTRAINT `Guid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for joincharity
-- ----------------------------
DROP TABLE IF EXISTS `joincharity`;
CREATE TABLE `joincharity`  (
  `Jid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '参加公益的id',
  `Cid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '相应的公益id',
  `Uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '参加用户的id',
  `Cuid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '发起公益人的id',
  `Jtime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参加公益的时间',
  PRIMARY KEY (`Jid`) USING BTREE,
  UNIQUE INDEX `Jid`(`Jid`) USING BTREE,
  INDEX `Jcid`(`Cid`) USING BTREE,
  INDEX `Juid`(`Uid`) USING BTREE,
  INDEX `Jcuid`(`Cuid`) USING BTREE,
  CONSTRAINT `Jcid` FOREIGN KEY (`Cid`) REFERENCES `charity` (`Cid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Jcuid` FOREIGN KEY (`Cuid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Juid` FOREIGN KEY (`Uid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of joincharity
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `Mid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `Receiveid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '接收人id',
  `Mtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `Mcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `Mtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息建立时间',
  `Mstate` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '消息状态，1表示未读，2表示已读，3表示该消息已删除',
  PRIMARY KEY (`Mid`) USING BTREE,
  UNIQUE INDEX `mid`(`Mid`) USING BTREE,
  INDEX `rid`(`Receiveid`) USING BTREE,
  CONSTRAINT `rid` FOREIGN KEY (`Receiveid`) REFERENCES `user` (`Uid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` int(100) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'token的id',
  `apptoken` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token的字符串',
  `expires` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token有效时间，在有效时间内不去获取',
  `application` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前app的UUID值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Uid` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `Uaccount` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `Upwd` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `Uhxid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环信id',
  `Unickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `Uphoto` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像,储存图片路径',
  `Ubalance` double(100, 2) NULL DEFAULT NULL COMMENT '余额',
  `Usex` int(100) UNSIGNED NULL DEFAULT NULL COMMENT '性别，1是男，2是女',
  `Uschool` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校',
  `Uaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `Ureputation` int(100) NULL DEFAULT NULL COMMENT '信誉值',
  `Utel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `Ustate` int(100) UNSIGNED NULL DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`Uid`) USING BTREE,
  UNIQUE INDEX `index`(`Uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
