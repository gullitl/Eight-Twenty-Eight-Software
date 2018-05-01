package com.cecilsystems.mistersoftfrontend;

import com.cecilsystems.mistersoftbackend.service.MainService;
import com.cecilsystems.mistersoftbackend.service.VersaoService;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    private ImageView imageView;
    private Stage splashLayoutStage;

    @Override
    public void init() throws IOException {
    }

    private void showSplashConexaoComBD() {
        try {
            StackPane page = FXMLLoader.load(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "SplashConexaoComBD.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setTitle("Conexão");
            dialogStage.setScene(scene);

            dialogStage.show();
            MainService.getInstance().iniciaBase();
            dialogStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showSplashAtualizacao(final Stage initStage, Task task,
            InitCompletionHandler initCompletionHandler) throws IOException {

        HBox vbxSplashAtualizacao = new HBox();

        imageView = new ImageView(new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "loading.gif").toString()));
        imageView.setStyle("-fx-background-color: white");
        imageView.setFitWidth(147);
        imageView.setFitHeight(149);

        Pane pane = new Pane();
        pane.setPrefWidth(292);
        pane.setPrefHeight(147);
        pane.setStyle("-fx-background-color: white");

        Label lblMensagemAtualizacao = new Label();
        lblMensagemAtualizacao.setStyle("-fx-font-size: 16; -fx-text-fill: #1de0c3");
        lblMensagemAtualizacao.setLayoutX(-23);
        lblMensagemAtualizacao.setLayoutY(59);

        pane.getChildren().add(lblMensagemAtualizacao);
        vbxSplashAtualizacao.getChildren().addAll(imageView, pane);

        lblMensagemAtualizacao.textProperty().bind(task.messageProperty());

        vbxSplashAtualizacao.setEffect(new DropShadow());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                initStage.toFront();
                FadeTransition fadeSplash;
                fadeSplash = new FadeTransition(Duration.seconds(0.0), vbxSplashAtualizacao);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(vbxSplashAtualizacao, Color.TRANSPARENT);
        initStage.setResizable(false);
        initStage.setMaximized(false);
        initStage.setScene(splashScene);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    private void showSplashLayout(ReadOnlyObjectProperty<ObservableList<String>> friends) {
        try {
            splashLayoutStage = new Stage();
            StackPane splashLayoutPane = FXMLLoader.load(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "SplashLayout.fxml"));
            Scene scene = new Scene(splashLayoutPane);
            splashLayoutStage.getIcons().add(new Image(PathEnum.IMAGES_PATH + "mistersoftlogo.png"));
            splashLayoutStage.setResizable(false);
            splashLayoutStage.setMaximized(false);
            splashLayoutStage.setScene(scene);
            splashLayoutStage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public interface InitCompletionHandler {

        void complete();
    }

    @Override
    public void start(final Stage initStage) throws Exception {

        showSplashConexaoComBD();
        final Task friendTask = new Task() {
            @Override
            protected String call() throws InterruptedException, FileNotFoundException {
                updateMessage("Trabalhando em Atualizações . . .");
                Thread.sleep(200);
                try {
                    VersaoService.getInstance().atualizaEsquema();
                    updateMessage("Atualização concluida com êxito.");
                    imageView.setImage(new Image(MainApp.class
                            .getResource(PathEnum.IMAGES_PATH + "smileys.jpg")
                            .toString()));
                    Thread.sleep(1750);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }
        };

        if (VersaoService.getInstance().temNovaVersao()) {
            if (DialogFactory.getInstance().informa2("refresh.png",
                    "Nova versão do software", "Uma nova versão do software Mistersoft está disponível para atualização",
                    "O mecanismo de Atualização do Mistersoft garante que você tenha a versão mais atualizada do software."
                    + " A Atualização do Mistersoft inclui novas funcionalidades e correções para melhor desempenho "
                    + "e estabilidade aprimorada do software.",
                    "ATUALIZAR")) {
                showSplashAtualizacao(initStage, friendTask, ()
                        -> showSplashLayout(friendTask.valueProperty()));
                new Thread(friendTask).start();
            }

        } else {
            showSplashLayout(friendTask.valueProperty());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
