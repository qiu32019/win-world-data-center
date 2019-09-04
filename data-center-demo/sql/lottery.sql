CREATE TABLE `lottery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '彩票名称',
  `type` varchar(40) NOT NULL COMMENT '彩票类型',
  `issue` varchar(40) NOT NULL COMMENT '期号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `open_win_time` varchar(40) DEFAULT NULL COMMENT '开奖时间',
  `version` int(11) NOT NULL COMMENT '版本号',
  `site` int(2) NOT NULL COMMENT '源采集站点',
  `data` json COMMENT '开奖信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='彩票开奖信息';

CREATE UNIQUE INDEX lottery_tis_uindex on lottery(`type`,`issue`,`site`);