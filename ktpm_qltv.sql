-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: ktpm_qltv
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` mediumtext COLLATE utf8_unicode_ci,
  `publishing_company` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publishing_year` date NOT NULL,
  `category` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_id_UNIQUE` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Kiểm thử phần mềm','Công nghệ thông tin','ABC','2022-04-22',1,'K21','2021-01-01','Tin học','Dương Hữu Thành'),(4,'An toàn hệ thống thông tin','An toàn hệ thống thông tin','Đại học Mở','2013-10-27',1,'K23','2011-10-20','Tin học','def'),(5,'Pháp luật Đại cương','Pháp luật Đại cương - Đại học Mở TP.HCM','Hồng Đức','2013-10-26',1,'K11','2012-12-29','Pháp luật','Bùi Ngọc Tuyền'),(6,'Cơ sở dữ liệu','Cơ sở dữ liệu (nhập môn)','XYZ','0035-04-05',1,'K22','0019-04-03','Tin học','Đại học mở'),(7,'Nhập môn tin học','Nhập môn tin học','XYZ','2013-10-29',1,'K24','2011-10-22','Tin học','ABC');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowbook`
--

DROP TABLE IF EXISTS `borrowbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowbook` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `amount` int NOT NULL,
  `card_id` int NOT NULL,
  `book_id` int NOT NULL,
  `fine` float DEFAULT '0',
  `status` int DEFAULT '1',
  PRIMARY KEY (`borrow_id`),
  UNIQUE KEY `borrow_id_UNIQUE` (`borrow_id`),
  KEY `fk_card_borrow_idx` (`card_id`),
  KEY `fk_book_borrow_idx` (`book_id`),
  CONSTRAINT `fk_book_borrow` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `fk_card_borrow` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowbook`
--

LOCK TABLES `borrowbook` WRITE;
/*!40000 ALTER TABLE `borrowbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `card_id` int NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `activation_date` date DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `total_money_penalty` float DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  CONSTRAINT `fk_card_reader` FOREIGN KEY (`card_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,1,'2022-04-24','2023-04-24',0),(2,1,'2022-04-19','2023-04-19',0);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_id_UNIQUE` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Công nghệ thông tin'),(2,'Đông Nam Á Học'),(3,'Ngôn ngữ Anh'),(4,'Luật'),(5,'Công nghệ sinh học'),(6,'Đào tạo đặc biệt');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `reader_id` int NOT NULL AUTO_INCREMENT,
  `reader_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '123456',
  `sex` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `object` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `user_role` enum('ADMIN','USER') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'USER',
  `department_id` int NOT NULL,
  PRIMARY KEY (`reader_id`),
  UNIQUE KEY `reader_id_UNIQUE` (`reader_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_department_reader_idx` (`department_id`),
  CONSTRAINT `fk_department_reader` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'Nguyễn Thị Thúy Ngân','admin','123','female','2001-07-21','195105002129ngan@ou.edu.vn','Bến Tre','0877900625','Sinh viên',1,'ADMIN',1),(2,'Phan Thị Diệu Hiền','hien01','123','female','2001-07-12','1951052049hien@ou.edu.vn','Bình Định','0297548975','Sinh Viên',1,'USER',4);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservebook`
--

DROP TABLE IF EXISTS `reservebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservebook` (
  `reserve_id` int NOT NULL AUTO_INCREMENT,
  `activation_date` date NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `amount` int NOT NULL,
  `card_id` int NOT NULL,
  `book_id` int NOT NULL,
  PRIMARY KEY (`reserve_id`,`card_id`),
  UNIQUE KEY `reserve_id_UNIQUE` (`reserve_id`),
  KEY `fk_card_reserve_idx` (`card_id`),
  KEY `fk_book_reserve_idx` (`book_id`),
  CONSTRAINT `fk_book_reserve` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `fk_card_reserve` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservebook`
--

LOCK TABLES `reservebook` WRITE;
/*!40000 ALTER TABLE `reservebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservebook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-24 22:26:25
