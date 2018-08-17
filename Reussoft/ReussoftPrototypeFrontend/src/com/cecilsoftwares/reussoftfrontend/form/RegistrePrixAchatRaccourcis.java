package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.PrixAchatProduitService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreClient;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import java.awt.Cursor;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistrePrixAchatRaccourcis extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private Produit produit;

    /**
     * @param parent
     * @param modal
     * @param produit
     */
    public RegistrePrixAchatRaccourcis(java.awt.Frame parent, boolean modal, Produit produit) {
        super(parent, modal);
        initComponents();

        this.produit = produit;
        selectionnerProduit(this.produit);

    }

    private void selectionnerProduit(Produit produit) {
        tfdDescription.setText(produit.getReseau().getNom() + " - " + produit.getDescription());
        tfdPrixAchat.setText(produit.getPrixAchatProduit().getValeurUSD().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnActualiser = new javax.swing.JButton();
        tfdPrixAchat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfdDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualisation de Prix d'Achat");
        setResizable(false);

        btnActualiser.setText("ACTUALISER");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });

        jLabel4.setText("Prix d'achat:");

        jLabel2.setText("Description:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnActualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        if (frameAncetre instanceof OperationEntreeStock) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            tfdDescription.setEditable(false);
            tfdPrixAchat.setEditable(false);

            try {

                PrixAchatProduit prixAchatProduit = new PrixAchatProduit("");
                prixAchatProduit.setValeurUSD(new BigDecimal(tfdPrixAchat.getText()));
                produit.setPrixAchatProduit(prixAchatProduit);

                prixAchatProduit.setId(PrixAchatProduitService.getInstance().enregistrerPrixAchatProduit(produit));

                OperationEntreeStock operationEntreeStock = (OperationEntreeStock) frameAncetre;
                operationEntreeStock.prixActualise(prixAchatProduit);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistrePrixAchatRaccourcis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrePrixAchatRaccourcis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(RegistrePrixAchatRaccourcis.class.getName()).log(Level.SEVERE, null, ex);
            }

            setCursor(Cursor.getDefaultCursor());

        }

        this.dispose();
    }//GEN-LAST:event_btnActualiserActionPerformed

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdPrixAchat;
    // End of variables declaration//GEN-END:variables
}
