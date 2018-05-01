-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema mistersoftdb
--

CREATE DATABASE IF NOT EXISTS mistersoftdb;
USE mistersoftdb;

--
-- Definition of table `chavelicenca`
--

DROP TABLE IF EXISTS `chavelicenca`;
CREATE TABLE `chavelicenca` (
  `cdChave` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nrChave` varchar(145) NOT NULL,
  `diasValidade` int(10) unsigned NOT NULL,
  `dataVencimento` datetime NOT NULL,
  PRIMARY KEY (`cdChave`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chavelicenca`
--

/*!40000 ALTER TABLE `chavelicenca` DISABLE KEYS */;
INSERT INTO `chavelicenca` (`cdChave`,`nrChave`,`diasValidade`,`dataVencimento`) VALUES 
 (1,'ZINCADOP/MANG.38-61MM',15,'2017-08-02 00:00:00'),
 (2,'Teste-AÇO-ZINCADO-P/-MANG.-38-61MM',45,'2017-08-02 00:00:00'),
 (4,'ZINCADOP/MANG.38-61MM',15,'2017-08-02 00:00:00');
/*!40000 ALTER TABLE `chavelicenca` ENABLE KEYS */;


--
-- Definition of table `detalheitempecacomposta`
--

DROP TABLE IF EXISTS `detalheitempecacomposta`;
CREATE TABLE `detalheitempecacomposta` (
  `idPecaComposta` int(10) unsigned NOT NULL,
  `idPecaSimples` int(10) unsigned NOT NULL,
  `idPecaCompostaMae` int(10) unsigned NOT NULL,
  `idPecaMediadora` int(10) unsigned NOT NULL,
  `qtdNaPecaCompostaMae` decimal(10,2) unsigned DEFAULT NULL,
  `qtdNaPecaMediadora` decimal(10,2) DEFAULT NULL,
  KEY `FK_detalheitempecacomposta_pecacomposta` (`idPecaComposta`),
  KEY `FK_detalheitempecacomposta_pecasimples` (`idPecaSimples`),
  KEY `FK_detalheitempecacomposta_pecacompostamae` (`idPecaCompostaMae`),
  KEY `FK_detalheitempecacomposta_peca` (`idPecaMediadora`),
  CONSTRAINT `FK_detalheitempecacomposta_peca` FOREIGN KEY (`idPecaMediadora`) REFERENCES `peca` (`cdPeca`),
  CONSTRAINT `FK_detalheitempecacomposta_pecacomposta` FOREIGN KEY (`idPecaComposta`) REFERENCES `pecacomposta` (`cdPecaComposta`),
  CONSTRAINT `FK_detalheitempecacomposta_pecacompostamae` FOREIGN KEY (`idPecaCompostaMae`) REFERENCES `pecacomposta` (`cdPecaComposta`),
  CONSTRAINT `FK_detalheitempecacomposta_pecasimples` FOREIGN KEY (`idPecaSimples`) REFERENCES `pecasimples` (`cdPecaSimples`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalheitempecacomposta`
--

/*!40000 ALTER TABLE `detalheitempecacomposta` DISABLE KEYS */;
INSERT INTO `detalheitempecacomposta` (`idPecaComposta`,`idPecaSimples`,`idPecaCompostaMae`,`idPecaMediadora`,`qtdNaPecaCompostaMae`,`qtdNaPecaMediadora`) VALUES 
 (1035,2236,1035,2236,'2.00','1.00'),
 (1035,9332,1035,9332,'3.00','1.00'),
 (1036,2236,1035,1035,'2.00','2.00'),
 (1036,9332,1035,1035,'3.00','2.00'),
 (1036,1126,1036,1126,'2.00','1.00'),
 (2648,9338,2648,9338,'1.00','1.00'),
 (2648,2236,1035,1035,'2.00','2.00'),
 (2648,9332,1035,1035,'3.00','2.00'),
 (2122,1039,2122,1039,'2.00','1.00'),
 (2122,1065,2122,1065,'5.00','1.00'),
 (2122,9202,2122,9202,'2.00','1.00'),
 (2647,660,2647,660,'1.00','1.00'),
 (2647,735,2647,735,'3.00','1.00'),
 (2647,9202,2647,9202,'2.00','1.00'),
 (1238,1234,1238,1234,'1.00','1.00'),
 (1238,1237,1238,1237,'8.00','1.00'),
 (1238,1235,1238,1235,'0.08','1.00'),
 (1238,1236,1238,1236,'0.09','1.00'),
 (1056,1039,2122,2122,'2.00','2.00'),
 (1056,1065,2122,2122,'5.00','2.00'),
 (1056,9202,2122,2122,'2.00','2.00'),
 (1056,660,2647,2647,'1.00','1.00'),
 (1056,735,2647,2647,'3.00','1.00'),
 (1056,9202,2647,2647,'2.00','1.00'),
 (1056,9338,2648,2648,'1.00','1.00'),
 (1056,2236,1035,2648,'2.00','1.00'),
 (1056,9332,1035,2648,'3.00','1.00'),
 (1056,9204,1056,9204,'6.00','1.00');
/*!40000 ALTER TABLE `detalheitempecacomposta` ENABLE KEYS */;


--
-- Definition of table `grupousuario`
--

DROP TABLE IF EXISTS `grupousuario`;
CREATE TABLE `grupousuario` (
  `cdGrupoUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dsGrupousuario` varchar(45) DEFAULT NULL,
  `daGrupoUsuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cdGrupoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupousuario`
--

/*!40000 ALTER TABLE `grupousuario` DISABLE KEYS */;
INSERT INTO `grupousuario` (`cdGrupoUsuario`,`dsGrupousuario`,`daGrupoUsuario`) VALUES 
 (1,'Administrador','Admin'),
 (2,'Usuário','Usr');
/*!40000 ALTER TABLE `grupousuario` ENABLE KEYS */;


--
-- Definition of table `itempecacomposta`
--

DROP TABLE IF EXISTS `itempecacomposta`;
CREATE TABLE `itempecacomposta` (
  `idPeca` int(10) unsigned NOT NULL,
  `idPecaComposta` int(10) unsigned NOT NULL,
  `qtdItens` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idPecaComposta`,`idPeca`) USING BTREE,
  KEY `FK_itempecacomposta_2` (`idPeca`) USING BTREE,
  CONSTRAINT `FK_itempecacomposta_peca` FOREIGN KEY (`idPeca`) REFERENCES `peca` (`cdPeca`),
  CONSTRAINT `FK_itempecacomposta_pecacomposta` FOREIGN KEY (`idPecaComposta`) REFERENCES `pecacomposta` (`cdPecaComposta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itempecacomposta`
--

/*!40000 ALTER TABLE `itempecacomposta` DISABLE KEYS */;
INSERT INTO `itempecacomposta` (`idPeca`,`idPecaComposta`,`qtdItens`) VALUES 
 (2236,1035,'2.00'),
 (9332,1035,'3.00'),
 (1035,1036,'2.00'),
 (1126,1036,'2.00'),
 (2122,1056,'2.00'),
 (2647,1056,'10.00'),
 (2648,1056,'1.00'),
 (9204,1056,'6.00'),
 (1234,1238,'1.00'),
 (1235,1238,'0.08'),
 (1236,1238,'0.09'),
 (1237,1238,'8.00'),
 (1039,2122,'2.00'),
 (1065,2122,'5.00'),
 (9202,2122,'2.00'),
 (660,2647,'1.00'),
 (735,2647,'3.00'),
 (9202,2647,'2.00'),
 (1035,2648,'2.00'),
 (9338,2648,'1.00');
/*!40000 ALTER TABLE `itempecacomposta` ENABLE KEYS */;


--
-- Definition of table `maodeobra`
--

DROP TABLE IF EXISTS `maodeobra`;
CREATE TABLE `maodeobra` (
  `cdMaoDeObra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dsMaoDeObra` varchar(45) DEFAULT NULL,
  `daMaoDeObra` varchar(12) DEFAULT NULL,
  `vlCusto` decimal(10,2) DEFAULT NULL,
  `observacao` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`cdMaoDeObra`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `maodeobra`
--

/*!40000 ALTER TABLE `maodeobra` DISABLE KEYS */;
INSERT INTO `maodeobra` (`cdMaoDeObra`,`dsMaoDeObra`,`daMaoDeObra`,`vlCusto`,`observacao`) VALUES 
 (1,'SOLDAGEM','SLDGM','100.00','Só para válvulas testando de novo mais uma vez'),
 (2,'INDUÇÃO IND','INDC','75.45','Válvulas e Cilíndros só pra testar\nTestando');
/*!40000 ALTER TABLE `maodeobra` ENABLE KEYS */;


--
-- Definition of table `maodeobrapecacomposta`
--

DROP TABLE IF EXISTS `maodeobrapecacomposta`;
CREATE TABLE `maodeobrapecacomposta` (
  `idPecaComposta` int(10) unsigned NOT NULL,
  `idMaoDeObra` int(10) unsigned NOT NULL,
  `qtdHoras` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idPecaComposta`,`idMaoDeObra`) USING BTREE,
  KEY `FK_maodeobrapecacomposta_2` (`idMaoDeObra`) USING BTREE,
  CONSTRAINT `FK_maodeobrapecacomposta_maodeobra` FOREIGN KEY (`idMaoDeObra`) REFERENCES `maodeobra` (`cdMaoDeObra`),
  CONSTRAINT `FK_maodeobrapecacomposta_pecacomposta` FOREIGN KEY (`idPecaComposta`) REFERENCES `pecacomposta` (`cdPecaComposta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `maodeobrapecacomposta`
--

/*!40000 ALTER TABLE `maodeobrapecacomposta` DISABLE KEYS */;
INSERT INTO `maodeobrapecacomposta` (`idPecaComposta`,`idMaoDeObra`,`qtdHoras`) VALUES 
 (1035,2,'2.50'),
 (1036,2,'1.55'),
 (1056,1,'1.00'),
 (1056,2,'1.50'),
 (2122,1,'1.50'),
 (2122,2,'1.42'),
 (2647,2,'2.00');
/*!40000 ALTER TABLE `maodeobrapecacomposta` ENABLE KEYS */;


--
-- Definition of table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
CREATE TABLE `parametros` (
  `cdCliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NomeCliente` varchar(45) DEFAULT NULL,
  `Empresa` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`cdCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parametros`
--

/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;


--
-- Definition of table `peca`
--

DROP TABLE IF EXISTS `peca`;
CREATE TABLE `peca` (
  `cdPeca` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dsPeca` varchar(145) DEFAULT NULL,
  `idUnidadeMedida` int(10) unsigned DEFAULT NULL,
  `tipoPeca` char(1) DEFAULT NULL,
  `markup` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`cdPeca`),
  KEY `FK_peca_2` (`idUnidadeMedida`) USING BTREE,
  CONSTRAINT `FK_peca_unidademedida` FOREIGN KEY (`idUnidadeMedida`) REFERENCES `unidademedida` (`cdUnidadeMedida`)
) ENGINE=InnoDB AUTO_INCREMENT=9341 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peca`
--

/*!40000 ALTER TABLE `peca` DISABLE KEYS */;
INSERT INTO `peca` (`cdPeca`,`dsPeca`,`idUnidadeMedida`,`tipoPeca`,`markup`) VALUES 
 (660,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.50'),
 (735,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.10'),
 (1035,'PC-ABRAÇADEIRA AZUL AÇO ZINCADO P/ MANG. 38 - 51 MM',1,'C','2.15'),
 (1036,'PC-ABRA AZUL AÇO ZINCADO P/ MANG. 38 - 51 MM',1,'C','1.05'),
 (1039,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.20'),
 (1056,'ALICATE CORTA TUBO DE   02 À 12MM',1,'C','1.30'),
 (1065,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',4,'S','1.50'),
 (1126,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.00'),
 (1234,'KIT MONTAGEM CILINDRO ISO 32',1,'S','1.75'),
 (1235,'PERFIL CILINDRO ISO 32',2,'S','2.75'),
 (1236,'BARRA DE AÇO CROMADO 12 MM',2,'S','2.50'),
 (1237,'PARAFUSO CILINDRO ISO 32',1,'S','2.55'),
 (1238,'CILINDRO ISO 32 MM X CURSO 50 MM',1,'C','1.80'),
 (1259,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.17'),
 (1363,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','1.15'),
 (1433,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',4,'S','2.50'),
 (1490,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.70'),
 (1491,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',3,'S','2.30'),
 (1492,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',4,'S','2.50'),
 (1493,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',3,'S','2.25'),
 (2067,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',3,'S','1.50'),
 (2122,'ALIMENTADOR PNEUM. P/ CHAPAS BOBINADAS C/ ATÉ 150MM L, 0,2~1,5MM ESPES, 10~100MM AVANÇO AJUSTÁVEL',1,'C','1.80'),
 (2205,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','1.60'),
 (2207,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.65'),
 (2236,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',4,'S','2.16'),
 (2237,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',4,'S','1.65'),
 (2313,'ADAPTADOR AD 767/657 C/ ESPIGA P/ MANG. 1/2\"',4,'S','1.80'),
 (2382,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',2,'S','2.30'),
 (2647,'AMORTECEDOR HIDRÁULICO AUTO-COMPENSADO CURSO 25 MM X ROSCA M25X2.0',1,'C','1.50'),
 (2648,'Testando muito importante',1,'C','1.50'),
 (9200,'KIT MONTAGEM CILINDRO IDO 6431 D. 100MM - KK401100',1,'S','2.35'),
 (9202,'ANEL MAGNÉTICO P/ CILINDRO SÉRIE K Ø100MM - KR161100',1,'S','2.10'),
 (9204,'PARAFUSO PARA MONTAGEM CABEÇOTES CILINDRO ISO 6431 Ø80/100MM - KR131080100',1,'S','1.45'),
 (9332,'PERFIL CILINDRO SÉRIE KL Ø100X1000MM - KLK201003000',4,'S','2.05'),
 (9334,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 51 MM',3,'S','1.70'),
 (9335,'ABRAÇADEIRA AÇO ZINCADO P/ MANG. 38 - 61 MM',2,'S','2.55'),
 (9338,'Peça fictícia',2,'S','2.00'),
 (9340,'Teste',3,'S','1.45');
/*!40000 ALTER TABLE `peca` ENABLE KEYS */;


--
-- Definition of table `pecacomposta`
--

DROP TABLE IF EXISTS `pecacomposta`;
CREATE TABLE `pecacomposta` (
  `cdPecaComposta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cdPecaComposta`),
  CONSTRAINT `FK_pecacomposta_peca` FOREIGN KEY (`cdPecaComposta`) REFERENCES `peca` (`cdPeca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pecacomposta`
--

/*!40000 ALTER TABLE `pecacomposta` DISABLE KEYS */;
INSERT INTO `pecacomposta` (`cdPecaComposta`) VALUES 
 (1035),
 (1036),
 (1056),
 (1238),
 (2122),
 (2647),
 (2648);
/*!40000 ALTER TABLE `pecacomposta` ENABLE KEYS */;


--
-- Definition of table `pecasimples`
--

DROP TABLE IF EXISTS `pecasimples`;
CREATE TABLE `pecasimples` (
  `cdPecaSimples` int(10) unsigned NOT NULL,
  `vlCusto` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`cdPecaSimples`),
  CONSTRAINT `FK_pecasimples_peca` FOREIGN KEY (`cdPecaSimples`) REFERENCES `peca` (`cdPeca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pecasimples`
--

/*!40000 ALTER TABLE `pecasimples` DISABLE KEYS */;
INSERT INTO `pecasimples` (`cdPecaSimples`,`vlCusto`) VALUES 
 (660,'46.50'),
 (735,'30.00'),
 (1039,'40.00'),
 (1065,'25.00'),
 (1126,'15.00'),
 (1234,'10.00'),
 (1235,'25.00'),
 (1236,'15.00'),
 (1237,'1.50'),
 (1259,'45.00'),
 (1363,'20.00'),
 (1433,'55.00'),
 (1490,'40.00'),
 (1491,'35.00'),
 (1492,'60.00'),
 (1493,'30.00'),
 (2067,'50.00'),
 (2205,'20.00'),
 (2207,'55.00'),
 (2236,'40.00'),
 (2237,'35.00'),
 (2313,'43.00'),
 (2382,'25.00'),
 (9200,'25.00'),
 (9202,'45.00'),
 (9204,'10.00'),
 (9332,'15.00'),
 (9334,'40.00'),
 (9335,'57.25'),
 (9338,'50.25'),
 (9340,'13.70');
/*!40000 ALTER TABLE `pecasimples` ENABLE KEYS */;


--
-- Definition of table `unidademedida`
--

DROP TABLE IF EXISTS `unidademedida`;
CREATE TABLE `unidademedida` (
  `cdUnidadeMedida` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dsUnidadeMedida` varchar(25) NOT NULL,
  `daUnidadeMedida` varchar(2) NOT NULL,
  PRIMARY KEY (`cdUnidadeMedida`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unidademedida`
--

/*!40000 ALTER TABLE `unidademedida` DISABLE KEYS */;
INSERT INTO `unidademedida` (`cdUnidadeMedida`,`dsUnidadeMedida`,`daUnidadeMedida`) VALUES 
 (1,'Peça','pc'),
 (2,'Metro','m'),
 (3,'Litro','l'),
 (4,'Grama','g');
/*!40000 ALTER TABLE `unidademedida` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `cdUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idGrupoUsuario` int(10) unsigned NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `senha` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `fotoPath` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`cdUsuario`),
  KEY `FK_usuario_grupousuario` (`idGrupoUsuario`),
  CONSTRAINT `FK_usuario_grupousuario` FOREIGN KEY (`idGrupoUsuario`) REFERENCES `grupousuario` (`cdGrupoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`cdUsuario`,`idGrupoUsuario`,`email`,`senha`,`nome`,`fotoPath`) VALUES 
 (1,1,'plam.lusembo@gmail.com',0x3132333435,'Plamedi L. Lusembo',NULL),
 (2,2,'patricia.luzolo@yahoo.fr',0x3132333435,'Patricia Luzolo Makiese',NULL),
 (3,1,'contato@vergoautomacao.com.br',0x766572676F33303130,'Guilherme Treis',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `versao`
--

DROP TABLE IF EXISTS `versao`;
CREATE TABLE `versao` (
  `cdVersao` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `xmaior` int(5) unsigned NOT NULL,
  `ymenor` int(5) unsigned NOT NULL,
  `zcorrecao` int(5) unsigned NOT NULL,
  `dsVersao` varchar(45) NOT NULL,
  `cdVersaoAnterior` int(15) unsigned NOT NULL,
  `observacao` varchar(250) NOT NULL,
  PRIMARY KEY (`cdVersao`) USING BTREE,
  KEY `FK_versao_1` (`dsVersao`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `versao`
--

/*!40000 ALTER TABLE `versao` DISABLE KEYS */;
INSERT INTO `versao` (`cdVersao`,`xmaior`,`ymenor`,`zcorrecao`,`dsVersao`,`cdVersaoAnterior`,`observacao`) VALUES 
 (1,1,0,0,'alpha',1,'Versão de desenvolvimento'),
 (2,1,0,0,'beta',1,'Versão de teste');
/*!40000 ALTER TABLE `versao` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
