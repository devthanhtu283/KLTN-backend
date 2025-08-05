-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2025 at 04:50 PM
-- Server version: 10.4.32-MariaDB-log
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jobs`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `questionID` int(11) NOT NULL,
  `content` text NOT NULL,
  `is_correct` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `questionID`, `content`, `is_correct`) VALUES
(1, 1, '2', 1),
(2, 1, '3', 0),
(5, 1, '4', 0),
(6, 1, '5', 0),
(8, 4, 'Tố Hữu', 1),
(9, 4, 'Xuân Diệu', 0),
(10, 4, 'Quang Trung', 0),
(11, 5, 'Đúng', 1),
(12, 5, 'Sai', 0),
(13, 7, 'Tú', 0),
(14, 7, 'World', 1),
(15, 7, 'Các bạn', 0),
(16, 7, 'Gì', 0);

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `applied_at` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `job_id`, `seeker_id`, `applied_at`, `status`) VALUES
(88, 101, 65, '2025-04-13 04:10:48', 0),
(89, 101, 65, '2025-04-13 04:15:14', 0),
(90, 101, 65, '2025-04-13 04:20:45', 0),
(91, 89, 65, '2025-04-13 04:27:41', 3),
(92, 89, 65, '2025-04-13 04:28:12', 3),
(94, 31, 65, '2025-01-27 11:36:30', 2),
(95, 31, 65, '2025-01-27 11:36:30', 2),
(97, 31, 65, '2025-01-27 18:36:30', 2),
(98, 142, 65, '2025-05-06 04:57:51', 0),
(101, 140, 65, '2025-01-27 18:36:30', 2),
(106, 269, 108, '2025-07-20 19:22:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(500) NOT NULL,
  `subcategory_name` varchar(500) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`, `subcategory_name`, `status`) VALUES
(1, 'Software Engineering', 'Backend Developer', 1),
(2, 'Software Engineering', 'Frontend Developer', 1),
(3, 'Software Engineering', 'Fullstack Developer', 1),
(4, 'Software Engineering', 'Mobile Developer', 1),
(5, 'Software Engineering', 'Web Developer', 1),
(6, 'Software Engineering', 'DevOps Engineer', 1),
(7, 'Software Engineering', 'Game Developer', 1),
(8, 'Software Engineering', 'Embedded Systems Engineer', 1),
(9, 'Software Engineering', 'Cloud Engineer', 1),
(10, 'Software Engineering', 'Software Architect', 1),
(11, 'Software Engineering', 'Software Development Engineer in Test (SDET)', 1),
(12, 'Software Engineering', 'UI/UX Developer', 1),
(13, 'Software Engineering', 'Blockchain Developer', 1),
(14, 'Software Engineering', 'Machine Learning Engineer', 1),
(15, 'Software Testing', 'Software Tester', 1),
(16, 'Software Testing', 'Automation Tester', 1),
(17, 'Software Testing', 'Manual Tester', 1),
(18, 'Software Testing', 'Performance Tester', 1),
(19, 'Software Testing', 'QA Engineer', 1),
(20, 'Software Testing', 'Game Tester', 1),
(21, 'Software Testing', 'Security Tester', 1),
(22, 'Artificial Intelligence (AI)', 'AI Engineer', 1),
(23, 'Artificial Intelligence (AI)', 'Data Scientist', 1),
(24, 'Artificial Intelligence (AI)', 'Data Analyst', 1),
(25, 'Artificial Intelligence (AI)', 'Machine Learning Engineer', 1),
(26, 'Artificial Intelligence (AI)', 'AI Researcher', 1),
(27, 'Artificial Intelligence (AI)', 'Natural Language Processing (NLP) Engineer', 1),
(28, 'Artificial Intelligence (AI)', 'Computer Vision Engineer', 1),
(29, 'Artificial Intelligence (AI)', 'Deep Learning Engineer', 1),
(30, 'Artificial Intelligence (AI)', 'AI Product Manager', 1),
(31, 'Cybersecurity', 'Cybersecurity Analyst', 1),
(32, 'Cybersecurity', 'Security Engineer', 1),
(33, 'Cybersecurity', 'Penetration Tester', 1),
(34, 'Cybersecurity', 'IT Security Specialist', 1),
(35, 'Cybersecurity', 'SOC Analyst', 1),
(36, 'Cybersecurity', 'Cryptographer', 1),
(37, 'Cybersecurity', 'Cloud Security Engineer', 1),
(38, 'Cybersecurity', 'Application Security Engineer', 1),
(39, 'Cybersecurity', 'Network Security Engineer', 1),
(40, 'Database Administration', 'Database Administrator', 1),
(41, 'Database Administration', 'Data Architect', 1),
(42, 'Database Administration', 'Database Developer', 1),
(43, 'Database Administration', 'DBA Support', 1),
(44, 'Database Administration', 'NoSQL Database Administrator', 1),
(45, 'Database Administration', 'Big Data Engineer', 1),
(46, 'Cloud Computing', 'Cloud Architect', 1),
(47, 'Cloud Computing', 'Cloud Engineer', 1),
(48, 'Cloud Computing', 'Cloud Solutions Architect', 1),
(49, 'Cloud Computing', 'Cloud Consultant', 1),
(50, 'Cloud Computing', 'Cloud Developer', 1),
(51, 'Cloud Computing', 'AWS Engineer', 1),
(52, 'Cloud Computing', 'Azure Engineer', 1),
(53, 'Cloud Computing', 'Google Cloud Engineer', 1),
(54, 'Cloud Computing', 'Cloud Security Engineer', 1),
(55, 'Network and Systems Administration', 'System Administrator', 1),
(56, 'Network and Systems Administration', 'Network Administrator', 1),
(57, 'Network and Systems Administration', 'Linux Administrator', 1),
(58, 'Network and Systems Administration', 'IT Support Engineer', 1),
(59, 'Network and Systems Administration', 'Cloud Systems Administrator', 1),
(60, 'Network and Systems Administration', 'Virtualization Engineer', 1),
(61, 'UI/UX Design', 'UI Designer', 1),
(62, 'UI/UX Design', 'UX Designer', 1),
(63, 'UI/UX Design', 'Interaction Designer', 1),
(64, 'UI/UX Design', 'Visual Designer', 1),
(65, 'UI/UX Design', 'Product Designer', 1),
(66, 'UI/UX Design', 'UX Researcher', 1),
(67, 'Game Development', 'Game Developer', 1),
(68, 'Game Development', 'Game Designer', 1),
(69, 'Game Development', 'Game Artist', 1),
(70, 'Game Development', 'Game Programmer', 1),
(71, 'Game Development', 'Game Producer', 1),
(72, 'Game Development', 'Game Animator', 1),
(73, 'Game Development', 'Game Sound Designer', 1),
(74, 'Blockchain Technology', 'Blockchain Developer', 1),
(75, 'Blockchain Technology', 'Blockchain Architect', 1),
(76, 'Blockchain Technology', 'Blockchain Consultant', 1),
(77, 'Blockchain Technology', 'Blockchain Researcher', 1),
(78, 'Blockchain Technology', 'Smart Contract Developer', 1),
(79, 'Technical Support', 'IT Support', 1),
(80, 'Technical Support', 'Technical Support Engineer', 1),
(81, 'Technical Support', 'Help Desk Technician', 1),
(82, 'Technical Support', 'Customer Support Engineer', 1),
(83, 'Technical Support', 'Product Support Engineer', 1),
(84, 'Business Analysis', 'Business Analyst', 1),
(85, 'Business Analysis', 'Data Analyst', 1),
(86, 'Business Analysis', 'Business Systems Analyst', 1),
(87, 'Business Analysis', 'Financial Analyst', 1),
(88, 'Business Analysis', 'Process Analyst', 1),
(89, 'Business Analysis', 'Product Analyst', 1),
(90, 'IT Project Management', 'IT Project Manager', 1),
(91, 'IT Project Management', 'Scrum Master', 1),
(92, 'IT Project Management', 'Agile Project Manager', 1),
(93, 'IT Project Management', 'Program Manager', 1),
(94, 'IT Project Management', 'Project Coordinator', 1);

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `sender_role` text NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `receiver_role` text NOT NULL,
  `message` text NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`id`, `sender_id`, `sender_role`, `receiver_id`, `receiver_role`, `message`, `time`, `status`) VALUES
(146, 108, 'SEEKER', 1, 'EMPLOYER', 'Ứng viên đã ứng tuyển công việc <strong>Sr Java</strong>. Đây là CV của Ứng viên: <a href=\"http://192.168.198.1:8081/assets/files/a7fa4ab260c345ffa052d26699a2e002.pdf\" target=\"_blank\">Xem CV</a>', '2025-07-20 12:22:05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cv`
--

CREATE TABLE `cv` (
  `id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `skills` text DEFAULT NULL,
  `primary_skills` longtext DEFAULT NULL,
  `secondary_skills` longtext DEFAULT NULL,
  `adverbs` longtext DEFAULT NULL,
  `adjectives` longtext DEFAULT NULL,
  `experience` text DEFAULT NULL,
  `type` text DEFAULT NULL,
  `education` text DEFAULT NULL,
  `certification` text DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `offer_salary` text DEFAULT NULL,
  `job_deadline` text DEFAULT NULL,
  `linked_in` text DEFAULT NULL,
  `link_git` text DEFAULT NULL,
  `upload_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cv`
--

INSERT INTO `cv` (`id`, `seeker_id`, `name`, `skills`, `primary_skills`, `secondary_skills`, `adverbs`, `adjectives`, `experience`, `type`, `education`, `certification`, `status`, `offer_salary`, `job_deadline`, `linked_in`, `link_git`, `upload_at`) VALUES
(2, 65, '066148d66a6c41688cf40c68abe42d92.pdf', 'python, java, javascript, php, java spring, spring boot, laravel, sql, mysql, postgresql, redis, sql server, html, css, restful, oop, kafka, software engineer, software, backend, security, reliability, database, performance, pipeline, git, github, docker, apache, apache kafka, rest api, keycloak, api, caching, redis caching, wordpress', '[\"python\", \"java\", \"javascript\", \"php\", \"java spring\", \"spring boot\", \"laravel\", \"sql\", \"mysql\", \"postgresql\", \"redis\", \"sql server\", \"html\", \"css\", \"restful\", \"oop\", \"kafka\", \"software engineer\", \"software\", \"backend\", \"security\", \"reliability\", \"database\", \"performance\", \"pipeline\", \"git\", \"github\", \"docker\", \"apache\", \"apache kafka\", \"rest api\", \"keycloak\", \"api\", \"caching\", \"redis caching\", \"wordpress\"]', '[]', '[\"directly\", \"forward\", \"solely\"]', '[\"debezium\", \"advanced\", \"limited\", \"passionate\", \"innovative\", \"new\", \"responsible\", \"restful\", \"basic\", \"large\", \"admin\", \"high\", \"real\", \"apis\", \"wordpress\", \"present\", \"seamless\", \"electronic\", \"dynamic\", \"entire\"]', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2025-07-19 17:14:22'),
(4, 97, 'fcc7ccb87c704431bc0faeec4b2adf35.pdf', 'java, javascript, php, angular, java spring, spring framework, laravel, jquery, flutter, sql, mysql, sql server, html, css, kafka, mobile, platform, frontend, git, github, sourcetree, docker, api, jira, wordpress, python, spring boot, postgresql, redis, restful, oop, software engineer, software, backend, security, reliability, database, performance, pipeline, apache, apache kafka, rest api, keycloak, caching, redis caching', NULL, NULL, '\"forward, solely, directly\"', '\"apis, limited, basic, innovative, present, high, dynamic, large, new, entire, debezium, real, passionate, responsible, admin, restful, wordpress, seamless, electronic, advanced\"', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2025-07-17 21:52:40'),
(5, 108, '9f10fa612a914577803eca4180929d9d.pdf', 'python, java, javascript, php, angular, java spring, spring boot, laravel, sql, mysql, postgresql, redis, sql server, html, css, restful, oop, kafka, software, backend developer, backend, security, reliability, database, performance, pipeline, git, github, docker, apache, apache kafka, rest api, keycloak, api, caching, redis caching, wordpress, software engineer', '[\"python\", \"java\", \"javascript\", \"php\", \"angular\", \"java spring\", \"spring boot\", \"laravel\", \"sql\", \"mysql\", \"postgresql\", \"redis\", \"sql server\", \"html\", \"css\", \"restful\", \"oop\", \"kafka\", \"software\", \"backend developer\", \"backend\", \"security\", \"reliability\", \"database\", \"performance\", \"pipeline\", \"git\", \"github\", \"docker\", \"apache\", \"apache kafka\", \"rest api\", \"keycloak\", \"api\", \"caching\", \"redis caching\", \"wordpress\"]', '[]', '[\"forward\", \"solely\", \"online\", \"quickly\", \"directly\"]', '[\"basic\", \"online\", \"apis\", \"new\", \"limited\", \"advanced\", \"admin\", \"friendly\", \"innovative\", \"entire\", \"easy\", \"dynamic\", \"wordpress\", \"modern\", \"seamless\", \"passionate\", \"angular\", \"restful\", \"real\", \"high\", \"debezium\", \"large\", \"responsible\", \"present\"]', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2025-07-20 12:36:15');

-- --------------------------------------------------------

--
-- Table structure for table `employer`
--

CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `company_name` text DEFAULT NULL,
  `company_profile` text DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `contact_info` text DEFAULT NULL,
  `logo` text DEFAULT NULL,
  `cover_img` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `map_link` text DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `founded_in` text DEFAULT NULL,
  `team_member` text DEFAULT NULL,
  `company_field` text DEFAULT NULL,
  `company_link` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employer`
--

INSERT INTO `employer` (`id`, `company_name`, `company_profile`, `rating`, `contact_info`, `logo`, `cover_img`, `address`, `map_link`, `amount`, `status`, `description`, `founded_in`, `team_member`, `company_field`, `company_link`) VALUES
(1, 'Công ty FireGroup', 'FIREGROUP TECHNOLOGY\nFireGroup envisions and strives to make an e-Commerce world where every merchant can succeed a reality. Since our founding in 2016, we have specialized in providing a comprehensive portfolio of category-leading SaaS products for the global e-Commerce market, including online store development, store management, sales & marketing automation, and almost every aspect of running an online business.\n\nAfter almost a decade in the market, we are loved by over 450,000 merchants and enterprises across 175+ countries and highly trusted by our globally renowned partners such as Shopify, BigCommerce, WooCommerce, Google, Meta, Tiktok, and Amazon. To continue empowering more merchants, we always welcome new tech talents who work toward a rewarding career and live by these 4 core values:\n\nGrowth\nTrust\nEmbracing Change\nCustomer Centricity\nWhy to build your career with us\n\nMake your impact globally on hundreds of thousands of merchants’ success\nDevelop your skills in a fast-paced and highly competitive SaaS market\nCreate and grow continuously among passionate talents with growth mindsets', 0, 'acc1@gmail.com', '8dd661aefb2f484d85228df401cb1093.jpg', 'be8e26206e1f4f009c52daeed6b7bfce.jpg', 'TP Hồ Chí Minh - Đà Nẵng', '', 200000, 1, 'Không có OT', '2016', '300', 'Thương Mại Điện Tử', 'https://firegroup.io/'),
(2, 'FPT Software', 'FPT Software is a global IT company providing software development and IT services.', 0, 'contact@fptsoftware.com', '34538c77a91f4f4494596754d85dbec7.png', 'fbefed9351924f819b5f1f66256bcca0.png', 'Hà Nội', '', 5000000000, 1, 'One of the largest IT companies in Vietnam.', '1988', '48000', 'Công nghệ thông tin', 'https://fptsoftware.com'),
(3, 'Tiki Corporation', 'Tiki Corporation is a leading e-commerce platform in Vietnam.', 4.5, 'support@tiki.vn', NULL, NULL, 'TP Hồ Chí Minh', NULL, 2000000000, 1, 'Focuses on e-commerce and logistics.', '2010', '4000', 'Thương mại điện tử', 'https://tiki.vn'),
(4, 'Axon Active Vietnam', 'Axon Active Vietnam provides software development and IT outsourcing services.', 4.3, 'info@axonactive.com', NULL, NULL, 'TP Hồ Chí Minh', NULL, 1000000000, 1, 'Specializes in offshore software development.', '2008', '1000', 'Công nghệ thông tin', 'https://axonactive.com'),
(5, 'KMS Technology', 'KMS Technology focuses on software development and IT solutions.', 4.7, 'contact@kms-technology.com', NULL, NULL, 'TP Hồ Chí Minh', NULL, 1500000000, 1, 'Provides software development and testing services.', '2009', '1500', 'Công nghệ thông tin', 'https://kms-technology.com'),
(6, 'Haravan', 'Haravan provides e-commerce and retail solutions.', 4.2, 'support@haravan.com', NULL, NULL, 'TP Hồ Chí Minh', NULL, 500000000, 1, 'Specializes in e-commerce platforms and services.', '2014', '150', 'Thương mại điện tử', 'https://haravan.com'),
(7, 'TikiNow', 'TikiNow is a logistics service under Tiki Corporation.', 4, 'support@tiki.vn', NULL, NULL, 'TP Hồ Chí Minh', NULL, 300000000, 1, 'Focuses on fast delivery services.', '2016', '100', 'Logistics', 'https://tiki.vn'),
(8, 'Viettel Solutions', 'Viettel Solutions provides IT services and digital transformation solutions to businesses.', 4.6, 'contact@viettelsolutions.vn', 'https://viettelsolutions.vn/logo.png', 'https://viettelsolutions.vn/cover.jpg', 'Hà Nội', 'https://maps.google.com?viettel', 3000000000, 1, 'A leading IT arm of Viettel Group, focusing on digital innovation.', '2007', '2000', 'Công nghệ thông tin', 'https://viettelsolutions.vn'),
(9, 'TMA Solutions', 'TMA Solutions offers software outsourcing and development services globally.', 4.4, 'info@tmasolutions.com', 'https://tmasolutions.com/logo.png', 'https://tmasolutions.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?tma', 1200000000, 1, 'A pioneer in software engineering with a strong global presence.', '1997', '3500', 'Công nghệ thông tin', 'https://tmasolutions.com'),
(10, 'Rikkei Soft', 'Rikkei Soft focuses on mobile and web development with innovative approaches.', 4.2, 'contact@rikkeisoft.com', 'https://rikkeisoft.com/logo.png', 'https://rikkeisoft.com/cover.jpg', 'Hà Nội', 'https://maps.google.com?rikkei', 500000000, 1, 'A growing IT firm specializing in mobile and web technologies.', '2012', '150', 'Công nghệ thông tin', 'https://rikkeisoft.com'),
(11, 'LogiGear Vietnam', 'LogiGear Vietnam provides software testing and quality assurance services.', 4.5, 'support@logigear.com', 'https://logigear.com/logo.png', 'https://logigear.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?logigear', 800000000, 1, 'Known for its expertise in software quality assurance.', '2003', '800', 'Công nghệ thông tin', 'https://logigear.com'),
(12, 'Tek Experts Vietnam', 'Tek Experts offers tech support and IT services with global reach.', 4.3, 'info@tekexperts.com', 'https://tekexperts.com/logo.png', 'https://tekexperts.com/cover.jpg', 'Đà Nẵng', 'https://maps.google.com?tek', 700000000, 1, 'A global provider of technical support and IT solutions.', '2014', '400', 'Công nghệ thông tin', 'https://tekexperts.com'),
(13, 'Sun* Inc.', 'Sun* Inc. specializes in software development and artificial intelligence.', 4.7, 'contact@sun-asterisk.com', 'https://sun-asterisk.com/logo.png', 'https://sun-asterisk.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?sun', 900000000, 1, 'An innovative firm focusing on AI and software solutions.', '2009', '1200', 'Công nghệ thông tin', 'https://sun-asterisk.com'),
(14, 'NashTech Vietnam', 'NashTech provides digital transformation and cloud services.', 4.4, 'info@nashtechglobal.com', 'https://nashtechglobal.com/logo.png', 'https://nashtechglobal.com/cover.jpg', 'Hà Nội', 'https://maps.google.com?nashtech', 1100000000, 1, 'A leader in digital and cloud transformation solutions.', '2000', '1500', 'Công nghệ thông tin', 'https://nashtechglobal.com'),
(15, 'FPT Telecom', 'FPT Telecom offers internet and telecom services across Vietnam.', 4.6, 'support@fpttelecom.vn', 'https://fpttelecom.vn/logo.png', 'https://fpttelecom.vn/cover.jpg', 'Hà Nội', 'https://maps.google.com?fpt', 2500000000, 1, 'A leading telecom provider with nationwide coverage.', '1997', '3000', 'Viễn thông', 'https://fpttelecom.vn'),
(16, 'CMC Global', 'CMC Global provides IT services and software solutions.', 4.5, 'contact@cmcglobal.com', 'https://cmcglobal.com/logo.png', 'https://cmcglobal.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?cmc', 1300000000, 1, 'Part of CMC Corporation, focusing on IT innovation.', '2005', '1800', 'Công nghệ thông tin', 'https://cmcglobal.com'),
(17, 'SmartOSC', 'SmartOSC specializes in e-commerce and digital solutions.', 4.3, 'info@smartosc.com', 'https://smartosc.com/logo.png', 'https://smartosc.com/cover.jpg', 'Hà Nội', 'https://maps.google.com?smartosc', 600000000, 1, 'A leading e-commerce and digital agency in Vietnam.', '2006', '700', 'Thương mại điện tử', 'https://smartosc.com'),
(18, '10Clouds Vietnam', '10Clouds Vietnam offers software development and cloud solutions.', 4.2, 'contact@10clouds.com', 'https://10clouds.com/logo.png', 'https://10clouds.com/cover.jpg', 'Đà Nẵng', 'https://maps.google.com?10clouds', 400000000, 1, 'Focuses on cloud-based and mobile application development.', '2015', '200', 'Công nghệ thông tin', 'https://10clouds.com'),
(19, 'Sii Asia Vietnam', 'Sii Asia provides IT outsourcing and consulting services.', 4.4, 'info@siiasia.com', 'https://siiasia.com/logo.png', 'https://siiasia.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?siiasia', 500000000, 1, 'Part of Sii Group, offering global IT services.', '2013', '300', 'Công nghệ thông tin', 'https://siiasia.com'),
(20, 'Digiworld', 'Digiworld distributes IT products and provides tech services.', 4.5, 'support@digiworld.com', 'https://digiworld.com/logo.png', 'https://digiworld.com/cover.jpg', 'Hà Nội', 'https://maps.google.com?digiworld', 800000000, 1, 'A leading IT distributor with a strong market presence.', '1996', '900', 'Phân phối công nghệ', 'https://digiworld.com'),
(21, 'VinAI', 'VinAI focuses on AI research and development for innovation.', 4.7, 'contact@vinai.io', 'https://vinai.io/logo.png', 'https://vinai.io/cover.jpg', 'Hà Nội', 'https://maps.google.com?vinai', 1000000000, 1, 'An AI research arm under Vingroup.', '2019', '150', 'Trí tuệ nhân tạo', 'https://vinai.io'),
(22, 'CyberLogitec Vietnam', 'CyberLogitec provides logistics software and solutions.', 4.3, 'info@cyberlogitec.com', 'https://cyberlogitec.com/logo.png', 'https://cyberlogitec.com/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?cyberlogitec', 600000000, 1, 'Specializes in logistics and supply chain technology.', '2005', '500', 'Công nghệ logistics', 'https://cyberlogitec.com'),
(23, 'Global CyberSoft', 'Global CyberSoft offers IT services and software development.', 4.2, 'support@globalcybersoft.com', 'https://globalcybersoft.com/logo.png', 'https://globalcybersoft.com/cover.jpg', 'Đà Nẵng', 'https://maps.google.com?globalcybersoft', 400000000, 1, 'A mid-sized IT firm with growing potential.', '2011', '180', 'Công nghệ thông tin', 'https://globalcybersoft.com'),
(24, 'Halotech', 'Halotech provides software solutions and IT support.', 4.1, 'contact@halotech.vn', 'https://halotech.vn/logo.png', 'https://halotech.vn/cover.jpg', 'Hà Nội', 'https://maps.google.com?halotech', 300000000, 1, 'A small IT startup with innovative projects.', '2018', '80', 'Công nghệ thông tin', 'https://halotech.vn'),
(25, 'KiotViet', 'KiotViet offers POS and e-commerce management solutions.', 4.3, 'info@kiotviet.vn', 'https://kiotviet.vn/logo.png', 'https://kiotviet.vn/cover.jpg', 'TP Hồ Chí Minh', 'https://maps.google.com?kiotviet', 450000000, 1, 'A leader in retail management software in Vietnam.', '2013', '120', 'Thương mại điện tử', 'https://kiotviet.vn'),
(26, 'VCCorp', 'VCCorp provides digital marketing and technology services.', 4.6, 'support@vccorp.vn', 'https://vccorp.vn/logo.png', 'https://vccorp.vn/cover.jpg', 'Hà Nội', 'https://maps.google.com?vccorp', 900000000, 1, 'A leading digital media and technology company.', '2007', '1100', 'Truyền thông số', 'https://vccorp.vn'),
(27, 'G-Group', 'G-Group offers IT training and software development services.', 4.2, 'contact@ggroup.vn', 'https://ggroup.vn/logo.png', 'https://ggroup.vn/cover.jpg', 'Đà Nẵng', 'https://maps.google.com?ggroup', 350000000, 1, 'Focuses on IT education and technology training.', '2016', '90', 'Đào tạo công nghệ', 'https://ggroup.vn'),
(88, 'Công ty Yes4all', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employermembership`
--

CREATE TABLE `employermembership` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `membership_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `end_date` datetime NOT NULL,
  `renewal_date` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employermembership`
--

INSERT INTO `employermembership` (`id`, `user_id`, `membership_id`, `start_date`, `end_date`, `renewal_date`, `status`) VALUES
(2, 1, 4, '2025-07-04 21:45:03', '2026-06-03 16:35:42', '2026-06-03 16:35:42', 1),
(5, 97, 3, '2025-07-17 21:50:33', '2025-08-17 21:50:33', '2025-08-17 21:50:33', 1),
(7, 95, 3, '2025-07-18 12:06:28', '2025-08-18 12:06:28', '2025-08-18 12:06:28', 1),
(10, 65, 3, '2025-07-19 17:01:34', '2025-08-19 17:01:34', '2025-08-19 17:01:34', 1),
(11, 108, 3, '2025-07-20 12:32:39', '2025-08-20 12:32:39', '2025-08-20 12:32:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `experience`
--

CREATE TABLE `experience` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `experience`
--

INSERT INTO `experience` (`id`, `name`, `status`) VALUES
(1, 'Sắp đi làm', 1),
(2, 'Dưới 1 năm', 1),
(3, '1 năm', 1),
(4, '2 năm', 1),
(5, '3 năm', 1),
(6, '4 năm', 1),
(7, '5 năm', 1),
(8, 'Trên 5 năm', 1);

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`id`, `seeker_id`, `job_id`, `status`, `created`) VALUES
(2, 65, 101, 1, '2025-04-04'),
(5, 65, 102, 1, '2025-04-10');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `user_id`, `content`, `created_at`, `status`) VALUES
(1, 65, 'User: Hoàng Tú Nguyễn - Email: tuhoangnguyen2003@gmail.com - Feedback: aaa', '2025-04-06 14:26:43', 1);

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`id`, `employer_id`, `seeker_id`, `status`, `created`) VALUES
(17, 1, 65, 1, '2025-04-18'),
(18, 1, 87, 1, '2025-04-22'),
(19, 88, 65, 0, '2025-04-23'),
(24, 9, 97, 1, '2025-07-17');

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `interview`
--

CREATE TABLE `interview` (
  `id` int(11) NOT NULL,
  `application_id` int(11) NOT NULL,
  `scheduled_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `interview_link` text NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `title` text NOT NULL,
  `description` text DEFAULT NULL,
  `description_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `required` text NOT NULL,
  `skills` longtext DEFAULT NULL,
  `primary_skills` longtext DEFAULT NULL,
  `secondary_skills` longtext DEFAULT NULL,
  `adverbs` longtext DEFAULT NULL,
  `adjectives` longtext DEFAULT NULL,
  `address` text NOT NULL,
  `location_id` int(11) NOT NULL,
  `salary` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `posted_at` datetime NOT NULL,
  `posted_expired` datetime NOT NULL,
  `experience_id` int(11) NOT NULL,
  `required_skills` text NOT NULL,
  `member` text NOT NULL,
  `work_type_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job`
--

INSERT INTO `job` (`id`, `employer_id`, `title`, `description`, `description_json`, `required`, `skills`, `primary_skills`, `secondary_skills`, `adverbs`, `adjectives`, `address`, `location_id`, `salary`, `status`, `posted_at`, `posted_expired`, `experience_id`, `required_skills`, `member`, `work_type_id`, `category_id`) VALUES
(31, 1, 'Nodejs Developer', 'We are seeking a skilled Node.js Developer to join our dynamic engineering team in Hanoi. You’ll design, develop, and maintain robust backend systems, focusing on creating scalable RESTful APIs using Node.js and Express. Your responsibilities include integrating MongoDB databases, optimizing server-side performance, and ensuring system security through best practices like JWT authentication and input validation. You’ll collaborate with frontend developers to deliver seamless integrations and participate in code reviews to maintain high-quality standards. Expect to work in an agile environment with tools like Git, Docker, and Postman, contributing to innovative projects that impact thousands of users.', '{\"job_description\":{\"overview\":\"We are seeking a skilled Node.js Developer to join our dynamic engineering team in Hanoi. You will play a key role in designing, developing, and maintaining robust backend systems, focusing on creating scalable RESTful APIs using Node.js and Express to support innovative projects impacting thousands of users.\",\"responsibilities\":[\"Design and develop scalable RESTful APIs using Node.js and Express.\",\"Integrate and manage MongoDB databases, ensuring efficient schema design, indexing, and aggregation pipelines.\",\"Optimize server-side performance through techniques like caching with Redis and asynchronous programming.\",\"Ensure system security by implementing best practices such as JWT authentication, input validation, and OWASP Top 10 mitigation.\",\"Collaborate with frontend developers to ensure seamless API integrations.\",\"Participate in code reviews to maintain high-quality standards and contribute to an agile development environment using tools like Git, Docker, and Postman.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 3 months\' salary.\",\"additional_benefits\":[\"Comprehensive health insurance and social benefits as per Vietnamese law.\",\"Annual team-building trips and company events.\",\"Opportunities for training and certification in Node.js and related technologies.\",\"Modern workspace with free snacks and coffee in Hanoi.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'Node.js Developer Application\'.\"}', 'Minimum 3 years of experience with Node.js and Express in production environments. Proficiency in MongoDB (schema design, indexing, and aggregation pipelines) and RESTful API development (including versioning and documentation with Swagger). Strong knowledge of asynchronous programming, error handling, and performance optimization (e.g., caching with Redis). Familiarity with security practices (e.g., OWASP Top 10 mitigation) and testing frameworks (e.g., Mocha, Jest). Excellent problem-solving skills and experience with Git workflows.', 'mongodb, redis, restful, security, production, performance, git, optimization, owasp, jest, mocha, api, caching', 'mongodb, redis, restful, security, production, performance, git, optimization, owasp, jest, mocha, api, caching', 'mongodb, restful, backend, security, frontend, systems, performance, git, docker, postman, jwt, agile', '', 'innovative, restful, dynamic, high, good, scalable, skilled, robust, agile, seamless', 'Hà Nội', 1, '15000000', 0, '2025-05-22 12:56:21', '2025-07-01 00:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(82, 88, 'Backend Software Engineer', 'We are looking for a skilled Backend Software Engineer to join our team. In this role, you will design, implement, and maintain robust backend systems for our applications. You will work with APIs, databases, and third-party services. You will be expected to optimize system performance and ensure scalability. You will work with technologies like Node.js, Express, and MongoDB. The ideal candidate will have experience in server-side programming, API integration, and handling large-scale systems in production environments.', '{\n  \"job_description\": {\n    \"overview\": \"We are seeking a talented Backend Software Engineer to join our team in Hanoi. You will design, implement, and maintain robust backend systems for our applications, ensuring high performance and scalability while working with cutting-edge technologies like Node.js, Express, and MongoDB.\",\n    \"responsibilities\": [\n      \"Design and implement robust backend systems using Node.js and Express.\",\n      \"Build and maintain RESTful APIs for seamless integration with frontend applications.\",\n      \"Work with databases like MongoDB and PostgreSQL to manage and optimize data storage and retrieval.\",\n      \"Integrate third-party services and APIs to enhance application functionality.\",\n      \"Optimize system performance and ensure scalability for large-scale production environments.\",\n      \"Collaborate with cross-functional teams to deliver high-quality software solutions.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"17,000,000 VND/month (net)\",\n    \"bonus\": \"Performance-based bonus, up to 2 months\' salary.\",\n    \"additional_benefits\": [\n      \"Health and social insurance as per Vietnamese regulations.\",\n      \"Annual company retreat and team-building activities.\",\n      \"Access to cloud certification programs (AWS, Azure).\",\n      \"Flexible remote work options after probation period.\"\n    ]\n  },\n  \"work_hours\": \"Monday to Friday, 8:30 AM - 5:30 PM\",\n  \"application_method\": \"Apply online via our career portal at www.company.com/careers with your CV and a brief introduction.\"\n}', 'Minimum 4 years of experience with Node.js and Express. Strong experience with databases (MongoDB, PostgreSQL). Experience in building RESTful APIs and optimizing server-side performance. Knowledge of cloud platforms like AWS or Azure is a plus.', 'postgresql, mongodb, restful, aws, cloud, performance', 'postgresql, mongodb, restful, aws, cloud, performance', 'mongodb, software engineer, software, backend, production, systems, performance, api', '', 'robust, ideal, apis, skilled, large', 'Hà Nội', 1, '17000000', 1, '2025-04-11 00:00:00', '2025-05-12 00:00:00', 4, 'Node.js, Express, MongoDB, PostgreSQL, RESTful API', '1', 1, 1),
(83, 1, 'UI Developer', 'As a UI Developer, you will focus on building intuitive and beautiful user interfaces using React.js. You will work with designers to create seamless user experiences and collaborate with backend developers to integrate APIs. Your tasks will include improving frontend performance, ensuring cross-browser compatibility, and implementing responsive web designs. If you enjoy working with cutting-edge frontend technologies and have a keen eye for design, we want you on our team!', '{\n  \"job_description\": {\n    \"overview\": \"We are looking for a passionate UI Developer to join our frontend team in Ho Chi Minh City. You will focus on building intuitive and visually stunning user interfaces using React.js, collaborating with designers and backend developers to deliver seamless user experiences.\",\n    \"responsibilities\": [\n      \"Develop intuitive and beautiful user interfaces using React.js, JavaScript, CSS, and HTML.\",\n      \"Collaborate with designers to transform wireframes and mockups into responsive web designs.\",\n      \"Integrate RESTful APIs with backend developers to ensure smooth data flow.\",\n      \"Optimize frontend performance for faster load times and better user experience.\",\n      \"Ensure cross-browser compatibility and implement mobile-first design principles.\",\n      \"Use Git for version control and participate in code reviews to maintain code quality.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"13,000,000 VND/month (net)\",\n    \"bonus\": \"Performance-based bonus, up to 1 month\'s salary.\",\n    \"additional_benefits\": [\n      \"Health insurance and social benefits as per Vietnamese law.\",\n      \"Yearly team outings and company events in Ho Chi Minh City.\",\n      \"Free access to online learning platforms for frontend development skills.\",\n      \"Modern office with a creative and collaborative environment.\"\n    ]\n  },\n  \"work_hours\": \"Monday to Friday, 9:00 AM - 6:00 PM\",\n  \"application_method\": \"Submit your CV and portfolio to hr@company.com with the subject \'UI Developer Application\'.\"\n}', 'Proficiency in React.js, JavaScript, CSS, HTML, and experience with frontend optimization. Experience in responsive design and mobile-first approaches. Familiarity with RESTful APIs and version control tools like Git.', 'javascript, html, css, restful, frontend, git, optimization, responsive design', 'javascript, html, css, restful, frontend, git, optimization, responsive design', 'backend, frontend, performance', '', 'backend, cross, responsive, browser, beautiful, apis, intuitive, keen, seamless', 'Hồ Chí Minh', 2, '13000000', 1, '2025-04-12 00:00:00', '2025-07-12 00:00:00', 3, 'React.js, JavaScript, CSS, HTML, Frontend Development', '1', 1, 1),
(84, 1, 'Cloud Solutions Architect', 'We are seeking an experienced Cloud Solutions Architect to design and implement scalable cloud solutions for our applications. You will be responsible for creating cloud infrastructure, setting up automated deployment pipelines, and ensuring high availability and fault tolerance of our systems. You will work with cloud platforms like AWS, Google Cloud, and Azure. The ideal candidate should have a deep understanding of cloud security, cost optimization, and best practices for cloud architecture.', NULL, 'Experience with AWS, Google Cloud, Azure, and cloud infrastructure design. Strong understanding of cloud security, cost optimization, and deployment strategies. Ability to collaborate with development and operations teams to implement cloud solutions.', 'aws, cloud, security, infrastructure, optimization', 'aws, cloud, security, infrastructure, optimization', 'aws, cloud, security, infrastructure, systems, optimization', '', 'cloud, high, good, experienced, scalable, deep, ideal, responsible', 'Đà Nẵng', 3, '20000000', 1, '2025-04-13 00:00:00', '2025-07-13 00:00:00', 6, 'AWS, Google Cloud, Azure, Cloud Architecture, DevOps', '1', 1, 6),
(85, 1, 'Frontend Engineer', 'Join our team as a Frontend Engineer, where you will be building dynamic, user-friendly web applications. You will be working with the design team to implement creative UI/UX features, focusing on performance, scalability, and usability. You will work with technologies such as React, HTML5, CSS3, and JavaScript. You should have a deep understanding of web performance and be able to implement optimizations and debugging for smooth user experience.', NULL, 'Experience with React.js, HTML5, CSS3, JavaScript, and frontend performance optimization. Strong understanding of web standards, cross-browser compatibility, and testing/debugging tools. Familiarity with version control systems like Git.', 'javascript, html5, frontend, systems, performance, git, optimization', 'javascript, html5, frontend, systems, performance, git, optimization', 'javascript, react, ui ux, html5, frontend, performance', '', 'able, dynamic, smooth, creative, friendly, deep', 'Hà Nội', 1, '12000000', 1, '2025-04-14 00:00:00', '2025-07-14 00:00:00', 2, 'React.js, JavaScript, HTML, CSS, Web Development', '1', 1, 1),
(86, 1, 'Mobile App Developer', 'We are looking for a Mobile App Developer to create high-quality applications for Android and iOS. You will be responsible for the development, testing, and deployment of mobile applications. You will use Flutter to create cross-platform applications and work with APIs to integrate data into the app. The ideal candidate should have experience building mobile applications and be comfortable working in an agile environment.', NULL, 'Proven experience with Flutter, Dart, and mobile app development. Knowledge of Android and iOS application lifecycle. Familiarity with mobile backend integration using APIs and cloud storage solutions.', 'dart, flutter, backend, mobile, ios, cloud', 'dart, flutter, backend, mobile, ios, cloud', 'flutter, mobile, ios, agile', '', 'mobile, cross, high, ideal, platform, agile, responsible, comfortable', 'Hồ Chí Minh', 2, '14000000', 1, '2025-04-15 00:00:00', '2025-07-15 00:00:00', 3, 'Flutter, Dart, Firebase, Mobile Development, API Integration', '1', 1, 1),
(87, 1, 'Security Engineer', 'As a Security Engineer, you will be responsible for securing our applications, infrastructure, and data. You will conduct security assessments, vulnerability scans, and threat analysis to identify and mitigate risks. You will also be tasked with ensuring compliance with security standards and best practices. The ideal candidate should have expertise in network security, cryptography, and ethical hacking practices.', NULL, 'Experience with security tools like Kali Linux, Metasploit, and Wireshark. Strong knowledge of network security protocols, cryptography, and vulnerability assessment techniques. Experience with cloud security and compliance standards like GDPR, HIPAA.', 'cloud, network, security, compliance, linux, vulnerability assessment, network security', 'cloud, network, security, compliance, linux, vulnerability assessment, network security', 'network, security engineer, security, infrastructure, compliance, network security', '', 'ethical, good, responsible, ideal', 'Đà Nẵng', 3, '16000000', 1, '2025-04-16 00:00:00', '2025-07-16 00:00:00', 2, 'Network Security, Ethical Hacking, Cryptography, Risk Assessment', '1', 4, 4),
(88, 1, 'Data Engineer', 'We are hiring a Data Engineer to help build and manage our data pipelines. You will be responsible for creating systems that collect, process, and analyze large datasets. You will work with big data tools such as Hadoop, Spark, and Kafka to ensure data is processed in real-time. The ideal candidate should have experience with data warehousing, ETL pipelines, and cloud-based data storage.', NULL, 'Experience with big data technologies like Hadoop, Spark, Kafka, and ETL processes. Proficiency in SQL and NoSQL databases. Ability to design and manage scalable data pipelines and work with cloud storage services like AWS S3 or Google Cloud Storage.', 'sql, nosql, hadoop, kafka, aws, cloud, etl', 'sql, nosql, hadoop, kafka, aws, cloud, etl', 'hadoop, kafka, data engineer, systems, etl', '', 'big, ideal, large, responsible, real', 'Hà Nội', 1, '17000000', 1, '2025-04-17 00:00:00', '2025-07-17 00:00:00', 4, 'Hadoop, Spark, Kafka, SQL, Data Warehousing', '1', 3, 5),
(89, 1, 'Artificial Intelligence Engineer', 'Join our team as an AI Engineer and work on cutting-edge artificial intelligence projects. You will build and optimize machine learning models for a variety of applications, such as computer vision, natural language processing, and recommendation systems. You will work with frameworks like TensorFlow, PyTorch, and Keras to create intelligent systems that learn and improve over time.', NULL, 'Strong knowledge of machine learning algorithms, deep learning, and AI frameworks like TensorFlow, Keras, and PyTorch. Experience with Python and data preprocessing. Ability to design, train, and deploy AI models for real-world applications.', 'python, tensorflow, pytorch, keras, deep learning, machine learning, ai', 'python, tensorflow, pytorch, keras, deep learning, machine learning, ai', 'tensorflow, pytorch, keras, computer vision, natural language processing, machine learning, ai engineer, ai, systems', '', 'artificial, intelligent, natural', 'Hồ Chí Minh', 2, '18000000', 1, '2025-04-18 00:00:00', '2025-07-18 00:00:00', 5, 'AI, TensorFlow, Keras, Deep Learning, Python', '1', 3, 3),
(90, 1, 'Blockchain Developer', 'As a Blockchain Developer, you will be responsible for developing blockchain-based applications, including smart contracts, dApps, and decentralized solutions. You will design and implement secure blockchain protocols and integrate them with backend systems. The ideal candidate will have a solid understanding of blockchain technology, cryptographic techniques, and decentralized systems.', NULL, 'Experience with blockchain platforms like Ethereum, Hyperledger, or Solana. Proficiency in Solidity and smart contract development. Understanding of consensus algorithms, cryptographic methods, and decentralized application (dApp) development.', 'blockchain, smart contract, solidity, ethereum', 'blockchain, smart contract, solidity, ethereum', 'backend, blockchain developer, blockchain, systems, smart contracts', '', 'decentralized, smart, secure, cryptographic, ideal, solid, responsible', 'Hà Nội', 1, '19000000', 1, '2025-04-19 00:00:00', '2025-07-19 00:00:00', 6, 'Blockchain, Solidity, Ethereum, Hyperledger, dApps', '1', 4, 7),
(91, 1, 'Game Developer', 'We are looking for a Game Developer to join our studio and help create immersive game experiences. You will work with Unity or Unreal Engine to develop game features, mechanics, and levels. You will collaborate with artists, designers, and other developers to create fun and engaging games. The ideal candidate should have experience with game engines, game physics, and multiplayer systems.', NULL, 'Experience with Unity or Unreal Engine. Knowledge of game physics, AI for games, and multiplayer systems. Strong C# or C++ programming skills. Experience in game design, gameplay mechanics, and user interface integration.', 'c++, c#, ai, game, unity, unreal, systems', 'c++, c#, ai, game, unity, unreal, systems', 'game developer, game, unity, unreal, systems', '', 'engaging, ideal, immersive, unreal', 'Hồ Chí Minh', 2, '16000000', 1, '2025-04-20 00:00:00', '2025-07-20 00:00:00', 2, 'Unity, Unreal Engine, C#, C++, Game Development', '1', 4, 8),
(92, 1, 'Java Developer', 'We are looking for a Java Developer to join our team. You will be responsible for building scalable backend systems using Java and Spring Boot. You will work closely with other developers to ensure the application meets performance and reliability standards. Your tasks will include creating RESTful APIs, optimizing the database, and ensuring secure and efficient data handling. If you are passionate about backend development and have a strong background in Java, we want to hear from you.', '{\n  \"job_description\": {\n    \"overview\": \"We are seeking a dedicated Java Developer to join our team in Hanoi. You will be responsible for building scalable backend systems using Java and Spring Boot, ensuring high performance and reliability while working on innovative projects.\",\n    \"responsibilities\": [\n      \"Develop scalable backend systems using Java and Spring Boot.\",\n      \"Design and implement RESTful APIs to support frontend applications.\",\n      \"Optimize database performance using SQL and Hibernate for efficient data handling.\",\n      \"Ensure application security by implementing best practices and secure coding techniques.\",\n      \"Collaborate with other developers to meet performance and reliability standards.\",\n      \"Participate in code reviews and contribute to improving development processes.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"16,000,000 VND/month (net)\",\n    \"bonus\": \"Performance-based bonus, up to 2 months\' salary.\",\n    \"additional_benefits\": [\n      \"Comprehensive health and social insurance as per Vietnamese law.\",\n      \"Annual company trip and team-building events.\",\n      \"Support for Java and Spring certification programs.\",\n      \"Modern workspace in Hanoi with a focus on collaboration.\"\n    ]\n  },\n  \"work_hours\": \"Monday to Friday, 8:30 AM - 5:30 PM\",\n  \"application_method\": \"Apply online via our career portal at www.company.com/careers with your CV and a brief introduction.\"\n}', 'Experience with Java, Spring Boot, Hibernate, and RESTful APIs. Proficiency in SQL and database optimization. Strong knowledge of application security and performance optimization techniques.', 'java, java spring, spring boot, sql, restful, security, database, performance, optimization, database optimization', 'java, java spring, spring boot, sql, restful, security, database, performance, optimization, database optimization', 'java, spring boot, restful, backend, reliability, database, systems, performance', 'closely', 'secure, restful, passionate, scalable, efficient, strong, responsible', 'Hà Nội', 1, '16000000', 1, '2025-04-21 00:00:00', '2025-07-21 00:00:00', 4, 'Java, Spring Boot, Hibernate, RESTful API', '1', 1, 1),
(93, 1, 'C++ Software Engineer', 'Join our team as a C++ Software Engineer! You will work on developing high-performance applications, focusing on system-level programming, algorithms, and data structures. You will contribute to the design and optimization of real-time applications and help improve the performance of complex software solutions. The ideal candidate will have experience with C++ development, understanding memory management, and working with low-latency systems.', NULL, 'Experience with C++ programming and system-level development. Strong understanding of algorithms, data structures, and memory management. Experience with performance optimization and debugging tools.', 'c++, performance, optimization', 'c++, performance, optimization', 'c++, software engineer, software, systems, performance, optimization', '', 'high, low, ideal, complex, real', 'Hồ Chí Minh', 2, '17000000', 1, '2025-04-22 00:00:00', '2025-07-22 00:00:00', 5, 'C++, Algorithms, System-level Programming, Real-time Applications', '1', 1, 1),
(94, 1, 'PHP Developer', 'We are seeking a PHP Developer to work on developing server-side web applications. You will work with various PHP frameworks such as Laravel and Symfony to build scalable and secure web solutions. Your responsibilities will include building new features, optimizing database queries, and maintaining backend systems. You should have experience in both object-oriented programming and modern web development practices.', NULL, 'Experience with PHP, Laravel, Symfony, MySQL, and RESTful APIs. Strong understanding of web security best practices and performance optimization.', 'php, laravel, symfony, mysql, restful, security, performance, optimization', 'php, laravel, symfony, mysql, restful, security, performance, optimization', 'php, laravel, symfony, backend, database, systems', '', 'secure, scalable, php, modern, new', 'Đà Nẵng', 3, '15000000', 1, '2025-04-23 00:00:00', '2025-07-23 00:00:00', 3, 'PHP, Laravel, Symfony, MySQL, RESTful API', '1', 1, 1),
(95, 1, '.NET Developer', 'We are looking for a .NET Developer to build and maintain high-quality applications using .NET technologies. You will work on both web and desktop applications, collaborating with a team of developers to deliver functional and robust software. Your role will involve working with C#, ASP.NET Core, and SQL Server to build scalable systems and APIs. You should have strong problem-solving skills and the ability to optimize software performance.', NULL, 'Experience with C#, ASP.NET Core, SQL Server, and building web APIs. Knowledge of OOP principles and design patterns. Strong debugging and performance optimization skills.', 'c#, sql, sql server, oop, performance, optimization', 'c#, sql, sql server, oop, performance, optimization', 'c#, sql, sql server, software, systems, performance', '', 'high, scalable, functional, strong, robust', 'Hà Nội', 1, '14500000', 1, '2025-04-24 00:00:00', '2025-07-24 00:00:00', 4, '.NET, C#, ASP.NET Core, SQL Server', '1', 1, 1),
(96, 1, 'Python Developer', 'Join us as a Python Developer and be responsible for designing and developing backend applications. You will work with frameworks such as Django and Flask to build robust APIs and services. Your tasks will include database management, working with cloud platforms, and implementing machine learning models. If you have a passion for Python and enjoy working in an agile environment, this is the job for you.', NULL, 'Experience with Python, Django, Flask, and PostgreSQL. Familiarity with cloud platforms like AWS and Google Cloud. Knowledge of machine learning libraries is a plus.', 'python, django, flask, postgresql, machine learning, aws, cloud', 'python, django, flask, postgresql, machine learning, aws, cloud', 'python, django, flask, machine learning, backend, cloud, database, agile', '', 'robust, agile, responsible', 'Hồ Chí Minh', 2, '16000000', 1, '2025-04-25 00:00:00', '2025-07-25 00:00:00', 3, 'Python, Django, Flask, PostgreSQL, AWS', '1', 1, 1),
(97, 1, 'React.js Developer', 'We are looking for a skilled React.js Developer to join our frontend team. You will be responsible for building interactive and dynamic user interfaces, focusing on performance, accessibility, and cross-browser compatibility. You will work closely with the design and backend teams to ensure seamless integration of frontend and backend services. If you are passionate about creating great user experiences and are familiar with the latest frontend technologies, we want you on our team.', NULL, 'Experience with React.js, Redux, JavaScript, HTML5, and CSS3. Strong understanding of web performance optimization and testing. Familiarity with RESTful APIs and working with backend teams.', 'javascript, html5, restful, backend, performance, optimization', 'javascript, html5, restful, backend, performance, optimization', 'backend, frontend, performance', 'closely', 'late, responsible, cross, great, dynamic, familiar, browser, passionate, skilled, interactive, seamless', 'Đà Nẵng', 3, '14000000', 1, '2025-04-26 00:00:00', '2025-07-26 00:00:00', 2, 'React.js, JavaScript, Redux, HTML5, CSS3, Web Development', '1', 1, 1),
(98, 1, 'Mobile Software Developer', 'We are hiring a Mobile Software Developer to create cutting-edge mobile applications. You will be responsible for designing and developing Android and iOS apps using Flutter and Dart. Your tasks will include integrating mobile applications with APIs, improving app performance, and ensuring a seamless user experience. You will also work with the team to develop features and ensure that the applications meet both user and business needs.', NULL, 'Experience with Flutter, Dart, Firebase, and mobile app development. Familiarity with RESTful APIs and third-party mobile integrations.', 'dart, flutter, firebase, restful, mobile', 'dart, flutter, firebase, restful, mobile', 'dart, flutter, software, mobile, ios, performance', '', 'apis, mobile, responsible, seamless', 'Hà Nội', 1, '15500000', 1, '2025-04-27 00:00:00', '2025-07-27 00:00:00', 3, 'Flutter, Dart, Firebase, Mobile Development, API Integration', '1', 1, 1),
(99, 1, 'Game Developer', 'We are looking for a talented Game Developer to help create immersive and engaging games. You will be responsible for building the game mechanics, designing levels, and creating interactive experiences for players. You will work with game engines like Unity or Unreal Engine to develop games for PC, console, and mobile platforms. The ideal candidate should have strong C# or C++ skills and be familiar with game development principles.', NULL, 'Experience with Unity or Unreal Engine, C# or C++. Knowledge of game physics, AI for games, and multiplayer systems. Ability to develop game mechanics and levels for a variety of game genres.', 'c++, c#, ai, game, unity, unreal, systems', 'c++, c#, ai, game, unity, unreal, systems', 'c++, c#, mobile, game developer, game, unity, unreal', '', 'responsible, familiar, unreal, talented, ideal, strong, interactive, engaging', 'Hồ Chí Minh', 2, '18000000', 1, '2025-04-28 00:00:00', '2025-07-28 00:00:00', 4, 'Unity, Unreal Engine, C#, C++, Game Development', '1', 1, 8),
(100, 1, 'Automation Tester', 'We are looking for an experienced Automation Tester to join our QA team. You will be responsible for automating test cases, performing regression testing, and ensuring that our software meets the highest quality standards. You will work with testing frameworks like Selenium and Appium, and collaborate with the development team to integrate tests into the CI/CD pipeline. The ideal candidate will have experience in test automation and knowledge of software testing best practices.', NULL, 'Experience with test automation tools like Selenium, Appium, and JUnit. Strong knowledge of CI/CD pipelines and integration testing. Experience with web and mobile application testing.', 'automation, ci cd, mobile, selenium, junit', 'automation, ci cd, mobile, selenium, junit', 'automation, software, ci cd, tester, qa, pipeline, selenium', '', 'high, good, experienced, ideal, responsible', 'Đà Nẵng', 3, '14500000', 1, '2025-04-29 00:00:00', '2025-07-29 00:00:00', 2, 'Selenium, Appium, JUnit, CI/CD, Test Automation', '1', 2, 2),
(101, 1, 'Ruby on Rails Developer', 'Join our team as a Ruby on Rails Developer! You will work on building web applications using the Ruby on Rails framework. Your primary responsibility will be creating server-side logic and ensuring high performance and scalability. You will work closely with frontend developers to deliver seamless integrations and contribute to code quality through reviews and testing. If you are passionate about Ruby and web development, this is the job for you.', NULL, 'Experience with Ruby on Rails, SQL, and RESTful API development. Strong understanding of MVC architecture, performance optimization, and best practices for building scalable web applications.', 'ruby, ruby on rails, sql, restful, performance, optimization, api', 'ruby, ruby on rails, sql, restful, performance, optimization, api', 'ruby, ruby on rails, frontend, performance', 'closely', 'passionate, high, primary, seamless', 'Hà Nội', 1, '15000000', 1, '2025-04-30 00:00:00', '2025-07-30 00:00:00', 4, 'Ruby on Rails, SQL, RESTful API, Web Development', '1', 1, 1),
(102, 1, 'Lập trình viên Java', '<p>Lập trình viên Java</p>', '{\n  \"job_description\": {\n    \"overview\": \"We are looking for an experienced Java Developer to join our team in Ho Chi Minh City. You will be responsible for developing high-performance backend applications using Java and Spring, leveraging modern technologies like AWS, Docker, and CI/CD pipelines to deliver scalable solutions.\",\n    \"responsibilities\": [\n      \"Develop and maintain backend applications using Java and Spring Framework.\",\n      \"Design and implement RESTful APIs to support various business requirements.\",\n      \"Manage and optimize PostgreSQL databases for efficient data storage and retrieval.\",\n      \"Deploy applications on AWS using Docker containers and CI/CD pipelines.\",\n      \"Collaborate with cross-functional teams to ensure seamless integration and delivery.\",\n      \"Participate in code reviews and ensure adherence to coding standards and best practices.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"50,000,000 VND/month (net)\",\n    \"bonus\": \"Performance-based bonus, up to 4 months\' salary.\",\n    \"additional_benefits\": [\n      \"Premium health insurance and social benefits as per Vietnamese law.\",\n      \"Annual international company trip and team-building activities.\",\n      \"Support for AWS and Docker certification programs.\",\n      \"Flexible work-from-home options after probation.\"\n    ]\n  },\n  \"work_hours\": \"Monday to Friday, 9:00 AM - 6:00 PM\",\n  \"application_method\": \"Submit your CV and cover letter via email to hr@company.com with the subject \'Java Developer Application - HCMC\'.\"\n}', 'Java, Spring, SQL, Postgresql, AWS, Docker, CI/CD', 'java, java spring, sql, postgresql, aws, ci cd, docker', 'java, java spring, sql, postgresql, aws, ci cd, docker', 'java', '', '', 'Thành phố Hồ Chí Minh', 2, '50000000', 1, '2025-04-03 12:07:13', '2025-04-24 00:00:00', 4, 'Java, Spring, SQL, Postgresql, AWS, Docker, CI/CD', '3', 1, 1),
(110, 1, 'Software Engineer', NULL, '{\"job_description\":{\"overview\":\"As a Software Engineer, you will be responsible for the development and maintenance of scalable web applications. You will work closely with product managers and designers to deliver high-quality products.\",\"responsibilities\":[\"Design, develop, and maintain backend and frontend applications.\",\"Collaborate with cross-functional teams to define, design, and ship new features.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"Premium healthcare insurance.\",\"Flexible working hours and remote work options.\"]},\"work_hours\":\"8:00 - 17:00\",\"application_method\":\"\"}', 'Có kinh nghiệm 2 năm về Software', 'software', 'software', '', '', '', 'Tòa nhà Bitesco Quận 1', 2, '15,000,000 / tháng (Gross)', 1, '2025-04-13 16:38:10', '2025-04-30 00:00:00', 1, 'Java, SpringBoot, Mysql, PostgreSQL, Docker, CI/CD', '1', 1, 1),
(112, 1, 'Java Backend Engineer', NULL, '{\"job_description\":{\"overview\":\"As a Software Engineer, you will be responsible for the development and maintenance of scalable web applications. You will work closely with product managers and designers to deliver high-quality products.\",\"responsibilities\":[\"Design, develop, and maintain backend and frontend applications.\",\"Collaborate with cross-functional teams to define, design, and ship new features.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"Premium healthcare insurance.\",\"Flexible working hours and remote work options.\"]},\"work_hours\":\"8:00 - 17:00\",\"application_method\":\"\"}', 'Bachelor\'s degree in Computer Science or related field. Solid understanding of data structures, algorithms, and software design principles.', 'software', 'software', '', '', '', 'Phường Chánh Mỹ, Thành phố Thủ Dầu Một, Tỉnh Bình Dương', 2, '15,000,000 / tháng (Gross)', 1, '2025-04-13 16:45:32', '2025-04-30 00:00:00', 5, 'Java, SpringBoot, Mysql, PostgreSQL, Docker, CI/CD', '1', 1, 1),
(135, 1, 'PHP Developer', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-04-22 09:10:02', '2025-07-01 00:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(139, 1, 'PHP Developer', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-04-23 08:36:56', '2025-07-01 00:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(140, 1, 'Fullstack Developer', NULL, '{\"job_description\":{\"overview\":\"Lập trình API \",\"responsibilities\":[\"Lập trình API \",\"Lập trình API \",\"Lập trình API \"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"Không có\"]},\"work_hours\":\"9:00 - 18:00\",\"application_method\":\"\"}', 'Tốt nghiệp Đại học', '', '', '', '', '', 'Phường Chánh Mỹ, Thành phố Thủ Dầu Một, Tỉnh Bình Dương', 2, '10,000,000 / tháng', 1, '2025-04-23 08:38:52', '2025-04-29 17:00:00', 2, '.NET, C#, Golang, CI/CD, AWS', '2', 1, 3),
(141, 1, 'PHP Developer', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-04-27 14:47:49', '2025-07-01 00:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(142, 2, 'PHP Developer', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-04-28 01:30:54', '2025-07-01 00:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(143, 1, 'aaaa', NULL, '{\"job_description\":{\"overview\":\"aaa\",\"responsibilities\":[\"aaaa\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"aaa\"]},\"work_hours\":\"8:00 - 17:00\",\"application_method\":\"\"}', 'aaa', '', '', '', '', '', 'Thành phố Hồ Chí Minh', 2, '15,000,000 / tháng (Gross)', 1, '2025-04-28 01:44:50', '2025-04-29 17:00:00', 1, 'Java, Spring, SQL, Postgresql, AWS, Docker, CI/CD', '1', 2, 1),
(195, 1, 'Java 3', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-06-07 19:38:32', '2025-07-01 07:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(235, 1, 'Java 5', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-06-12 12:40:57', '2025-07-01 07:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(239, 1, 'Backend Developer', 'Yêu cầu chuyên môn:\nCó kinh nghiệm 1–3 năm phát triển Backend sử dụng Java (Spring Boot) hoặc tương đương.\n\nThành thạo RESTful API, JWT, và các phương pháp xác thực người dùng.\n\nCó hiểu biết về ORM như JPA/Hibernate, hệ cơ sở dữ liệu MySQL/PostgreSQL.\n\nQuen thuộc với Redis, Kafka hoặc các công nghệ hỗ trợ hiệu năng và message queue.\n\nBiết sử dụng Git, có kinh nghiệm làm việc với mô hình CI/CD, Docker là lợi thế.\n\nTư duy logic, giải quyết vấn đề tốt, có khả năng viết code sạch và dễ bảo trì.\nƯu tiên có:\nKinh nghiệm làm việc với microservices.\n\nĐã từng dùng Redis cache, RabbitMQ, hoặc triển khai Docker/Kubernetes.\n\nHiểu biết về các nguyên tắc thiết kế hệ thống: CQRS, event-driven, clean architecture...\n\nTrách nhiệm công việc:\n• Phân tích, thiết kế và phát triển các dịch vụ backend, API phục vụ frontend/mobile apps.\n• Tối ưu hóa truy vấn, xử lý dữ liệu lớn, đảm bảo hệ thống ổn định, bảo mật và hiệu năng.\n• Tham gia vào việc code review, viết test và bảo trì mã nguồn.\n• Tích hợp và kết nối với các dịch vụ bên thứ ba: payment, auth, notifications,...\n• Làm việc chặt chẽ với frontend, QA và DevOps để đảm bảo tiến độ và chất lượng.\n• Viết tài liệu kỹ thuật và hỗ trợ triển khai hệ thống khi cần.', '{\"job_description\":{\"overview\":\"Yêu cầu chuyên môn:\\nCó kinh nghiệm 1–3 năm phát triển Backend sử dụng Java (Spring Boot) hoặc tương đương.\\n\\nThành thạo RESTful API, JWT, và các phương pháp xác thực người dùng.\\n\\nCó hiểu biết về ORM như JPA/Hibernate, hệ cơ sở dữ liệu MySQL/PostgreSQL.\\n\\nQuen thuộc với Redis, Kafka hoặc các công nghệ hỗ trợ hiệu năng và message queue.\\n\\nBiết sử dụng Git, có kinh nghiệm làm việc với mô hình CI/CD, Docker là lợi thế.\\n\\nTư duy logic, giải quyết vấn đề tốt, có khả năng viết code sạch và dễ bảo trì.\\nƯu tiên có:\\nKinh nghiệm làm việc với microservices.\\n\\nĐã từng dùng Redis cache, RabbitMQ, hoặc triển khai Docker/Kubernetes.\\n\\nHiểu biết về các nguyên tắc thiết kế hệ thống: CQRS, event-driven, clean architecture...\",\"responsibilities\":[\"Phân tích, thiết kế và phát triển các dịch vụ backend, API phục vụ frontend/mobile apps.\",\"Tối ưu hóa truy vấn, xử lý dữ liệu lớn, đảm bảo hệ thống ổn định, bảo mật và hiệu năng.\",\"Tham gia vào việc code review, viết test và bảo trì mã nguồn.\",\"Tích hợp và kết nối với các dịch vụ bên thứ ba: payment, auth, notifications,...\",\"Làm việc chặt chẽ với frontend, QA và DevOps để đảm bảo tiến độ và chất lượng.\",\"Viết tài liệu kỹ thuật và hỗ trợ triển khai hệ thống khi cần.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"13th month salary and annual performance-based bonus.\"]},\"work_hours\":\"8:00 - 17:00\",\"application_method\":\"\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', 'java, java spring, spring boot, mysql, postgresql, redis, restful, kafka, backend, ci cd, mobile, devops, qa, frontend, git, docker, kubernetes, rabbitmq, jwt, api', 'trì', 'mobile, thành, restful, clean', 'Thành phố Hồ Chí Minh', 2, '15,000,000 / tháng (Gross)', 1, '2025-06-29 14:49:20', '2025-07-07 00:00:00', 5, 'Java, Spring, SQL, Postgresql, AWS, Docker, CI/CD', '1', 1, 1);
INSERT INTO `job` (`id`, `employer_id`, `title`, `description`, `description_json`, `required`, `skills`, `primary_skills`, `secondary_skills`, `adverbs`, `adjectives`, `address`, `location_id`, `salary`, `status`, `posted_at`, `posted_expired`, `experience_id`, `required_skills`, `member`, `work_type_id`, `category_id`) VALUES
(242, 1, 'Java Developer', 'Chúng tôi đang tìm kiếm một Java Backend Developer có kinh nghiệm để tham gia vào đội ngũ phát triển hệ thống backend của công ty. Bạn sẽ chịu trách nhiệm thiết kế, phát triển và tối ưu các hệ thống backend hiệu năng cao, bảo mật và dễ mở rộng.\n\nTrách nhiệm công việc:\n• Phân tích, thiết kế và phát triển các tính năng backend sử dụng Java (Spring Boot).\n• Xây dựng và triển khai RESTful APIs phục vụ frontend/mobile/web services.\n• Tích hợp với hệ thống CSDL như MySQL, PostgreSQL, MongoDB,...\n• Tham gia thiết kế kiến trúc hệ thống, tối ưu hiệu suất và khả năng mở rộng.\n• Viết unit tests, đảm bảo chất lượng mã nguồn và tuân thủ coding convention.\n• Tham gia review code và hỗ trợ các thành viên khác trong team.\n• Triển khai, cấu hình và giám sát dịch vụ backend trên môi trường dev/staging/production.\n• Làm việc chặt chẽ với team BA, frontend, QA để đảm bảo tính năng hoạt động chính xác.\n• Báo cáo tiến độ và tình trạng công việc định kỳ.', '{\"job_description\":{\"overview\":\"Chúng tôi đang tìm kiếm một Java Backend Developer có kinh nghiệm để tham gia vào đội ngũ phát triển hệ thống backend của công ty. Bạn sẽ chịu trách nhiệm thiết kế, phát triển và tối ưu các hệ thống backend hiệu năng cao, bảo mật và dễ mở rộng.\",\"responsibilities\":[\"Phân tích, thiết kế và phát triển các tính năng backend sử dụng Java (Spring Boot).\",\"Xây dựng và triển khai RESTful APIs phục vụ frontend/mobile/web services.\",\"Tích hợp với hệ thống CSDL như MySQL, PostgreSQL, MongoDB,...\",\"Tham gia thiết kế kiến trúc hệ thống, tối ưu hiệu suất và khả năng mở rộng.\",\"Viết unit tests, đảm bảo chất lượng mã nguồn và tuân thủ coding convention.\",\"Tham gia review code và hỗ trợ các thành viên khác trong team.\",\"Triển khai, cấu hình và giám sát dịch vụ backend trên môi trường dev/staging/production.\",\"Làm việc chặt chẽ với team BA, frontend, QA để đảm bảo tính năng hoạt động chính xác.\",\"Báo cáo tiến độ và tình trạng công việc định kỳ.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"13th month salary and annual performance-based bonus.\",\"additional_benefits\":[\"13th month salary and annual performance-based bonus.\"]},\"work_hours\":\"8:00 - 17:00\",\"application_method\":\"\"}', 'Tốt nghiệp chuyên ngành CNTT, Khoa học máy tính hoặc ngành liên quan.\n\nCó từ 1–3 năm kinh nghiệm phát triển ứng dụng backend với Java, ưu tiên Spring Boot.\n\nHiểu rõ về mô hình MVC, kiến trúc Microservices là lợi thế.\n\nThành thạo làm việc với cơ sở dữ liệu quan hệ (SQL) và NoSQL.\n\nCó kinh nghiệm sử dụng các công cụ quản lý source code như Git.\n\nƯu tiên ứng viên hiểu CI/CD, Docker, Kafka, Redis, Elasticsearch,...\n\nKỹ năng giải quyết vấn đề tốt, tư duy logic, teamwork và chủ động trong công việc.\n\nCó khả năng đọc hiểu tài liệu kỹ thuật tiếng Anh.', 'java, spring boot, sql, nosql, redis, elasticsearch, kafka, backend, ci cd, git, docker', 'java, spring boot, sql, nosql, redis, elasticsearch, kafka, backend, ci cd, git, docker', 'java, java spring, spring boot, mysql, postgresql, mongodb, restful, backend developer, backend, mobile, qa, frontend, production', '', 'restful, triển', 'Thành phố Hồ Chí Minh', 2, '15,000,000 / tháng (Gross)', 1, '2025-06-29 15:59:58', '2025-06-30 00:00:00', 4, 'Java, Spring, SQL, Postgresql, AWS, Docker, CI/CD', '1', 1, 1),
(243, 1, 'Java 5', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', 'php, mongodb, restful, production, api', 'php, mongodb, restful, production, api', '', '', '', 'Hà Nội', 1, '15000000', 1, '2025-07-04 21:45:12', '2025-07-01 07:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(244, 1, 'AI Engineer', 'Vị trí AI Engineer đóng vai trò trung tâm trong việc xây dựng và triển khai các mô hình AI nhằm nâng cao hiệu suất và khả năng tự động hóa cho các sản phẩm công nghệ của công ty.\n\nVí dụ: Xây dựng hệ thống recommendation sử dụng deep learning cho nền tảng thương mại điện tử.\n\n\nTrách nhiệm công việc:\n• - Thiết kế, huấn luyện và đánh giá các mô hình AI phục vụ các bài toán cụ thể (dự đoán, phân loại, gợi ý...).\n• - Làm việc với team backend để tích hợp mô hình AI vào sản phẩm.\n• - Phân tích và xử lý dữ liệu huấn luyện.\n• - Theo dõi hiệu suất mô hình sau triển khai và đề xuất cải tiến.\n• - Viết tài liệu kỹ thuật, nghiên cứu công nghệ mới.', '{\"job_description\":{\"overview\":\"Vị trí AI Engineer đóng vai trò trung tâm trong việc xây dựng và triển khai các mô hình AI nhằm nâng cao hiệu suất và khả năng tự động hóa cho các sản phẩm công nghệ của công ty.\\n\\nVí dụ: Xây dựng hệ thống recommendation sử dụng deep learning cho nền tảng thương mại điện tử.\\n\",\"responsibilities\":[\"- Thiết kế, huấn luyện và đánh giá các mô hình AI phục vụ các bài toán cụ thể (dự đoán, phân loại, gợi ý...).\",\"- Làm việc với team backend để tích hợp mô hình AI vào sản phẩm.\",\"- Phân tích và xử lý dữ liệu huấn luyện.\",\"- Theo dõi hiệu suất mô hình sau triển khai và đề xuất cải tiến.\",\"- Viết tài liệu kỹ thuật, nghiên cứu công nghệ mới.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"\",\"additional_benefits\":[]},\"work_hours\":\"\",\"application_method\":\"\"}', '- Tốt nghiệp ngành Khoa học máy tính, Kỹ thuật phần mềm hoặc tương đương.\n- Tối thiểu 2 năm kinh nghiệm làm việc với các mô hình Machine Learning/Deep Learning.\n- Thành thạo Python và các thư viện ML như TensorFlow, PyTorch, scikit-learn.\n- Có kinh nghiệm triển khai mô hình AI vào môi trường sản phẩm thực tế (production).\n- Hiểu biết về xử lý dữ liệu lớn (big data) và tối ưu hóa mô hình.\n', 'python, tensorflow, pytorch, scikit-learn, deep learning, machine learning, ai, production', 'python, tensorflow, pytorch, scikit-learn, deep learning, machine learning, ai, production', 'deep learning, backend, ai engineer, ai', '', '', 'HCM', 2, '15000000', 1, '2025-07-04 21:45:52', '2025-07-26 00:00:00', 3, 'Python', '1', 2, 22),
(245, 1, 'Technical Manager (Java/NodeJS)', 'Participate in digital transformation projects of SmartOSC’s clients in the globe. Lead and manage a team of 15+ developers and technical staff in a global project. Oversee the technical design and architecture of software systems, ensuring scalability, security, and performance. Collaborate closely with clients to gather requirements, provide solutions, and manage technical expectations. Perform code reviews, provide mentorship to team members, and foster technical excellence. Ensure project milestones and deliverables are met on time and within budget. Coordinate with cross-functional teams including project managers, BA, tester, and DevOps. Identify opportunities for process improvement and drive engineering best practices. Stay up-to-date with industry trends and emerging technologies relevant to the business. Support post-sales solution implementation (if needed) to ensure success and satisfaction.', '{\n  \"job_description\": {\n    \"overview\": \"Participate in digital transformation projects of SmartOSC’s clients in the globe. Oversee the technical design and architecture of software systems, ensuring scalability, security, and performance. Oversee the technical design and architecture of software systems, ensuring scalability, security, and performance. Collaborate closely with clients to gather requirements, provide solutions, and manage technical expectations. Perform code reviews, provide mentorship to team members, and foster technical excellence.\",\n    \"responsibilities\": [\n      \"Lead and manage a team of 15+ developers and technical staff in a global project.\",\n      \"Ensure project milestones and deliverables are met on time and within budget.\",\n      \"Coordinate with cross-functional teams including project managers, BA, tester, and DevOps.\",\n      \"Identify opportunities for process improvement and drive engineering best practices.\",\n      \"Stay up-to-date with industry trends and emerging technologies relevant to the business.\",\n      \"Support post-sales solution implementation (if needed) to ensure success and satisfaction.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"Attractive salary package: up to USD 3500/month. Salary review twice a year.\",\n    \"bonus\": \"Performance-based bonus, up to 3 months\' salary.\",\n    \"additional_benefits\": [\n      \"Premium health care.\",\n      \"Working in One of the largest digital transformation agencies – A professional English environment Free English, Japanese, and professional training packages.\",\n      \"Firm’s Certified Qualifications Sponsorship for career development.\",\n      \"Annual company trip inside or outside Vietnam.\"\n    ]\n  },\n  \"work_hours\": \"Flexible working hour. Monday - Friday (from 09:00 to 18:00)\",\n  \"application_method\": \"Submit your CV and cover letter via email to smartosc@company.com with the subject \'Technical Manager (Java/NodeJS)\'.\"\n}', 'Proven experience (8+ years) in software development, with a strong focus on either Java or NodeJS. Solid background in system design, software architecture, and performance optimization. Previous experience in a technical leadership or managerial role. Good verbal and written English communication skills. Experience working with AWS cloud platform. Familiarity with microservices architecture, containerization (Docker/Kubernetes). Knowledge of DevOps practices and CI/CD tools. Good understanding of Agile/Scrum methodologies.', 'java, nodejs, aws, software, ci cd, devops, cloud, platform, performance, optimization, docker, kubernetes, agile, scrum', 'java, nodejs, aws, software, ci cd, devops, cloud, platform, performance, optimization, docker, kubernetes, agile, scrum', 'software, devops, security, tester, systems, performance', 'closely', 'technical, cross, relevant, good, global, sales, functional, digital, post', 'Hà Nội', 1, '8000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 7, 'Node.js, Java, AWS cloud, Docker/Kubernetes, CI/CD tools', '1', 1, 90),
(246, 1, 'Full Stack Developer PHP, Python, JavaScript', 'Develop informational websites on WordPress and Shopify platforms. Developing small applications for businesses using PHP, Python... Participate in developing enterprise management modules (ERP) such as warehouse, logistics, CRM, sales... Install and configure apps and plugins. Write APIs to get sales data from shopify to SQL server database to serve inventory monitoring and create other management reports.', '{\"job_description\":{\"overview\":/\"Develop informational websites on WordPress and Shopify platforms. Developing small applications for businesses using PHP, Python...\",\"responsibilities\":[\"Participate in developing enterprise management modules (ERP) such as warehouse, logistics, CRM, sales...\",\"Install and configure apps and plugins.\",\"Write APIs to get sales data from shopify to SQL server database to serve inventory monitoring and create other management reports.\"]},\"benefits\":{\"salary_range\":/\"15 – 20 million/month for senior.\",\"bonus\":/\"Up to 30 million for lead with backend and database experience.\",\"additional_benefits\":[\"High career advancement opportunities.\",\"Full benefits according to labor law (social insurance, holidays, 13th month salary, bonuses, company trips, etc.), along with many additional attractive benefits.\"]},\"work_hours\":/\"Monday to Friday (8:00 - 17:00), and 2 Saturday mornings per month.\",\"application_method\":/\"Submit your CV and cover letter via email to maxbiocare@company.com with the subject \'Full Stack Developer PHP, Python, JavaScript\'.\"}', 'Male / female from 23 years old and have at least 2 years of FULL time experience in developing websites or applications on Wordpress platform. Good experience and knowledge of PHP (and/or Python), JavaScript, React, VueJS, AngularJS... Solid front-end with HTML5, CSS3, JavaScript, Liquid and Rest API, PHP, and database management (MySQL, SQL server...). GIT source code management. Knowing how to design and operate MySQL or SQL Server databases will be an advantage.', 'python, javascript, php, react, vuejs, sql, mysql, sql server, html5, platform, database, git, rest api, api, wordpress', 'python, javascript, php, react, vuejs, sql, mysql, sql server, html5, platform, database, git, rest api, api, wordpress', 'python, php, sql, sql server, sales, database, monitoring, wordpress, shopify', '', 'wordpress, small, shopify, informational', 'Hà Nội', 1, '15000000 - 30000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 4, 'PHP, Python, Frontend, database management, Wordpress', '1', 1, 3),
(247, 2, 'Fresher Developer', 'Study and participate in the Fresher training program built by a team of more than 100 experienced programming engineers, project managers, and training experts and successfully implemented through 6 recruitment seasons. After only 2 months of training at SystemEXE in the form of On Job Training (OJT), you will perfect your programming skills as well as soft skills:\r\nAbout programming skills: Master programming skills as well as software testing skills, project management tools, and practice theory through real projects.\r\nAbout soft skills: Get expert guidance on working style, professional behavior of employees, and necessary soft skills.\r\nDirectly participate in strategic projects and have the opportunity to become an official programmer at SystemEXE Vietnam.', '{\"job_description\":{\"overview\":/\"Study and participate in the Fresher training program built by a team of more than 100 experienced programming engineers, project managers, and training experts and successfully implemented through 6 recruitment seasons.\",\"responsibilities\":[\"About programming skills: Master programming skills as well as software testing skills, project management tools, and practice theory through real projects.\",\"About soft skills: Get expert guidance on working style, professional behavior of employees, and necessary soft skills.\",\"Directly participate in strategic projects and have the opportunity to become an official programmer at SystemEXE Vietnam.\"]},\"benefits\":{\"salary_range\":/\"Only study, no work, receive training allowance: Gross 12,000,000 VND/month.\",\"bonus\":/\"Travel expenses support for candidates from areas outside the South. After signing an official contract, summer bonus, 13th month salary, profit-based bonus at the end of the year,...\",\"additional_benefits\":[\"Full participation in social insurance, health insurance, unemployment insurance 100% of basic salary.\",\"12 days off + 1 day off for birthday. Gasoline and parking allowance.\", \"Regular awards: Effort award, excellent employee award, promising employee award, successful project award,...\"]},\"work_hours\":/\"Monday - Friday (from 08:00 to 17:00)\",\"application_method\":/\"Please complete the registration process by filling in the information at https://fresher.system-exe.com.vn.\"}', 'Can participate in training/work full-time from Monday to Friday. About to graduate from University or have just graduated within 1 year majoring in IT or related majors. Quick, careful, eager to learn, highly responsible. Priority is given to candidates with good academic performance, candidates who know Japanese/English are an advantage. Priority is given to candidates who participate in social and community activities.', 'performance', 'performance', 'software', 'directly, successfully', 'strategic, official, fresh, experienced, soft, professional, necessary, expert, real', 'TPHCM', 2, '12000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 2, 'Not required', '2', 1, 3),
(248, 2, 'Senior Java Developer - 3I094', 'Participate in developing and playing an important role in MSB’s key digital transformation projects such as BPM Risk - building a unified digital lending platform from end to end; Magnet - a 360-degree data platform to enhance customer experience; Risk STP - a highly automated risk management system... and many other projects. Participate in clarifying business operations, designing solutions, developing and upgrading systems as required, ensuring projects are implemented effectively. Participate in reviewing designs, reviewing codes, optimizing systems to meet high traffic. Pioneer in researching and applying new technologies, aiming to continuously improve product quality and optimize development resources.', '{\"job_description\":{\"overview\":/\"Participate in developing and playing an important role in MSB’s key digital transformation projects such as BPM Risk - building a unified digital lending platform from end to end; Magnet - a 360-degree data platform to enhance customer experience; Risk STP - a highly automated risk management system... and many other projects. Participate in clarifying business operations, designing solutions, developing and upgrading systems as required, ensuring projects are implemented effectively. Participate in reviewing designs, reviewing codes, optimizing systems to meet high traffic.\",\"responsibilities\":[\"Pioneer in researching and applying new technologies, aiming to continuously improve product quality and optimize development resources.\"]},\"benefits\":{\"salary_range\":/\"1000 - 2000 USD\",\"bonus\":/\"Income package from 15 to 18 months salary/year (with project bonus depending on job position). Receive competitive offer if you have outstanding ability.\",\"additional_benefits\":[\"Realize creative ideas by participating in the entire project lifecycle, from ideation, development to implementation.\",\"Flexible working hours, casual attire\", \"Work with leading partners and solution providers such as BCG, McKinsey, IBM, AWS, Temenos.\"]},\"work_hours\":/\"Monday - Friday (from 08:00 to 17:00)\",\"application_method\":/\"Submit your CV and cover letter via email to msb@company.com with the subject \'Senior Java Developer - 3I094\'.\"}', 'Proficiency in Java Core, Java frameworks such as EE, Spring, Hibernate ... and some popular design patterns. Thinking about object-oriented design and mastering knowledge of data structures and algorithms, object-oriented programming... Proficient in SQL, PLSQL, knowledge of Oracle/MySQL, noSQL database management systems, ability to optimize databases. Priority is given to those with knowledge of finance, banking, experience with microservices, eager to learn and innovate new things. Priority is given to candidates who have deployed products to AWS. Priority is given to candidates who have used some AWS products (AppSync, Batch, Lambda, EMR, Lake Formation, Glue, Fargate, API gateway, DynamoDB, ...) and some AWS components for security, API and storage). Priority is given to candidates who are proficient in Bitbucket, Jenkins, AWS CodePipeline. Priority is given to candidates with experience working in an Agile/Scrum model', 'java, sql, nosql, mysql, dynamodb, oracle, aws, security, database, systems, bitbucket, jenkins, codepipeline, api, agile, scrum', 'java, sql, nosql, mysql, dynamodb, oracle, aws, security, database, systems, bitbucket, jenkins, codepipeline, api, agile, scrum', 'platform, systems', 'continuously, effectively, highly', 'important, new, high, unified, key, digital', 'Hà Nội', 1, '25000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 5, 'Java Core, EE, Spring, Hibernate, Oracle/MySQL, noSQL, AWS, Bitbucket, Jenkins, AWS CodePipeline, Agile/Scrum', '2', 1, 1),
(249, 3, 'Lập Trình Viên Python (Python Developer)', 'Develop internal applications or commercial products on Python/Odoo/flask platform. Customize and develop modules in Odoo: Model, View, Business Logic, Report. Build and edit interfaces using HTML, CSS, JavaScript (XML View/Odoo OWL). Optimize the system, process data, write API or connect the system via RPC/XML-RPC/REST. Coordinate with members of the BA, tester, PM team to deploy features. Participate in maintenance, bug fixing, upgrading of operating Odoo systems.', '{\"job_description\":{\"overview\":/\"Develop internal applications or commercial products on Python/Odoo/flask platform. Customize and develop modules in Odoo: Model, View, Business Logic, Report. Build and edit interfaces using HTML, CSS, JavaScript (XML View/Odoo OWL).\",\"responsibilities\":[\"Optimize the system, process data, write API or connect the system via RPC/XML-RPC/REST. Coordinate with members of the BA, tester, PM team to deploy features. Participate in maintenance, bug fixing, upgrading of operating Odoo systems.\"]},\"benefits\":{\"salary_range\":/\"Salary up to 1800$ (or negotiable based on ability).\",\"bonus\":/\"Salary increase 2 times/year + bonus + 13th month salary\",\"additional_benefits\":[\"Travel at least once a year.\",\"12 days off per year (for official employees)\", \"Enjoy benefits and regimes according to the law (Holidays, social insurance, health insurance...)\",\"If you have an attractive idea, you will be able to start your own project at the company.\"]},\"work_hours\":/\"Working from Monday to Friday, 08:00 – 17:30, 1.5 hour lunch break\",\"application_method\":/\"Submit your CV and cover letter via email to advancetech@company.com with the subject \'Python Developer\'.\"}', 'General requirements:\r\n\r\nUniversity graduate majoring in IT and Telecommunication or equivalent, At least 1.5 years of experience.\r\n\r\nProficient in Python 3.x programming language.\r\n\r\nPractical experience with Odoo from v13 and above (v16 - v18 preferred).\r\n\r\nSkilled in working with JavaScript, HTML, CSS to edit UI and process client logic.\r\n\r\nUnderstand the MVC, ORM models, and Odoo’s module structure.\r\n\r\nKnow how to use Git, understand the teamwork process (Scrum, Agile is an advantage).\r\n\r\nHave logical thinking, good problem-solving ability, careful, highly responsible in work.\r\n\r\nAbility to work independently, work in a team. Being able to read and understand English documents is an advantage.\r\n\r\nUse Git source code management tool. Willing to learn new languages and frameworks.\r\n\r\nSoft skills:\r\n\r\nEffective teamwork skills, Problem solving skills\r\n\r\nHonest, highly responsible, Proactive and creative in work.', 'python, javascript, html, css, git, agile, scrum', 'python, javascript, html, css, git, agile, scrum', 'python, javascript, flask, html, css, tester, platform, systems, api', '', 'commercial, odoo, internal', 'Hà Nội', 1, '45000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 3, 'Python 3.x, MVC, ORM of Odoo v16 – v18, Agile/Scrum', '3', 1, 3),
(250, 3, 'Mobile Developer (Java Based)', 'Development of Mobile Application Software (Java Base). Will be part of Software Development Team. Should manage trouble shooting. Will also manage all project releases to client, update of patches. Planning test cases and test skill.', '{\"job_description\":{\"overview\":/\"Development of Mobile Application Software (Java Base). Will be part of Software Development Team.\",\"responsibilities\":[\"Should manage trouble shooting. Will also manage all project releases to client, update of patches. Planning test cases and test skill.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable\",\"bonus\":/\"100% salary during 2-month probation\",\"additional_benefits\":[\"Full-salary insurance starting right from probation period.\",\"3 times bonus per year. 5 working days per week with flexible check-in\", \"Health check once per year. Health care insurance package.\"]},\"work_hours\":/\"Monday-Friday with flexible check-in time (6A.M to 10A.M)\",\"application_method\":/\"Submit your CV and cover letter via email to sds@company.com with the subject \'Mobile Developer (Java Based)\'.\"}', 'Bachelor’s degree. Minimum 5 years of experience in Developing Mobile Application Software. Knowledge of Mobile Application Development. Knowledge of XML, CSS. Test code, find and fix bug in code. Create test cases. Should have problem solving attitude', 'css, software, mobile', 'css, software, mobile', 'java, software, mobile', '', 'mobile', 'Hà Nội', 1, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 8, 'Java Base, XML, CSS', '3', 1, 4),
(251, 4, 'Java Backend Developer (Senior/Tech Lead)', 'Domain: Travel industry\r\nTake responsibility to build backend web server\r\nImplement customer project requirements (new features, sustaining services). Research, compare, experiment with and integrate new services and frameworks. Leads the discovery phase of medium to large projects to come up with high level design. Leads the work of other small group of 3 to 4 engineers for assigned Engineering projects. Write quality backend code primarily in Java, Spring. Share your experience with your colleagues, review code, help improve others.', '{\"job_description\":{\"overview\":/\"Take responsibility to build backend web server\",\"responsibilities\":[\"Implement customer project requirements (new features, sustaining services). Research, compare, experiment with and integrate new services and frameworks. Leads the discovery phase of medium to large projects to come up with high level design. Leads the work of other small group of 3 to 4 engineers for assigned Engineering projects. Write quality backend code primarily in Java, Spring. Share your experience with your colleagues, review code, help improve others.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable.\",\"bonus\":/\"100% salary during 2-month probation\",\"additional_benefits\":[\"Working in an international, dynamic and professional environment with many opportunities to develop career.\",\"Having opportunities of being trained oversea and working directly with oversea customer. A stable and rewarding position where your long-term commitment will be highly valued.\", \"Technical & Soft skills internal training courses. Many company activities (Sport and music festival, TMA Futsal league …) are held annually.\",\"Competitive salary and bonus. Total Health Care Insurance. Loan Fund. Team Building Fund.\"]},\"work_hours\":/\"Monday - Friday (from 08:00 to 17:00)\",\"application_method\":/\"Submit your CV and cover letter via email to tma@company.com with the subject \'Java Backend Developer (Senior/Tech Lead)\'.\"}', 'At least 5 YoE working with MicroService architecture, especially using Java Spring boot framework. Strong Micro Service design patterns like CQRS, SAGA. Have experience in using noSQL databases like Cassandra, Redis, Neo4J is  a must. Have experience in using ElasticSearch, Apache Kafka is a plus. Have experience in writing API using gRPC, GraphQL and REST. Having knowledge of Agile (Scrum) process. Having knowledge of booking system for hotel, flight, car, tour is a plus. Good English written and verbal communication skills', 'java, java spring, spring boot, nosql, redis, cassandra, neo4j, elasticsearch, kafka, apache, apache kafka, graphql, grpc, api, agile, scrum', 'java, java spring, spring boot, nosql, redis, cassandra, neo4j, elasticsearch, kafka, apache, apache kafka, graphql, grpc, api, agile, scrum', 'java, java spring, backend', 'primarily', 'small, high, implement, new, large', 'TPHCM', 2, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 7, 'MicroService, Java Spring boot framework, Cassandra, Redis, Neo4J, ElasticSearch, Apache Kafka, gRPC, GraphQL and REST, Agile/Scrum', '4', 1, 1),
(252, 4, 'Angular Developer (Banking Project)', 'Programming Angular interface for customer projects (Website, App,...). Programming reports, system functions. Programming and developing product features according to Microservices model. Closely coordinate with the team to build products that meet requirements and designs. Contribute ideas, continuously improve products to meet customer needs and user experience. Work under the management of PM, Tech Lead.', '{\"job_description\":{\"overview\":/\"Programming Angular interface for customer projects (Website, App,...). Programming reports, system functions. Programming and developing product features according to Microservices model.\",\"responsibilities\":[\"Closely coordinate with the team to build products that meet requirements and designs. Contribute ideas, continuously improve products to meet customer needs and user experience. Work under the management of PM, Tech Lead.\"]},\"benefits\":{\"salary_range\":/\"Salary 10 đến 20 triệu\",\"bonus\":/\"Tùy vào năng lực làm việc.\",\"additional_benefits\":[\"Enjoy full social insurance, health insurance, unemployment insurance for employees according to the provisions of the Law.\",\"Have the opportunity to train internally to improve professional skills and soft skills\", \"Periodic health check-up once a year at international hospitals. 12 days of paid leave per year and 1 day off during the birthday month.\"]},\"work_hours\":/\"Monday-Friday (8:00 to 17:00)\",\"application_method\":/\"Submit your CV and cover letter via email to gogroup@company.com with the subject \'Angular Developer (Banking Project)\'.\"}', 'University degree, major in Information Technology, Computer Science, Information Systems,... is an advantage. At least 2 years of experience with Angular 10. Deep experience with Javascript, HTML5, CSS3. Experience with RESTFul and APIs. Experience working in Bank/Fintech projects is preferred. Good English communication is an advantage', 'javascript, angular, html5, restful, systems', 'javascript, angular, html5, restful, systems', 'angular', 'continuously, closely', 'angular', 'Hà Nội', 1, '10000000-20000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 4, 'Angular 10, Javascript, HTML5, CSS3, RESTFul và APIs', '4', 1, 2),
(253, 5, 'Vue Front-End Developer', 'Responsible for the architecture and development of product front-end features. Participate in the design and implementation of front-end tools and frameworks. Optimize product performance, explore faster and more flexible WebView features or user experience improvements, and achieve optimal page loading, execution, and rendering times. Collaborate with backend engineers to discuss technical solutions and perform application/system optimization and integration. Stay updated on the latest front-end technologies to better serve the team and business.', '{\"job_description\":{\"overview\":/\"Responsible for the architecture and development of product front-end features. Participate in the design and implementation of front-end tools and frameworks. Optimize product performance, explore faster and more flexible WebView features or user experience improvements, and achieve optimal page loading, execution, and rendering times.\",\"responsibilities\":[\"Collaborate with backend engineers to discuss technical solutions and perform application/system optimization and integration. Stay updated on the latest front-end technologies to better serve the team and business.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable.\",\"bonus\":/\"Competitive salary and performance-based bonuses\",\"additional_benefits\":[\"Flexible working hours and remote work options.\",\"Opportunity to work on exciting, cutting-edge projects.\", \"Supportive and collaborative team environment.\",\"Learning and development opportunities.\"]},\"work_hours\":/\"Monday-Friday (9:00 to 18:00)\",\"application_method\":/\"Submit your CV and cover letter via email to nexsoft@company.com with the subject \'Vue Front-End Developer\'.\"}', '', '', '', 'backend, performance, optimization', 'fast, well', 'late, technical, optimal, flexible, responsible', 'TPHCM', 2, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 5, 'JavaScript, HTML/HTML5/XML, CSS/CSS3 và Ajax, jQuery, Bootstrap, Zepto, DOM, WebKit, JSON', '1', 1, 2),
(254, 5, 'Middle IOS Developer', 'Never settle, think bigger. Vulcan Labs is a leading mobile application development company based in Ho Chi Minh City, Vietnam, with flexibility for some remote work. Since 2017, we have published over 30 game and mobile applications with several hundred thousand Daily Active Users and over 10 million downloads combined. Our products on iOS App Store and Android include Camera Translator: Translate+, Face Story - AI Photo Editor, and TV Cast & Screen Mirroring. We welcome individuals from diverse backgrounds to join our team and embrace challenges in a fast-changing industry.', '{\"job_description\":{\"overview\":/\"As an iOS Developer in our team, you will do research to turn ideas into products on the iOS platform.\",\"responsibilities\":[\"Implement designs and prototypes to build world-class applications on the IOS platform to deliver the best market products. Study new APIs and frameworks with regard to the capability to apply for market products.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable\",\"bonus\":/\"Additionally to the 13th-month salary, you will have an attractive remuneration package including KPI bonuses and hot rewards for each project.\",\"additional_benefits\":[\"You will participate in the development team of top chart applications globally at a cool office with young and friendly talents with a global approach.\",\"You have an opportunity to work with cutting-edge technology: Virtual Reality, Augmented Reality, and Artificial Intelligence, and challenge your career path with the meaning of “ Go Big or Go Home”\", \"You are independent to give ideas and design innovative products to serve millions of global users and products will stand on top of technological trends.\"]},\"work_hours\":/\"Monday-Friday(10:00 to 18:00)\",\"application_method\":/\"Submit your CV and cover letter via email to vulcanlabs@company.com with the subject \'Middle IOS Developer\'.\"}', 'Strong background in computer science. Concurrency and networking management in iOS. Strong knowledge of Programming algorithms and data structures. Strong knowledge of architectures like MVVM or VIPER. Strong knowledge of SwiftUI. Having experience in iOS Applications (at least 3 years). Deep knowledge of the latest trends in software engineering best practices – code reviews, code quality, CI/CD, monitoring, multi-threading, and memory management. Good to have experience on:UIKit, RxSwift, Combine, Firebase, Google Cloud platform, SwiftPM, CocoaPods, Carthage, StoreKit, StoreKit2, Realm, CoreData', 'swiftui, uikit, firebase, google cloud platform, software, ci cd, ios, cloud, platform, monitoring', 'swiftui, uikit, firebase, google cloud platform, software, ci cd, ios, cloud, platform, monitoring', 'mobile, ios, ai, game', 'fast, daily', 'big, mobile, remote, active, diverse', 'TPHCM', 2, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 5, 'MVVM or VIPER, SwiftUI, code reviews, code quality, CI/CD, UIKit', '1', 1, 4),
(255, 6, 'Mobile Developer (Android Or IOS)', 'We are looking for skilled Mobile Developers (Android/iOS) to join our team at LG CNS Vietnam Build Center. The ideal candidates will have strong experience in mobile application development using Kotlin (for Android) or Swift (for iOS) and should be capable of developing and integrating REST APIs. You will collaborate with cross-functional teams to design, develop, and maintain high-quality mobile applications.', '{\"job_description\":{\"overview\":/\"Develop, test, and maintain high-performance mobile applications for Android (Kotlin) or iOS (Swift). Design and integrate REST APIs to ensure seamless communication between the mobile app and backend services. Work closely with designers, backend developers, and product managers to deliver high-quality mobile applications.\",\"responsibilities\":[\"Ensure the applications meet security, performance, and user experience standards. Debug and resolve issues, optimize code, and ensure scalability of applications. Stay updated with the latest mobile development trends and best practices. Participate in Agile/Scrum development processes.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable.\",\"bonus\":/\"Topik allowance, salary increases according to work performance.\",\"additional_benefits\":[\"Health care: Premium health insurance, Annual health check-up. Young working environment. Good career development opportunities with interesting and challenging projects.\",\"English, Korean, technical, soft skills training courses.\", \"Opportunity to learn special courses from LG CNS, new technology and security. Gifts on holidays (April 30th - May 1st, September 2nd, Tet, etc.)\"]},\"work_hours\":/\"8 hours from Monday - Friday (8 hours/day)\",\"application_method\":/\"Submit your CV and cover letter via email to lgcns@company.com with the subject \'Mobile Developer (Android Or IOS)\'.\"}', 'Education: Bachelor’s degree in computer science, Software Engineering, or a related field. Experience: At least 4 years of experience in mobile development. Technical Skills: Strong proficiency in Kotlin (for Android) or Swift (for iOS). Experience with developing and integrating RESTful APIs. Familiarity with mobile UI/UX design principles and best practices. Experience with Git version control. Strong problem-solving skills and ability to work in a fast-paced environment.', 'swift, kotlin, ui ux, restful, software, mobile, ios, git', 'swift, kotlin, ui ux, restful, software, mobile, ios, git', 'swift, kotlin, mobile, ios', 'apis', 'mobile, cross, capable, high, functional, ideal, strong, skilled', 'Hà Nội', 1, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 6, 'RESTful APIs, mobile UI/UX design, Git, Jenkins, Microsoft 365 Graph API', '2', 1, 4),
(256, 6, 'Full Stack Developer (Java, Springboot, React)', 'Develop and maintain fullstack web applications using Java (Spring Boot) on backend and React.js on frontend. Participate in the entire software development lifecycle, from requirements gathering, analysis, design to deployment and maintenance. Conduct unit testing and support integration testing. Ensure code quality, performance, and scalability. Maintain technical documentation and follow coding standards.', '{\"job_description\":{\"overview\":/\"Develop and maintain fullstack web applications using Java (Spring Boot) on backend and React.js on frontend. Participate in the entire software development lifecycle, from requirements gathering, analysis, design to deployment and maintenance. Conduct unit testing and support integration testing.\",\"responsibilities\":[\"Ensure code quality, performance, and scalability. Maintain technical documentation and follow coding standards.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable.\",\"bonus\":/\"Topik allowance, salary increases according to work performance.\",\"additional_benefits\":[\"Health care: Premium health insurance, Annual health check-up. Young working environment. Good career development opportunities with interesting and challenging projects.\",\"English, Korean, technical, soft skills training courses.\", \"Opportunity to learn special courses from LG CNS, new technology and security. Gifts on holidays (April 30th - May 1st, September 2nd, Tet, etc.)\"]},\"work_hours\":/\"8 hours from Monday - Friday (8 hours/day)\",\"application_method\":/\"Submit your CV and cover letter via email to lgcns@company.com with the subject \'Full Stack Developer (Java, Springboot, React)\'.\"}', 'Bachelor’s degree of Information Technology or higher. Have working experience and excellent knowledge at software developing using Java, Spring boot. Have working experience and excellent knowledge at software developing using React, HTML, JavaScript. Good knowledge about AI (Azure OpenAI and GenAI). Good knowledge about public cloud (Azure, AWS,). Database: MariaDB (or MySQL).', 'java, javascript, react, java spring, spring boot, mysql, mariadb, html, aws, software, cloud, ai, database', 'java, javascript, react, java spring, spring boot, mysql, mariadb, html, aws, software, cloud, ai, database', 'java, java spring, spring boot, fullstack, software, backend, frontend, performance', '', 'entire, technical', 'Hà Nội', 1, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 6, 'Java Springboot, React, HTML, JavaScript, AI (Azure OpenAI và GenAI), Azure, AWS, MariaDB (or MySQL).', '2', 1, 3),
(257, 7, 'Java Developer', 'Work for projects to develop and operate information technology systems for subsidiaries in Samsung group globally. Development of services’ new functions. Functional development and testing for improvements and requirements (SR) requested by the customers. Source code refactoring and maintenance development. In case of failure, source debugging and reporting of cause identification. Support for service change and PM business.', '{\"job_description\":{\"overview\":/\"Work for projects to develop and operate information technology systems for subsidiaries in Samsung group globally. Development of services’ new functions. Functional development and testing for improvements and requirements (SR) requested by the customers.\",\"responsibilities\":[\"Source code refactoring and maintenance development. In case of failure, source debugging and reporting of cause identification. Support for service change and PM business.\"]},\"benefits\":{\"salary_range\":/\"Salary negotiable\",\"bonus\":/\"100% salary during 2-month probation. Full-salary insurance starting right from probation period. 3 times bonus per year\",\"additional_benefits\":[\"5 working days per week with flexible check-in\",\"Health check once per year. Health care insurance package\"]},\"work_hours\":/\"Thứ 2 - Thứ 6 (từ 08:00 đến 17:48)\",\"application_method\":/\"Submit your CV and cover letter via email to sds@company.com with the subject \'Java Developer\'.\"}', 'Bachelor’s degree. Min 4 years of relevant experience in Java Technicals (Java Web, Java Application, Java Core, Spring, Spring MVC...). Ability to structure, analyze, and develop Oracle, MySQL, MS SQL, SQL tuning, MongoDB... Ability to Programming of XML, CSS, HTML5. Ability to Programming of JSP/ Spring/ mybatis/ jquery/ REST/ Web Service, Vuejs. Adapt to changes, and learn to apply new technology and domain knowledge. Ability to work closely with international team members to understand goals and product vision. No problem to go Biz. Trip to Korea', 'java, vuejs, jquery, sql, mysql, mongodb, oracle, html5, css', 'java, vuejs, jquery, sql, mysql, mongodb, oracle, html5, css', 'systems', 'globally', 'new, functional', 'Hà Nội', 1, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 6, 'Java Technicals, SQL, XML, CSS, HTML5, JSP/ Spring/ mybatis/ jquery/ REST/ Web Service, Vuejs', '1', 1, 1),
(258, 7, 'Fullstack Developer', 'Join our team in developing a cloud-based enterprise application designed to support complex business operations at scale. You’ll contribute to backend development, working with modern frameworks and cloud-native technologies.', '{\n  \"job_description\": {\n    \"overview\": \"Join our team in developing a cloud-based enterprise application designed to support complex business operations at scale. You’ll contribute to backend development, working with modern frameworks and cloud-native technologies.\",\n    \"responsibilities\": [\n      \"Develop and maintain backend features for enterprise applications on cloud platforms. Build robust backend services and APIs using Java (Spring Boot or similar frameworks). Collaborate with product owners, UI/UX designers, QA engineers, and DevOps for end-to-end delivery. Ensure performance, scalability, and security of the application. Participate in code reviews and agile ceremonies.\"\n    ]\n  },\n  \"benefits\": {\n    \"salary_range\": \"Salary negotiable\",\n    \"bonus\": \"Tăng theo công việc\",\n    \"additional_benefits\": [\n      \"Working in an international, dynamic and professional environment with many opportunities to develop career.\",\n      \"Having opportunities of being trained oversea and working directly with oversea customer. A stable and rewarding position where your long-term commitment will be highly valued.\",\n      \"Technical & Soft skills internal training courses. Many company activities (Sport and music festival, TMA Futsal league …) are held annually.\",\n      \"Competitive salary and bonus. Total Health Care Insurance. Loan Fund. Team Building Fund.\"\n    ]\n  },\n  \"work_hours\": \"Thứ 2 - Thứ 6 (từ 08:00 đến 17:00)\",\n  \"application_method\": \"Submit your CV and cover letter via email to tma@company.com with the subject \'Fullstack Developer\'.\"\n}', 'TStrong programming skills in Java (preferably Spring Boot). Experience with Angular or ReactJS for frontend development. Good understanding of RESTful APIs and microservices architecture. Experience with relational databases (e.g., PostgreSQL, MySQL) and version control (Git). Familiar with containerization (Docker) and basic DevOps workflows (CI/CD). Solid problem-solving skills and ability to work independently or in a team.', 'java, angular, spring boot, mysql, postgresql, restful, ci cd, devops, frontend, git, docker', 'java, angular, spring boot, mysql, postgresql, restful, ci cd, devops, frontend, git, docker', 'backend', '', 'cloud, native, complex, modern', 'TPHCM', 2, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 3, 'Java Spring Boot, Angular/ReactJS, PostgreSQL, MySQL, RESTful APIs, Git, Docker, CI/CD', '1', 1, 3),
(259, 8, 'Intern PHP Developer (Wordpress)', 'We are looking for a Web Developer Intern who is passionate about PHP and WordPress development. You will work closely with our web team to support website development, maintenance, and optimization tasks — mainly using WordPress.', '{\"job_description\":{\"overview\":\"We are looking for a Web Developer Intern who is passionate about PHP and WordPress development. You will work closely with our web team to support website development, maintenance, and optimization tasks — mainly using WordPress.\",\"responsibilities\":[\"Support the development and customization of websites using WordPress and PHP.  Assist in building or modifying themes and plugins.  Work with HTML, CSS, JavaScript to implement UI/UX improvements.  Troubleshoot and fix minor bugs or layout issues.  Support content updates and testing for websites.  Collaborate with designers, content writers, and developers as needed.\"]},\"benefits\":{\"salary_range\":\"Salary 4.5-7 triệu.\",\"bonus\":\"Motorbike or bus support allowance.\",\"additional_benefits\":[\"Professional and challenge working environment.\",\"Japanese culture and working atmosphere discovery.\"]},\"work_hours\":\"Thứ 2 - Thứ 6 (từ 08:00 đến 17:00)\",\"application_method\":\"Submit your CV and cover letter via email to isb@company.com with the subject \'Intern PHP Developer (Wordpress)\'.\"}', 'Final year student or recent graduate majoring in Information Technology\r\nFull-time internship and work full time after internship. Basic knowledge of PHP and WordPress. Familiar with HTML, CSS, and JavaScript. Eagerness to learn and improve coding skills. Responsible, detail-oriented, and a team player. Excellent problem-solving skills, a meticulous attention to detail, and a logical approach to challenges. Strong communication and teamwork skills, with a proactive and eager-to-learn attitude. Ability to commit to a full-time internship schedule. (Optional but nice to have). Experience with Git, Figma.', 'javascript, php, html, css, git, figma, wordpress', 'javascript, php, html, css, git, figma, wordpress', 'php, optimization, wordpress', 'mainly, closely', 'passionate, wordpress', 'TPHCM', 2, '4500000 - 7000000', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 1, 'PHP and WordPress, HTML, CSS, and JavaScript, Excellent problem-solving skills', '1', 1, 1);
INSERT INTO `job` (`id`, `employer_id`, `title`, `description`, `description_json`, `required`, `skills`, `primary_skills`, `secondary_skills`, `adverbs`, `adjectives`, `address`, `location_id`, `salary`, `status`, `posted_at`, `posted_expired`, `experience_id`, `required_skills`, `member`, `work_type_id`, `category_id`) VALUES
(260, 8, 'Backend Developer (Java/Golang) - Hà Nội', 'Tham gia phát triển và bảo trì các ứng dụng. Nhận công việc từ team lead. Phát triển các tính năng hệ thống theo BA, API. Bảo mật ứng dụng. Hợp tác với team dự án để không ngừng đổi mới chức năng và thiết kế ứng dụng. Khắc phục sự cố và gỡ rối tối ưu hóa hiệu suất ứng dụng. Thu thập yêu cầu cụ thể và đề xuất giải pháp.', '{\"job_description\":{\"overview\":\"Tham gia phát triển và bảo trì các ứng dụng. Phát triển các tính năng hệ thống theo BA, API. Bảo mật ứng dụng. Khắc phục sự cố và gỡ rối tối ưu hóa hiệu suất ứng dụng. Thu thập yêu cầu cụ thể và đề xuất giải pháp.\",\"responsibilities\":[\"Nhận công việc từ team lead. Hợp tác với team dự án để không ngừng đổi mới chức năng và thiết kế ứng dụng.\"]},\"benefits\":{\"salary_range\":\"Lương thỏa thuận\",\"bonus\":\"Lương tháng 13 + KPI theo kết quả kinh doanh.\",\"additional_benefits\":[\"Tham gia bảo hiểm theo Luật lao động Việt Nam. Bảo hiểm sức khỏe cộng thêm của PVI cho NLD.\",\"Trang bị laptop riêng làm việc. Tham gia các khóa đào tạo do công ty tổ chức, chương trình team building\", \"Khám sức khỏe định kỳ hàng năm. 12 ngày phép năm.\"]},\"work_hours\":\"Thứ 2 - Thứ 6 (từ 08:30 đến 18:00)\",\"application_method\":\"Submit your CV and cover letter via email to greenspeed@company.com with the subject \'Backend Developer (Java/Golang) - Hà Nội\'.\"}', 'Tốt nghiệp loại Khá trở lên Đại học chính quy chuyên ngành Công nghệ thông tin, Kỹ thuật máy tính, Kỹ thuật phần mềm, Khoa học máy tính hoặc các ngành liên quan. Tối thiểu 1 năm kinh nghiệm vị trí tương đương. Có kiến thức và kỹ năng lập trình với ngôn ngữ Golang/Java. Kỹ năng lập kế hoạch và triển khai. Tin học: Thành thạo Ms Office, PP... Có khả năng tối ưu code, tìm lỗi và xử lý. ', 'java, golang', 'java, golang', 'team lead, api', '', '', 'Hà Nội', 1, 'Negotiable', 1, '2025-07-09 12:56:21', '2025-10-09 00:00:00', 2, 'GORM giao tiếp với DB, GIN expose API, Design Patterns, OOP, swagger, Kubernetes, PostgreSQL, Redis', '1', 1, 1),
(262, 1, '.NET Developer ', 'We are seeking a skilled and passionate .NET Developer to join our growing development team. You will be responsible for designing, implementing, and maintaining applications using the Microsoft .NET framework. The ideal candidate should have strong experience with backend development and a good understanding of frontend technologies.\n\nAs a .NET Developer, you will work closely with cross-functional teams including Product, QA, and DevOps to deliver high-quality solutions in a fast-paced Agile environment.\n\nTrách nhiệm công việc:\n• Develop, test, and maintain high-performance applications using .NET technologies\n• Design and build RESTful APIs and backend services\n• Participate in architecture discussions and contribute to system design decisions\n• Troubleshoot and resolve bugs, performance issues, and production incidents\n• Collaborate with front-end developers to integrate user-facing elements with server-side logic\n• Write clean, scalable, and maintainable code following best practices\n• Contribute to code reviews and mentor junior developers\n• Participate in Agile/Scrum meetings and provide estimates for tasks', '{\"job_description\":{\"overview\":\"We are seeking a skilled and passionate .NET Developer to join our growing development team. You will be responsible for designing, implementing, and maintaining applications using the Microsoft .NET framework. The ideal candidate should have strong experience with backend development and a good understanding of frontend technologies.\\n\\nAs a .NET Developer, you will work closely with cross-functional teams including Product, QA, and DevOps to deliver high-quality solutions in a fast-paced Agile environment.\",\"responsibilities\":[\"Develop, test, and maintain high-performance applications using .NET technologies\",\"Design and build RESTful APIs and backend services\",\"Participate in architecture discussions and contribute to system design decisions\",\"Troubleshoot and resolve bugs, performance issues, and production incidents\",\"Collaborate with front-end developers to integrate user-facing elements with server-side logic\",\"Write clean, scalable, and maintainable code following best practices\",\"Contribute to code reviews and mentor junior developers\",\"Participate in Agile/Scrum meetings and provide estimates for tasks\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"không có\",\"additional_benefits\":[\"không có\"]},\"work_hours\":\"\",\"application_method\":\"\"}', 'Minimum 3 years of experience with C# and ASP.NET MVC/Web API\n\nSolid understanding of object-oriented programming (OOP)\n\nExperience with Entity Framework or similar ORM\n\nFamiliarity with RESTful API design and development\n\nKnowledge of HTML, CSS, JavaScript\n\nAbility to write clean, maintainable, and efficient code\n\nStrong problem-solving and communication skills', 'javascript, c#, html, css, restful, oop, api', 'javascript, c#, html, css, restful, oop, api', 'restful, backend, devops, qa, frontend, production, performance, agile, scrum', 'fast, closely', 'backend, restful, cross, high, junior, good, clean, passionate, scalable, functional, ideal, strong, skilled, agile, responsible, maintainable', 'HCM', 2, '15000000', 1, '2025-07-09 21:59:40', '2025-07-26 00:00:00', 3, '.NET, MySQL', '1', 1, 1),
(263, 9, 'Java Developer', 'Test\n\nTrách nhiệm công việc:\n• Test', '{\"job_description\":{\"overview\":\"Test\",\"responsibilities\":[\"Test\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"\",\"additional_benefits\":[]},\"work_hours\":\"\",\"application_method\":\"\"}', 'Test', '', '', '', '', '', 'HCM', 2, '15000000', 1, '2025-07-17 22:02:03', '2025-07-18 00:00:00', 4, '.NET, MySQL', '2', 1, 1),
(264, 1, 'Senior Java Developer', 'Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\n\n\nTrách nhiệm công việc:\n• • Thiết kế, phát triển và bảo trì các dịch vụ backend.\n• • Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\n• • Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\n• • Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.', '{\"job_description\":{\"overview\":\"Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \\nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\\n\",\"responsibilities\":[\"• Thiết kế, phát triển và bảo trì các dịch vụ backend.\",\"• Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\",\"• Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\",\"• Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"Thưởng hiệu suất hàng quý, thưởng dự án và thưởng Tết hấp dẫn.\",\"additional_benefits\":[\"• Bảo hiểm sức khỏe cá nhân.\",\"• 12 ngày phép/năm, tăng theo thâm niên.\",\"• Laptop làm việc, môi trường làm việc năng động, trẻ trung.\",\"• Cơ hội đào tạo và phát triển chuyên môn định kỳ.\"]},\"work_hours\":\"8:00 - 17:00 \",\"application_method\":\"\"}', 'Tốt nghiệp chuyên ngành Công nghệ Thông tin hoặc các ngành liên quan. \nCó tối thiểu 2 năm kinh nghiệm phát triển ứng dụng Java, sử dụng Spring Boot. \nHiểu biết về kiến trúc microservices, thiết kế RESTful API và có kinh nghiệm với hệ thống quản lý cơ sở dữ liệu (MySQL, PostgreSQL). \nƯu tiên ứng viên đã từng làm việc với Docker, CI/CD pipeline, và các công cụ quản lý source như Git.\n', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, java spring, spring boot, backend, agile, scrum', '', 'trì', 'HCM', 2, '15000000', 1, '2025-07-19 19:53:34', '2025-07-30 00:00:00', 4, 'Java, Spring Boot, RESTful API, SQL, Git, Docker, Microservices', '1', 1, 1),
(265, 1, 'Test', 'Test\n\nTrách nhiệm công việc:\n• Test', '{\"job_description\":{\"overview\":\"Test\",\"responsibilities\":[\"Test\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"Test\",\"additional_benefits\":[\"Test\"]},\"work_hours\":\"\",\"application_method\":\"\"}', 'Test', '', '', '', '', '', 'HCM', 2, '15000000', 1, '2025-07-19 20:00:08', '2025-07-23 00:00:00', 3, 'Test', '1', 2, 1),
(266, 1, 'Java', NULL, '{\"job_description\":{\"overview\":\"Join our team as a PHP Developer in Hà Nội. We are seeking a skilled PHP Developer to join our dynamic engineering team in Hanoi.\",\"responsibilities\":[\"Develop and maintain applications using PHP, Laravel, Java, Spring, MySql.\",\"Design and implement scalable RESTful APIs.\",\"Optimize server-side performance and ensure security.\",\"Collaborate with frontend developers for seamless integration.\"]},\"benefits\":{\"salary_range\":\"15,000,000 VND/month (net)\",\"bonus\":\"Performance-based bonus, up to 2 months\' salary.\",\"additional_benefits\":[\"Health insurance and social benefits as per Vietnamese law.\",\"Annual company trip and team-building activities.\",\"Training and certification support for Node.js technologies.\"]},\"work_hours\":\"Monday to Friday, 9:00 AM - 6:00 PM\",\"application_method\":\"Submit your CV and cover letter via email to hr@company.com with the subject \'PHP Developer Application - Hà Nội\'.\"}', 'Minimum 3 years of experience with PHP in production environments. Proficiency in MongoDB and RESTful API development.', NULL, NULL, NULL, NULL, NULL, 'Hà Nội', 1, '15000000', 1, '2025-07-19 20:12:39', '2025-07-01 07:00:00', 5, 'Node.js, Express, MongoDB, RESTful API', '1', 1, 1),
(267, 1, 'Middle Java Dev', 'Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\n\n\nTrách nhiệm công việc:\n• • Thiết kế, phát triển và bảo trì các dịch vụ backend.\n• • Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\n• • Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\n• • Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.', '{\"job_description\":{\"overview\":\"Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \\nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\\n\",\"responsibilities\":[\"• Thiết kế, phát triển và bảo trì các dịch vụ backend.\",\"• Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\",\"• Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\",\"• Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"Thưởng hiệu suất hàng quý, thưởng dự án và thưởng Tết hấp dẫn.\",\"additional_benefits\":[]},\"work_hours\":\"8:00 - 17:00 \",\"application_method\":\"\"}', 'Tốt nghiệp chuyên ngành Công nghệ Thông tin hoặc các ngành liên quan. \nCó tối thiểu 2 năm kinh nghiệm phát triển ứng dụng Java, sử dụng Spring Boot. \nHiểu biết về kiến trúc microservices, thiết kế RESTful API và có kinh nghiệm với hệ thống quản lý cơ sở dữ liệu (MySQL, PostgreSQL). \nƯu tiên ứng viên đã từng làm việc với Docker, CI/CD pipeline, và các công cụ quản lý source như Git.\n', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, java spring, spring boot, backend, agile, scrum', '', 'trì', 'HCM', 2, '15000000', 1, '2025-07-19 20:17:55', '2025-07-31 00:00:00', 4, 'Java, Spring Boot, RESTful API, SQL, Git, Docker, Microservices', '1', 1, 1),
(268, 1, 'Java Dev', 'Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\n\n\nTrách nhiệm công việc:\n• • Thiết kế, phát triển và bảo trì các dịch vụ backend.\n• • Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\n• • Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\n• • Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.', '{\"job_description\":{\"overview\":\"Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \\nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\\n\",\"responsibilities\":[\"• Thiết kế, phát triển và bảo trì các dịch vụ backend.\",\"• Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\",\"• Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\",\"• Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"Thưởng hiệu suất hàng quý, thưởng dự án và thưởng Tết hấp dẫn.\",\"additional_benefits\":[]},\"work_hours\":\"8:00 - 17:00 \",\"application_method\":\"\"}', 'Tốt nghiệp chuyên ngành Công nghệ Thông tin hoặc các ngành liên quan. \nCó tối thiểu 2 năm kinh nghiệm phát triển ứng dụng Java, sử dụng Spring Boot. \nHiểu biết về kiến trúc microservices, thiết kế RESTful API và có kinh nghiệm với hệ thống quản lý cơ sở dữ liệu (MySQL, PostgreSQL). \nƯu tiên ứng viên đã từng làm việc với Docker, CI/CD pipeline, và các công cụ quản lý source như Git.\n', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, java spring, spring boot, backend, agile, scrum', '', 'trì', 'HCM', 2, '15000000', 1, '2025-07-19 20:21:42', '2025-07-31 00:00:00', 5, 'Java, Spring Boot, RESTful API, SQL, Git, Docker, Microservices', '1', 1, 1),
(269, 1, 'Sr Java', 'Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\n\n\nTrách nhiệm công việc:\n• • Thiết kế, phát triển và bảo trì các dịch vụ backend.\n• • Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\n• • Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\n• • Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.', '{\"job_description\":{\"overview\":\"Phát triển và bảo trì các hệ thống backend sử dụng Java Spring Boot. \\nTham gia vào quy trình phát triển phần mềm theo mô hình Agile/Scrum, đảm bảo chất lượng và tiến độ sản phẩm.\\n\",\"responsibilities\":[\"• Thiết kế, phát triển và bảo trì các dịch vụ backend.\",\"• Phân tích yêu cầu hệ thống và đề xuất giải pháp kỹ thuật.\",\"• Tối ưu hóa hiệu năng hệ thống và sửa lỗi khi phát sinh.\",\"• Tham gia vào code review, viết tài liệu kỹ thuật và hướng dẫn triển khai.\"]},\"benefits\":{\"salary_range\":\"\",\"bonus\":\"Thưởng hiệu suất hàng quý, thưởng dự án và thưởng Tết hấp dẫn.\",\"additional_benefits\":[]},\"work_hours\":\"8:00 - 17:00 \",\"application_method\":\"\"}', 'Tốt nghiệp chuyên ngành Công nghệ Thông tin hoặc các ngành liên quan. \nCó tối thiểu 2 năm kinh nghiệm phát triển ứng dụng Java, sử dụng Spring Boot. \nHiểu biết về kiến trúc microservices, thiết kế RESTful API và có kinh nghiệm với hệ thống quản lý cơ sở dữ liệu (MySQL, PostgreSQL). \nƯu tiên ứng viên đã từng làm việc với Docker, CI/CD pipeline, và các công cụ quản lý source như Git.\n', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, spring boot, mysql, postgresql, restful, ci cd, pipeline, git, docker, api', 'java, java spring, spring boot, backend, agile, scrum', '', 'trì', 'HCM', 2, '15000000', 1, '2025-07-19 20:29:50', '2025-07-31 00:00:00', 5, 'Java, Spring Boot, RESTful API, SQL, Git, Docker, Microservices', '1', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `name`, `status`) VALUES
(1, 'Hà Nội', 1),
(2, 'Hồ Chí Minh', 1),
(3, 'Đà Nẵng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `id` int(11) NOT NULL,
  `cv_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `matched_skill` text NOT NULL,
  `time_matches` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  `accuracy` float DEFAULT NULL,
  `label` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`id`, `cv_id`, `job_id`, `matched_skill`, `time_matches`, `status`, `accuracy`, `label`) VALUES
(394, 5, 242, 'kafka, redis, java, backend, sql, git, spring boot, docker', '2025-07-20 12:36:15', 1, 0.996172, 'Moderately Suitable'),
(395, 5, 264, 'mysql, postgresql, restful, java, pipeline, docker, git, spring boot, api', '2025-07-20 12:36:16', 1, 0.695815, 'Moderately Suitable'),
(396, 5, 267, 'mysql, postgresql, restful, java, pipeline, docker, git, spring boot, api', '2025-07-20 12:36:16', 1, 0.695815, 'Moderately Suitable'),
(397, 5, 268, 'mysql, postgresql, restful, java, pipeline, docker, git, spring boot, api', '2025-07-20 12:36:16', 1, 0.695815, 'Moderately Suitable'),
(398, 5, 269, 'mysql, postgresql, restful, java, pipeline, docker, git, spring boot, api', '2025-07-20 12:36:16', 1, 0.695815, 'Moderately Suitable');

-- --------------------------------------------------------

--
-- Table structure for table `membership`
--

CREATE TABLE `membership` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` double NOT NULL,
  `type_for` int(11) NOT NULL,
  `description` text NOT NULL,
  `duration` text NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `membership`
--

INSERT INTO `membership` (`id`, `name`, `price`, `type_for`, `description`, `duration`, `status`) VALUES
(1, 'Gói miễn phí cho người ứng tuyển/ hàng tháng', 0, 1, 'Gói dịch vụ dành cho người tìm việc, Cung cấp quyền truy cập cơ bản miễn phí mỗi tháng, Bao gồm: tìm kiếm và xem thông tin việc làm, Ứng tuyển trực tiếp vào tin tuyển dụng, Quản lý hồ sơ cá nhân.', 'MONTHLY', 1),
(2, 'Gói miễn phí cho người ứng tuyển/ hàng năm', 0, 2, 'Gói dịch vụ dành cho người tìm việc, Cung cấp quyền truy cập cơ bản miễn phí mỗi tháng, Bao gồm: tìm kiếm và xem thông tin việc làm, Ứng tuyển trực tiếp vào tin tuyển dụng, Quản lý hồ sơ cá nhân.', 'MONTHLY', 1),
(3, 'Gói PRO cho người ứng tuyển/ hàng tháng', 250000, 1, 'Gói PRO dành cho người tìm việc chuyên nghiệp, Mở rộng cơ hội với gợi ý việc làm thông minh bằng AI, Tích hợp và tối ưu CV dễ dàng, Thao tác ứng tuyển nhanh chóng, Nhận thông báo ngay khi có việc làm mới.', 'MONTHLY', 1),
(4, 'Gói PRO cho người ứng tuyển/ hàng năm', 2500000, 2, 'Gói PRO dành cho người tìm việc chuyên nghiệp, Mở rộng cơ hội với gợi ý việc làm thông minh bằng AI, Tích hợp và tối ưu CV dễ dàng, Thao tác ứng tuyển nhanh chóng, Nhận thông báo ngay khi có việc làm mới.', 'MONTHLY', 0);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `is_read` tinyint(1) DEFAULT 0,
  `created_at` datetime DEFAULT current_timestamp(),
  `type` varchar(50) NOT NULL,
  `job_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `user_id`, `title`, `content`, `is_read`, `created_at`, `type`, `job_id`) VALUES
(370, 1, 'Tin nhắn mới từ tài khoản <span class=\'sender-name\'>tuhoangnguyen2003</span>', 'Tài khoản <span class=\'sender-name\'>tuhoangnguyen2003</span> đã gửi tin nhắn: \"Ứng viên đã ứng tuyển công việc <strong>Sr Java</strong>. Đây là CV của Ứng viên: <a href=\"http://192.168.198.1:8081/assets/files/a7fa4ab260c345ffa052d26699a2e002.pdf\" target=\"_blank\">Xem CV</a>\"', 0, '2025-07-20 12:22:05', 'CHAT_MESSAGE', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `employer_membership_id` int(11) DEFAULT NULL,
  `payment_type` text NOT NULL,
  `amount` double NOT NULL,
  `payment_method` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `employer_membership_id`, `payment_type`, `amount`, `payment_method`, `status`, `transaction_id`, `description`, `time`) VALUES
(1, 1, 'BANKING', 250000, 'VNPAY', 1, 240175, 'Thanh toán thành công số tiền 250000.0', '2025-04-10 10:05:46'),
(2, 4, 'BANKING', 2500000, 'VNPAY', 1, 361377, 'Thanh toán thành công số tiền 2500000.0', '2025-06-01 01:26:09'),
(3, 5, 'BANKING', 250000, 'VNPAY', 1, 971993, 'Thanh toán thành công số tiền 250000.0', '2025-07-17 21:50:34'),
(4, 7, 'BANKING', 250000, 'VNPAY', 1, 358873, 'Thanh toán thành công số tiền 250000.0', '2025-07-18 12:06:28'),
(5, 10, 'BANKING', 250000, 'VNPAY', 1, 295078, 'Thanh toán thành công số tiền 250000.0', '2025-07-19 17:01:34'),
(6, 11, 'BANKING', 250000, 'VNPAY', 1, 111846, 'Thanh toán thành công số tiền 250000.0', '2025-07-20 12:32:39');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `testID` int(11) NOT NULL,
  `question_type` int(11) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `testID`, `question_type`, `content`) VALUES
(1, 1, 1, '1 + 1 = ?'),
(3, 1, 2, 'Ai đẹp trai nhất'),
(4, 2, 1, 'Ai là tác giả bài thơ Việt Bắc'),
(5, 2, 1, 'Quang Trung là Nguyễn Huệ đúng hay sai'),
(6, 2, 2, 'Tả con mèo'),
(7, 3, 1, 'Hello, ... ?');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `seeker_id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `satisfied` tinyint(1) NOT NULL,
  `good_message` text NOT NULL,
  `reason` text NOT NULL,
  `improve` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `seeker_id`, `employer_id`, `rating`, `satisfied`, `good_message`, `reason`, `improve`, `created_at`, `status`) VALUES
(2, 65, 1, 4, 1, 'cc', 'cc', 'ccc', '2025-04-06 09:47:05', 1),
(3, 87, 1, 5, 1, 'Môi trường và đồng nghiệp thân thiệt', 'Làm việc ở đây vui lắm', 'Không có gì để cải thiện !!!!', '2025-04-06 09:55:28', 1),
(4, 65, 1, 5, 0, 'Môi trường và đồng nghiệp thân thiệt', 'Môi trường và đồng nghiệp thân thiệt', 'Môi trường và đồng nghiệp thân thiệt', '2025-04-06 09:57:13', 1),
(5, 65, 1, 1, 0, 'ccccccccccccccccc', 'cccccccccccccccccccccccccccc', 'cccccccccccccccccccccc', '2025-04-06 09:57:39', 0),
(6, 65, 1, 5, 0, 'aaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaa', '2025-04-06 09:58:12', 0),
(7, 65, 1, 4, 1, 'ccccccccccccccccccccc', 'cccccccccccccccccccccccccccccccc', 'cccccccccccccccccc', '2025-04-06 09:59:44', 0),
(8, 65, 1, 5, 1, 'Học hỏi được nhiều điều', 'Làm việc ở đây rất vui', 'Không có gì cả. Mọi thứ đều ổn', '2025-04-18 06:06:35', 0),
(9, 65, 1, 5, 1, 'Công ty rất tốt, đáng để học tập.', 'Công ty rất tốt, đáng để học tập.', 'Công ty rất tốt, đáng để học tập.', '2025-04-27 20:05:49', 0);

-- --------------------------------------------------------

--
-- Table structure for table `seeker`
--

CREATE TABLE `seeker` (
  `id` int(11) NOT NULL,
  `full_name` text DEFAULT NULL,
  `phone` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(250) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `avatar` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seeker`
--

INSERT INTO `seeker` (`id`, `full_name`, `phone`, `address`, `dob`, `gender`, `status`, `update_at`, `avatar`) VALUES
(65, 'Hoàng Tú Nguyễn', '+84916700827', 'Phường Hùng Vương, Thị xã Phú Thọ, Tỉnh Phú Thọ', '2025-02-14', 'female', 1, NULL, '5e48b28ddd444d73837a2c1b25e2b7a8.jpg'),
(87, 'John Doe 123', '123456789', '123 Street', '2003-05-12', 'male', 1, NULL, 'c92231668dac49358afe7955a317e7b7.jpg'),
(97, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(108, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `skill`
--

CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `skill_parent_id` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `userID` int(11) NOT NULL,
  `code` varchar(250) NOT NULL,
  `time` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `title`, `description`, `userID`, `code`, `time`) VALUES
(1, 'Toán', 'aaa', 1, 'toan', 0),
(2, 'Ngữ Văn', 'aa', 1, 'nguvan', 0),
(3, 'Kiểm tra Java', 'Kiểm tra Java', 1, 'java', 10),
(4, 'Test UI', 'Test UI', 1, 'UI', 10);

-- --------------------------------------------------------

--
-- Table structure for table `testhistory`
--

CREATE TABLE `testhistory` (
  `id` int(11) NOT NULL,
  `testID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `time_submit` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `user_type` int(11) NOT NULL,
  `email` text NOT NULL,
  `created` date NOT NULL DEFAULT current_timestamp(),
  `security_code` text NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `user_type`, `email`, `created`, `security_code`, `status`) VALUES
(1, 'acc1', '$2a$10$dYXwldJJvKQsP8eeym8UI.Xv006GA.txAKYf9TtGVrJSXn7p5WbHO', 2, 'acc1@gmail.com', '2021-01-18', '', 1),
(2, 'fptsoftware', '$2a$10$dYXwldJJvKQsP8eeym8UI.Xv006GA.txAKYf9TtGVrJSXn7p5WbHO', 2, 'contact@fptsoftware.com', '2025-04-28', '123456', 1),
(3, 'tikicorporation', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@tiki.vn', '2025-05-02', '123456', 1),
(4, 'axonactive', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@axonactive.com', '2025-05-02', '123456', 1),
(5, 'kmstechnology', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@kms-technology.com', '2025-05-02', '123456', 1),
(6, 'haravan', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@haravan.com', '2025-05-02', '123456', 1),
(7, 'tikinow', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@tiki.vn', '2025-05-02', '123456', 1),
(8, 'viettelsolutions', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@viettelsolutions.vn', '2025-05-02', '123456', 1),
(9, 'tmasolutions', '$2a$10$dYXwldJJvKQsP8eeym8UI.Xv006GA.txAKYf9TtGVrJSXn7p5WbHO', 2, 'info@tmasolutions.com', '2025-05-02', '234567', 1),
(10, 'rikkeisoft', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@rikkeisoft.com', '2025-05-02', '345678', 1),
(11, 'logigearvn', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@logigear.com', '2025-05-02', '456789', 1),
(12, 'tekexpertsvn', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@tekexperts.com', '2025-05-02', '567890', 1),
(13, 'suninc', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@sun-asterisk.com', '2025-05-02', '678901', 1),
(14, 'nashtechvn', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@nashtechglobal.com', '2025-05-02', '789012', 1),
(15, 'fpttelecom', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@fpttelecom.vn', '2025-05-02', '890123', 1),
(16, 'cmcglobal', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@cmcglobal.com', '2025-05-02', '901234', 1),
(17, 'smartosc', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@smartosc.com', '2025-05-02', '012345', 1),
(18, '10cloudsvietnam', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@10clouds.com', '2025-05-02', '123456', 1),
(19, 'siiasiavn', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@siiasia.com', '2025-05-02', '234567', 1),
(20, 'digiworld', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@digiworld.com', '2025-05-02', '345678', 1),
(21, 'vinai', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@vinai.io', '2025-05-02', '456789', 1),
(22, 'cyberlogitec', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@cyberlogitec.com', '2025-05-02', '567890', 1),
(23, 'globalcybersoft', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@globalcybersoft.com', '2025-05-02', '678901', 1),
(24, 'halotech', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@halotech.vn', '2025-05-02', '789012', 1),
(25, 'kiotviet', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'info@kiotviet.vn', '2025-05-02', '890123', 1),
(26, 'vccorp', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'support@vccorp.vn', '2025-05-02', '901234', 1),
(27, 'ggroup', '$2a$10$EG2UXiZKO.GV.j2mM.0u4CA6BKV8Z897TNkZ...', 2, 'contact@ggroup.vn', '2025-05-02', '012345', 1),
(65, 'hoangtu', '$2a$10$dYXwldJJvKQsP8eeym8UI.Xv006GA.txAKYf9TtGVrJSXn7p5WbHO', 1, 'tuleep.pf@gmail.com', '2025-01-21', '741234', 1),
(66, 'nhà tuyển dụng', '$2a$10$c/LXuS9URRbeD6clfDH2nOBKYdp6goWh3Inemjdl9AMFEVmSg.Hsi', 2, 'test@gmail.com', '2023-04-12', '123456', 0),
(67, 'seeker 8', '$2a$10$nwaA.rUYFCwfjaNCDYD85O7ia1wEk1J/GWh/KUmv9AxVC/u8dZ1C6', 1, 'gmail1123', '2023-04-12', '123456', 0),
(71, 'seeker 9', '$2a$10$sDdFnQ6WUTGYdmUv2dUd6uHp/jsofA0mHi7U/Md/woKn7o9J3ZeD6', 1, 'gmail1123', '2023-04-12', '123456', 0),
(87, 'employee', '$2a$10$/6qPESzfhr8pfWD19qUw4e23LNfEaecet1CpkqiXORsOPzhlROCUO', 1, 'atun123456789cu@gmail.com', '2025-01-27', '607070', 1),
(88, 'Nhà tuyển dụng YES4ALL', '$2a$10$/6qPESzfhr8pfWD19qUw4e23LNfEaecet1CpkqiXORsOPzhlROCUO', 2, 'acc@gmail.com', '2025-04-23', '730979', 1),
(90, 'superadmin', '$2a$10$dYXwldJJvKQsP8eeym8UI.Xv006GA.txAKYf9TtGVrJSXn7p5WbHO', 0, 'superadmin@gmail.com', '2025-05-16', '123456', 1),
(97, 'Hoàng Tú', '$2a$10$SQmjDWAUDoeJZj8VoUBXn.fgYz8Kx7CodIeEoK/cVBjRMRs72W7IO', 1, 'hoangtunguyen12052003@gmail.com', '2025-07-17', '781197', 1),
(108, 'tuhoangnguyen2003', '$2a$10$OHMNbXcJQR/itSu3sdbySeh8YtDCELN9Q9Xd9ZaZW6l9GGIAvlth6', 1, 'tuhoangnguyen2003@gmail.com', '2025-07-20', 'google', 1);

-- --------------------------------------------------------

--
-- Table structure for table `worktype`
--

CREATE TABLE `worktype` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `worktype`
--

INSERT INTO `worktype` (`id`, `name`, `status`) VALUES
(1, 'Toàn thời gian', 1),
(2, 'Bán thời gian', 1),
(3, 'Thực tập', 1),
(4, 'Làm tại nhà', 1),
(5, 'Thời vụ', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `questionID` (`questionID`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seeker_id` (`seeker_id`),
  ADD KEY `job_id` (`job_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receiver_id` (`receiver_id`),
  ADD KEY `sender_id` (`sender_id`);

--
-- Indexes for table `cv`
--
ALTER TABLE `cv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seeker_id` (`seeker_id`);

--
-- Indexes for table `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employermembership`
--
ALTER TABLE `employermembership`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `experience`
--
ALTER TABLE `experience`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `job_id` (`job_id`),
  ADD KEY `seeker_id` (`seeker_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`),
  ADD KEY `seeker_id` (`seeker_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `interview`
--
ALTER TABLE `interview`
  ADD PRIMARY KEY (`id`),
  ADD KEY `application_id` (`application_id`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employer_id` (`employer_id`),
  ADD KEY `experience_id` (`experience_id`),
  ADD KEY `location_id` (`location_id`),
  ADD KEY `work_type_id` (`work_type_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_cv_job` (`cv_id`,`job_id`),
  ADD KEY `cv_id` (`cv_id`),
  ADD KEY `job_id` (`job_id`);

--
-- Indexes for table `membership`
--
ALTER TABLE `membership`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `testID` (`testID`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seeker_id` (`seeker_id`),
  ADD KEY `employer_id` (`employer_id`);

--
-- Indexes for table `seeker`
--
ALTER TABLE `seeker`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `testhistory`
--
ALTER TABLE `testhistory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `testID` (`testID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `worktype`
--
ALTER TABLE `worktype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;

--
-- AUTO_INCREMENT for table `cv`
--
ALTER TABLE `cv`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `employer`
--
ALTER TABLE `employer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `employermembership`
--
ALTER TABLE `employermembership`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `experience`
--
ALTER TABLE `experience`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `interview`
--
ALTER TABLE `interview`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=270;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=399;

--
-- AUTO_INCREMENT for table `membership`
--
ALTER TABLE `membership`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=371;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `seeker`
--
ALTER TABLE `seeker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `skill`
--
ALTER TABLE `skill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `testhistory`
--
ALTER TABLE `testhistory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `worktype`
--
ALTER TABLE `worktype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionID`) REFERENCES `question` (`id`);

--
-- Constraints for table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `application_ibfk_1` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`),
  ADD CONSTRAINT `application_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cv`
--
ALTER TABLE `cv`
  ADD CONSTRAINT `cv_ibfk_1` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`);

--
-- Constraints for table `employer`
--
ALTER TABLE `employer`
  ADD CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  ADD CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`);

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `interview`
--
ALTER TABLE `interview`
  ADD CONSTRAINT `interview_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`);

--
-- Constraints for table `job`
--
ALTER TABLE `job`
  ADD CONSTRAINT `job_ibfk_1` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `job_ibfk_2` FOREIGN KEY (`experience_id`) REFERENCES `experience` (`id`),
  ADD CONSTRAINT `job_ibfk_3` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  ADD CONSTRAINT `job_ibfk_4` FOREIGN KEY (`work_type_id`) REFERENCES `worktype` (`id`),
  ADD CONSTRAINT `job_ibfk_5` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`cv_id`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`testID`) REFERENCES `test` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`seeker_id`) REFERENCES `seeker` (`id`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);

--
-- Constraints for table `seeker`
--
ALTER TABLE `seeker`
  ADD CONSTRAINT `seeker_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`id`);

--
-- Constraints for table `testhistory`
--
ALTER TABLE `testhistory`
  ADD CONSTRAINT `testhistory_ibfk_1` FOREIGN KEY (`testID`) REFERENCES `test` (`id`),
  ADD CONSTRAINT `testhistory_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
