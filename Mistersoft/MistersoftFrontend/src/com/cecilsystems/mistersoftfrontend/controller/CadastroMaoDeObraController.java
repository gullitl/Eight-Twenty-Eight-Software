package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaMaoDeObraController;
import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftbackend.model.MaoDeObra;
import com.cecilsystems.mistersoftbackend.service.MaoDeObraService;
import com.cecilsystems.mistersoftfrontend.useful.Notificacoes;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class CadastroMaoDeObraController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoMaoDeObra;
    @FXML
    private JFXTextField jtxDescricaoMaoDeObra;
    @FXML
    private JFXTextField jtxDAMaoDeObra;
    @FXML
    private MaskedJFXTextField jmskCustoMaoDeObra;
    @FXML
    private JFXTextArea jtaObservacao;
    @FXML
    private JFXButton jbtnConsultarMaoDeObra;
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
    private Stage formStage;
    private boolean modoEdicao;
    private Notificacoes notificacoes;
    private static CadastroMaoDeObraController uniqueInstance;

    public CadastroMaoDeObraController() {
    }

    public static synchronized CadastroMaoDeObraController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroMaoDeObraController();
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
        notificacoes = new Notificacoes(imgvwNotificacao, lblNotificacao);

        jbtnExcluir.setDisable(true);

        RequiredFieldValidator codigoValidator = new RequiredFieldValidator();
        RequiredFieldValidator descricaoValidator = new RequiredFieldValidator();
        RequiredFieldValidator custoValidator = new RequiredFieldValidator();

        jmskCodigoMaoDeObra.getValidators().add(codigoValidator);
        jtxDescricaoMaoDeObra.getValidators().add(descricaoValidator);
        jmskCustoMaoDeObra.getValidators().add(custoValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        descricaoValidator.setMessage("Descrição: Campo obrigatório");
        custoValidator.setMessage("Custo: Campo obrigatório");

        jmskCodigoMaoDeObra.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoMaoDeObra.validate()) {
                    jmskCodigoMaoDeObraFocusLost();
                } else {
                    if (jbtnConsultarMaoDeObra.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoMaoDeObra.resetValidation();
                        return;
                    }
                    jmskCodigoMaoDeObra.requestFocus();
                }
            }
        });
        jbtnConsultarMaoDeObra.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoMaoDeObra.validate()) {
                    jmskCodigoMaoDeObra.requestFocus();
                }
            }
        });

        jtxDescricaoMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxDescricaoMaoDeObra.validate();
                }
            }
        });

        jmskCustoMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jmskCustoMaoDeObra.validate()) {
                        jmskCustoMaoDeObra.setMask("*!");
                        jmskCustoMaoDeObra.setText(CecilDecimalFormatter.getInstance()
                                .formattedValue(jmskCustoMaoDeObra.getText()));
                    }
                } else {
                    jmskCustoMaoDeObra.setMask("N!,NN");
                    if (!jmskCustoMaoDeObra.getText().isEmpty()) {
                        jmskCustoMaoDeObra.setText(CecilDecimalFormatter.getInstance()
                                .standardValue(jmskCustoMaoDeObra.getText()));
                    }
                }
            }
        });
        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        descricaoValidator.setIcon(new ImageView(errorIcon));
        custoValidator.setIcon(new ImageView(errorIcon));
    }

    private void jmskCodigoMaoDeObraFocusLost() {
        if (!jmskCodigoMaoDeObra.isEditable()) {
            return;
        }
        try {
            MaoDeObra maoDeObra = MaoDeObraService.getInstance()
                    .selecionaMaoDeObra(Integer.parseInt(jmskCodigoMaoDeObra.getText()));
            jmskCodigoMaoDeObra.setEditable(false);
            if (maoDeObra == null) {
                return;
            }
            preencherMaoDeObra(maoDeObra);
        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoMaoDeObraKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoMaoDeObra.validate()) {
                jtxDescricaoMaoDeObra.requestFocus();
            } else {
                jmskCodigoMaoDeObra.requestFocus();
            }
        }
    }

    public void preencherMaoDeObra(MaoDeObra maoDeObra) {
        jbtnExcluir.setDisable(false);
        setModoEdicao(true);

        jmskCodigoMaoDeObra.setText("" + maoDeObra.getCdMaoDeObra());
        jtxDescricaoMaoDeObra.setText(maoDeObra.getDsMaoDeObra());
        jtxDAMaoDeObra.setText(maoDeObra.getDaMaoDeObra());

        jmskCustoMaoDeObra.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(maoDeObra.getVlCusto()));

        jtaObservacao.setText(maoDeObra.getObservacao());

        jbtnSalvar.setText("ATUALIZAR");

    }

    @FXML
    public void jmskCodigoMaoDeObraKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            try {
                Integer csb = MaoDeObraService.getInstance().selecionaCodigoMaoDeObraSubsequente();
                jmskCodigoMaoDeObra.setText(csb.toString());
                jmskCodigoMaoDeObra.positionCaret(csb.toString().length());
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnConsultarMaoDeObraAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaMaoDeObra.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Maos de Obra");
            dialogStage.setResizable(false);;
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(414);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaMaoDeObraController controller = loader.getController();
            dialogStage.showAndWait();

            MaoDeObra maoDeObra = controller.getMaoDeObra();

            if (maoDeObra != null) {
                maoDeObra = MaoDeObraService.getInstance()
                        .selecionaMaoDeObra(maoDeObra.getCdMaoDeObra());
                jmskCodigoMaoDeObra.setEditable(false);
                preencherMaoDeObra(maoDeObra);
            }

            jmskCodigoMaoDeObra.resetValidation();
            jtxDescricaoMaoDeObra.resetValidation();
            jmskCustoMaoDeObra.resetValidation();

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jbtnSalvarAction() {
        if (!jmskCodigoMaoDeObra.validate()) {
            jmskCodigoMaoDeObra.requestFocus();
            return;
        }
        if (!jtxDescricaoMaoDeObra.validate()) {
            jtxDescricaoMaoDeObra.requestFocus();
            return;
        }
        if (!jmskCustoMaoDeObra.validate()) {
            jmskCustoMaoDeObra.requestFocus();
            return;
        }

        MaoDeObra maoDeObra = new MaoDeObra();
        maoDeObra.setCdMaoDeObra(Integer.parseInt(jmskCodigoMaoDeObra.getText()));
        maoDeObra.setDsMaoDeObra(jtxDescricaoMaoDeObra.getText().trim());
        maoDeObra.setDaMaoDeObra(jtxDAMaoDeObra.getText().trim());

        maoDeObra.setVlCusto(CecilDecimalFormatter.getInstance()
                .bigStandardValue(jmskCustoMaoDeObra.getText()));

        maoDeObra.setObservacao(jtaObservacao.getText().trim());
        try {
            if (MaoDeObraService.getInstance().salvaMaoDeObra(maoDeObra, isModoEdicao())) {
                NotifierPigeon.getInstance().notificaSucesso(!isModoEdicao()
                        ? "Cadastro bem sucedido!" : "Atualização bem sucedida!");
            }
            limpaForm();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        jmskCodigoMaoDeObra.requestFocus();
    }

    @FXML
    private void jbtnExcluirAction() {
        if (DialogFactory.getInstance().adverte("trash.png",
                "Excluir Mão de obra", "Esta mão de obra será excluido permanentemente",
                "Tem certeza que deseja excluir esta mão de obra ?", "EXCLUIR")) {
            try {
                MaoDeObraService.getInstance().excluiMaoDeObra(Integer.parseInt(jmskCodigoMaoDeObra.getText()));
                notificacoes.notificaExcluido();
                limpaForm();
                jmskCodigoMaoDeObra.requestFocus();
            } catch (SQLException | ClassNotFoundException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoMaoDeObra.resetValidation();
        jtxDescricaoMaoDeObra.resetValidation();
        jmskCustoMaoDeObra.resetValidation();

        jmskCodigoMaoDeObra.requestFocus();
    }

    private void limpaForm() {
        jmskCodigoMaoDeObra.setEditable(true);
        jbtnExcluir.setDisable(true);
        setModoEdicao(false);

        jmskCodigoMaoDeObra.setText("");
        jtxDescricaoMaoDeObra.setText("");
        jtxDAMaoDeObra.setText("");
        jmskCustoMaoDeObra.setText("");
        jtaObservacao.setText("");

        jbtnSalvar.setText("SALVAR");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CADASTRO_MAO_OBRA)) {
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
