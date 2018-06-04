package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CollaborateurService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCollaborateur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProfilUtilisateur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationShop;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreCollaborateur extends JInternalFrame {

    private int codeCollaborateur;
    private boolean modeEdition;
    private boolean btnConsulterCollaborateurClickable;
    private boolean btnConsulterShopClickable;
    private boolean btnConsulterProfilUtilisateurClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public RegistreCollaborateur() {
        initComponents();
        effacerFormulaire();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnConsulterCollaborateur = new javax.swing.JButton();
        tfdPrenom = new javax.swing.JTextField();
        tfdNom = new javax.swing.JTextField();
        tfdPostnom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdSurnom = new javax.swing.JTextField();
        chbActiver = new javax.swing.JCheckBox();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        tfdIdShop = new javax.swing.JTextField();
        btnConsulterShop = new javax.swing.JButton();
        lblDescriptionShop = new javax.swing.JLabel();
        btnExclure = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tfdNomUtilisateur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfdIdProfilUtilisateur = new javax.swing.JTextField();
        btnConsulterProfilUtilisateur = new javax.swing.JButton();
        lblDescriptionProfilUtilisateur = new javax.swing.JLabel();
        pwfMotDePasse = new javax.swing.JPasswordField();
        pwfConfirmerMotDePasse = new javax.swing.JPasswordField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Collaborateur");

        jLabel2.setText("Prénom:");

        jLabel3.setText("Nom:");

        jLabel4.setText("Surnom:");

        jLabel5.setText("Shop:");

        btnConsulterCollaborateur.setText("...");
        btnConsulterCollaborateur.setFocusable(false);
        btnConsulterCollaborateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCollaborateurActionPerformed(evt);
            }
        });

        jLabel6.setText("Postnom:");

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

        tfdIdShop.setEditable(false);

        btnConsulterShop.setText("...");
        btnConsulterShop.setFocusable(false);
        btnConsulterShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterShopActionPerformed(evt);
            }
        });

        lblDescriptionShop.setText("jLabel10");

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Utilisateur"));

        jLabel7.setText("Nom d'utilisateur:");

        jLabel1.setText("Mot de passe:");

        jLabel8.setText("Confirmer mot de passe:");

        tfdIdProfilUtilisateur.setEditable(false);

        btnConsulterProfilUtilisateur.setText("...");
        btnConsulterProfilUtilisateur.setFocusable(false);
        btnConsulterProfilUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProfilUtilisateurActionPerformed(evt);
            }
        });

        lblDescriptionProfilUtilisateur.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdNomUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfdIdProfilUtilisateur)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterProfilUtilisateur)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pwfMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pwfConfirmerMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel8))
                    .addComponent(lblDescriptionProfilUtilisateur))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNomUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwfMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwfConfirmerMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdProfilUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProfilUtilisateur)
                    .addComponent(lblDescriptionProfilUtilisateur))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chbActiver)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfdIdShop, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterShop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDescriptionShop))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfdSurnom)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(tfdPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(tfdNom, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(tfdPostnom, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterCollaborateur))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdPostnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCollaborateur))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdSurnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterShop)
                    .addComponent(lblDescriptionShop))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chbActiver)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        Shop shop = new ShopBuilder(Integer.parseInt(tfdIdShop.getText())).build();
        ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(Integer.parseInt(tfdIdProfilUtilisateur.getText())).build();
        Collaborateur collaborateur = new CollaborateurBuilder(codeCollaborateur)
                .prenom(tfdPrenom.getText())
                .nom(tfdNom.getText())
                .postnom(tfdPostnom.getText())
                .surnom(tfdSurnom.getText())
                .nomUtilisateur(tfdNomUtilisateur.getText())
                .motDePasse(pwfMotDePasse.getPassword().toString())
                .shop(shop)
                .profilUtilisateur(profilUtilisateur)
                .active(modeEdition ? chbActiver.isSelected() : true)
                .build();
        try {
            if (CollaborateurService.getInstance().enregistrerCollaborateur(collaborateur)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Collaborateur");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterCollaborateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCollaborateurActionPerformed
        if (btnConsulterCollaborateurClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationCollaborateur consultationCollaborateur = new ConsultationCollaborateur(null, true);
            consultationCollaborateur.setFrameAncetre(this);
            consultationCollaborateur.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterCollaborateurActionPerformed

    public void collaborateurSelectionne(Collaborateur collaborateur) {
        if (collaborateur != null) {
            modeEdition = true;
            btnExclure.setEnabled(true);

            codeCollaborateur = collaborateur.getCode();
            tfdPrenom.setText(collaborateur.getPrenom());
            tfdNom.setText(collaborateur.getNom());
            tfdPostnom.setText(collaborateur.getPostnom());
            tfdSurnom.setText(collaborateur.getSurnom());
            tfdIdShop.setText(String.valueOf(collaborateur.getShop().getCode()));
            lblDescriptionShop.setText(collaborateur.getShop().getNom());
            tfdNomUtilisateur.setText(collaborateur.getNomUtilisateur());
            pwfMotDePasse.setText(collaborateur.getMotDePasse());
            pwfConfirmerMotDePasse.setText(collaborateur.getMotDePasse());
            tfdIdProfilUtilisateur.setText(String.valueOf(collaborateur.getProfilUtilisateur().getCode()));
            lblDescriptionProfilUtilisateur.setText(collaborateur.getProfilUtilisateur().getDescription());
            chbActiver.setVisible(true);
            chbActiver.setSelected(collaborateur.isActive());
            btnEnregistrer.setText("ACTUALISER");
        }
    }

    private void btnConsulterShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterShopActionPerformed
        if (btnConsulterShopClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationShop consultationShop = new ConsultationShop(null, true);
            consultationShop.setFrameAncetre(this);
            consultationShop.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterShopActionPerformed

    public void shopSelectionne(Shop shop) {
        if (shop != null) {
            tfdIdShop.setText(String.valueOf(shop.getCode()));
            lblDescriptionShop.setText(shop.getNom());
        }
    }

    private void btnConsulterProfilUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProfilUtilisateurActionPerformed
        if (btnConsulterProfilUtilisateurClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ConsultationProfilUtilisateur consultationProfilUtilisateur = new ConsultationProfilUtilisateur(null, true);
            consultationProfilUtilisateur.setFrameAncetre(this);
            consultationProfilUtilisateur.setVisible(true);

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProfilUtilisateurActionPerformed

    public void profilUtilisateurSelectionne(ProfilUtilisateur profilUtilisateur) {
        if (profilUtilisateur != null) {
            tfdIdProfilUtilisateur.setText(String.valueOf(profilUtilisateur.getCode()));
            lblDescriptionProfilUtilisateur.setText(profilUtilisateur.getDescription());
        }
    }

    private void btnExclureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExclureActionPerformed
        Object[] options = {"Exclure", "Annuler"};
        int n = JOptionPane.showOptionDialog(this,
                "Êtes-vous sûr de vouloir exclure définitivement ce collaborateur?",
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
                CollaborateurService.getInstance().exclureCollaborateur(codeCollaborateur);
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Exclusion effectuée avec succès");
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion de la Catégorie Produit");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    private void effacerFormulaire() {
        codeCollaborateur = 0;
        tfdPrenom.setText("");
        tfdPrenom.requestFocus();
        tfdNom.setText("");
        tfdPostnom.setText("");
        tfdSurnom.setText("");
        tfdIdShop.setText("");
        lblDescriptionShop.setText("");
        tfdNomUtilisateur.setText("");
        pwfMotDePasse.setText("");
        pwfConfirmerMotDePasse.setText("");
        tfdIdProfilUtilisateur.setText("");
        lblDescriptionProfilUtilisateur.setText("");
        chbActiver.setVisible(false);
        modeEdition = false;
        btnEnregistrer.setText("ENREGISTRER");
        habiliterComposantFormulaire(true);
        btnExclure.setEnabled(false);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdPrenom.setEditable(hcf);
        tfdNom.setEditable(hcf);
        tfdPostnom.setEditable(hcf);
        tfdSurnom.setEditable(hcf);
        tfdNomUtilisateur.setEditable(hcf);
        pwfMotDePasse.setEditable(hcf);
        pwfConfirmerMotDePasse.setEditable(hcf);
        chbActiver.setEnabled(hcf);
        btnConsulterCollaborateurClickable = hcf;
        btnConsulterShopClickable = hcf;
        btnConsulterProfilUtilisateurClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnExclureClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterCollaborateur;
    private javax.swing.JButton btnConsulterProfilUtilisateur;
    private javax.swing.JButton btnConsulterShop;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JCheckBox chbActiver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescriptionProfilUtilisateur;
    private javax.swing.JLabel lblDescriptionShop;
    private javax.swing.JPasswordField pwfConfirmerMotDePasse;
    private javax.swing.JPasswordField pwfMotDePasse;
    private javax.swing.JTextField tfdIdProfilUtilisateur;
    private javax.swing.JTextField tfdIdShop;
    private javax.swing.JTextField tfdNom;
    private javax.swing.JTextField tfdNomUtilisateur;
    private javax.swing.JTextField tfdPostnom;
    private javax.swing.JTextField tfdPrenom;
    private javax.swing.JTextField tfdSurnom;
    // End of variables declaration//GEN-END:variables
}
