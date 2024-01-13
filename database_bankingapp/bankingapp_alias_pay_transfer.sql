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
-- Table structure for table `alias_pay_transfer`
--

DROP TABLE IF EXISTS `alias_pay_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alias_pay_transfer` (
  `idtransfer` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `payment_details` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `idaccount` int NOT NULL,
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`idtransfer`),
  KEY `idaccount_fk_idx` (`idaccount`),
  CONSTRAINT `idaccount_alias_fk` FOREIGN KEY (`idaccount`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alias_pay_transfer`
--

LOCK TABLES `alias_pay_transfer` WRITE;
/*!40000 ALTER TABLE `alias_pay_transfer` DISABLE KEYS */;
INSERT INTO `alias_pay_transfer` VALUES (3,350,'Online Package','0859632214',1,'2024-01-04 19:14:30'),(4,100,'Birthday gift','0236985741',1,'2024-01-04 19:22:46'),(5,500,'Birthday gift','2657481203',6,'2024-01-05 16:29:39'),(6,300,'Dog food','1236548795',2,'2024-05-15 14:30:00'),(7,150,'Toys','1235478962',3,'2023-09-28 09:45:20'),(8,75,'Christmas present','1258479632',5,'2024-02-10 18:12:45'),(9,485,'Groceries','0289640215',2,'2023-11-05 22:00:00'),(10,210,'Cinema tickets','0748596321',4,'2024-07-19 11:05:30');
/*!40000 ALTER TABLE `alias_pay_transfer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-13 14:58:52
