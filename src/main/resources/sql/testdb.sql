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
CREATE TABLE `user`
(
    `id`           BIGINT       NOT NULL COMMENT '用户唯一标识',
    `username`     varchar(50)  NOT NULL COMMENT '用户用户名',
    `email`        varchar(100) NOT NULL COMMENT '用户邮箱地址',
    `password`     varchar(100) NOT NULL COMMENT '用户密码（哈希）',
    `role`         varchar(50)  NULL COMMENT '用户角色（ROLE_USER | ROLE_ADMIN）',
    `created_time` timestamp    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间戳',
    `updated_time` timestamp    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户上次更新时间戳',
    `is_deleted`   tinyint(1)   NULL DEFAULT 0 COMMENT '标记用户是否已删除（0 = 未删除，1 = 已删除）',
    PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
