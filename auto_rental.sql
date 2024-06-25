/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : auto_rental

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 22/06/2024 18:13:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auto_brand
-- ----------------------------
DROP TABLE IF EXISTS `auto_brand`;
CREATE TABLE `auto_brand`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `mid` int NULL DEFAULT NULL COMMENT '厂商id',
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auto_brand
-- ----------------------------
INSERT INTO `auto_brand` VALUES (1, 1, '奥迪A8', 0);
INSERT INTO `auto_brand` VALUES (2, 4, '海豹', 0);
INSERT INTO `auto_brand` VALUES (4, 1, '奥迪a4', 0);
INSERT INTO `auto_brand` VALUES (5, 4, '宋plus', 0);
INSERT INTO `auto_brand` VALUES (6, 8, '小米su7', 0);
INSERT INTO `auto_brand` VALUES (7, 4, '秦', 0);

-- ----------------------------
-- Table structure for auto_info
-- ----------------------------
DROP TABLE IF EXISTS `auto_info`;
CREATE TABLE `auto_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '车辆信息id',
  `auto_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号码',
  `maker_id` int NULL DEFAULT NULL COMMENT '厂商id',
  `brand_id` int NULL DEFAULT NULL COMMENT '品牌id',
  `info_type` tinyint(1) NULL DEFAULT NULL COMMENT '车辆类型 0燃油车 1电动车 2混东车',
  `color` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `displacement` double NULL DEFAULT NULL COMMENT '汽车排量',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '排量计量单位',
  `mileage` int NULL DEFAULT NULL COMMENT '行驶里程',
  `rent` int NULL DEFAULT NULL COMMENT '日租金额',
  `registration_date` date NULL DEFAULT NULL COMMENT '上牌日期',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车辆照片',
  `deposit` int NULL DEFAULT NULL COMMENT '押金',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 0未租 1已租 2维保 3自用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `expected_num` int NULL DEFAULT NULL COMMENT '应保次数',
  `actual_num` int NULL DEFAULT NULL COMMENT '实保次数',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auto_info
-- ----------------------------
INSERT INTO `auto_info` VALUES (1, '京A9999', 4, 2, 0, '#ff0000', 3.5, 'T', 15000, 500, '2024-04-01', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/bc0429c392044fc59cd11124a89bd04d.jpg', 500, 0, '2024-06-17 21:45:07', '2024-06-17 21:45:10', 3, 1, 0);
INSERT INTO `auto_info` VALUES (2, '京b6666', 8, 6, 1, '#0848F9', 3.5, 'T', 10000, 500, '2024-06-09', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/d6c60838372c4c35adfa74953fa31def.jpg', 500, 0, '2024-06-18 16:37:44', '2024-06-18 16:37:44', 2, 0, 0);
INSERT INTO `auto_info` VALUES (3, '京b8888', 1, 1, 0, '#B95A5A', 3, 'T', 20000, 500, '2021-06-07', NULL, 300, 1, '2024-06-18 17:03:59', '2024-06-18 17:03:59', 4, 3, 0);
INSERT INTO `auto_info` VALUES (4, '京b5555', 1, 4, 0, '#A21515', 2, 'T', 60000, 200, '2018-06-12', NULL, 200, 1, '2024-06-18 17:04:46', '2024-06-18 17:04:46', 12, 8, 0);
INSERT INTO `auto_info` VALUES (5, '京b1232', 4, 5, 2, '#253BC7', 3, 'T', 2342, 234, '2021-06-23', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/13fac85cb179438390492cb0771599a9.jpg', 234, 0, '2024-06-18 17:15:50', '2024-06-18 17:15:50', 0, 0, 0);
INSERT INTO `auto_info` VALUES (6, '京b12332', 8, 6, 1, '#EFEFF3', 3, 'T', 212, 500, '2024-06-10', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/a6cd109aba1d4cea93517d19f2d85eb1.jpg', 499, 0, '2024-06-18 17:16:38', '2024-06-18 17:16:38', 0, 0, 0);

-- ----------------------------
-- Table structure for auto_maker
-- ----------------------------
DROP TABLE IF EXISTS `auto_maker`;
CREATE TABLE `auto_maker`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '厂商id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '厂商名称',
  `order_letter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '厂商字母',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 0未删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auto_maker
-- ----------------------------
INSERT INTO `auto_maker` VALUES (1, '一汽大众', 'YQDZ', 0);
INSERT INTO `auto_maker` VALUES (2, '上汽大众', 'SQDZ', 0);
INSERT INTO `auto_maker` VALUES (3, '一汽丰田', 'YQFT', 0);
INSERT INTO `auto_maker` VALUES (4, '比亚迪', 'BYD', 0);
INSERT INTO `auto_maker` VALUES (5, '长安666', 'ZA666', 0);
INSERT INTO `auto_maker` VALUES (6, '东风本田', 'DFBT', 0);
INSERT INTO `auto_maker` VALUES (7, '东风日产', 'DFRC', 0);
INSERT INTO `auto_maker` VALUES (8, '小米汽车', 'XMQC', 0);
INSERT INTO `auto_maker` VALUES (9, '理想汽车', 'LXQC', 0);
INSERT INTO `auto_maker` VALUES (10, '小米', 'XM', 1);

-- ----------------------------
-- Table structure for busi_customer
-- ----------------------------
DROP TABLE IF EXISTS `busi_customer`;
CREATE TABLE `busi_customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `age` int NULL DEFAULT NULL COMMENT '客户年龄',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '客户状态 0黑名单 1白名单',
  `id_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_customer
-- ----------------------------
INSERT INTO `busi_customer` VALUES (1, '张三', 18, 1, '13988886666', '2000-06-14', 1, '450922200006141811', '2024-06-19 10:09:13', '2024-06-19 11:41:26', 0);
INSERT INTO `busi_customer` VALUES (2, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2024-06-19 11:29:21', NULL, 1);
INSERT INTO `busi_customer` VALUES (3, '张山', 20, 1, '14566667777', '2004-11-09', 1, '452524200411090459', '2024-06-19 11:32:02', '2024-06-19 11:42:01', 0);
INSERT INTO `busi_customer` VALUES (4, '王八', 29, 1, '13500094577', '1995-12-09', 1, '458734199512095698', '2024-06-20 15:59:18', '2024-06-20 15:59:18', 0);

-- ----------------------------
-- Table structure for busi_maintain
-- ----------------------------
DROP TABLE IF EXISTS `busi_maintain`;
CREATE TABLE `busi_maintain`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '保养id',
  `auto_id` int NULL DEFAULT NULL COMMENT '车辆id',
  `auto_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号码',
  `maintain_time` datetime NULL DEFAULT NULL COMMENT '维保时间',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维保地点',
  `item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维保项目',
  `cost` int NULL DEFAULT NULL COMMENT '维保费用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_maintain
-- ----------------------------
INSERT INTO `busi_maintain` VALUES (3, 3, '京b232131', '2024-06-05 23:49:40', '北京', '换机油', 300, '2024-06-19 23:49:41', '2024-06-19 23:49:41', 0);
INSERT INTO `busi_maintain` VALUES (4, 3, '京b8888', '2024-06-19 23:51:52', '北京', '加玻璃水', 80, '2024-06-19 23:51:53', '2024-06-19 23:51:53', 0);

-- ----------------------------
-- Table structure for busi_order
-- ----------------------------
DROP TABLE IF EXISTS `busi_order`;
CREATE TABLE `busi_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单编号',
  `auto_id` int NULL DEFAULT NULL COMMENT '关联车辆id',
  `customer_id` int NULL DEFAULT NULL COMMENT '用户id',
  `rental_time` datetime NULL DEFAULT NULL COMMENT '出租时间',
  `type_id` int NULL DEFAULT NULL COMMENT '出租类型',
  `rent` int NULL DEFAULT NULL COMMENT '出租金额',
  `deposit` int NULL DEFAULT NULL COMMENT '押金',
  `mileage` int NULL DEFAULT NULL COMMENT '起租里程',
  `return_time` datetime NULL DEFAULT NULL COMMENT '归还时间',
  `return_mileage` int NULL DEFAULT NULL COMMENT '归还里程',
  `rent_payable` int NULL DEFAULT NULL COMMENT '应付租金',
  `rent_actual` int NULL DEFAULT NULL COMMENT '实付租金',
  `deposit_return` tinyint(1) NULL DEFAULT NULL COMMENT '押金返还状态 0-未返还 1-已返还',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_order
-- ----------------------------
INSERT INTO `busi_order` VALUES (2, '20240620155917', 2, 4, '2024-06-21 06:59:18', 1, 500, 500, 1000, '2024-06-21 09:54:54', 10000, 500, 75, 1, '2024-06-20 15:59:18', '2024-06-20 15:59:18', 0);
INSERT INTO `busi_order` VALUES (3, '20240620235446', 1, 1, '2024-06-18 23:54:47', 1, 500, 500, 5000, '2024-06-21 09:23:53', 10001, 500, 75, NULL, '2024-06-20 23:54:47', '2024-06-20 23:54:47', 0);
INSERT INTO `busi_order` VALUES (4, '20240621175830', 1, 3, '2024-06-21 17:58:31', 1, 500, 500, 10001, '2024-06-21 17:59:19', 15000, 500, 75, NULL, '2024-06-21 17:58:31', '2024-06-21 17:58:31', 0);

-- ----------------------------
-- Table structure for busi_rental_type
-- ----------------------------
DROP TABLE IF EXISTS `busi_rental_type`;
CREATE TABLE `busi_rental_type`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '出租类型id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出租类型名称',
  `type_discount` double NULL DEFAULT NULL COMMENT '享受折扣',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_rental_type
-- ----------------------------
INSERT INTO `busi_rental_type` VALUES (1, '短租', 1.5, '限时优惠', NULL, NULL, 0);
INSERT INTO `busi_rental_type` VALUES (2, '长租', 9, 'bbb', NULL, '2024-06-17 17:38:50', 0);
INSERT INTO `busi_rental_type` VALUES (3, '短租', 3, 'aaa', '2024-06-17 17:38:40', '2024-06-17 17:38:40', 0);

-- ----------------------------
-- Table structure for busi_violation
-- ----------------------------
DROP TABLE IF EXISTS `busi_violation`;
CREATE TABLE `busi_violation`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '违章id',
  `auto_id` int NULL DEFAULT NULL COMMENT '车辆id',
  `auto_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号码',
  `violation_time` datetime NULL DEFAULT NULL COMMENT '违章时间',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违章事由',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违章地点',
  `deduct_points` int NULL DEFAULT NULL COMMENT '扣分',
  `fine` int NULL DEFAULT NULL COMMENT '罚款',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否处理0-未处理 1-已处理',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_violation
-- ----------------------------
INSERT INTO `busi_violation` VALUES (1, 1, '京A9999', '2024-06-19 08:05:37', '闯红灯', '北京故宫东路', 3, 300, 0, '2024-06-19 16:05:59', '2024-06-19 16:05:59', 0);
INSERT INTO `busi_violation` VALUES (2, 1, '京A9999', '2024-06-19 16:22:10', '酒驾', '北京大学路', 12, 300, 0, '2024-06-19 16:22:12', '2024-06-19 16:22:12', 0);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门地址',
  `pid` int NULL DEFAULT NULL COMMENT '上级部门id',
  `parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级部门名称',
  `order_num` int NULL DEFAULT NULL COMMENT '排序号',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '宇通汽车租赁上海部', '021-88887777', '上海市徐家汇', 0, '', 1, 0);
INSERT INTO `sys_dept` VALUES (2, '业务部', '021-88887777', '上海市徐家汇', 1, '宇通汽车租赁上海部', 2, 0);
INSERT INTO `sys_dept` VALUES (3, '维保部', '021-88887777', '上海市徐家汇', 1, '宇通汽车租赁上海部', 3, 0);
INSERT INTO `sys_dept` VALUES (4, '宇通汽车租赁北京部', '010-77778888', '北京昌平', 0, '', 4, 0);
INSERT INTO `sys_dept` VALUES (5, '业务部', '010-77778888', '北京昌平', 4, '宇通汽车租赁北京部', 5, 0);
INSERT INTO `sys_dept` VALUES (6, '维保部', '010-77778888', '北京', 4, '宇通汽车租赁北京部', 6, 0);
INSERT INTO `sys_dept` VALUES (7, '测试部', '123213123', '北京', 4, '宇通汽车租赁北京部', 7, 0);
INSERT INTO `sys_dept` VALUES (8, '业务一部', '1234678', '上海徐汇区', 2, '业务部', 8, 1);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `pid` int NULL DEFAULT NULL COMMENT '父权限id',
  `parent_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父权限名称',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `route_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路由地址',
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路由名称',
  `route_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路径 ',
  `permission_type` tinyint(1) NULL DEFAULT NULL COMMENT '权限类型0-根目录 1-子目录 2-按钮级别',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标地址',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '权限管理', 0, '顶级菜单', 'sys:manager', '/system', 'system', '/system/system', 0, 'el-icon-menu', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (2, '用户管理', 1, '权限管理', 'sys:user', '/userList', 'userList', '/system/user/userList', 1, 'el-icon-s-promotion', NULL, NULL, '2024-06-17 10:33:20', NULL, 0);
INSERT INTO `sys_permission` VALUES (3, '部门管理', 1, '权限管理', 'sys:dept', '/deptList', 'deptList', '/system/dept/deptList', 1, 'el-icon-s-promotion', NULL, NULL, '2024-06-17 15:30:32', NULL, 0);
INSERT INTO `sys_permission` VALUES (4, '角色管理', 1, '权限管理', 'sys:role', '/roleList', 'roleList', '/system/role/roleList', 1, 'el-icon-s-promotion', NULL, NULL, '2024-06-17 15:30:39', NULL, 0);
INSERT INTO `sys_permission` VALUES (5, '新增', 2, '用户管理', 'sys:user:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (6, '删除', 2, '用户管理', 'sys:user:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (7, '修改', 2, '用户管理', 'sys:user:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (8, '查询', 2, '用户管理', 'sys:user:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (9, '新增', 3, '部门管理', 'sys:dept:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (10, '删除', 3, '部门管理', 'sys:dept:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (11, '修改', 3, '部门管理', 'sys:dept:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (12, '查询', 3, '部门管理', 'sys:dept:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (13, '日常业务', 0, '顶级菜单', 'busi:manager', '/busi', 'busi', '/busi/busi', 0, 'el-icon-menu', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (14, '车辆出租', 13, '日常业务', 'sys:rental', '/rentalList', 'rentalList', '/busi/rental/rentalList', 1, 'el-icon-s-promotion', NULL, NULL, '2024-06-20 09:57:06', NULL, 0);
INSERT INTO `sys_permission` VALUES (15, '数据初始', 0, '顶级菜单', 'auto:manager', '/auto', 'auto', '/auto/auto', 0, 'el-icon-menu', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (16, '车辆厂商', 15, '数据初始', 'auto:maker', '/makerList', 'makerList', '/auto/maker/makerList', 1, 'el-icon-s-promotion', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (17, '新增', 16, '车辆厂商', 'auto:maker:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (18, '删除', 16, '车辆厂商', 'auto:maker:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (19, '修改', 16, '车辆厂商', 'auto:maker:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (20, '查询', 16, '车辆厂商', 'auto:maker:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (21, '车辆品牌', 15, '数据初始', 'auto:brand', '/brandList', 'brandList', '/auto/brand/brandList', 1, 'el-icon-s-promotion', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (22, '新增', 21, '车辆品牌', 'auto:brand:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (23, '删除', 21, '车辆品牌', 'auto:brand:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (24, '修改', 21, '车辆品牌', 'auto:brand:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (25, '查询', 21, '车辆品牌', 'auto:brand:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (26, '菜单管理', 1, '权限管理', 'sys:permission', '/permissionList', 'permissionList', '/system/permission/permissionList', 1, 'el-icon-s-promotion', NULL, NULL, '2024-06-17 15:30:46', NULL, 0);
INSERT INTO `sys_permission` VALUES (27, '新增', 26, '菜单管理', 'sys:permission:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (28, '删除', 26, '菜单管理', 'sys:permission:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (29, '修改', 26, '菜单管理', 'sys:permission:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (30, '查询', 26, '菜单管理', 'sys:permission:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (31, '删除选中', 2, '用户管理', 'sys:user:deleteBath', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, NULL, '2024-06-17 10:33:42', NULL, 0);
INSERT INTO `sys_permission` VALUES (33, '订单管理', 13, '日常业务', 'busi:order', '/orderList', 'orderList', '/busi/rental/orderList', 1, 'el-icon-s-promotion', NULL, '2024-06-14 18:00:42', '2024-06-14 18:00:42', NULL, 0);
INSERT INTO `sys_permission` VALUES (34, '新增', 4, '角色管理', 'sys:role:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, '2024-06-14 22:00:13', '2024-06-14 22:00:13', NULL, 0);
INSERT INTO `sys_permission` VALUES (35, '删除', 4, '角色管理', 'sys:role:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, '2024-06-14 22:01:11', '2024-06-14 22:01:11', NULL, 0);
INSERT INTO `sys_permission` VALUES (36, '修改', 4, '角色管理', 'sys:role:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, '2024-06-14 22:01:42', '2024-06-14 22:01:42', NULL, 0);
INSERT INTO `sys_permission` VALUES (37, '查询', 4, '角色管理', 'sys:role:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, '2024-06-14 22:02:26', '2024-06-14 22:02:26', NULL, 0);
INSERT INTO `sys_permission` VALUES (38, '权限分配', 4, '角色管理', 'sys:role:permission', NULL, NULL, NULL, 2, 'el-icon-setting', NULL, '2024-06-17 10:21:42', '2024-06-17 10:21:42', NULL, 0);
INSERT INTO `sys_permission` VALUES (39, '绑定角色', 2, '用户管理', 'sys:user:bind', NULL, NULL, NULL, 2, 'el-icon-user-solid', NULL, '2024-06-17 10:34:50', '2024-06-17 10:34:50', NULL, 0);
INSERT INTO `sys_permission` VALUES (40, '出租类型', 15, '数据初始', 'busi:rentalType', '/rentalTypeList', 'rentalTypeList', '/busi/rentalType/rentalTypeList', 1, 'el-icon-s-promotion', NULL, '2024-06-17 15:30:00', '2024-06-17 15:30:00', NULL, 0);
INSERT INTO `sys_permission` VALUES (41, '新增', 40, '出租类型', 'busi:rentalType:add', NULL, NULL, NULL, 2, 'el-icon-plus', NULL, '2024-06-17 15:33:04', '2024-06-17 15:33:04', NULL, 0);
INSERT INTO `sys_permission` VALUES (42, '删除', 40, '出租类型', 'busi:rentalType:delete', NULL, NULL, NULL, 2, 'el-icon-delete', NULL, '2024-06-17 15:35:19', '2024-06-17 15:35:19', NULL, 0);
INSERT INTO `sys_permission` VALUES (43, '修改', 40, '出租类型', 'busi:rentalType:edit', NULL, NULL, NULL, 2, 'el-icon-edit', NULL, '2024-06-17 15:38:56', '2024-06-17 15:38:56', NULL, 0);
INSERT INTO `sys_permission` VALUES (44, '查询', 40, '出租类型', 'busi:rentalType:select', NULL, NULL, NULL, 2, 'el-icon-search', NULL, '2024-06-17 15:39:36', '2024-06-17 15:39:36', NULL, 0);
INSERT INTO `sys_permission` VALUES (45, '车辆信息', 15, '数据初始', 'auto:info', '/infoList', 'infoList', '/auto/info/infoList', 1, 'el-icon-s-promotion', NULL, '2024-06-18 10:23:08', '2024-06-18 10:23:08', NULL, 0);
INSERT INTO `sys_permission` VALUES (46, '客户管理', 13, '日常业务', 'busi:customer', '/customerList', 'customerList', '/busi/customer/customerList', 1, 'el-icon-s-promotion', NULL, '2024-06-19 09:37:50', '2024-06-19 09:37:50', NULL, 0);
INSERT INTO `sys_permission` VALUES (47, '违章管理', 13, '日常业务', 'busi:violation', '/violationList', 'violationList', '/busi/violation/violationList', 1, 'el-icon-s-promotion', NULL, '2024-06-19 14:43:54', '2024-06-19 14:43:54', NULL, 0);
INSERT INTO `sys_permission` VALUES (48, '车辆保养', 13, '日常业务', 'busi:maintain', '/maintainList', 'maintainList', '/busi/maintain/maintainList', 1, 'el-icon-s-promotion', NULL, '2024-06-19 22:50:05', '2024-06-19 22:50:05', NULL, 0);
INSERT INTO `sys_permission` VALUES (49, '车辆归还', 13, '日常业务', 'busi:rentalReturn', '/returnList', 'returnList', '/busi/rental/returnList', 1, 'el-icon-s-promotion', NULL, '2024-06-20 16:32:10', '2024-06-20 16:32:10', NULL, 0);
INSERT INTO `sys_permission` VALUES (50, '财务统计', 0, '根目录', 'finance:manager', '/finance', 'finance', '/finance/finance', 0, 'el-icon-menu', NULL, '2024-06-21 15:04:38', '2024-06-21 15:04:38', NULL, 0);
INSERT INTO `sys_permission` VALUES (51, '日报数据', 50, '财务统计', 'finance:countDay', '/day', 'day', '/finance/count/day', 1, 'el-icon-s-promotion', NULL, '2024-06-21 15:05:40', '2024-06-21 15:05:40', NULL, 0);
INSERT INTO `sys_permission` VALUES (52, '月报数据', 50, '财务统计', 'finance:month', '/monthList', 'monthList', '/finance/count/monthList', 1, 'el-icon-s-promotion', NULL, '2024-06-21 20:24:00', '2024-06-21 20:24:00', NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `creater_id` int NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'qqqq', '超级管理员', 1, NULL, NULL, 'aaaa', 0);
INSERT INTO `sys_role` VALUES (2, 'xiu', '普通管理员', 2, NULL, '2024-06-16 20:28:35', 'bbbb', 0);
INSERT INTO `sys_role` VALUES (3, '003', '操作员', 1, '2024-06-14 23:05:55', '2024-06-14 23:11:44', '可以操作日常业务', 0);
INSERT INTO `sys_role` VALUES (4, 'caiwu', '财务管理员', 1, '2024-06-15 17:09:16', '2024-06-15 17:09:16', '财务管理工作', 0);
INSERT INTO `sys_role` VALUES (5, 'aaaa', 'aaaa', 1, '2024-06-16 20:20:35', '2024-06-16 20:20:35', 'aaaa', 1);
INSERT INTO `sys_role` VALUES (6, 'aaa', 'aaa', 2, '2024-06-16 20:23:17', '2024-06-16 20:23:17', 'aaa', 1);
INSERT INTO `sys_role` VALUES (7, 'aa', 'aaa', 1, '2024-06-16 22:16:56', '2024-06-16 22:16:56', NULL, 1);
INSERT INTO `sys_role` VALUES (8, 'aaa', 'aaa', 1, '2024-06-16 22:18:26', '2024-06-16 22:18:26', NULL, 1);
INSERT INTO `sys_role` VALUES (9, 'aaa', 'aaa', 1, '2024-06-16 22:20:20', '2024-06-16 22:20:20', 'aaa', 1);
INSERT INTO `sys_role` VALUES (10, 'aaa', 'aaa', 1, '2024-06-16 22:22:42', '2024-06-16 22:22:42', 'aaa', 0);
INSERT INTO `sys_role` VALUES (11, 'bbb', 'bbb', 1, '2024-06-16 22:23:53', '2024-06-16 22:23:53', 'bbbb', 0);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `permission_id` int NOT NULL COMMENT '权限资源id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 29);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 33);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 37);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 41);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (1, 44);
INSERT INTO `sys_role_permission` VALUES (1, 45);
INSERT INTO `sys_role_permission` VALUES (1, 46);
INSERT INTO `sys_role_permission` VALUES (1, 47);
INSERT INTO `sys_role_permission` VALUES (1, 48);
INSERT INTO `sys_role_permission` VALUES (1, 49);
INSERT INTO `sys_role_permission` VALUES (1, 50);
INSERT INTO `sys_role_permission` VALUES (1, 51);
INSERT INTO `sys_role_permission` VALUES (1, 52);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 16);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 18);
INSERT INTO `sys_role_permission` VALUES (2, 19);
INSERT INTO `sys_role_permission` VALUES (2, 20);
INSERT INTO `sys_role_permission` VALUES (2, 26);
INSERT INTO `sys_role_permission` VALUES (2, 34);
INSERT INTO `sys_role_permission` VALUES (2, 35);
INSERT INTO `sys_role_permission` VALUES (2, 36);
INSERT INTO `sys_role_permission` VALUES (2, 37);
INSERT INTO `sys_role_permission` VALUES (3, 13);
INSERT INTO `sys_role_permission` VALUES (3, 14);
INSERT INTO `sys_role_permission` VALUES (4, 13);
INSERT INTO `sys_role_permission` VALUES (4, 33);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `is_account_non_expired` tinyint(1) NULL DEFAULT NULL COMMENT '账户是否过期',
  `is_account_non_locked` tinyint(1) NULL DEFAULT NULL COMMENT '账户是否被锁定',
  `is_credentials_non_expired` tinyint(1) NULL DEFAULT NULL COMMENT '密码是否过期',
  `is_enabled` tinyint(1) NULL DEFAULT NULL COMMENT '账户是否可用',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `dept_id` int NULL DEFAULT NULL COMMENT '用户部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户部门名称',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `is_admin` tinyint(1) NULL DEFAULT NULL COMMENT '是否管理员',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$06$0c/ndr5FZ87vzmlIVJdUd.sEtG0neP5cgvsSljCBQWxDCemLzp5cq', 1, 1, 1, 1, 'aaaa', '超级管理员', 1, '宇通汽车租赁上海部', 1, '13888866669', '2433@qq.com', 'https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191', 1, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (2, 'lisi', '$2a$06$s38Vw8g3D99w2e.4.MPl..QG.dkc.WVEzA4FB8DTkUR6B1uTM4hI6', 1, 1, 1, 1, '李四', 'lisi', 5, '业务部', 1, '13999998888', '666@163.com', 'https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg', 0, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'teacher', '$2a$06$DW9QmrRPVvDt0HTCdbgR4upgsBrFLZEc2wbaaan3TMESH5zvneq8u', 1, 1, 1, 1, '老师', 'teacher', 2, '业务部', 2, '13155556666', '4562@163.com', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/65ab96088dce47589d885fc6db70546c.png', 0, NULL, '2024-06-16 16:09:52', 0);
INSERT INTO `sys_user` VALUES (4, 'test1', '$2a$06$tOt0StYodI6/Vs7XZn/nqeJ/O1/4LltVVW6oqwRr2p0suhY6Ahn1O', 1, 1, 1, 1, '测试员', '测试', 7, '测试部', 1, '12346756', '123@odpas', 'https://big-event668.oss-cn-shenzhen.aliyuncs.com/7a4101f40ff442aca91e48ea067db6fb.png', 0, '2024-06-16 16:00:50', '2024-06-16 16:00:50', 0);
INSERT INTO `sys_user` VALUES (5, 'test2', '$2a$06$udmm4jK4Jxrw8ehhJmLvXuhZiP0iZWnKGCccUzab0ksuXH5i72.Cu', 1, 1, 1, 1, 'test2', 'test2', 3, '维保部', 2, '123123545', '1232@adas', 'https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg', 0, '2024-06-17 10:08:39', '2024-06-17 10:08:39', 0);
INSERT INTO `sys_user` VALUES (6, 'test3', '$2a$06$Xw12SSRrdmyvq9NySp4l8.UJVcyOQ4TD.sobzmD65DkmE5yqRjlyq', 1, 1, 1, 1, 'test3', 'test3', 6, '维保部', 1, '57688769', '2343222@fsfsdf', 'https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg', 0, '2024-06-17 10:09:55', '2024-06-17 10:09:55', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
