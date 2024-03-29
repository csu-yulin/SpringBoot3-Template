/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : testdb

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/03/2024 19:25:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `testdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `testdb`;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user1', 'user1@example.com', 'password1', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (2, 'user2', 'user2@example.com', 'password2', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (3, 'user3', 'user3@example.com', 'password3', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (4, 'user4', 'user4@example.com', 'password4', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (5, 'user5', 'user5@example.com', 'password5', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (6, 'user6', 'user6@example.com', 'password6', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (7, 'user7', 'user7@example.com', 'password7', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (8, 'user8', 'user8@example.com', 'password8', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (9, 'user9', 'user9@example.com', 'password9', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);
INSERT INTO `user` VALUES (10, 'user10', 'user10@example.com', 'password10', '2024-03-27 19:25:16', '2024-03-27 19:25:16', 0);

SET FOREIGN_KEY_CHECKS = 1;
