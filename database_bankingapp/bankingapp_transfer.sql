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
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `idtransfer` int NOT NULL AUTO_INCREMENT,
  `account1` int DEFAULT NULL,
  `account2` int DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `payment_details` varchar(50) DEFAULT 'Transfer StudentChest',
  `transfer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`idtransfer`),
  KEY `idaccount1_fk_idx` (`account1`),
  KEY `idaccount2_fk_idx` (`account2`),
  CONSTRAINT `idaccount1_fk` FOREIGN KEY (`account1`) REFERENCES `account` (`idaccount`),
  CONSTRAINT `idaccount2_fk` FOREIGN KEY (`account2`) REFERENCES `account` (`idaccount`),
  CONSTRAINT `chk_accounts` CHECK ((`account1` <> `account2`))
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,1,2,'send',50,'Transfer StudentChest','2022-01-01 12:00:00'),(2,1,3,'send',23,'Transfer StudentChest','2022-02-15 18:30:00'),(3,1,5,'send',100,'Transfer StudentChest','2022-03-20 08:45:00'),(7,4,1,'send',30,'Transfer StudentChest','2022-07-12 16:45:00'),(8,9,1,'send',133,'Transfer StudentChest','2022-08-08 09:15:00'),(9,1,3,'send',100,'Transfer StudentChest','2022-09-23 19:00:00'),(10,1,2,'send',150,'Transfer StudentChest','2022-10-17 11:50:00'),(24,2,1,'send',100,'Ia ti o paine','2022-11-30 22:05:00'),(25,2,8,'send',50,'ptr chipsuri','2022-12-24 05:40:00'),(26,1,3,'send',100,'ptr bere','2023-01-18 13:25:00'),(27,2,6,'send',500,'for your bday girlll','2023-02-03 17:55:00'),(28,4,2,'send',100,'ptr flori','2023-03-12 23:30:00'),(29,4,5,'request',50,'sa mi iau suc','2023-04-28 04:10:00'),(44,8,5,'send',60,'Transfer StudentChest','2024-01-05 13:39:29'),(45,8,7,'send',12,'Transfer StudentChest','2024-01-05 13:50:29'),(46,8,7,'send',20,'Transfer StudentChest','2024-01-05 16:12:02'),(47,8,7,'send',30,'Transfer StudentChest','2024-01-05 16:14:27'),(48,3,8,'send',10,'Transfer StudentChest','2024-01-05 16:17:06'),(49,1,2,'send',100,'Transfer StudentChest','2024-01-05 16:18:31'),(50,6,4,'send',200,'Detalii','2024-01-05 16:26:23'),(51,6,4,'request',50,'Transfer StudentChest','2024-01-05 16:27:00'),(53,1,9,'send',10,'Transfer StudentChest','2024-01-05 22:46:26'),(55,1,2,'send',150,'orice','2024-01-09 17:23:39'),(56,1,12,'send',120,'Transfer StudentChest','2024-01-09 18:55:43'),(58,1,12,'send',100,'Transfer StudentChest','2024-01-09 21:16:55');
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
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
