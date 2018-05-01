package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
import com.cecilsystems.mistersoftbackend.service.UnidadeMedidaService;
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

public class ConsultaUnidadeMedidaController implements Initializable {

    @FXML
    private TableView<UnidadeMedida> tvwUnidadeMedida;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<UnidadeMedida> obsUnidadeMedida = FXCollections.observableArrayList();
    private UnidadeMedida unidadeMedida;
    private static ConsultaUnidadeMedidaController uniqueInstance;

    public ConsultaUnidadeMedidaController() {
    }

    public static synchronized ConsultaUnidadeMedidaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaUnidadeMedidaController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("cdUnidadeMedida"));
        clmCodigo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigo.getStyleClass().add("right-header");

        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("dsUnidadeMedida"));
        clmDescricao.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricao.getStyleClass().add("left-header");

        try {
            //Coloca lista de usuários no ObservableList
            obsUnidadeMedida.addAll(UnidadeMedidaService.getInstance().listaUnidadeMedida());
            tvwUnidadeMedida.setItems(obsUnidadeMedida);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaUnidadeMedidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaQtd() {
        if (!obsUnidadeMedida.isEmpty()) {
            lblQtd.setText(obsUnidadeMedida.size() > 1 ? obsUnidadeMedida.size()
                    + " unidades medida encontradas" : obsUnidadeMedida.size() + " unidade medida encontrada");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwUnidadeMedidaSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            UnidadeMedida grusr = (UnidadeMedida) tvwUnidadeMedida.getSelectionModel().getSelectedItem();
            if (grusr == null) {
                return;
            }
            this.setUnidadeMedida(grusr);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<UnidadeMedida> lstGruposUsuario = UnidadeMedidaService.getInstance().listaUnidadeMedida();

            obsUnidadeMedida.clear();
            lstGruposUsuario.stream().filter((unidadeMedida1) -> (unidadeMedida1.getDsUnidadeMedida().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((unidadeMedida1) -> {
                        obsUnidadeMedida.add(unidadeMedida1);
                    });

            tvwUnidadeMedida.setItems(obsUnidadeMedida);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaUnidadeMedidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}
