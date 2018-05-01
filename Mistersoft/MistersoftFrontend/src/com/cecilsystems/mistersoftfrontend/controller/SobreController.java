package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftbackend.service.VersaoService;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SobreController implements Initializable {

    @FXML
    private Label lblVersao;
    private Stage formStage;
    private static SobreController uniqueInstance;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblVersao.setText(VersaoService.getInstance().selecionaUltimaVersao().toString());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SplashLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SobreController() {
    }

    public static synchronized SobreController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SobreController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.AJUDA_SOBRE)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

}
