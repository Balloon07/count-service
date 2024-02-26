# count-config实例：单库单表
CREATE TABLE `count_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `creator` varchar(16)  NOT NULL DEFAULT '' COMMENT '创建人',
  `updater` varchar(16)  NOT NULL DEFAULT '' COMMENT '修改人',
  `count_id` varchar(64)  NOT NULL DEFAULT '' COMMENT '计次id',
  `count_type` varchar(16)  NOT NULL DEFAULT '' COMMENT '计次类型（活动、奖品、任务等）',
  `count_name` varchar(256)  NOT NULL DEFAULT '' COMMENT '计次名称',
  `dimension_type` varchar(32)  NOT NULL DEFAULT '' COMMENT '计次维度（用户id、手机号、身份证、设备号等）',
  `state` varchar(16)  NOT NULL DEFAULT '' COMMENT '计次状态',
  `start_time` datetime(3) NOT NULL COMMENT '计次周期',
  `end_time` datetime(3) NOT NULL COMMENT '计次周期',
  `count_rule` varchar(512)  NOT NULL DEFAULT '' COMMENT '计次规则（2天3次，相对/绝对周期）',
  `extend_info` varchar(1024)  NOT NULL DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_count_id` (`count_id`),
  KEY `idx_count_type` (`count_type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '计次配置表';

# count_user实例：十库十表，以dimension_id字段后两位进行分库分表
CREATE TABLE `count_record_xx` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `request_no` varchar(128)  NOT NULL DEFAULT '' COMMENT '幂等号（uuid、订单号等）',
  `dimension_type` varchar(32)  NOT NULL DEFAULT '' COMMENT '计次维度（用户id、手机号、身份证、设备号等）',
  `dimension_id` varchar(128)  NOT NULL DEFAULT '' COMMENT '计次维度值（分库分表字段）',
  `count_id` varchar(64)  NOT NULL DEFAULT '' COMMENT '计次id',
  `count_type` varchar(16)  NOT NULL DEFAULT '' COMMENT '计次类型（活动、奖品、任务等）',
  `count_num` int(10) NOT NULL DEFAULT '0' COMMENT '计次数量',
  `occur_time` datetime(3) NOT NULL COMMENT '计次时间',
  `extend_info` varchar(1024)  NOT NULL DEFAULT '' COMMENT '扩展信息（计次来源：活动id、奖品id、任务id等）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_request_no` (`request_no`),
  KEY `idx_dimension_id_count_id` (`dimension_id`, `count_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '计次流水表';

CREATE TABLE `count_cycle_xx` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `dimension_type` varchar(32)  NOT NULL DEFAULT '' COMMENT '计次维度（用户id、手机号、身份证、设备号等）',
  `dimension_id` varchar(128)  NOT NULL DEFAULT '' COMMENT '计次维度值（分库分表字段）',
  `count_id` varchar(64)  NOT NULL DEFAULT '' COMMENT '计次id',
  `count_type` varchar(16)  NOT NULL DEFAULT '' COMMENT '计次类型（活动、奖品、任务等）',
  `count_cycle_info` varchar(512)  NOT NULL DEFAULT '' COMMENT '计次周期信息',
  `extend_info` varchar(1024)  NOT NULL DEFAULT '' COMMENT '扩展信息',
  PRIMARY KEY (`id`),
  KEY `idx_dimension_id_count_id` (`dimension_id`, `count_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '计次周期表';



