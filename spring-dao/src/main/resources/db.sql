CREATE DATABASE IF NOT EXISTS `demo_ds_master` DEFAULT CHARACTER SET utf8;
CREATE DATABASE IF NOT EXISTS `demo_ds_slave_0` DEFAULT CHARACTER SET utf8;
CREATE DATABASE IF NOT EXISTS `demo_ds_slave_1` DEFAULT CHARACTER SET utf8;

USE `demo_ds_master`;
-- -----------------------------------------------------
-- Table `order` 订单
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
	`id` BIGINT UNSIGNED NOT NULL COMMENT 'id',
	`no` VARCHAR(45) NULL UNIQUE COMMENT '编号',
	`type` TINYINT(4) NULL COMMENT '类型',
	`user_id` VARCHAR(32) NULL COMMENT '用户id',
	`amount` DECIMAL(19, 2) NULL COMMENT '金额',
	`shop_id` VARCHAR(32) NULL COMMENT '商铺id',
	`expiry_time` DATETIME NULL COMMENT '过期时间',
	`promotion_token_id` VARCHAR(32) NULL COMMENT '推广令id',
	`payment_mark` TINYINT(4) NULL COMMENT '支付标记',
	`parent_order_id` BIGINT UNSIGNED NULL COMMENT '父订单id',
	`status` TINYINT(4) NULL COMMENT '状态',
	`gmt_created` DATETIME(3) NULL COMMENT '创建时间',
	`gmt_modified` DATETIME(3) NULL COMMENT '修改时间',
	`note` VARCHAR(255) NULL COMMENT '备注',
	PRIMARY KEY (`id`),
	INDEX `idx_order_no` (`no`),
	INDEX `idx_order_user_id` (`user_id`),
	INDEX `idx_order_shop_id` (`shop_id`),
	INDEX `idx_order_promotion_token_id` (`promotion_token_id`)
)
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '订单';

USE `demo_ds_slave_0`;
-- -----------------------------------------------------
-- Table `order` 订单
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
	`id` BIGINT UNSIGNED NOT NULL COMMENT 'id',
	`no` VARCHAR(45) NULL UNIQUE COMMENT '编号',
	`type` TINYINT(4) NULL COMMENT '类型',
	`user_id` VARCHAR(32) NULL COMMENT '用户id',
	`amount` DECIMAL(19, 2) NULL COMMENT '金额',
	`shop_id` VARCHAR(32) NULL COMMENT '商铺id',
	`expiry_time` DATETIME NULL COMMENT '过期时间',
	`promotion_token_id` VARCHAR(32) NULL COMMENT '推广令id',
	`payment_mark` TINYINT(4) NULL COMMENT '支付标记',
	`parent_order_id` BIGINT UNSIGNED NULL COMMENT '父订单id',
	`status` TINYINT(4) NULL COMMENT '状态',
	`gmt_created` DATETIME(3) NULL COMMENT '创建时间',
	`gmt_modified` DATETIME(3) NULL COMMENT '修改时间',
	`note` VARCHAR(255) NULL COMMENT '备注',
	PRIMARY KEY (`id`),
	INDEX `idx_order_no` (`no`),
	INDEX `idx_order_user_id` (`user_id`),
	INDEX `idx_order_shop_id` (`shop_id`),
	INDEX `idx_order_promotion_token_id` (`promotion_token_id`)
)
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '订单';

USE `demo_ds_slave_1`;
-- -----------------------------------------------------
-- Table `order` 订单
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
	`id` BIGINT UNSIGNED NOT NULL COMMENT 'id',
	`no` VARCHAR(45) NULL UNIQUE COMMENT '编号',
	`type` TINYINT(4) NULL COMMENT '类型',
	`user_id` VARCHAR(32) NULL COMMENT '用户id',
	`amount` DECIMAL(19, 2) NULL COMMENT '金额',
	`shop_id` VARCHAR(32) NULL COMMENT '商铺id',
	`expiry_time` DATETIME NULL COMMENT '过期时间',
	`promotion_token_id` VARCHAR(32) NULL COMMENT '推广令id',
	`payment_mark` TINYINT(4) NULL COMMENT '支付标记',
	`parent_order_id` BIGINT UNSIGNED NULL COMMENT '父订单id',
	`status` TINYINT(4) NULL COMMENT '状态',
	`gmt_created` DATETIME(3) NULL COMMENT '创建时间',
	`gmt_modified` DATETIME(3) NULL COMMENT '修改时间',
	`note` VARCHAR(255) NULL COMMENT '备注',
	PRIMARY KEY (`id`),
	INDEX `idx_order_no` (`no`),
	INDEX `idx_order_user_id` (`user_id`),
	INDEX `idx_order_shop_id` (`shop_id`),
	INDEX `idx_order_promotion_token_id` (`promotion_token_id`)
)
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '订单';



