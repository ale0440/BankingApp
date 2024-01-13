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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `idtransaction` int NOT NULL AUTO_INCREMENT,
  `idaccount` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `trader` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idtransaction`),
  KEY `idaccount_idx` (`idaccount`),
  CONSTRAINT `idaccount_transaction_fk` FOREIGN KEY (`idaccount`) REFERENCES `account` (`idaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,35,'2024-01-02 08:45:30','New Yorker Sued Jeans-','5691 Men\'s and Women\'s Clothing','New Yorker Sued Jeans-, Rastatt, DE','payed'),(2,1,305.35,'2023-05-15 18:20:00','Zalando Store Karlsruhe','5651 Family Clothing Stores','Zalando Store Karlsruhe, Karlsruhe, DE','payed'),(3,1,401.37,'2022-11-07 12:30:45','Converse','5661 Shoe Store','Converse, Paris, FR','payed'),(4,1,100.88,'2024-03-28 09:10:15','TK Maxx','5311 Department Stores','TK Maxx, Karlsruhe, DE','payed'),(5,1,30,'2023-08-19 22:05:00','Remedium SRL','5912 Drug Stores and Pharmacies','Remedium SRL, Medias, RO','payed'),(6,2,32.7,'2022-12-10 15:55:30','Rompetrol RC216 C6','5541 Service Station','Rompetrol RC216 C6, Pecica, RO','payed'),(7,2,40,'2023-06-03 04:25:10','www.orange.ro contul-meu','4814 Telecommunication Service','www.orange.ro contul-meu, Bucuresti, RO','payed'),(8,3,82,'2024-02-14 20:00:00','Mida','5812 Eating Places','Mida, Medias, RO','payed'),(9,4,50.51,'2022-09-26 11:40:55','Market Mosnei','5411 Grocery Storess','Market Mosnei, Medias, RO','payed'),(10,5,10,'2022-09-26 11:40:55','Cartofisserie - Cluj N','5812 Eating Places','Cartofisserie - Cluj N, Cluj Napoca, RO','payed'),(11,1,60,'2023-05-15 18:20:00','TUCN','9990','TUCN, Cluj Napoca','recieved');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
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
