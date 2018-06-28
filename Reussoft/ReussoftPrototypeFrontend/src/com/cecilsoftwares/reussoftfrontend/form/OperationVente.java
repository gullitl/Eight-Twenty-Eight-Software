package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.FournisseurService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.SortieStockService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationFournisseur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class OperationVente extends JInternalFrame {

    private int codeVente;
    private int codeTauxMonnaie;
    private boolean modeEdition;
    private boolean modeEditionItemVente;

    private boolean btnConsulterVenteClickable;
    private boolean btnConsulterClientClickable;
    private boolean btnConsulterProduitClickable;
    private boolean btnAjouterProduitClickable;
    private boolean btnEffacerChampsProduitsClickable;
    private boolean tblItemsVenteClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    private Produit produitSelectionne;
    private List<ItemSortieStock> itemsVente;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    public OperationVente() {
        initComponents();
        pnlVente.setVisible(false);

        itemsVente = new ArrayList();

        defaultTableModel = (DefaultTableModel) tblItemsVente.getModel();
        dataRows = new Object[5];

        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVente = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tfdIdProduit = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        lblDescriptionProduit = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblProduitStockActuel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPrixAchat = new javax.swing.JLabel();
        btnAjouterProduit = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsVente = new javax.swing.JTable();
        btnEffacerChampsProduits = new javax.swing.JButton();
        lblNombreItemEntreeStock = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnConsulterClient = new javax.swing.JButton();
        lblInfoClient = new javax.swing.JLabel();
        tfdIdClient = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnEnregistrer = new javax.swing.JButton();
        lblTotalAPayer = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfdDateHeureSortieStock = new javax.swing.JTextField();
        btnConsulterEntreeStock = new javax.swing.JButton();
        lblTauxcarte = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        btnOuvrirVente = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vente");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        tfdIdProduit.setEditable(false);

        btnConsulterProduit.setText("...");
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        lblDescriptionProduit.setText("jLabel2");

        jLabel12.setText("Produit:");

        lblProduitStockActuel.setText("jLabel5");

        jLabel14.setText("Stock actuel:");

        jLabel8.setText("Prix de vente:");

        lblPrixAchat.setText("labelx");

        btnAjouterProduit.setText("+");
        btnAjouterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterProduitActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantité:");

        tblItemsVente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Produit", "Quantité", "Prix de vente", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemsVente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsVenteMouseClicked(evt);
            }
        });
        tblItemsVente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblItemsVenteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemsVente);
        if (tblItemsVente.getColumnModel().getColumnCount() > 0) {
            tblItemsVente.getColumnModel().getColumn(0).setResizable(false);
            tblItemsVente.getColumnModel().getColumn(1).setResizable(false);
            tblItemsVente.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblItemsVente.getColumnModel().getColumn(2).setResizable(false);
            tblItemsVente.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblItemsVente.getColumnModel().getColumn(3).setResizable(false);
            tblItemsVente.getColumnModel().getColumn(4).setResizable(false);
            tblItemsVente.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        btnEffacerChampsProduits.setText("<-");
        btnEffacerChampsProduits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerChampsProduitsActionPerformed(evt);
            }
        });

        lblNombreItemEntreeStock.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfdIdProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterProduit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescriptionProduit)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrixAchat)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProduitStockActuel))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btnAjouterProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEffacerChampsProduits, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblNombreItemEntreeStock))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProduit)
                    .addComponent(lblDescriptionProduit))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrixAchat)
                        .addComponent(jLabel8)
                        .addComponent(jLabel14)
                        .addComponent(lblProduitStockActuel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterProduit)
                    .addComponent(btnEffacerChampsProduits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreItemEntreeStock)
                .addContainerGap())
        );

        jLabel13.setText("Taux carte:");

        btnConsulterClient.setText("...");
        btnConsulterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterClientActionPerformed(evt);
            }
        });

        lblInfoClient.setText("jLabel5");

        tfdIdClient.setEditable(false);

        jLabel1.setText("Client:");

        btnAnnuler.setText("ANNULER");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        jLabel2.setText("Total à Recevoir:");

        btnEnregistrer.setText("EFFECTUER VENTE");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        lblTotalAPayer.setText("jLabel3");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Date:");

        tfdDateHeureSortieStock.setEditable(false);

        btnConsulterEntreeStock.setText("...");
        btnConsulterEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterEntreeStockActionPerformed(evt);
            }
        });

        lblTauxcarte.setText("jLabel10");

        jLabel3.setText("Total reçu:");

        jLabel5.setText("Différence:");

        jCheckBox1.setText("FC");

        jCheckBox2.setText("U$D");

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout pnlVenteLayout = new javax.swing.GroupLayout(pnlVente);
        pnlVente.setLayout(pnlVenteLayout);
        pnlVenteLayout.setHorizontalGroup(
            pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVenteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVenteLayout.createSequentialGroup()
                            .addComponent(tfdIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConsulterClient)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblInfoClient))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalAPayer))
                    .addComponent(jLabel3)
                    .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlVenteLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBox1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBox2)))
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addComponent(tfdDateHeureSortieStock, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterEntreeStock))
                    .addComponent(jLabel15)
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTauxcarte))
                    .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlVenteLayout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVenteLayout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(136, 136, 136))
        );
        pnlVenteLayout.setVerticalGroup(
            pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsulterClient)
                            .addComponent(lblInfoClient)))
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdDateHeureSortieStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsulterEntreeStock))))
                .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVenteLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblTauxcarte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalAPayer)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(33, 33, 33)
                        .addGroup(pnlVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnOuvrirVente.setText("Ouvrir");
        btnOuvrirVente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOuvrirVenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(pnlVente, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(btnOuvrirVente)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnOuvrirVente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlVente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        if (isInformationObligatoiresRemplies()) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            SortieStock sortieStock = new SortieStock(codeVente);
            sortieStock.setClient(new Client(Integer.parseInt(tfdIdClient.getText())));
            sortieStock.setTauxMonnaie(new TauxMonnaie(codeTauxMonnaie));
            sortieStock.setDateHeure(new Date());
            sortieStock.setItemsSortieStock(itemsVente);

            try {
                if (SortieStockService.getInstance().enregistrerSortieStock(sortieStock)) {
                    String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                    effacerFormulaire();
                    JOptionPane.showMessageDialog(null, notification);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Client");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    public void sortieStockSelectionnee(SortieStock sortieStock) {
        if (sortieStock != null) {
            tfdDateHeureSortieStock.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(sortieStock.getDateHeure()));
            tfdIdClient.setText(String.valueOf(sortieStock.getClient().getCode()));
            lblInfoClient.setText(new StringBuilder(sortieStock.getClient().getNom())
                    .append(sortieStock.getClient().getEntreprise()).toString());

            itemsVente = sortieStock.getItemsSortieStock();

            chargerTableauItemEntreeStock();

        }
    }

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnAjouterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterProduitActionPerformed

        if (spnQuantiteProduit.getValue().toString().equals("0") || spnQuantiteProduit.getValue().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantité incorrect!");
        } else {

            if (modeEditionItemVente) {
                for (ItemSortieStock iss : itemsVente) {
                    if (iss.getProduit().getCode() == codeVente) {
                        iss.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));
                        break;
                    }
                }

            } else {

                SortieStock sortieStock = new SortieStock(codeVente);

                ItemSortieStock itemEntreeStock = new ItemSortieStock(sortieStock, produitSelectionne);
                itemEntreeStock.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));

                for (ItemSortieStock ies : itemsVente) {
                    if (ies.getProduit().getCode() == itemEntreeStock.getProduit().getCode()) {
                        JOptionPane.showMessageDialog(null, "Ce produit a déjà été ajouté");
                        return;
                    }
                }

                itemsVente.add(itemEntreeStock);
            }

            chargerTableauItemEntreeStock();

            effacerChampsItemStock();
        }

    }//GEN-LAST:event_btnAjouterProduitActionPerformed

    private void btnConsulterEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterEntreeStockActionPerformed
        if (btnConsulterVenteClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                List<SortieStock> sortiesStock = SortieStockService.getInstance().listerToutesLesSortiesStockSansItems();
                ConsultationSortieStock consultationSortieStock = new ConsultationSortieStock(null, true, sortiesStock);
                consultationSortieStock.setFrameAncetre(this);
                consultationSortieStock.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OperationVente.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterEntreeStockActionPerformed

    private void btnConsulterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterClientActionPerformed
        if (btnConsulterClientClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {

                ConsultationFournisseur consultationFournisseur = new ConsultationFournisseur(null, true, FournisseurService.getInstance()
                        .listerTousLesFournisseurs());
                consultationFournisseur.setFrameAncetre(this);
                consultationFournisseur.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterClientActionPerformed

    public void fournisseurSelectionne(Fournisseur fournisseur) {
        if (fournisseur != null) {
//            modeEditionFournisseur = true;

            tfdIdClient.setText(String.valueOf(fournisseur.getCode()));
            lblInfoClient.setText(new StringBuilder(fournisseur.getEntreprise())
                    .append(" ").append(fournisseur.getResponsable()).toString());
        }
    }

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (btnConsulterProduitClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {

                ConsultationProduit consultationProduit = new ConsultationProduit(null, true, ProduitService.getInstance()
                        .listerTousLesProduits());
                consultationProduit.setFrameAncetre(this);
                consultationProduit.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnEffacerChampsProduitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerChampsProduitsActionPerformed
        effacerChampsItemStock();
    }//GEN-LAST:event_btnEffacerChampsProduitsActionPerformed

    private void effacerChampsItemStock() {
        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        lblPrixAchat.setText("");
        lblProduitStockActuel.setText("");
        modeEditionItemVente = false;
        tfdIdProduit.requestFocus();
    }

    private void tblItemsVenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsVenteMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblItemsVente.getSelectedRow();

            ItemSortieStock itemSortieStock;

            itemSortieStock = itemsVente.stream()
                    .filter(ies -> ies.getProduit().getCode() == (int) defaultTableModel.getValueAt(row, 0))
                    .findFirst().orElse(null);

            if (itemSortieStock != null) {
                tfdIdProduit.setText(String.valueOf(itemSortieStock.getProduit().getCode()));
                lblDescriptionProduit.setText(String.valueOf(itemSortieStock.getProduit().getDescription()));
                spnQuantiteProduit.setValue(itemSortieStock.getQuantiteProduit());
                modeEditionItemVente = true;

            }
        }
    }//GEN-LAST:event_tblItemsVenteMouseClicked

    private void tblItemsVenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemsVenteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            boolean exclu = false;

            List<ItemSortieStock> listeItemsSortieStock = itemsVente;
            int row = tblItemsVente.getSelectedRow();

            for (ItemSortieStock iss : listeItemsSortieStock) {
                if (iss.getProduit().getCode() == (int) defaultTableModel.getValueAt(row, 0)) {
                    itemsVente.remove(iss);
                    exclu = true;
                    break;
                }
            }

            if (exclu) {
                chargerTableauItemEntreeStock();
            }

        }
    }//GEN-LAST:event_tblItemsVenteKeyReleased

    private void btnOuvrirVenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOuvrirVenteActionPerformed
        pnlVente.setVisible(true);
        btnOuvrirVente.setVisible(false);
    }//GEN-LAST:event_btnOuvrirVenteActionPerformed

    private void chargerTableauItemEntreeStock() {
        BigDecimal totalAPayer = new BigDecimal("0");
        defaultTableModel.setRowCount(0);

        itemsVente.forEach(ies -> {
            dataRows[0] = ies.getProduit().getCode();
            dataRows[1] = ies.getProduit().getDescription();
            dataRows[2] = ies.getQuantiteProduit();
            dataRows[3] = new StringBuilder(ies.getProduit().getPrixAchat().toString()).append(" $");
            dataRows[4] = new StringBuilder(ies.getProduit().getPrixAchat().multiply(ies.getQuantiteProduit()).toString()).append(" $");

            totalAPayer.add(ies.getProduit().getPrixAchat().multiply(ies.getQuantiteProduit()));
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = itemsVente.size() > 1 ? "Items" : "Item";
        lblNombreItemEntreeStock.setText(itemsVente.size() + " " + formeNombre);
        lblTotalAPayer.setText(totalAPayer.toString());

    }

    public void setProduitSelectionne(Produit produit) {
        if (produit != null) {
            produitSelectionne = produit;
            tfdIdProduit.setText(String.valueOf(produitSelectionne.getCode()));
            lblDescriptionProduit.setText(produitSelectionne.getDescription());
            lblPrixAchat.setText(new StringBuilder(produitSelectionne.getPrixAchat().toString()).append(" $").toString());
        }
    }

    private void effacerFormulaire() {

        tfdDateHeureSortieStock.setText("");
        tfdDateHeureSortieStock.requestFocus();

        lblTauxcarte.setText("");
        tfdIdClient.setText("");
        lblInfoClient.setText("");

        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        lblPrixAchat.setText("");
        lblProduitStockActuel.setText("");
        modeEditionItemVente = false;

        defaultTableModel.setRowCount(0);
        itemsVente.clear();

        habiliterComposantFormulaire(true);

    }

    private void habiliterComposantFormulaire(boolean hcf) {
        spnQuantiteProduit.setEnabled(hcf);
        btnConsulterVenteClickable = hcf;
        btnConsulterClientClickable = hcf;
        btnConsulterProduitClickable = hcf;

        btnAjouterProduitClickable = hcf;
        btnEffacerChampsProduitsClickable = hcf;
        tblItemsVenteClickable = hcf;

        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdIdClient.getText().isEmpty()) {
            notification.append("\nClient");
            nio.add(1);
        }
        if (itemsVente.isEmpty()) {
            notification.append("\nProduits");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdIdClient.requestFocus();
                    break;

                case 2:
                    tfdIdProduit.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterProduit;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterClient;
    private javax.swing.JButton btnConsulterEntreeStock;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnEffacerChampsProduits;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnOuvrirVente;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblDescriptionProduit;
    private javax.swing.JLabel lblInfoClient;
    private javax.swing.JLabel lblNombreItemEntreeStock;
    private javax.swing.JLabel lblPrixAchat;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblTauxcarte;
    private javax.swing.JLabel lblTotalAPayer;
    private javax.swing.JPanel pnlVente;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblItemsVente;
    private javax.swing.JTextField tfdDateHeureSortieStock;
    private javax.swing.JTextField tfdIdClient;
    private javax.swing.JTextField tfdIdProduit;
    // End of variables declaration//GEN-END:variables
}
