-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`users`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`, `Email`)
VALUES (1, 'ainstain', 'a1l3.14', 'Albert', 'Ainstain', 'ainstain.a@mailinator.com');
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`, `Email`)
VALUES (2, 'freddy', 'rock', 'Frederick', 'Mercury', 'mad_freddy@mailinator.com');
INSERT INTO `java2_leftside`.`users` (`UserID`, `Login`, `Password`, `FirstName`, `LastName`, `Email`)
VALUES (3, 'testuser', 'password', 'Justin', 'Beiber', 'imweird@mailinator.com');

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
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (1, 4, 'testTitle', 'testDescription', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (2, 3, 'Plant a tree', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (3, 2, 'Build a house', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (4, 1, 'Grow a son', 'as part of Great Man''s plan', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (5, 3, 'Balance report!!!', '', '2014-10-01');
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (6, 1, 'Meet Joe', 'St. Jose park, blue jeans, solid black t-shirt, silver case', '2014-12-31');
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (7, 1, 'Horse ride', '', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (8, 1, 'Hawaii trip', 'call Amily!', NULL);
INSERT INTO `java2_leftside`.`todoItems` (`ItemID`, `StateID`, `Title`, `Description`, `DueDate`)
VALUES (9, 1, 'Bills', 'electricity, phone and water', '2014-10-25');

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`todoItemComment`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (1, 1, 1, NULL, '2013-11-01 01:00:00', 'testCommentTitle', 'testCommentMessage');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (2, 2, 1, 1, '2013-11-01 01:01:00', 'testCommentResponse', 'blahblahblah');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (3, 1, 1, 1, '2013-11-01 01:02:00', 'boo', 'foobar');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (4, 1, 1, 2, '2013-11-01 01:03:00', 'tar', 'gzip');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (5, 1, 1, 1, '2013-11-01 01:05:00', 'add some fish',
        '"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (6, 2, 8, NULL, '2013-11-01 00:00:00', 'you might need that',
        'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...');
INSERT INTO `java2_leftside`.`todoItemComments` (`CommentID`, `UserID`, `ItemID`, `ReplyToID`, `Date`, `Title`, `Message`)
VALUES (7, 1, 1, NULL, '2013-11-01 01:10:00', 'fantasy', '... is over');

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
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`,  `UploadDate`)
VALUES (1, 'C:\\Data\\Poesy.txt', 'Poesy', 1, '2013-11-01 14:26:00');
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`,  `UploadDate`)
VALUES (2, 'C:\\Data\\Thriller.dat', 'Thriller.dat', NULL, '2013-10-01 10:00:35');
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`,  `UploadDate`)
VALUES (3, 'C:\\Data\\Call.me', 'Call.me', NULL, '2013-11-05 12:21:00');
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`, `UploadDate`)
VALUES (4, 'C:\\Data\\Maybe.doc', 'Maybe', 2, '2013-10-02 12:10:00');
INSERT INTO `java2_leftside`.`files` (`FileID`, `Path`, `FileName`, `ExtensionID`,  `UploadDate`)
VALUES (5, 'C:\\Data\\Moon1.docx', 'Moon1', 3, '2013-10-01 12:00:00');

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

-- -----------------------------------------------------
-- Table `Java2_LeftSide`.`filesTotodoItems`
-- -----------------------------------------------------
INSERT INTO `java2_leftside`.`filesTotodoItems` (`FileID`, `ItemID`) VALUES (1, 6);
INSERT INTO `java2_leftside`.`filesTotodoItems` (`FileID`, `ItemID`) VALUES (2, 5);
INSERT INTO `java2_leftside`.`filesTotodoItems` (`FileID`, `ItemID`) VALUES (3, 8);
INSERT INTO `java2_leftside`.`filesTotodoItems` (`FileID`, `ItemID`) VALUES (4, 6);
INSERT INTO `java2_leftside`.`filesTotodoItems` (`FileID`, `ItemID`) VALUES (5, 6);

