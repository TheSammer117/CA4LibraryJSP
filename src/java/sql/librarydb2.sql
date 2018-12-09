-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2018 at 12:06 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `addressID` int(10) NOT NULL,
  `userID` int(10) NOT NULL,
  `primaryAddressLine1` varchar(255) NOT NULL,
  `primaryAddressLine2` varchar(255) DEFAULT NULL,
  `primaryTown` varchar(255) DEFAULT NULL,
  `primaryCounty` varchar(50) NOT NULL,
  `primaryEircode` varchar(9) DEFAULT NULL,
  `optAddressLine1` varchar(255) DEFAULT NULL,
  `optAddressLine2` varchar(255) DEFAULT NULL,
  `optTown` varchar(50) DEFAULT NULL,
  `optCounty` varchar(50) DEFAULT NULL,
  `optEircode` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`addressID`, `userID`, `primaryAddressLine1`, `primaryAddressLine2`, `primaryTown`, `primaryCounty`, `primaryEircode`, `optAddressLine1`, `optAddressLine2`, `optTown`, `optCounty`, `optEircode`) VALUES
(1, 4, '123. eastwood', '', 'Carlingford', 'Co. Louth', 'c15dn20', NULL, NULL, NULL, NULL, NULL),
(2, 5, '8 Cathedral pl', '', 'Limerick', 'Co. Limerick', NULL, 'Main st Newbridge', NULL, 'Newbridge', 'Co. Kildare', NULL),
(3, 6, 'Main st Charleville', 'Roadside', 'Charleville', 'Co. Cork', 'D02 X285 ', NULL, NULL, NULL, NULL, NULL),
(4, 7, 'Knock Claremorris', '', 'Knock', 'Co. Mayo', NULL, NULL, NULL, NULL, NULL, NULL),
(5, 8, 'The Boree Log ', '', 'Clondara', 'Co. Clare', NULL, NULL, NULL, NULL, NULL, NULL),
(6, 9, 'BAREFOOT BEACHHOUSE', '1A MARINE PARADE\r\n', 'KILKEE', 'CO. CLARE', 'V15 XH26', NULL, NULL, NULL, NULL, NULL),
(7, 10, '112', 'college', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
(8, 12, '11', 'dundalk', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL),
(9, 13, '45', 'dundalk', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genreID` int(5) NOT NULL,
  `genre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreID`, `genre`) VALUES
(1, 'Science fiction'),
(2, 'Satire'),
(3, 'Drama'),
(4, 'Action'),
(5, 'Adventure'),
(6, 'Romance'),
(7, 'Mystery'),
(8, 'Horror'),
(9, 'Self help'),
(10, 'Health'),
(11, 'Guide'),
(12, 'Travel'),
(13, 'Children\'s'),
(14, 'Religion'),
(15, 'Science'),
(16, 'History'),
(17, 'Math'),
(18, 'Anthology'),
(19, 'Poetry'),
(20, 'Encyclopedias'),
(21, 'Dictionaries'),
(22, 'Comics'),
(23, 'Art'),
(24, 'Cookbooks'),
(25, 'Diaries'),
(26, 'Journals'),
(27, 'Prayer books'),
(28, 'Series'),
(29, 'Trilogy'),
(30, 'Biographies'),
(31, 'Autobiographies'),
(32, 'Fantasy');

-- --------------------------------------------------------

--
-- Table structure for table `like`
--

CREATE TABLE `like` (
  `likeID` int(11) NOT NULL,
  `titleID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `count` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `like`
--

INSERT INTO `like` (`likeID`, `titleID`, `userID`, `count`) VALUES
(1, 1, 13, 1),
(2, 1, 14, 1),
(3, 1, 15, 1),
(4, 3, 13, 1),
(5, 1, 5, 1),
(6, 5, 6, 1),
(7, 5, 7, 1),
(8, 3, 6, 1),
(9, 4, 14, 1),
(10, 2, 15, 1),
(11, 1, 4, 0),
(12, 1, 5, 0),
(13, 1, 6, 0),
(14, 3, 15, 0),
(15, 3, 7, 0),
(16, 2, 8, 0),
(17, 2, 9, 0),
(18, 2, 15, 0);

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `loanID` int(10) NOT NULL,
  `userID` int(10) NOT NULL,
  `titleID` int(10) NOT NULL,
  `status` int(1) NOT NULL,
  `dayStarted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `duedate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dayEnded` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`loanID`, `userID`, `titleID`, `status`, `dayStarted`, `duedate`, `dayEnded`) VALUES
(1, 4, 1, 0, '2018-09-30 23:00:00', '0000-00-00 00:00:00', '2018-10-09 23:00:00'),
(2, 5, 4, 0, '2018-09-30 23:00:00', '0000-00-00 00:00:00', '2018-10-07 23:00:00'),
(3, 7, 7, 0, '2018-10-06 23:00:00', '0000-00-00 00:00:00', '2018-10-12 23:00:00'),
(4, 6, 8, 0, '2017-09-09 23:00:00', '0000-00-00 00:00:00', '2017-09-17 23:00:00'),
(5, 7, 7, 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(8, 9, 6, 1, '2018-10-15 23:00:00', '0000-00-00 00:00:00', '2018-10-16 23:00:00'),
(11, 12, 2, 1, '2017-11-01 00:00:00', '0000-00-00 00:00:00', '2017-11-20 00:00:00'),
(12, 15, 6, 1, '2018-10-29 08:23:45', '2018-10-29 08:02:28', '2018-10-29 08:02:28');

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `titleID` int(10) NOT NULL,
  `novelName` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `stock` int(5) NOT NULL,
  `onLoan` int(5) NOT NULL,
  `titleDescription` varchar(5000) NOT NULL,
  `isDisable` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`titleID`, `novelName`, `author`, `stock`, `onLoan`, `titleDescription`, `isDisable`) VALUES
(1, 'All the King\'s Men', 'Robert Penn Warren ', 20, 7, 'More than just a classic political novel, Warren’s tale of power and corruption in the Depression-era South is a sustained meditation on the unforeseen consequences of every human act, the vexing connectedness of all people and the possibility—it’s not much of one—of goodness in a sinful world. Willie Stark, Warren’s lightly disguised version of Huey Long, the onetime Louisiana strongman/governor, begins as a genuine tribune of the people and ends as a murderous populist demagogue. Jack Burden is his press agent, who carries out the boss’s orders, first without objection, then in the face of his own increasingly troubled conscience. And the politics? For Warren, that’s simply the arena most likely to prove that man is a fallen creature. Which it does.', 0),
(2, 'The Adventures of Augie March', 'Saul Bellow', 5, 0, 'Augie comes on stage with one of literature’s most famous opening lines. “I am an American, Chicago born, and go at things as I have taught myself, free-style, and will make the record in my own way: first to knock, first admitted.” It’s the “Call me Ishmael” of mid-20th-century American fiction. (For the record, Bellow was born in Canada.) Or it would be if Ishmael had been more like Tom Jones with a philosophical disposition. With this teeming book Bellow returned a Dickensian richness to the American novel. As he makes his way to a full brimming consciousness of himself, Augie careens through numberless occupations and countless mentors and exemplars, all the while enchanting us with the slapdash American music of his voice.', 0),
(3, 'American Pastoral', 'Philip Roth', 2, 0, 'In American Pastoral, Philip Roth gives us a novel of unqualified greatness that is an elegy for all the twentieth century\'s promises of prosperity, civic order, and domestic bliss. Roth\'s protagonist is Seymour \'Swede\' Levov - a legendary high school athlete, a devoted family man, a hard worker, the prosperous inheritor of his father\'s Newark glove factory - comes of age in thriving, triumphant post-war America. And then one day in 1968, Swede\'s beautiful American luck deserts him.\r\n\r\nFor Swede\'s adored daughter, Merry, has grown from a loving, quick-witted girl into a sullen, fanatical teenager - a teenager capable of an outlandishly savage act of political terrorism. And overnight Swede is wrenched out of the longed-for American pastoral and into the indigenous American berserk. Compulsively readable, propelled by sorrow, rage, and a deep compassion for its characters, this is Roth\'s masterpiece.', 0),
(4, 'An American Tragedy', 'Theodore Dreiser', 2, 0, 'On one level, An American Tragedy is the story of the corruption and destruction of one man, Clyde Griffiths, who forfeits his life in desperate pursuit of success. On a deeper, more profound level, the novel represents a massive portrayal of the society whose values both shape Clyde\'s tawdry ambitions and seal his fate: It is an unsurpassed depiction of the harsh realities of American life and of the dark side of the American Dream. Extraordinary in scope and power, vivid in its sense of wholesale human waste, unceasing in its rich compassion, An American Tragedy stands as Theodore Dreiser\'s supreme achievement.\r\n\r\nBased on an actual crime case, An American Tragedy was the inspiration for the film A Place in the Sun, winner of six Academy Awards, starring Elizabeth Taylor and Montgomery Clift.', 0),
(6, 'Appointment in Samarra ', 'John O\'Hara', 62, 0, 'O’Hara did for fictional Gibbsville, Pennsylvania what Faulkner did for Yoknapatawpha County, Mississippi: surveyed its social life and drew its psychic outlines, but he did it in utterly worldly terms, without Faulkner’s taste for mythic inference or the basso profundo of his prose. Julian English is a man who squanders what fate gave him. He lives on the right side of the tracks, with a country club membership and a wife who loves him. His decline and fall, over the course of just 72 hours around Christmas, is a matter of too much spending, too much liquor, and a couple of reckless gestures. That his calamity is petty and preventable only makes it more powerful. In Faulkner, the tragedies all seem to be taking place on Olympus, even when they’re happening among the low-lifes. In O’Hara, they could be happening to you.', 0),
(7, 'Are You There God? It’s Me, Margaret', 'Judy Blume', 15, 0, 'Margaret Simon, almost twelve, likes long hair, tuna fish, the smell of rain, and things that are pink. She’s just moved from New York City to Farbook, New Jersey, and is anxious to fit in with her new friends—Nancy, Gretchen, and Janie. When they form a secret club to talk about private subjects like boys, bras, and getting their first periods, Margaret is happy to belong.\r\n\r\nBut none of them can believe Margaret doesn’t have religion, and that she isn’t going to the Y or the Jewish Community Center. What they don’t know is Margaret has her own very special relationship with God. She can talk to God about everything—family, friends, even Moose Freed, her secret crush.\r\n\r\nMargaret is funny and real, and her thoughts and feelings are oh-so-relatable—you’ll feel like she’s talking right to you, sharing her secrets with a friend.', 0),
(8, 'The Assistant', 'Camille Perri', 25, 0, 'Tina Fontana is the hapless but brazen thirty-year-old executive assistant to Robert Barlow, the all-powerful and commanding CEO of Titan Corp., a multinational media conglomerate. She’s excellent at her job and beloved by her famous boss—but after six years of making his reservations for restaurants she’d never get into on her own and pouring his drinks from bottles that cost more than her rent, she’s bored, broke, and just a bit over it all.\r\n \r\nWhen a technical error with Robert’s travel-and-expenses report presents Tina with the opportunity to pay off the entire balance of her student loan debt with what would essentially be pocket change for her boss, she struggles with the decision: She’s always played by the rules. But it’s such a relatively small amount of money for the Titan Corporation—and for her it would be a life-changer . . .\r\n \r\nThe Assistants speaks directly to a new generation of women who feel stuck and unable to get ahead playing by the rules. It will appeal to all of those who have ever asked themselves, “How is it that after all these years, we are still assistants?”', 0);

-- --------------------------------------------------------

--
-- Table structure for table `titlegenre`
--

CREATE TABLE `titlegenre` (
  `genreID` int(10) NOT NULL,
  `titleID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `titlegenre`
--

INSERT INTO `titlegenre` (`genreID`, `titleID`) VALUES
(1, 1),
(3, 6),
(4, 8),
(5, 2),
(7, 3),
(8, 4),
(14, 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `isAdmin` int(1) NOT NULL,
  `dateJoined` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `email`, `password`, `firstName`, `lastName`, `isAdmin`, `dateJoined`, `active`) VALUES
(4, 'fakeEmail@fakingEmails.com', 'password', 'Fake', 'News', 0, '2018-10-22 09:34:31', 0),
(5, 'jamesWord@gmail.com', 'passingWord', 'James', 'Wood', 0, '2018-10-22 09:19:38', 1),
(6, 'Frank13@live.com', 'FrankWest92', 'Frank', 'West', 0, '2018-10-22 09:19:38', 1),
(7, 'Test@test.com', 'testing', 'Sean', 'Tester', 0, '2018-10-22 09:34:31', 0),
(8, 'tester@tester.com', '123', '123', '123', 0, '2018-10-22 09:19:38', 1),
(9, 'ayesha@test.com', 'ayesha', 'ayesha', 'khan', 0, '2018-10-22 09:19:38', 1),
(10, 'admin@admin.com', 'admin', 'Ayesha', 'Khan', 1, '2018-10-22 09:19:38', 1),
(12, 'sean@gmail.com', 'sean', 'sean', 'ohora', 1, '2018-10-22 09:19:38', 1),
(13, 'sami@admin', 'sami', 'sami', 'mahmoud', 1, '2018-10-22 09:34:31', 1),
(14, 'jump@into.water', 'pass', 'sa', 'mi', 0, '2018-10-29 09:04:42', 0),
(15, 'I@want.books', 'pass', 'sa', 'mi', 0, '2018-10-29 07:41:44', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`addressID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genreID`);

--
-- Indexes for table `like`
--
ALTER TABLE `like`
  ADD PRIMARY KEY (`likeID`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`loanID`,`userID`,`titleID`),
  ADD KEY `fk_B_TitleID` (`titleID`),
  ADD KEY `fk_B_UserID` (`userID`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`titleID`);

--
-- Indexes for table `titlegenre`
--
ALTER TABLE `titlegenre`
  ADD PRIMARY KEY (`genreID`,`titleID`),
  ADD KEY `fk_TG_TitleID` (`titleID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `addressID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genreID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `like`
--
ALTER TABLE `like`
  MODIFY `likeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loanID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `titleID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `fk_B_TitleID` FOREIGN KEY (`titleID`) REFERENCES `title` (`titleID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_B_UserID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `titlegenre`
--
ALTER TABLE `titlegenre`
  ADD CONSTRAINT `fk_TG_GenreID` FOREIGN KEY (`genreID`) REFERENCES `genre` (`genreID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_TG_TitleID` FOREIGN KEY (`titleID`) REFERENCES `title` (`titleID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
