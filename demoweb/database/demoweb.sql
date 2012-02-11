SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `demoweb` ;
CREATE SCHEMA IF NOT EXISTS `demoweb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `demoweb` ;

-- -----------------------------------------------------
-- Table `profession`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `profession` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `state`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `state` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `first_name` VARCHAR(75) NULL ,
  `last_name` VARCHAR(75) NULL ,
  `dob` DATE NULL ,
  `email_id` VARCHAR(250) NULL ,
  `address_line` VARCHAR(80) NULL ,
  `profession_id` INT NULL ,
  `state_id` INT NULL ,
  `status_id` INT NOT NULL DEFAULT 0 ,
  `password_fail_count` INT NULL ,
  `activation_code` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  INDEX `fk_user_profession_id` (`profession_id` ASC) ,
  INDEX `fk_user_state_id` (`state_id` ASC) ,
  INDEX `fk_user_status_id` (`status_id` ASC) ,
  CONSTRAINT `fk_user_profession_id`
    FOREIGN KEY (`profession_id` )
    REFERENCES `profession` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_state_id`
    FOREIGN KEY (`state_id` )
    REFERENCES `state` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_status_id`
    FOREIGN KEY (`status_id` )
    REFERENCES `status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user_role` (
  `role_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`role_id`, `user_id`) ,
  INDEX `fk_user_role_user_id` (`user_id` ASC) ,
  INDEX `fk_user_role_role_id` (`role_id` ASC) ,
  CONSTRAINT `fk_user_role_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES `role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `email_template`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `email_template` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `template` MEDIUMTEXT  NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `profession`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `profession` (`id`, `name`) VALUES (NULL, 'Doctor');
INSERT INTO `profession` (`id`, `name`) VALUES (NULL, 'Software Professional');
INSERT INTO `profession` (`id`, `name`) VALUES (NULL, 'Business ');
INSERT INTO `profession` (`id`, `name`) VALUES (NULL, 'Student');

COMMIT;

-- -----------------------------------------------------
-- Data for table `state`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `state` (`id`, `name`) VALUES (1, 'Andhra Pradesh');
INSERT INTO `state` (`id`, `name`) VALUES (2, 'Tamil Nadu');
INSERT INTO `state` (`id`, `name`) VALUES (3, 'Karnataka');
INSERT INTO `state` (`id`, `name`) VALUES (4, 'Delhi');
INSERT INTO `state` (`id`, `name`) VALUES (5, 'Orissa');

COMMIT;

-- -----------------------------------------------------
-- Data for table `status`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `status` (`id`, `name`) VALUES (1, 'Pending');
INSERT INTO `status` (`id`, `name`) VALUES (2, 'Active');
INSERT INTO `status` (`id`, `name`) VALUES (3, 'Locked');

COMMIT;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `dob`, `email_id`, `address_line`, `profession_id`, `state_id`, `status_id`, `password_fail_count`, `activation_code`) VALUES (1, 'admin', '5a105e8b9d40e1329780d62ea2265d8a', 'Harinath', 'Mallepally', '01/01/1979', 'hari@harinath.in', 'hyderbad', NULL, 1, 2, 0, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `role` (`id`, `name`, `description`) VALUES (NULL, 'admin', 'admin user account');
INSERT INTO `role` (`id`, `name`, `description`) VALUES (NULL, 'user', 'normal user');

COMMIT;

-- -----------------------------------------------------
-- Data for table `email_template`
-- -----------------------------------------------------
START TRANSACTION;
USE `demoweb`;
INSERT INTO `email_template` (`id`, `name`, `template`) VALUES (1, 'registration', 'Dear $$first_name$$, Welcome to demo web application.  Regards, Harinath, Administrator');

COMMIT;
