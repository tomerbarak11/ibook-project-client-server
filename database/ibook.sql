-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2017 at 10:34 PM
-- Server version: 5.7.12-log
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ibook`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--
DROP DATABASE IF EXISTS iBook;
CREATE DATABASE iBook;
USE iBook;
CREATE TABLE `book` (
  `bookID` int(11) NOT NULL,
  `author` varchar(45) NOT NULL,
  `title` varchar(50) NOT NULL,
  `plot` text NOT NULL,
  `price` varchar(45) NOT NULL,
  `Table_of_Contents` text NOT NULL,
  `numberOfSearches` varchar(45) DEFAULT '0',
  `status` varchar(45) DEFAULT '1',
  `author2` varchar(45) DEFAULT NULL,
  `url_pdf` varchar(300) NOT NULL,
  `url_doc` varchar(300) NOT NULL,
  `language` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookID`, `author`, `title`, `plot`, `price`, `Table_of_Contents`, `numberOfSearches`, `status`, `author2`, `url_pdf`, `url_doc`, `language`) VALUES
(1, 'Suzanne Collins', 'THE HUNGER GAMES', 'The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The \'tributes\' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory.\r\n\r\nWhen 16-year-old Katniss\'s young sister, Prim, is selected as District 12\'s female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.', '11', 'Introduction\r\nThemes\r\nQuotes\r\nPower\r\nPower Quotes\r\nVersions of Reality\r\nVersions of Reality Quotes\r\nIdentity\r\nIdentity Quotes\r\nSociety and Class\r\nSociety and Class Quotes\r\nLove\r\nLove Quotes\r\nStrength and Skill\r\nStrength and Skill Quotes\r\nAppearances\r\nAppearances Quotes\r\nPolitics\r\nPolitics Quotes\r\nCompetition\r\nCompetition Quotes\r\nSacrifice\r\nSacrifice Quotes\r\nSummary\r\nChapter 1\r\nChapter 2\r\nChapter 3\r\nChapter 4\r\nChapter 5\r\nChapter 6\r\nChapter 7\r\nChapter 8\r\nChapter 9\r\nChapter 10\r\nChapter 11\r\nChapter 12\r\nChapter 13\r\nChapter 14\r\nChapter 15\r\nChapter 16\r\nChapter 17\r\nChapter 18\r\nChapter 19\r\nChapter 20\r\nChapter 21\r\nChapter 22\r\nChapter 23\r\nChapter 24\r\nChapter 25\r\nChapter 26\r\nChapter 27\r\nPlot Overview', '1', '1', '', 'https://www.dropbox.com/s/hr6do6rbon73b8l/THE%20HUNGER%20GAMES.pdf?dl=0', 'https://www.dropbox.com/s/o3ohwivle2gs2px/THE%20HUNGER%20GAMES.docx?dl=0', 'english'),
(2, 'Michael Connelly', 'THE WRONG SIDE OF GOODBYE', 'Harry Bosch helps track a serial rapist, while as a P.I. he aids a billionaire in search of a possible heir.', '10', 'About This Book\r\nThe Success of C\r\nStandards\r\nHosted and Free-Standing Environments\r\nTypographical conventions\r\nOrder of topics\r\nExample programs\r\nDeference to Higher Authority\r\nAddress for the Standard', '2', '1', '', 'https://www.dropbox.com/s/r8rc8tcm8imvfck/THE%20WRONG%20SIDE%20OF%20GOODBYE.pdf?dl=0', 'https://www.dropbox.com/s/ile8w9j25rhitln/THE%20WRONG%20SIDE%20OF%20GOODBYE.docx?dl=0', 'hebrew'),
(3, 'Carrie Fisher', 'PRINCESS DIARIST', 'Recollections of life on the set of the first Star Wars movie by the actress and writer, who died in December.\r\n', '12', 'Introduction\r\nSetting Goals\r\nMy Goals\r\nMeeting Your Goals\r\nBrain Dump System\r\nStuck for a Story Idea?\r\nSuggestion #1\r\nSuggestion #2\r\nWho, What, Where, When\r\nWho – Your Characters\r\nCharacter Sketch\r\nSeasons Change and So Should Your Characters\r\nWhat – Your Plot\r\nHero’s Journey in Transformers and Princess Diaries\r\nWhere and When – Your Setting\r\nWays to Start a Story\r\nWith Character\r\nWith Action\r\nWith Setting\r\nDialogue\r\nRULE #1\r\nRULE #2\r\nRULE #3\r\nPoint of View\r\nWriting Description Even You Want to Read\r\nWatch the Was’s and Is’s\r\n7 Editing Questions and Why You Need to Ask Them\r\nConnecting with other writers\r\nInspiration – Be Ready For It!\r\nBeat Writer’s Block by Playing Your Cards Right\r\nKeeping the Writer in you Motivated\r\nLast Words', '1', '1', '', 'https://www.dropbox.com/s/hhoplivzbmpo1mr/PRINCESS%20DIARIST.pdf?dl=0', 'https://www.dropbox.com/s/p3lht8h4s0n9sib/PRINCESS%20DIARIST.docx?dl=0', 'french'),
(4, 'J. K. Rowling', 'HARRY POTTER', 'A wizard hones his conjuring skills in the service of fighting evil.', '15', 'The Boy Who Lived\r\nThe Vanishing Glass\r\nThe Letters from No One\r\nThe Keeper of the Keys\r\nDiagon Alley\r\nThe Journey from Platform Nine and Three-quarters\r\nThe Sorting Hat\r\nThe Potions Master\r\nThe Midnight Duel\r\nHalloween\r\nQuidditch\r\nThe Mirror of Erised\r\nNicolas Flamel\r\nNorbert The Norwegian Ridgeback\r\nThe Forbidden Forest\r\nThrough the Trapdoor\r\nThe Man with Two Faces', '2', '1', '', 'https://www.dropbox.com/s/1564o46dq004nvy/HARRY%20POTTER.pdf?dl=0', 'https://www.dropbox.com/s/1deezqwobqw7rw8/HARRY%20POTTER.docx?dl=0', 'english'),
(5, 'Paul Kalanithi', 'WHEN BREATH BECOMES AIR', 'A memoir by a physician who received a diagnosis of Stage IV lung cancer at the age of 36.', '10', 'Foreword / by Abraham Verghese\r\nPrologue\r\nIn perfect health I begin\r\nCease not till death\r\nEpilogue / by Lucy Kalanithi.', '1', '1', '', 'https://www.dropbox.com/s/k7cxkyvyils4nbz/WHEN%20BREATH%20BECOMES%20AIR.pdf?dl=0', 'https://www.dropbox.com/s/mb0mzpx420s3xc1/WHEN%20BREATH%20BECOMES%20AIR.docx?dl=0', 'hebrew'),
(6, 'Dorothy L. Sayers', 'The Documents in the Case', 'The grotesquely grinning corpse in the Devonshire shack was a man who died horribly -- with a dish of mushrooms at his side. His body contained enough death-dealing muscarine to kill 30 people. Why would an expert on fungi feast on a large quantity of this particularly poisonous species. A clue to the brilliant murderer, who had baffled the best minds in London, was hidden in a series of letters and documents that no one seemed to care about, except the dead man\'s son.', '13', '1. Introduction	1 \r\n2. Product Evaluation	2 \r\n 	2.1 Stationary	3 \r\n 	2.2 Computer products	4 \r\n 	2.3 Presentation equipment & products	5 \r\n 	2.4 RentSmart	6 \r\n3. Business and consumer product buying behaviour	7 \r\n 	2.1 Stationary	8 \r\n 	2.2 Computer products	9 \r\n 	2.3 Presentation equipment & products	10 \r\n 	2.4 RentSmart	11', '0', '1', 'Robert Eustace', 'https://www.dropbox.com/s/425uvzmvtfsblvg/The%20Documents%20in%20the%20Case.pdf?dl=0', 'https://www.dropbox.com/s/uwbb55o7tca4osi/The%20Documents%20in%20the%20Case.docx?dl=0', 'english');

-- --------------------------------------------------------

--
-- Table structure for table `bookcopies`
--

CREATE TABLE `bookcopies` (
  `copyid` int(11) NOT NULL,
  `bookTitle` varchar(45) NOT NULL,
  `language` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bookcopies`
--

INSERT INTO `bookcopies` (`copyid`, `bookTitle`, `language`) VALUES
(1, 'THE HUNGER GAMES', 'english'),
(2, 'THE HUNGER GAMES', 'hebrew'),
(5, 'THE WRONG SIDE OF GOODBYE', 'hebrew'),
(6, 'THE WRONG SIDE OF GOODBYE', 'french'),
(7, 'PRINCESS DIARIST', 'english'),
(8, 'PRINCESS DIARIST', 'french'),
(9, 'HARRY POTTER', 'french'),
(10, 'HARRY POTTER', 'hebrew'),
(11, 'HARRY POTTER', 'arabic'),
(12, 'WHEN BREATH BECOMES AIR', 'hebrew'),
(13, 'The Documents in the Case', 'french');

-- --------------------------------------------------------

--
-- Table structure for table `bookorder`
--

CREATE TABLE `bookorder` (
  `orderID` int(11) NOT NULL,
  `bookTitle` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `orderDate` date NOT NULL,
  `price` int(10) NOT NULL,
  `format` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bookorder`
--

INSERT INTO `bookorder` (`orderID`, `bookTitle`, `userName`, `orderDate`, `price`, `format`) VALUES
(4, 'HARRY POTTER', 'Hadi Abumaruf', '2017-01-10', 15, 'pdf'),
(6, 'THE HUNGER GAMES', 'Tomer Barak', '2017-01-05', 11, 'doc'),
(7, 'PRINCESS DIARIST', 'Nadav Rosenfeld', '2017-01-24', 10, 'doc'),
(8, 'THE WRONG SIDE OF GOODBYE', 'Tomer Barak', '2017-01-25', 11, 'pdf');

-- --------------------------------------------------------

--
-- Table structure for table `bookscope`
--

CREATE TABLE `bookscope` (
  `ScopeName` varchar(45) NOT NULL,
  `subjectName` varchar(45) NOT NULL,
  `bookTitle` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bookscope`
--

INSERT INTO `bookscope` (`ScopeName`, `subjectName`, `bookTitle`) VALUES
('ForFun', 'Children', 'HARRY POTTER'),
('ForStudies', 'Health', 'WHEN BREATH BECOMES AIR'),
('ForFun', 'Fiction', 'THE WRONG SIDE OF GOODBYE'),
('ForFun', 'Nonfiction', 'PRINCESS DIARIST'),
('ForFun', 'Nonfiction', 'THE HUNGER GAMES');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `bookTitle` varchar(50) NOT NULL,
  `review` text NOT NULL,
  `signature` varchar(11) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`reviewID`, `userName`, `bookTitle`, `review`, `signature`, `status`) VALUES
(12, 'Hadi Abumaruf', 'HARRY POTTER', 'I will never ever rate this lower than 5 stars. BEAUTIFUL.', 'hadi', 0),
(13, 'Tomer Barak', 'THE HUNGER GAMES', 'I have never read the book but I have seen the film and I enjoyed it very much. The ideas of the Story are so amazing for me, because I would never can write a book. I have much respect for People who write books. It must be a great Feeling when you write a book and then it get so famous then for example the Hunger Games. I think the idea is a little bit crazy that People who don´t know each other must kill each other but for a book or a film it is really great. Some day maybe I will read the book but bevor I watch the next parts of the film.', 'tomer', 0),
(14, 'Tomer Barak', 'THE WRONG SIDE OF GOODBYE', 'Harry Bosch is one of my favorite characters, and it was great to read this newest adventure.', 'tomer', 0),
(15, 'Nadav Rosenfeld', 'PRINCESS DIARIST', 'On the whole, I thoroughly enjoyed this, though I feel like the 1976 section probably works better in audio form than it did on the page. Definitely worth reading though.', 'nadav', 0);

-- --------------------------------------------------------

--
-- Table structure for table `scope`
--

CREATE TABLE `scope` (
  `scopeName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `scope`
--

INSERT INTO `scope` (`scopeName`) VALUES
('ForFun'),
('ForStudies');

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `BookTitle` varchar(45) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `search`
--

INSERT INTO `search` (`BookTitle`, `Date`) VALUES
('THE HUNGER GAMES', '2017-01-05'),
('THE WRONG SIDE OF GOODBYE', '2017-01-25'),
('THE WRONG SIDE OF GOODBYE', '2017-01-24'),
('PRINCESS DIARIST', '2017-01-24'),
('HARRY POTTER', '2017-01-10'),
('HARRY POTTER', '2017-01-28'),
('WHEN BREATH BECOMES AIR', '2017-01-27');

-- --------------------------------------------------------

--
-- Table structure for table `subjectscope`
--

CREATE TABLE `subjectscope` (
  `ScopeName` varchar(45) NOT NULL,
  `subjectName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjectscope`
--

INSERT INTO `subjectscope` (`ScopeName`, `subjectName`) VALUES
('ForFun', 'Children'),
('ForFun', 'Fiction'),
('ForStudies', 'Health'),
('ForFun', 'Nonfiction');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `accessLevel` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  `ibookStatus` int(1) NOT NULL DEFAULT '0',
  `cardNumber` varchar(16) DEFAULT NULL,
  `ibookconfirm` int(1) DEFAULT NULL,
  `loginStatus` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `password`, `accessLevel`, `status`, `ibookStatus`, `cardNumber`, `ibookconfirm`, `loginStatus`) VALUES
(11111, 'Moshe Nathan', '777', 2, 1, 0, NULL, NULL, 0),
(38833740, 'Israel Israeli', '111', 2, 1, 0, NULL, NULL, 0),
(111111111, 'Saar Alul', '666', 1, 1, 0, NULL, NULL, 0),
(123456789, 'danny rose', '555', 1, 1, 0, NULL, NULL, 0),
(301188504, 'Hadi Abumaruf', '333', 1, 1, 3, '2222222222222222', 4, 0),
(302703319, 'Nadav Rosenfeld', 'nadavro1', 2, 1, 2, '2222333344445555', 4, 0),
(305284275, 'Tomer Barak', '444', 1, 1, 1, '2222333344445555', 5, 0),
(319286738, 'Dima Spektor', '222', 3, 1, 0, '111111111111111', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

CREATE TABLE `workers` (
  `worker ID` int(9) NOT NULL,
  `Worker name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Rank` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`worker ID`, `Worker name`, `Email`, `Rank`) VALUES
(11111, 'Moshe Nathan', 'moshe@sgsd.com', 'librarian'),
(38833740, 'Israel Israeli', 'israel@gmail.com', 'librarian'),
(319286738, 'Dima Spektor', 'hapoeldima@gmail.com', 'manager');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookID`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `title_2` (`title`);

--
-- Indexes for table `bookcopies`
--
ALTER TABLE `bookcopies`
  ADD PRIMARY KEY (`copyid`),
  ADD KEY `bookTitle` (`bookTitle`);

--
-- Indexes for table `bookorder`
--
ALTER TABLE `bookorder`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `bookName` (`bookTitle`),
  ADD KEY `userName` (`userName`);

--
-- Indexes for table `bookscope`
--
ALTER TABLE `bookscope`
  ADD KEY `ScopeName` (`ScopeName`),
  ADD KEY `subjectName` (`subjectName`),
  ADD KEY `bookTitle` (`bookTitle`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewID`,`userName`,`bookTitle`),
  ADD KEY `userName` (`userName`),
  ADD KEY `bookTitle` (`bookTitle`);

--
-- Indexes for table `scope`
--
ALTER TABLE `scope`
  ADD PRIMARY KEY (`scopeName`);

--
-- Indexes for table `search`
--
ALTER TABLE `search`
  ADD KEY `Book Title` (`BookTitle`);

--
-- Indexes for table `subjectscope`
--
ALTER TABLE `subjectscope`
  ADD KEY `ScopeName` (`ScopeName`),
  ADD KEY `subjectName` (`subjectName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `userName` (`userName`),
  ADD KEY `cardNumber` (`cardNumber`),
  ADD KEY `accessLevel` (`accessLevel`);

--
-- Indexes for table `workers`
--
ALTER TABLE `workers`
  ADD PRIMARY KEY (`worker ID`),
  ADD KEY `workerName` (`Worker name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `bookcopies`
--
ALTER TABLE `bookcopies`
  MODIFY `copyid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `bookorder`
--
ALTER TABLE `bookorder`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookcopies`
--
ALTER TABLE `bookcopies`
  ADD CONSTRAINT `bookcopies_ibfk_1` FOREIGN KEY (`bookTitle`) REFERENCES `book` (`title`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `bookorder`
--
ALTER TABLE `bookorder`
  ADD CONSTRAINT `bookorder_ibfk_1` FOREIGN KEY (`bookTitle`) REFERENCES `book` (`title`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookorder_ibfk_2` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `bookscope`
--
ALTER TABLE `bookscope`
  ADD CONSTRAINT `bookscope_ibfk_1` FOREIGN KEY (`ScopeName`) REFERENCES `scope` (`scopeName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookscope_ibfk_2` FOREIGN KEY (`subjectName`) REFERENCES `subjectscope` (`subjectName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookscope_ibfk_3` FOREIGN KEY (`bookTitle`) REFERENCES `book` (`title`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`bookTitle`) REFERENCES `book` (`title`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `search`
--
ALTER TABLE `search`
  ADD CONSTRAINT `search_ibfk_1` FOREIGN KEY (`BookTitle`) REFERENCES `book` (`title`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subjectscope`
--
ALTER TABLE `subjectscope`
  ADD CONSTRAINT `subjectscope_ibfk_1` FOREIGN KEY (`ScopeName`) REFERENCES `scope` (`scopeName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `workers`
--
ALTER TABLE `workers`
  ADD CONSTRAINT `workers_ibfk_1` FOREIGN KEY (`worker ID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `workers_ibfk_2` FOREIGN KEY (`Worker name`) REFERENCES `users` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
