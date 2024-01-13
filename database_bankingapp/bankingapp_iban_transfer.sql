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
-- Table structure for table `iban_transfer`
--

DROP TABLE IF EXISTS `iban_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iban_transfer` (
  `idtransfer` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `payment_details` varchar(50) NOT NULL,
  `iban` varchar(34) NOT NULL COMMENT 'not null if type = "IBAN"\n',
  `beneficiary_name` varchar(45) NOT NULL COMMENT 'not null if type = "IBAN"\n',
  `idaccount` int NOT NULL,
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`idtransfer`),
  KEY `idaccount_fk_idx` (`idaccount`),
  CONSTRAINT `idaccount_iban_fk` FOREIGN KEY (`idaccount`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iban_transfer`
--

LOCK TABLES `iban_transfer` WRITE;
/*!40000 ALTER TABLE `iban_transfer` DISABLE KEYS */;
INSERT INTO `iban_transfer` VALUES (4,1500,'Vacation','RO6YSTCH8I0JKLMNOPQRSTUVWX','John Smith',1,'2024-01-04 19:13:38'),(6,47,'toys','RO2XSTCH4D6E8FGHIJKLMNOPQ','Elena Williams',1,'2024-01-04 19:22:24'),(7,150,'Online Package','RO0NSTCH3JKLMPQRSTUVWXYZA','Mia Kunis',9,'2024-01-05 16:21:36'),(8,300,'New bike','RO7KSTCH9FGHJKLMNOPQRSTU','Emlity Bronte',8,'2024-05-15 14:30:00'),(9,575,'Concert tickets','RO5MSTCH1O3PQABCDEFGHIJKL','Jimmy Ramirez',7,'2023-09-28 09:45:20'),(10,140,'Dragon food','ROMHSTCHLJ23O23N2KJUB1DSR3','Daenerys Targaryen',7,'2024-02-10 18:12:45'),(11,210,'Concert tickets','RO7KSTCH9FGHJKLMNOPQRSTU','Selena Gomez',4,'2023-11-05 22:00:00'),(12,100,'orice','RO7KSTCH9FGHJKLMNOPQRSTU','Alecsia Coman',1,'2024-01-07 19:02:25');
/*!40000 ALTER TABLE `iban_transfer` ENABLE KEYS */;
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
