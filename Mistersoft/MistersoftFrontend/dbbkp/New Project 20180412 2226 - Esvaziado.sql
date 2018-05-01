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
-- Definition of table `pecacomposta`
--
DROP TABLE IF EXISTS `pecacomposta`;
CREATE TABLE `pecacomposta` (
  `cdPecaComposta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cdPecaComposta`),
  CONSTRAINT `FK_pecacomposta_peca` FOREIGN KEY (`cdPecaComposta`) REFERENCES `peca` (`cdPeca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
