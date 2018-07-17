/*
Navicat MySQL Data Transfer

Source Server         : Mybatis
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : schoolshop

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2018-07-13 07:38:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_area`
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(6) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `area_priority` int(6) NOT NULL DEFAULT '0',
  `area_create_time` datetime NOT NULL,
  `area_edit_time` datetime NOT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `area_name` (`area_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '南苑', '8', '2018-07-05 11:42:01', '2018-07-05 11:42:05');
INSERT INTO `tb_area` VALUES ('2', '雅苑', '7', '2018-07-06 16:59:58', '2018-07-06 17:00:00');

-- ----------------------------
-- Table structure for `tb_headline`
-- ----------------------------
DROP TABLE IF EXISTS `tb_headline`;
CREATE TABLE `tb_headline` (
  `head_id` int(6) NOT NULL AUTO_INCREMENT,
  `head_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_img` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_link` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_create_time` datetime DEFAULT NULL,
  `head_edit_time` date DEFAULT NULL,
  `head_enable_status` int(2) DEFAULT '0',
  `head_priority` int(2) DEFAULT NULL,
  PRIMARY KEY (`head_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_headline
-- ----------------------------
INSERT INTO `tb_headline` VALUES ('1', '头条一', '/shop/images/1.jpg', null, '2018-07-10 10:41:44', '2018-07-10', '1', '100');
INSERT INTO `tb_headline` VALUES ('2', '头条二', '/shop/images/2.jpg', null, '2018-07-10 10:42:28', '2018-07-10', '1', '50');
INSERT INTO `tb_headline` VALUES ('3', '头条三', '/shop/images/3.jpg', null, '2018-07-10 11:21:09', '2018-07-10', '1', '50');
INSERT INTO `tb_headline` VALUES ('4', '头条四2', '/shop/images/1.jpg', null, null, null, '1', null);

-- ----------------------------
-- Table structure for `tb_localauth`
-- ----------------------------
DROP TABLE IF EXISTS `tb_localauth`;
CREATE TABLE `tb_localauth` (
  `local_id` int(6) NOT NULL AUTO_INCREMENT,
  `local_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `local_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `local_create_time` datetime NOT NULL,
  `local_edit_time` datetime NOT NULL,
  `user_id` int(6) NOT NULL,
  PRIMARY KEY (`local_id`),
  KEY `tb_localauth_user_id` (`user_id`),
  CONSTRAINT `tb_localauth_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_localauth
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(6) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_desc` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_img_address` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_normal_price` double DEFAULT NULL,
  `product_promotion_price` double DEFAULT NULL,
  `product_priority` int(2) DEFAULT NULL,
  `product_create_time` datetime DEFAULT NULL,
  `product_edit_time` datetime DEFAULT NULL,
  `product_enable_status` int(2) DEFAULT NULL,
  `shop_id` int(6) DEFAULT NULL,
  `productcategory_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `shop` (`shop_id`),
  KEY `category` (`productcategory_id`),
  CONSTRAINT `category` FOREIGN KEY (`productcategory_id`) REFERENCES `tb_productcategory` (`product_category_id`),
  CONSTRAINT `shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('6', '2018华为新款', '质量保障，价格实惠', '/shop/images/1.jpg', '1500', '500', '5', '2018-07-11 07:52:41', '2018-07-11 07:52:41', '1', '12', '5');
INSERT INTO `tb_product` VALUES ('7', '红豆酸皮奶', '好吃。再来一杯', '/shop/images/1.jpg', '8', '5', '8', '2018-07-11 07:55:18', '2018-07-11 07:55:18', '1', '11', '7');
INSERT INTO `tb_product` VALUES ('8', '西瓜汁', '西瓜汁', '/shop/images/1.jpg', '4', '4', '4', '2018-07-11 08:51:19', '2018-07-11 08:51:19', '1', '11', '8');
INSERT INTO `tb_product` VALUES ('9', '芒果汁', '芒果汁好吃', '/shop/images/1.jpg', '7', '6', '6', '2018-07-11 09:20:29', '2018-07-11 09:20:29', '1', '11', '8');
INSERT INTO `tb_product` VALUES ('10', '草莓汁', '草莓汁我就爱', '/shop/images/1.jpg', '6', '6', '6', '2018-07-11 09:21:25', '2018-07-11 09:21:25', '1', '11', '8');

-- ----------------------------
-- Table structure for `tb_productcategory`
-- ----------------------------
DROP TABLE IF EXISTS `tb_productcategory`;
CREATE TABLE `tb_productcategory` (
  `product_category_id` int(6) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_category_priority` int(2) DEFAULT NULL,
  `product_category_create_time` datetime DEFAULT NULL,
  `product_category_shop_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`product_category_id`),
  KEY `product_shop_ui` (`product_category_shop_id`),
  CONSTRAINT `product_shop_ui` FOREIGN KEY (`product_category_shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_productcategory
-- ----------------------------
INSERT INTO `tb_productcategory` VALUES ('5', '手机类', '5', '2018-07-11 07:50:16', '12');
INSERT INTO `tb_productcategory` VALUES ('6', '笔电类', '5', '2018-07-11 07:51:26', '12');
INSERT INTO `tb_productcategory` VALUES ('7', '酸奶类', '6', '2018-07-11 07:53:37', '11');
INSERT INTO `tb_productcategory` VALUES ('8', '水果类', '4', '2018-07-11 07:54:06', '11');

-- ----------------------------
-- Table structure for `tb_productimg`
-- ----------------------------
DROP TABLE IF EXISTS `tb_productimg`;
CREATE TABLE `tb_productimg` (
  `product_img_id` int(6) NOT NULL AUTO_INCREMENT,
  `product_img_address` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_img_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_img_create_time` datetime DEFAULT NULL,
  `product_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_productimg
-- ----------------------------
INSERT INTO `tb_productimg` VALUES ('5', '/shop/images/1.jpg', null, '2018-07-11 07:52:42', '6');
INSERT INTO `tb_productimg` VALUES ('6', '/shop/images/1.jpg', null, '2018-07-11 07:55:18', '7');
INSERT INTO `tb_productimg` VALUES ('7', '/shop/images/1.jpg', null, '2018-07-11 08:51:19', '8');
INSERT INTO `tb_productimg` VALUES ('8', '/shop/images/3.jpg', null, '2018-07-11 09:20:29', '9');
INSERT INTO `tb_productimg` VALUES ('9', '/shop/images/2.jpg', null, '2018-07-11 09:21:25', '10');

-- ----------------------------
-- Table structure for `tb_shop`
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(6) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_create_time` datetime DEFAULT NULL,
  `shop_edit_time` datetime DEFAULT NULL,
  `shop_advice` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_address` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area_id` int(6) DEFAULT NULL,
  `shopcategory_id` int(6) DEFAULT NULL,
  `user_id` int(6) DEFAULT NULL,
  `shop_priority` int(2) DEFAULT NULL,
  `shop_enable_status` int(2) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `tb_shop_area` (`area_id`),
  KEY `tb_shop_shopcategory` (`shopcategory_id`),
  KEY `tb_shop_user` (`user_id`),
  CONSTRAINT `tb_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `tb_shop_shopcategory` FOREIGN KEY (`shopcategory_id`) REFERENCES `tb_shopcategory` (`shop_category_id`),
  CONSTRAINT `tb_shop_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('11', '爽爽糖水', '陈鹏程店家人好', '/shop/images/1.jpg', '2018-07-11 06:49:04', '2018-07-11 06:49:04', null, '西湖乐园99号', '15918927425', '1', '5', '1', null, '1');
INSERT INTO `tb_shop` VALUES ('12', '广科手机平台', '陈宇巍人好开心', '/shop/images/2.jpg', '2018-07-11 06:51:35', '2018-07-11 06:51:35', null, '广东科技学院南苑宿舍楼', '1591927425', '1', '6', '1', null, '1');
INSERT INTO `tb_shop` VALUES ('13', '121212', '12', '/shop/images/3.jpg', '2018-07-11 08:15:54', '2018-07-11 08:15:54', null, '121', '1212', '1', '6', '1', null, '1');
INSERT INTO `tb_shop` VALUES ('14', '111', '11', '/shop/images/1.jpg', '2018-07-11 08:36:54', '2018-07-11 08:36:54', null, '11', '111', '1', '6', null, null, '0');

-- ----------------------------
-- Table structure for `tb_shopcategory`
-- ----------------------------
DROP TABLE IF EXISTS `tb_shopcategory`;
CREATE TABLE `tb_shopcategory` (
  `shop_category_id` int(6) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_category_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_category_img` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_category_create_time` datetime DEFAULT NULL,
  `shop_category_edit_time` datetime DEFAULT NULL,
  `shop_category_priority` int(2) DEFAULT NULL,
  `parent_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `tb_shopcategory_id` (`parent_id`),
  CONSTRAINT `tb_shopcategory_id` FOREIGN KEY (`parent_id`) REFERENCES `tb_shopcategory` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_shopcategory
-- ----------------------------
INSERT INTO `tb_shopcategory` VALUES ('5', '糖水类', '夏季必备', '/shop/images/1.jpg', '2018-07-11 06:45:19', '2018-07-11 06:45:22', '5', '7');
INSERT INTO `tb_shopcategory` VALUES ('6', '饮料类', '夏天必必备', '/shop/images/2.jpg', '2018-07-11 06:45:53', '2018-07-11 06:45:56', '5', '7');
INSERT INTO `tb_shopcategory` VALUES ('7', '饮品类', '一生必备', '/shop/images/3.jpg', '2018-07-11 06:46:37', '2018-07-11 06:46:40', '5', null);
INSERT INTO `tb_shopcategory` VALUES ('8', '二手市场', '普通家庭的首选', '/shop/images/1.jpg', '2018-07-11 06:47:24', '2018-07-11 06:47:27', '5', null);
INSERT INTO `tb_shopcategory` VALUES ('9', '二手手机', '高中生的必备', '/shop/images/1.jpg', '2018-07-11 06:47:54', '2018-07-11 06:47:57', '5', '8');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(6) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `user_gender` char(1) COLLATE utf8_unicode_ci DEFAULT '1',
  `user_type` int(2) DEFAULT '1',
  `user_enable_status` int(2) DEFAULT '0',
  `user_create_time` datetime DEFAULT NULL,
  `user_edit_time` datetime DEFAULT NULL,
  `user_email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_img` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'hgx', '1', '1', '0', '2018-07-05 15:49:56', '2018-07-05 15:49:58', 'hgx@hgx.com', 'hgx');
INSERT INTO `tb_user` VALUES ('2', 'hgx2323', '1', '1', '0', '2018-07-06 10:23:20', null, 'hgx@gui.com', null);

-- ----------------------------
-- Table structure for `tb_wechatauth`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechatauth`;
CREATE TABLE `tb_wechatauth` (
  `we_chat_id` int(6) NOT NULL AUTO_INCREMENT,
  `we_chat_open_id` int(12) NOT NULL,
  `we_chat_create_time` datetime NOT NULL,
  `user_id` int(6) NOT NULL,
  PRIMARY KEY (`we_chat_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_wechatauth
-- ----------------------------
