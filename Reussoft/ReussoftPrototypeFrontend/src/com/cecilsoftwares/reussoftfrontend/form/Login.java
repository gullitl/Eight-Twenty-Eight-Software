package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CollaborateurService;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class Login extends javax.swing.JFrame {

    private ChoixShopLogin choixShopLogin;
    private final Shop shop;
    private boolean retournerAuChoixShop;

    private boolean btnRetournerAuChoixShopClickable;
    private boolean btnEntrerClickable;

    public Login(Shop shop) {
        initComponents();

        try {
            Collaborateur collaborateur = CollaborateurService.getInstance().rappelToiDeLUtilisateur();
            cbxRappelToiDeMoi.setSelected(true);
            tfdUtilisateur.setText(collaborateur.getNomUtilisateur());
            pwfMotDePasse.setText(collaborateur.getMotDePasse());
        } catch (FileNotFoundException | UnknownHostException | SocketException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        enFermantDialog();

        retournerAuChoixShop = true;

        this.shop = shop;
        lblShopSelectionne.setText(this.shop.getCode() + " " + this.shop.getNom());

        habiliterComposantFormulaire(true);
    }

    public void setChoixShopLogin(ChoixShopLogin choixShopLogin) {
        this.choixShopLogin = choixShopLogin;
    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (choixShopLogin instanceof ChoixShopLogin) {
                    ChoixShopLogin csl = (ChoixShopLogin) choixShopLogin;
                    csl.retourneAuChoixShop(retournerAuChoixShop);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfdUtilisateur = new javax.swing.JTextField();
        pwfMotDePasse = new javax.swing.JPasswordField();
        cbxRappelToiDeMoi = new javax.swing.JCheckBox();
        btnEntrer = new javax.swing.JButton();
        btnRetournerAuChoixShop = new javax.swing.JButton();
        lblShopSelectionne = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel2.setText("Utilisateur:");

        jLabel3.setText("Mot de passe:");

        cbxRappelToiDeMoi.setText("Rappel-toi de moi");

        btnEntrer.setText("ENTRER");
        btnEntrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrerActionPerformed(evt);
            }
        });

        btnRetournerAuChoixShop.setText("RETOURNER");
        btnRetournerAuChoixShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetournerAuChoixShopActionPerformed(evt);
            }
        });

        lblShopSelectionne.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblShopSelectionne)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnRetournerAuChoixShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEntrer, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbxRappelToiDeMoi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(pwfMotDePasse))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                            .addComponent(tfdUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(lblShopSelectionne)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pwfMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(cbxRappelToiDeMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetournerAuChoixShop, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrerActionPerformed
        if (isInformationObligatoiresRemplies()) {
            if (btnEntrerClickable) {
                try {

                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    habiliterComposantFormulaire(false);

                    if (CollaborateurService.getInstance()
                            .login(shop, tfdUtilisateur.getText(), pwfMotDePasse.getText(), cbxRappelToiDeMoi.isSelected())) {
                        retournerAuChoixShop = false;
                        java.awt.EventQueue.invokeLater(() -> {
                            MDI mdi = new MDI();
                            mdi.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            mdi.setVisible(true);
                            this.dispose();
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Login incorrect!");
                        habiliterComposantFormulaire(true);
                    }

                    setCursor(Cursor.getDefaultCursor());

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnEntrerActionPerformed

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdUtilisateur.setEditable(hcf);
        pwfMotDePasse.setEditable(hcf);
        cbxRappelToiDeMoi.setEnabled(hcf);
        btnRetournerAuChoixShopClickable = hcf;
        btnEntrerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdUtilisateur.getText().isEmpty()) {
            notification.append("\nNom");
            nio.add(1);
        }
        if (pwfMotDePasse.getText().isEmpty()) {
            notification.append("\nSenha");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdUtilisateur.requestFocus();
                    break;

                case 2:
                    pwfMotDePasse.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    private void btnRetournerAuChoixShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetournerAuChoixShopActionPerformed
        if (btnRetournerAuChoixShopClickable) {
            dispose();
        }
    }//GEN-LAST:event_btnRetournerAuChoixShopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrer;
    private javax.swing.JButton btnRetournerAuChoixShop;
    private javax.swing.JCheckBox cbxRappelToiDeMoi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblShopSelectionne;
    private javax.swing.JPasswordField pwfMotDePasse;
    private javax.swing.JTextField tfdUtilisateur;
    // End of variables declaration//GEN-END:variables
}
