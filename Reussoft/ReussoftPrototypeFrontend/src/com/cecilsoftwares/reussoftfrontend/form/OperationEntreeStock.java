package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.EntreeStockService;
import com.cecilsoftwares.reussoftbackend.service.FournisseurService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationFournisseur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock.ItemEntreeStockBuilder;
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
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class OperationEntreeStock extends JInternalFrame {

    private int idEntreeStock;
    private boolean modeEdition;
    private boolean modeEditionFournisseur;
    private boolean btnConsulterMouvementStockClickable;
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
        jLabel16 = new javax.swing.JLabel();
        btnAjouterProduit = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsEntreeStock = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        lblNombreItemEntreeStock = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfdIdEntreeStock = new javax.swing.JTextField();
        btnConsulterEntreeStock = new javax.swing.JButton();
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

        jButton2.setText("<-");

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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tfdIdProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnConsulterProduit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDescriptionProduit)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProduitStockActuel)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAjouterProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreItemEntreeStock)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(jButton2))
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

        jLabel9.setText("Date:");

        lblDate.setText("jLabel10");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Code:");

        tfdIdEntreeStock.setEditable(false);

        btnConsulterEntreeStock.setText("...");
        btnConsulterEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterEntreeStockActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdIdEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterEntreeStock)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)))
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
                        .addComponent(tfdIdEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsulterEntreeStock))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(lblTauxcarte)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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

        int teste = Integer.parseInt(tfdIdEntreeStock.getText());

        EntreeStock entreeStock = new EntreeStockBuilder(teste).build();

        ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produitSelectionne)
                .quantiteProduit(new BigDecimal("4"))
                .build();

        itemsEntreeStock.add(itemEntreeStock);

        dataRows[0] = produitSelectionne.getCode();
        dataRows[1] = itemEntreeStock.getProduit().getDescription();
        dataRows[2] = itemEntreeStock.getQuantiteProduit();
        dataRows[3] = new StringBuilder(itemEntreeStock.getProduit().getPrixAchatUSD().toString()).append(" $ + ")
                .append(itemEntreeStock.getProduit().getPrixAchatFC().toString()).append(" FC");
        dataRows[4] = produitSelectionne.getDescription();
        defaultTableModel.addRow(dataRows);

        String formeNombre = itemsEntreeStock.size() > 1 ? "Items" : "Item";
        lblNombreItemEntreeStock.setText(itemsEntreeStock.size() + " " + formeNombre);
    }//GEN-LAST:event_btnAjouterProduitActionPerformed

    private void btnConsulterEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterEntreeStockActionPerformed
        if (btnConsulterMouvementStockClickable) {
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

    public void setProduitSelectionne(Produit produit) {
        if (produit != null) {
            produitSelectionne = produit;
            tfdIdProduit.setText(String.valueOf(produitSelectionne.getCode()));
            lblDescriptionProduit.setText(produitSelectionne.getDescription());
        }
    }

    private void effacerFormulaire() {

        try {
            tfdIdEntreeStock.setText(String.valueOf(EntreeStockService.getInstance().selectionnerCodeEntreeStockSubsequente()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        }

        tfdIdEntreeStock.requestFocus();
        lblDate.setText("");
        lblTauxcarte.setText("");
        tfdIdFournisseur.setText("");
        lblInfoFournisseur.setText("");
        tfdIdProduit.setText("");
        lblDescriptionProduit.setText("");
        lblProduitStockActuel.setText("");
        spnQuantiteProduit.setToolTipText("0");

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
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel lblNombreItemEntreeStock;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblTauxcarte;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblItemsEntreeStock;
    private javax.swing.JTextField tfdIdEntreeStock;
    private javax.swing.JTextField tfdIdFournisseur;
    private javax.swing.JTextField tfdIdProduit;
    // End of variables declaration//GEN-END:variables
}
