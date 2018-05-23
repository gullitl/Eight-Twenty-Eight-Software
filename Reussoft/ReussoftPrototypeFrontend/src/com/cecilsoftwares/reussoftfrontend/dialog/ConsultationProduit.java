package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
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
public class ConsultationProduit extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<Shop> shops;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationProduit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblProduit.getModel();
        dataRows = new Object[2];

        try {
            shops = ShopService.getInstance().listerTousLesShops();
            listerShops(shops);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationProduit.class.getName()).log(Level.SEVERE, null, ex);
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
        lblNombreProduit.setText(shops.size() + " " + formeNombre);
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

        tfdRechercheDescriptionProduit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduit = new javax.swing.JTable();
        lblNombreProduit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produits");
        setResizable(false);

        tfdRechercheDescriptionProduit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDescriptionProduitKeyReleased(evt);
            }
        });

        tblProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description"
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
        tblProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduitMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduit);
        if (tblProduit.getColumnModel().getColumnCount() > 0) {
            tblProduit.getColumnModel().getColumn(0).setResizable(false);
            tblProduit.getColumnModel().getColumn(1).setResizable(false);
            tblProduit.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        lblNombreProduit.setText("jLabel1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreProduit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheDescriptionProduit)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheDescriptionProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreProduit)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblProduitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduitMouseClicked
        if (evt.getClickCount() == 2) {
            if (getFrameAncetre() != null) {
                try {
                    int row = tblProduit.getSelectedRow();
                    Produit produit = ProduitService.getInstance()
                            .selectionnerProduitParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof RegistreProduit) {
                        RegistreProduit registreProduit = (RegistreProduit) getFrameAncetre();
                        registreProduit.produitSelectionne(produit);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationProduit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblProduitMouseClicked

    private void tfdRechercheDescriptionProduitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDescriptionProduitKeyReleased
        List<Shop> listeShops = new ArrayList();
        for (Shop shop : shops) {
            if (shop.getNom().toUpperCase()
                    .startsWith(tfdRechercheDescriptionProduit.getText().toUpperCase())) {
                listeShops.add(shop);
            }
        }

        listerShops(listeShops);
    }//GEN-LAST:event_tfdRechercheDescriptionProduitKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreProduit;
    private javax.swing.JTable tblProduit;
    private javax.swing.JTextField tfdRechercheDescriptionProduit;
    // End of variables declaration//GEN-END:variables
}
