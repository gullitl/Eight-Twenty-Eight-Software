package com.cecilsoftwares.reussoftfrontend.form.registres;

import com.cecilsoftwares.reussoftbackend.service.ClientService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationClient;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
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
public class RegistreClient extends JInternalFrame {

    private String idClient;
    private boolean modeEdition;
    private boolean btnConsulterClientClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public RegistreClient() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        tfdEntreprise = new javax.swing.JTextField();
        tfdNom = new javax.swing.JTextField();
        btnConsulterClient = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfdTelephone = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter telefone = new javax.swing.text.MaskFormatter("(##) ####-####");
            tfdTelephone = new javax.swing.JFormattedTextField(telefone);
        }catch(Exception e){
        }
        btnExclure = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Client");

        jLabel2.setText("Entreprise:");

        jLabel3.setText("Nom:");

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

        btnConsulterClient.setText("...");
        btnConsulterClient.setFocusable(false);
        btnConsulterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterClientActionPerformed(evt);
            }
        });

        jLabel5.setText("Téléphone:");

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
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfdEntreprise, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(tfdTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(225, 225, 225))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdNom, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsulterClient)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterClient))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdEntreprise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
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

        Client client = new Client(idClient);
        client.setEntreprise(tfdEntreprise.getText());
        client.setNom(tfdNom.getText());
        client.setTelephone(tfdTelephone.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
        client.setShop(SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getShop());
        try {
            if (ClientService.getInstance().enregistrerClient(client)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Client");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistreClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterClientActionPerformed
        if (!btnConsulterClientClickable) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {
            ConsultationClient consultationClient = new ConsultationClient(null, true, ClientService.getInstance()
                    .listerTousLesClients());
            consultationClient.setFrameAncetre(this);
            consultationClient.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterClientActionPerformed

    public void clientSelectionne(Client client) {
        if (client == null) {
            return;
        }
        idClient = client.getId();
        tfdNom.setText(client.getNom());
        tfdEntreprise.setText(client.getEntreprise());
        tfdTelephone.setText(client.getTelephone());
        btnEnregistrer.setText("ACTUALISER");
        modeEdition = true;
        btnExclure.setEnabled(true);
    }

    private void btnExclureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExclureActionPerformed
        Object[] options = {"Exclure", "Annuler"};
        int n = JOptionPane.showOptionDialog(this,
                "Êtes-vous sûr de vouloir exclure définitivement ce client?",
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
                ClientService.getInstance().exclureClient(idClient);
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Exclusion effectuée avec succès");
            } catch (SQLException ex) {
                StringBuilder notification = new StringBuilder("Une faille est survenue lors de l'exclusion du client :(");
                switch (ex.getErrorCode()) {
                    case 1451:
                        notification.append("\n\nCe client est utilisé par un autre registre!");
                        break;
                    default:
                        break;
                }
                JOptionPane.showMessageDialog(null, notification);
                habiliterComposantFormulaire(true);
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion du client :(");
                habiliterComposantFormulaire(true);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    private void effacerFormulaire() {
        idClient = "";
        tfdNom.setText("");
        tfdNom.requestFocus();
        tfdEntreprise.setText("");
        tfdTelephone.setText("");
        modeEdition = false;
        btnEnregistrer.setText("ENREGISTRER");
        habiliterComposantFormulaire(true);
        btnExclure.setEnabled(false);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdEntreprise.setEditable(hcf);
        tfdNom.setEditable(hcf);
        btnConsulterClientClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdNom.getText().isEmpty()) {
            notification.append("\nNom");
            nio.add(1);
        }
        if (tfdEntreprise.getText().isEmpty()) {
            notification.append("\nEntreprise");
            nio.add(2);
        }
        if (tfdTelephone.getText().isEmpty()) {
            notification.append("\nTéléphone");
            nio.add(3);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdNom.requestFocus();
                    break;

                case 2:
                    tfdEntreprise.requestFocus();
                    break;
                case 3:
                    tfdTelephone.requestFocus();
                    break;

                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulterClient;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField tfdEntreprise;
    private javax.swing.JTextField tfdNom;
    private javax.swing.JTextField tfdTelephone;
    // End of variables declaration//GEN-END:variables
}
