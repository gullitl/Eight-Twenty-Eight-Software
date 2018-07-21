package controledeestoque;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class ControleDeEstoque {

    public static void main(String[] argv) {

//        Produto produto1 = new Produto();
//        produto1.setId(2);
//        try {
//            h2SairEstoque(produto1, 5, new BigDecimal("1"));
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(ControleDeEstoque.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        backupDataWithOutDatabase("C:/xampp/mysql/bin/mysqldump.exe",
//                "plus-pc",
//                "3306",
//                "umistersoft",
//                "mistersoft2017",
//                "makservicedb",
//                "C:/Users/Plamedi L. Lusembo/Documents/IISExpress");
        backupDataWithDatabase("C:/xampp/mysql/bin/mysqldump.exe",
                "plus-pc",
                "3306",
                "umistersoft",
                "mistersoft2017",
                "makservicedb",
                "C:/Users/Plamedi L. Lusembo/Documents/IISExpress/");
    }

    public static void criarh2Conexao() {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/makservicedb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO produto(status, descricao, estoque_minimo, estoque_maximo) VALUES ('A', 'Claudio', 10, 100)");
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection criarH2Conexao() {
        System.out.println("-------- H2 JDBC Connection Testing ------------");
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your H2 JDBC Driver?");
            e.printStackTrace();
            return null;
        }
        System.out.println("H2 JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:~/makservicedb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public static Connection criarConexao() {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://plus-pc:3306/makservicedb", "umistersoft", "mistersoft2017");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public static boolean h2SalvaProduto(Produto produto) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarH2Conexao()) {
            scriptSQL = new StringBuilder("INSERT INTO produto(");
            scriptSQL.append("status, descricao, estoque_minimo, estoque_maximo)");
            scriptSQL.append(" VALUES ('A', ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produto.getDescricao());
            prs.setInt(2, produto.getEstoque_minimo());
            prs.setInt(3, produto.getEstoque_maximo());

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean salvaProduto(Produto produto) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO produto(");
            scriptSQL.append("status, descricao, estoque_minimo, estoque_maximo)");
            scriptSQL.append(" VALUES ('A', ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produto.getDescricao());
            prs.setInt(2, produto.getEstoque_minimo());
            prs.setInt(3, produto.getEstoque_maximo());

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean h2SP_AtualizaEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        boolean retorno;
        int contador = 0;
        StringBuilder scriptSQL;
        try (Connection conexao = criarH2Conexao()) {
            scriptSQL = new StringBuilder("SELECT count(*) FROM estoque WHERE id_produto = ?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            res = prs.executeQuery();

            if (res != null) {
                while (res.next()) {
                    contador = res.getInt(1);
                }
            }

            if (contador > 0) {
                scriptSQL = new StringBuilder("UPDATE estoque SET qtde=qtde + ?, valor_unitario= ?");
                scriptSQL.append(" WHERE id_produto = ?");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, qtdeComprada);
                prs.setBigDecimal(2, valor_unit);
                prs.setInt(3, produto.getId());

                prs.execute();
                retorno = true;
            } else {
                scriptSQL = new StringBuilder("INSERT INTO estoque (qtde, valor_unitario, id_produto)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, qtdeComprada);
                prs.setBigDecimal(2, valor_unit);
                prs.setInt(3, produto.getId());

                prs.execute();
                retorno = true;
            }
            conexao.close();
        }
        prs.close();
        res.close();
        return retorno;
    }

    public static boolean SP_AtualizaEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        boolean retorno;
        int contador = 0;
        StringBuilder scriptSQL;
        try (Connection conexao = criarConexao()) {
            scriptSQL = new StringBuilder("SELECT count(*) FROM estoque WHERE id_produto = ?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            res = prs.executeQuery();

            if (res != null) {
                while (res.next()) {
                    contador = res.getInt(1);
                }
            }

            if (contador > 0) {
                scriptSQL = new StringBuilder("UPDATE estoque SET qtde=qtde + ?, valor_unitario= ?");
                scriptSQL.append(" WHERE id_produto = ?");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, qtdeComprada);
                prs.setBigDecimal(2, valor_unit);
                prs.setInt(3, produto.getId());

                prs.execute();
                retorno = true;
            } else {
                scriptSQL = new StringBuilder("INSERT INTO estoque (qtde, valor_unitario, id_produto)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, qtdeComprada);
                prs.setBigDecimal(2, valor_unit);
                prs.setInt(3, produto.getId());

                prs.execute();
                retorno = true;
            }
            conexao.close();
        }
        prs.close();
        res.close();
        return retorno;
    }

    public static boolean h2EntrarEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarH2Conexao()) {
            scriptSQL = new StringBuilder("INSERT INTO entrada_produto(");
            scriptSQL.append("id_produto, qtde, valor_unitario)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            prs.setInt(2, qtdeComprada);
            prs.setBigDecimal(3, valor_unit);

            prs.execute();

            h2SP_AtualizaEstoque(produto, qtdeComprada, valor_unit);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean entrarEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO entrada_produto(");
            scriptSQL.append("id_produto, qtde, valor_unitario)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            prs.setInt(2, qtdeComprada);
            prs.setBigDecimal(3, valor_unit);

            prs.execute();

            SP_AtualizaEstoque(produto, qtdeComprada, valor_unit);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean h2SairEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarH2Conexao()) {
            scriptSQL = new StringBuilder("INSERT INTO saida_produto(");
            scriptSQL.append("id_produto, qtde, valor_unitario)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            prs.setInt(2, qtdeComprada);
            prs.setBigDecimal(3, valor_unit);

            prs.execute();

            h2SP_AtualizaEstoque(produto, -qtdeComprada, valor_unit);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean sairEstoque(Produto produto, int qtdeComprada, BigDecimal valor_unit)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = criarConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO saida_produto(");
            scriptSQL.append("id_produto, qtde, valor_unitario)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produto.getId());
            prs.setInt(2, qtdeComprada);
            prs.setBigDecimal(3, valor_unit);

            prs.execute();

            SP_AtualizaEstoque(produto, -qtdeComprada, valor_unit);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public static boolean backupDataWithOutDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath) {
        boolean status = false;
        try {
            Process p = null;

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = "backup(without_DB)-" + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";

            String batchCommand = "";
            if (password != "") {
                //only backup the data not included create database
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }

            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                status = true;
                System.out.println("Backup created successfully for without DB " + database + " in " + host + ":" + port);
            } else {
                status = false;
                System.out.println("Could not create the backup for without DB " + database + " in " + host + ":" + port);
            }

        } catch (IOException ioe) {
            System.out.println(ioe.toString() + " " + ioe.getCause());
        } catch (Exception e) {
            System.out.println(e.toString() + " " + e.getCause());
        }
        return status;
    }

    public static boolean backupDataWithDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath) {
        boolean status = false;
        try {
            Process p = null;

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh.mm.s");
//            Date date = new Date();
//            String filepath = "backup(with_DB)-" + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";
            String filepath = database + "-" + "backup(" + dateFormat.format(new Date()) + ").sql";

            String batchCommand = "";
            if (password != "") {
                //Backup with database
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }

            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                status = true;
                System.out.println("Backup created successfully for with DB " + database + " in " + host + ":" + port);
            } else {
                status = false;
                System.out.println("Could not create the backup for with DB " + database + " in " + host + ":" + port);
            }

        } catch (IOException ioe) {
            System.out.println(ioe.toString() + " " + ioe.getCause());
        } catch (Exception e) {
            System.out.println(e.toString() + " " + e.getCause());
        }
        return status;
    }

}
