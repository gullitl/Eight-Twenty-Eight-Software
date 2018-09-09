package com.cecilsoftwares.reussoftfrontend.view.form;

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

        this.shop = shop;
        lblShopSelectionne.setText(this.shop.getNom().toUpperCase());

        try {
            Collaborateur collaborateur = CollaborateurService.getInstance().rappelToiDeLUtilisateur();
            if (shop.getId().equals(collaborateur.getShop().getId())) {
                cbxRappelToiDeMoi.setSelected(true);
                tfdUtilisateur.setText(collaborateur.getNomUtilisateur());
                pwfMotDePasse.setText(collaborateur.getMotDePasse());
                btnEntrer.requestFocus();
            }
        } catch (FileNotFoundException | UnknownHostException | SocketException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        enFermantDialog();

        retournerAuChoixShop = true;

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

        lblShopSelectionne.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblShopSelectionne.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbxRappelToiDeMoi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pwfMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRetournerAuChoixShop, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEntrer, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(lblShopSelectionne)
                    .addComponent(tfdUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblShopSelectionne)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(tfdUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(pwfMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cbxRappelToiDeMoi)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetournerAuChoixShop, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrerActionPerformed
        if (!isInformationObligatoiresRemplies() || !btnEntrerClickable) {
            return;
        }

        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            switch (CollaborateurService.getInstance()
                    .login(shop, tfdUtilisateur.getText(), pwfMotDePasse.getText(), cbxRappelToiDeMoi.isSelected())) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Login incorrect!");
                    habiliterComposantFormulaire(true);
                    break;
                case 1:
                    retournerAuChoixShop = false;
                    java.awt.EventQueue.invokeLater(() -> {
                        MDI mdi = new MDI();
                        mdi.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mdi.setVisible(true);
                        this.dispose();
                    });
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Désolé, mais vous n'êtes pas autorisé à acceder à ce shop!");
                    habiliterComposantFormulaire(true);
                    break;
            }
            setCursor(Cursor.getDefaultCursor());

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
