package com.cecilsoftwares.reussoftfrontend.form.registres;

import com.cecilsoftwares.reussoftbackend.service.PrixVenteProduitService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationShop;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduitShop;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
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
public class RegistrePrixVenteProduit extends JInternalFrame {

    private List<PrixVenteProduitShop> prixVenteProduitShops;
    private String idProduit;
    private String idShop;

    private boolean modeEditionPrixVenteProduitShop;
    private boolean btnConsulterProduitClickable;
    private boolean btnConsulterShopClickable;
    private boolean btnAjouterProduitClickable;
    private boolean btnEffacerChampsProduitsClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    public RegistrePrixVenteProduit() {
        initComponents();
        effacerFormulaire();

        defaultTableModel = (DefaultTableModel) tblPrixVenteProduitShop.getModel();
        dataRows = new Object[2];
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnConsulterProduit = new javax.swing.JButton();
        tfdDescriptionProduit = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        tfdNomShop = new javax.swing.JTextField();
        btnConsulterShop = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnAjouterShop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrixVenteProduitShop = new javax.swing.JTable();
        btnEffacerChampsShop = new javax.swing.JButton();
        lblNombreShop = new javax.swing.JLabel();
        tfdValeur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Prix de vente de Produit");

        jLabel3.setText("Produit:");

        btnAnnuler.setText("EFFACER");
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

        btnConsulterProduit.setText("...");
        btnConsulterProduit.setFocusable(false);
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        tfdDescriptionProduit.setEditable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        tfdNomShop.setEditable(false);

        btnConsulterShop.setText("...");
        btnConsulterShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterShopActionPerformed(evt);
            }
        });

        jLabel12.setText("Shop:");

        btnAjouterShop.setText("+");
        btnAjouterShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterShopActionPerformed(evt);
            }
        });

        tblPrixVenteProduitShop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Shop", "Prix de vente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrixVenteProduitShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrixVenteProduitShopMouseClicked(evt);
            }
        });
        tblPrixVenteProduitShop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblPrixVenteProduitShopKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblPrixVenteProduitShop);
        if (tblPrixVenteProduitShop.getColumnModel().getColumnCount() > 0) {
            tblPrixVenteProduitShop.getColumnModel().getColumn(0).setResizable(false);
            tblPrixVenteProduitShop.getColumnModel().getColumn(0).setPreferredWidth(350);
            tblPrixVenteProduitShop.getColumnModel().getColumn(1).setResizable(false);
            tblPrixVenteProduitShop.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        btnEffacerChampsShop.setText("<-");
        btnEffacerChampsShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerChampsShopActionPerformed(evt);
            }
        });

        lblNombreShop.setText("jLabel2");

        jLabel4.setText("Valeur:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAjouterShop, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEffacerChampsShop, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(283, 304, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreShop)
                            .addComponent(jLabel4)
                            .addComponent(tfdValeur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 413, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfdNomShop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterShop)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNomShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterShop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdValeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterShop)
                    .addComponent(btnEffacerChampsShop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreShop)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdDescriptionProduit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterProduit))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescriptionProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProduit))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (!btnEnregistrerClickable || !isInformationObligatoiresRemplies()) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        PrixVenteProduit prixVenteProduit = new PrixVenteProduit();
        prixVenteProduit.setDateHeure(new Date());

        Produit produit = new Produit(idProduit);
        produit.setDescription(tfdDescriptionProduit.getText());
        prixVenteProduit.setProduit(produit);
        prixVenteProduit.setValeurUSD(new BigDecimal(tfdValeur.getText()));

        try {
            if (PrixVenteProduitService.getInstance().enregistrerPrixVenteProduitShop(prixVenteProduit, prixVenteProduitShops)) {
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (btnConsulterShopClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationProduit consultationProduit;
            try {
                consultationProduit = new ConsultationProduit(null, true, ProduitService.getInstance().listerTousLesProduits());
                consultationProduit.setFrameAncetre(this);
                consultationProduit.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegistrePrixVenteProduit.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnConsulterShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterShopActionPerformed
        if (btnConsulterProduitClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {

                ConsultationShop consultationShop = new ConsultationShop(null, true, ShopService.getInstance()
                        .listerTousLesShops());
                consultationShop.setFrameAncetre(this);
                consultationShop.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrixVenteProduit.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterShopActionPerformed

    private void btnAjouterShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterShopActionPerformed

        Shop shop = new Shop(idShop);
        shop.setNom(tfdNomShop.getText());

        PrixVenteProduitShop prixVenteProduitShop = new PrixVenteProduitShop();
        prixVenteProduitShop.setShop(shop);
        prixVenteProduitShop.setValeurUSD(new BigDecimal(tfdValeur.getText()));

        prixVenteProduitShops.add(prixVenteProduitShop);

        chargerTableauShop();
        effacerChampsShop();
    }//GEN-LAST:event_btnAjouterShopActionPerformed

    private void chargerTableauShop() {
        defaultTableModel.setRowCount(0);

        prixVenteProduitShops.forEach(pvps -> {
            dataRows[0] = pvps.getShop().getNom();
            dataRows[1] = pvps.getValeurUSD();

            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = prixVenteProduitShops.size() > 1 ? "Items" : "Item";
        lblNombreShop.setText(prixVenteProduitShops.size() + " " + formeNombre);

    }

    private void effacerChampsShop() {
        tfdNomShop.setText("");
        tfdNomShop.requestFocus();
    }

    private void tblPrixVenteProduitShopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPrixVenteProduitShopKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            boolean exclu = false;

            List<PrixVenteProduitShop> listePrixVenteProduitShop = prixVenteProduitShops;
            int row = tblPrixVenteProduitShop.getSelectedRow();

            for (PrixVenteProduitShop prixVenteProduitShop : listePrixVenteProduitShop) {
                if (prixVenteProduitShop.getShop().getId().equals(prixVenteProduitShops.get(row).getShop().getId())) {
                    prixVenteProduitShops.remove(prixVenteProduitShop);
                    exclu = true;
                    break;
                }
            }

            if (exclu) {
                chargerTableauShop();
            }

        }
    }//GEN-LAST:event_tblPrixVenteProduitShopKeyReleased

    private void btnEffacerChampsShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerChampsShopActionPerformed
        effacerChampsShop();
    }//GEN-LAST:event_btnEffacerChampsShopActionPerformed

    private void tblPrixVenteProduitShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrixVenteProduitShopMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblPrixVenteProduitShop.getSelectedRow();

            PrixVenteProduitShop prixVenteProduitShop;

            prixVenteProduitShop = prixVenteProduitShops.get(row);

//
//            prixVenteProduitShop = prixVenteProduitShops.stream()
//                    .filter(ies -> ies.getShop().getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                    .findFirst().orElse(null);
            if (prixVenteProduitShop != null) {
                tfdNomShop.setText(prixVenteProduitShop.getShop().getNom());
                tfdValeur.setText(prixVenteProduitShop.getValeurUSD().toString());
                modeEditionPrixVenteProduitShop = true;

            }
        }
    }//GEN-LAST:event_tblPrixVenteProduitShopMouseClicked

    public void produitSelectionne(Produit produit) {
        if (produit != null) {
            tfdDescriptionProduit.setText(produit.getDescription());
            idProduit = produit.getId();
            try {
                List<PrixVenteProduit> listePrixVenteProduit = PrixVenteProduitService.getInstance()
                        .selectionnerPrixVenteProduitParIdProduit(produit.getId());

                prixVenteProduitShops.clear();

                listePrixVenteProduit.forEach(pvp -> {
                    PrixVenteProduitShop prixVenteProduitShop = new PrixVenteProduitShop();
                    prixVenteProduitShop.setShop(pvp.getShop());
                    prixVenteProduitShop.setValeurUSD(pvp.getValeurUSD());
                    prixVenteProduitShops.add(prixVenteProduitShop);
                });

                chargerTableauShop();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegistrePrixVenteProduit.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void shopSelectionne(Shop shop) {
        if (shop != null) {
            tfdNomShop.setText(shop.getNom());
            idShop = shop.getId();
        }
    }

    private void effacerFormulaire() {
        tfdDescriptionProduit.setText("");
        tfdNomShop.setText("");
        tfdValeur.setText("");
        habiliterComposantFormulaire(true);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        btnConsulterProduitClickable = hcf;
        btnConsulterShopClickable = hcf;
        tfdValeur.setEditable(hcf);
        btnEnregistrerClickable = hcf;
        btnExclureClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdDescriptionProduit.getText().isEmpty()) {
            notification.append("\nRéseau");
            nio.add(1);
        }
        if (prixVenteProduitShops.isEmpty()) {
            notification.append("\nListe de shops");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdDescriptionProduit.requestFocus();
                    break;
                case 2:
                    tfdNomShop.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterShop;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnConsulterShop;
    private javax.swing.JButton btnEffacerChampsShop;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JTable tblPrixVenteProduitShop;
    private javax.swing.JTextField tfdDescriptionProduit;
    private javax.swing.JTextField tfdNomShop;
    private javax.swing.JTextField tfdValeur;
    // End of variables declaration//GEN-END:variables
}
