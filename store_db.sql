-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 22, 2023 at 06:50 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `admin` bit(1) NOT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `expiry` varchar(10) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `order_items_id` int(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `total_price` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `date`, `delivery_address`, `order_items_id`, `payment_status`, `total_price`) VALUES
(54, 13, '2023-05-22', '123 street rg1234', 20, 'Paid', '166.66'),
(55, 13, '2023-05-22', '123 street rg1234', 20, 'Paid', '9.98'),
(56, 13, '2023-05-22', '123 street rg1234', 20, 'Paid', '9.98'),
(57, 15, '2023-05-22', '123 s 1234', 20, 'Paid', '119.96'),
(58, 17, '2023-05-22', '321 street ox123', 20, 'Paid', '510'),
(59, 21, '2023-05-22', '321 street ox1234', 20, 'Paid', '69.98'),
(60, 21, '2023-05-22', '123 street rg1234', 20, 'Paid', '69.98');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `order_items_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `price` varchar(45) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`order_items_id`, `customer_id`, `price`, `product_id`, `quantity`, `product_name`) VALUES
(73, 15, '29.99', 26, 4, 'Razer mouse (black)'),
(74, 0, '34.99', 26, 1, 'Razer mouse (black)'),
(75, 0, '34.99', 26, 1, 'Razer mouse (black)'),
(76, 0, '34.99', 26, 1, 'Razer mouse (black)'),
(77, 0, '34.99', 26, 1, 'Razer mouse (black)'),
(78, 0, '34.99', 26, 1, 'Razer mouse (black)'),
(79, 0, '33.65', 29, 1, 'Hyper gaming keyboard RGB'),
(81, 17, '255.0', 30, 2, '32 inch 4K monitor'),
(83, 21, '34.99', 26, 2, 'Razer mouse (black)'),
(84, 21, '56.79', 28, 1, 'Razer keyboard (black)'),
(85, 21, '255.0', 30, 1, '32 inch 4K monitor');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `product_type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `description`, `image`, `name`, `price`, `stock`, `product_type`) VALUES
(26, 'This is a black razer mouse', NULL, 'Razer mouse (black)', 34.99, 43, 'MOUSE'),
(27, 'The white version of the mouse ', '', 'Razer Mouse (white)', 12.75, 0, 'MOUSE'),
(28, 'this is a great keyboard', '', 'Razer keyboard (black)', 56.79, 23, 'KEYBOARD'),
(29, 'this light up keyboard has many colours for a great price!', '', 'Hyper gaming keyboard RGB', 33.65, 59, 'KEYBOARD'),
(30, 'This is a high definition screen', '', '32 inch 4K monitor', 255, 4, 'MONITOR'),
(31, '1080p monitor 60HZ', '', 'HD monitor Asus', 150, 12, 'MONITOR'),
(32, 'Best headphones wireless', '', 'SoundWave headphones', 100.5, 0, 'HEADSET'),
(33, 'Great headset for playing xbox with ', '', 'Headset Xbox 360', 35.65, 10, 'HEADSET'),
(34, 'This is the best wireless playstation headset', '', 'Headset Playstation 4', 34.85, 10, 'HEADSET'),
(35, 'Amazing noise cancelling headphones ', '', 'Noise Cancelling headphones Sony', 120.25, 1, 'HEADSET'),
(36, 'This is a very pink and very clickey keyboard ', '', '60% mechanical keyboard (pink)', 9, 12, 'KEYBOARD'),
(37, 'A classic keyboard with a classic colour', '', 'Beige keyboard ', 12.99, 12, 'KEYBOARD'),
(38, 'This ultra light gaming mouse only weights 30grams!', '', 'Superlight gaming mouse (back)', 44.99, 3, 'MOUSE'),
(39, 'Old but gold ', '', 'Optical mouse (beige)', 5.99, 5, 'MOUSE'),
(40, 'this is a budget keyboard but gets the job done', '', 'Hyper keyboard', 11.99, 159, 'KEYBOARD'),
(41, 'The wireless range extends to over 30 meters. ', '', 'Wireless headset', 24.65, 32, 'HEADSET'),
(42, 'This keyboard is black', '', 'Keyboard (white)', 15.99, 4, 'KEYBOARD'),
(43, 'This keyboard is white', '', 'Keyboard (black)', 12.76, 15, 'KEYBOARD'),
(44, 'this is massive', '', '55 inch monitor ', 550, 5, 'MONITOR'),
(45, 'This is even bigger', '', '65 inch monitor ', 650, 3, 'MONITOR'),
(46, 'This High resolution gaming monitor is great', '', '24 inch Gaming monitor Samsung ', 156.99, 65, 'MONITOR'),
(47, 'This is a wireless mouse. ', '', 'Wireless mouse ', 19.99, 1, 'MOUSE');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`order_items_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `order_items_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
