package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaMaoDeObraController;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaCompostaController;
import com.cecilsystems.mistersoftfrontend.controller.consulta.ConsultaPecaController;
import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import com.cecilsystems.mistersoftfrontend.useful.DialogFactory;
import com.cecilsystems.cecilfxcomponent.MaskedJFXTextField;
import com.cecilsystems.mistersoftbackend.model.DetalheItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.MaoDeObra;
import com.cecilsystems.mistersoftbackend.model.MaoDeObraPecaComposta;
import com.cecilsystems.mistersoftbackend.model.Peca;
import com.cecilsystems.mistersoftbackend.model.PecaComposta;
import com.cecilsystems.mistersoftbackend.model.PecaSimples;
import com.cecilsystems.mistersoftbackend.service.DetalheItemPecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.MaoDeObraService;
import com.cecilsystems.mistersoftbackend.service.PecaCompostaService;
import com.cecilsystems.mistersoftbackend.service.PecaService;
import com.cecilsystems.mistersoftbackend.service.PecaSimplesService;
import com.cecilsystems.mistersoftfrontend.useful.Notificacoes;
import com.cecilsystems.mistersoftfrontend.useful.NotifierPigeon;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import com.cecilsystems.mistersoftbackend.util.FormMenu;
import com.cecilsystems.mistersoftbackend.util.HoraDecimalConversor;
import com.cecilsystems.mistersoftbackend.enumerable.MenuEnum;
import com.cecilsystems.mistersoftbackend.enumerable.TipoPecaEnum;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroMontagemPecaController implements Initializable {

    @FXML
    private MaskedJFXTextField jmskCodigoPecaComposta;
    @FXML
    private JFXTextField jtxDescricaoPecaComposta;
    @FXML
    private JFXTextField jtxTotalItens;
    @FXML
    private Label lblTotalItens;
    @FXML
    private JFXTextField jtxTotalMaoDeObra;
    @FXML
    private Label lblTotalMaoDeObra;
    @FXML
    private JFXTextField jtxCustoPecaComposta;
    @FXML
    private MaskedJFXTextField jmskMarkup;
    @FXML
    private JFXTextField jtxPrecoVenda;
    @FXML
    private JFXButton jbtnConsultarPecaComposta;

    @FXML
    private TabPane tbpnIPCMDO; //TabPane Itens Peças compostas e Mão de obra
    @FXML
    private Tab tbItens;
    @FXML
    private Tab tbMaoDeObra;

    @FXML
    private Line lnSuperior;
    @FXML
    private Line lnInferior;
    @FXML
    private Line lnDireita;
    @FXML
    private Line lnEsquerdaItens;
    @FXML
    private Line lnEsquerdaMDO1;
    @FXML
    private Line lnEsquerdaMDO2;

    @FXML
    private ImageView imgvwItens;
    @FXML
    private ImageView imgvwMaoDeObra;

    @FXML
    private MaskedJFXTextField jmskCodigoItem;
    @FXML
    private Button jbtnConsultarItensPecaComposta;
    @FXML
    private Label lblDescricaoItem;
    @FXML
    private MaskedJFXTextField jmskQtdItem;
    @FXML
    private Label lblUnidadeMedida;
    @FXML
    private TableView<ItemPecaComposta> tvwItens;
    @FXML
    private TableColumn clmCodigoItem;
    @FXML
    private TableColumn clmDescricaoItem;
    @FXML
    private TableColumn clmQtdItem;
    @FXML
    private TableColumn clmCustoUnitarioItem;
    @FXML
    private TableColumn clmCustoTotalItem;

    @FXML
    private Label lblQtdItensAdd;

    @FXML
    private MaskedJFXTextField jmskCodigoMaoDeObra;
    @FXML
    private Button jbtnConsultarMaoDeObra;
    @FXML
    private Label lblDescricaoMaoDeObra;
    @FXML
    private MaskedJFXTextField jmskQtdHHMaoDeObra;
    @FXML
    private MaskedJFXTextField jmskQtdMMMaoDeObra;
    @FXML
    private Label lblDoisPontosSeparadoresHHMM;
    @FXML
    private Button jbtnUp;
    @FXML
    private Button jbtnDown;
    @FXML
    private Button jbtnAdicionarMdo;
    @FXML
    private Button jbtnLimparCamposMdo;
    @FXML
    private Button jbtnAdicionarItem;
    @FXML
    private Button jbtnLimparCamposItem;
    @FXML
    private TableView<MaoDeObraPecaComposta> tvwMaoDeObra;
    @FXML
    private TableColumn clmCodigoMaoDeObra;
    @FXML
    private TableColumn clmDescricaoMaoDeObra;
    @FXML
    private TableColumn clmQtdMdo;
    @FXML
    private TableColumn clmCustoUnitarioMdo;
    @FXML
    private TableColumn clmCustoTotalMdo;

    @FXML
    private Label lblQtdMaoDeObraAdd;

    @FXML
    private JFXButton jbtnSalvar;
    @FXML
    private JFXButton jbtnExcluir;
    @FXML
    private JFXButton jbtnFechar;
    @FXML
    private JFXButton jbtnLimpar;

    @FXML
    private Label lblCodigoItemNaoCadastrado;
    @FXML
    private ImageView imgvwCodigoItemNaoCadastrado;
    @FXML
    private Label lblCodigoMdoNaoCadastrado;
    @FXML
    private ImageView imgvwCodigoMdoNaoCadastrado;
    @FXML
    private Label lblQtdHoraMdoNaoInformado;
    @FXML
    private ImageView imgvwQtdHoraMdoNaoInformado;

    @FXML
    private Label lblNotificacao;
    @FXML
    private ImageView imgvwNotificacao;
    private Notificacoes notificacoes;

    private Stage formStage;
    private boolean modoEdicao;
    private boolean modoEdicaoItem;
    private boolean modoEdicaoMdo;

    private final ObservableList<ItemPecaComposta> obsListItemPecaComposta;
    private final List<DetalheItemPecaComposta> listDetalheItemPecaComposta;
    private final ObservableList<MaoDeObraPecaComposta> obsListMaoDeObraPecaComposta;
    private static CadastroMontagemPecaController uniqueInstance;

    public CadastroMontagemPecaController() {
        obsListItemPecaComposta = FXCollections.observableArrayList();
        listDetalheItemPecaComposta = new ArrayList();
        obsListMaoDeObraPecaComposta = FXCollections.observableArrayList();
    }

    public static synchronized CadastroMontagemPecaController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CadastroMontagemPecaController();
        }
        return uniqueInstance;
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setModoEdicao(false);
        setModoEdicaoItem(false);
        setModoEdicaoMdo(false);

        lblDescricaoItem.setText("");
        lblDescricaoMaoDeObra.setText("");

        lblUnidadeMedida.setText("");

        lblDoisPontosSeparadoresHHMM.setVisible(false);
        jbtnDown.setText("");
        jbtnUp.setText("");

        lblNotificacao.setVisible(false);
        imgvwNotificacao.setVisible(false);
        notificacoes = new Notificacoes(imgvwNotificacao, lblNotificacao);

        lblCodigoMdoNaoCadastrado.setVisible(false);
        lblCodigoMdoNaoCadastrado.setStyle("-fx-text-fill: #c00d0d;");
        imgvwCodigoMdoNaoCadastrado.setVisible(false);

        lblCodigoItemNaoCadastrado.setVisible(false);
        lblCodigoItemNaoCadastrado.setStyle("-fx-text-fill: #c00d0d;");
        imgvwCodigoItemNaoCadastrado.setVisible(false);

        clmCodigoItem.setCellValueFactory(new PropertyValueFactory<>("cdPeca"));
        clmCodigoItem.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigoItem.getStyleClass().add("right-header");

        clmDescricaoItem.setCellValueFactory(new PropertyValueFactory<>("dsPeca"));
        clmDescricaoItem.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricaoItem.getStyleClass().add("left-header");

        clmQtdItem.setCellValueFactory(new PropertyValueFactory<>("qtdItensFormattedComUnidadeMedida"));
        clmQtdItem.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmQtdItem.getStyleClass().add("right-header");

        clmCustoUnitarioItem.setCellValueFactory(new PropertyValueFactory<>("vlCustoFormatted"));
        clmCustoUnitarioItem.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCustoUnitarioItem.getStyleClass().add("right-header");

        clmCustoTotalItem.setCellValueFactory(new PropertyValueFactory<>("vlCustoTotalFormatted"));
        clmCustoTotalItem.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCustoTotalItem.getStyleClass().add("right-header");

        clmCodigoMaoDeObra.setCellValueFactory(new PropertyValueFactory<>("cdMaoDeObra"));
        clmCodigoMaoDeObra.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCodigoMaoDeObra.getStyleClass().add("right-header");

        clmDescricaoMaoDeObra.setCellValueFactory(new PropertyValueFactory<>("dsMaoDeObra"));
        clmDescricaoMaoDeObra.setStyle("-fx-alignment: CENTER-LEFT;");
        clmDescricaoMaoDeObra.getStyleClass().add("left-header");

        clmQtdMdo.setCellValueFactory(new PropertyValueFactory<>("qtHorasEmHHMM"));
        clmQtdMdo.setStyle("-fx-alignment: CENTER; -fx-padding: 0 10 0 0;");
        clmQtdMdo.getStyleClass().add("right-header");

        clmCustoUnitarioMdo.setCellValueFactory(new PropertyValueFactory<>("vlCustoFormatted"));
        clmCustoUnitarioMdo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCustoUnitarioMdo.getStyleClass().add("right-header");

        clmCustoTotalMdo.setCellValueFactory(new PropertyValueFactory<>("vlCustoTotalFormatted"));
        clmCustoTotalMdo.setStyle("-fx-alignment: CENTER-RIGHT; -fx-padding: 0 10 0 0;");
        clmCustoTotalMdo.getStyleClass().add("right-header");

        lblQtdItensAdd.setText("");
        lblQtdMaoDeObraAdd.setText("");
        lblTotalItens.setText("");
        lblTotalMaoDeObra.setText("");

        jbtnExcluir.setDisable(true);

        RequiredFieldValidator codigoValidator = new RequiredFieldValidator();
        RequiredFieldValidator descricaoValidator = new RequiredFieldValidator();
        RequiredFieldValidator totalItensValidator = new RequiredFieldValidator();
        RequiredFieldValidator totalMaoDeObraValidator = new RequiredFieldValidator();
        RequiredFieldValidator custoValidator = new RequiredFieldValidator();
        RequiredFieldValidator markupValidator = new RequiredFieldValidator();
        RequiredFieldValidator precoVendaValidator = new RequiredFieldValidator();
        RequiredFieldValidator codigoItemValidator = new RequiredFieldValidator();
        RequiredFieldValidator qtdItemValidator = new RequiredFieldValidator();
        RequiredFieldValidator codigoMaoDeObraVendaValidator = new RequiredFieldValidator();

        jmskCodigoPecaComposta.getValidators().add(codigoValidator);
        jtxDescricaoPecaComposta.getValidators().add(descricaoValidator);
        jtxTotalItens.getValidators().add(totalItensValidator);
        jtxTotalMaoDeObra.getValidators().add(totalMaoDeObraValidator);
        jtxCustoPecaComposta.getValidators().add(custoValidator);
        jmskMarkup.getValidators().add(markupValidator);
        jtxPrecoVenda.getValidators().add(precoVendaValidator);
        jmskCodigoItem.getValidators().add(codigoItemValidator);
        jmskQtdItem.getValidators().add(qtdItemValidator);
        jmskCodigoMaoDeObra.getValidators().add(codigoMaoDeObraVendaValidator);

        codigoValidator.setMessage("Código: Campo obrigatório");
        descricaoValidator.setMessage("Descrição: Campo obrigatório");
        totalItensValidator.setMessage("Itens: Campo abrigatório");
        totalMaoDeObraValidator.setMessage("Mão de Obra: Campo abrigatório");
        custoValidator.setMessage("Custo: Campo obrigatório");
        markupValidator.setMessage("Markup: Campo obrigatório");
        precoVendaValidator.setMessage("Preço venda: Campo obrigatório");
        codigoItemValidator.setMessage("Campo obrigatório");
        qtdItemValidator.setMessage("Campo obrigatório");
        codigoMaoDeObraVendaValidator.setMessage("Campo obrigatório");

        jmskCodigoPecaComposta.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (jmskCodigoPecaComposta.validate()) {
                    jmskCodigoPecaCompostaFocusLost();
                } else {
                    if (jbtnConsultarPecaComposta.isFocused() || jbtnLimpar.isFocused() || jbtnFechar.isFocused()) {
                        jmskCodigoPecaComposta.resetValidation();
                        return;
                    }
                    jmskCodigoPecaComposta.requestFocus();
                }
            } else {
                tbpnIPCMDOUnFocused();
            }
        });

        jbtnConsultarPecaComposta.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0,
                Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (oldPropertyValue) {
                if (!jmskCodigoPecaComposta.validate()) {
                    jmskCodigoPecaComposta.requestFocus();
                }
            }
        });

        jtxDescricaoPecaComposta.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxDescricaoPecaComposta.validate();
                }
            }
        });

        jtxTotalItens.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDO.getSelectionModel().select(tbItens);
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jtxTotalMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDO.getSelectionModel().select(tbMaoDeObra);
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jtxCustoPecaComposta.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxCustoPecaComposta.validate();
                }
            }
        });

        jmskMarkup.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    if (jmskMarkup.validate()) {
                        jmskMarkup.setMask("*!");
                        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                                .formattedValueSemSimbolo(jmskMarkup.getText()));
                    }
                } else {
                    jmskMarkup.setMask("N!,NN");
                    if (!jmskMarkup.getText().isEmpty()) {
                        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                                .standardValue(jmskMarkup.getText()));
                    }
                }
            }
        });

        jtxPrecoVenda.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    jtxPrecoVenda.validate();
                }
            }
        });

        tbpnIPCMDO.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jmskCodigoItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                    jmskCodigoItem.resetValidation();

                    if (!jmskCodigoItem.getText().isEmpty()) {
                        jmskCodigoItemFocusLost();
                    }
                }
            }
        });

        jmskQtdItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                    jmskQtdItem.setMask("N!,NNNN");
                    if (!jmskQtdItem.getText().isEmpty()) {
                        jmskQtdItem.setText(CecilDecimalFormatter.getInstance()
                                .standardValue(jmskQtdItem.getText()));
                    }
                } else {
                    tbpnIPCMDOUnFocused();
                    if (jmskQtdItem.validate()) {
                        jmskQtdItem.setMask("*!");
                        jmskQtdItem.setText(CecilDecimalFormatter.getInstance()
                                .formattedValueSemSimbolo4Zero(jmskQtdItem.getText()));
                    }
                }
            }
        });

        tvwItens.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jmskCodigoMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                    jmskCodigoMaoDeObra.resetValidation();

                    if (!jmskCodigoMaoDeObra.getText().isEmpty()) {
                        jmskCodigoMaoDeObraFocusLost();
                    }
                }
            }
        });

        jmskQtdHHMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                    lblDoisPontosSeparadoresHHMM.setVisible(true);
                } else {
                    tbpnIPCMDOUnFocused();
                    if (jmskQtdHHMaoDeObra.getText().isEmpty()) {
                        jmskQtdHHMaoDeObra.setText("00");
                    } else if (jmskQtdHHMaoDeObra.getText().length() == 1) {
                        jmskQtdHHMaoDeObra.setText("0" + jmskQtdHHMaoDeObra.getText());
                    }
                }
            }
        });

        jmskQtdMMMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                    if (jmskQtdHHMaoDeObra.getText().isEmpty()) {
                        jmskQtdHHMaoDeObra.setText("00");
                    }
                    lblDoisPontosSeparadoresHHMM.setVisible(true);
                } else {
                    tbpnIPCMDOUnFocused();
                    if (jmskQtdMMMaoDeObra.getText().isEmpty()) {
                        jmskQtdMMMaoDeObra.setText("00");
                    } else if (jmskQtdMMMaoDeObra.getText().length() == 1) {
                        jmskQtdMMMaoDeObra.setText("0" + jmskQtdMMMaoDeObra.getText());
                    }
                }
            }
        });

        jbtnAdicionarMdo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jbtnLimparCamposMdo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jbtnAdicionarItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        jbtnLimparCamposItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });

        tvwMaoDeObra.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    tbpnIPCMDOFocused();
                } else {
                    tbpnIPCMDOUnFocused();
                }
            }
        });
        Image errorIcon = new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "error.png").toString());
        codigoValidator.setIcon(new ImageView(errorIcon));
        descricaoValidator.setIcon(new ImageView(errorIcon));

        totalItensValidator.setIcon(new ImageView(errorIcon));
        totalMaoDeObraValidator.setIcon(new ImageView(errorIcon));
        custoValidator.setIcon(new ImageView(errorIcon));
        markupValidator.setIcon(new ImageView(errorIcon));
        precoVendaValidator.setIcon(new ImageView(errorIcon));
        codigoItemValidator.setIcon(new ImageView(errorIcon));
        qtdItemValidator.setIcon(new ImageView(errorIcon));
        codigoMaoDeObraVendaValidator.setIcon(new ImageView(errorIcon));
    }

    private void jmskCodigoPecaCompostaFocusLost() {
        if (!jmskCodigoPecaComposta.isEditable()) {
            return;
        }
        try {
            Peca peca = PecaService.getInstance().selecionaPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));
            if (peca == null) {
                jmskCodigoPecaComposta.setEditable(false);
                return;
            }

            if (peca.getTipoPeca() == TipoPecaEnum.SIMPLES.getTipo()) {
                notificacoes.notificaAdvertencia(peca.getCdPeca() + " é uma peça simples!");
                jmskCodigoPecaComposta.requestFocus();
                return;
            }

            PecaComposta pecaComposta = PecaCompostaService.getInstance()
                    .selecionaPecaComposta(peca.getCdPeca());

            jmskCodigoPecaComposta.setEditable(false);

            preenchePecaComposta(pecaComposta);

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoPecaCompostaKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoPecaComposta.validate()) {
                jtxDescricaoPecaComposta.requestFocus();
            } else {
                jmskCodigoPecaComposta.requestFocus();
            }
        }
    }

    public void preenchePecaComposta(PecaComposta pecaComposta) {
        jbtnExcluir.setDisable(false);
        setModoEdicao(true);

        jmskCodigoPecaComposta.setText("" + pecaComposta.getCdPeca());
        jtxDescricaoPecaComposta.setText(pecaComposta.getDsPeca());

        obsListItemPecaComposta.clear();
        obsListItemPecaComposta.addAll(pecaComposta.getLstItensPecaComposta());
        tvwItens.setItems(obsListItemPecaComposta);

        lblTotalItens.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlTotalItens()));
        jtxTotalItens.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlTotalItens()));

        lblQtdItensAdd.setText(obsListItemPecaComposta.size() > 1
                ? "Qtd.: " + obsListItemPecaComposta.size()
                : "Qtd.: " + obsListItemPecaComposta.size());

        listDetalheItemPecaComposta.clear();
        listDetalheItemPecaComposta.addAll(pecaComposta.getLstDetalheItemPecaComposta());

        obsListMaoDeObraPecaComposta.clear();
        obsListMaoDeObraPecaComposta.addAll(pecaComposta.getLstMaoDeObra());
        tvwMaoDeObra.setItems(obsListMaoDeObraPecaComposta);

        lblTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlTotalMaoDeObra()));
        jtxTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlTotalMaoDeObra()));

        lblQtdMaoDeObraAdd.setText(obsListMaoDeObraPecaComposta.size() > 1
                ? "Qtd.: " + obsListMaoDeObraPecaComposta.size()
                : "Qtd.: " + obsListMaoDeObraPecaComposta.size());

        jtxCustoPecaComposta.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlCusto()));

        jmskMarkup.setText(CecilDecimalFormatter.getInstance()
                .formattedValueSemSimbolo(pecaComposta.getMarkup()));

        jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance()
                .formattedValue(pecaComposta.getVlVenda()));

        jbtnSalvar.setText("ATUALIZAR");
    }

    @FXML
    public void jmskCodigoPecaCompostaKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1) {
            try {
                Integer csb = PecaService.getInstance().selecionaCodigoPecaSubsequente();
                jmskCodigoPecaComposta.setText(csb.toString());
                jmskCodigoPecaComposta.positionCaret(csb.toString().length());
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnConsultarPecaCompostaAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaPecaComposta.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Peca Compostas");
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
                pecaComposta = PecaCompostaService.getInstance()
                        .selecionaPecaComposta(pecaComposta.getCdPeca());
                jmskCodigoPecaComposta.setEditable(false);

                preenchePecaComposta(pecaComposta);

            }

            jmskCodigoPecaComposta.resetValidation();
            jtxDescricaoPecaComposta.resetValidation();
            jtxTotalItens.resetValidation();
            jtxTotalMaoDeObra.resetValidation();
            jtxCustoPecaComposta.resetValidation();
            jmskMarkup.resetValidation();
            jtxPrecoVenda.resetValidation();
            jmskCodigoItem.resetValidation();
            jmskQtdItem.resetValidation();
            jmskCodigoMaoDeObra.resetValidation();

            lblQtdHoraMdoNaoInformado.setVisible(false);
            imgvwQtdHoraMdoNaoInformado.setVisible(false);

        } catch (IOException | SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    private void jmskCodigoItemFocusLost() {
        Peca peca;
        try {
            peca = PecaService.getInstance().selecionaPeca(Integer.parseInt(jmskCodigoItem.getText()));
            if (peca == null) {
                lblUnidadeMedida.setText("");
                lblDescricaoItem.setText("");
                lblCodigoItemNaoCadastrado.setVisible(true);
                imgvwCodigoItemNaoCadastrado.setVisible(true);

                jmskCodigoItem.requestFocus();
                return;
            }
            lblCodigoItemNaoCadastrado.setVisible(false);
            imgvwCodigoItemNaoCadastrado.setVisible(false);
            lblDescricaoItem.setText(peca.getDsPeca());
            lblUnidadeMedida.setText(peca.getUnidadeMedida().getDaUnidadeMedida());
        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }

    }

    @FXML
    public void jmskCodigoItemKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoItem.validate()) {
                jmskQtdItem.requestFocus();
            } else {
                jmskCodigoItem.requestFocus();
            }
        }
    }

    @FXML
    private void jbtnConsultarItensPecaCompostaAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaPeca.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Pecas");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(376);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaPecaController controller = loader.getController();
            dialogStage.showAndWait();

            Peca peca = controller.getPeca();

            if (peca != null) {
                jmskCodigoItem.setText("" + peca.getCdPeca());
                lblDescricaoItem.setText(peca.getDsPeca());
                lblUnidadeMedida.setText(peca.getUnidadeMedida().getDaUnidadeMedida());
                jmskQtdItem.requestFocus();
            }

            jmskCodigoItem.resetValidation();
            jmskQtdItem.resetValidation();

        } catch (IOException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    private void jbtnAdicionarItemAction() {
        if (!jmskCodigoItem.validate()) {
            jmskCodigoItem.requestFocus();
            return;
        }
        if (!jmskQtdItem.validate()) {
            jmskQtdItem.requestFocus();
            return;
        }
        try {
            if (isModoEdicaoItem()) {
                for (ItemPecaComposta ipc : obsListItemPecaComposta) {
                    if (ipc.getCdPeca() == Integer.parseInt(jmskCodigoItem.getText())) {
                        ipc.setQtdItens(CecilDecimalFormatter.getInstance().bigStandardValue4Zero(jmskQtdItem.getText()));

                        tvwItens.setItems(obsListItemPecaComposta);
                        tvwItens.refresh();

                        for (DetalheItemPecaComposta dipc : listDetalheItemPecaComposta) {
                            if (dipc.getCdPecaMediadora() == ipc.getCdPeca()) {
                                if (ipc.getTipoPeca() == TipoPecaEnum.SIMPLES.getTipo()) {
                                    dipc.setQtdNaPecaCompostaMae(ipc.getQtdItens());
                                } else if (ipc.getTipoPeca() == TipoPecaEnum.COMPOSTA.getTipo()) {
                                    if (dipc.getCdPecaCompostaMae() != dipc.getCdPecaMediadora()) {
                                        dipc.setQtdNaPecaMediadora(ipc.getQtdItens());
                                    }
                                }

                            }
                        }

                        setModoEdicaoItem(false);
                        jmskCodigoItem.setEditable(true);
                        jbtnConsultarItensPecaComposta.setDisable(false);
                        jbtnAdicionarItem.setText("Adicionar");
                        break;
                    }
                }
            } else {

                for (ItemPecaComposta ipc : obsListItemPecaComposta) {
                    if (ipc.getCdPeca() == Integer.parseInt(jmskCodigoItem.getText())) {
                        DialogFactory.getInstance().erro("Esta peça já foi adicionada na tabela");
                        jmskCodigoItem.requestFocus();
                        return;
                    }
                }

                Peca peca = PecaService.getInstance()
                        .selecionaPeca(Integer.parseInt(jmskCodigoItem.getText()));
                if (peca == null) {
                    lblCodigoItemNaoCadastrado.setVisible(true);
                    imgvwCodigoItemNaoCadastrado.setVisible(true);
                    jmskCodigoItem.requestFocus();
                    return;
                }

                if (peca.getTipoPeca() == TipoPecaEnum.SIMPLES.getTipo()) {
                    PecaSimples pecaSimples = PecaSimplesService.getInstance()
                            .selecionaPecaSimples(peca.getCdPeca());
                    ItemPecaComposta itemPecaComposta = new ItemPecaComposta();

                    itemPecaComposta.setCdPeca(pecaSimples.getCdPeca());
                    itemPecaComposta.setDsPeca(pecaSimples.getDsPeca());
                    itemPecaComposta.setTipoPeca(pecaSimples.getTipoPeca());
                    itemPecaComposta.setUnidadeMedida(pecaSimples.getUnidadeMedida());
                    itemPecaComposta.setVlCusto(pecaSimples.getVlCusto());

                    PecaComposta pecaCompostaS = new PecaComposta();
                    pecaCompostaS.setCdPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                    itemPecaComposta.setPecaComposta(pecaCompostaS);

                    itemPecaComposta.setQtdItens(CecilDecimalFormatter.getInstance()
                            .bigStandardValue4Zero(jmskQtdItem.getText()));

                    obsListItemPecaComposta.add(itemPecaComposta);
                    tvwItens.setItems(obsListItemPecaComposta);

                    DetalheItemPecaComposta detalheItemPecaComposta = new DetalheItemPecaComposta();
                    detalheItemPecaComposta.setCdPecaComposta(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                    detalheItemPecaComposta.setCdPecaSimples(itemPecaComposta.getCdPeca());
                    detalheItemPecaComposta.setCdPecaCompostaMae(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                    detalheItemPecaComposta.setCdPecaMediadora(itemPecaComposta.getCdPeca());
                    detalheItemPecaComposta.setQtdNaPecaCompostaMae(itemPecaComposta.getQtdItens());
                    detalheItemPecaComposta.setQtdNaPecaMediadora(new BigDecimal("1.0000"));

                    listDetalheItemPecaComposta.add(detalheItemPecaComposta);

                } else if (peca.getTipoPeca() == TipoPecaEnum.COMPOSTA.getTipo()) {
                    if (peca.getCdPeca() == Integer.parseInt(jmskCodigoPecaComposta.getText())) {
                        DialogFactory.getInstance()
                                .erro("Item de Peça Composta não pode ser a mesma Peça Composta");
                        jmskCodigoItem.requestFocus();
                        return;
                    }
                    List<DetalheItemPecaComposta> DetalhesItens = DetalheItemPecaCompostaService.getInstance()
                            .listaDetalheItemPecaComposta(Integer.parseInt(jmskCodigoItem.getText()));

                    for (DetalheItemPecaComposta dipc : DetalhesItens) {
                        if (dipc.getCdPecaMediadora() == Integer.parseInt(jmskCodigoPecaComposta.getText())) {
                            DialogFactory.getInstance()
                                    .erro("Item Peça Composta não pode ser composto pela mesma Peça Composta");
                            return;
                        }
                    }

                    PecaComposta pecaComposta = PecaCompostaService.getInstance()
                            .selecionaPecaComposta(peca.getCdPeca());

                    ItemPecaComposta itemPecaComposta = new ItemPecaComposta();

                    itemPecaComposta.setCdPeca(pecaComposta.getCdPeca());
                    itemPecaComposta.setDsPeca(pecaComposta.getDsPeca());
                    itemPecaComposta.setTipoPeca(pecaComposta.getTipoPeca());
                    itemPecaComposta.setUnidadeMedida(pecaComposta.getUnidadeMedida());
                    itemPecaComposta.setVlCusto(pecaComposta.getVlCusto());

                    PecaComposta pecaCompostaC = new PecaComposta();
                    pecaCompostaC.setCdPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                    itemPecaComposta.setPecaComposta(pecaCompostaC);

                    itemPecaComposta.setQtdItens(CecilDecimalFormatter.getInstance()
                            .bigStandardValue4Zero(jmskQtdItem.getText()));

                    obsListItemPecaComposta.add(itemPecaComposta);
                    tvwItens.setItems(obsListItemPecaComposta);

                    for (DetalheItemPecaComposta dipc : DetalhesItens) {
                        DetalheItemPecaComposta detalheItemPecaComposta = new DetalheItemPecaComposta();

                        detalheItemPecaComposta.setCdPecaComposta(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                        detalheItemPecaComposta.setCdPecaSimples(dipc.getCdPecaSimples());
                        detalheItemPecaComposta.setCdPecaCompostaMae(dipc.getCdPecaCompostaMae());
                        detalheItemPecaComposta.setCdPecaMediadora(dipc.getCdPecaComposta());
                        detalheItemPecaComposta.setQtdNaPecaCompostaMae(dipc.getQtdNaPecaCompostaMae());
                        if (dipc.getCdPecaCompostaMae() == dipc.getCdPecaMediadora()) {
                            detalheItemPecaComposta.setQtdNaPecaMediadora(new BigDecimal("1.0000"));
                        } else {
                            detalheItemPecaComposta.setQtdNaPecaMediadora(itemPecaComposta.getQtdItens());
                        }
                        listDetalheItemPecaComposta.add(detalheItemPecaComposta);
                    }

                }
            }

            BigDecimal tg = new BigDecimal("0.00");

            for (ItemPecaComposta ipc : obsListItemPecaComposta) {
                tg = tg.add(ipc.getVlCustoTotal());
            }

            lblTotalItens.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

            jtxTotalItens.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

            if (calculaValorCusto().doubleValue() == 0.0) {
                jtxCustoPecaComposta.setText("");
            } else {
                jtxCustoPecaComposta.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaValorCusto()));
            }

            if (calculaPrecoVenda().doubleValue() == 0.0) {
                jtxPrecoVenda.setText("");
            } else {
                jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaPrecoVenda()));
            }

            lblQtdItensAdd.setText(obsListItemPecaComposta.size() > 1
                    ? "Qtd.: " + obsListItemPecaComposta.size()
                    : "Qtd.: " + obsListItemPecaComposta.size());

            jmskCodigoItem.setText("");
            lblDescricaoItem.setText("");
            lblUnidadeMedida.setText("");
            jmskQtdItem.setText("");
            jmskCodigoItem.requestFocus();

            jtxTotalItens.resetValidation();

        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    private void jmskCodigoMaoDeObraFocusLost() {
        try {
            MaoDeObra maoDeObra = MaoDeObraService.getInstance()
                    .selecionaMaoDeObra(Integer.parseInt(jmskCodigoMaoDeObra.getText()));
            if (maoDeObra == null) {
                lblDescricaoMaoDeObra.setText("");

                lblCodigoMdoNaoCadastrado.setVisible(true);
                imgvwCodigoMdoNaoCadastrado.setVisible(true);

                jmskCodigoMaoDeObra.requestFocus();
                return;
            }
            lblCodigoMdoNaoCadastrado.setVisible(false);
            imgvwCodigoMdoNaoCadastrado.setVisible(false);
            lblDescricaoMaoDeObra.setText(maoDeObra.getDsMaoDeObra());
        } catch (ClassNotFoundException | SQLException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void jmskCodigoMaoDeObraKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (jmskCodigoMaoDeObra.validate()) {
                jmskQtdHHMaoDeObra.requestFocus();
            } else {
                jmskCodigoMaoDeObra.requestFocus();
            }
        }
    }

    @FXML
    private void jmskQtdMMMaoDeObraKeyReleased() {
        try {
            if (Integer.parseInt(jmskQtdMMMaoDeObra.getText()) > 58) {
                jmskQtdMMMaoDeObra.setText("58");
                jmskQtdMMMaoDeObra.positionCaret(jmskQtdMMMaoDeObra.getText().length());
            }
        } catch (NumberFormatException nfe) {
        }

    }

    @FXML
    private void jbtnConsultarMaoDeObraAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource(PathEnum.VIEW_PATH + "ConsultaMaoDeObra.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Maos de Obra");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(formStage);
            dialogStage.setX(414);
            dialogStage.setY(85);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConsultaMaoDeObraController controller = loader.getController();
            dialogStage.showAndWait();

            MaoDeObra maoDeObra = controller.getMaoDeObra();

            if (maoDeObra != null) {
                jmskCodigoMaoDeObra.setText("" + maoDeObra.getCdMaoDeObra());
                lblDescricaoMaoDeObra.setText(maoDeObra.getDsMaoDeObra());
                jmskQtdHHMaoDeObra.requestFocus();
            }

            jmskCodigoMaoDeObra.resetValidation();
            lblQtdHoraMdoNaoInformado.setVisible(false);
            imgvwQtdHoraMdoNaoInformado.setVisible(false);

        } catch (IOException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
    }

    @FXML
    public void tvwMaoDeObraMouseCliked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (obsListMaoDeObraPecaComposta.isEmpty()) {
                return;
            }
            if (mouseEvent.getClickCount() == 2) {
                editaMaoDeObraPecaComposta();
            }
        }
    }

    @FXML
    public void tvwItensMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (obsListItemPecaComposta.isEmpty()) {
                return;
            }
            if (mouseEvent.getClickCount() == 2) {
                editaItemPecaComposta();
            }
        }
    }

    private void editaMaoDeObraPecaComposta() {

        MaoDeObraPecaComposta maoDeObraPecaComposta = tvwMaoDeObra.getSelectionModel()
                .getSelectedItem();

        if (maoDeObraPecaComposta == null) {
            return;
        }
        jmskCodigoMaoDeObra.setText("" + maoDeObraPecaComposta.getCdMaoDeObra());
        lblDescricaoMaoDeObra.setText(maoDeObraPecaComposta.getDsMaoDeObra());
        jmskQtdHHMaoDeObra.setText(maoDeObraPecaComposta.getQtHorasEmHHMM().substring(0, 2));
        jmskQtdMMMaoDeObra.setText(maoDeObraPecaComposta.getQtHorasEmHHMM().substring(3, 5));

        setModoEdicaoMdo(true);
        jmskCodigoMaoDeObra.setEditable(false);
        jbtnConsultarMaoDeObra.setDisable(true);
        jmskQtdHHMaoDeObra.requestFocus();
        jbtnAdicionarMdo.setText("Atualizar");
    }

    private void editaItemPecaComposta() {

        ItemPecaComposta itemPecaComposta = tvwItens.getSelectionModel()
                .getSelectedItem();

        if (itemPecaComposta == null) {
            return;
        }
        jmskCodigoItem.setText("" + itemPecaComposta.getCdPeca());
        lblDescricaoItem.setText(itemPecaComposta.getDsPeca());
        jmskQtdItem.setText(itemPecaComposta.getQtdItensFormatted());
        lblUnidadeMedida.setText(itemPecaComposta.getUnidadeMedida().getDaUnidadeMedida());

        setModoEdicaoItem(true);
        jmskCodigoItem.setEditable(false);
        jbtnConsultarItensPecaComposta.setDisable(true);
        jmskQtdItem.requestFocus();
        jbtnAdicionarItem.setText("Atualizar");
    }

    @FXML
    private void jbtnUpAction() {
        if (jmskQtdHHMaoDeObra.isFocused()) {
            if (jmskQtdHHMaoDeObra.getText().isEmpty()) {
                jmskQtdHHMaoDeObra.setText("00");
            }
            int result = Integer.parseInt(jmskQtdHHMaoDeObra.getText()) + 1;
            if (result < 10) {
                jmskQtdHHMaoDeObra.setText("0" + result);
            } else {
                jmskQtdHHMaoDeObra.setText("" + result);
            }
            jmskQtdHHMaoDeObra.positionCaret(jmskQtdHHMaoDeObra.getText().length());
        } else if (jmskQtdMMMaoDeObra.isFocused()) {
            if (jmskQtdMMMaoDeObra.getText().isEmpty()) {
                jmskQtdMMMaoDeObra.setText("00");
            }
            int result = Integer.parseInt(jmskQtdMMMaoDeObra.getText()) + 10;
            if (result > 58) {
                jmskQtdMMMaoDeObra.setText("58");
            } else {
                jmskQtdMMMaoDeObra.setText(result + "");
            }
            jmskQtdMMMaoDeObra.positionCaret(jmskQtdMMMaoDeObra.getText().length());

        }
    }

    @FXML
    private void jbtnDownAction() {
        if (jmskQtdHHMaoDeObra.isFocused()) {
            if (jmskQtdHHMaoDeObra.getText().isEmpty() || jmskQtdHHMaoDeObra.getText().equals("00")) {
                jmskQtdHHMaoDeObra.setText("00");
            } else {
                int result = Integer.parseInt(jmskQtdHHMaoDeObra.getText()) - 1;
                if (result < 10) {
                    jmskQtdHHMaoDeObra.setText("0" + result);
                } else {
                    jmskQtdHHMaoDeObra.setText("" + result);
                }
            }
            jmskQtdHHMaoDeObra.positionCaret(jmskQtdHHMaoDeObra.getText().length());
        } else if (jmskQtdMMMaoDeObra.isFocused()) {
            if (jmskQtdMMMaoDeObra.getText().isEmpty() || jmskQtdMMMaoDeObra.getText().equals("00")) {
                jmskQtdMMMaoDeObra.setText("00");
            } else {
                int result = Integer.parseInt(jmskQtdMMMaoDeObra.getText()) - 10;

                if (result < 1) {
                    jmskQtdMMMaoDeObra.setText("00");
                } else {
                    jmskQtdMMMaoDeObra.setText(result + "");
                }
            }
            jmskQtdMMMaoDeObra.positionCaret(jmskQtdMMMaoDeObra.getText().length());
        }
    }

    private BigDecimal calculaValorCusto() {
        BigDecimal valorMaoDeObra = new BigDecimal("0.00");
        BigDecimal valorItens = new BigDecimal("0.00");

        for (MaoDeObraPecaComposta mdo : obsListMaoDeObraPecaComposta) {
            valorMaoDeObra = valorMaoDeObra.add(mdo.getVlCustoTotal());
        }

        for (ItemPecaComposta ipc : obsListItemPecaComposta) {
            valorItens = valorItens.add(ipc.getVlCustoTotal());
        }

        return valorItens.add(valorMaoDeObra);
    }

    private BigDecimal calculaPrecoVenda() {
        return calculaValorCusto().multiply(CecilDecimalFormatter.getInstance()
                .bigStandardValue(jmskMarkup.getText()));
    }

    @FXML
    private void jbtnAdicionarMdoAction() {
        if (!jmskCodigoMaoDeObra.validate()) {
            jmskCodigoMaoDeObra.requestFocus();
            return;
        }
        if ((jmskQtdHHMaoDeObra.getText().equals("00") & jmskQtdMMMaoDeObra.getText().equals("00"))
                || ((jmskQtdHHMaoDeObra.getText().equals("") & jmskQtdMMMaoDeObra.getText().equals("")))
                || ((jmskQtdHHMaoDeObra.getText().equals("00") & jmskQtdMMMaoDeObra.getText().equals("")))
                || ((jmskQtdHHMaoDeObra.getText().equals("") & jmskQtdMMMaoDeObra.getText().equals("00")))) {
            lblQtdHoraMdoNaoInformado.setVisible(true);
            imgvwQtdHoraMdoNaoInformado.setVisible(true);
            jmskQtdHHMaoDeObra.requestFocus();
            return;
        }
        if (jmskQtdHHMaoDeObra.getText().equals("") & !jmskQtdMMMaoDeObra.getText().equals("")) {
            jmskQtdHHMaoDeObra.setText("00");
        } else if (!jmskQtdHHMaoDeObra.getText().equals("") & jmskQtdMMMaoDeObra.getText().equals("")) {
            jmskQtdMMMaoDeObra.setText("00");
        }
        if (isModoEdicaoMdo()) {
            for (MaoDeObraPecaComposta mdo : obsListMaoDeObraPecaComposta) {
                if (mdo.getCdMaoDeObra() == Integer.parseInt(jmskCodigoMaoDeObra.getText())) {
                    String qtdHoras = jmskQtdHHMaoDeObra.getText() + ":" + jmskQtdMMMaoDeObra.getText();
                    mdo.setQtHoras(HoraDecimalConversor.getInstance().converteHHMMEmDecimal(qtdHoras));

                    tvwMaoDeObra.setItems(obsListMaoDeObraPecaComposta);
                    tvwMaoDeObra.refresh();

                    setModoEdicaoMdo(false);
                    jmskCodigoMaoDeObra.setEditable(true);
                    jbtnConsultarMaoDeObra.setDisable(false);
                    jbtnAdicionarMdo.setText("Adicionar");
                    break;
                }
            }

        } else {

            for (MaoDeObraPecaComposta mdo : obsListMaoDeObraPecaComposta) {
                if (mdo.getCdMaoDeObra() == Integer.parseInt(jmskCodigoMaoDeObra.getText())) {
                    DialogFactory.getInstance().erro("Esta mão de obra já foi adicionada na tabela");
                    jmskCodigoMaoDeObra.requestFocus();
                    return;
                }
            }
            try {
                MaoDeObra maoDeObra = MaoDeObraService.getInstance()
                        .selecionaMaoDeObra(Integer.parseInt(jmskCodigoMaoDeObra.getText()));

                if (maoDeObra == null) {
                    lblCodigoMdoNaoCadastrado.setVisible(true);
                    imgvwCodigoMdoNaoCadastrado.setVisible(true);
                    jmskCodigoMaoDeObra.requestFocus();
                    return;
                }

                MaoDeObraPecaComposta maoDeObraPecaComposta = new MaoDeObraPecaComposta();
                maoDeObraPecaComposta.setCdMaoDeObra(maoDeObra.getCdMaoDeObra());
                maoDeObraPecaComposta.setDsMaoDeObra(maoDeObra.getDsMaoDeObra());
                maoDeObraPecaComposta.setDaMaoDeObra(maoDeObra.getDaMaoDeObra());
                maoDeObraPecaComposta.setVlCusto(maoDeObra.getVlCusto());
                maoDeObraPecaComposta.setObservacao(maoDeObra.getObservacao());

                PecaComposta pecaComposta = new PecaComposta();
                pecaComposta.setCdPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                maoDeObraPecaComposta.setPecaComposta(pecaComposta);
                String qtdHoras = jmskQtdHHMaoDeObra.getText() + ":" + jmskQtdMMMaoDeObra.getText();
                maoDeObraPecaComposta.setQtHoras(HoraDecimalConversor.getInstance().converteHHMMEmDecimal(qtdHoras));

                obsListMaoDeObraPecaComposta.add(maoDeObraPecaComposta);
                tvwMaoDeObra.setItems(obsListMaoDeObraPecaComposta);
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
        BigDecimal tg = new BigDecimal("0.00");
        for (MaoDeObraPecaComposta mdo : obsListMaoDeObraPecaComposta) {
            tg = tg.add(mdo.getVlCustoTotal());
        }
        lblTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));
        jtxTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));
        if (calculaValorCusto().doubleValue() == 0.0) {
            jtxCustoPecaComposta.setText("");
        } else {
            jtxCustoPecaComposta.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaValorCusto()));
        }
        if (calculaPrecoVenda().doubleValue() == 0.0) {
            jtxPrecoVenda.setText("");
        } else {
            jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaPrecoVenda()));
        }
        lblQtdMaoDeObraAdd.setText(obsListMaoDeObraPecaComposta.size() > 1
                ? "Qtd.: " + obsListMaoDeObraPecaComposta.size()
                : "Qtd.: " + obsListMaoDeObraPecaComposta.size());
        jmskCodigoMaoDeObra.setText("");
        lblDescricaoMaoDeObra.setText("");
        jmskQtdHHMaoDeObra.setText("");
        jmskQtdMMMaoDeObra.setText("");
        lblDoisPontosSeparadoresHHMM.setVisible(false);
        jmskCodigoMaoDeObra.requestFocus();

    }

    @FXML
    private void tvwMaoDeObraKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            MaoDeObraPecaComposta maoDeObraPecaComposta = tvwMaoDeObra.getSelectionModel().getSelectedItem();

            for (MaoDeObraPecaComposta obra : obsListMaoDeObraPecaComposta) {
                if (obra.equals(maoDeObraPecaComposta)) {
                    obsListMaoDeObraPecaComposta.remove(obra);
                    tvwMaoDeObra.setItems(obsListMaoDeObraPecaComposta);
                    break;
                }
            }

            BigDecimal tg = new BigDecimal("0.00");

            for (MaoDeObraPecaComposta mdopc : obsListMaoDeObraPecaComposta) {
                tg = tg.add(mdopc.getVlCustoTotal());
            }

            if (!obsListMaoDeObraPecaComposta.isEmpty()) {
                lblTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

                jtxTotalMaoDeObra.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

                lblQtdMaoDeObraAdd.setText(obsListMaoDeObraPecaComposta.size() > 1
                        ? "Qtd.: " + obsListMaoDeObraPecaComposta.size()
                        : "Qtd.: " + obsListMaoDeObraPecaComposta.size());
            } else {
                jtxTotalMaoDeObra.setText("");
                lblTotalMaoDeObra.setText("");
                lblQtdMaoDeObraAdd.setText("");

                jmskCodigoMaoDeObra.requestFocus();
            }

            if (calculaValorCusto().doubleValue() == 0.0) {
                jtxCustoPecaComposta.setText("");
            } else {
                jtxCustoPecaComposta.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaValorCusto()));
            }

            if (calculaPrecoVenda().doubleValue() == 0.0) {
                jtxPrecoVenda.setText("");
            } else {
                jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaPrecoVenda()));
            }

        } else if (event.getCode() == KeyCode.ALT) {
            if (obsListMaoDeObraPecaComposta.isEmpty()) {
                return;
            }
            editaMaoDeObraPecaComposta();
        }
    }

    @FXML
    private void jmskMarkupKeyReleased() {
        String mrkp = CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(jmskMarkup.getText());
        BigDecimal markup = CecilDecimalFormatter.getInstance().bigStandardValue(mrkp);
        BigDecimal custo = CecilDecimalFormatter.getInstance()
                .bigStandardValue(jtxCustoPecaComposta.getText());

        BigDecimal precoVenda = custo.multiply(markup);

        if (precoVenda.doubleValue() == 0.0) {
            jtxPrecoVenda.setText("");
        } else {
            jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance()
                    .formattedValue(precoVenda));
        }
    }

    @FXML
    private void tvwItensKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            ItemPecaComposta itemPecaComposta = (ItemPecaComposta) tvwItens.getSelectionModel().getSelectedItem();

            for (ItemPecaComposta ipc : obsListItemPecaComposta) {
                if (ipc.equals(itemPecaComposta)) {
                    obsListItemPecaComposta.remove(ipc);
                    tvwItens.setItems(obsListItemPecaComposta);
                    break;
                }
            }
            BigDecimal tg = new BigDecimal("0.00");

            for (ItemPecaComposta itens : obsListItemPecaComposta) {
                tg = tg.add(itens.getVlCustoTotal());
            }

            if (!obsListItemPecaComposta.isEmpty()) {
                lblTotalItens.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

                jtxTotalItens.setText(CecilDecimalFormatter.getInstance().formattedValue(tg));

                lblQtdItensAdd.setText(obsListItemPecaComposta.size() > 1
                        ? "Qtd.: " + obsListItemPecaComposta.size()
                        : "Qtd.: " + obsListItemPecaComposta.size());
            } else {
                jtxTotalItens.setText("");
                lblQtdItensAdd.setText("");

                jmskCodigoItem.requestFocus();
            }

            if (calculaValorCusto().doubleValue() == 0.0) {
                jtxCustoPecaComposta.setText("");
            } else {
                jtxCustoPecaComposta.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaValorCusto()));
            }

            if (calculaPrecoVenda().doubleValue() == 0.0) {
                jtxPrecoVenda.setText("");
            } else {
                jtxPrecoVenda.setText(CecilDecimalFormatter.getInstance().formattedValue(calculaPrecoVenda()));
            }

            List<DetalheItemPecaComposta> listDetalheIPC = new ArrayList();

            listDetalheItemPecaComposta.forEach((dipc) -> {
                listDetalheIPC.add(dipc);
            });

            for (DetalheItemPecaComposta dipc : listDetalheIPC) {
                if (dipc.getCdPecaMediadora() == itemPecaComposta.getCdPeca()) {
                    listDetalheItemPecaComposta.remove(dipc);
                }
            }

        } else if (event.getCode() == KeyCode.ALT) {
            if (obsListItemPecaComposta.isEmpty()) {
                return;
            }
            editaItemPecaComposta();
        }
    }

    @FXML
    private void jbtnLimparCamposItemAction() {
        jmskCodigoItem.setText("");
        lblDescricaoItem.setText("");
        lblUnidadeMedida.setText("");
        jmskQtdItem.setText("");

        jmskCodigoItem.resetValidation();
        jmskQtdItem.resetValidation();

        setModoEdicaoItem(false);
        jmskCodigoItem.setEditable(true);
        jbtnConsultarItensPecaComposta.setDisable(false);

        jbtnAdicionarItem.setText("Adicionar");

        jmskCodigoItem.requestFocus();
    }

    @FXML
    private void jbtnLimparCamposMdoAction() {
        jmskCodigoMaoDeObra.setText("");
        lblDescricaoMaoDeObra.setText("");
        jmskQtdHHMaoDeObra.setText("");
        jmskQtdMMMaoDeObra.setText("");
        lblDoisPontosSeparadoresHHMM.setVisible(false);

        jmskCodigoMaoDeObra.resetValidation();
        lblQtdHoraMdoNaoInformado.setVisible(false);
        imgvwQtdHoraMdoNaoInformado.setVisible(false);

        setModoEdicaoMdo(false);
        jmskCodigoMaoDeObra.setEditable(true);
        jbtnConsultarMaoDeObra.setDisable(false);

        jbtnAdicionarMdo.setText("Adicionar");

        jmskCodigoMaoDeObra.requestFocus();
    }

    @FXML
    private void jbtnSalvarAction() {

        if (!jmskCodigoPecaComposta.validate()) {
            jmskCodigoPecaComposta.requestFocus();
            return;
        }
        if (!jtxDescricaoPecaComposta.validate()) {
            jtxDescricaoPecaComposta.requestFocus();
            return;
        }
        if (!jtxTotalItens.validate()) {
            jtxTotalItens.requestFocus();
            return;
        }
        if (!jtxCustoPecaComposta.validate()) {
            jtxCustoPecaComposta.requestFocus();
            return;
        }
        if (!jmskMarkup.validate()) {
            jmskMarkup.requestFocus();
            return;
        }
        if (!jtxPrecoVenda.validate()) {
            jtxPrecoVenda.requestFocus();
            return;
        }

        PecaComposta pecaComposta = new PecaComposta();
        pecaComposta.setCdPeca(Integer.parseInt(jmskCodigoPecaComposta.getText()));
        pecaComposta.setDsPeca(jtxDescricaoPecaComposta.getText().trim());

        pecaComposta.setMarkup(CecilDecimalFormatter.getInstance().bigStandardValue(jmskMarkup.getText()));

        pecaComposta.setLstMaoDeObra(obsListMaoDeObraPecaComposta);
        pecaComposta.setLstItensPecaComposta(obsListItemPecaComposta);
        pecaComposta.setLstDetalheItemPecaComposta(listDetalheItemPecaComposta);

        try {
            if (PecaCompostaService.getInstance().salvaPecaComposta(pecaComposta, isModoEdicao())) {
                NotifierPigeon.getInstance().notificaSucesso(!isModoEdicao()
                        ? "Cadastro bem sucedido!" : "Atualização bem sucedida!");
            }
            limpaForm();

        } catch (SQLException | ClassNotFoundException ex) {
            DialogFactory.getInstance().erro(ex.getMessage());
        }
        jmskCodigoPecaComposta.requestFocus();
    }

    @FXML
    private void jbtnExcluirAction() {
        if (DialogFactory.getInstance().adverte("trash.png",
                "Excluir Peça Composta", "Esta peça será excluida permanentemente",
                "Tem certeza que deseja excluir esta peça ?", "EXCLUIR")) {
            try {
                PecaCompostaService.getInstance()
                        .excluiPecaComposta(Integer.parseInt(jmskCodigoPecaComposta.getText()));
                notificacoes.notificaExcluido();
                limpaForm();
                jmskCodigoPecaComposta.requestFocus();
            } catch (ClassNotFoundException | SQLException ex) {
                DialogFactory.getInstance().erro(ex.getMessage());
            }
        }
    }

    @FXML
    private void jbtnLimparAction() {
        limpaForm();

        jmskCodigoPecaComposta.resetValidation();
        jtxDescricaoPecaComposta.resetValidation();
        jtxTotalItens.resetValidation();
        jtxTotalMaoDeObra.resetValidation();
        jtxCustoPecaComposta.resetValidation();
        jmskMarkup.resetValidation();
        jtxPrecoVenda.resetValidation();
        jmskCodigoItem.resetValidation();
        jmskQtdItem.resetValidation();
        jmskCodigoMaoDeObra.resetValidation();

        lblQtdHoraMdoNaoInformado.setVisible(false);
        imgvwQtdHoraMdoNaoInformado.setVisible(false);

        jmskCodigoPecaComposta.requestFocus();

    }

    private void limpaForm() {
        jmskCodigoPecaComposta.setEditable(true);
        jbtnExcluir.setDisable(true);
        setModoEdicao(false);

        jmskCodigoPecaComposta.setText("");
        jtxDescricaoPecaComposta.setText("");
        jtxTotalItens.setText("");
        limpaCamposItem();

        jtxTotalMaoDeObra.setText("");
        limpaCamposMdo();

        jtxCustoPecaComposta.setText("");
        jmskMarkup.setText("");
        jtxPrecoVenda.setText("");

        tbpnIPCMDO.getSelectionModel().select(tbItens);

        obsListItemPecaComposta.clear();
        listDetalheItemPecaComposta.clear();
        obsListMaoDeObraPecaComposta.clear();

        jbtnSalvar.setText("SALVAR");
    }

    @FXML
    private void jbtnFecharAction() {
        formStage.close();
        for (FormMenu fm : MainController.lstFormsMenu) {
            if (fm.getMenum().equals(MenuEnum.CADASTRO_MONTAGEM)) {
                MainController.lstFormsMenu.remove(fm);
                break;
            }
        }
    }

    @FXML
    private void tbItensSelectionChanged() {
        if (tbItens.isSelected()) {
            tbItens.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #0f9d58;\n"
                    + "}");
            jtxTotalItens.setStyle("-jfx-unfocus-color: #0f9d58;"
                    + "-fx-prompt-text-fill: #0f9d58;");
            imgvwItens.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "itemb.png").toString()));
        } else {
            tbItens.setStyle("-fx-border-width: 0");
            jtxTotalItens.setStyle("-jfx-unfocus-color: #4050a8;"
                    + "-fx-prompt-text-fill: #4050a8;");
            imgvwItens.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "itema.png").toString()));

            jmskCodigoMaoDeObra.resetValidation();
            lblQtdHoraMdoNaoInformado.setVisible(false);
            imgvwQtdHoraMdoNaoInformado.setVisible(false);

            lblCodigoMdoNaoCadastrado.setVisible(false);
            imgvwCodigoMdoNaoCadastrado.setVisible(false);

        }

    }

    @FXML
    private void tbMaoDeObraSelectionChanged() {
        if (tbMaoDeObra.isSelected()) {
            tbMaoDeObra.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #0f9d58;\n"
                    + "}");

            jtxTotalMaoDeObra.setStyle("-jfx-unfocus-color: #0f9d58;"
                    + "-fx-prompt-text-fill: #0f9d58;");
            imgvwMaoDeObra.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "mdob.png").toString()));
        } else {
            tbMaoDeObra.setStyle("-fx-border-width: 0");
            jtxTotalMaoDeObra.setStyle("-jfx-unfocus-color: #4050a8;"
                    + "-fx-prompt-text-fill: #4050a8;");
            imgvwMaoDeObra.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "mdoa.png").toString()));

            jmskCodigoItem.resetValidation();
            jmskQtdItem.resetValidation();
            lblCodigoItemNaoCadastrado.setVisible(false);
            imgvwCodigoItemNaoCadastrado.setVisible(false);

        }

    }

    private void tbpnIPCMDOFocused() {
        lnSuperior.setStyle("-fx-stroke: #17613c");
        lnInferior.setStyle("-fx-stroke: #17613c");
        lnDireita.setStyle("-fx-stroke: #17613c");
        lnEsquerdaItens.setStyle("-fx-stroke: #17613c");
        lnEsquerdaMDO1.setStyle("-fx-stroke: #17613c");
        lnEsquerdaMDO2.setStyle("-fx-stroke: #17613c");

        if (tbItens.isSelected()) {
            tbItens.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #0f9d58;\n"
                    + "}");
            tbMaoDeObra.setStyle("-fx-border-width: 0");

            jtxTotalItens.setStyle("-jfx-unfocus-color: #0f9d58;"
                    + "-fx-prompt-text-fill: #0f9d58;");
            imgvwItens.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "itemb.png").toString()));
        } else if (tbMaoDeObra.isSelected()) {
            tbItens.setStyle("-fx-border-width: 0");
            tbMaoDeObra.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #0f9d58;\n"
                    + "}");

            jtxTotalMaoDeObra.setStyle("-jfx-unfocus-color: #0f9d58;"
                    + "-fx-prompt-text-fill: #0f9d58;");
            imgvwMaoDeObra.setImage(new Image(MainApp.class
                    .getResource(PathEnum.IMAGES_PATH + "mdob.png").toString()));
        }
    }

    private void tbpnIPCMDOUnFocused() {
        if (tbItens.isSelected()) {
            tbItens.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #4050a8;\n"
                    + "}");
            tbMaoDeObra.setStyle("-fx-border-width: 0");
        } else if (tbMaoDeObra.isSelected()) {
            tbItens.setStyle("-fx-border-width: 0");
            tbMaoDeObra.setStyle(".tab:selected {\n"
                    + "    -fx-border-width: 1.5 1 0 1.5;\n"
                    + "    -fx-border-color: #4050a8;\n"
                    + "}");
        }

        lnSuperior.setStyle("-fx-stroke: #14256b");
        lnInferior.setStyle("-fx-stroke: #14256b");
        lnDireita.setStyle("-fx-stroke: #14256b");
        lnEsquerdaItens.setStyle("-fx-stroke: #14256b");
        lnEsquerdaMDO1.setStyle("-fx-stroke: #14256b");
        lnEsquerdaMDO2.setStyle("-fx-stroke: #14256b");

        jtxTotalItens.setStyle("-jfx-unfocus-color: #4050a8; -fx-prompt-text-fill: #4050a8;");
        jtxTotalMaoDeObra.setStyle("-jfx-unfocus-color: #4050a8; -fx-prompt-text-fill: #4050a8;");

        imgvwItens.setImage(new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "itema.png").toString()));

        imgvwMaoDeObra.setImage(new Image(MainApp.class
                .getResource(PathEnum.IMAGES_PATH + "mdoa.png").toString()));
    }

    private void limpaCamposItem() {
        jmskCodigoItem.setText("");
        lblDescricaoItem.setText("");
        lblUnidadeMedida.setText("");
        jmskQtdItem.setText("");
        jmskCodigoItem.resetValidation();
        jmskQtdItem.resetValidation();
        lblCodigoItemNaoCadastrado.setVisible(false);
        imgvwCodigoItemNaoCadastrado.setVisible(false);

        lblQtdItensAdd.setText("");
        lblTotalItens.setText("");
    }

    private void limpaCamposMdo() {
        jmskCodigoMaoDeObra.setText("");
        lblDescricaoMaoDeObra.setText("");
        jmskQtdHHMaoDeObra.setText("");
        jmskQtdMMMaoDeObra.setText("");

        jmskCodigoMaoDeObra.resetValidation();

        lblQtdHoraMdoNaoInformado.setVisible(false);
        imgvwQtdHoraMdoNaoInformado.setVisible(false);

        lblCodigoMdoNaoCadastrado.setVisible(false);
        imgvwCodigoMdoNaoCadastrado.setVisible(false);

        lblQtdMaoDeObraAdd.setText("");
        lblTotalMaoDeObra.setText("");
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public void setModoEdicao(boolean modoEdicao) {
        this.modoEdicao = modoEdicao;
    }

    public boolean isModoEdicaoItem() {
        return modoEdicaoItem;
    }

    public void setModoEdicaoItem(boolean modoEdicaoItem) {
        this.modoEdicaoItem = modoEdicaoItem;
    }

    public boolean isModoEdicaoMdo() {
        return modoEdicaoMdo;
    }

    public void setModoEdicaoMdo(boolean modoEdicaoMdo) {
        this.modoEdicaoMdo = modoEdicaoMdo;
    }

}
