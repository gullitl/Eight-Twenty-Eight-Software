package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import com.cecilsystems.mistersoftbackend.model.Versao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Plamedi L. Lusembo
 */
public class VersaoDao {

    private StringBuilder scriptSQL;
    private static VersaoDao uniqueInstance;

    public VersaoDao() {
    }

    public static synchronized VersaoDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new VersaoDao();
        }
        return uniqueInstance;
    }

    public List<Versao> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Versao> lstVersoes;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstVersoes = new ArrayList();
            scriptSQL = new StringBuilder("SELECT cdVersao, xmaior, ymenor, zcorrecao, dsVersao, cdVersaoAnterior");
            scriptSQL.append(" FROM Versao");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    Versao versao = new Versao();
                    versao.setCdVersao(res.getInt(1));
                    versao.setXmaior(res.getInt(2));
                    versao.setYmenor(res.getInt(3));
                    versao.setZcorrecao(res.getInt(4));
                    versao.setDsVersao(res.getString(5));
                    versao.setCdVersaoAnterior(res.getInt(6));

                    lstVersoes.add(versao);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstVersoes;
    }

    public Versao seleciona(Versao rcbVersao) throws SQLException, ClassNotFoundException {
        PreparedStatement prs;
        ResultSet res;
        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdVersao, xmaior, ymenor, zcorrecao, dsVersao, cdVersaoAnterior");
            scriptSQL.append(" FROM Versao");
            scriptSQL.append(" WHERE xmaior=? AND ymenor=? AND zcorrecao=? AND dsVersao=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, rcbVersao.getXmaior());
            prs.setInt(2, rcbVersao.getYmenor());
            prs.setInt(3, rcbVersao.getZcorrecao());
            prs.setString(4, rcbVersao.getDsVersao());
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {
                    Versao versao = new Versao();
                    versao.setCdVersao(res.getInt(1));
                    versao.setXmaior(res.getInt(2));
                    versao.setYmenor(res.getInt(3));
                    versao.setZcorrecao(res.getInt(4));
                    versao.setDsVersao(res.getString(5));
                    versao.setCdVersaoAnterior(res.getInt(6));

                    res.close();
                    prs.close();
                    conexao.close();

                    return versao;
                }
            }
            res.close();
            prs.close();
            conexao.close();
        }
        return null;
    }

    public Versao getUltimaVersao() throws SQLException, ClassNotFoundException {
        Versao versao = new Versao();
        PreparedStatement prs;
        ResultSet res;
        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdVersao, xmaior, ymenor, zcorrecao, dsVersao, cdVersaoAnterior");
            scriptSQL.append(" FROM Versao");
            scriptSQL.append(" WHERE cdVersao=(SELECT Max(cdVersao) FROM Versao)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    versao.setCdVersao(res.getInt(1));
                    versao.setXmaior(res.getInt(2));
                    versao.setYmenor(res.getInt(3));
                    versao.setZcorrecao(res.getInt(4));
                    versao.setDsVersao(res.getString(5));
                    versao.setCdVersaoAnterior(res.getInt(6));

                    res.close();
                    prs.close();
                    conexao.close();

                    return versao;
                }
            }
            res.close();
            prs.close();
            conexao.close();
        }
        return versao;
    }

    public boolean salva(Versao versao) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO Versao(");
            scriptSQL.append("xmaior, ymenor, zcorrecao, dsVersao, cdVersaoAnterior, observacao)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setLong(1, versao.getXmaior());
            prs.setLong(2, versao.getYmenor());
            prs.setLong(3, versao.getZcorrecao());
            prs.setString(4, versao.getDsVersao());
            prs.setInt(5, versao.getCdVersaoAnterior());
            prs.setString(6, versao.getObservacao());

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    private void criarTabelaVersaoSeNaoExiste() {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {

            //Definition of table chavelicenca
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS chavelicenca (");
            scriptSQL.append(" cdChave int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" nrChave varchar(145) NOT NULL,");
            scriptSQL.append(" diasValidade int(10) unsigned NOT NULL,");
            scriptSQL.append(" dataVencimento datetime NOT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdChave)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            //Definition of table versao
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS versao (");
            scriptSQL.append(" cdVersao int(15) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" xmaior int(5) unsigned NOT NULL,");
            scriptSQL.append(" ymenor int(5) unsigned NOT NULL,");
            scriptSQL.append(" zcorrecao int(5) unsigned NOT NULL,");
            scriptSQL.append(" dsVersao varchar(45) NOT NULL,");
            scriptSQL.append(" cdVersaoAnterior int(15) unsigned NOT NULL,");
            scriptSQL.append(" observacao varchar(250) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdVersao) USING BTREE,");
            scriptSQL.append(" KEY FK_versao_1 (dsVersao) USING BTREE");
            scriptSQL.append(" ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            prs.close();
            conexao.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VersaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //region GERENCIAMENTO DE VERSÕES
    private final Versao versaoAtual = new Versao(1, 0, 0, "alpha");
//    private final Versao versaoAtual = new Versao(1, 0, 0, "beta");

    public boolean temNovaVersao() throws SQLException, ClassNotFoundException {
        criarTabelaVersaoSeNaoExiste();
        return !getUltimaVersao().equals(versaoAtual);
    }

    public void atualizaEsquema() throws SQLException, ClassNotFoundException {
        if (versaoAtual.equals(new Versao(1, 0, 0, "alpha"))) {
            atualiza1_0_0_alpha();
        }
        if (versaoAtual.equals(new Versao(1, 0, 0, "beta"))) {
            atualiza1_0_0_beta();
        }
        versaoAtual.setCdVersaoAnterior(getUltimaVersao().getCdVersao());
        salva(versaoAtual);
    }

    private void atualiza1_0_0_alpha() {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {

            //Definition of table chavelicenca
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS chavelicenca (");
            scriptSQL.append(" cdChave int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" nrChave varchar(145) NOT NULL,");
            scriptSQL.append(" diasValidade int(10) unsigned NOT NULL,");
            scriptSQL.append(" dataVencimento datetime NOT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdChave)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table grupousuario
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS grupousuario (");
            scriptSQL.append(" cdGrupoUsuario int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" dsGrupousuario varchar(45) DEFAULT NULL,");
            scriptSQL.append(" daGrupoUsuario varchar(45) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdGrupoUsuario)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Dumping default data for table grupousuario
            List<GrupoUsuario> listaGruposUsuario = new ArrayList();
            listaGruposUsuario.add(new GrupoUsuario(1, "Administrador", "Admin"));
            listaGruposUsuario.add(new GrupoUsuario(2, "Usuário", "Usr"));

            for (GrupoUsuario grupoUsuario : listaGruposUsuario) {
                scriptSQL = new StringBuilder("INSERT INTO grupousuario(");
                scriptSQL.append("cdGrupoUsuario, dsGrupousuario, daGrupoUsuario)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, grupoUsuario.getCdGrupoUsuario());
                prs.setString(2, grupoUsuario.getDsGrupoUsuario());
                prs.setString(3, grupoUsuario.getDaGrupoUsuario());

                prs.execute();
            }

            // Definition of table usuario
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS usuario (");
            scriptSQL.append(" cdUsuario int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" idGrupoUsuario int(10) unsigned NOT NULL,");
            scriptSQL.append(" email varchar(45) DEFAULT NULL,");
            scriptSQL.append(" senha varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,");
            scriptSQL.append(" nome varchar(45) DEFAULT NULL,");
            scriptSQL.append(" fotoPath varchar(145) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdUsuario),");
            scriptSQL.append(" KEY FK_usuario_grupousuario (idGrupoUsuario),");
            scriptSQL.append(" CONSTRAINT FK_usuario_grupousuario FOREIGN KEY (idGrupoUsuario)");
            scriptSQL.append(" REFERENCES grupousuario (cdGrupoUsuario)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Dumping default data for table usuario
            Usuario usuario = new Usuario();
            usuario.setCdUsuario(1);
            usuario.setEmail("contato@vergoautomacao.com.br");
            usuario.setSenha("default");
            usuario.setNome("Admin");
            usuario.setFotoPath("");
            usuario.setGrupoUsuario(new GrupoUsuario(1));

            scriptSQL = new StringBuilder("INSERT INTO Usuario(");
            scriptSQL.append("cdUsuario, email, senha, nome, fotoPath, idGrupoUsuario)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, usuario.getCdUsuario());
            prs.setString(2, usuario.getEmail());
            prs.setString(3, usuario.getSenha());
            prs.setString(4, usuario.getNome());
            prs.setString(5, usuario.getFotoPath());
            prs.setInt(6, usuario.getGrupoUsuario().getCdGrupoUsuario());

            prs.execute();

            // Definition of table unidademedida
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS unidademedida (");
            scriptSQL.append(" cdUnidadeMedida int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" dsUnidadeMedida varchar(25) NOT NULL,");
            scriptSQL.append(" daUnidadeMedida varchar(2) NOT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdUnidadeMedida)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table peca
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS peca (");
            scriptSQL.append(" cdPeca int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" dsPeca varchar(145) DEFAULT NULL,");
            scriptSQL.append(" idUnidadeMedida int(10) unsigned DEFAULT NULL,");
            scriptSQL.append(" tipoPeca char(1) DEFAULT NULL,");
            scriptSQL.append(" markup decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdPeca),");
            scriptSQL.append(" KEY FK_peca_2 (idUnidadeMedida) USING BTREE,");
            scriptSQL.append(" CONSTRAINT FK_peca_unidademedida FOREIGN KEY (idUnidadeMedida)");
            scriptSQL.append(" REFERENCES unidademedida (cdUnidadeMedida)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table pecasimples
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS pecasimples (");
            scriptSQL.append(" cdPecaSimples int(10) unsigned NOT NULL,");
            scriptSQL.append(" vlCusto decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdPecaSimples),");
            scriptSQL.append(" CONSTRAINT FK_pecasimples_peca FOREIGN KEY (cdPecaSimples)");
            scriptSQL.append(" REFERENCES peca (cdPeca)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table pecacomposta
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS pecacomposta (");
            scriptSQL.append(" cdPecaComposta int(10) unsigned NOT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdPecaComposta),");
            scriptSQL.append(" CONSTRAINT FK_pecacomposta_peca FOREIGN KEY (cdPecaComposta)");
            scriptSQL.append(" REFERENCES peca (cdPeca)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table itempecacomposta
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS itempecacomposta (");
            scriptSQL.append(" idPeca int(10) unsigned NOT NULL,");
            scriptSQL.append(" idPecaComposta int(10) unsigned NOT NULL,");
            scriptSQL.append(" qtdItens decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (idPecaComposta,idPeca) USING BTREE,");
            scriptSQL.append(" KEY FK_itempecacomposta_2 (idPeca) USING BTREE,");
            scriptSQL.append(" CONSTRAINT FK_itempecacomposta_peca FOREIGN KEY (idPeca)");
            scriptSQL.append(" REFERENCES peca (cdPeca),");
            scriptSQL.append(" CONSTRAINT FK_itempecacomposta_pecacomposta");
            scriptSQL.append(" FOREIGN KEY (idPecaComposta)");
            scriptSQL.append(" REFERENCES pecacomposta (cdPecaComposta)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table maodeobra
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS maodeobra (");
            scriptSQL.append(" cdMaoDeObra int(10) unsigned NOT NULL AUTO_INCREMENT,");
            scriptSQL.append(" dsMaoDeObra varchar(45) DEFAULT NULL,");
            scriptSQL.append(" daMaoDeObra varchar(12) DEFAULT NULL,");
            scriptSQL.append(" vlCusto decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" observacao varchar(145) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (cdMaoDeObra)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table maodeobrapecacomposta
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS maodeobrapecacomposta (");
            scriptSQL.append(" idPecaComposta int(10) unsigned NOT NULL,");
            scriptSQL.append(" idMaoDeObra int(10) unsigned NOT NULL,");
            scriptSQL.append(" qtdHoras decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" PRIMARY KEY (idPecaComposta,idMaoDeObra) USING BTREE,");
            scriptSQL.append(" KEY FK_maodeobrapecacomposta_2 (idMaoDeObra) USING BTREE,");
            scriptSQL.append(" CONSTRAINT FK_maodeobrapecacomposta_maodeobra FOREIGN KEY (idMaoDeObra)");
            scriptSQL.append(" REFERENCES maodeobra (cdMaoDeObra),");
            scriptSQL.append(" CONSTRAINT FK_maodeobrapecacomposta_pecacomposta FOREIGN KEY (idPecaComposta)");
            scriptSQL.append(" REFERENCES pecacomposta (cdPecaComposta)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            // Definition of table detalheitempecacomposta
            scriptSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS detalheitempecacomposta (");
            scriptSQL.append(" idPecaComposta int(10) unsigned NOT NULL,");
            scriptSQL.append(" idPecaSimples int(10) unsigned NOT NULL,");
            scriptSQL.append(" idPecaCompostaMae int(10) unsigned NOT NULL,");
            scriptSQL.append(" idPecaMediadora int(10) unsigned NOT NULL,");
            scriptSQL.append(" qtdNaPecaCompostaMae decimal(10,2) unsigned DEFAULT NULL,");
            scriptSQL.append(" qtdNaPecaMediadora decimal(10,2) DEFAULT NULL,");
            scriptSQL.append(" KEY FK_detalheitempecacomposta_pecacomposta (idPecaComposta),");
            scriptSQL.append(" KEY FK_detalheitempecacomposta_pecasimples (idPecaSimples),");
            scriptSQL.append(" KEY FK_detalheitempecacomposta_pecacompostamae (idPecaCompostaMae),");
            scriptSQL.append(" KEY FK_detalheitempecacomposta_peca (idPecaMediadora),");
            scriptSQL.append(" CONSTRAINT FK_detalheitempecacomposta_peca FOREIGN KEY (idPecaMediadora)");
            scriptSQL.append(" REFERENCES peca (cdPeca),");
            scriptSQL.append(" CONSTRAINT FK_detalheitempecacomposta_pecacomposta FOREIGN KEY (idPecaComposta)");
            scriptSQL.append(" REFERENCES pecacomposta (cdPecaComposta),");
            scriptSQL.append(" CONSTRAINT FK_detalheitempecacomposta_pecacompostamae FOREIGN KEY (idPecaCompostaMae)");
            scriptSQL.append(" REFERENCES pecacomposta (cdPecaComposta),");
            scriptSQL.append(" CONSTRAINT FK_detalheitempecacomposta_pecasimples FOREIGN KEY (idPecaSimples)");
            scriptSQL.append(" REFERENCES pecasimples (cdPecaSimples)");
            scriptSQL.append(" ) ENGINE=InnoDB DEFAULT CHARSET=latin1;");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.execute();

            prs.close();
            conexao.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VersaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void atualiza1_0_0_beta() {

    }
    //endregion
}
