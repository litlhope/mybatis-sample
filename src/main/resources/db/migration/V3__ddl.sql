ALTER TABLE `user`
  ADD COLUMN `tel` VARCHAR(50) AFTER `user_name`,
  ADD COLUMN `sex` VARCHAR(1) AFTER `tel`;