package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.EntreeStockService;
import com.cecilsoftwares.reussoftbackend.service.FournisseurService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationFournisseur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import java.awt.Cursor;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class OperationEntreeStock extends JInternalFrame {

    private int codeEntreeStock;
    private boolean modeEdition;
    private boolean modeEditionFournisseur;
    private boolean modeEditionitemEntreeStock;
    private boolean btnConsulterEntreeStockClickable;
    private boolean btnConsulterFournisseurClickable;
    private boolean btnConsulterProduitClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    private Produit produitSelectionne;
    private final List<ItemEntreeStock> itemsEntreeStock;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    public OperationEntreeStock() {
        initComponents();

        itemsEntreeStock = new ArrayList();

        defaultTableModel = (DefaultTableModel) tblItemsEntreeStock.getModel();
        dataRows = new Object[5];

        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tfdIdProduit = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        lblDescriptionProduit = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblProduitStockActuel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnAjouterProduit = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsEntreeStock = new javax.swing.JTable();
        btnEffacerChampsProduits = new javax.swing.JButton();
        lblNombreItemEntreeStock = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        tfdDateHeureEntreeStock = new javax.swing.JTextField();
        btnConsulterEntreeStock = new javax.swing.JButton();
        lblTauxcarte = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnConsulterFournisseur = new javax.swing.JButton();
        lblInfoFournisseur = new javax.swing.JLabel();
        tfdIdFournisseur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrée Stock");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produits"));

        tfdIdProduit.setEditable(false);

        btnConsulterProduit.setText("...");
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        lblDescriptionProduit.setText("jLabel2");

        jLabel12.setText("Code:");

        lblProduitStockActuel.setText("jLabel5");

        jLabel14.setText("Stock actuel:");

        jLabel8.setText("Prix d'achat:");

        jLabel16.setText("labelx");

        btnAjouterProduit.setText("+");
        btnAjouterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterProduitActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantité:");

        tblItemsEntreeStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Produit", "Quantité", "Prix d'achat", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        tblItemsEntreeStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsEntreeStockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemsEntreeStock);
        if (tblItemsEntreeStock.getColumnModel().getColumnCount() > 0) {
            tblItemsEntreeStock.getColumnModel().getColumn(0).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(1).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblItemsEntreeStock.getColumnModel().getColumn(2).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblItemsEntreeStock.getColumnModel().getColumn(3).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(4).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(4).setPreferredWidth(100);
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
                                .addComponent(jLabel16)
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
                        .addComponent(jLabel16)
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

        btnAnnuler.setText("ANNULER");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        btnEnregistrer.setText("ENREGISTRER");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Date:");

        tfdDateHeureEntreeStock.setEditable(false);

        btnConsulterEntreeStock.setText("...");
        btnConsulterEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterEntreeStockActionPerformed(evt);
            }
        });

        lblTauxcarte.setText("jLabel10");

        jLabel13.setText("Taux carte:");

        btnConsulterFournisseur.setText("...");
        btnConsulterFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterFournisseurActionPerformed(evt);
            }
        });

        lblInfoFournisseur.setText("jLabel5");

        tfdIdFournisseur.setEditable(false);

        jLabel1.setText("Fournisseur:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfdIdFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConsulterFournisseur)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblInfoFournisseur)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdDateHeureEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterEntreeStock))
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTauxcarte)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDateHeureEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterEntreeStock)
                    .addComponent(jLabel13)
                    .addComponent(lblTauxcarte))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterFournisseur)
                    .addComponent(lblInfoFournisseur))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        Produit produit = new Produit(Integer.parseInt(tfdIdProduit.getText()));

//        EntreeStock entreeStock = new EntreeStock.EntreeStockBuilder(Integer.parseInt(tfdCode.getText()))
//                .produit(produit)
//                .prixAchatUSD(new BigDecimal(tfdPrixAchatUSD.getText()))
//                .prixAchatFC(new BigDecimal(tfdPrixAchatFC.getText()))
//                .quantiteProduit(new BigDecimal(spnQuantiteProduit.getToolTipText()))
//                .build();
//
//        try {
//            if (EntreeStockService.getInstance().enregistrerEntreeStock(entreeStock)) {
//                effacerFormulaire();
//                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant l'Entree de Stock");
//            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    public void entreeStockSelectionne(EntreeStock entreeStock) {
        if (!isInformationObligatoiresRemplies()) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

//        Fournisseur fournisseur = new FournisseurBuilder(Integer.parseInt(tfdIdFournisseur.getText())).build();
//        Produit produit = new ProduitBuilder(Integer.parseInt(tfdIdProduit.getText())).build();
//        EntreeStock shop = new EntreeStockBuilder(codeEntreeStock)
//                .fournisseur(fournisseur)
//                .produit(produit)
//                .prixAchatFC(new BigDecimal(tfdPrixAchatFC.getText()))
//                .prixAchatUSD(new BigDecimal(tfdPrixAchatUSD.getText()))
//                .nom(tfdNom.getText())
//                .adresse(adresse)
//                .active(modeEdition ? chbActiver.isSelected() : true)
//                .build();
//
//        try {
//            if (ShopService.getInstance().enregistrerShop(shop)) {
//                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
//                effacerFormulaire();
//                JOptionPane.showMessageDialog(null, notification);
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le nouveau Shop");
//            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
//        }
        setCursor(Cursor.getDefaultCursor());
    }

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnAjouterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterProduitActionPerformed

        if (!spnQuantiteProduit.getValue().equals("0") && !spnQuantiteProduit.getValue().equals("")) {

            if (modeEditionitemEntreeStock) {
                for (ItemEntreeStock ies : itemsEntreeStock) {
                    if (ies.getProduit().getCode() == codeEntreeStock) {
                        ies.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));
                        break;
                    }
                }

                defaultTableModel.setRowCount(0);

                itemsEntreeStock.forEach(ies -> {
                    dataRows[0] = ies.getProduit().getCode();
                    dataRows[1] = ies.getProduit().getDescription();
                    dataRows[2] = ies.getQuantiteProduit();
                    dataRows[3] = new StringBuilder(ies.getProduit().getPrixAchatUSD().toString()).append(" $ + ")
                            .append(ies.getProduit().getPrixAchatFC().toString()).append(" FC");
                    dataRows[4] = new StringBuilder(ies.getProduit().getPrixAchatUSD().toString()).append(" $ + ")
                            .append(ies.getProduit().getPrixAchatFC().toString()).append(" FC");
                    defaultTableModel.addRow(dataRows);
                });

                String formeNombre = itemsEntreeStock.size() > 1 ? "Items" : "Item";
                lblNombreItemEntreeStock.setText(itemsEntreeStock.size() + " " + formeNombre);

            } else {

                EntreeStock entreeStock = new EntreeStock(codeEntreeStock);

                ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produitSelectionne);
                itemEntreeStock.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));

                itemsEntreeStock.add(itemEntreeStock);

                defaultTableModel.setRowCount(0);

                itemsEntreeStock.forEach(ies -> {
                    dataRows[0] = ies.getProduit().getCode();
                    dataRows[1] = ies.getProduit().getDescription();
                    dataRows[2] = ies.getQuantiteProduit();
                    dataRows[3] = new StringBuilder(ies.getProduit().getPrixAchatUSD().toString()).append(" $ + ")
                            .append(ies.getProduit().getPrixAchatFC().toString()).append(" FC");
                    dataRows[4] = new StringBuilder(ies.getProduit().getPrixAchatUSD().toString()).append(" $ + ")
                            .append(ies.getProduit().getPrixAchatFC().toString()).append(" FC");
                    defaultTableModel.addRow(dataRows);
                });

                String formeNombre = itemsEntreeStock.size() > 1 ? "Items" : "Item";
                lblNombreItemEntreeStock.setText(itemsEntreeStock.size() + " " + formeNombre);
            }
            effacerChampsItemStock();
        } else {
            JOptionPane.showMessageDialog(null, "Quantité incorrect!");
        }

    }//GEN-LAST:event_btnAjouterProduitActionPerformed

    private void btnConsulterEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterEntreeStockActionPerformed
        if (btnConsulterEntreeStockClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                List<EntreeStock> entreesStock = EntreeStockService.getInstance().listerTousLesEntreesStockSansItems();
                ConsultationEntreeStock consultationMouvementStock = new ConsultationEntreeStock(null, true, entreesStock);
                consultationMouvementStock.setFrameAncetre(this);
                consultationMouvementStock.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterEntreeStockActionPerformed

    public void EntreeStockSelectionnee(EntreeStock mouvementStock) {
//        if (mouvementStock != null) {
//            modeEdition = true;
//
//            idMouvementStock = mouvementStock.getCode();
//            tfdIdFournisseur.setText(mouvementStock.getFour());
//
//            String[] adresse = shop.getAdresse().split("@");
//            tfdAvenue.setText(adresse[0]);
//            tfdNumero.setText(adresse[1]);
//            tfdQuartier.setText(adresse[2]);
//            tfdCommune.setText(adresse[3]);
//            switch (adresse[4]) {
//                case "Kinshasa":
//                    cbxProvince.setSelectedIndex(0);
//                    break;
//                case "Kasaï":
//                    cbxProvince.setSelectedIndex(1);
//                    break;
//                case "Kongo-Central":
//                    cbxProvince.setSelectedIndex(2);
//                    break;
//            }
//            tfdDistrict.setText(adresse[5]);
//
//            chbActiver.setVisible(true);
//            chbActiver.setSelected(shop.isActive());
//            btnEnregistrer.setText("ACTUALISER");
//        }
    }

    private void btnConsulterFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterFournisseurActionPerformed
        if (btnConsulterFournisseurClickable) {
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
    }//GEN-LAST:event_btnConsulterFournisseurActionPerformed

    public void fournisseurSelectionne(Fournisseur fournisseur) {
        if (fournisseur != null) {
            modeEditionFournisseur = true;

            tfdIdFournisseur.setText(String.valueOf(fournisseur.getCode()));
            lblInfoFournisseur.setText(new StringBuilder(fournisseur.getEntreprise())
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

    public void effacerChampsItemStock() {
        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        jLabel16.setText("");
        lblProduitStockActuel.setText("");
        modeEditionitemEntreeStock = false;
        tfdIdProduit.requestFocus();
    }

    private void tblItemsEntreeStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsEntreeStockMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblItemsEntreeStock.getSelectedRow();

            ItemEntreeStock itemEntreeStock;

            itemEntreeStock = itemsEntreeStock.stream()
                    .filter(ies -> ies.getProduit().getCode() == (int) defaultTableModel.getValueAt(row, 0))
                    .findFirst().orElse(null);

            if (itemEntreeStock != null) {
                codeEntreeStock = itemEntreeStock.getProduit().getCode();
                tfdIdProduit.setText(String.valueOf(itemEntreeStock.getProduit().getCode()));
                lblDescriptionProduit.setText(String.valueOf(itemEntreeStock.getProduit().getDescription()));
                spnQuantiteProduit.setValue(itemEntreeStock.getQuantiteProduit());
                modeEditionitemEntreeStock = true;

            }
        }
    }//GEN-LAST:event_tblItemsEntreeStockMouseClicked

    public void setProduitSelectionne(Produit produit) {
        if (produit != null) {
            produitSelectionne = produit;
            tfdIdProduit.setText(String.valueOf(produitSelectionne.getCode()));
            lblDescriptionProduit.setText(produitSelectionne.getDescription());
        }
    }

    private void effacerFormulaire() {

        tfdDateHeureEntreeStock.setText("");
        tfdDateHeureEntreeStock.requestFocus();

        lblTauxcarte.setText("");
        tfdIdFournisseur.setText("");
        lblInfoFournisseur.setText("");

        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        jLabel16.setText("");
        lblProduitStockActuel.setText("");
        modeEditionitemEntreeStock = false;

        defaultTableModel.setRowCount(0);

        habiliterComposantFormulaire(true);

    }

    private void habiliterComposantFormulaire(boolean hcf) {
//        tfdNom.setEditable(hcf);
//        tfdAvenue.setEditable(hcf);
//        tfdNumero.setEditable(hcf);
//        tfdQuartier.setEditable(hcf);
//        tfdCommune.setEditable(hcf);
//        tfdDistrict.setEditable(hcf);
//        chbActiver.setEnabled(hcf);
//        cbxProvince.setEnabled(hcf);
        btnConsulterEntreeStockClickable = hcf;
        btnConsulterFournisseurClickable = hcf;
        btnConsulterProduitClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

//        if (tfdNom.getText().isEmpty()) {
//            notification.append("\nNom");
//            nio.add(1);
//        }
//        if (tfdAvenue.getText().isEmpty()) {
//            notification.append("\nAvenue");
//            nio.add(2);
//        }
//        if (tfdNumero.getText().isEmpty()) {
//            notification.append("\nNuméro");
//            nio.add(3);
//        }
//
//        if (tfdQuartier.getText().isEmpty()) {
//            notification.append("\nQuartier");
//            nio.add(4);
//        }
//        if (tfdCommune.getText().isEmpty()) {
//            notification.append("\nCommune");
//            nio.add(5);
//        }
//
//        if (notification.toString().isEmpty()) {
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
//                    .append(notification));
//            switch (nio.poll()) {
//                case 1:
//                    tfdNom.requestFocus();
//                    break;
//
//                case 2:
//                    tfdAvenue.requestFocus();
//                    break;
//                case 3:
//                    tfdNumero.requestFocus();
//                    break;
//
//                case 4:
//                    tfdQuartier.requestFocus();
//                    break;
//                case 5:
//                    tfdCommune.requestFocus();
//                    break;
//                default:
//            }
//            return false;
//        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterProduit;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterEntreeStock;
    private javax.swing.JButton btnConsulterFournisseur;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnEffacerChampsProduits;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescriptionProduit;
    private javax.swing.JLabel lblInfoFournisseur;
    private javax.swing.JLabel lblNombreItemEntreeStock;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblTauxcarte;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblItemsEntreeStock;
    private javax.swing.JTextField tfdDateHeureEntreeStock;
    private javax.swing.JTextField tfdIdFournisseur;
    private javax.swing.JTextField tfdIdProduit;
    // End of variables declaration//GEN-END:variables
}
