package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.TauxService;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreTaux extends JInternalFrame {

    private Shop shop;
    private List<Shop> shops;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    public RegistreTaux() {
        initComponents();

        defaultTableModel = (DefaultTableModel) tblTauxShop.getModel();
        dataRows = new Object[2];

        effacerFormulaire();

    }

    private void listerShopsTauxCarte() {
        new Thread() {
            @Override
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                habiliterComposantFormulaire(false);

                try {
                    shops = TauxService.getInstance().listerTousLesShopsAvecTauxCarte();

                    defaultTableModel.setRowCount(0);
                    shops.forEach(s -> {
                        dataRows[0] = s.getNom();
                        dataRows[1] = s.getTauxCarte().getValeur();
                        defaultTableModel.addRow(dataRows);
                    });

                    String formeNombre = shops.size() > 1 ? "Shops" : "Shop";
                    lblNombreShop.setText(shops.size() + " " + formeNombre);

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
                }

                setCursor(Cursor.getDefaultCursor());
                habiliterComposantFormulaire(true);

            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNomShop = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTauxShop = new javax.swing.JTable();
        lblNombreShop = new javax.swing.JLabel();
        btnEffacerFormulaire1 = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        tfdTauxCarte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Taux Carte");

        lblNomShop.setText("Shop:");

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

        lblNombreShop.setText("jLabel1");

        btnEffacerFormulaire1.setText("<-");
        btnEffacerFormulaire1.addActionListener(new java.awt.event.ActionListener() {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreShop)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfdTauxCarte)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnEnregistrer)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEffacerFormulaire1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblNomShop)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdTauxCarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomShop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnregistrer)
                    .addComponent(btnEffacerFormulaire1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreShop)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTauxShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTauxShopMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblTauxShop.getSelectedRow();

            shop = shops.get(row);

            tfdTauxCarte.setText(shop.getTauxCarte().getValeur().toString());
            lblNomShop.setText(shop.getNom());
        }
    }//GEN-LAST:event_tblTauxShopMouseClicked

    private void tblTauxShopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTauxShopKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
//
//            boolean exclu = false;
//
//            List<ItemEntreeStock> listeItemsEntreeStock = itemsEntreeStock;
//            int row = tblTauxShop.getSelectedRow();
//
//            for (ItemEntreeStock ies : listeItemsEntreeStock) {
//                if (ies.getProduit().getId().equals(itemsEntreeStock.get(row).getProduit().getId())) {
//                    itemsEntreeStock.remove(ies);
//                    exclu = true;
//                    break;
//                }
//            }
//
//            if (exclu) {
//                listerItemsEntreeStock(itemsEntreeStock);
//            }
//        }
    }//GEN-LAST:event_tblTauxShopKeyReleased

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        if (!isInformationObligatoiresRemplies() || !btnEnregistrerClickable) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

//        Client client = new Client(idTauxCarte);
        //        client.setNom(tfdShop.getText());
//        client.setShop(SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getShop());
//        try {
//            if (ClientService.getInstance().enregistrerClient(client)) {
//                effacerFormulaire();
//                JOptionPane.showMessageDialog(null, "Actualisation effectuée avec succès");
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Client");
//            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
//        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void effacerFormulaire() {
        lblNomShop.setText("");

        listerShopsTauxCarte();

        tfdTauxCarte.setText("");

        shop = null;

        habiliterComposantFormulaire(true);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tblTauxShop.setEnabled(hcf);

        tfdTauxCarte.setEditable(hcf);

        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdTauxCarte.getText().isEmpty()) {
            notification.append("\nTaux Carte");
            nio.add(1);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdTauxCarte.requestFocus();
                    break;

                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEffacerFormulaire1;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomShop;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JTable tblTauxShop;
    private javax.swing.JTextField tfdTauxCarte;
    // End of variables declaration//GEN-END:variables
}
