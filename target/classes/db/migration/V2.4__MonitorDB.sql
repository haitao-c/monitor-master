DROP TABLE IF EXISTS app_sys;
CREATE TABLE `monitor`.`app_sys` (
`sys_id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称' ,
`category_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`sys_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=DYNAMIC
;

INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (1,'系统1',1);
INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (2,'系统2',1);
INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (3,'系统3',1);
INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (4,'系统4',2);
INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (5,'系统5',2);
INSERT INTO `monitor`.`app_sys`(`sys_id`,`name`,`category_id`) VALUES (6,'系统6',2);

DROP TABLE IF EXISTS sys_category;
CREATE TABLE `monitor`.`sys_category` (
`id`  int(11) NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=DYNAMIC
;
INSERT INTO `sys_category` (`id`, `name`) VALUES ('1', '分类系统一');
INSERT INTO `sys_category` (`id`, `name`) VALUES ('2', '分类系统二');


DROP TABLE IF EXISTS sys_device_items;
CREATE TABLE `monitor`.`sys_device_items`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `device_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备IP地址',
  `cpu_status` tinyint(1) NULL DEFAULT NULL COMMENT 'CPU状态',
  `memory_status` tinyint(1) NULL DEFAULT NULL COMMENT '内存状态',
  `network_status` tinyint(1) NULL DEFAULT NULL COMMENT '网络状态',
  `db_status` tinyint(1) NULL DEFAULT NULL COMMENT '数据库状态',
  `datetime` datetime(3) NULL DEFAULT NULL COMMENT '状态的时间戳',
  `sys_id` int(11) NULL DEFAULT NULL COMMENT '外键应用系统关联',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `device_ip`(`device_ip`) USING BTREE,
  INDEX `device_sys_id`(`sys_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (1,  '127.0.0.1', 1, 1, 1, 1, '2018-07-19 15:50:08',1);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (2,  '127.0.0.2', 1, 1, 1, 1, '2018-07-19 15:16:55',1);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (3,  '127.0.0.3', 1, 1, 1, 1, '2018-07-19 15:16:56',2);
INSERT INTO `monitor`.`sys_device_items`(`id`, `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (4,   '127.0.0.4', 1, 1, 1, 1, '2018-07-19 15:16:57',2);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (5,  '127.0.0.5', 1, 1, 1, 1, '2018-07-19 15:16:58',3);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (6,  '127.0.0.6', 1, 1, 1, 1, '2018-07-19 15:16:59',3);
INSERT INTO `monitor`.`sys_device_items`(`id`, `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (7,   '127.0.0.7', 1, 1, 1, 1, '2018-07-19 15:17:57',4);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (8,  '127.0.0.8', 1, 1, 1, 1, '2018-07-19 15:17:58',5);
INSERT INTO `monitor`.`sys_device_items`(`id`,  `device_ip`, `cpu_status`, `memory_status`, `network_status`, `db_status`, `datetime`,`sys_id`) VALUES (9,  '127.0.0.9', 1, 1, 1, 1, '2018-07-19 15:17:59',6);

DROP TABLE IF EXISTS warn;
CREATE TABLE `monitor`.`warn`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `sys_id` int(11) NULL DEFAULT NULL,
  `warn_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `confirm` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `find_time` datetime(3) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `warn_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '告警的状态类型如：cpu_status, 与sys_device_items中相对应',
  `warn_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警级别: 严重、警告',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `device_ip`(`device_ip`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS sys_business;
CREATE TABLE `monitor`.`sys_business`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_status` tinyint(1) NULL DEFAULT NULL COMMENT '系统状态',
  `deleg_status` tinyint(1) NULL DEFAULT NULL COMMENT '委托状态',
  `trade_status` tinyint(1) NULL DEFAULT NULL COMMENT '交易日期状态',
  `sys_id` int(11) NULL DEFAULT NULL COMMENT '对应的系统id',
  `scan_time` datetime(3) NULL DEFAULT NULL COMMENT '扫描时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (1, 1, 1, 1, 1, '2018-07-19 15:50:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (2, 1, 1, 1, 2, '2018-07-19 15:52:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (3, 1, 1, 1, 3, '2018-07-19 15:53:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (4, 1, 1, 1, 4, '2018-07-19 15:54:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (5, 1, 1, 1, 5, '2018-07-19 15:55:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (6, 1, 1, 1, 6, '2018-07-19 15:56:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (7, 1, 1, 1, 7, '2018-07-19 15:57:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (8, 1, 1, 1, 8, '2018-07-19 15:58:08');
INSERT INTO `monitor`.`sys_business`(`id`, `sys_status`, `deleg_status`, `trade_status`, `sys_id`, `scan_time`) VALUES (9, 1, 1, 1, 9, '2018-07-19 15:59:08');

