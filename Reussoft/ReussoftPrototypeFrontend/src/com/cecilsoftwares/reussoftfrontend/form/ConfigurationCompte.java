package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CollaborateurService;
import com.cecilsoftwares.reussoftbackend.service.ProfilUtilisateurService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCollaborateur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.awt.Cursor;
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
public class ConfigurationCompte extends JInternalFrame {

    private int codeCollaborateur;
    private boolean modeEdition;
    private boolean btnConsulterCollaborateurClickable;
    private boolean btnConsulterShopClickable;
    private boolean btnConsulterProfilUtilisateurClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public ConfigurationCompte() {
        initComponents();
        effacerFormulaire();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnConsulterCollaborateur = new javax.swing.JButton();
        tfdPrenom = new javax.swing.JTextField();
        tfdNom = new javax.swing.JTextField();
        tfdPostnom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdSurnom = new javax.swing.JTextField();
        chbActiver = new javax.swing.JCheckBox();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
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
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Collaborateur");

        jLabel2.setText("Prénom:");

        jLabel3.setText("Nom:");

        jLabel4.setText("Surnom:");

        btnConsulterCollaborateur.setText("...");
        btnConsulterCollaborateur.setFocusable(false);
        btnConsulterCollaborateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCollaborateurActionPerformed(evt);
            }
        });

        jLabel6.setText("Postnom:");

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

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Utilisateur"));

        tfdNomUtilisateur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfdNomUtilisateurFocusGained(evt);
            }
        });

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

        jLabel9.setText("Profil d'utilisateur:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                            .addComponent(lblDescriptionProfilUtilisateur)))
                    .addComponent(jLabel9))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdIdProfilUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProfilUtilisateur)
                    .addComponent(lblDescriptionProfilUtilisateur))
                .addGap(21, 21, 21))
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
                        .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chbActiver)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfdSurnom)
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
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chbActiver)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (isInformationObligatoiresRemplies()) {

            if (!isMotdePasseConfirme()) {
                return;
            }

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(Integer.parseInt(tfdIdProfilUtilisateur.getText()));
            Collaborateur collaborateur = new Collaborateur(codeCollaborateur);
            collaborateur.setPrenom(tfdPrenom.getText());
            collaborateur.setNom(tfdNom.getText());
            collaborateur.setPostnom(tfdPostnom.getText());
            collaborateur.setSurnom(tfdSurnom.getText());
            collaborateur.setNomUtilisateur(tfdNomUtilisateur.getText());
            collaborateur.setMotDePasse(pwfMotDePasse.getText());
            collaborateur.setProfilUtilisateur(profilUtilisateur);
            collaborateur.setActive(modeEdition ? chbActiver.isSelected() : true);
            try {
                if (CollaborateurService.getInstance().enregistrerCollaborateur(collaborateur)) {
                    String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                    effacerFormulaire();
                    JOptionPane.showMessageDialog(null, notification);
                }
            } catch (SQLException ex) {
                StringBuilder notification = new StringBuilder("Une faille est survenue en sauvegardant le collaborateur :(");
                switch (ex.getErrorCode()) {
                    case 1062:
                        notification.append("\n\nLe nom d'utilisateur de ce collaborateur est déjà utilisé!");
                        tfdNomUtilisateur.selectAll();
                        break;
                    default:
                        break;
                }
                JOptionPane.showMessageDialog(null, notification);
                habiliterComposantFormulaire(true);
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le collaborateur :(");
                habiliterComposantFormulaire(true);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }

            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterCollaborateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCollaborateurActionPerformed
        if (btnConsulterCollaborateurClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                ConsultationCollaborateur consultationCollaborateur = new ConsultationCollaborateur(null, true, CollaborateurService.getInstance()
                        .listerTousLesCollaborateurs());
                consultationCollaborateur.setFrameAncetre(this);
                consultationCollaborateur.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConfigurationCompte.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterCollaborateurActionPerformed

    public void collaborateurSelectionne(Collaborateur collaborateur) {
        if (collaborateur != null) {
            codeCollaborateur = collaborateur.getCode();
            tfdPrenom.setText(collaborateur.getPrenom());
            tfdNom.setText(collaborateur.getNom());
            tfdPostnom.setText(collaborateur.getPostnom());
            tfdSurnom.setText(collaborateur.getSurnom());
            tfdNomUtilisateur.setText(collaborateur.getNomUtilisateur());
            pwfMotDePasse.setText(collaborateur.getMotDePasse());
            pwfConfirmerMotDePasse.setText(collaborateur.getMotDePasse());
            tfdIdProfilUtilisateur.setText(String.valueOf(collaborateur.getProfilUtilisateur().getCode()));
            lblDescriptionProfilUtilisateur.setText(collaborateur.getProfilUtilisateur().getDescription());
            chbActiver.setVisible(true);
            chbActiver.setSelected(collaborateur.isActive());
            btnEnregistrer.setText("ACTUALISER");
            modeEdition = true;
            btnExclure.setEnabled(true);
        }
    }

    private void btnConsulterProfilUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProfilUtilisateurActionPerformed
        if (btnConsulterProfilUtilisateurClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                ConsultationProfilUtilisateur consultationProfilUtilisateur = new ConsultationProfilUtilisateur(null, true, ProfilUtilisateurService.getInstance()
                        .listerTousLesProfilUtilisateurs());
                consultationProfilUtilisateur.setFrameAncetre(this);
                consultationProfilUtilisateur.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConfigurationCompte.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProfilUtilisateurActionPerformed

    public void profilUtilisateurSelectionne(ProfilUtilisateur profilUtilisateur) {
        if (profilUtilisateur != null) {
            tfdIdProfilUtilisateur.setText(String.valueOf(profilUtilisateur.getCode()));
            lblDescriptionProfilUtilisateur.setText(profilUtilisateur.getDescription());
            tfdIdProfilUtilisateur.requestFocus();
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
            } catch (SQLException ex) {
                StringBuilder notification = new StringBuilder("Une faille est survenue lors de l'exclusion du collaborateur :(");
                switch (ex.getErrorCode()) {
                    case 1451:
                        notification.append("\n\n Ce collaborateur est utilisé par un autre registre!");
                        break;
                    default:
                        break;
                }
                JOptionPane.showMessageDialog(null, notification);
                habiliterComposantFormulaire(true);
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion du collaborateur :(");
                habiliterComposantFormulaire(true);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    private void tfdNomUtilisateurFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdNomUtilisateurFocusGained
        if (tfdNomUtilisateur.getText().isEmpty() && !tfdNom.getText().isEmpty() && !tfdPostnom.getText().isEmpty()) {
            char premiereLettrePrenom = tfdPrenom.getText().toLowerCase().charAt(0);
            tfdNomUtilisateur.setText(new StringBuilder().append(premiereLettrePrenom)
                    .append(tfdPostnom.getText().toLowerCase()).toString());
        }

    }//GEN-LAST:event_tfdNomUtilisateurFocusGained

    private void effacerFormulaire() {
        codeCollaborateur = 0;
        tfdPrenom.setText("");
        tfdPrenom.requestFocus();
        tfdNom.setText("");
        tfdPostnom.setText("");
        tfdSurnom.setText("");
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

    private boolean isMotdePasseConfirme() {
        if (pwfMotDePasse.getText().equals(pwfConfirmerMotDePasse.getText())) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Le mot de passe ne correspond pas!");
            return false;
        }
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdPrenom.getText().isEmpty()) {
            notification.append("\nPrénom");
            nio.add(1);
        }
        if (tfdNom.getText().isEmpty()) {
            notification.append("\nNom");
            nio.add(2);
        }
        if (tfdPostnom.getText().isEmpty()) {
            notification.append("\nPostnom");
            nio.add(3);
        }

        if (tfdSurnom.getText().isEmpty()) {
            notification.append("\nSurnom");
            nio.add(4);
        }

        if (tfdNomUtilisateur.getText().isEmpty()) {
            notification.append("\nnom d'utilisateur");
            nio.add(5);
        }
        if (pwfMotDePasse.getText().isEmpty()) {
            notification.append("\nMot de passe");
            nio.add(6);
        }

        if (tfdIdProfilUtilisateur.getText().isEmpty()) {
            notification.append("\nprofil d'utilisateur");
            nio.add(7);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdPrenom.requestFocus();
                    break;

                case 2:
                    tfdNom.requestFocus();
                    break;
                case 3:
                    tfdPostnom.requestFocus();
                    break;

                case 4:
                    tfdSurnom.requestFocus();
                    break;
                case 5:
                    tfdNomUtilisateur.requestFocus();
                    break;
                case 6:
                    pwfMotDePasse.requestFocus();
                    break;
                case 7:
                    pwfMotDePasse.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulterCollaborateur;
    private javax.swing.JButton btnConsulterProfilUtilisateur;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JCheckBox chbActiver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescriptionProfilUtilisateur;
    private javax.swing.JPasswordField pwfConfirmerMotDePasse;
    private javax.swing.JPasswordField pwfMotDePasse;
    private javax.swing.JTextField tfdIdProfilUtilisateur;
    private javax.swing.JTextField tfdNom;
    private javax.swing.JTextField tfdNomUtilisateur;
    private javax.swing.JTextField tfdPostnom;
    private javax.swing.JTextField tfdPrenom;
    private javax.swing.JTextField tfdSurnom;
    // End of variables declaration//GEN-END:variables
}
