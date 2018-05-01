package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaCompostaController;
import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.Peca;
import com.cecilsystems.mistersoftbackend.model.PecaComposta;
import com.cecilsystems.mistersoftbackend.service.ItemPecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.PecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.PecaService;
import com.cecilsystems.mistersoftfrontend.useful.Essential;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioMontagemPecaController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoPecaComposta;
    @FXML
    private JFXButton jbtnConsultarPeca;
    @FXML
    private MaskedJFXTextField jmskQtdPecaComposta;
    @FXML
    private Button jbtnUp;
    @FXML
    Button jbtnDown;
    @FXML
    private Label lblDescricao;
    @FXML
    private JFXButton jbtnFechar;
    @FXML
    private JFXButton jbtnLimpar;
    @FXML
    private Label lblPecaNaoCadastrado;
    @FXML
    private ImageView imgvwPecaNaoCadastrado;
    @FXML
    private Label lblQtdObrigatoria;
    @FXML
    private ImageView imgvwQtdObrigatoria;

    private Stage formStage;
    private static RelatorioMontagemPecaController uniqueInstance;

    public RelatorioMontagemPecaController() {
    }

    public static synchronized RelatorioMontagemPecaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RelatorioMontagemPecaController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblPecaNaoCadastrado.setVisible(false);
        lblPecaNaoCadastrado.setStyle("-fx-text-fill: #c00d0d;");
        imgvwPecaNaoCadastrado.setVisible(false);

        lblQtdObrigatoria.setVisible(false);
        lblQtdObrigatoria.setStyle("-fx-text-fill: #c00d0d;");
        imgvwQtdObrigatoria.setVisible(false);

        jmskQtdPecaComposta.setText("1");

        lblDescricao.setText("");
        jbtnDown.setText("");
        jbtnUp.setText("");

        RequiredFieldValidator codigoValidator = new RequiredFieldValidator();
        RequiredFieldValidator qtdValidator = new RequiredFieldValidator();

        jmskCodigoPecaComposta.getValidators().add(codigoValidator);
        jmskQtdPecaComposta.getValidators().add(qtdValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        qtdValidator.setMessage("Qtd: Campo obrigatório");

        jmskCodigoPecaComposta.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoPecaComposta.validate()) {
                    jmskCodigoPecaFocusLost();
                } else {
                    if (jbtnConsultarPeca.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoPecaComposta.resetValidation();
                        return;
                    }
                    jmskCodigoPecaComposta.requestFocus();
                }
            }
        });

        jmskQtdPecaComposta.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskQtdPecaComposta.getText().equals("0")) {
                    lblQtdObrigatoria.setVisible(true);
                    imgvwQtdObrigatoria.setVisible(true);
                    jmskQtdPecaComposta.resetValidation();
                    return;
                }
                if (!jmskQtdPecaComposta.validate()) {
                    lblQtdObrigatoria.setVisible(false);
                    imgvwQtdObrigatoria.setVisible(false);
                }
            }
        });

        jbtnConsultarPeca.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoPecaComposta.validate()) {
                    jmskCodigoPecaComposta.requestFocus();
                }
            }
        });

        Image errorIcon = new Image(MainApp.class.getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        qtdValidator.setIcon(new ImageView(errorIcon));

    }

    private void jmskCodigoPecaFocusLost() {
        try {
            Peca peca = PecaService.getInstance()
                    .selecionaPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));

            if (peca == null) {
                lblPecaNaoCadastrado.setVisible(true);
                imgvwPecaNaoCadastrado.setVisible(true);
                jmskCodigoPecaComposta.requestFocus();
                return;
            }
            lblDescricao.setText(peca.getDsPeca());

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jmskCodigoPecaCompostaKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoPecaComposta.validate()) {
                jmskQtdPecaComposta.requestFocus();
            } else {
                jmskCodigoPecaComposta.requestFocus();
            }
        }
    }

    @FXML
    private void jmskQtdPecaCompostaKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP) {
            jbtnUpAction();
        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            jbtnDownAction();
        }
    }

    @FXML
    private void jbtnConsultarPecaAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(PathEnum.VIEW_PATH + "ConsultaPecaComposta.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Pecas Compostas");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(376);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaPecaCompostaController controller = loader.getController();
            dialogStage.showAndWait();

            PecaComposta pecaComposta = controller.getPecaComposta();

            if (pecaComposta != null) {
                jmskCodigoPecaComposta.setEditable(false);
                jmskCodigoPecaComposta.setText("" + pecaComposta.getCdPeca());
                lblDescricao.setText(pecaComposta.getDsPeca());
            }
            jmskCodigoPecaComposta.resetValidation();
            lblPecaNaoCadastrado.setVisible(false);
            imgvwPecaNaoCadastrado.setVisible(false);
            lblQtdObrigatoria.setVisible(false);
            imgvwQtdObrigatoria.setVisible(false);

        } catch (IOException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jbtnUpAction() {
        if (jmskQtdPecaComposta.getText().isEmpty()) {
            jmskQtdPecaComposta.setText("1");
        } else {
            int result = Integer.parseInt(jmskQtdPecaComposta.getText()) + 1;
            jmskQtdPecaComposta.setText("" + result);
        }
        jmskQtdPecaComposta.positionCaret(jmskQtdPecaComposta.getText().length());
    }

    @FXML
    private void jbtnDownAction() {
        if (jmskQtdPecaComposta.getText().isEmpty() || jmskQtdPecaComposta.getText().equals("0")
                || jmskQtdPecaComposta.getText().equals("1")) {
            jmskQtdPecaComposta.setText("1");
        } else {
            int result = Integer.parseInt(jmskQtdPecaComposta.getText()) - 1;
            jmskQtdPecaComposta.setText("" + result);
        }
        jmskQtdPecaComposta.positionCaret(jmskQtdPecaComposta.getText().length());
    }

    @FXML
    private void jbtnImprimirAction() {

        if (!jmskCodigoPecaComposta.validate()) {
            jmskCodigoPecaComposta.requestFocus();
            return;
        }
        if (!jmskQtdPecaComposta.validate()) {
            jmskQtdPecaComposta.requestFocus();
            return;
        }
        if (jmskQtdPecaComposta.getText().equals("0")) {
            lblQtdObrigatoria.setVisible(true);
            imgvwQtdObrigatoria.setVisible(true);
            return;
        }
        ImpressaoRelatorioMontagemPeca impressaoRelatorio = new ImpressaoRelatorioMontagemPeca();
        impressaoRelatorio.start();

        synchronized (impressaoRelatorio) {
            try {
                impressaoRelatorio.join();
            } catch (InterruptedException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
        limpaForm();
    }

    private class ImpressaoRelatorioMontagemPeca extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                URL arquivo = MainApp.class.getResource(PathEnum.REPORT_PATH + "MontagemPecaComposta.jasper");
                JRDataSource jRDataSource = new JRBeanCollectionDataSource(getItensPecaComposta());

                HashMap parm = new HashMap();
                parm.put("QtdPecaComposta", jmskQtdPecaComposta.getText());

                Essential.getInstance().geraRelatorio(jRDataSource, parm, arquivo, "RELATÓRIO DE MONTAGEM DE PEÇA");
                notify();
            }
        }
    }

    private List<ItemPecaComposta> getItensPecaComposta() {
        List<ItemPecaComposta> itens = new ArrayList();
        try {
            PecaComposta pecaComposta = PecaCompostaService.getInstance()
                    .selecionaPecaComposta(Integer.parseInt(jmskCodigoPecaComposta.getText()));

            pecaComposta.setVlTotalItens(pecaComposta.getVlTotalItens()
                    .multiply(new BigDecimal(Integer.parseInt(jmskQtdPecaComposta.getText())))
                    .setScale(2, RoundingMode.HALF_UP));

            pecaComposta.setVlTotalMaoDeObra(pecaComposta.getVlTotalMaoDeObra()
                    .multiply(new BigDecimal(Integer.parseInt(jmskQtdPecaComposta.getText())))
                    .setScale(2, RoundingMode.HALF_UP));

            pecaComposta.setVlCusto(pecaComposta.getVlTotalItens().add(pecaComposta.getVlTotalMaoDeObra())
                    .setScale(2, RoundingMode.HALF_UP));

            pecaComposta.setVlVenda(pecaComposta.getVlCusto().multiply(pecaComposta.getMarkup())
                    .setScale(2, RoundingMode.HALF_UP));

            for (ItemPecaComposta itPecaComposta : ItemPecaCompostaService.getInstance()
                    .listaItemPecaComposta(pecaComposta.getCdPeca())) {

                itPecaComposta.setPecaComposta(pecaComposta);
                itPecaComposta.setQtdItens(itPecaComposta.getQtdItens()
                        .multiply(new BigDecimal(Integer.parseInt(jmskQtdPecaComposta.getText()))));

                itens.add(itPecaComposta);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        return itens;
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();
    }

    private void limpaForm() {
        jmskCodigoPecaComposta.setEditable(true);
        jmskCodigoPecaComposta.setText("");
        jmskCodigoPecaComposta.resetValidation();
        lblPecaNaoCadastrado.setVisible(false);
        imgvwPecaNaoCadastrado.setVisible(false);
        lblDescricao.setText("");
        jmskQtdPecaComposta.setText("1");
        jmskQtdPecaComposta.resetValidation();
        lblQtdObrigatoria.setVisible(false);
        imgvwQtdObrigatoria.setVisible(false);

        jmskCodigoPecaComposta.requestFocus();
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.RELATORIO_MONTAGEM_PECA)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

}
