package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.Peca;
import com.cecilsystems.mistersoftbackend.service.PecaService;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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

public class ConsultaPecaController implements Initializable {

    @FXML
    private TableView<Peca> tvwPeca;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private TableColumn clmTipo;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<Peca> obsPeca = FXCollections.observableArrayList();
    private Peca peca;
    private static ConsultaPecaController uniqueInstance;

    public ConsultaPecaController() {
    }

    public static synchronized ConsultaPecaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaPecaController();
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

        clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipoPeca"));
        clmTipo.setStyle("-fx-alignment: CENTER-LEFT;");
        clmTipo.getStyleClass().add("center-header");

        try {
            //Coloca lista de usuários no ObservableList
            obsPeca.addAll(PecaService.getInstance().listaPeca());
            tvwPeca.setItems(obsPeca);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    private void atualizaQtd() {
        if (!obsPeca.isEmpty()) {
            lblQtd.setText(obsPeca.size() > 1 ? obsPeca.size()
                    + " peças encontradas" : obsPeca.size() + " peças encontrada");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwPecaSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Peca pcspl = (Peca) tvwPeca.getSelectionModel().getSelectedItem();
            if (pcspl == null) {
                return;
            }
            this.setPeca(pcspl);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<Peca> lstPeca = PecaService.getInstance().listaPeca();

            obsPeca.clear();
            lstPeca.stream().filter((peca1) -> (peca1.getDsPeca().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((peca1) -> {
                        obsPeca.add(peca1);
                    });

            tvwPeca.setItems(obsPeca);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
