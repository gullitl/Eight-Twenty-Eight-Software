package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
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
    private boolean btnConsulterCategorieProduitClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    public RegistreCategorieProduit() {
        initComponents();
        effacerFormulaire();
    }

    public boolean isModeEdition() {
        return modeEdition;
    }

    public void setModeEdition(boolean modeEdition) {
        this.modeEdition = modeEdition;
    }

    public boolean isBtnConsulterCategorieProduitClickable() {
        return btnConsulterCategorieProduitClickable;
    }

    public void setBtnConsulterCategorieProduitClickable(boolean btnConsulterCategorieProduitClickable) {
        this.btnConsulterCategorieProduitClickable = btnConsulterCategorieProduitClickable;
    }

    public boolean isBtnEnregistrerClickable() {
        return btnEnregistrerClickable;
    }

    public void setBtnEnregistrerClickable(boolean btnEnregistrerClickable) {
        this.btnEnregistrerClickable = btnEnregistrerClickable;
    }

    public boolean isBtnAnnulerClickable() {
        return btnAnnulerClickable;
    }

    public void setBtnAnnulerClickable(boolean btnAnnulerClickable) {
        this.btnAnnulerClickable = btnAnnulerClickable;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        lblChargement = new javax.swing.JLabel();
        tfdCode = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Catégorie de Produit");

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
        btnConsulterCategorieProduit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnConsulterCategorieProduitKeyReleased(evt);
            }
        });

        lblChargement.setText("Chargement...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblChargement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnConsulterCategorieProduit)
                    .addComponent(tfdCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChargement))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CategorieProduit categorieProduit = new CategorieProduitBuilder(Integer.parseInt(tfdCode.getText()))
                .description(tfdDescription.getText())
                .descriptionAbregee(tfdDescriptionAbregee.getText())
                .observation(txaObservation.getText())
                .build();

        try {
            if (CategorieProduitService.getInstance().enregistrerCategorieProduit(categorieProduit)) {
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant la Catégorie Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        if (!isBtnConsulterCategorieProduitClickable()) {
            return;
        }
        consulterCategorieProduit();
    }//GEN-LAST:event_btnConsulterCategorieProduitActionPerformed

    private void btnConsulterCategorieProduitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!isBtnConsulterCategorieProduitClickable()) {
                return;
            }
            consulterCategorieProduit();
        }
    }//GEN-LAST:event_btnConsulterCategorieProduitKeyReleased

    private void consulterCategorieProduit() {
        ConsultationCategorieProduit consultationCategorieProduit = new ConsultationCategorieProduit(null, true);
        consultationCategorieProduit.setFrameAncetre(this);
        consultationCategorieProduit.setVisible(true);
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        if (categorieProduit == null) {
            return;
        }

        setModeEdition(true);
        tfdCode.setText("" + categorieProduit.getCode());
        tfdDescription.setText(categorieProduit.getDescription());
        tfdDescriptionAbregee.setText(categorieProduit.getDescriptionAbregee());
        txaObservation.setText(categorieProduit.getObservation());
        btnEnregistrer.setText("ACTUALISER");
    }

    private void effacerFormulaire() {
        tfdCode.setEnabled(true);
        tfdCode.setText("");
        tfdCode.requestFocus();
        tfdDescription.setText("");
        tfdDescriptionAbregee.setText("");
        txaObservation.setText("");
        btnEnregistrer.setText("ENREGISTRER");

        chargerCodeSubsequentAuTfdCode();

    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdDescription.setEditable(hcf);
        tfdDescriptionAbregee.setEditable(hcf);
        txaObservation.setEditable(hcf);
        setBtnConsulterCategorieProduitClickable(hcf);
        setBtnEnregistrerClickable(hcf);
        setBtnAnnulerClickable(hcf);
        lblChargement.setVisible(!hcf);
    }

    private void chargerCodeSubsequentAuTfdCode() {

        new Thread() {
            @Override
            public void run() {
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    habiliterComposantFormulaire(false);
                    tfdCode.setText(String.valueOf(CategorieProduitService.getInstance()
                            .selectionnerCodeCategorieProduitSubsequent()));
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegistreCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
                }
                habiliterComposantFormulaire(true);
                setCursor(Cursor.getDefaultCursor());
            }
        }.start();
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
    private javax.swing.JLabel lblChargement;
    private javax.swing.JTextField tfdCode;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdDescriptionAbregee;
    private javax.swing.JTextArea txaObservation;
    // End of variables declaration//GEN-END:variables
}
