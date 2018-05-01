package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.MaoDeObra;
import com.cecilsystems.mistersoftbackend.service.MaoDeObraService;
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

public class ConsultaMaoDeObraController implements Initializable {

    @FXML
    private TableView<MaoDeObra> tvwMaoDeObra;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<MaoDeObra> obsMaoDeObra = FXCollections.observableArrayList();
    private MaoDeObra maoDeObra;
    private static ConsultaMaoDeObraController uniqueInstance;

    public ConsultaMaoDeObraController() {
    }

    public static synchronized ConsultaMaoDeObraController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaMaoDeObraController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("cdMaoDeObra"));
        clmCodigo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigo.getStyleClass().add("right-header");

        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("dsMaoDeObra"));
        clmDescricao.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricao.getStyleClass().add("left-header");

        try {
            //Coloca lista de usuários no ObservableList
            obsMaoDeObra.addAll(MaoDeObraService.getInstance().listaMaoDeObra());
            tvwMaoDeObra.setItems(obsMaoDeObra);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaMaoDeObraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaQtd() {
        if (!obsMaoDeObra.isEmpty()) {
            lblQtd.setText(obsMaoDeObra.size() > 1 ? obsMaoDeObra.size()
                    + " maos de obra encontrados" : obsMaoDeObra.size() + " mao de obra encontrado");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwMaoDeObraSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            MaoDeObra mdo = (MaoDeObra) tvwMaoDeObra.getSelectionModel().getSelectedItem();
            if (mdo == null) {
                return;
            }
            this.setMaoDeObra(mdo);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<MaoDeObra> lstMaoDeObra = MaoDeObraService.getInstance().listaMaoDeObra();

            obsMaoDeObra.clear();
            lstMaoDeObra.stream().filter((maoDeObra1) -> (maoDeObra1.getDsMaoDeObra().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((maoDeObra1) -> {
                        obsMaoDeObra.add(maoDeObra1);
                    });

            tvwMaoDeObra.setItems(obsMaoDeObra);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaMaoDeObraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MaoDeObra getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(MaoDeObra maoDeObra) {
        this.maoDeObra = maoDeObra;
    }
}
