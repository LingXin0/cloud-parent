ALTER TABLE `flyway-demo`.`user` ADD COLUMN `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名' AFTER `name`;

ALTER TABLE `flyway-demo`.`user` ADD COLUMN `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机' AFTER `password`;

ALTER TABLE `flyway-demo`.`user` ADD COLUMN `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱' AFTER `phone`;

ALTER TABLE `flyway-demo`.`user` ADD COLUMN `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `email`;

ALTER TABLE `flyway-demo`.`user` ADD COLUMN `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间' AFTER `create_time`;
