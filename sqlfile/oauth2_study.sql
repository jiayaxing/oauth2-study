/*
Navicat MySQL Data Transfer
先在数据库新建oauth2_study数据库，然后打开这个数据库执行这个文件生成两个表并插入数据
Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : oauth2_study

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-03-05 19:05:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', null, '$2a$10$5IxSbJN3ok5zICABqvUlRey7WD3q5EeeWbQ6.Zw7xq9wGhv9j0fg6', 'all', 'authorization_code,refresh_token,password', null, null, '3600', '36000', null, '1');
INSERT INTO `oauth_client_details` VALUES ('client-order', null, '$2a$10$zbIgKXtJuctnik15tkZB3ew0EnXEHsQm9ygbcVXnqHOdPZ3piD9De', 'all', 'authorization_code,refresh_token,password', null, null, '3600', '36000', null, '1');
INSERT INTO `oauth_client_details` VALUES ('user-client', null, '$2a$10$wuQJptn89D6drntLOQHtUOzNEcWCJhCkw3CLfDVfFNfy1WTW7E9Ka', 'all', 'authorization_code,refresh_token,password', null, null, '3600', '36000', null, '1');
INSERT INTO `oauth_client_details` VALUES ('web', null, '$2a$10$5IxSbJN3ok5zICABqvUlRey7WD3q5EeeWbQ6.Zw7xq9wGhv9j0fg6', 'all', 'authorization_code,refresh_token,password', null, null, '3600', '36000', null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名:rjsc用户是用其他软件平台上传的数据',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  `encode_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'liusi', '123456', 'ROLE_admin', '$2a$10$oC2uBZxC.M/AbmPaNg1mieztPqlg83TeVAUDtWSuvymQAJIWpOrkK');
INSERT INTO `user` VALUES ('2', 'jyx', '123456', 'ROLE_user', '$2a$10$c8tF1gWKzoWYR4iAXEm/penH0f/Ts6orLkECrMOIOSQIq1khR47A6');
INSERT INTO `user` VALUES ('3', 'db', '123456', 'ROLE_user', '$2a$10$XmS99fajsgA4y6W5dEuq7.g7aERPpZSFxkwQgvlLUG39pegPf7TPS');
INSERT INTO `user` VALUES ('4', 'xpc', '123456', 'ROLE_user', '$2a$10$1hsVxvGvcEZCAy9um5tXUeWhxfMFjuC4ybhvdbkTwsdw6q2jGP5b6');
INSERT INTO `user` VALUES ('5', 'hyq', '123456', 'ROLE_admin', '$2a$10$EC6VaHuiSTTsAEm5yzOyp.zioQPJXf1xgs.P2l.JwD5m0cBKfMfTm');
INSERT INTO `user` VALUES ('6', 'dulei', '123456', 'ROLE_admin', '$2a$10$4hRDMoPZbGwr6bL4s4PYl.DuP9pr7IUZv4ofmFj22umwBEv5anmpy');
