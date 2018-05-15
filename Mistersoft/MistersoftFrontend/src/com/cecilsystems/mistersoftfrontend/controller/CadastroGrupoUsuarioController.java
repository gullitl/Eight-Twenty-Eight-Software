package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaGrupoUsuarioController;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import com.cecilsystems.mistersoftbackend.service.GrupoUsuarioService;
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

public class CadastroGrupoUsuarioController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoGrupoUsuario;
    @FXML
    private JFXTextField jtxDescricaoGrupoUsuario;
    @FXML
    private JFXTextField jtxDAGrupoUsuario;
    @FXML
    private JFXButton jbtnConsultarGrupoUsuario;
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
    private static CadastroGrupoUsuarioController uniqueInstance;

    public CadastroGrupoUsuarioController() {
    }

    public static synchronized CadastroGrupoUsuarioController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroGrupoUsuarioController();
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

        jmskCodigoGrupoUsuario.getValidators().add(codigoValidator);
        jtxDescricaoGrupoUsuario.getValidators().add(descricaoValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        descricaoValidator.setMessage("Descrição: Campo obrigatório");

        jmskCodigoGrupoUsuario.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoGrupoUsuario.validate()) {
                    jmskCodigoGrupoUsuarioFocusLost();
                } else {
                    if (jbtnConsultarGrupoUsuario.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoGrupoUsuario.resetValidation();
                        return;
                    }
                    jmskCodigoGrupoUsuario.requestFocus();
                }
            }
        });
        jbtnConsultarGrupoUsuario.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoGrupoUsuario.validate()) {
                    jmskCodigoGrupoUsuario.requestFocus();
                }
            }
        });

        jtxDescricaoGrupoUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxDescricaoGrupoUsuario.validate();
                }
            }
        });
        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        descricaoValidator.setIcon(new ImageView(errorIcon));
    }

    private void jmskCodigoGrupoUsuarioFocusLost() {
        if (!jmskCodigoGrupoUsuario.isEditable()) {
            return;
        }
        try {
            GrupoUsuario grupoUsuario = GrupoUsuarioService.getInstance()
                    .selecionaGrupoUsuario(Integer.parseInt(jmskCodigoGrupoUsuario.getText()));
            jmskCodigoGrupoUsuario.setEditable(false);
            if (grupoUsuario == null) {
                return;
            }
            preencheGrupoUsurio(grupoUsuario);
        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoGrupoUsuarioKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoGrupoUsuario.validate()) {
                jtxDescricaoGrupoUsuario.requestFocus();
            } else {
                jmskCodigoGrupoUsuario.requestFocus();
            }
        }
    }

    @FXML
    public void jmskCodigoGrupoUsuarioKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            try {
                Integer csb = GrupoUsuarioService.getInstance().selecionaCodigoGrupoUsuarioSubsequente();
                jmskCodigoGrupoUsuario.setText(csb.toString());
                jmskCodigoGrupoUsuario.positionCaret(csb.toString().length());
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    private void preencheGrupoUsurio(GrupoUsuario grupoUsuario) {
        jbtnExcluir.setDisable(false);
        setModoEdicao(true);

        jmskCodigoGrupoUsuario.setText("" + grupoUsuario.getCdGrupoUsuario());
        jtxDescricaoGrupoUsuario.setText(grupoUsuario.getDsGrupoUsuario());
        jtxDAGrupoUsuario.setText(grupoUsuario.getDaGrupoUsuario());

        jbtnSalvar.setText("ATUALIZAR");
    }

    @FXML
    private void jbtnConsultarGrupoUsuarioAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaGrupoUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Grupos Usuário");
            dialogStage.setResizable(false);;
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(414);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaGrupoUsuarioController controller = loader.getController();

            dialogStage.showAndWait();

            GrupoUsuario grupoUsuario = controller.getGrupoUsuario();

            if (grupoUsuario != null) {
                grupoUsuario = GrupoUsuarioService.getInstance()
                        .selecionaGrupoUsuario(grupoUsuario.getCdGrupoUsuario());
                jmskCodigoGrupoUsuario.setEditable(false);
                preencheGrupoUsurio(grupoUsuario);
            }

            jmskCodigoGrupoUsuario.resetValidation();
            jtxDescricaoGrupoUsuario.resetValidation();

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jbtnSalvarAction() {
        if (!jmskCodigoGrupoUsuario.validate()) {
            jmskCodigoGrupoUsuario.requestFocus();
            return;
        }
        if (!jtxDescricaoGrupoUsuario.validate()) {
            jtxDescricaoGrupoUsuario.requestFocus();
            return;
        }

        GrupoUsuario grupoUsuario = new GrupoUsuario();
        grupoUsuario.setCdGrupoUsuario(Integer.parseInt(jmskCodigoGrupoUsuario.getText()));
        grupoUsuario.setDsGrupoUsuario(jtxDescricaoGrupoUsuario.getText().trim());
        grupoUsuario.setDaGrupoUsuario(jtxDAGrupoUsuario.getText().trim());

        try {

            if (GrupoUsuarioService.getInstance().salvaGrupoUsuario(grupoUsuario, isModoEdicao())) {
                NotifierPigeon.getInstance().notificaSucesso(!isModoEdicao()
                        ? "Cadastro bem sucedido!" : "Atualização bem sucedida!");
            }
            limpaForm();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        jmskCodigoGrupoUsuario.requestFocus();
    }

    @FXML
    private void jbtnExcluirAction() {
        if (DialogFactory.getInstance().adverte("trash.png",
                "Excluir Grupo usuário", "Este grupo usuário será excluido permanentemente",
                "Tem certeza que deseja excluir este grupo usuário ?", "EXCLUIR")) {
            try {
                GrupoUsuarioService.getInstance().excluiGrupoUsuario(Integer.parseInt(jmskCodigoGrupoUsuario.getText()));
                notificacoes.notificaExcluido();
                limpaForm();
                jmskCodigoGrupoUsuario.requestFocus();
            } catch (SQLException | ClassNotFoundException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoGrupoUsuario.resetValidation();
        jtxDescricaoGrupoUsuario.resetValidation();

        jmskCodigoGrupoUsuario.requestFocus();
    }

    private void limpaForm() {
        jmskCodigoGrupoUsuario.setEditable(true);
        jbtnExcluir.setDisable(true);
        setModoEdicao(false);

        jmskCodigoGrupoUsuario.setText("");
        jtxDescricaoGrupoUsuario.setText("");
        jtxDAGrupoUsuario.setText("");

        jbtnSalvar.setText("SALVAR");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CADASTRO_GRUPO_USUARIO)) {
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
