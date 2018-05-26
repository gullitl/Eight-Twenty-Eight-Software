package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCategorieProduit;
import com.cecilsoftwares.reussoftfrontend.essential.JCustomTextField;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreCategorieProduit extends JInternalFrame {

    private boolean modeEdition;

    public RegistreCategorieProduit() {

        initComponents();
        tfdCode.setMaximumLength(10);
        tfdCode.setRegexFilter("\\d+");
        annulerEnregistrement();
    }

    public boolean isModeEdition() {
        return modeEdition;
    }

    public void setModeEdition(boolean modeEdition) {
        this.modeEdition = modeEdition;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdCode = new JCustomTextField();
        tfdDescription = new javax.swing.JTextField();
        tfdDescriptionAbregee = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaObservation = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnConsulterCategorieProduit = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Catégorie de Produit");

        tfdCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfdCodeFocusLost(evt);
            }
        });

        txaObservation.setColumns(20);
        txaObservation.setRows(5);
        jScrollPane1.setViewportView(txaObservation);

        jLabel1.setText("Code:");

        jLabel2.setText("Description:");

        jLabel3.setText("Description abregrée:");

        jLabel4.setText("Observation:");

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

        btnConsulterCategorieProduit.setText("...");
        btnConsulterCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCategorieProduitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConsulterCategorieProduit))
                        .addComponent(jLabel3)
                        .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCategorieProduit))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        annulerEnregistrement();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private int selectionnerCodeSubsequent() {
        try {
            return CategorieProduitService.getInstance().selectionnerCodeCategorieProduitSubsequent();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistreCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CategorieProduit categorieProduit = new CategorieProduitBuilder(Integer.parseInt(tfdCode.getText()))
                .description(tfdDescription.getText())
                .descriptionAbregee(tfdDescriptionAbregee.getText())
                .observation(txaObservation.getText())
                .build();

        try {
            if (CategorieProduitService.getInstance().enregistrerCategorieProduit(categorieProduit)) {
                annulerEnregistrement();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant la Catégorie Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ConsultationCategorieProduit consultationCategorieProduit = new ConsultationCategorieProduit(null, true);
        consultationCategorieProduit.setFrameAncetre(this);
        consultationCategorieProduit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterCategorieProduitActionPerformed

    private void tfdCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdCodeFocusLost
        if (tfdCode.getText().isEmpty()) {
            if (btnConsulterCategorieProduit.hasFocus() || btnAnnuler.hasFocus()) {
                return;
            }
            tfdCode.requestFocus();

        } else {
            downloadCategorieProduit();
        }
    }//GEN-LAST:event_tfdCodeFocusLost

    private void downloadCategorieProduit() {
        if (!tfdCode.isEditable()) {
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            CategorieProduit categorieProduit = CategorieProduitService.getInstance()
                    .selectionnerCategorieProduitParCode(Integer.parseInt(tfdCode.getText()));
            tfdCode.setEditable(false);
            if (categorieProduit == null) {
                this.setCursor(Cursor.getDefaultCursor());
                return;
            }
            categorieProduitSelectionne(categorieProduit);
        } catch (SQLException | ClassNotFoundException ex) {
            this.setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        tfdCode.setEditable(false);
        setModeEdition(true);
        tfdCode.setText("" + categorieProduit.getCode());
        tfdDescription.setText(categorieProduit.getDescription());
        tfdDescriptionAbregee.setText(categorieProduit.getDescriptionAbregee());
        txaObservation.setText(categorieProduit.getObservation());
        btnEnregistrer.setText("ACTUALIZER");
    }

    private void annulerEnregistrement() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tfdCode.setEditable(true);
        tfdCode.setText(String.valueOf(selectionnerCodeSubsequent()));
        tfdCode.requestFocus();
        tfdDescription.setText("");
        tfdDescriptionAbregee.setText("");
        txaObservation.setText("");
        this.setCursor(Cursor.getDefaultCursor());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterCategorieProduit;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private JCustomTextField tfdCode;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdDescriptionAbregee;
    private javax.swing.JTextArea txaObservation;
    // End of variables declaration//GEN-END:variables
}
