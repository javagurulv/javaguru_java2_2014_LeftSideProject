-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`users`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`users` (`UserID`,`FirstName`,`LastName`) VALUES (1, 'Albert', 'Ainstain');
INSERT INTO `java2_leftside`.`users` (`UserID`,`FirstName`,`LastName`) VALUES (2, 'Frederick', 'Mercury');
INSERT INTO `java2_leftside`.`users` (`UserID`,`FirstName`,`LastName`) VALUES (3, 'Justin', 'Beiber');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`fileExtensions`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`,`Extension`) VALUES (1, 'txt');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`,`Extension`) VALUES (2, 'doc');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`,`Extension`) VALUES (3, 'docx');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`,`Extension`) VALUES (4, 'xls');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`,`Extension`) VALUES (5, 'xlsx');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`files`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`files` (`FileID`,`ParentFolderID`,`FileName`,`ExtensionID`) VALUES (1, 2, 'Poesy', 1);
INSERT INTO `java2_leftside`.`files` (`FileID`,`ParentFolderID`,`FileName`,`ExtensionID`) VALUES (2, 2, 'Thriller.dat', NULL);
INSERT INTO `java2_leftside`.`files` (`FileID`,`ParentFolderID`,`FileName`,`ExtensionID`) VALUES (3, 2, 'Call.me', NULL);
INSERT INTO `java2_leftside`.`files` (`FileID`,`ParentFolderID`,`FileName`,`ExtensionID`) VALUES (4, 2, 'Maybe', 2);
INSERT INTO `java2_leftside`.`files` (`FileID`,`ParentFolderID`,`FileName`,`ExtensionID`) VALUES (5, 4, 'Moon1', 3);