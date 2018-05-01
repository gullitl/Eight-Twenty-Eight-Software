package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaUnidadeMedidaController;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
import com.cecilsystems.mistersoftbackend.service.UnidadeMedidaService;
import com.cecilsystems.mistersoftfrontend.useful.Notificacoes;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.jfoenix.controls.JFXButton;
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

public class CadastroUnidadeMedidaController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoUnidadeMedida;
    @FXML
    private JFXTextField jtxDescricaoUnidadeMedida;
    @FXML
    private JFXTextField jtxDAUnidadeMedida;
    @FXML
    private JFXButton jbtnConsultarUnidadeMedida;
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
    private static CadastroUnidadeMedidaController uniqueInstance;

    public CadastroUnidadeMedidaController() {
    }

    public static synchronized CadastroUnidadeMedidaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroUnidadeMedidaController();
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

        jmskCodigoUnidadeMedida.getValidators().add(codigoValidator);
        jtxDescricaoUnidadeMedida.getValidators().add(descricaoValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        descricaoValidator.setMessage("Descrição: Campo obrigatório");

        jmskCodigoUnidadeMedida.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoUnidadeMedida.validate()) {
                    jmskCodigoUnidadeMedidaFocusLost();
                } else {
                    if (jbtnConsultarUnidadeMedida.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoUnidadeMedida.resetValidation();
                        return;
                    }
                    jmskCodigoUnidadeMedida.requestFocus();
                }
            }
        });
        jbtnConsultarUnidadeMedida.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoUnidadeMedida.validate()) {
                    jmskCodigoUnidadeMedida.requestFocus();
                }
            }
        });

        jtxDescricaoUnidadeMedida.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxDescricaoUnidadeMedida.validate();
                }
            }
        });
        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        descricaoValidator.setIcon(new ImageView(errorIcon));
    }

    private void jmskCodigoUnidadeMedidaFocusLost() {
        if (!jmskCodigoUnidadeMedida.isEditable()) {
            return;
        }
        try {
            UnidadeMedida unidadeMedida = UnidadeMedidaService.getInstance()
                    .selecionaUnidadeMedida(Integer.parseInt(jmskCodigoUnidadeMedida.getText()));
            jmskCodigoUnidadeMedida.setEditable(false);
            if (unidadeMedida == null) {
                return;
            }
            preencheUnidadeMedida(unidadeMedida);

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoUnidadeMedidaKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoUnidadeMedida.validate()) {
                jtxDescricaoUnidadeMedida.requestFocus();
            } else {
                jmskCodigoUnidadeMedida.requestFocus();
            }
        }
    }

    private void preencheUnidadeMedida(UnidadeMedida unidadeMedida) {
        jbtnExcluir.setDisable(false);

        setModoEdicao(true);
        jmskCodigoUnidadeMedida.setText("" + unidadeMedida.getCdUnidadeMedida());
        jtxDescricaoUnidadeMedida.setText(unidadeMedida.getDsUnidadeMedida());
        jtxDAUnidadeMedida.setText(unidadeMedida.getDaUnidadeMedida());

        jbtnSalvar.setText("ATUALIZAR");
    }

    @FXML
    public void jmskCodigoUnidadeMedidaKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            try {
                Integer csb = UnidadeMedidaService.getInstance().selecionaCodigoUnidadeMedidaSubsequente();
                jmskCodigoUnidadeMedida.setText(csb.toString());
                jmskCodigoUnidadeMedida.positionCaret(csb.toString().length());
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnConsultarUnidadeMedidaAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaUnidadeMedida.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Unidade Medida");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(414);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaUnidadeMedidaController controller = loader.getController();
            dialogStage.showAndWait();

            UnidadeMedida unidadeMedida = controller.getUnidadeMedida();

            if (unidadeMedida != null) {
                unidadeMedida = UnidadeMedidaService.getInstance()
                        .selecionaUnidadeMedida(unidadeMedida.getCdUnidadeMedida());
                jmskCodigoUnidadeMedida.setEditable(false);
                preencheUnidadeMedida(unidadeMedida);
            }

            jmskCodigoUnidadeMedida.resetValidation();
            jtxDescricaoUnidadeMedida.resetValidation();

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jbtnSalvarAction() {
        if (!jmskCodigoUnidadeMedida.validate()) {
            jmskCodigoUnidadeMedida.requestFocus();
            return;
        }
        if (!jtxDescricaoUnidadeMedida.validate()) {
            jtxDescricaoUnidadeMedida.requestFocus();
            return;
        }
        if (!jtxDAUnidadeMedida.validate()) {
            jtxDAUnidadeMedida.requestFocus();
            return;
        }

        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setCdUnidadeMedida(Integer.parseInt(jmskCodigoUnidadeMedida.getText()));
        unidadeMedida.setDsUnidadeMedida(jtxDescricaoUnidadeMedida.getText().trim());
        unidadeMedida.setDaUnidadeMedida(jtxDAUnidadeMedida.getText().trim());
        try {
            if (UnidadeMedidaService.getInstance().salvaUnidadeMedida(unidadeMedida, isModoEdicao())) {
                NotifierPigeon.getInstance().notificaSucesso(!isModoEdicao()
                        ? "Cadastro bem sucedido!" : "Atualização bem sucedida!");
            }
            limpaForm();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        jmskCodigoUnidadeMedida.requestFocus();
    }

    @FXML
    private void jbtnExcluirAction() {
        if (DialogFactory.getInstance().adverte("trash.png",
                "Excluir Unidade Medida", "Esta unidade medida será excluida permanentemente",
                "Tem certeza que deseja excluir esta unidade medida ?", "EXCLUIR")) {
            try {
                UnidadeMedidaService.getInstance().excluiUnidadeMedida(Integer.parseInt(jmskCodigoUnidadeMedida.getText()));
                notificacoes.notificaExcluido();
                limpaForm();
                jmskCodigoUnidadeMedida.requestFocus();
            } catch (SQLException | ClassNotFoundException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoUnidadeMedida.resetValidation();
        jtxDescricaoUnidadeMedida.resetValidation();

        jmskCodigoUnidadeMedida.requestFocus();
    }

    private void limpaForm() {
        jmskCodigoUnidadeMedida.setEditable(true);
        jbtnExcluir.setDisable(true);
        setModoEdicao(false);

        jmskCodigoUnidadeMedida.setText("");
        jtxDescricaoUnidadeMedida.setText("");
        jtxDAUnidadeMedida.setText("");

        jbtnSalvar.setText("SALVAR");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CADASTRO_UNIDADE_MEDIDA)) {
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
