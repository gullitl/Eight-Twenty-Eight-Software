package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import com.cecilsystems.mistersoftbackend.service.LoginService;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Plamedi L. Lusembo
 */
public class LoginController implements Initializable {

    private Stage loginStage;
    @FXML
    private JFXTextField jtxEmail;
    @FXML
    private JFXPasswordField jpwSenha;
    @FXML
    private JFXCheckBox jchxLembrarDeMim;
    private static LoginController uniqueInstance;

    public LoginController() {
    }

    public static synchronized LoginController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LoginController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Usuario usuario = LoginService.getInstance().lembraDoUsuario();
            jchxLembrarDeMim.setSelected(true);
            jtxEmail.setText(usuario.getEmail());
            jpwSenha.setText(usuario.getSenha());
        } catch (FileNotFoundException | UnknownHostException | SocketException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequiredFieldValidator emailValidator = new RequiredFieldValidator();
        RequiredFieldValidator senhaValidator = new RequiredFieldValidator();
        jtxEmail.getValidators().add(emailValidator);
        jpwSenha.getValidators().add(senhaValidator);
        emailValidator.setMessage("Email: Campo obrigatório");
        senhaValidator.setMessage("Senha: Campo obrigatório");
        jtxEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    jtxEmail.validate();
                }
            }
        });
        jpwSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    jpwSenha.validate();
                }
            }
        });

        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        emailValidator.setIcon(new ImageView(errorIcon));
        senhaValidator.setIcon(new ImageView(errorIcon));

    }

    @FXML
    private void jbtnEntrarOnAction() {
        if (!jtxEmail.validate()) {
            return;
        }
        if (!jpwSenha.validate()) {
            return;
        }
        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(jtxEmail.getText());
            usuario.setSenha(jpwSenha.getText());

            if (LoginService.getInstance().entrar(usuario, jchxLembrarDeMim.isSelected())) {
                Stage mainLayoutStage = new Stage();
                loginStage = (Stage) jchxLembrarDeMim.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass()
                        .getResource(PathEnum.VIEW_PATH + "MainLayout.fxml"));
                StackPane mainStackPane = loader.load();
                Scene scene = new Scene(mainStackPane);
                mainLayoutStage.setMaximized(true);
                mainLayoutStage.getIcons().add(new Image(PathEnum.IMAGES_PATH + "mistersoftlogo.png"));
                mainLayoutStage.setTitle("Mistersoft");
                mainLayoutStage.setScene(scene);
                MainController mainController = loader.getController();

                mainLayoutStage.setOnCloseRequest((WindowEvent we) -> {
                    if (!DialogFactory.getInstance().questiona("exit.png",
                            "Fechar o sistema", "Você está prestes a fechar o sistema Mistersoft",
                            "Tem certeza que deseja fechar o sistema ?", "FECHAR")) {
                        we.consume();
                    }
                });

                mainLayoutStage.show();

                loginStage.close();
            } else {
                NotifierPigeon.getInstance().notificaErro("Senha ou e-mail errado!");
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
