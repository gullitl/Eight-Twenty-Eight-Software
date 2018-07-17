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
-- Definition of table `Itemdispatch`
--

DROP TABLE IF EXISTS `Itemdispatch`;
CREATE TABLE `Itemdispatch` (
  `idDispatch` varchar(45) DEFAULT NULL,
  `idProduit` varchar(45) DEFAULT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `quantite` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Itemdispatch`
--

/*!40000 ALTER TABLE `Itemdispatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `Itemdispatch` ENABLE KEYS */;


--
-- Definition of table `Itementreestock`
--

DROP TABLE IF EXISTS `Itementreestock`;
CREATE TABLE `Itementreestock` (
  `idEntreeStock` varchar(45) NOT NULL,
  `idProduit` varchar(45) NOT NULL,
  `prixAchat` decimal(10,2) DEFAULT NULL,
  `quantite` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idEntreeStock`,`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Itementreestock`
--

/*!40000 ALTER TABLE `Itementreestock` DISABLE KEYS */;
/*!40000 ALTER TABLE `Itementreestock` ENABLE KEYS */;


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
-- Definition of table `Produit`
--

DROP TABLE IF EXISTS `Produit`;
CREATE TABLE `Produit` (
  `id` varchar(45) NOT NULL,
  `idShop` varchar(45) DEFAULT NULL,
  `valeur` decimal(10,2) DEFAULT NULL,
  `dateHeure` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `Produit`
--

/*!40000 ALTER TABLE `Produit` DISABLE KEYS */;
/*!40000 ALTER TABLE `Produit` ENABLE KEYS */;


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
INSERT INTO `categorieproduit` (`id`,`description`,`descriptionAbregee`) VALUES 
 ('1','Teste description','Tst da'),
 ('2','Flash unité','Fl unt');
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id`,`entreprise`,`nom`,`telephone`,`idShop`) VALUES 
 ('1','fhgfyhtfy','gydygdfgfjht','5646345544','2'),
 ('4','Une entreprise','Un client de plus','5646900000','2');
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `collaborateur`
--

/*!40000 ALTER TABLE `collaborateur` DISABLE KEYS */;
INSERT INTO `collaborateur` (`id`,`idProfilUtilisateur`,`idShop`,`nom`,`prenom`,`postnom`,`surnom`,`active`,`nomUtilisateur`,`motDePasse`) VALUES 
 ('1','1','1','Luzolo','Plamedi','Lusembo','Gulit',1,'plam.l','12345'),
 ('6','2',NULL,'Luzolo','Nadine','Nsambu','Nana',1,'nluzolo','12345'),
 ('9','2',NULL,'Luzolo','Patricia','Lusembo','Pati',1,'pluzolo','12345');
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
INSERT INTO `fournisseur` (`id`,`entreprise`,`responsable`,`telephone`) VALUES 
 ('1','tESTW TWEFEWFUYF','tESTEGSDUG IUSDGUI','4564630000'),
 ('3','Teste fournisseur','Responssabçle fouirniskçk','7896098799');
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;


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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produit`
--

/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` (`id`,`idReseau`,`description`,`idCategorieProduit`,`active`) VALUES 
 ('2','2','Teste2 JKHILHKJHK','1',1),
 ('3','1','jhlnskjhnlkjn','2',1);
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
INSERT INTO `profilutilisateur` (`id`,`description`,`descriptionAbregee`) VALUES 
 ('1','Administrateur','Admini'),
 ('2','Utilisateur','Utl');
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
INSERT INTO `reseau` (`id`,`nom`,`nomAbrege`,`active`) VALUES 
 ('1','Congo Chine Télécom','CCT',1),
 ('2','Vodacom','Voda',1),
 ('6','Orange','org',1);
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
  KEY `FK_sessionutilisateur_collaborateur` (`idCollaborateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessionutilisateur`
--

/*!40000 ALTER TABLE `sessionutilisateur` DISABLE KEYS */;
INSERT INTO `sessionutilisateur` (`id`,`idCollaborateur`,`actionEntree`,`dateHeure`) VALUES 
 ('1','1',1,'2018-07-03 01:19:09'),
 ('10','1',1,'2018-07-07 00:27:11'),
 ('11','1',1,'2018-07-07 00:33:45'),
 ('12','1',1,'2018-07-07 01:02:12'),
 ('13','1',1,'2018-07-07 01:02:52'),
 ('14','1',1,'2018-07-08 18:03:18'),
 ('15','1',1,'2018-07-08 18:04:42'),
 ('16','1',1,'2018-07-08 18:12:56'),
 ('17','1',1,'2018-07-08 18:19:19'),
 ('18','1',1,'2018-07-08 18:34:18'),
 ('19','1',1,'2018-07-08 18:34:47'),
 ('2','1',1,'2018-07-03 01:53:58'),
 ('20','1',1,'2018-07-08 19:05:22'),
 ('21','1',1,'2018-07-08 19:33:09'),
 ('22','1',1,'2018-07-08 19:38:08'),
 ('23','1',1,'2018-07-08 19:51:09'),
 ('24','1',1,'2018-07-10 16:33:56'),
 ('25','1',1,'2018-07-10 16:35:49'),
 ('26','1',1,'2018-07-10 16:39:30'),
 ('27','1',1,'2018-07-10 16:50:31'),
 ('28','1',1,'2018-07-10 16:52:07'),
 ('29','1',1,'2018-07-10 16:54:44'),
 ('3','1',1,'2018-07-03 02:07:46'),
 ('30','1',1,'2018-07-11 02:30:44'),
 ('31','1',1,'2018-07-11 02:42:25'),
 ('32','1',1,'2018-07-11 02:43:20'),
 ('33','1',1,'2018-07-11 02:44:24'),
 ('34','1',1,'2018-07-11 16:34:48'),
 ('35','1',1,'2018-07-11 16:36:22'),
 ('36','1',1,'2018-07-11 16:42:01'),
 ('37','1',1,'2018-07-11 16:43:57'),
 ('38','1',1,'2018-07-11 16:47:48'),
 ('39','1',1,'2018-07-11 16:54:23'),
 ('4','1',1,'2018-07-06 23:10:13'),
 ('40','1',1,'2018-07-11 17:09:28'),
 ('41','1',1,'2018-07-11 17:11:05'),
 ('42','1',1,'2018-07-12 13:58:36'),
 ('43','1',1,'2018-07-12 14:00:17'),
 ('44','1',1,'2018-07-14 18:44:07'),
 ('45','1',1,'2018-07-14 18:59:36'),
 ('46','1',1,'2018-07-14 19:02:24'),
 ('47','1',1,'2018-07-14 19:03:25'),
 ('48','1',1,'2018-07-14 19:06:09'),
 ('49','1',1,'2018-07-14 22:33:14'),
 ('5','1',1,'2018-07-06 23:13:01'),
 ('50','1',1,'2018-07-14 22:34:33'),
 ('6','1',1,'2018-07-07 00:02:35'),
 ('7','1',1,'2018-07-07 00:05:47'),
 ('8','1',1,'2018-07-07 00:09:59'),
 ('9','1',1,'2018-07-07 00:19:16');
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
INSERT INTO `shop` (`id`,`nom`,`adresse`,`active`) VALUES 
 ('1','La source','Avenue Lutendele@4786@Quartier 7@N\'djili@Kinshasa@Teste',1),
 ('2','Quartier 7','Avenue Lutendele@4786@Quartier 7@N\'djili@Kinshasa@Teste',1),
 ('3','Masina','Teste gfjhfj@546@uigug Teste@Teste ugiugi@Kinshasa@Teste yguogi',1),
 ('4','Centre Ville','Teste gfjhfj@546@uigug Teste@Teste ugiugi@Kinshasa@Teste yguogi',1),
 ('5','Quartier 9','Teste gfjhfj@546@uigug Teste@Teste ugiugi@Kinshasa@Teste yguogi',1);
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`idProduit`,`idShop`)
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
  PRIMARY KEY (`id`) USING BTREE
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
