SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `SocialNetwork` ;
CREATE SCHEMA IF NOT EXISTS `SocialNetwork` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `SocialNetwork` ;

-- -----------------------------------------------------
-- Table `SocialNetwork`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Users` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `position` VARCHAR(45) NOT NULL ,
  `birthday` DATETIME NOT NULL ,
  `avatar` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Back_Office_Administrators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Back_Office_Administrators` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Back_Office_Administrators` (
  `id` INT NOT NULL ,
  INDEX `fk_BackOfficeAdministrators_Users1_idx` (`id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_BackOfficeAdministrators_Users1`
    FOREIGN KEY (`id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Administrators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Administrators` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Administrators` (
  `id` INT NOT NULL ,
  INDEX `fk_Administrators_Users_idx` (`id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Administrators_Users`
    FOREIGN KEY (`id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Passwords`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Passwords` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Passwords` (
  `user_id` INT NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  INDEX `fk_Passwords_Users1_idx` (`user_id` ASC) ,
  PRIMARY KEY (`user_id`) ,
  CONSTRAINT `fk_Passwords_Users1`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Groups` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Groups` (
  `group_id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `author_id` INT NOT NULL ,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`group_id`) ,
  INDEX `fk_Groups_1_idx` (`author_id` ASC) ,
  CONSTRAINT `fk_Groups_1`
    FOREIGN KEY (`author_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Users_Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Users_Groups` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Users_Groups` (
  `user_id` INT NOT NULL ,
  `group_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `group_id`) ,
  INDEX `fk_Users_Groups_1_idx` (`user_id` ASC) ,
  INDEX `fk_Users_Groups_2_idx` (`group_id` ASC) ,
  CONSTRAINT `fk_Users_Groups_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Groups_2`
    FOREIGN KEY (`group_id` )
    REFERENCES `SocialNetwork`.`Groups` (`group_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Followings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Followings` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Followings` (
  `follower_id` INT NOT NULL ,
  `following_id` INT NOT NULL ,
  PRIMARY KEY (`follower_id`, `following_id`) ,
  INDEX `fk_Friends_1_idx` (`follower_id` ASC) ,
  INDEX `fk_Friends_2_idx` (`following_id` ASC) ,
  CONSTRAINT `fk_Friends_1`
    FOREIGN KEY (`follower_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friends_2`
    FOREIGN KEY (`following_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Private_Messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Private_Messages` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Private_Messages` (
  `message_id` INT NOT NULL AUTO_INCREMENT ,
  `sent_user_id` INT NOT NULL ,
  `receiver_user_id` INT NOT NULL ,
  `message` TEXT NOT NULL ,
  `time` DATETIME NOT NULL ,
  `read` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`message_id`) ,
  INDEX `fk_PrivateMessages_1_idx` (`sent_user_id` ASC) ,
  INDEX `fk_PrivateMessages_2_idx` (`receiver_user_id` ASC) ,
  CONSTRAINT `fk_PrivateMessages_1`
    FOREIGN KEY (`sent_user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PrivateMessages_2`
    FOREIGN KEY (`receiver_user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Posts` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Posts` (
  `post_id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `post` TEXT NOT NULL ,
  `time` DATETIME NOT NULL ,
  PRIMARY KEY (`post_id`) ,
  INDEX `fk_Posts_1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Posts_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Invites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Invites` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Invites` (
  `invite_id` INT NOT NULL AUTO_INCREMENT ,
  `Invite` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`invite_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Interests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Interests` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Interests` (
  `interest_id` INT NOT NULL AUTO_INCREMENT ,
  `interest` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`interest_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Users_Interests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Users_Interests` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Users_Interests` (
  `user_id` INT NOT NULL ,
  `interest_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `interest_id`) ,
  INDEX `fk_Users_Interests_2_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Users_Interests_1`
    FOREIGN KEY (`interest_id` )
    REFERENCES `SocialNetwork`.`Interests` (`interest_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Interests_2`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Posts_Of_Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Posts_Of_Groups` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Posts_Of_Groups` (
  `group_post_id` INT NOT NULL AUTO_INCREMENT ,
  `group_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `post` TEXT NOT NULL ,
  `time` DATETIME NOT NULL ,
  PRIMARY KEY (`group_post_id`) ,
  INDEX `fk_Group_Posts_1_idx` (`group_id` ASC) ,
  INDEX `fk_Group_Posts_2_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Group_Posts_1`
    FOREIGN KEY (`group_id` )
    REFERENCES `SocialNetwork`.`Groups` (`group_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Group_Posts_2`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SocialNetwork`.`Sessions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SocialNetwork`.`Sessions` ;

CREATE  TABLE IF NOT EXISTS `SocialNetwork`.`Sessions` (
  `session_id` INT NOT NULL AUTO_INCREMENT ,
  `session` VARCHAR(45) NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`session_id`) ,
  INDEX `fk_Sessions_1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Sessions_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `SocialNetwork`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `SocialNetwork` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
