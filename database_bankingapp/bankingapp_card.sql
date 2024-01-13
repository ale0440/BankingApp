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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `idcard` int NOT NULL,
  `card_number` varchar(19) DEFAULT NULL,
  `expiry_date` varchar(5) DEFAULT NULL,
  `cvv` varchar(3) DEFAULT NULL,
  `card_name` varchar(30) DEFAULT NULL,
  `payment_limit` double DEFAULT '1000',
  `withdraw_limit` double DEFAULT '1000',
  `status` varchar(7) DEFAULT 'activ',
  PRIMARY KEY (`idcard`),
  UNIQUE KEY `cvv_UNIQUE` (`cvv`),
  UNIQUE KEY `card_number_UNIQUE` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'1234-5678-9012-3456','05/25','119','Owen',500,550,'activ'),(2,'9876-5432-1098-7654','09/23','325','Isabella Williams',1500,200,'activ'),(3,'5555-1234-9876-4567','03/24','779','Xavier Rodriguez',2000,300,'activ'),(4,'8789-1564-4135-4879','07/28','475','Lily Thompson',2500,400,'activ'),(5,'3256-7890-1421-5678','25/24','372','Ethan Mitchell',3000,500,'activ'),(6,'9876-1234-5648-0912','12/25','589','Sophia Ramirez',3500,600,'inactiv'),(7,'4567-8901-2378-3412','03/26','124','Caleb Cooper',4000,700,'activ'),(8,'1234-5678-8901-4321','18/27','746','Ava Turner',4500,800,'inactiv'),(9,'8765-4321-1098-7654','09/28','931','Noah Foster',5000,900,'activ'),(10,'2143-6789-0532-1897','30/29','208','Mia Patel',5500,1000,'activ'),(12,'2686-9841-3336-3366','09/30','355','Alecsia Coman',1000,1000,'inactiv'),(13,'0864-7017-1866-5000','07/65','629','Andreea Moldovan',1000,1000,'activ');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
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
