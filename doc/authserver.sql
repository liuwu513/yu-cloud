/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.18 : Database - yu-ahthserver
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yu-ahthserver` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yu-ahthserver`;

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values ('quartzScheduler','SampleJob','DEFAULT','0/1 * * * * ? ','Asia/Shanghai'),('quartzScheduler','SampleParamJob','DEFAULT','0/1 * * * * ? ','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values ('quartzScheduler','SampleJob','DEFAULT',NULL,'SampleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0 thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0 parametert\0\0x\0'),('quartzScheduler','SampleParamJob','DEFAULT',NULL,'SampleParamJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0  thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0 parametert\0Worldx\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('quartzScheduler','STATE_ACCESS'),('quartzScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('quartzScheduler','Howell1534080302504',1534080314417,10000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values ('quartzScheduler','SampleJob','DEFAULT','SampleJob','DEFAULT',NULL,1534080837000,1534080836000,5,'PAUSED','CRON',1534080790000,0,NULL,0,''),('quartzScheduler','SampleParamJob','DEFAULT','SampleParamJob','DEFAULT',NULL,1534081989000,1534081988000,5,'PAUSED','CRON',1534081976000,0,NULL,0,'');

/*Table structure for table `t_area` */

DROP TABLE IF EXISTS `t_area`;

CREATE TABLE `t_area` (
  `area_code` int(11) NOT NULL COMMENT '区域编码',
  `area_name` varchar(64) NOT NULL COMMENT '区域名称',
  `area_type` tinyint(4) NOT NULL COMMENT '区域类型',
  `parent_area_code` int(11) NOT NULL COMMENT '父区域编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `short_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`area_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域表';

/*Data for the table `t_area` */


/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_parent` tinyint(1) DEFAULT NULL,
  `parent_ids` varchar(512) DEFAULT NULL COMMENT '父级部门IDS,以","隔开',
  `area_code` varchar(11) DEFAULT NULL,
  `area_name` varchar(128) DEFAULT NULL,
  `province` varchar(11) DEFAULT NULL,
  `city` varchar(11) DEFAULT NULL,
  `county` varchar(11) DEFAULT NULL COMMENT '县、区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`create_by`,`create_time`,`del_flag`,`update_
by`,`update_time`,`parent_id`,`sort_order`,`status`,`title`,`is_parent`,`paren
t_ids`,`area_code`,`area_name`,`province`,`city`,`county`) values
('40322777781112832','',NULL,0,'682265633886208','2018-09-28 20:19:21','0','1.00',0,'总部',1,'',NULL,'云南省-天津市县-
麒麟区','530000','530300','530302 '),('57701341782347776','',NULL,0,'682265633886208','2018-09-28
20:35:13','40322777781112832','1.00',0,'技术部',1,',40322777781112832',NULL ,'天津市-天津市县-蓟县','120000','120200','120225');

/*Table structure for table `t_dict_data` */

DROP TABLE IF EXISTS `t_dict_data`;

CREATE TABLE `t_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_sn` int(11) DEFAULT NULL COMMENT '序列号',
  `dict_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `dict_number` varchar(50) DEFAULT NULL COMMENT '编号',
  `dict_code` varchar(50) DEFAULT NULL COMMENT '类别编码',
  `pid` int(11) DEFAULT NULL COMMENT '父类别,对应序号sn ',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(2) DEFAULT NULL COMMENT '类别层级',
  `remark` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `dict_sn_index` (`dict_sn`),
  KEY `dict_code_index` (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';


/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `request_param` varchar(255) DEFAULT NULL,
  `request_type` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_log` */


/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `button_type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`description`,`name`,`parent_id`,`type`,`sort_order`,`component`,`path`,`title`,`icon`,`level`,`button_type`,`status`,`url`) values ('15701400130424832','','2018-06-03 22:04:06',0,'','2018-08-13 15:15:47','','','5129710648430593',1,'1.11','','/oss/user/admin/add','添加用户','',3,'add',0,''),('15701915807518720','','2018-06-03 22:06:09',0,'','2018-06-06 14:46:51','','','5129710648430593',1,'1.13','','/oss/user/admin/disable/**','禁用用户','',3,'disable',0,NULL),('15708892205944832','','2018-06-03 22:33:52',0,'','2018-06-28 16:44:48','','','5129710648430593',1,'1.14','','/oss/user/admin/enable/**','启用用户','',3,'enable',0,NULL),('16392452747300864','','2018-06-05 19:50:06',0,'','2018-08-13 18:15:39','','access','',0,'4.00','Main','/access','权限按钮测试页','md-lock',1,'',0,''),('16392767785668608','','2018-06-05 19:51:21',0,'','2018-08-13 18:15:46','','access_index','16392452747300864',0,'4.10','access/access','index','权限按钮测试页','md-lock',2,'',0,''),('16438800255291392','','2018-06-05 22:54:18',0,'','2018-08-13 18:15:51','','','16392767785668608',1,'4.11','','test-add','添加按钮测试','',3,'add',0,''),('16438962738434048','','2018-06-05 22:54:55',0,'','2018-08-13 18:16:29','','','16392767785668608',1,'4.12','','edit-test','编辑按钮测试','',3,'edit',0,''),('16439068543946752','','2018-06-05 22:55:20',0,'','2018-08-13 18:16:12','','','16392767785668608',1,'4.13','','delete-test','删除按钮测试','',3,'delete',0,''),('16678126574637056','','2018-06-06 14:45:16',0,'','2018-06-06 14:46:45','','','5129710648430593',1,'1.12','','/oss/user/admin/edit','编辑用户','',3,'edit',0,NULL),('16678447719911424','','2018-06-06 14:46:32',0,'','2018-08-10 21:41:16','','','5129710648430593',1,'1.15','','/oss/user/delByIds/**','删除用户','',3,'delete',0,''),('16687383932047360',NULL,'2018-06-06 15:22:03',0,NULL,'2018-06-06 15:22:03',NULL,NULL,'5129710648430594',1,'1.21',NULL,'/oss/role/save','添加角色',NULL,3,'add',0,NULL),('16689632049631232',NULL,'2018-06-06 15:30:59',0,NULL,'2018-06-06 15:30:59',NULL,NULL,'5129710648430594',1,'1.22',NULL,'/oss/role/edit','编辑角色',NULL,3,'edit',0,NULL),('16689745006432256','','2018-06-06 15:31:26',0,'','2018-08-10 21:41:23','','','5129710648430594',1,'1.23','','/oss/role/delAllByIds/**','删除角色','',3,'delete',0,''),('16689883993083904',NULL,'2018-06-06 15:31:59',0,NULL,'2018-06-06 15:31:59',NULL,NULL,'5129710648430594',1,'1.24',NULL,'/oss/role/editRolePerm/**','分配权限',NULL,3,'editPerm',0,NULL),('16690313745666048',NULL,'2018-06-06 15:33:41',0,NULL,'2018-06-06 15:33:41',NULL,NULL,'5129710648430594',1,'1.25',NULL,'/oss/role/setDefault','设为默认角色',NULL,3,'setDefault',0,NULL),('16694861252005888',NULL,'2018-06-06 15:51:46',0,NULL,'2018-06-06 15:51:46',NULL,NULL,'5129710648430595',1,'1.31',NULL,'/oss/permission/add','添加菜单',NULL,3,'add',0,NULL),('16695107491205120',NULL,'2018-06-06 15:52:44',0,NULL,'2018-06-06 15:52:44',NULL,NULL,'5129710648430595',1,'1.32',NULL,'/oss/permission/edit','编辑菜单',NULL,3,'edit',0,NULL),('16695243126607872','','2018-06-06 15:53:17',0,'','2018-08-10 21:41:33','','','5129710648430595',1,'1.33','','/oss/permission/delByIds/**','删除菜单','',3,'delete',0,''),('25014528525733888',NULL,'2018-06-29 14:51:09',0,NULL,'2018-06-29 14:51:09',NULL,NULL,'5129710648430593',1,'1.16',NULL,'upload','上传图片',NULL,3,'upload',0,NULL),('39915540965232640',NULL,'2018-08-09 17:42:28',0,NULL,'2018-08-09 17:42:28',NULL,'monitor',NULL,0,'2.00','Main','/monitor','系统监控','ios-analytics',1,NULL,0,NULL),('39916171171991552','','2018-08-09 17:44:57',0,'','2018-08-13 17:58:05','','druid','39915540965232640',0,'2.40','sys/monitor/monitor','druid','SQL监控','md-analytics',2,'',0,'http://localhost:8888/druid/login.html'),('39918482854252544','','2018-08-09 17:54:08',0,'','2018-08-13 17:58:02','','swagger','39915540965232640',0,'2.50','sys/monitor/monitor','swagger','接口文档','md-document',2,'',0,'http://127.0.0.1:8888/swagger-ui.html'),('40238597734928384',NULL,'2018-08-10 15:06:10',0,NULL,'2018-08-10 15:06:10',NULL,'department-manage','5129710648430592',0,'1.20','sys/department-manage/departmentManage','department-manage','部门管理','md-git-branch',2,'',0,NULL),('41363147411427328','','2018-08-13 17:34:43',0,'','2018-08-20 20:05:21','','log-manage','39915540965232640',0,'2.20','sys/log-manage/logManage','log-manage','操作日志管理','md-list-box',2,'',0,''),('41363537456533504','','2018-08-13 17:36:16',0,'','2018-08-13 17:56:11','','','41363147411427328',1,'2.11','','/oss/log/delByIds/**','删除日志','',3,'delete',0,''),('41364927394353152','','2018-08-13 17:41:48',0,'','2018-08-13 17:56:17','','','41363147411427328',1,'2.12','','/oss/log/delAll','清空日志','',3,'undefined',0,''),('41370251991977984',NULL,'2018-08-13 18:02:57',0,NULL,'2018-08-13 18:02:57',NULL,'quartz-job','39915540965232640',0,'2.10','sys/quartz-manage/quartzManage','quartz-job','定时任务','md-time',2,'',0,NULL),('45235621697949696',NULL,'2018-08-24 10:02:33',0,NULL,'2018-08-24 10:02:33',NULL,'','40238597734928384',1,'1.21','','/oss/department/add','添加部门','',3,'add',0,NULL),('45235787867885568',NULL,'2018-08-24 10:03:13',0,NULL,'2018-08-24 10:03:13',NULL,'','40238597734928384',1,'1.22','','/oss/department/edit','编辑部门','',3,'edit',0,NULL),('45235939278065664',NULL,'2018-08-24 10:03:49',0,NULL,'2018-08-24 10:03:49',NULL,'','40238597734928384',1,'1.23','','/oss/department/delByIds/*','删除部门','',3,'delete',0,NULL),('45264987354042368',NULL,'2018-08-24 11:59:14',0,NULL,'2018-08-24 11:59:14',NULL,'','41370251991977984',1,'2.11','','/oss/quartzJob/add','添加定时任务','',3,'add',0,NULL),('45265487029866496',NULL,'2018-08-24 12:01:14',0,NULL,'2018-08-24 12:01:14',NULL,'','41370251991977984',1,'2.12','','/oss/quartzJob/edit','编辑定时任务','',3,'edit',0,NULL),('45265762415284224','','2018-08-24 12:02:19',0,'','2018-08-24 12:03:00','','','41370251991977984',1,'2.13','','/oss/quartzJob/pause','暂停定时任务','',3,'disable',0,''),('45265886315024384',NULL,'2018-08-24 12:02:49',0,NULL,'2018-08-24 12:02:49',NULL,'','41370251991977984',1,'2.14','','/oss/quartzJob/resume','恢复定时任务','',3,'enable',0,NULL),('45266070000373760',NULL,'2018-08-24 12:03:33',0,NULL,'2018-08-24 12:03:33',NULL,'','41370251991977984',1,'2.15','','/oss/quartzJob/delByIds/*','删除定时任务','',3,'delete',0,NULL),('5129710648430592','','2018-06-04 19:02:29',0,'','2018-08-09 15:25:01','','sys','',0,'1.00','Main','/form','系统管理','ios-settings',1,'',0,NULL),('5129710648430593','','2018-06-04 19:02:32',0,'','2018-08-13 15:15:33','','user-manage','5129710648430592',0,'1.10','sys/user-manage/userManage','user-manage','用户管理','md-person',2,'',0,''),('5129710648430594','','2018-06-04 19:02:35',0,'','2018-08-23 17:31:27','','role-manage','5129710648430592',0,'1.50','sys/role-manage/roleManage','role-manage','角色管理','md-contacts',2,'',0,''),('5129710648430595','','2018-06-04 19:02:37',0,'','2018-08-23 17:31:33','','menu-manage','5129710648430592',0,'1.60','sys/menu-manage/menuManage','menu-manage','菜单权限管理','md-menu',2,'',0,'');

/*Table structure for table `t_quartz_job` */

DROP TABLE IF EXISTS `t_quartz_job`;

CREATE TABLE `t_quartz_job` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `job_class_name` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_quartz_job` */

insert  into `t_quartz_job`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`cron_expression`,`description`,`job_class_name`,`parameter`,`status`) values ('41060689401352192','','2018-08-12 20:32:52',0,'','2018-08-12 21:33:56','0/1 * * * * ? ','无参测试 后台将每隔1秒执行输出日志','SampleJob','',-1),('41065738420621312','','2018-08-12 21:52:56',0,'','2018-08-12 21:53:08','0/1 * * * * ? ','带参测试 后台将每隔1秒执行输出日志','SampleParamJob','World',-1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围（0：所有数据；1：所在公司及以下数据；2：所在公司数据；3：所在部门及以下数据；4：所在部门   数据；8：仅本人数据；9：按明细设置）',
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `default_role` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`create_by`,`create_time`,`data_scope`,`update_by`,`update_time`,`name`,`del_flag`,`default_role`,`description`) values ('16457350655250432','','2018-06-06 00:08:00','8','682265633886208','2018-09-28 15:13:23','普通员工',0,NULL,'测试权限按钮显示'),('496138616573952','','2018-04-22 23:03:49','0','','2018-08-12 16:14:31','超级管理员',0,NULL,'超级管理员 拥有所有权限'),('496138616573953','','2018-05-02 21:40:03','3','','2018-08-16 13:33:15','管理员',0,'','普通注册用户 路过看看');

/*Table structure for table `t_role_department` */

DROP TABLE IF EXISTS `t_role_department`;

CREATE TABLE `t_role_department` (
  `id` varchar(17) NOT NULL,
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `department_id` varchar(64) NOT NULL COMMENT '部门ID',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色部门关联表';

/*Data for the table `t_role_department` */

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` varchar(17) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`permission_id`,`role_id`) values ('51797599795023872',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','5129710648430592','496138616573952'),('51797599820189696',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','5129710648430593','496138616573952'),('51797599828578304',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','15701400130424832','496138616573952'),('51797599832772608',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16678126574637056','496138616573952'),('51797599836966913',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','15701915807518720','496138616573952'),('51797599845355520',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','15708892205944832','496138616573952'),('51797599853744128',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16678447719911424','496138616573952'),('51797599862132736',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','25014528525733888','496138616573952'),('51797599870521344',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','40238597734928384','496138616573952'),('51797599878909952',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45235621697949696','496138616573952'),('51797599883104257',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45235787867885568','496138616573952'),('51797599891492864',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45235939278065664','496138616573952'),('51797599895687169',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','5129710648430594','496138616573952'),('51797599904075776',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16687383932047360','496138616573952'),('51797599916658688',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16689632049631232','496138616573952'),('51797599925047296',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16689745006432256','496138616573952'),('51797599933435904',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16689883993083904','496138616573952'),('51797599941824512',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16690313745666048','496138616573952'),('51797599950213120',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','5129710648430595','496138616573952'),('51797599954407425',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16694861252005888','496138616573952'),('51797599962796032',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16695107491205120','496138616573952'),('51797599966990337',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','16695243126607872','496138616573952'),('51797599975378944',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','39915540965232640','496138616573952'),('51797599983767552',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','41370251991977984','496138616573952'),('51797599987961857',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45264987354042368','496138616573952'),('51797599996350464',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45265487029866496','496138616573952'),('51797600004739073',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45265762415284224','496138616573952'),('51797600013127680',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45265886315024384','496138616573952'),('51797600017321985',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','45266070000373760','496138616573952'),('51797600025710592',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','41363147411427328','496138616573952'),('51797600029904897',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','41363537456533504','496138616573952'),('51797600038293504',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','41364927394353152','496138616573952'),('51797600042487809',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','39916171171991552','496138616573952'),('51797600050876416',NULL,'2018-09-11 12:37:31',0,'','2018-11-09 10:27:25','39918482854252544','496138616573952'),('51849505233965056',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','5129710648430592','496138616573953'),('51849505280102400',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','5129710648430593','496138616573953'),('51849505301073920',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','40238597734928384','496138616573953'),('51849505322045440',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','5129710648430594','496138616573953'),('51849505343016960',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','5129710648430595','496138616573953'),('51849505359794177',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','39915540965232640','496138616573953'),('51849505380765696',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','41370251991977984','496138616573953'),('51849505401737216',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','41363147411427328','496138616573953'),('51849505418514433',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','39916171171991552','496138616573953'),('51849505439485953',NULL,'2018-09-11 04:03:46',0,NULL,'2018-09-11 04:03:46','39918482854252544','496138616573953'),('54685512195117057','','2018-09-19 11:53:03',0,'','2018-09-19 16:13:24','15708892205944832','16457350655250432'),('54685512195117059','','2018-09-19 11:53:03',0,'','2018-09-19 16:13:24','5129710648430592','16457350655250432'),('54685512195117060','','2018-09-19 11:53:03',0,'','2018-09-19 16:13:24','5129710648430593','16457350655250432'),('55077461544669184','','2018-09-20 13:50:31',0,'','2018-11-09 10:27:25','55076468081823744','496138616573952'),('55077461544669185','','2018-09-20 13:50:31',0,'','2018-11-09 10:27:25','55077137979281408','496138616573952'),('55077461544669186','','2018-09-20 13:50:31',0,'','2018-11-09 10:27:25','55077334532755456','496138616573952'),('55382797631623168','','2018-09-21 10:03:49',0,'','2018-11-09 10:27:25','55382757177561088','496138616573952'),('73145743250034688','','2018-11-09 10:27:25',0,NULL,'2018-11-09 10:27:25','73145689768464384','496138616573952');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `department_id` varchar(255) DEFAULT NULL,
  `job_num` varchar(50) DEFAULT NULL COMMENT '工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`create_by`,`create_time`,`update_by`,`update_time`,`address`,`avatar`,`description`,`email`,`mobile`,`nick_name`,`password`,`sex`,`status`,`type`,`username`,`del_flag`,`department_id`,`job_num`) values ('682265633886208','','2018-05-01 16:13:51','682265633886208','2018-09-29 04:26:04','[\"510000\",\"510100\",\"510104\"]','http://p77xsahe9.bkt.clouddn.com/788eb3e78206429eb34fc7cd3e1e46f4.jpg','','','18782059038','','$2a$10$kBsk83.9lRKTbmkJ9u9rVe2Mco8UMIHo/P6f2BWzVe416CoT51b1i',1,0,1,'admin',0,'40322777781112832',NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`role_id`,`user_id`) values ('40679199995858945',NULL,'2018-08-11 20:16:58',0,NULL,'2018-08-11 20:16:58','496138616573952','682265633886208');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;