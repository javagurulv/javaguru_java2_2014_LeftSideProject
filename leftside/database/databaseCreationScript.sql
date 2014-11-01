SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE IF EXISTS `Java2_leftside`;
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
  `Login`    VARCHAR(32) NOT NULL,
  `Password` VARCHAR(32) NOT NULL,
  `FirstName` VARCHAR(32) NOT NULL,
  `LastName` VARCHAR(32) NOT NULL,
  `Email` VARCHAR (100) NOT NULL,
  PRIMARY KEY (`UserID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1002;

ALTER TABLE `java2_leftside`.`users`
ADD UNIQUE INDEX `ix_usersLogin` (`Login`);

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
  FOREIGN KEY (`ExtensionID`)
  REFERENCES fileExtensions (`ExtensionID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoGroups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java2_leftside`.`todoGroups` (
  `GroupID` INT(11)     NOT NULL AUTO_INCREMENT,
  `Name`    VARCHAR(40) NOT NULL,
  PRIMARY KEY (`GroupID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoStates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java2_leftside`.`todoStates` (
  `StateID` INT(11)     NOT NULL AUTO_INCREMENT,
  `State`   VARCHAR(20) NOT NULL,
  PRIMARY KEY (`StateID`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java2_leftside`.`todoItems` (
  `ItemID`      INT(11)       NOT NULL AUTO_INCREMENT,
  `StateID`     INT(11)       NOT NULL DEFAULT 0,
  `Title`     VARCHAR(100)  NOT NULL,
  `Description` VARCHAR(2000) NOT NULL,
  `DueDate`     DATE          NULL,
  PRIMARY KEY (`ItemID`),
  FOREIGN KEY (`StateID`)
  REFERENCES todoStates (`StateID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItemsToGroups`
-- ManyToOne - ManyToMany can be switched by changing Primary Key
-- example: PRIMARY KEY (`ItemID`, `GroupID`)
-- result: todoItem can be assigned to multiple Groups
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java2_leftside`.`todoItemsToGroups` (
  `ItemID`  INT(11) NOT NULL,
  `GroupID` INT(11) NOT NULL,
  PRIMARY KEY (`ItemID`),
  FOREIGN KEY (`ItemID`)
  REFERENCES todoItems (`ItemID`),
  FOREIGN KEY (`GroupID`)
  REFERENCES todoGroups (`GroupID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItemsToUsers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java2_leftside`.`todoItemsToUsers` (
  `ItemID` INT(11) NOT NULL,
  `UserID` INT(11) NOT NULL,
  PRIMARY KEY (`ItemID`),
  FOREIGN KEY (`ItemID`)
  REFERENCES todoItems (`ItemID`),
  FOREIGN KEY (`UserID`)
  REFERENCES users (`UserID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10;