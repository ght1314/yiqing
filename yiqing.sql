/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : yiqing

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-06-07 17:16:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `add_id` int NOT NULL AUTO_INCREMENT,
  `add_sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址代号',
  `add_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小区名称',
  `add_house` int NOT NULL,
  `add_unit` int NOT NULL,
  `add_count` int DEFAULT '0' COMMENT '地址中住户数量，默认0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`add_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES ('74', null, '朗风苑', '1', '1', '2', null, null, '0');
INSERT INTO `tb_address` VALUES ('75', null, '和风苑', '1', '540', '3', null, null, '1');
INSERT INTO `tb_address` VALUES ('76', null, '和风苑', '5', '20', '5', null, null, '0');
INSERT INTO `tb_address` VALUES ('77', null, '嘉风苑', '2', '233', '3', null, null, '0');
INSERT INTO `tb_address` VALUES ('80', '370411001', '和风苑', '1', '540', '2', null, null, '0');

-- ----------------------------
-- Table structure for tb_authority
-- ----------------------------
DROP TABLE IF EXISTS `tb_authority`;
CREATE TABLE `tb_authority` (
  `auth_id` int NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(99) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统管理员等级1 普通管理员等级2',
  `auth_describe` varchar(99) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`auth_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_authority
-- ----------------------------
INSERT INTO `tb_authority` VALUES ('1', '增加管理员信息', '超级管理员具有权限', '2028-01-05 13:32:57', '2028-01-08 12:59:58');
INSERT INTO `tb_authority` VALUES ('2', '修改管理员信息', '超级管理员权限', '2028-01-05 13:33:04', '2028-01-08 12:59:46');
INSERT INTO `tb_authority` VALUES ('3', '增加住户信息', '通用功能', '2028-01-08 12:58:58', '2028-01-08 12:59:30');
INSERT INTO `tb_authority` VALUES ('4', '修改住户信息', '通用功能', '2028-01-08 12:59:10', '2028-01-08 12:59:34');
INSERT INTO `tb_authority` VALUES ('5', '添加访问记录', '通用功能', '2028-01-08 12:59:22', '2028-01-08 13:01:10');
INSERT INTO `tb_authority` VALUES ('6', '修改访问记录', '通用功能', '2028-01-08 13:00:19', '2028-01-08 13:01:11');
INSERT INTO `tb_authority` VALUES ('7', '管理员修改个人信息', '通用功能', '2028-01-08 13:01:07', '2028-01-08 13:01:07');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int NOT NULL COMMENT '父菜单ID',
  `href` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `target` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0' COMMENT '0表示未删除 1删除',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '社区人员管理', '-1', null, 'fa fa-address-book', '_self', '0');
INSERT INTO `tb_menu` VALUES ('2', '系统用户管理', '-1', null, 'fa fa-lemon-o', '_self', '0');
INSERT INTO `tb_menu` VALUES ('4', '业主管理', '1', null, 'fa fa-home', '_self', '0');
INSERT INTO `tb_menu` VALUES ('5', '进出管理', '1', null, 'fa fa-window-maximize', '_self', '0');
INSERT INTO `tb_menu` VALUES ('6', '访客管理', '1', null, 'fa fa-gears', '_self', '0');
INSERT INTO `tb_menu` VALUES ('7', '地址管理', '1', null, 'fa fa-file-text', '_self', '0');
INSERT INTO `tb_menu` VALUES ('8', '业主信息', '4', '/User/resident', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('9', '高危业主', '4', '/User/perilPage', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('10', '业主统计', '4', '/User/resCount', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('11', '业主进出登记', '5', '/User/resRecord', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('12', '访客进出登记', '5', '/User/visRecord', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('13', '进出信息管理', '5', '/User/records', 'fa fa-tachometer', '_self', '0');
INSERT INTO `tb_menu` VALUES ('14', '访客信息', '6', '/User/vistors', 'fa fa-tachometer', null, '0');
INSERT INTO `tb_menu` VALUES ('15', '地址表', '7', '/User/address', 'fa fa-tachometer', null, '0');
INSERT INTO `tb_menu` VALUES ('16', '用户管理', '2', '/User/users', 'fa fa-snowflake-o', null, '0');
INSERT INTO `tb_menu` VALUES ('17', '注册码', '2', '/User/userSalt', 'fa fa-hourglass-end', null, '0');

-- ----------------------------
-- Table structure for tb_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role`;
CREATE TABLE `tb_menu_role` (
  `mu_id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL COMMENT '菜单表ID',
  `role_id` int NOT NULL COMMENT '角色表ID',
  PRIMARY KEY (`mu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of tb_menu_role
-- ----------------------------
INSERT INTO `tb_menu_role` VALUES ('1', '1', '1');
INSERT INTO `tb_menu_role` VALUES ('2', '2', '1');
INSERT INTO `tb_menu_role` VALUES ('3', '3', '1');
INSERT INTO `tb_menu_role` VALUES ('4', '4', '1');
INSERT INTO `tb_menu_role` VALUES ('5', '5', '1');
INSERT INTO `tb_menu_role` VALUES ('6', '6', '1');
INSERT INTO `tb_menu_role` VALUES ('7', '7', '1');
INSERT INTO `tb_menu_role` VALUES ('8', '8', '1');
INSERT INTO `tb_menu_role` VALUES ('9', '9', '1');
INSERT INTO `tb_menu_role` VALUES ('10', '10', '1');
INSERT INTO `tb_menu_role` VALUES ('11', '11', '1');
INSERT INTO `tb_menu_role` VALUES ('12', '12', '1');
INSERT INTO `tb_menu_role` VALUES ('13', '13', '1');
INSERT INTO `tb_menu_role` VALUES ('14', '1', '2');
INSERT INTO `tb_menu_role` VALUES ('15', '4', '2');
INSERT INTO `tb_menu_role` VALUES ('16', '5', '2');
INSERT INTO `tb_menu_role` VALUES ('17', '6', '2');
INSERT INTO `tb_menu_role` VALUES ('18', '7', '2');
INSERT INTO `tb_menu_role` VALUES ('19', '8', '2');
INSERT INTO `tb_menu_role` VALUES ('20', '9', '2');
INSERT INTO `tb_menu_role` VALUES ('21', '10', '2');
INSERT INTO `tb_menu_role` VALUES ('22', '14', '1');
INSERT INTO `tb_menu_role` VALUES ('23', '15', '1');
INSERT INTO `tb_menu_role` VALUES ('24', '16', '1');
INSERT INTO `tb_menu_role` VALUES ('25', '17', '1');
INSERT INTO `tb_menu_role` VALUES ('26', '18', '1');
INSERT INTO `tb_menu_role` VALUES ('27', '10', '2');
INSERT INTO `tb_menu_role` VALUES ('28', '11', '2');
INSERT INTO `tb_menu_role` VALUES ('29', '12', '2');
INSERT INTO `tb_menu_role` VALUES ('30', '13', '2');
INSERT INTO `tb_menu_role` VALUES ('31', '14', '2');
INSERT INTO `tb_menu_role` VALUES ('32', '15', '2');

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record` (
  `rec_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rec_time` datetime DEFAULT NULL,
  `rec_type` int NOT NULL COMMENT '进入是1 出去是0',
  `rec_personId` int DEFAULT NULL COMMENT '进出人员的信息ID',
  `rec_perType` int DEFAULT NULL COMMENT '住户是0 访客是1',
  `rec_orderadd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rec_tempera` float(10,2) NOT NULL,
  `rec_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`rec_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_record
-- ----------------------------
INSERT INTO `tb_record` VALUES ('1001', '2022-06-06 15:03:28', '1', '4', '0', '熙街', '36.50', '吃饭', null, null, '0');
INSERT INTO `tb_record` VALUES ('1002', '2022-06-06 15:07:42', '1', '1', '0', '熙街', '36.50', '谈恋爱', null, null, '0');
INSERT INTO `tb_record` VALUES ('1003', '2022-06-06 15:08:10', '1', '9', '1', '解放碑', '36.50', '', null, null, '0');
INSERT INTO `tb_record` VALUES ('1004', '2022-06-07 17:00:59', '1', '1', '0', '熙街', '36.50', '再次谈恋爱', null, null, '0');

-- ----------------------------
-- Table structure for tb_resident
-- ----------------------------
DROP TABLE IF EXISTS `tb_resident`;
CREATE TABLE `tb_resident` (
  `res_id` int NOT NULL AUTO_INCREMENT,
  `res_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `res_sex` int NOT NULL DEFAULT '0' COMMENT '0为男性，1为女性',
  `res_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `res_intotime` date NOT NULL,
  `res_status` int DEFAULT NULL COMMENT '0表示正常，1低风险，2高风险',
  `res_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `res_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`res_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_resident
-- ----------------------------
INSERT INTO `tb_resident` VALUES ('1', '戴晓旭', '0', '15723307205', '2022-06-06', null, '无', null, null, null, '0');
INSERT INTO `tb_resident` VALUES ('2', '李科', '0', '17623249171', '2022-06-06', null, '无', null, null, null, '0');
INSERT INTO `tb_resident` VALUES ('3', 'ddd', '1', '19283746574', '2022-06-06', '2', '无', null, null, null, '0');
INSERT INTO `tb_resident` VALUES ('4', 'king', '0', '17352736352', '2022-06-06', null, '无', null, null, null, '0');

-- ----------------------------
-- Table structure for tb_res_add
-- ----------------------------
DROP TABLE IF EXISTS `tb_res_add`;
CREATE TABLE `tb_res_add` (
  `skip_id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址住户关联表',
  `add_id` int NOT NULL COMMENT '地址ID',
  `res_id` int NOT NULL COMMENT '住户ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`skip_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_res_add
-- ----------------------------
INSERT INTO `tb_res_add` VALUES ('1', '1', '1', '2028-01-23 21:17:39', '2028-01-23 21:17:39', '0');
INSERT INTO `tb_res_add` VALUES ('2', '2', '2', '2028-01-23 21:19:06', '2028-01-23 21:19:06', '0');
INSERT INTO `tb_res_add` VALUES ('3', '3', '3', '2028-01-23 21:19:10', '2028-01-23 21:19:10', '0');
INSERT INTO `tb_res_add` VALUES ('4', '5', '5', '2028-01-23 21:19:13', '2028-01-23 21:19:13', '0');
INSERT INTO `tb_res_add` VALUES ('5', '8', '6', '2028-01-23 21:19:15', '2028-01-23 21:19:15', '0');
INSERT INTO `tb_res_add` VALUES ('6', '9', '16', '2028-01-23 21:19:26', '2028-01-23 21:19:26', '0');
INSERT INTO `tb_res_add` VALUES ('7', '10', '17', '2028-01-23 21:19:44', '2028-01-23 21:19:44', '0');
INSERT INTO `tb_res_add` VALUES ('8', '11', '18', '2028-01-23 21:20:02', '2028-01-23 21:20:02', '0');
INSERT INTO `tb_res_add` VALUES ('9', '12', '19', '2028-01-23 21:20:06', '2028-01-23 21:20:06', '0');
INSERT INTO `tb_res_add` VALUES ('10', '13', '20', '2028-01-23 21:20:09', '2028-01-23 21:20:09', '0');
INSERT INTO `tb_res_add` VALUES ('11', '14', '21', '2028-01-23 21:20:12', '2028-01-23 21:20:12', '0');
INSERT INTO `tb_res_add` VALUES ('12', '15', '22', '2028-01-23 21:20:16', '2028-01-23 21:20:16', '0');
INSERT INTO `tb_res_add` VALUES ('13', '16', '23', '2028-01-23 21:20:20', '2028-01-23 21:20:20', '0');
INSERT INTO `tb_res_add` VALUES ('14', '17', '24', '2028-01-23 21:20:26', '2028-01-23 21:20:26', '0');
INSERT INTO `tb_res_add` VALUES ('15', '18', '25', '2028-01-23 21:22:38', '2028-01-23 21:22:38', '0');
INSERT INTO `tb_res_add` VALUES ('16', '19', '26', '2028-01-23 21:22:41', '2028-01-23 21:22:41', '0');
INSERT INTO `tb_res_add` VALUES ('17', '20', '27', '2028-01-23 21:22:44', '2028-01-23 21:22:44', '0');
INSERT INTO `tb_res_add` VALUES ('18', '21', '28', '2028-01-23 21:22:47', '2028-01-23 21:22:47', '0');
INSERT INTO `tb_res_add` VALUES ('19', '22', '29', '2028-01-23 21:22:50', '2028-01-23 21:22:50', '0');
INSERT INTO `tb_res_add` VALUES ('20', '23', '30', '2028-01-23 21:22:53', '2028-01-23 21:22:53', '0');
INSERT INTO `tb_res_add` VALUES ('21', '24', '31', '2028-01-23 21:22:57', '2028-01-23 21:22:57', '0');
INSERT INTO `tb_res_add` VALUES ('22', '25', '32', '2028-01-23 21:23:00', '2028-01-23 21:23:00', '0');
INSERT INTO `tb_res_add` VALUES ('23', '26', '33', '2028-01-23 21:23:02', '2028-01-23 21:23:02', '0');
INSERT INTO `tb_res_add` VALUES ('24', '4', '34', '2028-01-23 22:29:34', '2028-01-23 22:29:34', '0');
INSERT INTO `tb_res_add` VALUES ('25', '65', '35', '2028-01-25 16:16:55', '2028-01-25 16:16:55', '0');
INSERT INTO `tb_res_add` VALUES ('26', '1', '36', '2028-01-25 16:37:47', '2028-01-25 16:37:47', '0');
INSERT INTO `tb_res_add` VALUES ('27', '1', '37', '2028-01-25 16:40:26', '2028-01-25 16:40:26', '0');
INSERT INTO `tb_res_add` VALUES ('28', '67', '222', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('29', '1', '1', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('30', '1', '2', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('31', '73', '3', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('32', '74', '4', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('33', '75', '1', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('34', '80', '1', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('35', '80', '2', null, null, '0');
INSERT INTO `tb_res_add` VALUES ('36', '76', '3', null, null, '0');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'admin', '2028-01-05 13:33:20', '2028-01-10 10:36:31');
INSERT INTO `tb_role` VALUES ('2', 'user', '2028-01-05 13:33:25', '2028-01-10 10:36:53');

-- ----------------------------
-- Table structure for tb_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_auth`;
CREATE TABLE `tb_role_auth` (
  `role_auth` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `auth_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_auth`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `auth_id` (`auth_id`) USING BTREE,
  CONSTRAINT `tb_role_auth_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_role_auth_ibfk_2` FOREIGN KEY (`auth_id`) REFERENCES `tb_authority` (`auth_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_role_auth
-- ----------------------------
INSERT INTO `tb_role_auth` VALUES ('1', '1', '1', '2028-01-08 13:33:44', '2028-01-08 13:33:44');
INSERT INTO `tb_role_auth` VALUES ('2', '1', '2', '2028-01-08 13:33:48', '2028-01-08 13:33:48');
INSERT INTO `tb_role_auth` VALUES ('3', '1', '3', '2028-01-08 13:33:56', '2028-01-08 13:33:56');
INSERT INTO `tb_role_auth` VALUES ('4', '1', '4', '2028-01-08 13:34:16', '2028-01-08 13:34:16');
INSERT INTO `tb_role_auth` VALUES ('5', '1', '5', '2028-01-08 13:34:19', '2028-01-08 13:34:19');
INSERT INTO `tb_role_auth` VALUES ('6', '1', '6', '2028-01-08 13:34:23', '2028-01-08 13:34:23');
INSERT INTO `tb_role_auth` VALUES ('7', '1', '7', '2028-01-08 13:34:25', '2028-01-08 13:34:25');
INSERT INTO `tb_role_auth` VALUES ('8', '2', '3', '2028-01-08 13:34:29', '2028-01-08 13:35:04');
INSERT INTO `tb_role_auth` VALUES ('9', '2', '4', '2028-01-08 13:34:31', '2028-01-08 13:35:07');
INSERT INTO `tb_role_auth` VALUES ('10', '2', '5', '2028-01-08 13:34:39', '2028-01-08 13:35:10');
INSERT INTO `tb_role_auth` VALUES ('11', '2', '6', '2028-01-08 13:34:57', '2028-01-08 13:35:14');
INSERT INTO `tb_role_auth` VALUES ('12', '2', '7', '2028-01-08 13:35:01', '2028-01-08 13:35:01');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用来找回密码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('5', '1005', '3899f181107fc446ecef3b0b1d9d0891', '李科', '17623249171', '2028-03-06 16:21:25', '2028-03-06 16:21:25', '0');
INSERT INTO `tb_user` VALUES ('6', '1006', 'c0123e0cbc5ef6ba5a7e1eecb0100eba', 'ddd', '18273645463', '2028-03-06 16:25:14', '2028-03-06 16:25:14', '0');
INSERT INTO `tb_user` VALUES ('9', '1001', '955766906007172822e851119fca4bd6', '戴晓旭', '15723307205', null, null, '0');
INSERT INTO `tb_user` VALUES ('10', '1002', '18bcf52df1f553d581515ab009fa8e37', 'test', '19283746554', null, null, '0');

-- ----------------------------
-- Table structure for tb_usersalt
-- ----------------------------
DROP TABLE IF EXISTS `tb_usersalt`;
CREATE TABLE `tb_usersalt` (
  `salt_id` int NOT NULL AUTO_INCREMENT,
  `salt_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt_status` int NOT NULL DEFAULT '0' COMMENT '0未使用，1密码已经被使用',
  `salt_role` int NOT NULL DEFAULT '2' COMMENT '1代表生成的管理员的salt，2生成的为普通用户，默认为2',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`salt_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_usersalt
-- ----------------------------
INSERT INTO `tb_usersalt` VALUES ('1', 'PY33SYYjbkhM1dVwrwpC', '1', '2', '2028-03-06 11:29:29', '2028-03-06 11:29:29', '0');
INSERT INTO `tb_usersalt` VALUES ('2', '7qAYh6CAUi0GXiZUBcTA', '0', '1', '2028-03-06 20:47:06', '2028-03-06 20:47:06', '0');
INSERT INTO `tb_usersalt` VALUES ('3', 'Z1ezRNbkiDfMceWw2tqO', '0', '2', '2028-03-06 20:50:27', '2028-03-06 20:50:27', '0');
INSERT INTO `tb_usersalt` VALUES ('4', 'nI5zamz4EzGuHHNWL1iK', '0', '2', '2028-03-06 20:50:27', '2028-03-06 20:50:27', '0');
INSERT INTO `tb_usersalt` VALUES ('5', 'BhOUpexQZWS0XOnm2cXV', '0', '2', '2028-03-06 20:50:27', '2028-03-06 20:50:27', '0');
INSERT INTO `tb_usersalt` VALUES ('6', '0I5Wwt74oSlRh1Ihpvql', '0', '2', '2028-03-06 20:50:27', '2028-03-06 20:50:27', '0');
INSERT INTO `tb_usersalt` VALUES ('7', '0rMadTNO3GBhfbM7Txxf', '0', '2', '2028-03-07 20:40:11', '2028-03-07 20:40:11', '0');
INSERT INTO `tb_usersalt` VALUES ('8', 'IlD7y4sFwetcb3fLxvoj', '0', '2', '2028-03-07 20:40:16', '2028-03-07 20:40:16', '0');
INSERT INTO `tb_usersalt` VALUES ('9', 'Y0ELW21LvSKpemwPajEl', '0', '2', '2028-03-06 21:02:58', '2028-03-06 21:02:58', '0');
INSERT INTO `tb_usersalt` VALUES ('10', 'd6sejHqZiE2q2EXC6ybW', '0', '1', '2028-03-07 20:40:19', '2028-03-07 20:40:19', '0');
INSERT INTO `tb_usersalt` VALUES ('11', 'AD5wtY7tH9GJTGCsWi3b', '0', '2', '2022-04-04 19:54:11', '2022-04-04 19:54:11', '0');
INSERT INTO `tb_usersalt` VALUES ('12', 'Kbu4UWfvufswbNdbd8L6', '0', '2', '2022-04-04 20:03:47', '2022-04-04 20:03:47', '0');
INSERT INTO `tb_usersalt` VALUES ('13', 'vVgVw80qHHb456PyVk8z', '0', '2', '2022-04-04 20:03:47', '2022-04-04 20:03:47', '0');
INSERT INTO `tb_usersalt` VALUES ('14', 'sUzogropK5XRp8ellCK5', '0', '2', '2022-04-04 20:03:47', '2022-04-04 20:03:47', '0');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_role` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1', '2028-01-27 18:10:19', '2028-01-27 18:10:19', '0');
INSERT INTO `tb_user_role` VALUES ('2', '2', '2', '2028-01-27 18:10:26', '2028-01-27 18:10:26', '0');
INSERT INTO `tb_user_role` VALUES ('3', '3', '2', '2028-01-27 18:10:36', '2028-01-27 18:10:36', '0');
INSERT INTO `tb_user_role` VALUES ('4', '4', '2', '2028-01-28 11:46:28', '2028-01-28 11:46:28', '0');
INSERT INTO `tb_user_role` VALUES ('5', '5', '1', '2028-03-06 16:21:25', '2028-03-06 16:21:25', '0');
INSERT INTO `tb_user_role` VALUES ('6', '6', '2', '2028-03-06 16:25:14', '2028-03-06 16:25:14', '0');
INSERT INTO `tb_user_role` VALUES ('7', '7', '1', null, null, '0');
INSERT INTO `tb_user_role` VALUES ('8', '8', '1', null, null, '0');
INSERT INTO `tb_user_role` VALUES ('9', '9', '1', null, null, '0');
INSERT INTO `tb_user_role` VALUES ('10', '10', '1', null, null, '0');

-- ----------------------------
-- Table structure for tb_vercode
-- ----------------------------
DROP TABLE IF EXISTS `tb_vercode`;
CREATE TABLE `tb_vercode` (
  `ver_id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ver_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ver_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_vercode
-- ----------------------------
INSERT INTO `tb_vercode` VALUES ('1', '1001', '3279', '2028-03-08 19:57:31', '2028-03-08 19:57:31', '1');
INSERT INTO `tb_vercode` VALUES ('2', '1001', '1969', '2028-03-08 19:57:34', '2028-03-08 19:57:34', '1');
INSERT INTO `tb_vercode` VALUES ('3', '1001', '7008', '2028-03-08 19:57:36', '2028-03-08 19:57:36', '1');
INSERT INTO `tb_vercode` VALUES ('4', '1001', '5793', '2028-03-08 20:06:55', '2028-03-08 20:06:55', '1');
INSERT INTO `tb_vercode` VALUES ('5', '1001', '8945', '2028-03-08 20:31:24', '2028-03-08 20:31:24', '1');
INSERT INTO `tb_vercode` VALUES ('6', '1001', '2669', '2028-03-08 20:45:38', '2028-03-08 20:45:38', '1');
INSERT INTO `tb_vercode` VALUES ('7', '1001', '9606', '2028-03-08 20:45:42', '2028-03-08 20:45:42', '1');
INSERT INTO `tb_vercode` VALUES ('8', '1001', '2272', '2028-03-08 21:07:08', '2028-03-08 21:07:08', '1');
INSERT INTO `tb_vercode` VALUES ('9', '1001', '2528', '2028-03-08 21:07:23', '2028-03-08 21:10:30', '1');
INSERT INTO `tb_vercode` VALUES ('10', '1001', '0739', '2028-03-08 21:23:28', '2028-03-08 21:23:36', '1');

-- ----------------------------
-- Table structure for tb_visitor
-- ----------------------------
DROP TABLE IF EXISTS `tb_visitor`;
CREATE TABLE `tb_visitor` (
  `vis_id` int NOT NULL AUTO_INCREMENT,
  `add_id` int NOT NULL,
  `vis_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `vis_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `vis_remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`vis_id`) USING BTREE,
  KEY `res_id` (`add_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_visitor
-- ----------------------------
INSERT INTO `tb_visitor` VALUES ('9', '75', 'ddd', '16253425342', '', null, null, '0');
