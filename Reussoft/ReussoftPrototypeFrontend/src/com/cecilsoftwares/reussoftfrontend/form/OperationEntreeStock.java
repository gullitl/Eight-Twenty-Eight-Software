package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.EntreeStockService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.MouvementStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import java.awt.Cursor;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class OperationEntreeStock extends JInternalFrame {

    private int codeEntreeStock;
    private boolean modeEdition;
    private boolean btnConsulterEntreeStockClickable;
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
        jLabel3 = new javax.swing.JLabel();
        lblEntrepriseFournisseur = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblResponsableFournisseur = new javax.swing.JLabel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfdCode = new javax.swing.JTextField();
        btnConsulterEntreeStock = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        spnQuantiteProduit = new javax.swing.JSpinner();
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

        jLabel3.setText("Entreprise:");

        lblEntrepriseFournisseur.setText("jLabel5");

        jLabel6.setText("Responsable:");

        lblResponsableFournisseur.setText("jLabel5");

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
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(lblEntrepriseFournisseur)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addComponent(lblResponsableFournisseur)))
                .addContainerGap(147, Short.MAX_VALUE))
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblEntrepriseFournisseur)
                        .addComponent(jLabel6)
                        .addComponent(lblResponsableFournisseur)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProduitStockActuel)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfdIdProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterProduit)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescriptionProduit)))
                .addContainerGap(273, Short.MAX_VALUE))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProduitStockActuel)
                            .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fournisseur", "Produit", "Quantité"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

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

        tfdCode.setEditable(false);

        btnConsulterEntreeStock.setText("...");
        btnConsulterEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterEntreeStockActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Quantité:");

        lblTauxcarte.setText("jLabel10");

        jLabel13.setText("Taux carte:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel4)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterEntreeStock))
                            .addComponent(jLabel15))
                        .addGap(400, 400, 400)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblTauxcarte))))
                .addContainerGap(25, Short.MAX_VALUE))
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
                        .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsulterEntreeStock))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(lblTauxcarte)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        Produit produit = new Produit.ProduitBuilder(Integer.parseInt(tfdIdProduit.getText())).build();

        EntreeStock entreeStock = new EntreeStock.EntreeStockBuilder(Integer.parseInt(tfdCode.getText()))
                .produit(produit)
                .prixAchatUSD(new BigDecimal(tfdPrixAchatUSD.getText()))
                .prixAchatFC(new BigDecimal(tfdPrixAchatFC.getText()))
                .quantiteProduit(new BigDecimal(spnQuantiteProduit.getToolTipText()))
                .build();

        try {
            if (EntreeStockService.getInstance().enregistrerEntreeStock(entreeStock)) {
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant l'Entree de Stock");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    public void mouvementStockSelectionne(MouvementStock entreeStock) {
        if (!isInformationObligatoiresRemplies()) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        Fournisseur fournisseur = new FournisseurBuilder(Integer.parseInt(tfdIdFournisseur.getText())).build();
        Produit produit = new ProduitBuilder(Integer.parseInt(tfdIdProduit.getText())).build();
        EntreeStock shop = new EntreeStockBuilder(codeEntreeStock)
                .fournisseur(fournisseur)
                .produit(produit)
                .prixAchatFC(new BigDecimal(tfdPrixAchatFC.getText()))
                .prixAchatUSD(new BigDecimal(tfdPrixAchatUSD.getText()))
                .nom(tfdNom.getText())
                .adresse(adresse)
                .active(modeEdition ? chbActiver.isSelected() : true)
                .build();

        try {
            if (ShopService.getInstance().enregistrerShop(shop)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le nouveau Shop");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConsulterEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterEntreeStockActionPerformed
        if (btnConsulterEntreeStockClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationEntreeStock consultationEntreeStock = new ConsultationEntreeStock(null, true);
            consultationEntreeStock.setFrameAncetre(this);
            consultationEntreeStock.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterEntreeStockActionPerformed

    public void EntreeStockSelectionnee(EntreeStock EntreeStock) {
        if (shop != null) {
            modeEdition = true;
            btnExclure.setEnabled(true);

            codeShop = shop.getCode();
            tfdNom.setText(shop.getNom());

            String[] adresse = shop.getAdresse().split("@");
            tfdAvenue.setText(adresse[0]);
            tfdNumero.setText(adresse[1]);
            tfdQuartier.setText(adresse[2]);
            tfdCommune.setText(adresse[3]);
            switch (adresse[4]) {
                case "Kinshasa":
                    cbxProvince.setSelectedIndex(0);
                    break;
                case "Kasaï":
                    cbxProvince.setSelectedIndex(1);
                    break;
                case "Kongo-Central":
                    cbxProvince.setSelectedIndex(2);
                    break;
            }
            tfdDistrict.setText(adresse[5]);

            chbActiver.setVisible(true);
            chbActiver.setSelected(shop.isActive());
            btnEnregistrer.setText("ACTUALISER");
        }
    }

    private void btnConsulterFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterFournisseurActionPerformed
        if (btnConsulterShopClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationShop consultationShop = new ConsultationShop(null, true);
            consultationShop.setFrameAncetre(this);
            consultationShop.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterFournisseurActionPerformed

    public void shopSelectionne(Shop shop) {
        if (shop != null) {
            modeEdition = true;
            btnExclure.setEnabled(true);

            codeShop = shop.getCode();
            tfdNom.setText(shop.getNom());

            String[] adresse = shop.getAdresse().split("@");
            tfdAvenue.setText(adresse[0]);
            tfdNumero.setText(adresse[1]);
            tfdQuartier.setText(adresse[2]);
            tfdCommune.setText(adresse[3]);
            switch (adresse[4]) {
                case "Kinshasa":
                    cbxProvince.setSelectedIndex(0);
                    break;
                case "Kasaï":
                    cbxProvince.setSelectedIndex(1);
                    break;
                case "Kongo-Central":
                    cbxProvince.setSelectedIndex(2);
                    break;
            }
            tfdDistrict.setText(adresse[5]);

            chbActiver.setVisible(true);
            chbActiver.setSelected(shop.isActive());
            btnEnregistrer.setText("ACTUALISER");
        }
    }

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (btnConsulterShopClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationShop consultationShop = new ConsultationShop(null, true);
            consultationShop.setFrameAncetre(this);
            consultationShop.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    public void shopSelectionne(Shop shop) {
        if (shop != null) {
            modeEdition = true;
            btnExclure.setEnabled(true);

            codeShop = shop.getCode();
            tfdNom.setText(shop.getNom());

            String[] adresse = shop.getAdresse().split("@");
            tfdAvenue.setText(adresse[0]);
            tfdNumero.setText(adresse[1]);
            tfdQuartier.setText(adresse[2]);
            tfdCommune.setText(adresse[3]);
            switch (adresse[4]) {
                case "Kinshasa":
                    cbxProvince.setSelectedIndex(0);
                    break;
                case "Kasaï":
                    cbxProvince.setSelectedIndex(1);
                    break;
                case "Kongo-Central":
                    cbxProvince.setSelectedIndex(2);
                    break;
            }
            tfdDistrict.setText(adresse[5]);

            chbActiver.setVisible(true);
            chbActiver.setSelected(shop.isActive());
            btnEnregistrer.setText("ACTUALISER");
        }
    }

    private void effacerFormulaire() {
        tfdCode.setText("");
        tfdCode.requestFocus();
        lblDate.setText("");
        lblTauxcarte.setText("");
        tfdIdFournisseur.setText("");
        lblEntrepriseFournisseur.setText("");
        lblResponsableFournisseur.setText("");
        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        tfdPrixAchatUSD.setText("");
        tfdPrixAchatFC.setText("");
        lblProduitStockActuel.setText("");
        spnQuantiteProduit.setToolTipText("0");
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdNom.setEditable(hcf);
        tfdAvenue.setEditable(hcf);
        tfdNumero.setEditable(hcf);
        tfdQuartier.setEditable(hcf);
        tfdCommune.setEditable(hcf);
        tfdDistrict.setEditable(hcf);
        chbActiver.setEnabled(hcf);
        cbxProvince.setEnabled(hcf);
        btnConsulterShopClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdNom.getText().isEmpty()) {
            notification.append("\nNom");
            nio.add(1);
        }
        if (tfdAvenue.getText().isEmpty()) {
            notification.append("\nAvenue");
            nio.add(2);
        }
        if (tfdNumero.getText().isEmpty()) {
            notification.append("\nNuméro");
            nio.add(3);
        }

        if (tfdQuartier.getText().isEmpty()) {
            notification.append("\nQuartier");
            nio.add(4);
        }
        if (tfdCommune.getText().isEmpty()) {
            notification.append("\nCommune");
            nio.add(5);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdNom.requestFocus();
                    break;

                case 2:
                    tfdAvenue.requestFocus();
                    break;
                case 3:
                    tfdNumero.requestFocus();
                    break;

                case 4:
                    tfdQuartier.requestFocus();
                    break;
                case 5:
                    tfdCommune.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterEntreeStock;
    private javax.swing.JButton btnConsulterFournisseur;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDescriptionProduit;
    private javax.swing.JLabel lblEntrepriseFournisseur;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblResponsableFournisseur;
    private javax.swing.JLabel lblTauxcarte;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTextField tfdCode;
    private javax.swing.JTextField tfdIdFournisseur;
    private javax.swing.JTextField tfdIdProduit;
    private javax.swing.JTextField tfdPrixAchatFC;
    private javax.swing.JTextField tfdPrixAchatUSD;
    // End of variables declaration//GEN-END:variables
}
