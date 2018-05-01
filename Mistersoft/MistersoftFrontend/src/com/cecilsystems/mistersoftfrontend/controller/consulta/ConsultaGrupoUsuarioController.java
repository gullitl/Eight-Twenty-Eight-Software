package com.cecilsystems.mistersoftfrontend.controller.consulta;

import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import com.cecilsystems.mistersoftbackend.service.GrupoUsuarioService;
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

public class ConsultaGrupoUsuarioController implements Initializable {

    @FXML
    private TableView<GrupoUsuario> tvwGrupoUsuario;
    @FXML
    private TableColumn clmCodigo;
    @FXML
    private TableColumn clmDescricao;
    @FXML
    private JFXTextField jtxDescricaoFiltro;
    @FXML
    private Label lblQtd;
    private final ObservableList<GrupoUsuario> obsGrupoUsuario = FXCollections.observableArrayList();
    private GrupoUsuario grupoUsuario;
    private static ConsultaGrupoUsuarioController uniqueInstance;

    public ConsultaGrupoUsuarioController() {
    }

    public static synchronized ConsultaGrupoUsuarioController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConsultaGrupoUsuarioController();
        }
        return uniqueInstance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("cdGrupoUsuario"));
        clmCodigo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigo.getStyleClass().add("right-header");

        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("dsGrupoUsuario"));
        clmDescricao.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricao.getStyleClass().add("left-header");

        try {
            //Coloca lista de usuários no ObservableList
            obsGrupoUsuario.addAll(GrupoUsuarioService.getInstance().listaGrupoUsuario());
            tvwGrupoUsuario.setItems(obsGrupoUsuario);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaGrupoUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaQtd() {
        if (!obsGrupoUsuario.isEmpty()) {
            lblQtd.setText(obsGrupoUsuario.size() > 1 ? obsGrupoUsuario.size()
                    + " grupos usuário encontrados" : obsGrupoUsuario.size() + " grupo usuário encontrado");
        } else {
            lblQtd.setText("");
        }
    }

    @FXML
    private void tvwGrupoUsuarioSelecionarMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            GrupoUsuario grusr = (GrupoUsuario) tvwGrupoUsuario.getSelectionModel().getSelectedItem();
            if (grusr == null) {
                return;
            }
            this.setGrupoUsuario(grusr);
            Stage stage = (Stage) lblQtd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void jtxDescricaoFiltroKeyReleased() {
        try {
            List<GrupoUsuario> lstGruposUsuario = GrupoUsuarioService.getInstance().listaGrupoUsuario();

            obsGrupoUsuario.clear();
            lstGruposUsuario.stream().filter((grupoUsuario1) -> (grupoUsuario1.getDsGrupoUsuario().toUpperCase()
                    .startsWith(jtxDescricaoFiltro.getText().toUpperCase())))
                    .forEachOrdered((grupoUsuario1) -> {
                        obsGrupoUsuario.add(grupoUsuario1);
                    });

            tvwGrupoUsuario.setItems(obsGrupoUsuario);

            atualizaQtd();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaGrupoUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GrupoUsuario getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

}
