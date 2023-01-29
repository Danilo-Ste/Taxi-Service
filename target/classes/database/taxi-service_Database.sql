DROP SCHEMA IF EXISTS `taxi_service`;
CREATE SCHEMA `taxi_service`;
USE `taxi_service`;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`id`)
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

USE `taxi_service`;
insert into `user`(name,surname,email,password,role)value ('Danil','Ste','dawdasa@gmail','1231ws',1)
SELECT * FROM taxi_service.user;