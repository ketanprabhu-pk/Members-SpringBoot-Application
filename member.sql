-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2021 at 10:48 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `members`
--

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(16) NOT NULL,
  `userid` tinytext NOT NULL,
  `name` varchar(100) NOT NULL,
  `jobs_completed` int(5) NOT NULL,
  `preferred_location` tinytext NOT NULL,
  `profile_completed` tinyint(1) NOT NULL,
  `inserted_at` datetime NOT NULL DEFAULT current_timestamp(),
  `created_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `userid`, `name`, `jobs_completed`, `preferred_location`, `profile_completed`, `inserted_at`, `created_at`) VALUES
(1, 'cbad123', 'queen', 5, 'remote', 0, '2021-12-09 02:04:42', '2021-12-09 02:04:42'),
(2, 'abcd123', 'king', 2, 'hybrid', 1, '2021-12-09 02:04:42', '2021-12-09 02:05:04'),
(4, 'jdks123', 'princess', 1, 'hybrid', 1, '2021-12-09 02:04:42', '2021-12-09 02:04:42'),
(5, 'sdds366', 'prince12', 18, 'remote', 1, '2021-12-10 10:47:34', '2021-12-10 10:47:34'),
(6, 'sdds345', 'kgfbff', 98, 'remote', 0, '2021-12-10 14:11:05', '2021-12-10 14:11:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userid` (`userid`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
