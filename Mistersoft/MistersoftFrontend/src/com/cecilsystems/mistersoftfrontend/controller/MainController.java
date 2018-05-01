package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftbackend.dao.ConnectionCredentialsKS;
import com.cecilsystems.mistersoftbackend.model.UsuarioLogado;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * @author Plamedi L. Lusembo
 */
public class MainController implements Initializable {

    private Stage mainLayoutStage;
    public static List<FormMenu> lstFormsMenu;
    @FXML
    private Hyperlink hlkNomeUsuario;
    @FXML
    private ImageView imgvwLogoVergo;
    @FXML
    private Label lblDataHora;

    @FXML
    private Menu mnConfiguracoes;
    @FXML
    private Menu mnCadastro;
    private static MainController uniqueInstance;

    public MainController() {
    }

    public static synchronized MainController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MainController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mnConfiguracoes.getItems().stream().filter((mi) -> (mi.getId().equals("CFG001")))
                .forEachOrdered((mi) -> {
                    mi.setVisible(false);
                });
        if (UsuarioLogado.getInstance().getUsuario().getGrupoUsuario().getCdGrupoUsuario() != 1) {

            mnCadastro.getItems().stream().filter((mi) -> (mi.getId().equals("SPRT")
                    || mi.getId().equals("CAD005") || mi.getId().equals("CAD006")))
                    .forEachOrdered((mi) -> {
                        mi.setVisible(false);
                    });
        }

        hlkNomeUsuario.setText(UsuarioLogado.getInstance().getUsuario().getNome()
                + " (" + UsuarioLogado.getInstance().getUsuario().getEmail() + ")");

        lblDataHora.setText("");
        lstFormsMenu = new ArrayList();
        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras());
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        imgvwLogoVergo.setX(180);
        imgvwLogoVergo.setY(50);
    }

    private void atualizaHoras() {
        Locale.setDefault(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE',' dd/MM/yyyy HH:mm:ss");
        lblDataHora.setText(sdf.format(Calendar.getInstance().getTime()));
    }

    private double calculaX(double w) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        return (dm.width - w) / 2.0;
    }

    private double calculaY(double h) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        return (dm.height - h) / 2.0;
    }

    @FXML
    private void mnuConfiguracaoContaUsuarioAction() {
        abreForm(MenuEnum.CONFIGURACOES_CONTA_USUARIO, "ConfiguracaoContaUsuario.fxml",
                null, calculaX(627), calculaY(485));
    }

    @FXML
    private void mnuCadastroPecaSimplesAction() {
        abreForm(MenuEnum.CADASTRO_PECA, "CadastroPecaSimples.fxml",
                null, calculaX(627), calculaY(562));
    }

    @FXML
    private void mnuCadastroMontagemPecaAction() {
        abreForm(MenuEnum.CADASTRO_MONTAGEM, "CadastroMontagemPeca.fxml",
                null, calculaX(1207), calculaY(614));
    }

    @FXML
    private void mnuCadastroMaoDeObraAction() {
        abreForm(MenuEnum.CADASTRO_MAO_OBRA, "CadastroMaoDeObra.fxml",
                null, calculaX(627), calculaY(533));
    }

    @FXML
    private void mnuCadastroUnidadeMedidaAction() {
        abreForm(MenuEnum.CADASTRO_UNIDADE_MEDIDA, "CadastroUnidadeMedida.fxml",
                null, calculaX(627), calculaY(439));
    }

    @FXML
    private void mnuCadastroGrupoUsuarioAction() {
        abreForm(MenuEnum.CADASTRO_GRUPO_USUARIO, "CadastroGrupoUsuario.fxml",
                null, calculaX(627), calculaY(342));
    }

    @FXML
    private void mnuCadastroUsuarioAction() {
        abreForm(MenuEnum.CADASTRO_USUARIO, "CadastroUsuario.fxml",
                null, calculaX(627), calculaY(556));
    }

    @FXML
    private void mnuRelatorioPecaAction() {
        abreForm(MenuEnum.RELATORIO_PECAS, "RelatorioPeca.fxml",
                null, calculaX(650), calculaY(600));
    }

    @FXML
    private void mnuRelatorioMontagemPecaAction() {
        abreForm(MenuEnum.RELATORIO_MONTAGEM_PECA, "RelatorioMontagemPeca.fxml",
                null, calculaX(670), calculaY(289));
    }

    @FXML
    private void mnuBuckupBancoDados() {
        String database = ConnectionCredentialsKS.getInstance().getConnectionCredentials().getSchema();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh.mm.s");
        String filepath = database + "-" + "backup[" + dateFormat.format(new Date()) + "].sql";

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Backup");
        fileChooser.setInitialFileName(filepath);

        ExtensionFilter sqlExtensionFilter;
        sqlExtensionFilter = new ExtensionFilter("SQL - Structured Query Language Format Script File UTF-8", "*.sql");
        fileChooser.getExtensionFilters().add(sqlExtensionFilter);
        fileChooser.setSelectedExtensionFilter(sqlExtensionFilter);

        File onSavingFile = fileChooser.showSaveDialog(mainLayoutStage);

        if (onSavingFile != null) {
            String mysqldumpLink = "C:/xampp/mysql/bin/mysqldump.exe";
            String filePath = onSavingFile.getPath();
            boolean deuCerto = executaBackupDataBase(mysqldumpLink,
                    ConnectionCredentialsKS.getInstance().getConnectionCredentials().getServerHost(),
                    ConnectionCredentialsKS.getInstance().getConnectionCredentials().getPort(),
                    ConnectionCredentialsKS.getInstance().getConnectionCredentials().getUser(),
                    ConnectionCredentialsKS.getInstance().getConnectionCredentials().getPassword(),
                    database,
                    filePath);

            if (DialogFactory.getInstance().informa2("refresh.png",
                    "Backup realizado com sucesso!",
                    "O arquivo do backup foi gerado no caminho: \n" + filePath,
                    "Você deseja copiar o caminho?",
                    "COPIAR")) {

                Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
                ClipboardOwner selection = new StringSelection(filePath);
                board.setContents((Transferable) selection, selection);

                NotifierPigeon.getInstance().notificaSucesso("Copiado na área de transferência.");
            }
        }
    }

    public boolean executaBackupDataBase(String dumpExePath,
            String host, int port, String user, String password, String database,
            String backupAndfilePath) {
        int processComplete = -1;
        try {
            String batchCommand = dumpExePath
                    + " -h " + host
                    + " --port " + port
                    + " -u " + user
                    + " --password=" + password
                    + " --add-drop-database -B " + database
                    + " -r \"" + backupAndfilePath + "\"";

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(batchCommand);
            processComplete = process.waitFor();

        } catch (IOException | InterruptedException ioe) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return processComplete == 0;
    }

    @FXML
    private void mnuSobreMistersoftAction() {
        abreForm(MenuEnum.AJUDA_SOBRE, "Sobre.fxml", null, calculaX(670), calculaY(289));
    }

    //--- *** ----- ### ----- *** ---
    @FXML
    private void hlkSairOnAction() {
        try {
            Stage loginStage = new Stage();
            mainLayoutStage = (Stage) hlkNomeUsuario.getScene().getWindow();
            AnchorPane loginAnchorPane = FXMLLoader.load(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "Login.fxml"));
            Scene scene = new Scene(loginAnchorPane);
            loginStage.setResizable(false);
            loginStage.setMaximized(false);
            loginStage.setTitle("Login");
            loginStage.setScene(scene);
            loginStage.show();
            mainLayoutStage.close();

        } catch (IOException ex) {
            DialogFactory.getInstance().erro("Erro ao tentar sair do systema!");
        }
    }

    private void abreForm(MenuEnum menum, String arquivofxml, String icone, double x, double y) {
        boolean aberto = false;

        try {
            for (FormMenu formMenu : lstFormsMenu) {
                if (formMenu.getMenum().equals(menum)) {
                    aberto = true;
                    formMenu.getStage().toFront();
                    break;
                }
            }

            if (!aberto) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(PathEnum.VIEW_PATH + arquivofxml));
                StackPane parent = (StackPane) loader.load();

                Stage formStage = new Stage();
                formStage.initOwner((Stage) hlkNomeUsuario.getScene().getWindow());
                formStage.setResizable(false);
                formStage.initModality(Modality.NONE);
                formStage.setTitle(menum.getTitulo());
                if (icone != null && !icone.equals("")) {
                    formStage.getIcons().add(new Image(icone));
                }
                if (x != 0) {
                    formStage.setX(x);
                }
                if (y != 0) {
                    formStage.setY(y);
                }

                Scene scene = new Scene(parent);
                formStage.setScene(scene);

                formStage.setOnCloseRequest((WindowEvent we) -> {
                    for (FormMenu frm : lstFormsMenu) {
                        if (frm.getMenum().equals(menum)) {
                            lstFormsMenu.remove(frm);
                            break;
                        }
                    }
                });

                lstFormsMenu.add(new FormMenu(menum, formStage));

                //Flexible zone begining
                switch (menum) {

                    case CONFIGURACOES_CONTA_USUARIO: {
                        ConfiguracaoContaUsuarioController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case CADASTRO_PECA: {
                        CadastroPecaSimplesController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }
                    case CADASTRO_MONTAGEM: {
                        CadastroMontagemPecaController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }
                    case CADASTRO_UNIDADE_MEDIDA: {
                        CadastroUnidadeMedidaController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case CADASTRO_MAO_OBRA: {
                        CadastroMaoDeObraController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case CADASTRO_GRUPO_USUARIO: {
                        CadastroGrupoUsuarioController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case CADASTRO_USUARIO: {
                        CadastroUsuarioController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case RELATORIO_PECAS: {
                        RelatorioPecaController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }
                    case RELATORIO_MONTAGEM_PECA: {
                        RelatorioMontagemPecaController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    case AJUDA_SOBRE: {
                        SobreController controller = loader.getController();
                        controller.setFormStage(formStage);
                        break;
                    }

                    default:
                        break;
                }
                //Flexible zone final

                formStage.showAndWait();

                if (UsuarioLogado.getInstance().isPrecisaFazerLogout()) {
                    hlkSairOnAction();
                }
            }

        } catch (IOException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
