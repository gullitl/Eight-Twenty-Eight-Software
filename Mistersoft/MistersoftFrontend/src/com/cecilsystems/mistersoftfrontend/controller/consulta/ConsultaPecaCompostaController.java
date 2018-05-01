package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.PecaComposta;
import com.cecilsystems.mistersoftbackend.service.PecaCompostaService;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConsultaPecaCompostaController implements Initializable {

    @FXML
    private TableView<PecaComposta> tvwPecaComposta;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<PecaComposta> obsPecaComposta = FXCollections.observableArrayList();
    private PecaComposta pecaComposta;
    private static ConsultaPecaCompostaController uniqueInstance;

    public ConsultaPecaCompostaController() {
    }

    public static synchronized ConsultaPecaCompostaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaPecaCompostaController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("cdPeca"));
        clmCodigo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigo.getStyleClass().add("right-header");

        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("dsPeca"));
        clmDescricao.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricao.getStyleClass().add("left-header");

        try {
            //Coloca lista de usuários no ObservableList
            obsPecaComposta.addAll(PecaCompostaService.getInstance().listaPecaComposta());
            tvwPecaComposta.setItems(obsPecaComposta);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaPecaCompostaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaQtd() {
        if (!obsPecaComposta.isEmpty()) {
            lblQtd.setText(obsPecaComposta.size() > 1 ? obsPecaComposta.size()
                    + " peças encontradas" : obsPecaComposta.size() + " peças encontrada");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwPecaCompostaSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            PecaComposta pccpst = (PecaComposta) tvwPecaComposta.getSelectionModel().getSelectedItem();
            if (pccpst == null) {
                return;
            }
            this.setPecaComposta(pccpst);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<PecaComposta> lstPecaComposta = PecaCompostaService.getInstance().listaPecaComposta();

            obsPecaComposta.clear();
            lstPecaComposta.stream().filter((pecaComposta1) -> (pecaComposta1.getDsPeca().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((pecaComposta1) -> {
                        obsPecaComposta.add(pecaComposta1);
                    });

            tvwPecaComposta.setItems(obsPecaComposta);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaPecaCompostaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PecaComposta getPecaComposta() {
        return pecaComposta;
    }

    public void setPecaComposta(PecaComposta pecaComposta) {
        this.pecaComposta = pecaComposta;
    }
}
