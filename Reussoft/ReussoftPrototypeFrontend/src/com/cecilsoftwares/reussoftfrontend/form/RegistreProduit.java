package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCategorieProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationReseau;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau.ReseauBuilder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreProduit extends JInternalFrame {

    public RegistreProduit() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfdCode = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        tfdDescription = new javax.swing.JTextField();
        tfdPrixAchatUSD = new javax.swing.JTextField();
        tfdPrixAchatFC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaObservation = new javax.swing.JTextArea();
        chbActiver = new javax.swing.JCheckBox();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnConsulterReseau = new javax.swing.JButton();
        tfdIdReseau = new javax.swing.JTextField();
        lblDescriptionReseau = new javax.swing.JLabel();
        tfdIdCategorieproduit = new javax.swing.JTextField();
        btnConsulterCategorieProduit = new javax.swing.JButton();
        lblDescriptionCategorieProduit = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Produit");

        jLabel1.setText("Code:");

        jLabel2.setText("Description:");

        jLabel3.setText("Reseau:");

        jLabel4.setText("Prix d'achat (USD):");

        jLabel5.setText("Prix d'achat (FC):");

        jLabel6.setText("Categorie Produit:");

        jLabel7.setText("Observation:");

        btnConsulterProduit.setText("...");
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        txaObservation.setColumns(20);
        txaObservation.setRows(5);
        jScrollPane1.setViewportView(txaObservation);

        chbActiver.setText("Activer");

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

        btnConsulterReseau.setText("...");
        btnConsulterReseau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterReseauActionPerformed(evt);
            }
        });

        lblDescriptionReseau.setText("jLabel8");

        btnConsulterCategorieProduit.setText("...");
        btnConsulterCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCategorieProduitActionPerformed(evt);
            }
        });

        lblDescriptionCategorieProduit.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterProduit))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdIdReseau, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterReseau)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblDescriptionReseau))
                                .addComponent(jLabel6)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdIdCategorieproduit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterCategorieProduit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblDescriptionCategorieProduit)))
                            .addGap(134, 134, 134)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(chbActiver)
                    .addComponent(jLabel1))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProduit))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterReseau)
                    .addComponent(lblDescriptionReseau))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdCategorieproduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCategorieProduit)
                    .addComponent(lblDescriptionCategorieProduit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdPrixAchatUSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfdPrixAchatFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(chbActiver)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        Reseau reseau = new ReseauBuilder(Integer.parseInt(tfdIdReseau.getText())).build();
        CategorieProduit categorieProduit = new CategorieProduitBuilder(Integer.parseInt(tfdIdCategorieproduit.getText())).build();

        Produit produit = new ProduitBuilder(Integer.parseInt(tfdCode.getText()))
                .description(tfdDescription.getText())
                .reseau(reseau)
                .categorieProduit(categorieProduit)
                .observation(txaObservation.getText())
                .active(true)
                .build();

        try {
            if (ProduitService.getInstance().enregistrerProduit(produit)) {
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        ConsultationProduit consultationProduit = new ConsultationProduit(null, true);
        consultationProduit.setFrameAncetre(this);
        consultationProduit.setVisible(true);
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnConsulterReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterReseauActionPerformed
        ConsultationReseau consultationReseau = new ConsultationReseau(null, true);
        consultationReseau.setFrameAncetre(this);
        consultationReseau.setVisible(true);
    }//GEN-LAST:event_btnConsulterReseauActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        ConsultationCategorieProduit consultationCategorieProduit = new ConsultationCategorieProduit(null, true);
        consultationCategorieProduit.setFrameAncetre(this);
        consultationCategorieProduit.setVisible(true);
    }//GEN-LAST:event_btnConsulterCategorieProduitActionPerformed

    public void produitSelectionne(Produit produit) {
        tfdCode.setText(String.valueOf(produit.getCode()));
        tfdDescription.setText(produit.getDescription());
        tfdIdReseau.setText(String.valueOf(produit.getReseau().getCode()));
        lblDescriptionReseau.setText(produit.getReseau().getNom());
        tfdIdCategorieproduit.setText(String.valueOf(produit.getCategorieProduit().getCode()));
        lblDescriptionCategorieProduit.setText(produit.getCategorieProduit().getDescription());
    }

    public void reseauSelectionne(Reseau reseau) {
        tfdIdReseau.setText(String.valueOf(reseau.getCode()));
        lblDescriptionReseau.setText(reseau.getNom());
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        tfdIdCategorieproduit.setText(String.valueOf(categorieProduit.getCode()));
        lblDescriptionCategorieProduit.setText(categorieProduit.getDescription());
    }

    private void effacerFormulaire() {
        tfdCode.setText("");
        tfdCode.requestFocus();
        tfdDescription.setText("");
        tfdIdReseau.setText("");
        lblDescriptionReseau.setText("");
        tfdPrixAchatUSD.setText("");
        tfdPrixAchatFC.setText("");
        tfdIdCategorieproduit.setText("");
        lblDescriptionCategorieProduit.setText("");
        txaObservation.setText("");
        chbActiver.setVisible(false);
        chbActiver.setSelected(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterCategorieProduit;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnConsulterReseau;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JCheckBox chbActiver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescriptionCategorieProduit;
    private javax.swing.JLabel lblDescriptionReseau;
    private javax.swing.JTextField tfdCode;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdIdCategorieproduit;
    private javax.swing.JTextField tfdIdReseau;
    private javax.swing.JTextField tfdPrixAchatFC;
    private javax.swing.JTextField tfdPrixAchatUSD;
    private javax.swing.JTextArea txaObservation;
    // End of variables declaration//GEN-END:variables
}
