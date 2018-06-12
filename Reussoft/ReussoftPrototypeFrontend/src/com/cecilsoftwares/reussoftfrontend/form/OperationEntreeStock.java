package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.MouvementStockService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationMouvementStock;
import com.cecilsoftwares.reussoftmiddleend.enumarable.TypeMouvementStockEnum;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 * @author Plamedi L. Lusembo
 */
public class OperationEntreeStock extends JInternalFrame {

    private int idMouvementStock;
    private boolean modeEdition;
    private boolean btnConsulterMouvementStockClickable;
    private boolean btnConsulterFournisseurClickable;
    private boolean btnConsulterProduitClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    public OperationEntreeStock() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfdIdFournisseur = new javax.swing.JTextField();
        btnConsulterFournisseur = new javax.swing.JButton();
        lblInfoFournisseur = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfdIdProduit = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        lblDescriptionProduit = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblProduitStockActuel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfdPrixAchatUSD = new javax.swing.JTextField();
        tfdPrixAchatFC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntreeStock = new javax.swing.JTable();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfdIdMouvementStock = new javax.swing.JTextField();
        btnConsulterMouvementStock = new javax.swing.JButton();
        lblTauxcarte = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrée Stock");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fournisseur"));

        jLabel1.setText("Code:");

        tfdIdFournisseur.setEditable(false);

        btnConsulterFournisseur.setText("...");
        btnConsulterFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterFournisseurActionPerformed(evt);
            }
        });

        lblInfoFournisseur.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfdIdFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterFournisseur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoFournisseur)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterFournisseur)
                    .addComponent(lblInfoFournisseur))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produit"));

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

        jLabel8.setText("Prix d'achat (USD):");

        jLabel16.setText("Prix d'achat (FC):");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantité:");

        tblEntreeStock.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEntreeStock);
        if (tblEntreeStock.getColumnModel().getColumnCount() > 0) {
            tblEntreeStock.getColumnModel().getColumn(0).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(1).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblEntreeStock.getColumnModel().getColumn(2).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblEntreeStock.getColumnModel().getColumn(3).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(4).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProduitStockActuel)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel12)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdIdProduit)
                            .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnConsulterProduit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescriptionProduit))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProduitStockActuel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

        jLabel9.setText("Date:");

        lblDate.setText("jLabel10");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Code:");

        tfdIdMouvementStock.setEditable(false);

        btnConsulterMouvementStock.setText("...");
        btnConsulterMouvementStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterMouvementStockActionPerformed(evt);
            }
        });

        lblTauxcarte.setText("jLabel10");

        jLabel13.setText("Taux carte:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdIdMouvementStock, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterMouvementStock)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblTauxcarte)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(lblDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfdIdMouvementStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsulterMouvementStock))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(lblTauxcarte)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        Produit produit = new Produit.ProduitBuilder(Integer.parseInt(tfdIdProduit.getText())).build();

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

    public void mouvementStockSelectionne(EntreeStock entreeStock) {
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConsulterMouvementStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterMouvementStockActionPerformed
        if (btnConsulterMouvementStockClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                List<EntreeStock> mouvementsStock = MouvementStockService.getInstance().listerMouvementStockPatType(TypeMouvementStockEnum.ENTREE_STOCK);
                ConsultationMouvementStock consultationMouvementStock = new ConsultationMouvementStock(null, true, mouvementsStock);
                consultationMouvementStock.setFrameAncetre(this);
                consultationMouvementStock.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterMouvementStockActionPerformed

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
//        if (btnConsulterShopClickable) {
//            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            habiliterComposantFormulaire(false);
//
//            ConsultationShop consultationShop = new ConsultationShop(null, true);
//            consultationShop.setFrameAncetre(this);
//            consultationShop.setVisible(true);
//
//            habiliterComposantFormulaire(true);
//            setCursor(Cursor.getDefaultCursor());
//        }
    }//GEN-LAST:event_btnConsulterFournisseurActionPerformed

    public void shopSelectionne(Shop shop) {
//        if (shop != null) {
//            modeEdition = true;
//            btnExclure.setEnabled(true);
//
//            codeShop = shop.getCode();
//            tfdNom.setText(shop.getNom());
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

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
//        if (btnConsulterShopClickable) {
//            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            habiliterComposantFormulaire(false);
//
//            ConsultationShop consultationShop = new ConsultationShop(null, true);
//            consultationShop.setFrameAncetre(this);
//            consultationShop.setVisible(true);
//
//            habiliterComposantFormulaire(true);
//            setCursor(Cursor.getDefaultCursor());
//        }
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    public void shopSeelectionne(Shop shop) {
//        if (shop != null) {
//            modeEdition = true;
//            btnExclure.setEnabled(true);
//
//            codeShop = shop.getCode();
//            tfdNom.setText(shop.getNom());
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

    private void effacerFormulaire() {
        tfdIdMouvementStock.setText("");
        tfdIdMouvementStock.requestFocus();
        lblDate.setText("");
        lblTauxcarte.setText("");
        tfdIdFournisseur.setText("");
        lblInfoFournisseur.setText("");
//        lblResponsableFournisseur.setText("");
        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        tfdPrixAchatUSD.setText("");
        tfdPrixAchatFC.setText("");
        lblProduitStockActuel.setText("");
        spnQuantiteProduit.setToolTipText("0");
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
//        btnConsulterShopClickable = hcf;
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
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterFournisseur;
    private javax.swing.JButton btnConsulterMouvementStock;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDescriptionProduit;
    private javax.swing.JLabel lblInfoFournisseur;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblTauxcarte;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblEntreeStock;
    private javax.swing.JTextField tfdIdFournisseur;
    private javax.swing.JTextField tfdIdMouvementStock;
    private javax.swing.JTextField tfdIdProduit;
    private javax.swing.JTextField tfdPrixAchatFC;
    private javax.swing.JTextField tfdPrixAchatUSD;
    // End of variables declaration//GEN-END:variables
}
