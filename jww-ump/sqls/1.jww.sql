/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : jww

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-31 23:10:01
*/

DROP DATABASE IF EXISTS `jww`;
CREATE DATABASE IF NOT EXISTS `jww` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jww`;
-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `unit_id` bigint(20) NOT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=947126071521034243 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '1', 'JWW', '0', '1', '0', '0', '1', 'qw', '1', '2016-06-28 18:04:06', '1', '2017-12-30 23:34:59');

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(50) NOT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `parent_type` varchar(50) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `type__code_` (`type_`,`code_`,`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=947147856354037763 DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('1', 'SEX', '0', '未知', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2017-12-31 00:50:07');
INSERT INTO `sys_dic` VALUES ('2', 'SEX', '1', '男', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:12');
INSERT INTO `sys_dic` VALUES ('3', 'SEX', '2', '女', null, null, '3', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2017-12-30 23:35:11');
INSERT INTO `sys_dic` VALUES ('4', 'LOCKED', '0', '激活', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:11');
INSERT INTO `sys_dic` VALUES ('5', 'LOCKED', '1', '锁定', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:10');
INSERT INTO `sys_dic` VALUES ('6', 'ROLETYPE', '1', '业务角色', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:09');
INSERT INTO `sys_dic` VALUES ('7', 'ROLETYPE', '2', '管理角色', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:09');
INSERT INTO `sys_dic` VALUES ('8', 'ROLETYPE', '3', '系统内置角色', null, null, '3', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:08');
INSERT INTO `sys_dic` VALUES ('9', 'LEAF', '0', '树枝节点', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2017-12-27 15:24:38');
INSERT INTO `sys_dic` VALUES ('10', 'LEAF', '1', '叶子节点', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:07');
INSERT INTO `sys_dic` VALUES ('11', 'EDITABLE', '0', '只读', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:06');
INSERT INTO `sys_dic` VALUES ('12', 'EDITABLE', '1', '可编辑', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:06');
INSERT INTO `sys_dic` VALUES ('13', 'ENABLE', '0', '禁用', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:05');
INSERT INTO `sys_dic` VALUES ('14', 'ENABLE', '1', '启用', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:04');
INSERT INTO `sys_dic` VALUES ('15', 'AUTHORIZELEVEL', '1', '访问权限', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:03');
INSERT INTO `sys_dic` VALUES ('16', 'AUTHORIZELEVEL', '2', '管理权限', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:02');
INSERT INTO `sys_dic` VALUES ('17', 'MENUTYPE', '1', '系统菜单', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:03');
INSERT INTO `sys_dic` VALUES ('18', 'MENUTYPE', '2', '业务菜单', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:01');
INSERT INTO `sys_dic` VALUES ('19', 'USERTYPE', '1', '经办员', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:50');
INSERT INTO `sys_dic` VALUES ('20', 'USERTYPE', '2', '管理员', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:48');
INSERT INTO `sys_dic` VALUES ('21', 'USERTYPE', '3', '系统内置用户', null, null, '3', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:47');
INSERT INTO `sys_dic` VALUES ('22', 'EXPAND', '0', '收缩', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:47');
INSERT INTO `sys_dic` VALUES ('23', 'EXPAND', '1', '展开', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:46');
INSERT INTO `sys_dic` VALUES ('24', 'CRUD', 'add', '新增', null, null, '1', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:56');
INSERT INTO `sys_dic` VALUES ('25', 'CRUD', 'read', '查询', null, null, '2', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:58');
INSERT INTO `sys_dic` VALUES ('26', 'CRUD', 'update', '修改', null, null, '3', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:59');
INSERT INTO `sys_dic` VALUES ('27', 'CRUD', 'delete', '删除', null, null, '4', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:03:59');
INSERT INTO `sys_dic` VALUES ('28', 'CRUD', 'open', '打开', null, null, '5', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:00');
INSERT INTO `sys_dic` VALUES ('29', 'CRUD', 'close', '关闭', null, null, '6', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:01');
INSERT INTO `sys_dic` VALUES ('30', 'CRUD', 'run', '执行', null, null, '7', '0', '0', '1', null, '1', '2016-06-28 18:04:06', '1', '2016-06-28 18:04:01');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `operation_` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `operation_type` tinyint(1) DEFAULT NULL COMMENT '操作类型',
  `method_` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params_` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `result_` tinyint(1) DEFAULT NULL COMMENT '操作结果',
  `time_` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip_` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=947484293540982786 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` smallint(2) DEFAULT '2' COMMENT '菜单类型(0：目录1：菜单2：按钮)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission_` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=947128275107692546 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '0', 'icon-xitongguanli', '#', '1', '1', '1', '', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-30 23:35:04');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '1', '1', 'icon-yonghuguanli', 'page/user/userList.html', '0', '1', '1', '', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-30 23:33:43');
INSERT INTO `sys_menu` VALUES ('3', '部门管理', '1', '1', 'icon-bumenguanli', 'page/dept/deptList.html', '0', '2', '1', '', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-28 22:41:08');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '1', '1', 'icon-caidanguanli', 'page/menu/menuList.html', '0', '3', '1', '', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-28 22:41:28');
INSERT INTO `sys_menu` VALUES ('5', '角色管理', '1', '1', 'icon-jiaoseguanli', 'page/role/roleList.html', '0', '4', '1', '', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-29 15:51:49');
INSERT INTO `sys_menu` VALUES ('7', '字典管理', '1', '1', 'icon-ccgl-shujuzidian-1', 'page/dic/dicList.html', '0', '7', '1', 'sys:dic', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-29 23:10:16');
INSERT INTO `sys_menu` VALUES ('8', '参数管理', '1', '1', 'icon-xitongcanshuguanli', 'page/param/paramList.html', '0', '8', '1', '', '', '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-28 22:42:19');
INSERT INTO `sys_menu` VALUES ('9', '日志管理', '1', '1', 'icon-ccgl-shujuzidian-1', 'page/log/logList.html', '0', '9', '1', 'sys:log:read', '', '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-29 23:07:05');
INSERT INTO `sys_menu` VALUES ('18', '新增', '2', '2', null, null, '0', '2', '1', 'sys:user:add,sys:role:read,sys:user:read', null, '0', '1', '1', '2017-12-19 11:22:58', '1', '2017-12-30 14:46:41');
INSERT INTO `sys_menu` VALUES ('19', '修改', '2', '2', null, null, '0', '3', '1', 'sys:user:update,sys:role:read,sys:user:read', null, '0', '1', '1', '2017-12-19 12:35:36', '1', '2017-12-30 15:14:57');
INSERT INTO `sys_menu` VALUES ('21', '删除', '2', '2', null, null, '0', '4', '1', 'sys:user:delete', null, '0', '1', '1', '2017-12-19 12:37:20', '1', '2017-12-19 12:37:23');
INSERT INTO `sys_menu` VALUES ('22', '应用监控', '1', '1', 'icon-jiaoseguanli', 'druid/webapp.html', '0', '12', '1', 'sys:sql:read', null, '0', '1', '1', '2016-06-20 09:16:56', '1', '2017-12-28 22:44:36');
INSERT INTO `sys_menu` VALUES ('945569164808769538', '新增', '2', '5', null, null, '0', '2', '1', 'sys:role:add,sys:menu:read', null, '0', '1', '1', '2017-12-26 16:17:11', '1', '2017-12-29 15:49:48');
INSERT INTO `sys_menu` VALUES ('945569292122673153', '修改', '2', '5', null, null, '0', '3', '1', 'sys:role:update,sys:role:read,sys:menu:read', null, '0', '1', '1', '2017-12-26 16:17:42', '1', '2017-12-29 15:49:57');
INSERT INTO `sys_menu` VALUES ('945569401908580354', '删除', '2', '5', null, null, '0', '4', '1', 'sys:role:delete', null, '0', '1', '1', '2017-12-26 16:18:08', '1', '2017-12-26 16:20:08');
INSERT INTO `sys_menu` VALUES ('945570894350995458', '新增', '2', '3', null, null, '0', '2', '1', 'sys:dept:add', null, '0', '1', '1', '2017-12-26 16:24:04', '1', '2017-12-26 16:24:53');
INSERT INTO `sys_menu` VALUES ('945570988546674689', '修改', '2', '3', null, null, '0', '3', '1', 'sys:dept:update,sys:dept:read', null, '0', '1', '1', '2017-12-26 16:24:26', '1', '2017-12-26 16:24:54');
INSERT INTO `sys_menu` VALUES ('945571061959577601', '删除', '2', '3', null, null, '0', '4', '1', 'sys:dept:delete', null, '0', '1', '1', '2017-12-26 16:24:44', '1', '2017-12-26 16:24:56');
INSERT INTO `sys_menu` VALUES ('945571423827349506', '新增', '2', '4', null, null, '0', '2', '1', 'sys:menu:add', null, '0', '1', '1', '2017-12-26 16:26:10', '1', '2017-12-26 16:26:51');
INSERT INTO `sys_menu` VALUES ('945571487333306370', '修改', '2', '4', null, null, '0', '3', '1', 'sys:menu:update,sys:menu:read', null, '0', '1', '1', '2017-12-26 16:26:25', '1', '2017-12-26 16:26:53');
INSERT INTO `sys_menu` VALUES ('945571554194706434', '删除', '2', '4', null, null, '0', '4', '1', 'sys:menu:delete', null, '0', '1', '1', '2017-12-26 16:26:41', '1', '2017-12-30 23:33:32');
INSERT INTO `sys_menu` VALUES ('946651550468222977', '新增', '2', '8', null, null, '0', '2', '1', 'sys:param:add', null, '0', '1', '1', '2017-12-29 15:58:12', '1', '2017-12-29 23:34:44');
INSERT INTO `sys_menu` VALUES ('946651788901822466', '修改', '2', '8', null, null, '0', '3', '1', 'sys:param:update,sys:param:read', null, '0', '1', '1', '2017-12-29 15:59:09', '1', '2017-12-30 15:07:09');
INSERT INTO `sys_menu` VALUES ('946651900851990530', '删除', '2', '8', null, null, '0', '4', '1', 'sys:param:delete', null, '0', '1', '1', '2017-12-29 15:59:36', '1', '2017-12-29 23:34:48');
INSERT INTO `sys_menu` VALUES ('946748446155739137', '查看', '2', '2', null, null, '0', '1', '1', 'sys:user:read,sys:role:read', null, '0', '1', '1', '2017-12-29 22:23:13', '1', '2017-12-30 23:25:49');
INSERT INTO `sys_menu` VALUES ('946758013140967425', '查看', '2', '3', null, null, '0', '1', '1', 'sys:dept:read', null, '0', '1', '1', '2017-12-29 23:01:15', '1', '2017-12-30 23:24:45');
INSERT INTO `sys_menu` VALUES ('946758083039043585', '查看', '2', '4', null, null, '0', '1', '1', 'sys:menu:read', null, '0', '1', '1', '2017-12-29 23:01:32', '1', '2017-12-29 23:35:15');
INSERT INTO `sys_menu` VALUES ('946758190165762049', '查看', '2', '5', null, null, '0', '1', '1', 'sys:role:read', null, '0', '1', '1', '2017-12-29 23:01:57', '1', '2017-12-29 23:35:13');
INSERT INTO `sys_menu` VALUES ('946758477211344898', '查看', '2', '7', null, null, '0', null, '1', 'sys:dic:read', null, '1', '1', '1', '2017-12-29 23:03:06', '1', '2017-12-29 23:07:17');
INSERT INTO `sys_menu` VALUES ('946758541132537857', '查看', '2', '8', null, null, '0', '1', '1', 'sys:param:read', null, '0', '1', '1', '2017-12-29 23:03:21', '1', '2017-12-29 23:35:09');
INSERT INTO `sys_menu` VALUES ('946758603266957313', '查看', '2', '9', null, null, '0', null, '1', 'sys:log:read', null, '1', '1', '1', '2017-12-29 23:03:36', '1', '2017-12-29 23:07:01');

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` bigint(20) DEFAULT NULL,
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=946767126541475843 DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- ----------------------------
-- Records of sys_param
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=947150613936922626 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '1', '1', '1', '0', null, '1', '2016-06-20 09:16:56', '1', '2017-12-31 22:42:59');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=947478195886272514 DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('947478193923338241', '1', '1', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194036584450', '1', '2', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194099499010', '1', '946748446155739137', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194174996481', '1', '18', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194229522433', '1', '19', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194288242690', '1', '21', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194346962946', '1', '3', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194393100289', '1', '946758013140967425', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194451820546', '1', '945570894350995458', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194502152194', '1', '945570988546674689', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194569261058', '1', '945571061959577601', '', '1', '0', '', '1', '2017-12-31 22:42:58', '1', '2017-12-31 22:42:58');
INSERT INTO `sys_role_menu` VALUES ('947478194657341441', '1', '4', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478194732838913', '1', '946758083039043585', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478194829307905', '1', '945571423827349506', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478194908999681', '1', '945571487333306370', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195026440193', '1', '945571554194706434', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195139686401', '1', '5', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195252932610', '1', '946758190165762049', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195374567425', '1', '945569164808769538', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195500396545', '1', '945569292122673153', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195584282625', '1', '945569401908580354', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195659780098', '1', '7', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195714306049', '1', '8', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195781414913', '1', '946758541132537857', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195827552258', '1', '9', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');
INSERT INTO `sys_role_menu` VALUES ('947478195886272513', '1', '22', '', '1', '0', '', '1', '2017-12-31 22:42:59', '1', '2017-12-31 22:42:59');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password_` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(2) DEFAULT '1' COMMENT '用户类型(1普通用户2管理员3系统用户)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `name_pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `sex_` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `avatar_` varchar(500) DEFAULT NULL COMMENT '头像',
  `phone_` varchar(50) DEFAULT NULL COMMENT '电话',
  `email_` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `wei_xin` varchar(32) DEFAULT NULL COMMENT '微信',
  `wei_bo` varchar(32) DEFAULT NULL COMMENT '微博',
  `qq_` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门编号',
  `position_` varchar(64) DEFAULT NULL COMMENT '职位',
  `address_` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `staff_no` varchar(32) DEFAULT NULL COMMENT '工号',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable_` tinyint(1) DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB AUTO_INCREMENT=947023181112483842 DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'YzlhZGMyNGYwNTk3NjA1NWQ4ZjdlZWI2NzlkNzIxZDk=', '3', '管理员', 'GUANLIYUAN', '0', 'http://118.190.43.148/group1/M00/00/00/dr4rlFjNBguAfJl7AAcOE67NTFk744.png', '', '', '', '', null, null, '0000-00-00', '1', '', null, null, '0', '1', '', '2016-05-06 10:06:52', '1', '2017-12-31 22:28:48', '1');

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_user_menu_key1` (`user_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES ('1', '1', '1', 'read', '1', '0', null, '0', '2017-08-28 16:24:01', '0', '2017-08-28 16:24:01');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=947474481788772354 DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '1', '0', null, '1', '2017-12-30 15:38:21', '1', '2017-12-30 15:38:21');
