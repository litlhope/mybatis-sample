CREATE TABLE `user` (
  `id` BIGINT AUTO_INCREMENT,
  `email` VARCHAR(200) NOT NULL,
  `user_name` VARCHAR(200),
  `tel` VARCHAR(50),
  `sex` VARCHAR(1),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `user`
  ADD CONSTRAINT `user_uq_email` UNIQUE(`email`);
