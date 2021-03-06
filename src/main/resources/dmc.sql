﻿# Host: 127.0.0.1  (Version: 5.5.29)
# Date: 2013-05-16 09:05:20
# Generator: MySQL-Front 5.3  (Build 2.30)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

DROP DATABASE IF EXISTS `dmc`;
CREATE DATABASE `dmc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dmc`;

#
# Source for table "mstb_application"
#

DROP TABLE IF EXISTS `mstb_application`;
CREATE TABLE `mstb_application` (
  `APPLICATION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APPLICATION_CODE` varchar(255) DEFAULT NULL,
  `APPLICATION_LAYOUT` int(11) DEFAULT NULL,
  `APPLICATION_NAME` varchar(255) DEFAULT NULL,
  `APPLICATION_STYLE` varchar(255) DEFAULT NULL,
  `CONTEXT` varchar(255) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `DEFAULT_PAGE` varchar(255) DEFAULT NULL,
  `DEPARTMENT_CODE` varchar(255) DEFAULT NULL,
  `DEPLOY_SERVER` varchar(255) DEFAULT NULL,
  `FAIL_NUM` int(11) DEFAULT NULL,
  `FAULT_HANDLER_EMP_NUMBER` varchar(255) DEFAULT NULL,
  `FIX_WAY` varchar(255) DEFAULT NULL,
  `IS_CHECK_CODE` int(11) DEFAULT NULL,
  `LANGUANGE` varchar(255) DEFAULT NULL,
  `ORDER_NO` int(11) DEFAULT NULL,
  `PORT` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `SESSION_TIME_OUT` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `TIME_ZONE` varchar(255) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_application"
#


#
# Source for table "mstb_application_plus"
#

DROP TABLE IF EXISTS `mstb_application_plus`;
CREATE TABLE `mstb_application_plus` (
  `APPLICATION_PLUS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `PARAMETER_CODE` varchar(255) DEFAULT NULL,
  `PARAMETER_NAME` varchar(255) DEFAULT NULL,
  `PARAMETER_VALUE` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `APPLICATION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`APPLICATION_PLUS_ID`),
  KEY `FKE30FD94F58AE500` (`APPLICATION_ID`),
  CONSTRAINT `FKE30FD94F58AE500` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_application_plus"
#


#
# Source for table "mstb_department"
#

DROP TABLE IF EXISTS `mstb_department`;
CREATE TABLE `mstb_department` (
  `DEPARTMENT_ID` bigint(20) NOT NULL,
  `BUSINESS_UNIT` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `CITY_CODE` varchar(255) DEFAULT NULL,
  `COMPANY` varchar(255) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `DEPT_FIN_CODE` varchar(255) DEFAULT NULL,
  `DEPT_NAME_CN` varchar(255) DEFAULT NULL,
  `DEPT_NAME_EN` varchar(255) DEFAULT NULL,
  `DEPT_STATUS` varchar(255) DEFAULT NULL,
  `DEPT_TYPE` varchar(255) DEFAULT NULL,
  `DEPT_UPDATE_DT` datetime DEFAULT NULL,
  `DESCR` varchar(255) DEFAULT NULL,
  `EFFDT` datetime DEFAULT NULL,
  `IS_FUNCTIONAL` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `MANAGER_ID` varchar(255) DEFAULT NULL,
  `OFFICER_CD` varchar(255) DEFAULT NULL,
  `PARENT_NODE_NAME` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) NOT NULL,
  `SAL_ADMIN_PLAN` varchar(255) DEFAULT NULL,
  `TREE_LEVEL_NUM` int(11) DEFAULT NULL,
  `UNIT_CODE` varchar(255) NOT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `XLATLONGNAME` varchar(255) DEFAULT NULL,
  `Z_DEPT_ATTR_ID` varchar(255) DEFAULT NULL,
  `Z_DEPT_FUNCTION` varchar(255) DEFAULT NULL,
  `Z_DFUNC_DESC_ENG` varchar(255) DEFAULT NULL,
  `Z_DFUNC_DESC_ZHS` varchar(255) DEFAULT NULL,
  `Z_LOCATION_DESCR` varchar(255) DEFAULT NULL,
  `Z_LOCATION_ID` varchar(255) DEFAULT NULL,
  `Z_TAX_CENTER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`),
  UNIQUE KEY `UNIT_CODE` (`UNIT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_department"
#


#
# Source for table "mstb_module"
#

DROP TABLE IF EXISTS `mstb_module`;
CREATE TABLE `mstb_module` (
  `MODULE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `ICO` varchar(255) DEFAULT NULL,
  `IS_MODULE_OR_MENU` int(11) DEFAULT NULL,
  `IS_SEED` int(11) DEFAULT NULL,
  `LEVEL_NO` int(11) DEFAULT NULL,
  `LINK` varchar(255) DEFAULT NULL,
  `MODULE_CODE` varchar(255) DEFAULT NULL,
  `MODULE_NAME` varchar(255) DEFAULT NULL,
  `OPEN_TYPE` int(11) DEFAULT NULL,
  `ORDER_NO` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `TARGET` varchar(255) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `APPLICATION_ID` bigint(20) DEFAULT NULL,
  `PARENT_MODULE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`),
  KEY `FK946A0677634397DF` (`PARENT_MODULE_ID`),
  KEY `FK946A0677F58AE500` (`APPLICATION_ID`),
  CONSTRAINT `FK946A0677F58AE500` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`),
  CONSTRAINT `FK946A0677634397DF` FOREIGN KEY (`PARENT_MODULE_ID`) REFERENCES `mstb_module` (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_module"
#


#
# Source for table "mstb_role"
#

DROP TABLE IF EXISTS `mstb_role`;
CREATE TABLE `mstb_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `ROLE_CODE` varchar(255) DEFAULT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `USER_SQL` varchar(255) DEFAULT NULL,
  `APPLICATION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK18236B41F58AE500` (`APPLICATION_ID`),
  CONSTRAINT `FK18236B41F58AE500` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_role"
#


#
# Source for table "mstb_role_right"
#

DROP TABLE IF EXISTS `mstb_role_right`;
CREATE TABLE `mstb_role_right` (
  `ROLE_RIGHT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `WORK_FLOW_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ROLE_RIGHT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_role_right"
#


#
# Source for table "mstb_role_user"
#

DROP TABLE IF EXISTS `mstb_role_user`;
CREATE TABLE `mstb_role_user` (
  `ROLE_USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `USERPROFILE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ROLE_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_role_user"
#


#
# Source for table "mstb_userprofile"
#

DROP TABLE IF EXISTS `mstb_userprofile`;
CREATE TABLE `mstb_userprofile` (
  `USERPROFILE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` int(11) DEFAULT NULL,
  `CHINESE_NAME` varchar(255) DEFAULT NULL,
  `CHINESE_POSITION` varchar(255) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `DATE_BIRTHDAY` datetime DEFAULT NULL,
  `DATE_HIRED` datetime DEFAULT NULL,
  `DATE_TERMINATED` datetime DEFAULT NULL,
  `EMAIL_ADDR` varchar(255) DEFAULT NULL,
  `EMP_ID` varchar(255) DEFAULT NULL,
  `EMP_NUMBER` varchar(255) DEFAULT NULL,
  `ENGLISH_NAME` varchar(255) DEFAULT NULL,
  `ENGLISH_POSITION` varchar(255) DEFAULT NULL,
  `FAIL_NUM` int(11) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `GRADE_CODE` varchar(255) DEFAULT NULL,
  `JOB_GRADE` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `LOGIN_IP` varchar(255) DEFAULT NULL,
  `LOGIN_NUM` int(11) DEFAULT NULL,
  `LOGIN_TIME` datetime DEFAULT NULL,
  `NATIVE_PLACE` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PROVINCIAL_ADDR` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `REPORT_LINE` varchar(255) DEFAULT NULL,
  `SESSION_ID` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `WORK_CITY` varchar(255) DEFAULT NULL,
  `WORK_PROVINCE` varchar(255) DEFAULT NULL,
  `ORG_UNIT_CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERPROFILE_ID`),
  KEY `FK6928CA336C328979` (`ORG_UNIT_CODE`),
  CONSTRAINT `FK6928CA336C328979` FOREIGN KEY (`ORG_UNIT_CODE`) REFERENCES `mstb_department` (`UNIT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_userprofile"
#


#
# Source for table "mstb_blacklist"
#

DROP TABLE IF EXISTS `mstb_blacklist`;
CREATE TABLE `mstb_blacklist` (
  `BLACKLIST_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BLACKLIST_TIME` datetime DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_USER_ID` varchar(255) DEFAULT NULL,
  `RECORD_STATE` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_USER_ID` varchar(255) DEFAULT NULL,
  `APPLICATION_ID` bigint(20) DEFAULT NULL,
  `USERPROFILE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`BLACKLIST_ID`),
  KEY `FKCA3E4D12359DA620` (`USERPROFILE_ID`),
  KEY `FKCA3E4D12F58AE500` (`APPLICATION_ID`),
  CONSTRAINT `FKCA3E4D12F58AE500` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `mstb_application` (`APPLICATION_ID`),
  CONSTRAINT `FKCA3E4D12359DA620` FOREIGN KEY (`USERPROFILE_ID`) REFERENCES `mstb_userprofile` (`USERPROFILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mstb_blacklist"
#


#
# Source for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'ROLE_ADMIN','管理员角色'),(2,'ROLE_USER','用户角色');

#
# Source for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','admin',1,'管理员'),(2,'user','user',1,'用户');

#
# Source for table "user_role"
#

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_role` (`role_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_role"
#

INSERT INTO `user_role` VALUES (1,1),(1,2),(2,2);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
