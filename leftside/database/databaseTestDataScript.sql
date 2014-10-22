-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`users`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`)
VALUES (1, 'ainstain', 'a1l3.14', 'Albert', 'Ainstain');
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`)
VALUES (2, 'freddy', 'rock', 'Frederick', 'Mercury');
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`)
VALUES (3, 'testuser', 'password', 'Justin', 'Beiber');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`fileExtensions`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`, `Extension`) VALUES (1, 'txt');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`, `Extension`) VALUES (2, 'doc');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`, `Extension`) VALUES (3, 'docx');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`, `Extension`) VALUES (4, 'xls');
INSERT INTO `java2_leftside`.`fileExtensions` (`ExtensionID`, `Extension`) VALUES (5, 'xlsx');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`files`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`)
VALUES (1, 'C:\\Data\\Poesy.txt', 'Poesy', 1);
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`)
VALUES (2, 'C:\\Data\\Thriller.dat', 'Thriller.dat', NULL);
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`)
VALUES (3, 'C:\\Data\\Call.me', 'Call.me', NULL);
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`)
VALUES (4, 'C:\\Data\\Maybe.doc', 'Maybe', 2);
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`)
VALUES (5, 'C:\\Data\\Moon1.docx', 'Moon1', 3);
