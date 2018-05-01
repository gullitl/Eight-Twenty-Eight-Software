package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.PecaSimples;
import com.cecilsystems.mistersoftbackend.service.PecaSimplesService;
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

public class ConsultaPecaSimplesController implements Initializable {

    @FXML
    private TableView<PecaSimples> tvwPecaSimples;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<PecaSimples> obsPecaSimples = FXCollections.observableArrayList();
    private PecaSimples pecaSimples;
    private static ConsultaPecaSimplesController uniqueInstance;

    public ConsultaPecaSimplesController() {
    }

    public static synchronized ConsultaPecaSimplesController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaPecaSimplesController();
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
            obsPecaSimples.addAll(PecaSimplesService.getInstance().listaPecaSimples());
            tvwPecaSimples.setItems(obsPecaSimples);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaPecaSimplesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaQtd() {
        if (!obsPecaSimples.isEmpty()) {
            lblQtd.setText(obsPecaSimples.size() > 1 ? obsPecaSimples.size()
                    + " peças encontradas" : obsPecaSimples.size() + " peças encontrada");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwPecaSimplesSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            PecaSimples pcspl = (PecaSimples) tvwPecaSimples.getSelectionModel().getSelectedItem();
            if (pcspl == null) {
                return;
            }
            this.setPecaSimples(pcspl);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<PecaSimples> lstPecaSimples = PecaSimplesService.getInstance().listaPecaSimples();

            obsPecaSimples.clear();
            lstPecaSimples.stream().filter((pecaSimples1) -> (pecaSimples1.getDsPeca().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((pecaSimples1) -> {
                        obsPecaSimples.add(pecaSimples1);
                    });

            tvwPecaSimples.setItems(obsPecaSimples);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaPecaSimplesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PecaSimples getPecaSimples() {
        return pecaSimples;
    }

    public void setPecaSimples(PecaSimples pecaSimples) {
        this.pecaSimples = pecaSimples;
    }
}
