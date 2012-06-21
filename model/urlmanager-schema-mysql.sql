-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: urlmanager
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

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
-- Table structure for table `LOCALE`
--

DROP TABLE IF EXISTS `LOCALE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOCALE` (
  `Id_Locale` bigint(20) NOT NULL AUTO_INCREMENT,
  `Country` varchar(255) COLLATE utf8_bin NOT NULL,
  `Language` varchar(255) COLLATE utf8_bin NOT NULL,
  `Long_Country` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Long_Language` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Id_Locale`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REQUEST`
--

DROP TABLE IF EXISTS `REQUEST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REQUEST` (
  `Id_Request` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id_Request`),
  UNIQUE KEY `Label` (`Label`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REQUEST_LOCALE`
--

DROP TABLE IF EXISTS `REQUEST_LOCALE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REQUEST_LOCALE` (
  `Id_Request` bigint(20) NOT NULL,
  `Id_Locale` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Request`,`Id_Locale`),
  KEY `FKA8F7362AF63CD2F1` (`Id_Request`),
  KEY `FKA8F7362A9E118282` (`Id_Locale`),
  CONSTRAINT `FKA8F7362A9E118282` FOREIGN KEY (`Id_Locale`) REFERENCES `LOCALE` (`Id_Locale`),
  CONSTRAINT `FKA8F7362AF63CD2F1` FOREIGN KEY (`Id_Request`) REFERENCES `REQUEST` (`Id_Request`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REQUEST_TAG`
--

DROP TABLE IF EXISTS `REQUEST_TAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REQUEST_TAG` (
  `Id_Request` bigint(20) NOT NULL,
  `Id_Tag` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Request`,`Id_Tag`),
  KEY `FKC4F421AAF63CD2F1` (`Id_Request`),
  KEY `FKC4F421AAEA818E12` (`Id_Tag`),
  CONSTRAINT `FKC4F421AAEA818E12` FOREIGN KEY (`Id_Tag`) REFERENCES `TAG` (`Id_Tag`),
  CONSTRAINT `FKC4F421AAF63CD2F1` FOREIGN KEY (`Id_Request`) REFERENCES `REQUEST` (`Id_Request`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TAG`
--

DROP TABLE IF EXISTS `TAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAG` (
  `Id_Tag` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id_Tag`),
  UNIQUE KEY `Label` (`Label`)
) ENGINE=InnoDB AUTO_INCREMENT=3079 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WEBPAGE`
--

DROP TABLE IF EXISTS `WEBPAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WEBPAGE` (
  `Id_Webpage` bigint(20) NOT NULL AUTO_INCREMENT,
  `Is_Root` bit(1) NOT NULL,
  `URL` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id_Webpage`),
  UNIQUE KEY `URL` (`URL`)
) ENGINE=InnoDB AUTO_INCREMENT=3667 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WEBPAGE_LOCALE`
--

DROP TABLE IF EXISTS `WEBPAGE_LOCALE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WEBPAGE_LOCALE` (
  `Id_Webpage` bigint(20) NOT NULL,
  `Id_Locale` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Webpage`,`Id_Locale`),
  KEY `FKDBDF5E563114FEED` (`Id_Webpage`),
  KEY `FKDBDF5E569E118282` (`Id_Locale`),
  CONSTRAINT `FKDBDF5E563114FEED` FOREIGN KEY (`Id_Webpage`) REFERENCES `WEBPAGE` (`Id_Webpage`),
  CONSTRAINT `FKDBDF5E569E118282` FOREIGN KEY (`Id_Locale`) REFERENCES `LOCALE` (`Id_Locale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WEBPAGE_TAG`
--

DROP TABLE IF EXISTS `WEBPAGE_TAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WEBPAGE_TAG` (
  `Id_Webpage` bigint(20) NOT NULL,
  `Id_Tag` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Webpage`,`Id_Tag`),
  KEY `FKD1CC8FE3114FEED` (`Id_Webpage`),
  KEY `FKD1CC8FEEA818E12` (`Id_Tag`),
  CONSTRAINT `FKD1CC8FE3114FEED` FOREIGN KEY (`Id_Webpage`) REFERENCES `WEBPAGE` (`Id_Webpage`),
  CONSTRAINT `FKD1CC8FEEA818E12` FOREIGN KEY (`Id_Tag`) REFERENCES `TAG` (`Id_Tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-21  9:08:07
