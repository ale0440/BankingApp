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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `idcustomer` int NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `cnp` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email_address` varchar(45) NOT NULL,
  PRIMARY KEY (`idcustomer`),
  UNIQUE KEY `cnp_UNIQUE` (`cnp`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Calin','Anderson','8754302198642','123 Main Street','987654555','owen.anderson@student.chest'),(2,'Isabella','Williams','3625410987531','456 Elm Avenue','1234567890','isa.williams@student.chest'),(3,'Xavier','Rodriguez','9542103876520','789 Oak Lane','8765432109','xavier.rodriguez@student.chest'),(4,'Lily','Thompson','1476032859643','210 Maple Boulevard','2345678901','lili.thompson@student.chest'),(5,'Ethan','Mitchell','6894321752014','567 Pine Drive','1098765432','ethan.mitchell@student.chest'),(6,'Sophia','Ramirez','5201983472658','890 Cedar Street','5432109875','sophia.ramirez@student.chest'),(7,'Caleb','Cooper','4368912075123','345 Birch Road','2109876543','caleb.cooper@student.chest'),(8,'Ava','Turner','7012543896128','678 Walnut Place','2365478912','ava.turner@student.chest'),(9,'Noah','Foster','8123456709234','901 Spruce Court','4567890123','noah.foster@student.chest'),(10,'Mia','Patel','2301987654312','234 Sycamore Lane','7654321098','mia.patel@student.chest'),(11,'dlaks','ldk;as;','1234567891234','ldkads','1234567891','ldjak'),(12,'Alecsia','Coman','5784962134561','11 Traian','2457896521','comanalecsiaa@gmail.com'),(13,'Andreea','Moldovan','2356984751236','744 Rastatt','5874965231','andreea.moldovan@yahoo.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
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
