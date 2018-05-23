package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreCollaborateur;
import com.cecilsoftwares.reussoftfrontend.form.RegistreShop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationShop extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<Shop> shops;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationShop(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblShop.getModel();
        dataRows = new Object[2];

        try {
            shops = ShopService.getInstance().listerTousLesShops();
            listerShops(shops);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationShop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerShops(List<Shop> shops) {
        defaultTableModel.setRowCount(0);
        for (Shop shop : shops) {
            dataRows[0] = shop.getCode();
            dataRows[1] = shop.getNom();
            defaultTableModel.addRow(dataRows);
        }
        String formeNombre = shops.size() > 1 ? "Shops" : "Shop";
        lblNombreShop.setText(shops.size() + " " + formeNombre);
    }

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdRechercheNomShop = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblShop = new javax.swing.JTable();
        lblNombreShop = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Shops");
        setResizable(false);

        tfdRechercheNomShop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheNomShopKeyReleased(evt);
            }
        });

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
        if (tblShop.getColumnModel().getColumnCount() > 0) {
            tblShop.getColumnModel().getColumn(0).setResizable(false);
            tblShop.getColumnModel().getColumn(0).setHeaderValue("Code");
            tblShop.getColumnModel().getColumn(1).setResizable(false);
            tblShop.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblShop.getColumnModel().getColumn(1).setHeaderValue("Nom");
        }

        lblNombreShop.setText("jLabel1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreShop)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheNomShop)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheNomShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreShop)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblShopMouseClicked
        if (evt.getClickCount() == 2) {
            if (getFrameAncetre() != null) {
                try {
                    int row = tblShop.getSelectedRow();
                    Shop shop = ShopService.getInstance()
                            .selectionnerShopParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof RegistreShop) {
                        RegistreShop registreShop = (RegistreShop) getFrameAncetre();
                        registreShop.shopSelectionne(shop);
                    } else if (getFrameAncetre() instanceof RegistreCollaborateur) {
                        RegistreCollaborateur registreCollaborateur = (RegistreCollaborateur) getFrameAncetre();
                        registreCollaborateur.shopSelectionne(shop);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationShop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblShopMouseClicked

    private void tfdRechercheNomShopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheNomShopKeyReleased
        List<Shop> listeShops = new ArrayList();
        for (Shop shop : shops) {
            if (shop.getNom().toUpperCase()
                    .startsWith(tfdRechercheNomShop.getText().toUpperCase())) {
                listeShops.add(shop);
            }
        }

        listerShops(listeShops);
    }//GEN-LAST:event_tfdRechercheNomShopKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreShop;
    private javax.swing.JTable tblShop;
    private javax.swing.JTextField tfdRechercheNomShop;
    // End of variables declaration//GEN-END:variables
}
