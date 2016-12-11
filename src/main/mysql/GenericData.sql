SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `GenericData`;

CREATE TABLE `GenericData` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `external_id` VARCHAR(20) NOT NULL,
  `domain` VARCHAR(20) NOT NULL,
  `meta_info` TEXT NOT NULL,
  `data` TEXT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(10) NOT NULL DEFAULT "",
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE INDEX GenericDataIndex ON GenericData(external_id,domain,status);
