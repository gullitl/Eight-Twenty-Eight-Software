package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftbackend.service.PrixAchatProduitService;
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

    private int codeProduit;
    private int codePrixVenteProduit;
    private BigDecimal valeurPrixVenteProduitInicial;
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
        tfdIdReseau = new javax.swing.JTextField();
        lblDescriptionReseau = new javax.swing.JLabel();
        tfdIdCategorieProduit = new javax.swing.JTextField();
        btnConsulterCategorieProduit = new javax.swing.JButton();
        lblDescriptionCategorieProduit = new javax.swing.JLabel();
        btnExclure = new javax.swing.JButton();

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

        tfdIdReseau.setEditable(false);

        lblDescriptionReseau.setText("jLabel8");

        tfdIdCategorieProduit.setEditable(false);

        btnConsulterCategorieProduit.setText("...");
        btnConsulterCategorieProduit.setFocusable(false);
        btnConsulterCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCategorieProduitActionPerformed(evt);
            }
        });

        lblDescriptionCategorieProduit.setText("jLabel8");

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdIdReseau, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterReseau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescriptionReseau))
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdIdCategorieProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterCategorieProduit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescriptionCategorieProduit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterProduit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEnregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(chbActiver)))
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
                    .addComponent(tfdIdReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterReseau)
                    .addComponent(lblDescriptionReseau))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdCategorieProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCategorieProduit)
                    .addComponent(lblDescriptionCategorieProduit))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPrixAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chbActiver)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (isInformationObligatoiresRemplies()) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            Produit produit = new Produit(codeProduit);
            produit.setDescription(tfdDescription.getText());

            Reseau reseau = new Reseau(Integer.parseInt(tfdIdReseau.getText()));

            produit.setReseau(reseau);

            CategorieProduit categorieProduit = new CategorieProduit(Integer.parseInt(tfdIdCategorieProduit.getText()));
            produit.setCategorieProduit(categorieProduit);

            PrixAchatProduit prixAchatProduit = new PrixAchatProduit();
            prixAchatProduit.setDateHeure(new Date());
            prixAchatProduit.setProduit(produit);
            prixAchatProduit.setValeurUSD(new BigDecimal(tfdPrixAchat.getText()));
            try {
                if (!valeurPrixVenteProduitInicial.equals(new BigDecimal(tfdPrixAchat.getText()))) {
                    PrixAchatProduitService.getInstance().enregistrerPrixAchatProduit(prixAchatProduit);
                    codePrixVenteProduit = PrixAchatProduitService.getInstance().selectionnerDernierPrixAchatProduit(produit);
                }

                produit.setPrixAchatProduit(new PrixAchatProduit(codePrixVenteProduit));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegistreProduit.class.getName()).log(Level.SEVERE, null, ex);
            }

            produit.setActive(modeEdition ? chbActiver.isSelected() : true);

            try {
                if (ProduitService.getInstance().enregistrerProduit(produit)) {
                    effacerFormulaire();
                    JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Produit");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

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
                Logger.getLogger(ConsultationProduit.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }

    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnConsulterReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterReseauActionPerformed
        if (btnConsulterReseauClickable) {
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
        }
    }//GEN-LAST:event_btnConsulterReseauActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        if (btnConsulterCategorieProduitClickable) {

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
        }
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
                ProduitService.getInstance().exclureProduit(codeProduit);
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
        if (produit != null) {
            codeProduit = produit.getCode();
            tfdDescription.setText(produit.getDescription());
            tfdIdReseau.setText(String.valueOf(produit.getReseau().getCode()));
            lblDescriptionReseau.setText(produit.getReseau().getNom());
            tfdIdCategorieProduit.setText(String.valueOf(produit.getCategorieProduit().getCode()));
            lblDescriptionCategorieProduit.setText(produit.getCategorieProduit().getDescription());

            codePrixVenteProduit = produit.getCode();
            tfdPrixAchat.setText(DecimalFormatter.getInstance().getFormattedValue(produit.getPrixAchatProduit().getValeurUSD()));
            chbActiver.setVisible(true);
            chbActiver.setSelected(produit.isActive());
            btnEnregistrer.setText("ACTUALISER");
            modeEdition = true;
            btnExclure.setEnabled(true);
        }
    }

    public void reseauSelectionne(Reseau reseau) {
        if (reseau != null) {
            tfdIdReseau.setText(String.valueOf(reseau.getCode()));
            lblDescriptionReseau.setText(reseau.getNom());
        }
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        if (categorieProduit != null) {
            tfdIdCategorieProduit.setText(String.valueOf(categorieProduit.getCode()));
            lblDescriptionCategorieProduit.setText(categorieProduit.getDescription());
        }
    }

    private void effacerFormulaire() {
        codeProduit = 0;
        tfdDescription.setText("");
        tfdDescription.requestFocus();
        tfdIdReseau.setText("");
        lblDescriptionReseau.setText("");
        tfdPrixAchat.setText("");
        tfdIdCategorieProduit.setText("");
        lblDescriptionCategorieProduit.setText("");
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
        if (tfdIdReseau.getText().isEmpty()) {
            notification.append("\nRéseau");
            nio.add(2);
        }
        if (tfdIdCategorieProduit.getText().isEmpty()) {
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
                    tfdIdReseau.requestFocus();
                    break;
                case 3:
                    tfdIdCategorieProduit.requestFocus();
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblDescriptionCategorieProduit;
    private javax.swing.JLabel lblDescriptionReseau;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdIdCategorieProduit;
    private javax.swing.JTextField tfdIdReseau;
    private javax.swing.JTextField tfdPrixAchat;
    // End of variables declaration//GEN-END:variables
}
