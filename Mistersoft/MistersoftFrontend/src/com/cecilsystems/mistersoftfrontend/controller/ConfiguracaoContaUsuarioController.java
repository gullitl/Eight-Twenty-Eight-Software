package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import com.cecilsystems.mistersoftbackend.model.UsuarioLogado;
import com.cecilsystems.mistersoftbackend.service.ConfiguracaoContaUsuarioService;
import com.cecilsystems.mistersoftbackend.service.GrupoUsuarioService;
import com.cecilsystems.mistersoftbackend.service.UsuarioService;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.EmailValidator;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ConfiguracaoContaUsuarioController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoUsuario;
    @FXML
    private JFXTextField jtxNomeUsuario;
    @FXML
    private JFXTextField jtxEmailUsuario;

    @FXML
    private JFXPasswordField jpwSenhaUsuario;
    @FXML
    private JFXPasswordField jpwConfirmarSenhaUsuario;
    @FXML
    private JFXButton jbtnSalvar;
    @FXML
    private JFXButton jbtnFechar;
    @FXML
    private JFXButton jbtnLimpar;

    @FXML
    private Label lblEmailInvalido;
    @FXML
    private ImageView imgvwEmailInvalido;

    @FXML
    private Label lblConfirmarSenha;
    @FXML
    private ImageView imgvwConfirmarSenha;

    private Stage formStage;
    private final ObservableList<GrupoUsuario> obsGrupoUsuario = FXCollections.observableArrayList();
    private static ConfiguracaoContaUsuarioController uniqueInstance;

    public ConfiguracaoContaUsuarioController() {
    }

    public static synchronized ConfiguracaoContaUsuarioController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConfiguracaoContaUsuarioController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        preencharUsuario(UsuarioLogado.getInstance().getUsuario());

        lblEmailInvalido.setVisible(false);
        imgvwEmailInvalido.setVisible(false);
        lblEmailInvalido.setStyle("-fx-text-fill: #c00d0d;");

        lblConfirmarSenha.setVisible(false);
        imgvwConfirmarSenha.setVisible(false);
        lblConfirmarSenha.setStyle("-fx-text-fill: #c00d0d;");

        try {
            GrupoUsuarioService.getInstance().listaGrupoUsuario().forEach(obsGrupoUsuario::add);
        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }

        RequiredFieldValidator nomeValidator = new RequiredFieldValidator();
        RequiredFieldValidator emailValidator = new RequiredFieldValidator();
        RequiredFieldValidator senhaValidator = new RequiredFieldValidator();
        RequiredFieldValidator confirmarSenhaValidator = new RequiredFieldValidator();

        jtxNomeUsuario.getValidators().add(nomeValidator);
        jtxEmailUsuario.getValidators().add(emailValidator);
        jpwSenhaUsuario.getValidators().add(senhaValidator);
        jpwConfirmarSenhaUsuario.getValidators().add(confirmarSenhaValidator);

        nomeValidator.setMessage("Nome: Campo obrigatório");
        emailValidator.setMessage("E-mail: Campo obrigatório");
        senhaValidator.setMessage("Senha: Campo obrigatório");
        confirmarSenhaValidator.setMessage("Confirmar Senha: Campo obrigatório");

        jtxNomeUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxNomeUsuario.validate();
                }
            }
        });

        jtxEmailUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jtxEmailUsuario.validate()) {
                        if (EmailValidator.getInstance().validaEmail(jtxEmailUsuario.getText())) {
                            lblEmailInvalido.setVisible(false);
                            imgvwEmailInvalido.setVisible(false);
                        } else {
                            lblEmailInvalido.setText("E-mail: Inválido");
                            lblEmailInvalido.setVisible(true);
                            imgvwEmailInvalido.setVisible(true);
                        }
                    } else {
                        lblEmailInvalido.setVisible(false);
                        imgvwEmailInvalido.setVisible(false);
                    }
                }
            }
        });

        jpwSenhaUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jpwSenhaUsuario.validate();
                }
            }
        });

        jpwConfirmarSenhaUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jpwConfirmarSenhaUsuario.validate()) {
                        if (jpwSenhaUsuario.getText().equals(jpwConfirmarSenhaUsuario.getText())) {
                            lblConfirmarSenha.setVisible(false);
                            imgvwConfirmarSenha.setVisible(false);
                        } else {
                            lblConfirmarSenha.setVisible(true);
                            imgvwConfirmarSenha.setVisible(true);
                        }
                    } else {
                        lblConfirmarSenha.setVisible(false);
                        imgvwConfirmarSenha.setVisible(false);
                    }
                }
            }
        });

        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        nomeValidator.setIcon(new ImageView(errorIcon));
        emailValidator.setIcon(new ImageView(errorIcon));
        senhaValidator.setIcon(new ImageView(errorIcon));
        confirmarSenhaValidator.setIcon(new ImageView(errorIcon));
    }

    private void preencharUsuario(Usuario usuario) {
        usuario = UsuarioLogado.getInstance().getUsuario();

        jmskCodigoUsuario.setText(usuario.getCdUsuario() + "");
        jtxNomeUsuario.setText(usuario.getNome());
        jtxEmailUsuario.setText(usuario.getEmail());
        jpwSenhaUsuario.setText(usuario.getSenha());

    }

    @FXML
    private void jbtnSalvarAction() {
        if (!jmskCodigoUsuario.validate()) {
            jmskCodigoUsuario.requestFocus();
            return;
        }
        if (!jtxNomeUsuario.validate()) {
            jtxNomeUsuario.requestFocus();
            return;
        }
        if (!jtxEmailUsuario.validate()) {
            jtxEmailUsuario.requestFocus();
            return;
        }
        if (!EmailValidator.getInstance().validaEmail(jtxEmailUsuario.getText())) {
            lblEmailInvalido.setText("E-mail: Inválido");
            lblEmailInvalido.setVisible(true);
            imgvwEmailInvalido.setVisible(true);
            jtxEmailUsuario.requestFocus();
            return;
        }

        if (!jpwSenhaUsuario.validate()) {
            jpwSenhaUsuario.requestFocus();
            return;
        }
        if (!jpwConfirmarSenhaUsuario.validate()) {
            jpwConfirmarSenhaUsuario.requestFocus();
            return;
        }
        if (!jpwSenhaUsuario.getText().equals(jpwConfirmarSenhaUsuario.getText())) {
            lblConfirmarSenha.setVisible(true);
            imgvwConfirmarSenha.setVisible(true);
            jpwConfirmarSenhaUsuario.requestFocus();
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setCdUsuario(Integer.parseInt(jmskCodigoUsuario.getText()));
        usuario.setNome(jtxNomeUsuario.getText().trim());
        usuario.setEmail(jtxEmailUsuario.getText().trim());
        usuario.setSenha(jpwSenhaUsuario.getText().trim());

        if (usuario.equals(UsuarioLogado.getInstance().getUsuario())) {
            DialogFactory.getInstance().informa1("", "Não houve necessidade de atualizar o usuário"
                    + ", pois nenhuma alteração foi realizada!");
            return;
        }

        try {
            if (UsuarioService.getInstance().isEmailJaUtilizado(usuario, true)) {
                lblEmailInvalido.setText("E-mail: Já utilizado por outro usuário");
                lblEmailInvalido.setVisible(true);
                imgvwEmailInvalido.setVisible(true);
                jtxEmailUsuario.requestFocus();
                return;
            }

            UsuarioLogado.getInstance().getUsuario().setNome(usuario.getNome());
            UsuarioLogado.getInstance().getUsuario().setEmail(usuario.getEmail());
            UsuarioLogado.getInstance().getUsuario().setSenha(usuario.getSenha());
            UsuarioLogado.getInstance().setPrecisaFazerLogout(true);

            ConfiguracaoContaUsuarioService.getInstance()
                    .salvaConfiguracaoContaUsuario(UsuarioLogado.getInstance().getUsuario());

            limpaForm();
            preencharUsuario(UsuarioLogado.getInstance().getUsuario());

            DialogFactory.getInstance().informa1("", "É necessário fazer logout"
                    + " para que as alterações sejam aplicadas!");

            jbtnFecharAction();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(ConfiguracaoContaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoUsuario.resetValidation();
        jtxNomeUsuario.resetValidation();
        jtxEmailUsuario.resetValidation();
        jpwSenhaUsuario.resetValidation();
        jpwConfirmarSenhaUsuario.resetValidation();

        lblEmailInvalido.setVisible(false);
        imgvwEmailInvalido.setVisible(false);

        lblConfirmarSenha.setVisible(false);
        imgvwConfirmarSenha.setVisible(false);

        jmskCodigoUsuario.requestFocus();
    }

    private void limpaForm() {
        jmskCodigoUsuario.setEditable(true);

        jmskCodigoUsuario.setText("");
        jtxNomeUsuario.setText("");
        jtxEmailUsuario.setText("");
        jpwSenhaUsuario.setText("");
        jpwConfirmarSenhaUsuario.setText("");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CONFIGURACOES_CONTA_USUARIO)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

}
