package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author Plamedi L. Lusembo
 */
public class ChoixShopLogin extends javax.swing.JFrame {

    private Shop shop;
    private List<Shop> shops;
    private final DefaultListModel<Shop> defaultListModel;

    private static ChoixShopLogin uniqueInstance;

    public static void main(String[] args) {
        ChoixShopLogin choixShopLogin = new ChoixShopLogin();
        choixShopLogin.setVisible(true);
    }

    public ChoixShopLogin() {
        initComponents();

        defaultListModel = new DefaultListModel<>();

        try {
            shops = ShopService.getInstance().listerTousLesShops();
            listerShops(shops);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChoixShopLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void listerShops(List<Shop> shops) {
        defaultListModel.clear();
        shops.forEach(s -> {
            defaultListModel.addElement(s);
        });

        lstShop = new JList(defaultListModel);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        lstShop = new javax.swing.JList<>();
        lblNombreShop = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        lstShop.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstShopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstShop);

        lblNombreShop.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreShop)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreShop)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lstShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstShopMouseClicked
        loginShopSelectionne();
    }//GEN-LAST:event_lstShopMouseClicked

    private void loginShopSelectionne() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JList<String> lstShop;
    // End of variables declaration//GEN-END:variables
}
