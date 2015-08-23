-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: pizza_delivery_db
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accumulative_card`
--

DROP TABLE IF EXISTS `accumulative_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accumulative_card` (
  `id_accumulative_card` int(11) NOT NULL AUTO_INCREMENT,
  `sum_accumulative_card` decimal(10,2) NOT NULL DEFAULT '0.00',
  `id_customer_FK` int(11) NOT NULL,
  PRIMARY KEY (`id_accumulative_card`),
  KEY `id_customer_FK` (`id_customer_FK`),
  CONSTRAINT `accumulative_card_ibfk_1` FOREIGN KEY (`id_customer_FK`) REFERENCES `customer` (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accumulative_card`
--

LOCK TABLES `accumulative_card` WRITE;
/*!40000 ALTER TABLE `accumulative_card` DISABLE KEYS */;
INSERT INTO `accumulative_card` VALUES (1,280.00,1);
/*!40000 ALTER TABLE `accumulative_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id_address` int(11) NOT NULL AUTO_INCREMENT,
  `id_city_FK` int(11) NOT NULL,
  `id_street_FK` int(11) NOT NULL,
  `building` varchar(10) NOT NULL,
  `apartment` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_address`),
  KEY `id_city_FK` (`id_city_FK`),
  KEY `id_street_FK` (`id_street_FK`),
  CONSTRAINT `address_fk_1` FOREIGN KEY (`id_city_FK`) REFERENCES `city` (`id_city`),
  CONSTRAINT `address_fk_2` FOREIGN KEY (`id_street_FK`) REFERENCES `street` (`id_street`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (48,2,30,'1','123');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id_city` int(11) NOT NULL AUTO_INCREMENT,
  `name_city` varchar(50) NOT NULL,
  PRIMARY KEY (`id_city`),
  UNIQUE KEY `name_city_UNIQUE` (`name_city`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Kharkiv'),(2,'Kyiv'),(3,'Lviv'),(4,'Odessa'),(5,'Vinnitsa');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `id_address_customer_FK` int(11) DEFAULT NULL,
  `id_user_FK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_customer`),
  KEY `id_address_customer_FK` (`id_address_customer_FK`),
  KEY `id_user_FK_idx` (`id_user_FK`),
  CONSTRAINT `customer_fk_1` FOREIGN KEY (`id_address_customer_FK`) REFERENCES `address` (`id_address`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`id_user_FK`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Anna','Oliinyk',NULL,NULL,1),(2,'Bob','Dou',NULL,NULL,2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `datetime_order` datetime NOT NULL,
  `sum_order` double NOT NULL,
  `id_customer_FK` int(11) NOT NULL,
  `id_address_order_FK` int(11) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `id_customer_FK` (`id_customer_FK`),
  KEY `id_address_order_FK` (`id_address_order_FK`),
  CONSTRAINT `order_fk_2` FOREIGN KEY (`id_customer_FK`) REFERENCES `customer` (`id_customer`),
  CONSTRAINT `order_fk_3` FOREIGN KEY (`id_address_order_FK`) REFERENCES `address` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (26,'2015-08-24 01:26:25',280,1,48);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `id_pizza` int(11) NOT NULL AUTO_INCREMENT,
  `name_pizza` varchar(50) NOT NULL,
  `price_pizza` decimal(10,2) NOT NULL,
  `type_pizza` varchar(20) NOT NULL,
  PRIMARY KEY (`id_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,'Margarita',80.00,'Vegetarian'),(2,'Americana',100.00,'Meat'),(3,'4Cheese',120.00,'Vegetarian'),(4,'Sea',150.00,'Sea');
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_in_order`
--

DROP TABLE IF EXISTS `pizza_in_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_in_order` (
  `id_pizza_in_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_pizza_FK` int(11) NOT NULL,
  `id_order_FK` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id_pizza_in_order`),
  KEY `id_pizza_FK` (`id_pizza_FK`),
  KEY `id_order_FK` (`id_order_FK`),
  CONSTRAINT `pizza_in_order_fk_1` FOREIGN KEY (`id_pizza_FK`) REFERENCES `pizza` (`id_pizza`),
  CONSTRAINT `pizza_in_order_fk_2` FOREIGN KEY (`id_order_FK`) REFERENCES `order` (`id_order`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_in_order`
--

LOCK TABLES `pizza_in_order` WRITE;
/*!40000 ALTER TABLE `pizza_in_order` DISABLE KEYS */;
INSERT INTO `pizza_in_order` VALUES (39,1,26,1),(40,2,26,2);
/*!40000 ALTER TABLE `pizza_in_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(40) NOT NULL,
  `id_user_FK` int(11) NOT NULL,
  PRIMARY KEY (`id_role`),
  KEY `id_user_FK_idx` (`id_user_FK`),
  CONSTRAINT `id_user_FK` FOREIGN KEY (`id_user_FK`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_USER',1),(3,'ROLE_USER',2);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `street`
--

DROP TABLE IF EXISTS `street`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `street` (
  `id_street` int(11) NOT NULL AUTO_INCREMENT,
  `name_street` varchar(50) NOT NULL,
  PRIMARY KEY (`id_street`),
  UNIQUE KEY `name_street_UNIQUE` (`name_street`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `street`
--

LOCK TABLES `street` WRITE;
/*!40000 ALTER TABLE `street` DISABLE KEYS */;
INSERT INTO `street` VALUES (30,'TestStreet1');
/*!40000 ALTER TABLE `street` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name_user` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Anna','123',''),(2,'Bob','456','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-24  1:27:51
