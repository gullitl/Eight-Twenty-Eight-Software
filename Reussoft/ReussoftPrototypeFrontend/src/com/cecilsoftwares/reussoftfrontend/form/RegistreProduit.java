package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.ReseauService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCategorieProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationReseau;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import com.cecilsoftwares.reussoftmiddleend.util.DecimalFormatter;
import java.awt.Cursor;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreProduit extends JInternalFrame {

    private String idProduit;
    private String idReseau;
    private String idCategorieProduit;
    private String idPrixAchatProduit;

    private PrixAchatProduit prixAchatProduit;
    private boolean modeEdition;
    private boolean btnConsulterProduitClickable;
    private boolean btnConsulterReseauClickable;
    private boolean btnConsulterCategorieProduitClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public RegistreProduit() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnConsulterProduit = new javax.swing.JButton();
        tfdDescription = new javax.swing.JTextField();
        tfdPrixAchat = new javax.swing.JTextField();
        chbActiver = new javax.swing.JCheckBox();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnConsulterReseau = new javax.swing.JButton();
        tfdNomReseau = new javax.swing.JTextField();
        tfdDescriptionCategorieProduit = new javax.swing.JTextField();
        btnConsulterCategorieProduit = new javax.swing.JButton();
        btnExclure = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfdQuantiteStockMinimum = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Produit");

        jLabel2.setText("Description:");

        jLabel3.setText("Réseau:");

        jLabel4.setText("Prix d'achat:");

        jLabel6.setText("Categorie Produit:");

        btnConsulterProduit.setText("...");
        btnConsulterProduit.setFocusable(false);
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        chbActiver.setText("Activer");

        btnEffacerFormulaire.setText("EFFACER");
        btnEffacerFormulaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerFormulaireActionPerformed(evt);
            }
        });

        btnEnregistrer.setText("ENREGISTRER");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btnConsulterReseau.setText("...");
        btnConsulterReseau.setFocusable(false);
        btnConsulterReseau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterReseauActionPerformed(evt);
            }
        });

        tfdNomReseau.setEditable(false);

        tfdDescriptionCategorieProduit.setEditable(false);

        btnConsulterCategorieProduit.setText("...");
        btnConsulterCategorieProduit.setFocusable(false);
        btnConsulterCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCategorieProduitActionPerformed(evt);
            }
        });

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        jLabel1.setText("Quantité stock minimum:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbActiver)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdDescriptionCategorieProduit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterCategorieProduit))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdNomReseau)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterReseau))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdDescription)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterProduit))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(tfdQuantiteStockMinimum, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProduit))
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNomReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterReseau))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescriptionCategorieProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCategorieProduit))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdQuantiteStockMinimum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(chbActiver)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (!btnEnregistrerClickable || !isInformationObligatoiresRemplies()) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        Produit produit = new Produit(idProduit);
        produit.setDescription(tfdDescription.getText());

        produit.setReseau(new Reseau(idReseau));
        produit.setCategorieProduit(new CategorieProduit(idCategorieProduit));
        produit.setActive(modeEdition ? chbActiver.isSelected() : true);

        try {

            if (modeEdition && prixAchatProduit.getValeurUSD().equals(new BigDecimal(tfdPrixAchat.getText()))) {
                prixAchatProduit = null;

            } else {
                prixAchatProduit = new PrixAchatProduit(idPrixAchatProduit);
                prixAchatProduit.setValeurUSD(new BigDecimal(tfdPrixAchat.getText()));
                prixAchatProduit.setDateHeure(new Date());
            }

            produit.setPrixAchatProduit(prixAchatProduit);
            produit.setQuantiteStockMininum(new BigDecimal(tfdQuantiteStockMinimum.getText()));

            if (ProduitService.getInstance().enregistrerProduit(produit)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistreProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (!btnConsulterProduitClickable) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {

            ConsultationProduit consultationProduit = new ConsultationProduit(null, true, ProduitService.getInstance()
                    .listerTousLesProduits());
            consultationProduit.setFrameAncetre(this);
            consultationProduit.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnConsulterReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterReseauActionPerformed
        if (!btnConsulterReseauClickable) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        ConsultationReseau consultationReseau;
        try {
            consultationReseau = new ConsultationReseau(null, true, ReseauService.getInstance().listerTousLesReseaux());
            consultationReseau.setFrameAncetre(this);
            consultationReseau.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistreProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterReseauActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        if (!btnConsulterCategorieProduitClickable) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);
        try {
            ConsultationCategorieProduit consultationCategorieProduit = new ConsultationCategorieProduit(null, true, CategorieProduitService.getInstance()
                    .listerTousLesCategorieProduits());
            consultationCategorieProduit.setFrameAncetre(this);
            consultationCategorieProduit.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterCategorieProduitActionPerformed

    private void btnExclureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExclureActionPerformed
        Object[] options = {"Exclure", "Annuler"};
        int n = JOptionPane.showOptionDialog(this,
                "Êtes-vous sûr de vouloir exclure définitivement ce produit?",
                "Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title

        if (n == 0) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);
            try {
                ProduitService.getInstance().exclureProduit(idProduit);
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Exclusion effectuée avec succès");
            } catch (SQLException ex) {
                StringBuilder notification = new StringBuilder("Une faille est survenue lors de l'exclusion du produit :(");
                switch (ex.getErrorCode()) {
                    case 1451:
                        notification.append("\n\nCe produit est utilisé par un autre registre!");
                        break;
                    default:
                        break;
                }
                JOptionPane.showMessageDialog(null, notification);
                habiliterComposantFormulaire(true);
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion du produit :(");
                habiliterComposantFormulaire(true);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    public void produitSelectionne(Produit produit) {
        if (produit == null) {
            return;
        }

        idProduit = produit.getId();
        tfdDescription.setText(produit.getDescription());

        idReseau = produit.getReseau().getId();
        tfdNomReseau.setText(produit.getReseau().getNom());

        idCategorieProduit = produit.getCategorieProduit().getId();
        tfdDescriptionCategorieProduit.setText(produit.getCategorieProduit().getDescription());

        idPrixAchatProduit = produit.getPrixAchatProduit().getId();
        tfdPrixAchat.setText(DecimalFormatter.getInstance().getFormattedValue(produit.getPrixAchatProduit().getValeurUSD()));
        prixAchatProduit = produit.getPrixAchatProduit();

        chbActiver.setVisible(true);
        chbActiver.setSelected(produit.isActive());
        btnEnregistrer.setText("ACTUALISER");
        modeEdition = true;
        btnExclure.setEnabled(true);
    }

    public void reseauSelectionne(Reseau reseau) {
        if (reseau == null) {
            return;
        }

        tfdNomReseau.setText(reseau.getNom());
        idReseau = reseau.getId();
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        if (categorieProduit == null) {
            return;
        }

        tfdDescriptionCategorieProduit.setText(categorieProduit.getDescription());
        idCategorieProduit = categorieProduit.getId();
    }

    private void effacerFormulaire() {
        idProduit = "";
        tfdDescription.setText("");
        tfdDescription.requestFocus();
        tfdNomReseau.setText("");
        tfdPrixAchat.setText("");
        tfdDescriptionCategorieProduit.setText("");
        chbActiver.setVisible(false);
        chbActiver.setSelected(true);
        modeEdition = false;
        btnEnregistrer.setText("ENREGISTRER");
        habiliterComposantFormulaire(true);
        btnExclure.setEnabled(false);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdDescription.setEditable(hcf);
        tfdPrixAchat.setEditable(hcf);
        btnConsulterProduitClickable = hcf;
        btnConsulterReseauClickable = hcf;
        btnConsulterCategorieProduitClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnExclureClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdDescription.getText().isEmpty()) {
            notification.append("\nDescription");
            nio.add(1);
        }
        if (tfdNomReseau.getText().isEmpty()) {
            notification.append("\nRéseau");
            nio.add(2);
        }
        if (tfdDescriptionCategorieProduit.getText().isEmpty()) {
            notification.append("\nCatégorie de Produit");
            nio.add(3);
        }

        if (tfdPrixAchat.getText().isEmpty()) {
            notification.append("\nPrix d'achat USD");
            nio.add(4);
        }
        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdDescription.requestFocus();
                    break;

                case 2:
                    tfdNomReseau.requestFocus();
                    break;
                case 3:
                    tfdDescriptionCategorieProduit.requestFocus();
                    break;

                case 4:
                    tfdPrixAchat.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulterCategorieProduit;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnConsulterReseau;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JCheckBox chbActiver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdDescriptionCategorieProduit;
    private javax.swing.JTextField tfdNomReseau;
    private javax.swing.JTextField tfdPrixAchat;
    private javax.swing.JTextField tfdQuantiteStockMinimum;
    // End of variables declaration//GEN-END:variables
}
