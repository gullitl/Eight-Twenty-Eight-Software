package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.ClientService;
import com.cecilsoftwares.reussoftbackend.service.TauxService;
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
public class RegistreTaux extends JInternalFrame {

    private String idTauxCarte;
    private boolean modeEdition;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    public RegistreTaux() {
        initComponents();
        effacerFormulaire();

        new Thread() {
            @Override
            public void run() {
                try {
                    TauxService.getInstance().selectionnerDernierTauxCarteEnDate(shop);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(CollaborateurService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpTaux = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTauxShop = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        pnlTaux = new javax.swing.JPanel();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        tfdTauxCarte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Taux");

        jLabel2.setText("Shop:");

        tblTauxShop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Shop", "Taux"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTauxShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTauxShopMouseClicked(evt);
            }
        });
        tblTauxShop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTauxShopKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblTauxShop);
        if (tblTauxShop.getColumnModel().getColumnCount() > 0) {
            tblTauxShop.getColumnModel().getColumn(0).setResizable(false);
            tblTauxShop.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblTauxShop.getColumnModel().getColumn(1).setResizable(false);
        }

        btnGrpTaux.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Taux Carte");

        jRadioButton2.setText("Taux monnaie");

        btnEffacerFormulaire.setText("<-");
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

        jLabel3.setText("Taux Carte:");

        javax.swing.GroupLayout pnlTauxLayout = new javax.swing.GroupLayout(pnlTaux);
        pnlTaux.setLayout(pnlTauxLayout);
        pnlTauxLayout.setHorizontalGroup(
            pnlTauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTauxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTauxLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTauxLayout.createSequentialGroup()
                        .addComponent(tfdTauxCarte, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnregistrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTauxLayout.setVerticalGroup(
            pnlTauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTauxLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdTauxCarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer)
                    .addComponent(btnEffacerFormulaire))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTaux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(37, 37, 37)
                                .addComponent(jRadioButton2)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTaux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        if (!isInformationObligatoiresRemplies()) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        Client client = new Client(idTauxCarte);
        client.setNom(tfdShop.getText());
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
            Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void tblTauxShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTauxShopMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblTauxShop.getSelectedRow();

            ItemEntreeStock itemEntreeStock;

            itemEntreeStock = itemsEntreeStock.get(row);
            //
            //            if (itemEntreeStock == null) {
            //                return;
            //            }
            //            tfdDescriptionProduit.setText(itemEntreeStock.getProduit().getDescription());
            spnQuantiteProduit.setValue(itemEntreeStock.getQuantiteProduit());
            modeEditionItemEntreeStock = true;
            //            btnAjouterProduit.setEnabled(true);
            setProduitSelectionne(itemEntreeStock.getProduit());

        }
    }//GEN-LAST:event_tblTauxShopMouseClicked

    private void tblTauxShopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTauxShopKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            boolean exclu = false;

            List<ItemEntreeStock> listeItemsEntreeStock = itemsEntreeStock;
            int row = tblTauxShop.getSelectedRow();

            for (ItemEntreeStock ies : listeItemsEntreeStock) {
                if (ies.getProduit().getId().equals(itemsEntreeStock.get(row).getProduit().getId())) {
                    itemsEntreeStock.remove(ies);
                    exclu = true;
                    break;
                }
            }

            if (exclu) {
                listerItemsEntreeStock(itemsEntreeStock);
            }
        }
    }//GEN-LAST:event_tblTauxShopKeyReleased

    private void effacerFormulaire() {
        idTauxCarte = "";
        tfdShop.setText("");
        tfdShop.requestFocus();
        tfdTauxMonnaie.setText("");
        modeEdition = false;
        btnEnregistrer.setText("ENREGISTRER");
        habiliterComposantFormulaire(true);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdTauxMonnaie.setEditable(hcf);
        tfdShop.setEditable(hcf);
        btnConsulterClientClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdShop.getText().isEmpty()) {
            notification.append("\nTaux Carte");
            nio.add(1);
        }
        if (tfdTauxMonnaie.getText().isEmpty()) {
            notification.append("\nTaux Monnaie");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdShop.requestFocus();
                    break;

                case 2:
                    tfdTauxMonnaie.requestFocus();
                    break;

                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.ButtonGroup btnGrpTaux;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTaux;
    private javax.swing.JTable tblTauxShop;
    private javax.swing.JTextField tfdTauxCarte;
    // End of variables declaration//GEN-END:variables
}
