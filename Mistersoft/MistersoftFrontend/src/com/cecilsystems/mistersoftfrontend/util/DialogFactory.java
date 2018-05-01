package com.cecilsystems.mistersoftfrontend.useful;

import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftfrontend.MainApp;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Plamedi L. Lusembo
 */
public class DialogFactory {

    private String iconPath;
    private Alert.AlertType alertType;
    private String title;
    private String headerText;
    private String contentText;
    private String yesButtonText;

    private static DialogFactory uniqueInstance;

    public DialogFactory() {
    }

    public static synchronized DialogFactory getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DialogFactory();
        }
        return uniqueInstance;
    }

    public void informa1(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(MainApp.class
                .getResource(PathEnum.STYLES_PATH + "myDialogs.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "info.png").toString()));

        alert.showAndWait();
    }

    public boolean informa2(String iconPath, String title, String headerText, String contentText, String yesButtonText) {
        this.iconPath = iconPath;
        alertType = Alert.AlertType.INFORMATION;
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
        this.yesButtonText = yesButtonText;
        return mostra();
    }

    public boolean questiona(String iconPath, String title, String headerText, String contentText, String yesButtonText) {
        this.iconPath = iconPath;
        alertType = Alert.AlertType.CONFIRMATION;
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
        this.yesButtonText = yesButtonText;
        return mostra();
    }

    public boolean adverte(String iconPath, String title, String headerText, String contentText, String yesButtonText) {
        this.iconPath = iconPath;
        alertType = Alert.AlertType.WARNING;
        this.title = title;
        this.headerText = headerText;
        this.contentText = contentText;
        this.yesButtonText = yesButtonText;
        return mostra();
    }

    public void erro(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(MainApp.class
                .getResource(PathEnum.STYLES_PATH + "myDialogs.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stage = (Stage) dialogPane.getScene().getWindow();

        alert.showAndWait();
    }

    private boolean mostra() {
        Alert dialogoExe = new Alert(alertType);
        ButtonType btnFechar = new ButtonType(yesButtonText, ButtonBar.ButtonData.YES);
        ButtonType btnCancelar = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialogoExe.setTitle(title);
        dialogoExe.setHeaderText(headerText);
        dialogoExe.setContentText(contentText + "\n ");
        dialogoExe.getButtonTypes().setAll(btnFechar, btnCancelar);

        DialogPane dialogPane = dialogoExe.getDialogPane();

        dialogPane.getStylesheets().add(MainApp.class
                .getResource(PathEnum.STYLES_PATH + "myDialogs.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getIcons().add(new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + iconPath).toString()));

        Optional<ButtonType> result = dialogoExe.showAndWait();
        if (!result.isPresent()) {
            return false;
        }
        return result.get() == btnFechar;
    }
}
