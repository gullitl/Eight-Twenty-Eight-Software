package com.cecilsoftwares.reussoftfrontend.view.form.registres;

import com.cecilsoftwares.reussoftbackend.service.TauxService;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.math.BigDecimal;
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

    private boolean btnbtnActualiserClickable;
    private boolean btnEffacerFormulaireClickable;

    public RegistreTaux() {
        initComponents();

        defaultTableModel = (DefaultTableModel) tblTauxShop.getModel();
        dataRows = new Object[2];

        effacerFormulaire();
        listerShopsTauxCarte();

        tblTauxShop.requestFocus();

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

        jPanel1 = new javax.swing.JPanel();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnActualiser = new javax.swing.JButton();
        tfdTauxCarte = new javax.swing.JTextField();
        lblNomShop = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTauxShop = new javax.swing.JTable();
        lblNombreShop = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Taux Carte");

        btnEffacerFormulaire.setText("<-");
        btnEffacerFormulaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerFormulaireActionPerformed(evt);
            }
        });

        btnActualiser.setText("ACTUALISER");
        btnActualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserActionPerformed(evt);
            }
        });

        lblNomShop.setText("Shop:");

        jLabel3.setText("Taux Carte:");

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
        jScrollPane1.setViewportView(tblTauxShop);
        if (tblTauxShop.getColumnModel().getColumnCount() > 0) {
            tblTauxShop.getColumnModel().getColumn(0).setResizable(false);
            tblTauxShop.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblTauxShop.getColumnModel().getColumn(1).setResizable(false);
        }

        lblNombreShop.setText("En train de charger...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdTauxCarte, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnActualiser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEffacerFormulaire)))
                        .addGap(18, 18, 18)
                        .addComponent(lblNomShop))
                    .addComponent(jLabel3)
                    .addComponent(lblNombreShop)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdTauxCarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomShop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEffacerFormulaire)
                    .addComponent(btnActualiser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreShop)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTauxShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTauxShopMouseClicked
        if (evt.getClickCount() == 2) {
            tfdTauxCarte.setEditable(true);

            int row = tblTauxShop.getSelectedRow();

            shop = shops.get(row);

            tfdTauxCarte.setText(shop.getTauxCarte().getValeur().toString());
            lblNomShop.setText("shop: " + shop.getNom());
        }
    }//GEN-LAST:event_tblTauxShopMouseClicked

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        if (!btnEffacerFormulaireClickable) {
            return;
        }
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnActualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserActionPerformed
        if (!btnbtnActualiserClickable || !isInformationObligatoiresRemplies()) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {

            shop.getTauxCarte().setValeur(new BigDecimal(tfdTauxCarte.getText()));

            if (TauxService.getInstance().enregistrerShopTauxCarte(shop)) {
                effacerFormulaire();
                actualiserTableauTauxShop();
                JOptionPane.showMessageDialog(null, "Actualisation effectuée avec succès");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Taux");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeadlessException ex) {
            Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
        }

        setCursor(Cursor.getDefaultCursor());
        habiliterComposantFormulaire(true);
    }//GEN-LAST:event_btnActualiserActionPerformed

    private void actualiserTableauTauxShop() {
        defaultTableModel.setRowCount(0);

        shops.forEach(shp -> {
            dataRows[0] = shp.getNom();
            dataRows[1] = shp.getTauxCarte().getValeur();

            defaultTableModel.addRow(dataRows);
        });
    }

    private void effacerFormulaire() {
        lblNomShop.setText("");
        tfdTauxCarte.setText("");
        tfdTauxCarte.setEditable(false);
        shop = null;
        tblTauxShop.requestFocus();
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tblTauxShop.setEnabled(hcf);
        btnbtnActualiserClickable = hcf;
        btnEffacerFormulaireClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdTauxCarte.getText().isEmpty()) {
            notification.append("\nTaux Carte");
            nio.add(1);
        }

        if (tfdTauxCarte.getText().equals("0")) {
            notification.append("\nTaux Carte -> Valeur incorrect!");
            nio.add(2);;
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
                case 2:
                    tfdTauxCarte.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualiser;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNomShop;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JTable tblTauxShop;
    private javax.swing.JTextField tfdTauxCarte;
    // End of variables declaration//GEN-END:variables
}
