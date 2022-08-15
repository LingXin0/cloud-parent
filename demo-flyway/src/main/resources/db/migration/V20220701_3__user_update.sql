ALTER TABLE `flyway-demo`.`user` MODIFY COLUMN `id` bigint NOT NULL COMMENT '主键' FIRST;

ALTER TABLE `flyway-demo`.`user` MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键';