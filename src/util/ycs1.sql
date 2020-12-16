/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : ycs1

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/10/2020 21:27:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(0) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员用户名',
  `apwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'yc', '123');

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `zname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书名字',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `sptime` datetime(0) NULL DEFAULT NULL COMMENT '审批时间',
  `zsimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书图片',
  `zt` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '状态0代表false，1代表true',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of certificate
-- ----------------------------
INSERT INTO `certificate` VALUES (2, '三好学生', '李四', '2020-10-06 20:22:06', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (3, '最美女生', '吴沼淇', '2020-10-06 20:22:57', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (4, '三号学生', '吴沼淇', '2020-10-06 20:22:59', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (5, '四级证书', '吴沼淇', '2020-10-06 20:23:02', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (6, '专四证书', '吴沼淇', '2020-10-22 20:29:36', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (7, '你好', '吴沼淇', '2020-10-06 21:01:03', NULL, '', 0);
INSERT INTO `certificate` VALUES (8, '你好', '吴沼淇', '2020-10-06 21:03:05', NULL, '', 0);
INSERT INTO `certificate` VALUES (9, '你好', '吴沼淇', '2020-10-06 21:03:10', NULL, '', 0);
INSERT INTO `certificate` VALUES (10, '4564', '吴沼淇', '2020-10-07 16:28:49', NULL, '', 0);
INSERT INTO `certificate` VALUES (11, '4', '吴沼淇', '2020-10-07 16:29:39', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (12, '11', '吴沼淇', '2020-10-07 16:30:39', NULL, NULL, 0);
INSERT INTO `certificate` VALUES (13, '11', '吴沼淇', '2020-10-07 16:30:48', NULL, '', 0);
INSERT INTO `certificate` VALUES (14, '杨开兰.jpg', '吴沼淇', '2020-10-07 16:35:35', NULL, '', 0);
INSERT INTO `certificate` VALUES (15, '吴沼淇', '吴沼淇', '2020-10-07 16:40:28', NULL, '', 0);
INSERT INTO `certificate` VALUES (16, '123', '吴沼淇', '2020-10-07 16:45:13', NULL, '18020140125-吴礼云.JPG', 0);
INSERT INTO `certificate` VALUES (17, 'aaa', '陈栋', '2020-10-07 17:12:10', NULL, '18020140101-付紫嫣.JPG', 0);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `Cid` int(0) NOT NULL AUTO_INCREMENT COMMENT '学院编号',
  `Cna` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`Cid`) USING BTREE,
  INDEX `Cna`(`Cna`) USING BTREE,
  INDEX `Cid`(`Cid`, `Cna`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (7, '外国语学院');
INSERT INTO `college` VALUES (6, '建工学院');
INSERT INTO `college` VALUES (4, '数能学院');
INSERT INTO `college` VALUES (8, '机械学院');
INSERT INTO `college` VALUES (3, '材化学院');
INSERT INTO `college` VALUES (5, '电信学院');
INSERT INTO `college` VALUES (2, '经管学院');
INSERT INTO `college` VALUES (1, '计信学院');

-- ----------------------------
-- Table structure for messageboard
-- ----------------------------
DROP TABLE IF EXISTS `messageboard`;
CREATE TABLE `messageboard`  (
  `Mid` int(0) NOT NULL AUTO_INCREMENT,
  `Sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生名字',
  `Mdate` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '留言时间',
  `Message` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  PRIMARY KEY (`Mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messageboard
-- ----------------------------
INSERT INTO `messageboard` VALUES (1, '陈栋', '2020-09-24 19:38:37', '我要女装把你们骗上我的床');
INSERT INTO `messageboard` VALUES (2, '蔡徐坤', '2020-09-24 19:41:04', '鸡你太美');
INSERT INTO `messageboard` VALUES (3, '陈栋', '2020-09-24 19:42:47', '给爷爬');
INSERT INTO `messageboard` VALUES (4, '蔡徐坤', '2020-09-24 19:43:07', '青春');
INSERT INTO `messageboard` VALUES (5, '陈栋', '2020-10-03 20:32:46', 'aa');
INSERT INTO `messageboard` VALUES (7, '陈栋', '2020-10-03 20:38:03', '东门什么时候开啊，我想找妹妹们了');
INSERT INTO `messageboard` VALUES (9, '陈栋', '2020-10-03 20:52:07', '鹊桥什么时候开啊，我想师院找妹妹们了 ');
INSERT INTO `messageboard` VALUES (10, '陈栋', '2020-10-03 20:56:37', '师院的妹妹好漂亮的');
INSERT INTO `messageboard` VALUES (11, '陈栋', '2020-10-03 20:57:51', '宝贝我想你了');
INSERT INTO `messageboard` VALUES (12, '匿名', '2020-10-05 10:32:21', '立马炸了');
INSERT INTO `messageboard` VALUES (13, '陈栋', '2020-10-05 10:32:27', '立马炸了');
INSERT INTO `messageboard` VALUES (14, '匿名', '2020-10-05 15:10:30', '你好');
INSERT INTO `messageboard` VALUES (15, '陈栋', '2020-10-05 15:10:36', '你好');
INSERT INTO `messageboard` VALUES (16, '陈栋', '2020-10-07 17:09:38', '吴沼淇你好漂亮');
INSERT INTO `messageboard` VALUES (17, '匿名', '2020-10-07 17:09:43', '吴沼淇你好漂亮');

-- ----------------------------
-- Table structure for pmail
-- ----------------------------
DROP TABLE IF EXISTS `pmail`;
CREATE TABLE `pmail`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生名',
  `sMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生留言',
  `pPreply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校长回复',
  `mTime` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  `pTime` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pmail
-- ----------------------------
INSERT INTO `pmail` VALUES (1, '陈栋', '为何东门还不开门', '开门了我的食堂怎么赚钱', '2020-10-01 22:21:55', '2020-10-03 22:22:00');
INSERT INTO `pmail` VALUES (2, '陈栋', '多喝热水', '222', '2020-10-04 01:12:02', '2020-10-10 01:30:09');
INSERT INTO `pmail` VALUES (3, '陈栋', 'aaa', NULL, '2020-10-04 01:34:27', NULL);
INSERT INTO `pmail` VALUES (4, '蔡徐坤', 'aaa', 'aaaaa', '2020-09-29 01:35:47', '2020-10-06 01:35:52');
INSERT INTO `pmail` VALUES (5, '陈栋', '怎么宿舍会断网', NULL, '2020-10-04 01:44:15', NULL);
INSERT INTO `pmail` VALUES (6, '陈栋', '宝贝，我想你了', NULL, '2020-10-04 17:03:07', NULL);
INSERT INTO `pmail` VALUES (7, '陈栋', '无聊', NULL, '2020-10-07 17:10:01', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sid` int(0) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `Sno` int(0) NOT NULL COMMENT '学号',
  `Sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `Ssex` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `Sage` int(0) NOT NULL COMMENT '年龄',
  `Sclass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `Cid` int(0) NOT NULL COMMENT '学院',
  `Smo` float(255, 2) NOT NULL COMMENT '余额',
  `Spw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `Sma` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生邮箱，用于修改密码的认证',
  `imgFile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生·图片·路径·相对路径',
  `state` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '学生卡状态，0为正常，1为挂失中，2为注销',
  PRIMARY KEY (`Sid`) USING BTREE,
  INDEX `fk_1`(`Cid`) USING BTREE,
  CONSTRAINT `fk_1` FOREIGN KEY (`Cid`) REFERENCES `college` (`Cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 101, '付紫嫣', '女', 18, '1', 1, 99.55, '0000', '824276226@qq.com', '18020140101-付紫嫣.JPG', 0);
INSERT INTO `student` VALUES (10, 102, '钟浣文', '女', 18, '1', 1, 105.50, '1234', 'a1261337209@qq.com', '18020140124-唐锋.JPG', 0);
INSERT INTO `student` VALUES (11, 103, '陈栋', '女', 19, '1', 1, 186.66, '0000', '18461326330@qq.com', '郑冬慧.JPG', 0);
INSERT INTO `student` VALUES (12, 109, '吴沼淇', '女', 19, '1', 7, 11197.99, '0000', 'aa@qq.com', '1.jpg', 0);
INSERT INTO `student` VALUES (15, 110, '廖志军', '女', 18, '1', 2, 188.29, '0000', '1261337209@qq.com', NULL, 0);
INSERT INTO `student` VALUES (16, 111, '蔡徐坤', '男', 21, '2', 3, 1883.69, '0000', '123@qq.com', NULL, 0);
INSERT INTO `student` VALUES (17, 99, '乔碧萝', '女', 60, '4', 4, 0.00, '0000', 'null', NULL, 2);
INSERT INTO `student` VALUES (18, 98, '张三', '男', 19, '3', 5, -99.00, '0000', 'null', NULL, 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `Tid` int(0) NOT NULL AUTO_INCREMENT COMMENT '老师id',
  `Tno` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师编号，登录用户名',
  `Tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师姓名',
  `Tpw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `Tlimi` int(0) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`Tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '111', '张三', '123', 1);

SET FOREIGN_KEY_CHECKS = 1;
