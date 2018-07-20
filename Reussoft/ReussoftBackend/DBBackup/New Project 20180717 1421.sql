-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	8.0.11


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema makservicedb
--

CREATE DATABASE IF NOT EXISTS makservicedb;
USE makservicedb;

--
-- Definition of table `Itemsortiestock`
--

DROP TABLE IF EXISTS `Itemsortiestock`;
CREATE TABLE `Itemsortiestock` (
  `idSortieStock` varchar(45) DEFAULT NULL,
  `idProduit` varchar(45) DEFAULT NULL,
  `idPrixVenteProduit` int(10) unsigned DEFAULT NULL,
  `quantite` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Itemsortiestock`
--

/*!40000 ALTER TABLE `Itemsortiestock` DISABLE KEYS */;
/*!40000 ALTER TABLE `Itemsortiestock` ENABLE KEYS */;


--
-- Definition of table `categorieproduit`
--

DROP TABLE IF EXISTS `categorieproduit`;
CREATE TABLE `categorieproduit` (
  `id` varchar(45) NOT NULL,
  `description` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `descriptionAbregee` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorieproduit`
--

/*!40000 ALTER TABLE `categorieproduit` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorieproduit` ENABLE KEYS */;


--
-- Definition of table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` varchar(45) NOT NULL,
  `entreprise` varchar(45) DEFAULT NULL,
  `nom` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_client_shop` (`idShop`),
  CONSTRAINT `FK_client_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Definition of table `collaborateur`
--

DROP TABLE IF EXISTS `collaborateur`;
CREATE TABLE `collaborateur` (
  `id` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `idProfilUtilisateur` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `nom` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `postnom` varchar(45) DEFAULT NULL,
  `surnom` varchar(45) DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  `nomUtilisateur` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_collaborateur_profilutilisateur` (`idProfilUtilisateur`),
  KEY `FK_collaborateur_shop` (`idShop`),
  CONSTRAINT `FK_collaborateur_profilutilisateur` FOREIGN KEY (`idProfilUtilisateur`) REFERENCES `profilutilisateur` (`id`),
  CONSTRAINT `FK_collaborateur_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `collaborateur`
--

/*!40000 ALTER TABLE `collaborateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `collaborateur` ENABLE KEYS */;


--
-- Definition of table `dispatch`
--

DROP TABLE IF EXISTS `dispatch`;
CREATE TABLE `dispatch` (
  `id` varchar(45) NOT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  `valide` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_dispatch_shop` (`idShop`),
  CONSTRAINT `FK_dispatch_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dispatch`
--

/*!40000 ALTER TABLE `dispatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispatch` ENABLE KEYS */;


--
-- Definition of table `entreestock`
--

DROP TABLE IF EXISTS `entreestock`;
CREATE TABLE `entreestock` (
  `id` varchar(45) NOT NULL,
  `idFournisseur` varchar(45) DEFAULT NULL,
  `dateHeure` datetime DEFAULT NULL,
  `ValeuTotalCoutUSD` decimal(10,2) DEFAULT NULL,
  `ValeurTotalCoutFC` decimal(10,2) DEFAULT NULL,
  `ValeurTauxMonnaie` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_entreestock_fournisseur` (`idFournisseur`),
  CONSTRAINT `FK_entreestock_fournisseur` FOREIGN KEY (`idFournisseur`) REFERENCES `fournisseur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entreestock`
--

/*!40000 ALTER TABLE `entreestock` DISABLE KEYS */;
/*!40000 ALTER TABLE `entreestock` ENABLE KEYS */;


--
-- Definition of table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE `fournisseur` (
  `id` varchar(45) NOT NULL,
  `entreprise` varchar(45) DEFAULT NULL,
  `responsable` varchar(45) DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fournisseur`
--

/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;


--
-- Definition of table `itemdispatch`
--

DROP TABLE IF EXISTS `itemdispatch`;
CREATE TABLE `itemdispatch` (
  `idDispatch` varchar(45) NOT NULL,
  `idProduit` varchar(45) NOT NULL,
  `idShop` varchar(45) NOT NULL,
  `quantite` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `itemdispatch`
--

/*!40000 ALTER TABLE `itemdispatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemdispatch` ENABLE KEYS */;


--
-- Definition of table `itementreestock`
--

DROP TABLE IF EXISTS `itementreestock`;
CREATE TABLE `itementreestock` (
  `idEntreeStock` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idProduit` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prixAchat` decimal(10,2) DEFAULT NULL,
  `quantite` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `itementreestock`
--

/*!40000 ALTER TABLE `itementreestock` DISABLE KEYS */;
/*!40000 ALTER TABLE `itementreestock` ENABLE KEYS */;


--
-- Definition of table `prixachatproduit`
--

DROP TABLE IF EXISTS `prixachatproduit`;
CREATE TABLE `prixachatproduit` (
  `id` varchar(45) NOT NULL,
  `idProduit` varchar(45) DEFAULT NULL,
  `valeurUSD` decimal(10,2) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `prixachatproduit`
--

/*!40000 ALTER TABLE `prixachatproduit` DISABLE KEYS */;
/*!40000 ALTER TABLE `prixachatproduit` ENABLE KEYS */;


--
-- Definition of table `prixventeproduitshop`
--

DROP TABLE IF EXISTS `prixventeproduitshop`;
CREATE TABLE `prixventeproduitshop` (
  `idProduit` varchar(45) NOT NULL,
  `idShop` varchar(45) NOT NULL,
  `valeurUSD` decimal(10,2) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `prixventeproduitshop`
--

/*!40000 ALTER TABLE `prixventeproduitshop` DISABLE KEYS */;
/*!40000 ALTER TABLE `prixventeproduitshop` ENABLE KEYS */;


--
-- Definition of table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE `produit` (
  `id` varchar(45) NOT NULL,
  `idReseau` varchar(45) DEFAULT NULL,
  `description` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `idCategorieProduit` varchar(45) DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_produit_reseau` (`idReseau`),
  CONSTRAINT `FK_produit_reseau` FOREIGN KEY (`idReseau`) REFERENCES `reseau` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produit`
--

/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;


--
-- Definition of table `profilutilisateur`
--

DROP TABLE IF EXISTS `profilutilisateur`;
CREATE TABLE `profilutilisateur` (
  `id` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `descriptionAbregee` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profilutilisateur`
--

/*!40000 ALTER TABLE `profilutilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `profilutilisateur` ENABLE KEYS */;


--
-- Definition of table `reseau`
--

DROP TABLE IF EXISTS `reseau`;
CREATE TABLE `reseau` (
  `id` varchar(45) NOT NULL,
  `nom` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `nomAbrege` varchar(15) DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reseau`
--

/*!40000 ALTER TABLE `reseau` DISABLE KEYS */;
/*!40000 ALTER TABLE `reseau` ENABLE KEYS */;


--
-- Definition of table `sessionutilisateur`
--

DROP TABLE IF EXISTS `sessionutilisateur`;
CREATE TABLE `sessionutilisateur` (
  `id` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `idCollaborateur` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `actionEntree` tinyint(3) unsigned DEFAULT NULL,
  `dateHeure` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_sessionutilisateur_collaborateur` (`idCollaborateur`),
  CONSTRAINT `FK_sessionutilisateur_collaborateur` FOREIGN KEY (`idCollaborateur`) REFERENCES `collaborateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessionutilisateur`
--

/*!40000 ALTER TABLE `sessionutilisateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessionutilisateur` ENABLE KEYS */;


--
-- Definition of table `shop`
--

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` varchar(45) NOT NULL,
  `nom` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `adresse` varchar(145) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shop`
--

/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;


--
-- Definition of table `sortiestock`
--

DROP TABLE IF EXISTS `sortiestock`;
CREATE TABLE `sortiestock` (
  `id` varchar(45) NOT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `idClient` varchar(45) DEFAULT NULL,
  `dateHeure` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `ValeurTauxMonnaie` decimal(10,2) DEFAULT NULL,
  `ValeuTotalVenteUSD` decimal(10,2) DEFAULT NULL,
  `ValeurTotalVenteFC` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_sortiestock_shop` (`idShop`),
  KEY `FK_sortiestock_client` (`idClient`),
  CONSTRAINT `FK_sortiestock_client` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_sortiestock_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sortiestock`
--

/*!40000 ALTER TABLE `sortiestock` DISABLE KEYS */;
/*!40000 ALTER TABLE `sortiestock` ENABLE KEYS */;


--
-- Definition of table `stockproduit`
--

DROP TABLE IF EXISTS `stockproduit`;
CREATE TABLE `stockproduit` (
  `idProduit` varchar(45) NOT NULL,
  `idShop` varchar(45) NOT NULL,
  `quantiteStock` decimal(10,0) DEFAULT NULL,
  `quantiteMaximumStock` decimal(10,0) DEFAULT NULL,
  `quantiteMinimumStock` decimal(10,0) DEFAULT NULL,
  `observation` varchar(245) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idProduit`,`idShop`),
  KEY `FK_stockproduit_shop` (`idShop`),
  CONSTRAINT `FK_stockproduit_produit` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  CONSTRAINT `FK_stockproduit_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockproduit`
--

/*!40000 ALTER TABLE `stockproduit` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockproduit` ENABLE KEYS */;


--
-- Definition of table `tauxcarte`
--

DROP TABLE IF EXISTS `tauxcarte`;
CREATE TABLE `tauxcarte` (
  `id` varchar(45) NOT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `valeur` decimal(10,0) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_tauxcarte_shop` (`idShop`),
  CONSTRAINT `FK_tauxcarte_shop` FOREIGN KEY (`idShop`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tauxcarte`
--

/*!40000 ALTER TABLE `tauxcarte` DISABLE KEYS */;
/*!40000 ALTER TABLE `tauxcarte` ENABLE KEYS */;


--
-- Definition of table `tauxmonnaie`
--

DROP TABLE IF EXISTS `tauxmonnaie`;
CREATE TABLE `tauxmonnaie` (
  `code` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `valeur` decimal(10,2) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tauxmonnaie`
--

/*!40000 ALTER TABLE `tauxmonnaie` DISABLE KEYS */;
/*!40000 ALTER TABLE `tauxmonnaie` ENABLE KEYS */;


--
-- Definition of table `version`
--

DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` varchar(45) NOT NULL,
  `xmajeur` int(5) unsigned DEFAULT NULL,
  `ymineur` int(5) unsigned DEFAULT NULL,
  `zcorrection` int(5) unsigned DEFAULT NULL,
  `description` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `idVersionAnterieur` varchar(45) DEFAULT NULL,
  `observation` varchar(245) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `version`
--

/*!40000 ALTER TABLE `version` DISABLE KEYS */;
/*!40000 ALTER TABLE `version` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
