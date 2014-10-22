SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE `Java2_leftside`;
CREATE SCHEMA IF NOT EXISTS `Java2_leftside`
  DEFAULT CHARACTER SET utf8;
SET FOREIGN_KEY_CHECKS = 1;
USE `Java2_leftside`;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`users`;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`users` (
  `UserID`   INT(11)  NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1002;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`fileExtensions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`fileExtensions`;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`fileExtensions` (
  `ExtensionID` TINYINT    NOT NULL AUTO_INCREMENT,
  `Extension`   VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ExtensionID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`files`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`files`;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`files` (
  `FileID`      INT(11)      NOT NULL AUTO_INCREMENT,
  `Path`        VARCHAR(500) NOT NULL,
  `FileName`    VARCHAR(40)  NOT NULL,
  `ExtensionID` TINYINT      NULL,
  PRIMARY KEY (`FileID`),
  FOREIGN KEY (ExtensionID)
  REFERENCES fileExtensions (ExtensionID)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

