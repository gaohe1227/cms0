/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : 127.0.0.1:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-08-16 18:30:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_cms_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_log`;
CREATE TABLE `t_cms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cms_log
-- ----------------------------
INSERT INTO `t_cms_log` VALUES ('1', null, '192.168.98.231', null, '2017-08-15 17:54:50', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('2', '2017-08-15 17:54:52', '192.168.98.231', '耗时1282毫秒', '2017-08-15 17:54:50', '0', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('3', null, '192.168.98.231', null, '2017-08-15 17:54:55', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('4', '2017-08-15 17:54:56', '192.168.98.231', '耗时476毫秒', '2017-08-15 17:54:55', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('5', null, '192.168.98.231', null, '2017-08-15 17:55:00', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('6', '2017-08-15 17:55:00', '192.168.98.231', '耗时70毫秒', '2017-08-15 17:55:00', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('7', null, '192.168.98.231', null, '2017-08-15 17:55:00', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('8', '2017-08-15 17:55:00', '192.168.98.231', '耗时74毫秒', '2017-08-15 17:55:00', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('9', null, '192.168.98.231', null, '2017-08-15 17:55:08', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('10', '2017-08-15 17:55:08', '192.168.98.231', '耗时301毫秒', '2017-08-15 17:55:08', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('11', null, '192.168.98.231', null, '2017-08-15 17:57:27', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('12', '2017-08-15 17:57:28', '192.168.98.231', '耗时927毫秒', '2017-08-15 17:57:27', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('13', null, '192.168.98.231', null, '2017-08-15 17:57:48', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('14', '2017-08-15 17:57:49', '192.168.98.231', '耗时99毫秒', '2017-08-15 17:57:48', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('15', null, '192.168.98.231', null, '2017-08-15 17:58:19', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('16', '2017-08-15 17:58:19', '192.168.98.231', '耗时53毫秒', '2017-08-15 17:58:19', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('17', null, '192.168.98.231', null, '2017-08-15 17:58:22', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('18', '2017-08-15 17:58:22', '192.168.98.231', '耗时169毫秒', '2017-08-15 17:58:22', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('19', null, '192.168.98.231', null, '2017-08-15 17:58:25', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('20', '2017-08-15 17:58:25', '192.168.98.231', '耗时339毫秒', '2017-08-15 17:58:25', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('21', null, '192.168.98.231', null, '2017-08-15 17:58:46', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('22', '2017-08-15 17:58:47', '192.168.98.231', '耗时632毫秒', '2017-08-15 17:58:46', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('23', null, '192.168.98.231', null, '2017-08-15 17:58:51', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('24', '2017-08-15 17:58:52', '192.168.98.231', '耗时616毫秒', '2017-08-15 17:58:51', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('25', null, '192.168.98.231', null, '2017-08-15 17:59:03', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('26', '2017-08-15 17:59:03', '192.168.98.231', '耗时161毫秒', '2017-08-15 17:59:03', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('27', null, '192.168.98.231', null, '2017-08-15 17:59:43', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('28', '2017-08-15 17:59:43', '192.168.98.231', '耗时232毫秒', '2017-08-15 17:59:43', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('29', null, '192.168.98.231', null, '2017-08-15 18:08:12', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('30', '2017-08-15 18:08:13', '192.168.98.231', '耗时783毫秒', '2017-08-15 18:08:12', '0', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('31', null, '192.168.98.231', null, '2017-08-15 18:17:50', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('32', '2017-08-15 18:17:50', '192.168.98.231', '耗时371毫秒', '2017-08-15 18:17:50', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('33', null, '192.168.98.231', null, '2017-08-15 18:17:54', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('34', '2017-08-15 18:17:54', '192.168.98.231', '耗时83毫秒', '2017-08-15 18:17:54', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('35', null, '192.168.98.231', null, '2017-08-15 18:17:55', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('36', '2017-08-15 18:17:55', '192.168.98.231', '耗时67毫秒', '2017-08-15 18:17:55', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('37', null, '192.168.98.231', null, '2017-08-15 18:17:56', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('38', '2017-08-15 18:17:56', '192.168.98.231', '耗时69毫秒', '2017-08-15 18:17:56', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('39', null, '192.168.98.231', null, '2017-08-15 18:17:56', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('40', '2017-08-15 18:17:56', '192.168.98.231', '耗时112毫秒', '2017-08-15 18:17:56', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('41', null, '192.168.98.231', null, '2017-08-15 18:18:00', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('42', '2017-08-15 18:18:00', '192.168.98.231', '耗时681毫秒', '2017-08-15 18:18:00', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('43', null, '192.168.98.231', null, '2017-08-15 18:18:02', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('44', '2017-08-15 18:18:03', '192.168.98.231', '耗时109毫秒', '2017-08-15 18:18:02', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('45', null, '192.168.98.231', null, '2017-08-15 18:18:04', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('46', '2017-08-15 18:18:04', '192.168.98.231', '耗时73毫秒', '2017-08-15 18:18:04', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('47', null, '192.168.98.231', null, '2017-08-15 18:18:05', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('48', '2017-08-15 18:18:05', '192.168.98.231', '耗时58毫秒', '2017-08-15 18:18:05', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('49', null, '192.168.98.231', null, '2017-08-15 18:18:10', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('50', '2017-08-15 18:18:10', '192.168.98.231', '耗时372毫秒', '2017-08-15 18:18:10', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('51', null, '192.168.98.231', null, '2017-08-15 18:18:12', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('52', '2017-08-15 18:18:12', '192.168.98.231', '耗时63毫秒', '2017-08-15 18:18:12', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('53', null, '192.168.98.231', null, '2017-08-15 18:18:15', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('54', '2017-08-15 18:18:15', '192.168.98.231', '耗时344毫秒', '2017-08-15 18:18:15', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('55', null, '192.168.98.231', null, '2017-08-15 18:18:17', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('56', '2017-08-15 18:18:17', '192.168.98.231', '耗时116毫秒', '2017-08-15 18:18:17', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('57', null, '192.168.98.231', null, '2017-08-15 18:18:18', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('58', '2017-08-15 18:18:19', '192.168.98.231', '耗时56毫秒', '2017-08-15 18:18:18', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('59', null, '192.168.98.231', null, '2017-08-15 18:18:45', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('60', '2017-08-15 18:18:45', '192.168.98.231', '耗时137毫秒', '2017-08-15 18:18:45', '0', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('61', null, '192.168.98.231', null, '2017-08-15 18:24:45', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('62', '2017-08-15 18:24:45', '192.168.98.231', '耗时74毫秒', '2017-08-15 18:24:45', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('63', null, '192.168.98.231', null, '2017-08-15 18:24:57', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('64', '2017-08-15 18:24:57', '192.168.98.231', '耗时56毫秒', '2017-08-15 18:24:57', '0', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('65', null, '192.168.98.231', null, '2017-08-15 18:26:29', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('66', '2017-08-15 18:26:30', '192.168.98.231', '耗时113毫秒', '2017-08-15 18:26:29', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('67', null, '192.168.98.231', null, '2017-08-15 18:28:04', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('68', '2017-08-15 18:28:04', '192.168.98.231', '耗时103毫秒', '2017-08-15 18:28:04', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('69', null, '192.168.98.231', null, '2017-08-15 18:28:10', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('70', '2017-08-15 18:28:11', '192.168.98.231', '耗时583毫秒', '2017-08-15 18:28:10', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('71', null, '192.168.98.231', null, '2017-08-15 18:29:11', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('72', '2017-08-15 18:29:12', '192.168.98.231', '耗时460毫秒', '2017-08-15 18:29:11', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('73', null, '192.168.98.231', null, '2017-08-16 10:05:16', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('74', '2017-08-16 10:05:17', '192.168.98.231', '耗时724毫秒', '2017-08-16 10:05:16', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('75', null, '192.168.98.231', null, '2017-08-16 10:05:41', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('76', '2017-08-16 10:05:41', '192.168.98.231', '耗时93毫秒', '2017-08-16 10:05:41', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('77', null, '192.168.98.231', null, '2017-08-16 10:05:45', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('78', '2017-08-16 10:05:45', '192.168.98.231', '耗时110毫秒', '2017-08-16 10:05:45', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('79', null, '192.168.98.231', null, '2017-08-16 10:05:47', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('80', '2017-08-16 10:05:47', '192.168.98.231', '耗时267毫秒', '2017-08-16 10:05:47', '0', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('81', null, '192.168.98.231', null, '2017-08-16 10:06:04', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('82', '2017-08-16 10:06:04', '192.168.98.231', '耗时364毫秒', '2017-08-16 10:06:04', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('83', null, '192.168.98.231', null, '2017-08-16 10:07:36', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('84', '2017-08-16 10:07:36', '192.168.98.231', '耗时75毫秒', '2017-08-16 10:07:36', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('85', null, '0:0:0:0:0:0:0:1', null, '2017-08-16 16:53:39', '1', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('86', '2017-08-16 16:53:40', '0:0:0:0:0:0:0:1', '耗时1175毫秒', '2017-08-16 16:53:39', '0', '/cmsLog/list', null);
INSERT INTO `t_cms_log` VALUES ('87', null, '0:0:0:0:0:0:0:1', null, '2017-08-16 16:53:43', '1', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('88', '2017-08-16 16:53:44', '0:0:0:0:0:0:0:1', '耗时665毫秒', '2017-08-16 16:53:43', '0', '/user/list', null);
INSERT INTO `t_cms_log` VALUES ('89', null, '0:0:0:0:0:0:0:1', null, '2017-08-16 17:00:22', '1', '/role/list', null);
INSERT INTO `t_cms_log` VALUES ('90', '2017-08-16 17:00:23', '0:0:0:0:0:0:0:1', '耗时1068毫秒', '2017-08-16 17:00:22', '0', '/role/list', null);

-- ----------------------------
-- Table structure for `t_cms_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_role`;
CREATE TABLE `t_cms_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwid` int(11) DEFAULT '0',
  `role_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态;0:正常1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cms_role
-- ----------------------------
INSERT INTO `t_cms_role` VALUES ('1', '0', '角色01', '0');
INSERT INTO `t_cms_role` VALUES ('2', null, '角色02', '0');
INSERT INTO `t_cms_role` VALUES ('3', null, '角色03', '0');
INSERT INTO `t_cms_role` VALUES ('4', null, '角色04', '0');
INSERT INTO `t_cms_role` VALUES ('5', null, '角色05', '0');
INSERT INTO `t_cms_role` VALUES ('6', null, '角色051', '1');
INSERT INTO `t_cms_role` VALUES ('7', null, '角色041', '1');

-- ----------------------------
-- Table structure for `t_cms_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_user`;
CREATE TABLE `t_cms_user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `dwid` varchar(255) DEFAULT NULL,
  `usercode` varchar(255) DEFAULT NULL,
  `userdep` varchar(255) DEFAULT NULL,
  `usermobiletel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  `userstate` varchar(255) DEFAULT NULL,
  `usertel` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '状态;0:正常1:删除',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cms_user
-- ----------------------------
INSERT INTO `t_cms_user` VALUES ('1', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('2', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('3', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('4', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('5', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('6', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('7', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('8', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('9', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('10', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('11', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('12', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('14', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('15', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('16', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('17', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('18', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('19', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('20', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('21', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('22', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('23', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('24', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('25', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('29', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('30', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('31', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('32', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('33', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('34', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('35', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('36', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('37', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('38', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('39', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('40', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('41', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('42', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('43', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('44', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('45', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('46', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('47', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('48', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('49', null, '03', null, null, '用户03', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('50', null, 'user01', null, null, '用户01', '123456', null, null, '0');
INSERT INTO `t_cms_user` VALUES ('51', null, 'user2', null, null, '用户02', '123456', null, null, '1');
INSERT INTO `t_cms_user` VALUES ('52', null, '03', null, null, '用户03', '123456', null, null, '0');
