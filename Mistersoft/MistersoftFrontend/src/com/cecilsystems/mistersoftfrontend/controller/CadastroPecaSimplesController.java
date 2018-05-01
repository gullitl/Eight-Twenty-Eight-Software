package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaSimplesController;
import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftbackend.model.Peca;
import com.cecilsystems.mistersoftbackend.model.PecaSimples;
import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
import com.cecilsystems.mistersoftbackend.service.PecaService;
import com.cecilsystems.mistersoftbackend.service.PecaSimplesService;
import com.cecilsystems.mistersoftbackend.service.UnidadeMedidaService;
import com.cecilsystems.mistersoftfrontend.useful.Notificacoes;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.cecilsystems.mistersoftbackend.enumerable.TipoPecaEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroPecaSimplesController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoPecaSimples;
    @FXML
    private JFXTextField jtxDescricaoPecaSimples;
    @FXML
    private JFXComboBox<UnidadeMedida> jcbxUnidadeMedida;
    @FXML
    private MaskedJFXTextField jmskCustoPecaSimples;
    @FXML
    private MaskedJFXTextField jmskMarkup;
    @FXML
    private JFXTextField jtxVlVendaPecaSimples;
    @FXML
    private JFXButton jbtnConsultarPecaSimples;
    @FXML
    private JFXButton jbtnSalvar;
    @FXML
    private JFXButton jbtnExcluir;
    @FXML
    private JFXButton jbtnFechar;
    @FXML
    private JFXButton jbtnLimpar;
    @FXML
    private Label lblNotificacao;
    @FXML
    private ImageView imgvwNotificacao;
    @FXML
    private Label lblUnidadeMedidaObrig;
    @FXML
    private ImageView imgvwUnidadeMedidaObrig;

    private Stage formStage;
    private boolean modoEdicao;
    private final ObservableList<UnidadeMedida> obsUnidadeMedida;
    private Notificacoes notificacoes;
    private static CadastroPecaSimplesController uniqueInstance;

    public CadastroPecaSimplesController() {
        obsUnidadeMedida = FXCollections.observableArrayList();
    }

    public static synchronized CadastroPecaSimplesController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroPecaSimplesController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNotificacao.setVisible(false);
        imgvwNotificacao.setVisible(false);

        lblUnidadeMedidaObrig.setVisible(false);
        lblUnidadeMedidaObrig.setStyle("-fx-text-fill: #c00d0d;");
        imgvwUnidadeMedidaObrig.setVisible(false);

        notificacoes = new Notificacoes(imgvwNotificacao, lblNotificacao);

        try {
            UnidadeMedidaService.getInstance().listaUnidadeMedida().forEach(obsUnidadeMedida::add);
            jcbxUnidadeMedida.setItems(obsUnidadeMedida);
        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }

        jcbxUnidadeMedida.getSelectionModel().select(null);

        jbtnExcluir.setDisable(true);

        RequiredFieldValidator codigoValidator = new RequiredFieldValidator();
        RequiredFieldValidator descricaoValidator = new RequiredFieldValidator();
        RequiredFieldValidator custoValidator = new RequiredFieldValidator();
        RequiredFieldValidator markupValidator = new RequiredFieldValidator();
        RequiredFieldValidator vlVendaValidator = new RequiredFieldValidator();

        jmskCodigoPecaSimples.getValidators().add(codigoValidator);
        jtxDescricaoPecaSimples.getValidators().add(descricaoValidator);
        jmskCustoPecaSimples.getValidators().add(custoValidator);
        jmskMarkup.getValidators().add(markupValidator);
        jtxVlVendaPecaSimples.getValidators().add(vlVendaValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        descricaoValidator.setMessage("Nome: Campo obrigatório");
        custoValidator.setMessage("Valor custo: Campo obrigatório");
        markupValidator.setMessage("Markup: Campo obrigatório");
        vlVendaValidator.setMessage("Valor venda: Campo obrigatório");

        jmskCodigoPecaSimples.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoPecaSimples.validate()) {
                    jmskCodigoPecaSimplesFocusLost();
                } else {
                    if (jbtnConsultarPecaSimples.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoPecaSimples.resetValidation();
                        return;
                    }
                    jmskCodigoPecaSimples.requestFocus();
                }
            }
        });
        jbtnConsultarPecaSimples.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoPecaSimples.validate()) {
                    jmskCodigoPecaSimples.requestFocus();
                }
            }
        });

        jtxDescricaoPecaSimples.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxDescricaoPecaSimples.validate();
                }
            }
        });

        jcbxUnidadeMedida.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jcbxUnidadeMedida.getValue() == null) {
                        lblUnidadeMedidaObrig.setVisible(true);
                        imgvwUnidadeMedidaObrig.setVisible(true);
                    } else {
                        lblUnidadeMedidaObrig.setVisible(false);
                        imgvwUnidadeMedidaObrig.setVisible(false);
                    }
                }
            }
        });

        jmskCustoPecaSimples.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jmskCustoPecaSimples.validate()) {
                        jmskCustoPecaSimples.setMask("*!");
                        jmskCustoPecaSimples.setText(CecilDecimalFormatter.getInstance()
                                .formattedValue(jmskCustoPecaSimples.getText()));
                    }
                } else {
                    jmskCustoPecaSimples.setMask("N!,NN");
                    if (!jmskCustoPecaSimples.getText().isEmpty()) {
                        jmskCustoPecaSimples.setText(CecilDecimalFormatter.getInstance()
                                .standardValue(jmskCustoPecaSimples.getText()));
                    }
                }
            }
        });
        jmskMarkup.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jmskMarkup.validate()) {
                        jmskMarkup.setMask("*!");
                        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                                .formattedValueSemSimbolo(jmskMarkup.getText()));
                    }
                } else {
                    jmskMarkup.setMask("N!,NN");
                    if (!jmskMarkup.getText().isEmpty()) {
                        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                                .standardValue(jmskMarkup.getText()));
                    }
                }
            }
        });
        jtxVlVendaPecaSimples.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxVlVendaPecaSimples.validate();
                }
            }
        });

        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        descricaoValidator.setIcon(new ImageView(errorIcon));
        custoValidator.setIcon(new ImageView(errorIcon));

    }

    private void jmskCodigoPecaSimplesFocusLost() {
        if (!jmskCodigoPecaSimples.isEditable()) {
            return;
        }
        try {
            Peca peca = PecaService.getInstance()
                    .selecionaPeca(Integer.parseInt(jmskCodigoPecaSimples.getText()));

            if (peca == null) {
                jmskCodigoPecaSimples.setEditable(false);
                return;
            }

            if (peca.getTipoPeca() == TipoPecaEnum.COMPOSTA.getTipo()) {
                notificacoes.notificaAdvertencia(peca.getCdPeca() + " é uma peça composta!");
                jmskCodigoPecaSimples.requestFocus();
                return;
            }

            PecaSimples pecaSimples = PecaSimplesService.getInstance().selecionaPecaSimples(peca.getCdPeca());

            jmskCodigoPecaSimples.setEditable(false);

            preenchePecaSimples(pecaSimples);

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoPecaSimplesKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoPecaSimples.validate()) {
                jtxDescricaoPecaSimples.requestFocus();
            } else {
                jmskCodigoPecaSimples.requestFocus();
            }
        }
    }

    public void preenchePecaSimples(PecaSimples pecaSimples) {
        jbtnExcluir.setDisable(false);
        setModoEdicao(true);

        jmskCodigoPecaSimples.setText("" + pecaSimples.getCdPeca());
        jtxDescricaoPecaSimples.setText(pecaSimples.getDsPeca());
        jcbxUnidadeMedida.getSelectionModel().select(pecaSimples.getUnidadeMedida());
        jmskCustoPecaSimples.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaSimples.getVlCusto()));

        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                .formattedValueSemSimbolo(pecaSimples.getMarkup()));

        jtxVlVendaPecaSimples.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaSimples.getVlVenda()));

        jbtnSalvar.setText("ATUALIZAR");
    }

    @FXML
    public void jmskCodigoPecaSimplesKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            try {
                Integer csb = PecaService.getInstance().selecionaCodigoPecaSubsequente();
                jmskCodigoPecaSimples.setText(csb.toString());
                jmskCodigoPecaSimples.positionCaret(csb.toString().length());
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnConsultarPecaSimplesAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaPecaSimples.fxml"));
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
                pecaSimples = PecaSimplesService.getInstance()
                        .selecionaPecaSimples(pecaSimples.getCdPeca());
                jmskCodigoPecaSimples.setEditable(false);

                preenchePecaSimples(pecaSimples);
            }
            jmskCodigoPecaSimples.resetValidation();
            jtxDescricaoPecaSimples.resetValidation();
            jmskCustoPecaSimples.resetValidation();
            jmskMarkup.resetValidation();
            jtxVlVendaPecaSimples.resetValidation();

            lblUnidadeMedidaObrig.setVisible(false);
            imgvwUnidadeMedidaObrig.setVisible(false);

        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jmskCustoPecaSimplesKeyReleased() {
        String cst = CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(jmskCustoPecaSimples.getText());
        BigDecimal custo = CecilDecimalFormatter.getInstance().bigStandardValue(cst);
        BigDecimal markup = CecilDecimalFormatter.getInstance().bigStandardValue(jmskMarkup.getText());

        BigDecimal precoVenda = custo.multiply(markup);

        if (precoVenda.doubleValue() == 0.0) {
            jtxVlVendaPecaSimples.setText("");
        } else {
            jtxVlVendaPecaSimples.setText(CecilDecimalFormatter.getInstance()
                    .formattedValue(precoVenda));
        }
    }

    @FXML
    private void jmskMarkupKeyReleased() {
        String mrkp = CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(jmskMarkup.getText());
        BigDecimal markup = CecilDecimalFormatter.getInstance().bigStandardValue(mrkp);
        BigDecimal custo = CecilDecimalFormatter.getInstance().bigStandardValue(jmskCustoPecaSimples.getText());

        BigDecimal precoVenda = custo.multiply(markup);

        if (precoVenda.doubleValue() == 0.0) {
            jtxVlVendaPecaSimples.setText("");
        } else {
            jtxVlVendaPecaSimples.setText(CecilDecimalFormatter.getInstance()
                    .formattedValue(precoVenda));
        }
    }

    @FXML
    private void jbtnSalvarAction() {
        if (!jmskCodigoPecaSimples.validate()) {
            jmskCodigoPecaSimples.requestFocus();
            return;
        }
        if (!jtxDescricaoPecaSimples.validate()) {
            jtxDescricaoPecaSimples.requestFocus();
            return;
        }

        if (jcbxUnidadeMedida.getValue() == null) {
            lblUnidadeMedidaObrig.setVisible(true);
            imgvwUnidadeMedidaObrig.setVisible(true);
            jcbxUnidadeMedida.requestFocus();
            return;
        }
        if (!jmskCustoPecaSimples.validate()) {
            jmskCustoPecaSimples.requestFocus();
            return;
        }
        if (!jmskMarkup.validate()) {
            jmskMarkup.requestFocus();
            return;
        }
        if (!jtxVlVendaPecaSimples.validate()) {
            jtxVlVendaPecaSimples.requestFocus();
            return;
        }
        PecaSimples pecaSimples = new PecaSimples();
        pecaSimples.setCdPeca(Integer.parseInt(jmskCodigoPecaSimples.getText()));
        pecaSimples.setDsPeca(jtxDescricaoPecaSimples.getText().trim());
        pecaSimples.setUnidadeMedida(jcbxUnidadeMedida.getValue());
        pecaSimples.setVlCusto(CecilDecimalFormatter.getInstance()
                .bigStandardValue(jmskCustoPecaSimples.getText()));
        pecaSimples.setMarkup(CecilDecimalFormatter.getInstance().bigStandardValue(jmskMarkup.getText()));
        pecaSimples.setVlVenda(CecilDecimalFormatter.getInstance()
                .bigStandardValue(jtxVlVendaPecaSimples.getText()));
        try {
            if (PecaSimplesService.getInstance().salvaPecaSimples(pecaSimples, isModoEdicao())) {
                NotifierPigeon.getInstance().notificaSucesso(!isModoEdicao()
                        ? "Cadastro bem sucedido!" : "Atualização bem sucedida!");
            }
            limpaForm();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        jmskCodigoPecaSimples.requestFocus();
    }

    @FXML
    private void jbtnExcluirAction() {
        if (DialogFactory.getInstance().adverte("trash.png",
                "Excluir Peça Simples", "Esta peça será excluida permanentemente",
                "Tem certeza que deseja excluir esta peça ?", "EXCLUIR")) {
            try {
                PecaSimplesService.getInstance().excluiPecaSimples(Integer.parseInt(jmskCodigoPecaSimples.getText()));

                notificacoes.notificaExcluido();

                limpaForm();
                jmskCodigoPecaSimples.requestFocus();
            } catch (SQLException | ClassNotFoundException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoPecaSimples.resetValidation();
        jtxDescricaoPecaSimples.resetValidation();
        jmskCustoPecaSimples.resetValidation();
        jmskMarkup.resetValidation();
        jtxVlVendaPecaSimples.resetValidation();

        lblUnidadeMedidaObrig.setVisible(false);
        imgvwUnidadeMedidaObrig.setVisible(false);

        jmskCodigoPecaSimples.requestFocus();
    }

    private void limpaForm() {
        jmskCodigoPecaSimples.setEditable(true);
        jbtnExcluir.setDisable(true);
        setModoEdicao(false);

        jmskCodigoPecaSimples.setText("");
        jtxDescricaoPecaSimples.setText("");
        jcbxUnidadeMedida.getSelectionModel().select(null);
        jmskCustoPecaSimples.setText("");
        jmskMarkup.setText("");
        jtxVlVendaPecaSimples.setText("");

        jbtnSalvar.setText("SALVAR");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CADASTRO_PECA)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public void setModoEdicao(boolean modoEdicao) {
        this.modoEdicao = modoEdicao;
    }

}
