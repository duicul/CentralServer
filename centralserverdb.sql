-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2019 at 11:48 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `centralserverdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `in_pins`
--

CREATE TABLE `in_pins` (
  `uid` int(11) NOT NULL,
  `Pin_No` int(11) NOT NULL,
  `Value` varchar(20) NOT NULL,
  `Sensor` varchar(30) NOT NULL,
  `TimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `in_pins`
--

INSERT INTO `in_pins` (`uid`, `Pin_No`, `Value`, `Sensor`, `TimeStamp`) VALUES
(1, 5, '20.2 60', 'DHT11', '2019-03-28 19:54:18');

-- --------------------------------------------------------

--
-- Table structure for table `in_pins_log`
--

CREATE TABLE `in_pins_log` (
  `ilid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `Pin_No` int(11) NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `Sensor` varchar(30) NOT NULL,
  `Value` varchar(20) NOT NULL,
  `TimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `in_pins_log`
--

INSERT INTO `in_pins_log` (`ilid`, `uid`, `Pin_No`, `NAME`, `Sensor`, `Value`, `TimeStamp`) VALUES
(53, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-24 09:00:29'),
(54, 1, 5, 'Termostat Cada', 'DTH11', '20.2 60', '2019-03-24 14:11:10'),
(55, 1, 5, 'Termostat Cada', 'DTH11', '31.073959257', '2019-03-24 14:17:56'),
(56, 1, 5, 'Termostat Cada', 'DTH11', '3.81440319138', '2019-03-24 14:18:33'),
(57, 1, 5, 'Termostat Cada', 'DTH11', '14.3339699116', '2019-03-24 14:22:00'),
(58, 1, 5, 'Termostat Cada', 'DTH11', '25.0 18.0', '2019-03-24 14:24:27'),
(59, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-24 14:28:11'),
(60, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-24 14:28:24'),
(61, 1, 5, 'Termostat Cada', 'DHT11', '12.5699317565', '2019-03-24 14:29:04'),
(62, 1, 5, 'Termostat Cada', 'DHT11', '28.0 29.0', '2019-03-24 14:30:55'),
(63, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-28 18:43:13'),
(64, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-28 19:51:08'),
(65, 1, 5, 'Termostat Cada', 'DHT11', '20.2 60', '2019-03-28 19:54:18');

-- --------------------------------------------------------

--
-- Table structure for table `out_pins`
--

CREATE TABLE `out_pins` (
  `uid` int(11) NOT NULL,
  `Pin_No` int(11) NOT NULL,
  `Value` int(1) NOT NULL,
  `CHANGED` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `out_pins`
--

INSERT INTO `out_pins` (`uid`, `Pin_No`, `Value`, `CHANGED`) VALUES
(1, 1, 1, 1),
(1, 3, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pins`
--

CREATE TABLE `pins` (
  `uid` int(11) NOT NULL,
  `Pin_No` int(11) NOT NULL,
  `Type` varchar(10) NOT NULL,
  `NAME` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pins`
--

INSERT INTO `pins` (`uid`, `Pin_No`, `Type`, `NAME`) VALUES
(1, 1, 'OUT', 'Lumina hol'),
(1, 3, 'OUT', 'Lumina baie'),
(1, 5, 'IN', 'Termostat Cada');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `uid` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(40) NOT NULL,
  `adress` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `info` varchar(100) NOT NULL,
  `security` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`uid`, `name`, `password`, `email`, `adress`, `phone`, `info`, `security`) VALUES
(1, 'duicul', 'edfb5a2ed9c5cecfc167bb32a1f586e794fcc0bdf4731af0221f9fe1711a9c4c1682dcf8df7ec57445f7d3db44824924f123c83a8a2cfa49ba03fd3f5deb4a86', 'duicul@yahoo.com', 'romania', '8865755744', 'data', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `in_pins`
--
ALTER TABLE `in_pins`
  ADD PRIMARY KEY (`uid`,`Pin_No`,`Sensor`);

--
-- Indexes for table `in_pins_log`
--
ALTER TABLE `in_pins_log`
  ADD PRIMARY KEY (`ilid`);

--
-- Indexes for table `out_pins`
--
ALTER TABLE `out_pins`
  ADD PRIMARY KEY (`uid`,`Pin_No`);

--
-- Indexes for table `pins`
--
ALTER TABLE `pins`
  ADD PRIMARY KEY (`uid`,`Pin_No`,`Type`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `in_pins_log`
--
ALTER TABLE `in_pins_log`
  MODIFY `ilid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
