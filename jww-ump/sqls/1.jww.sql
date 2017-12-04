-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.10 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5143
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 jww 的数据库结构
DROP DATABASE IF EXISTS `jww`;
CREATE DATABASE IF NOT EXISTS `jww` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jww`;

-- 导出  表 jww.sys_article 结构
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE IF NOT EXISTS `sys_article` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(2) DEFAULT NULL COMMENT '类型',
  `author_` varchar(16) DEFAULT NULL COMMENT '作者',
  `title_` varchar(128) DEFAULT NULL COMMENT '标题',
  `content_` longtext COMMENT '内容',
  `out_url` varchar(512) DEFAULT NULL COMMENT '外部链接',
  `seo_keyword` varchar(64) DEFAULT NULL COMMENT 'seo关键字',
  `seo_description` varchar(256) DEFAULT NULL COMMENT 'seo描述',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- 正在导出表  jww.sys_article 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_article` ENABLE KEYS */;

-- 导出  表 jww.sys_dept 结构
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `unit_id` bigint(20) NOT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- 正在导出表  jww.sys_dept 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`id_`, `unit_id`, `dept_name`, `parent_id`, `sort_no`, `leaf_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 'jww', 0, 1, 0, 1, 'qw', 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(2, 1, '市场部', 1, 1, 1, 1, 't', 0, '2016-06-28 18:04:06', 0, '2016-06-28 18:04:06'),
	(825363166504628224, 1, '技术部', 1, 2, NULL, NULL, '', 1, '2017-01-28 23:21:28', 1, '2017-05-29 08:15:29');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 jww.sys_dic 结构
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE IF NOT EXISTS `sys_dic` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(50) NOT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `parent_type` varchar(50) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `type__code_` (`type_`,`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

-- 正在导出表  jww.sys_dic 的数据：~43 rows (大约)
/*!40000 ALTER TABLE `sys_dic` DISABLE KEYS */;
INSERT INTO `sys_dic` (`id_`, `type_`, `code_`, `code_text`, `parent_type`, `parent_code`, `sort_no`, `editable_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 'SEX', '0', '未知', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:13'),
	(2, 'SEX', '1', '男', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:12'),
	(3, 'SEX', '2', '女', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(4, 'LOCKED', '0', '激活', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(5, 'LOCKED', '1', '锁定', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:10'),
	(6, 'ROLETYPE', '1', '业务角色', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(7, 'ROLETYPE', '2', '管理角色', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(8, 'ROLETYPE', '3', '系统内置角色', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:08'),
	(9, 'LEAF', '0', '树枝节点', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(10, 'LEAF', '1', '叶子节点', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(11, 'EDITABLE', '0', '只读', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(12, 'EDITABLE', '1', '可编辑', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(13, 'ENABLE', '0', '禁用', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:05'),
	(14, 'ENABLE', '1', '启用', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:04'),
	(15, 'AUTHORIZELEVEL', '1', '访问权限', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(16, 'AUTHORIZELEVEL', '2', '管理权限', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:02'),
	(17, 'MENUTYPE', '1', '系统菜单', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(18, 'MENUTYPE', '2', '业务菜单', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(19, 'USERTYPE', '1', '经办员', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:50'),
	(20, 'USERTYPE', '2', '管理员', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:48'),
	(21, 'USERTYPE', '3', '系统内置用户', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(22, 'EXPAND', '0', '收缩', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(23, 'EXPAND', '1', '展开', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:46'),
	(24, 'CRUD', 'add', '新增', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:56'),
	(25, 'CRUD', 'read', '查询', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:58'),
	(26, 'CRUD', 'update', '修改', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(27, 'CRUD', 'delete', '删除', NULL, NULL, 4, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(28, 'CRUD', 'open', '打开', NULL, NULL, 5, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:00'),
	(29, 'CRUD', 'close', '关闭', NULL, NULL, 6, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(30, 'CRUD', 'run', '执行', NULL, NULL, 7, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01');
/*!40000 ALTER TABLE `sys_dic` ENABLE KEYS */;

-- 导出  表 jww.sys_email 结构
DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE IF NOT EXISTS `sys_email` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '邮件编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `sender_` varchar(32) NOT NULL COMMENT '使用发送',
  `email_title` varchar(256) NOT NULL COMMENT '发送标题',
  `email_content` text NOT NULL COMMENT '发送内容',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件表';

-- 正在导出表  jww.sys_email 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_email` DISABLE KEYS */;
INSERT INTO `sys_email` (`id_`, `email_name`, `sender_`, `email_title`, `email_content`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 'test', 't', 'a', '并蒂芙蓉', NULL, 1, 1, '2017-02-02 16:37:54', 1, '2017-02-02 16:37:54');
/*!40000 ALTER TABLE `sys_email` ENABLE KEYS */;

-- 导出  表 jww.sys_email_config 结构
DROP TABLE IF EXISTS `sys_email_config`;
CREATE TABLE IF NOT EXISTS `sys_email_config` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '邮件配置编号',
  `smtp_host` varchar(32) NOT NULL COMMENT 'SMTP服务器',
  `smtp_port` varchar(8) NOT NULL COMMENT 'SMTP服务器端口',
  `send_method` varchar(16) NOT NULL COMMENT '发送方式',
  `sender_name` varchar(64) NOT NULL COMMENT '名称',
  `sender_account` varchar(32) NOT NULL COMMENT '发邮件邮箱账号',
  `sender_password` varchar(32) NOT NULL COMMENT '发邮件邮箱密码',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件配置表';

-- 正在导出表  jww.sys_email_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_email_config` DISABLE KEYS */;
INSERT INTO `sys_email_config` (`id_`, `smtp_host`, `smtp_port`, `send_method`, `sender_name`, `sender_account`, `sender_password`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(828157583909109760, 'smtp.163.com', '101', '0', 'jww', 'jww@163.com', 'BK5sgjz5JOOsFuD4w0mbe7==', NULL, 1, 1, '2017-02-05 16:25:29', 1, '2017-02-05 16:37:50');
/*!40000 ALTER TABLE `sys_email_config` ENABLE KEYS */;

-- 导出  表 jww.sys_email_template 结构
DROP TABLE IF EXISTS `sys_email_template`;
CREATE TABLE IF NOT EXISTS `sys_email_template` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '邮件模版编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `email_account` varchar(32) DEFAULT NULL COMMENT '发送邮件帐号',
  `sort_` int(5) DEFAULT NULL COMMENT '排序号',
  `title_` varchar(512) DEFAULT NULL COMMENT '标题模版',
  `template_` text COMMENT '内容模板',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件模版表';

-- 正在导出表  jww.sys_email_template 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_email_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_email_template` ENABLE KEYS */;

-- 导出  表 jww.sys_event 结构
DROP TABLE IF EXISTS `sys_event`;
CREATE TABLE IF NOT EXISTS `sys_event` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `title_` varchar(50) DEFAULT NULL,
  `request_uri` varchar(50) DEFAULT NULL,
  `parameters_` varchar(500) DEFAULT NULL,
  `method_` varchar(20) DEFAULT NULL,
  `client_host` varchar(50) DEFAULT NULL,
  `user_agent` varchar(300) DEFAULT NULL,
  `status_` int(3) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` text,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  jww.sys_event 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_event` ENABLE KEYS */;

-- 导出  表 jww.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` smallint(2) DEFAULT '2' COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission_` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (1,'系统管理',1,0,'icon-xitongguanli','#',1,1,1,'sys',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 08:19:19');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (2,'用户管理',1,1,'icon-yonghuguanli','page/user/allUsers.html',0,1,1,'sys.base.user',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 08:38:35');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (3,'部门管理',1,1,'icon-bumenguanli','page/dept/deptList.html',0,2,1,'sys.base.dept',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:43');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (4,'菜单管理',1,1,'icon-caidanguanli','main.sys.menu.list',0,3,1,'sys.base.menu',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:45');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (5,'角色管理',1,1,'icon-jiaoseguanli','main.sys.role.list',0,4,1,'sys.base.role',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:47');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (6,'会话管理',1,1,'icon-jiaoseguanli','main.sys.session.list',0,6,1,'sys.base.session',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:49');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (7,'字典管理',1,1,'icon-ccgl-shujuzidian-1','main.sys.dic.list',0,7,1,'sys.base.dic',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:50');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (8,'参数管理',1,1,'icon-xitongcanshuguanli','main.sys.param.list',0,8,1,'sys.base.param',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:07:52');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (9,'调度中心',1,0,'icon-xitongcanshuguanli','#',0,2,1,'sys.task',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-30 14:23:57');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (10,'调度管理',1,9,'icon-xitongcanshuguanli','main.task.scheduled.list',0,3,1,'sys.task.scheduled',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-30 14:24:02');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (11,'调度日志',1,9,'icon-xitongcanshuguanli','main.task.log.list',0,4,1,'sys.task.log',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-28 18:08:48');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (14,'清除缓存',1,1,NULL,NULL,0,9,0,'sys.sys.cache',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 09:39:25');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (15,'用户权限',1,1,NULL,NULL,0,10,0,'sys.permisson.roleMenu',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 09:39:27');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (16,'用户角色',1,1,NULL,NULL,0,11,0,'sys.permisson.useRole',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 09:39:29');
INSERT INTO `sys_menu` (`id_`,`menu_name`,`menu_type`,`parent_id`,`iconcls_`,`request_`,`expand_`,`sort_no`,`is_show`,`permission_`,`remark_`,`enable_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (17,'角色权限',1,1,NULL,NULL,0,12,0,'sys.permisson.userMenu',NULL,1,1,'2016-06-20 09:16:56',1,'2016-06-29 09:39:33');

-- 导出  表 jww.sys_msg 结构
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE IF NOT EXISTS `sys_msg` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `biz_id` varchar(64) NOT NULL COMMENT '平台编号',
  `type_` varchar(32) NOT NULL COMMENT '类型',
  `phone_` varchar(20) NOT NULL COMMENT '接收短信号码',
  `content_` varchar(256) NOT NULL COMMENT '短信内容',
  `send_state` varchar(1) NOT NULL COMMENT '发送状态',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信';

-- 正在导出表  jww.sys_msg 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_msg` ENABLE KEYS */;

-- 导出  表 jww.sys_msg_config 结构
DROP TABLE IF EXISTS `sys_msg_config`;
CREATE TABLE IF NOT EXISTS `sys_msg_config` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `sms_plat_url` varchar(128) DEFAULT NULL COMMENT '短信平台地址',
  `sms_plat_account` varchar(32) DEFAULT NULL COMMENT '短信平台帐号',
  `sms_plat_password` varchar(64) DEFAULT NULL COMMENT '短信平台密码',
  `send_phone` varchar(11) DEFAULT NULL COMMENT '发送短信',
  `sender_name` varchar(32) DEFAULT NULL COMMENT '发送短信签名',
  `order_is_send` tinyint(1) DEFAULT NULL COMMENT '客户下订单时是否给商家发短信',
  `pay_is_send` tinyint(1) DEFAULT NULL COMMENT '客户付款时是否给商家发短信',
  `send_goods_is_send` tinyint(1) DEFAULT NULL COMMENT '商家发货时是否给客户发短信',
  `regist_is_send` tinyint(1) DEFAULT NULL COMMENT '用户注册时是否给客户发短信',
  `advice_goods_is_send` tinyint(1) DEFAULT NULL COMMENT '用户付款后是否给客户发收货验证码',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  jww.sys_msg_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_msg_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_msg_config` ENABLE KEYS */;

-- 导出  表 jww.sys_news 结构
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE IF NOT EXISTS `sys_news` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻编号',
  `news_title` varchar(64) NOT NULL COMMENT '新闻标题',
  `news_type` varchar(8) NOT NULL COMMENT '新闻类型',
  `send_time` datetime NOT NULL COMMENT '发布时间',
  `author_` varchar(32) NOT NULL COMMENT '作者',
  `editor_` varchar(32) NOT NULL COMMENT '编辑',
  `tags_` varchar(128) DEFAULT NULL COMMENT 'Tag标签',
  `keys_` varchar(128) DEFAULT NULL COMMENT '关键字',
  `content_` text COMMENT '内容',
  `reader_times` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `status_` varchar(2) NOT NULL DEFAULT '1' COMMENT '发布状态',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表';

-- 正在导出表  jww.sys_news 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_news` ENABLE KEYS */;

-- 导出  表 jww.sys_notice 结构
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE IF NOT EXISTS `sys_notice` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告编号',
  `notice_title` varchar(128) NOT NULL COMMENT '公告标题',
  `notice_type` varchar(8) NOT NULL COMMENT '公告类型',
  `send_time` datetime DEFAULT NULL COMMENT '发布时间',
  `info_sources` varchar(256) DEFAULT NULL COMMENT '信息来源',
  `sources_url` varchar(2048) DEFAULT NULL COMMENT '来源地址',
  `content_` text COMMENT '内容',
  `reader_times` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `status_` varchar(2) NOT NULL DEFAULT '1' COMMENT '发布状态',
  `remark_` varchar(500) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- 正在导出表  jww.sys_notice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;

-- 导出  表 jww.sys_param 结构
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE IF NOT EXISTS `sys_param` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` bigint(20) DEFAULT NULL,
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- 正在导出表  jww.sys_param 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;

-- 导出  表 jww.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  jww.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id_`, `role_name`, `dept_id`, `role_type`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '管理员', 1, 1, 1, NULL, 1, '2016-06-20 09:16:56', 1, '2017-01-29 10:11:20');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 jww.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (1,1,1,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:04');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (2,1,2,'add',1,NULL,1,'2016-06-29 09:10:10',1,'2016-06-29 09:10:10');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (3,1,2,'delete',1,NULL,1,'2016-06-29 09:10:29',1,'2016-06-29 09:10:29');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (4,1,2,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:07');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (5,1,2,'update',1,NULL,1,'2016-06-29 09:10:20',1,'2016-06-29 09:10:20');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (6,1,3,'add',1,NULL,1,'2016-06-29 09:10:50',1,'2016-06-29 09:10:50');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (7,1,3,'delete',1,NULL,1,'2016-06-29 09:11:18',1,'2016-06-29 09:11:18');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (8,1,3,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:08');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (9,1,3,'update',1,NULL,1,'2016-06-29 09:11:01',1,'2016-06-29 09:11:01');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (10,1,4,'add',1,NULL,1,'2016-06-29 09:12:14',1,'2016-06-29 09:12:14');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (11,1,4,'delete',1,NULL,1,'2016-06-29 09:18:43',1,'2016-06-29 09:18:43');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (12,1,4,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:08');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (13,1,4,'update',1,NULL,1,'2016-06-29 09:18:33',1,'2016-06-29 09:18:33');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (14,1,5,'add',1,NULL,1,'2016-06-29 09:19:00',1,'2016-06-29 09:19:00');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (15,1,5,'delete',1,NULL,1,'2016-06-29 09:19:24',1,'2016-06-29 09:19:24');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (16,1,5,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:09');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (17,1,5,'update',1,NULL,1,'2016-06-29 09:19:10',1,'2016-06-29 09:19:10');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (18,1,6,'delete',1,NULL,1,'2016-06-29 09:19:35',1,'2016-06-29 09:19:35');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (19,1,6,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:09');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (20,1,7,'add',1,NULL,1,'2016-06-29 09:19:58',1,'2016-06-29 09:19:58');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (21,1,7,'delete',1,NULL,1,'2016-06-29 09:20:18',1,'2016-06-29 09:20:18');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (22,1,7,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:10');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (23,1,7,'update',1,NULL,1,'2016-06-29 09:20:08',1,'2016-06-29 09:20:08');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (24,1,8,'add',1,NULL,1,'2016-06-29 09:20:34',1,'2016-06-29 09:20:34');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (25,1,8,'delete',1,NULL,1,'2016-06-29 09:20:53',1,'2016-06-29 09:20:53');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (26,1,8,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:11');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (27,1,8,'update',1,NULL,1,'2016-06-29 09:20:44',1,'2016-06-29 09:20:44');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (28,1,9,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:11');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (29,1,10,'add',1,NULL,1,'2016-06-29 09:21:55',1,'2016-06-29 09:21:55');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (30,1,10,'delete',1,NULL,1,'2016-06-29 09:22:07',1,'2016-06-29 09:22:32');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (31,1,10,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:13');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (32,1,10,'update',1,NULL,1,'2016-06-29 09:22:49',1,'2016-06-29 09:22:49');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (33,1,10,'close',1,NULL,1,'2016-06-29 08:45:21',1,'2016-06-29 08:45:21');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (34,1,10,'open',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:13');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (35,1,10,'run',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:13');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (36,1,11,'read',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:23:13');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (37,1,14,'update',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:43:18');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (38,1,15,'update',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:43:33');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (39,1,16,'update',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:43:34');
INSERT INTO `sys_role_menu` (`id_`,`role_id`,`menu_id`,`permission_`,`enable_`,`remark_`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES (40,1,17,'update',1,NULL,1,'2016-06-28 18:18:50',1,'2016-06-29 08:43:35');

-- 导出  表 jww.sys_session 结构
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE IF NOT EXISTS `sys_session` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(50) DEFAULT NULL,
  `account_` varchar(50) DEFAULT NULL,
  `ip_` varchar(50) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(5000) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会话管理';

-- 正在导出表  jww.sys_session 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_session` ENABLE KEYS */;

-- 导出  表 jww.sys_unit 结构
DROP TABLE IF EXISTS `sys_unit`;
CREATE TABLE IF NOT EXISTS `sys_unit` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(128) NOT NULL COMMENT '单位名称',
  `principal_` varchar(32) DEFAULT NULL COMMENT '负责人',
  `phone_` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `address_` varchar(256) DEFAULT NULL COMMENT '地址',
  `sort_` int(5) DEFAULT NULL COMMENT '排序号',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位表';

-- 正在导出表  jww.sys_unit 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_unit` DISABLE KEYS */;
INSERT INTO `sys_unit` (`id_`, `unit_name`, `principal_`, `phone_`, `address_`, `sort_`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'jww', '经理', '13945678911', '中国', 1, NULL, '', '2017-01-12 00:00:00', 1, '2017-09-19 11:30:17', 1);
/*!40000 ALTER TABLE `sys_unit` ENABLE KEYS */;

-- 导出  表 jww.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
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
  `enable_` tinyint(1) DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 正在导出表  jww.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id_`, `account_`, `password_`, `user_type`, `user_name`, `name_pinyin`, `sex_`, `avatar_`, `phone_`, `email_`, `id_card`, `wei_xin`, `wei_bo`, `qq_`, `birth_day`, `dept_id`, `position_`, `address_`, `staff_no`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '3', '管理员', 'GUANLIYUAN', 0, 'http://118.190.43.148/group1/M00/00/00/dr4rlFjNBguAfJl7AAcOE67NTFk744.png', '15333821711', '12@12', NULL, NULL, NULL, NULL, '2017-01-27', 2, '213', NULL, NULL, 1, '1', '2016-05-06 10:06:52', 1, '2017-03-18 18:03:55', 1),
	(2, 'test', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', 'admin', 'CESHIRENYUAN', 1, 'http://118.190.43.148/group1/M00/00/00/dr4rlFj3H0iATcqFAAv7S9z_iMg689.png', '12345678901', '123@163.com', NULL, NULL, NULL, NULL, '2017-02-01', 825363166504628224, '测试', '', NULL, 1, '1', '2016-05-13 16:58:17', 1, '2017-05-29 08:31:38', 1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 jww.sys_user_menu 结构
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_user_menu_key1` (`user_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  jww.sys_user_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_menu` DISABLE KEYS */;
INSERT INTO `sys_user_menu` (`id_`, `user_id`, `menu_id`, `permission_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 1, 'read', 1, NULL, 0, '2017-08-28 16:24:01', 0, '2017-08-28 16:24:01');
/*!40000 ALTER TABLE `sys_user_menu` ENABLE KEYS */;

-- 导出  表 jww.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  jww.sys_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id_`, `user_id`, `role_id`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 1, 1, NULL, 1, '2016-06-16 15:59:56', 1, '2016-06-16 15:59:56');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 jww.sys_user_thirdparty 结构
DROP TABLE IF EXISTS `sys_user_thirdparty`;
CREATE TABLE IF NOT EXISTS `sys_user_thirdparty` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `provider_` varchar(50) NOT NULL COMMENT '第三方类型',
  `open_id` varchar(50) NOT NULL COMMENT '第三方Id',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_provider__open_id` (`user_id`,`provider_`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- 正在导出表  jww.sys_user_thirdparty 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_thirdparty` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_thirdparty` ENABLE KEYS */;

-- 导出  表 jww.task_fire_log 结构
DROP TABLE IF EXISTS `task_fire_log`;
CREATE TABLE IF NOT EXISTS `task_fire_log` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `status_` varchar(1) NOT NULL DEFAULT 'I',
  `server_host` varchar(50) DEFAULT NULL COMMENT '服务器名',
  `server_duid` varchar(50) DEFAULT NULL COMMENT '服务器网卡序列号',
  `fire_info` text,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `group_name_task_name_start_time` (`group_name`,`task_name`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  jww.task_fire_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `task_fire_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_fire_log` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;