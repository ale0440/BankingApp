CREATE DATABASE  IF NOT EXISTS `bankingapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bankingapp`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bankingapp
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `withdraw`
--

DROP TABLE IF EXISTS `withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withdraw` (
  `idwithdraw` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `idaccount` int DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'completed',
  `withdraw_date` datetime DEFAULT NULL,
  PRIMARY KEY (`idwithdraw`),
  KEY `idaccount_withdraw_fk_idx` (`idaccount`),
  CONSTRAINT `idaccount_withdraw_fk` FOREIGN KEY (`idaccount`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw`
--

LOCK TABLES `withdraw` WRITE;
/*!40000 ALTER TABLE `withdraw` DISABLE KEYS */;
INSERT INTO `withdraw` VALUES (2,10,1,'9876543210','canceled','2024-01-01 22:33:43'),(3,10,1,'9876543210','canceled','2024-01-01 22:33:49'),(4,50,1,'9876543210','completed','2024-01-01 22:33:58'),(5,0,1,'9876543210','canceled','2024-01-01 22:47:40'),(6,0,1,'9876543210','completed','2024-01-01 22:48:07'),(7,0,1,'9876543210','canceled','2024-01-01 22:51:58'),(8,50,1,'9876543210','completed','2024-01-01 22:52:03'),(9,100,1,'9876543210','completed','2024-01-01 22:53:29'),(10,20,1,'9876543210','completed','2024-01-01 22:58:03'),(11,80,1,'9876543210','completed','2024-01-01 22:59:16'),(12,250,1,'9876543210','completed','2024-01-04 13:51:14'),(13,60,8,'2365478912','completed','2024-01-05 16:11:54'),(14,100,1,'9876543210','completed','2024-01-05 16:18:56'),(15,50,1,'9876543210','completed','2024-01-05 16:19:32'),(16,50,1,'9876543210','completed','2024-01-05 16:20:39'),(17,51,9,'4567890123','completed','2024-01-05 16:22:25'),(18,400,6,'5432109875','completed','2024-01-05 16:29:35'),(19,50,12,'2457896521','completed','2024-01-05 20:36:02'),(20,0,1,'9876543218','canceled','2024-01-07 19:02:18'),(21,0,1,'9876543218','canceled','2024-01-09 17:24:35');
/*!40000 ALTER TABLE `withdraw` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-13 14:58:51
