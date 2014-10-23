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

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoGroups`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoGroups` (`GroupID`, `Name`) VALUES (1, 'Personal');
INSERT INTO `java2_leftside`.`todoGroups` (`GroupID`, `Name`) VALUES (2, 'Family');
INSERT INTO `java2_leftside`.`todoGroups` (`GroupID`, `Name`) VALUES (3, 'Work');
INSERT INTO `java2_leftside`.`todoGroups` (`GroupID`, `Name`) VALUES (4, 'Relax');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoStates`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoStates` (`StateID`, `State`) VALUES (1, 'Created');
INSERT INTO `java2_leftside`.`todoStates` (`StateID`, `State`) VALUES (2, 'Processing');
INSERT INTO `java2_leftside`.`todoStates` (`StateID`, `State`) VALUES (3, 'Done');
INSERT INTO `java2_leftside`.`todoStates` (`StateID`, `State`) VALUES (4, 'Cancelled');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItems`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (1, 4, 'testCaption', 'testDescription', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (2, 3, 'Plant a tree', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (3, 2, 'Build a house', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (4, 1, 'Grow a son', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (5, 3, 'Balance report!!!', '', '2014-10-01');
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (6, 1, 'Meet Joe', 'St. Jose park, blue jeans, solid black t-shirt, silver case', '2014-12-31');
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (7, 1, 'Horse ride', '', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (8, 1, 'Hawaii trip', 'call Amily!', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Caption`, `Description`, `DueDate`)
VALUES (9, 1, 'Bills', 'electricity, phone and water', '2014-10-25');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItemsToGroups`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (1, 1);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (2, 1);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (3, 1);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (4, 2);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (5, 3);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (6, 3);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (7, 4);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (8, 4);
INSERT INTO `java2_leftside`.`todoItemsToGroups` (`ItemID`, `GroupID`) VALUES (9, 1);

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItemsToUsers`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (1, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (2, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (3, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (4, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (5, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (6, 1);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (7, 2);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (8, 2);
INSERT INTO `java2_leftside`.`todoItemsToUsers` (`ItemID`, `UserID`) VALUES (9, 2);
