SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Java2_leftside` DEFAULT CHARACTER SET utf8 ;
USE `Java2_leftside` ;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`users` ;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`folders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`folders` ;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`folders` (
  `FolderID` INT(11) NOT NULL AUTO_INCREMENT,
  `ParentFolderID`  INT(11) NULL,
  `FolderName` VARCHAR(40) NOT NULL,
  `Path` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`FolderID`),
  FOREIGN KEY (ParentFolderID)
  REFERENCES folders(FolderID)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`fileExtensions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`fileExtensions` ;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`fileExtensions` (
  `ExtensionID` TINYINT NOT NULL AUTO_INCREMENT,
  `Extension` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`ExtensionID`)
)
  ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`files`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `java2_leftside`.`files` ;

CREATE TABLE IF NOT EXISTS `java2_leftside`.`files` (
  `FileID` INT(11) NOT NULL AUTO_INCREMENT,
  `ParentFolderID`  INT(11)  NOT NULL,
  `FileName` VARCHAR(40) NOT NULL,
  `ExtensionID` TINYINT NOT NULL,
  PRIMARY KEY (`FileID`),
  FOREIGN KEY (ParentFolderID)
  REFERENCES folders(FolderID),
  FOREIGN KEY (ExtensionID)
  REFERENCES fileExtensions(ExtensionID)
)
ENGINE = InnoDB;