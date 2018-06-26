package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ChoixShopLogin extends javax.swing.JFrame {

    private Shop shop;
    private List<Shop> shops;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    private static ChoixShopLogin uniqueInstance;

    public ChoixShopLogin() {
        initComponents();

        defaultTableModel = (DefaultTableModel) tblShop.getModel();
        dataRows = new Object[2];

        try {
            shops = ShopService.getInstance().listerTousLesShops();
            listerShops(shops);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChoixShopLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void listerShops(List<Shop> shops) {
        defaultTableModel.setRowCount(0);
        shops.forEach(s -> {
            dataRows[0] = s.getCode();
            dataRows[1] = s.getNom();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = shops.size() > 1 ? "Shops" : "Shop";
        lblNombreShop.setText(shops.size() + " " + formeNombre);
    }

    public static synchronized ChoixShopLogin getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ChoixShopLogin();
        }
        return uniqueInstance;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblShop = new javax.swing.JTable();
        lblNombreShop = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        tblShop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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
        tblShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblShopMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblShop);

        lblNombreShop.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreShop)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombreShop)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblShopMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblShop.getSelectedRow();

            shop = shops.stream()
                    .filter(s -> s.getCode() == (int) defaultTableModel.getValueAt(row, 0))
                    .findFirst().orElse(null);

            /* Create and display the login form */
            java.awt.EventQueue.invokeLater(() -> {
                Login login = new Login(shop);
                login.setChoixShopLogin(this);
                login.setVisible(true);

            });

            dispose();

        }
    }//GEN-LAST:event_tblShopMouseClicked

    public void retourneAuChoixShop(boolean retour) {
        setVisible(retour);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JTable tblShop;
    // End of variables declaration//GEN-END:variables
}
