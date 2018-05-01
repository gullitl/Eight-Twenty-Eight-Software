package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaCompostaController;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaController;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaSimplesController;
import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.Peca;
import com.cecilsystems.mistersoftbackend.model.PecaComposta;
import com.cecilsystems.mistersoftbackend.model.PecaSimples;
import com.cecilsystems.mistersoftbackend.service.ItemPecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.PecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.PecaService;
import com.cecilsystems.mistersoftbackend.service.PecaSimplesService;
import com.cecilsystems.mistersoftfrontend.useful.Essential;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.cecilsystems.mistersoftbackend.enumerable.TipoPecaEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioPecaController implements Initializable {

    @FXML
    private JFXCheckBox jchkPecaSimples;
    @FXML
    private JFXCheckBox jchkPecaComposta;
    @FXML
    private JFXToggleButton jtgbAdicionarTodas;
    @FXML
    private MaskedJFXTextField jmskCodigoPeca;
    @FXML
    private JFXButton jbtnConsultarPeca;
    @FXML
    private Label lblDescricao;
    @FXML
    private JFXButton jbtnAdicionarPeca;
    @FXML
    private JFXButton jbtnLimparCamposPeca;
    @FXML
    private TableView<Peca> tvwPeca;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private TableColumn clmTipo;
    @FXML
    private JFXCheckBox jchkListarItens;
    @FXML
    private Label lblQtd;
    @FXML
    private JFXButton jbtnFechar;
    @FXML
    private JFXButton jbtnLimpar;
    @FXML
    private Label lblPecaNaoCadastrado;
    @FXML
    private ImageView imgvwPecaNaoCadastrado;
    @FXML
    private HBox hbxPreenchimentoObrg;
    @FXML
    private Label lblPreenchimentoObrg;
    private Stage formStage;
    private final ObservableList<Peca> obsPeca;
    private final List<ItemPecaComposta> listItemPecaComposta;
    private static RelatorioPecaController uniqueInstance;

    public RelatorioPecaController() {
        obsPeca = FXCollections.observableArrayList();
        listItemPecaComposta = new ArrayList();
    }

    public static synchronized RelatorioPecaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RelatorioPecaController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(jchkPecaSimples::requestFocus);

        lblPecaNaoCadastrado.setVisible(false);
        lblPecaNaoCadastrado.setStyle("-fx-text-fill: #c00d0d;");
        imgvwPecaNaoCadastrado.setVisible(false);

        hbxPreenchimentoObrg.setLayoutY(511);
        hbxPreenchimentoObrg.setVisible(false);
        lblPreenchimentoObrg.setStyle("-fx-text-fill: #c00d0d;");

        jchkPecaSimples.setSelected(true);

        jchkListarItens.setVisible(false);

        lblQtd.setText("");

        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("cdPeca"));
        clmCodigo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigo.getStyleClass().add("right-header");

        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("dsPeca"));
        clmDescricao.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricao.getStyleClass().add("left-header");

        clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipoPeca"));
        clmTipo.setStyle("-fx-alignment: CENTER-LEFT;");
        clmTipo.getStyleClass().add("center-header");

        RequiredFieldValidator codigoValidator = new RequiredFieldValidator();

        jmskCodigoPeca.getValidators().add(codigoValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");

        jmskCodigoPeca.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {

                jmskCodigoPeca.resetValidation();

                if (!jmskCodigoPeca.getText().isEmpty()) {
                    jmskCodigoPecaFocusLost();
                }
            }
        });
        jbtnConsultarPeca.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoPeca.validate()) {
                    jmskCodigoPeca.requestFocus();
                }
            }
        });

        Image errorIcon = new Image(MainApp.class.getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));

    }

    private void jmskCodigoPecaFocusLost() {
        try {
            Peca peca = PecaService.getInstance()
                    .selecionaPeca(Integer.parseInt(jmskCodigoPeca.getText()));

            if (peca == null) {
                lblPecaNaoCadastrado.setVisible(true);
                imgvwPecaNaoCadastrado.setVisible(true);
                jmskCodigoPeca.requestFocus();
                return;
            }
            lblDescricao.setText(peca.getDsPeca());

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoPecaKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoPeca.validate()) {
                jbtnAdicionarPeca.requestFocus();
            } else {
                jmskCodigoPeca.requestFocus();
            }
        }
    }

    @FXML
    private void jbtnConsultarPecaAction() {
        try {
            if (jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(PathEnum.VIEW_PATH + "ConsultaPeca.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Pecas");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(formStage);
                dialogStage.setX(376);
                dialogStage.setY(85);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ConsultaPecaController controller = loader.getController();
                dialogStage.showAndWait();

                Peca peca = controller.getPeca();

                if (peca != null) {
                    jmskCodigoPeca.setEditable(false);
                    jmskCodigoPeca.setText("" + peca.getCdPeca());
                    lblDescricao.setText(peca.getDsPeca());
                }

            } else if (!jchkPecaComposta.isSelected()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(PathEnum.VIEW_PATH + "ConsultaPecaSimples.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Pecas Simples");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(formStage);
                dialogStage.setX(376);
                dialogStage.setY(85);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ConsultaPecaSimplesController controller = loader.getController();
                dialogStage.showAndWait();

                PecaSimples pecaSimples = controller.getPecaSimples();

                if (pecaSimples != null) {
                    jmskCodigoPeca.setEditable(false);
                    jmskCodigoPeca.setText("" + pecaSimples.getCdPeca());
                    lblDescricao.setText(pecaSimples.getDsPeca());
                }
            } else if (!jchkPecaSimples.isSelected()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(PathEnum.VIEW_PATH + "ConsultaPecaComposta.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Pecas Compostas");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(formStage);
                dialogStage.setX(376);
                dialogStage.setY(85);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ConsultaPecaCompostaController controller = loader.getController();
                dialogStage.showAndWait();

                PecaComposta pecaComposta = controller.getPecaComposta();

                if (pecaComposta != null) {
                    jmskCodigoPeca.setEditable(false);
                    jmskCodigoPeca.setText("" + pecaComposta.getCdPeca());
                    lblDescricao.setText(pecaComposta.getDsPeca());
                }
            }

            jmskCodigoPeca.resetValidation();

            lblPecaNaoCadastrado.setVisible(false);
            imgvwPecaNaoCadastrado.setVisible(false);

        } catch (IOException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jchkPecaSimplesAction() {
        if (!jchkPecaSimples.isSelected()) {
            if (!jchkPecaComposta.isSelected()) {
                jchkPecaComposta.setSelected(true);
            }
            jchkListarItens.setVisible(true);
        } else {
            jchkListarItens.setVisible(false);
        }
        tvwPeca.setStyle("-fx-border-color: null");
        hbxPreenchimentoObrg.setVisible(false);

    }

    @FXML
    private void jchkPecaCompostaAction() {
        if (!jchkPecaComposta.isSelected()) {
            if (!jchkPecaSimples.isSelected()) {
                jchkPecaSimples.setSelected(true);
            }
            jchkListarItens.setSelected(false);
            jchkListarItens.setVisible(false);
        }
        tvwPeca.setStyle("-fx-border-color: null");
        hbxPreenchimentoObrg.setVisible(false);
    }

    @FXML
    private void jtgbAdicionarTodasAction() {
        obsPeca.clear();
        listItemPecaComposta.clear();
        if (jtgbAdicionarTodas.isSelected()) {
            AdicaoTodasAsPecasNaTabela adicaoTodasAsPecas = new AdicaoTodasAsPecasNaTabela();
            adicaoTodasAsPecas.start();

            synchronized (adicaoTodasAsPecas) {
                try {
                    adicaoTodasAsPecas.join();
                } catch (InterruptedException ex) {
                    DialogFactory.getInstance().erro(ex.getMessage());
                }
            }
            jchkPecaSimples.setDisable(true);
            jchkPecaComposta.setDisable(true);

            jmskCodigoPeca.setText("");
            jmskCodigoPeca.resetValidation();
            jmskCodigoPeca.setDisable(true);

            jbtnConsultarPeca.setDisable(true);

            lblDescricao.setText("");

            lblPecaNaoCadastrado.setVisible(false);
            imgvwPecaNaoCadastrado.setVisible(false);

            jbtnAdicionarPeca.setDisable(true);
            jbtnLimparCamposPeca.setDisable(true);

        } else {

            tvwPeca.setItems(obsPeca);

            jchkPecaSimples.setDisable(false);
            jchkPecaComposta.setDisable(false);

            jmskCodigoPeca.setDisable(false);
            jbtnConsultarPeca.setDisable(false);

            jbtnAdicionarPeca.setDisable(false);
            jbtnLimparCamposPeca.setDisable(false);

        }

        tvwPeca.setStyle("-fx-border-color: null");
        hbxPreenchimentoObrg.setVisible(false);

        if (!jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
            jchkListarItens.setVisible(true);
        }
        atualizaQtd();
    }

    private class AdicaoTodasAsPecasNaTabela extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    //Coloca lista de peças no ObservableList
                    if (jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                        for (Peca peca : PecaService.getInstance().listaPeca()) {
                            if (peca.getTipoPeca() == TipoPecaEnum.SIMPLES.getTipo()) {
                                PecaSimples pecaSimples = PecaSimplesService.getInstance()
                                        .selecionaPecaSimples(peca.getCdPeca());

                                peca.setVlCusto(pecaSimples.getVlCusto());
                                peca.setVlVenda(pecaSimples.getVlVenda());

                            } else if (peca.getTipoPeca() == TipoPecaEnum.COMPOSTA.getTipo()) {
                                PecaComposta pecaComposta = PecaCompostaService.getInstance()
                                        .selecionaPecaComposta(peca.getCdPeca());

                                peca.setVlCusto(pecaComposta.getVlCusto());
                                peca.setVlVenda(pecaComposta.getVlVenda());
                            }
                            obsPeca.add(peca);
                        }

                    } else if (!jchkPecaComposta.isSelected()) {
                        for (PecaSimples pecaSimples : PecaSimplesService.getInstance().listaPecaSimples()) {
                            Peca peca = new Peca();
                            peca.setCdPeca(pecaSimples.getCdPeca());
                            peca.setDsPeca(pecaSimples.getDsPeca());
                            peca.setTipoPeca(pecaSimples.getTipoPeca());
                            peca.setVlCusto(pecaSimples.getVlCusto());
                            peca.setMarkup(pecaSimples.getMarkup());
                            peca.setVlVenda(pecaSimples.getVlVenda());
                            obsPeca.add(peca);
                        }

                    } else if (!jchkPecaSimples.isSelected()) {

                        for (PecaComposta pc : PecaCompostaService.getInstance().listaPecaComposta()) {
                            Peca peca = new Peca();
                            PecaComposta pecaComposta = PecaCompostaService.getInstance().selecionaPecaComposta(pc.getCdPeca());
                            peca.setCdPeca(pecaComposta.getCdPeca());
                            peca.setDsPeca(pecaComposta.getDsPeca());
                            peca.setTipoPeca(pecaComposta.getTipoPeca());
                            peca.setVlCusto(pecaComposta.getVlCusto());
                            peca.setMarkup(pecaComposta.getMarkup());
                            peca.setVlVenda(pecaComposta.getVlVenda());

                            obsPeca.add(peca);

                            for (ItemPecaComposta itPecaComposta : ItemPecaCompostaService.getInstance()
                                    .listaItemPecaComposta(pecaComposta.getCdPeca())) {
                                itPecaComposta.setPecaComposta(pecaComposta);

                                listItemPecaComposta.add(itPecaComposta);
                            }
                        }

                    }
                    tvwPeca.setItems(obsPeca);

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.getMessage();
                }
                notify();
            }
        }
    }

    private void atualizaQtd() {
        if (!obsPeca.isEmpty()) {
            lblQtd.setText(obsPeca.size() > 1 ? "Qtd.: " + obsPeca.size() : "Qtd.: " + obsPeca.size());
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void jbtnAdicionarPecaAction() {
        if (!jmskCodigoPeca.validate()) {
            lblPecaNaoCadastrado.setVisible(false);
            imgvwPecaNaoCadastrado.setVisible(false);

            jmskCodigoPeca.requestFocus();
            return;
        }

        for (Peca peca : obsPeca) {
            if (peca.getCdPeca() == Integer.parseInt(jmskCodigoPeca.getText())) {
                DialogFactory.getInstance().erro("Esta peça já foi adicionada na tabela");
                jmskCodigoPeca.requestFocus();
                return;
            }
        }

        adicaoPecaNaTabela adicionaPeca = new adicaoPecaNaTabela();
        adicionaPeca.start();

        synchronized (adicionaPeca) {
            try {
                adicionaPeca.join();
            } catch (InterruptedException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }

        jchkPecaSimples.setDisable(true);
        jchkPecaComposta.setDisable(true);

        jmskCodigoPeca.setText("");
        jmskCodigoPeca.resetValidation();
        lblPecaNaoCadastrado.setVisible(false);
        imgvwPecaNaoCadastrado.setVisible(false);
        lblDescricao.setText("");
        try {

            if (jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                if (obsPeca.size() == PecaService.getInstance().listaPeca().size()) {
                    jtgbAdicionarTodas.setSelected(true);
                    jmskCodigoPeca.setDisable(true);
                    jbtnConsultarPeca.setDisable(true);
                    jbtnAdicionarPeca.setDisable(true);
                    jbtnLimparCamposPeca.setDisable(true);
                }
            } else if (!jchkPecaComposta.isSelected()) {
                if (obsPeca.size() == PecaSimplesService.getInstance().listaPecaSimples().size()) {
                    jtgbAdicionarTodas.setSelected(true);
                    jmskCodigoPeca.setDisable(true);
                    jbtnConsultarPeca.setDisable(true);
                    jbtnAdicionarPeca.setDisable(true);
                    jbtnLimparCamposPeca.setDisable(true);
                }
            } else if (!jchkPecaSimples.isSelected()) {
                if (obsPeca.size() == PecaCompostaService.getInstance().listaPecaComposta().size()) {
                    jtgbAdicionarTodas.setSelected(true);
                    jmskCodigoPeca.setDisable(true);
                    jbtnConsultarPeca.setDisable(true);
                    jbtnAdicionarPeca.setDisable(true);
                    jbtnLimparCamposPeca.setDisable(true);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RelatorioPecaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tvwPeca.setStyle("-fx-border-color: null");
        hbxPreenchimentoObrg.setVisible(false);

        if (!jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
            jchkListarItens.setVisible(true);
        }
        atualizaQtd();
    }

    private class adicaoPecaNaTabela extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    Peca peca = PecaService.getInstance().selecionaPeca(Integer.parseInt(jmskCodigoPeca.getText()));
                    if (jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                        if (peca.getTipoPeca() == TipoPecaEnum.SIMPLES.getTipo()) {
                            PecaSimples pecaSimples = PecaSimplesService.getInstance()
                                    .selecionaPecaSimples(peca.getCdPeca());

                            peca.setVlCusto(pecaSimples.getVlCusto());
                            peca.setVlVenda(pecaSimples.getVlVenda());

                        } else if (peca.getTipoPeca() == TipoPecaEnum.COMPOSTA.getTipo()) {
                            PecaComposta pecaComposta = PecaCompostaService.getInstance().selecionaPecaComposta(peca.getCdPeca());

                            peca.setVlCusto(pecaComposta.getVlCusto());
                            peca.setVlVenda(pecaComposta.getVlVenda());
                        }

                    } else if (!jchkPecaComposta.isSelected()) {
                        PecaSimples pecaSimples = PecaSimplesService.getInstance()
                                .selecionaPecaSimples(Integer.parseInt(jmskCodigoPeca.getText()));
                        peca.setCdPeca(pecaSimples.getCdPeca());
                        peca.setDsPeca(pecaSimples.getDsPeca());
                        peca.setTipoPeca(pecaSimples.getTipoPeca());
                        peca.setVlCusto(pecaSimples.getVlCusto());
                        peca.setMarkup(pecaSimples.getMarkup());
                        peca.setVlVenda(pecaSimples.getVlVenda());

                    } else if (!jchkPecaSimples.isSelected()) {
                        PecaComposta pecaComposta = PecaCompostaService.getInstance()
                                .selecionaPecaComposta(peca.getCdPeca());

                        peca.setVlCusto(pecaComposta.getVlCusto());
                        peca.setVlVenda(pecaComposta.getVlVenda());
                        //Itens Peça Composta
                        for (ItemPecaComposta itPecaComposta : ItemPecaCompostaService.getInstance()
                                .listaItemPecaComposta(pecaComposta.getCdPeca())) {
                            itPecaComposta.setPecaComposta(pecaComposta);

                            listItemPecaComposta.add(itPecaComposta);
                        }
                    }

//            if (peca == null) {
//                lblPecaNaoCadastrado.setVisible(true);
//                imgvwPecaNaoCadastrado.setVisible(true);
//                jmskCodigoPeca.requestFocus();
//                return;
//            }
                    obsPeca.add(peca);
                    tvwPeca.setItems(obsPeca);

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RelatorioPecaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                notify();
            }
        }
    }

    @FXML
    private void jbtnLimparCamposPecaAction() {
        jmskCodigoPeca.setEditable(true);
        jmskCodigoPeca.setText("");
        jmskCodigoPeca.resetValidation();
        lblPecaNaoCadastrado.setVisible(false);
        imgvwPecaNaoCadastrado.setVisible(false);
        lblDescricao.setText("");
        jmskCodigoPeca.requestFocus();
    }

    @FXML
    private void tvwPecaKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            Peca peca = tvwPeca.getSelectionModel().getSelectedItem();
            for (Peca pc : obsPeca) {
                if (pc.equals(peca)) {
                    obsPeca.remove(pc);

                    if (!jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                        List<ItemPecaComposta> listIPC = new ArrayList();
                        listIPC.addAll(listItemPecaComposta);

                        for (ItemPecaComposta ipc : listIPC) {
                            if (ipc.getPecaComposta().getCdPeca() == peca.getCdPeca()) {
                                listItemPecaComposta.remove(ipc);
                            }
                        }
                    }
                    if (obsPeca.isEmpty()) {
                        jchkPecaSimples.setDisable(false);
                        jchkPecaComposta.setDisable(false);
                    }

                    tvwPeca.setItems(obsPeca);
                    break;
                }
            }
            jtgbAdicionarTodas.setSelected(false);

            jmskCodigoPeca.setDisable(false);
            jbtnConsultarPeca.setDisable(false);

            jbtnAdicionarPeca.setDisable(false);
            jbtnLimparCamposPeca.setDisable(false);

            atualizaQtd();
        }
    }

    @FXML
    private void jbtnImprimirAction() {

        if (obsPeca.isEmpty()) {
            tvwPeca.setStyle("-fx-border-color: #c00d0d");
            jchkListarItens.setVisible(false);
            lblQtd.setText("");
            hbxPreenchimentoObrg.setVisible(true);
            return;
        }

        ImpressaoRelatorioPeca impressaoRelatorio = new ImpressaoRelatorioPeca();
        impressaoRelatorio.start();

        synchronized (impressaoRelatorio) {
            try {
                impressaoRelatorio.join();
            } catch (InterruptedException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
            limpaForm();
        }
    }

    private class ImpressaoRelatorioPeca extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                if (jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected()) {
                    URL arquivo = MainApp.class.getResource(PathEnum.REPORT_PATH + "PecaSimplesEComposta.jasper");
                    JRDataSource jRDataSource = new JRBeanCollectionDataSource(obsPeca);

                    Essential.getInstance().geraRelatorio(jRDataSource, new HashMap(), arquivo, "Peças");

                } else if ((!jchkPecaSimples.isSelected() && jchkPecaComposta.isSelected())
                        && jchkListarItens.isSelected()) {
                    URL arquivo = MainApp.class.getResource(PathEnum.REPORT_PATH + "ItensPecaComposta.jasper");
                    JRDataSource jRDataSource = new JRBeanCollectionDataSource(listItemPecaComposta);

                    Essential.getInstance().geraRelatorio(jRDataSource, new HashMap(), arquivo, "Itens Peça Composta");
                } else {
                    URL arquivo = MainApp.class.getResource(PathEnum.REPORT_PATH + "Peca.jasper");
                    JRDataSource jRDataSource = new JRBeanCollectionDataSource(obsPeca);

                    String pageHeader = "RELATÓRIO DE PEÇAS SIMPLES";
                    if (jchkPecaComposta.isSelected()) {
                        pageHeader = "RELATÓRIO DE PEÇAS COMPOSTAS";
                    }
                    HashMap parm = new HashMap();
                    parm.put("PageHeader", pageHeader);

                    Essential.getInstance().geraRelatorio(jRDataSource, parm, arquivo, pageHeader);
                }
                notify();
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();
    }

    private void limpaForm() {
        jchkPecaSimples.setDisable(false);
        jchkPecaSimples.setSelected(true);
        jchkPecaComposta.setDisable(false);
        jchkPecaComposta.setSelected(false);

        jtgbAdicionarTodas.setSelected(false);

        jmskCodigoPeca.setEditable(true);
        jmskCodigoPeca.setDisable(false);
        jmskCodigoPeca.setText("");
        jmskCodigoPeca.resetValidation();
        jbtnConsultarPeca.setDisable(false);
        lblPecaNaoCadastrado.setVisible(false);
        imgvwPecaNaoCadastrado.setVisible(false);
        lblDescricao.setText("");
        jbtnAdicionarPeca.setDisable(false);
        jbtnLimparCamposPeca.setDisable(false);

        obsPeca.clear();
        listItemPecaComposta.clear();
        tvwPeca.setItems(obsPeca);

        jchkListarItens.setSelected(false);
        jchkListarItens.setVisible(false);
        lblQtd.setText("");

        tvwPeca.setStyle("-fx-border-color: null");
        hbxPreenchimentoObrg.setVisible(false);

        jchkPecaSimples.requestFocus();
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.RELATORIO_PECAS)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

}
