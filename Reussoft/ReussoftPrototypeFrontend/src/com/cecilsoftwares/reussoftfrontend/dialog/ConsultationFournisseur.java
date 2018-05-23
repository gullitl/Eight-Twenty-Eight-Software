package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.FournisseurService;
import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreFournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
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
public class ConsultationFournisseur extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<Shop> shops;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationFournisseur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblFournisseur.getModel();
        dataRows = new Object[2];

        try {
            shops = ShopService.getInstance().listerTousLesShops();
            listerShops(shops);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
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
        lblNombreFournisseur.setText(shops.size() + " " + formeNombre);
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

        tfdRechercheEntrepriseFournisseur = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFournisseur = new javax.swing.JTable();
        lblNombreFournisseur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fournisseurs");
        setResizable(false);

        tfdRechercheEntrepriseFournisseur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheEntrepriseFournisseurKeyReleased(evt);
            }
        });

        tblFournisseur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Fournisseur"
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
        tblFournisseur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFournisseurMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFournisseur);
        if (tblFournisseur.getColumnModel().getColumnCount() > 0) {
            tblFournisseur.getColumnModel().getColumn(0).setResizable(false);
            tblFournisseur.getColumnModel().getColumn(1).setResizable(false);
            tblFournisseur.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        lblNombreFournisseur.setText("jLabel1");

        jLabel1.setText("Fournisseur:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreFournisseur)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheEntrepriseFournisseur)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheEntrepriseFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreFournisseur)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblFournisseurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFournisseurMouseClicked
        if (evt.getClickCount() == 2) {
            if (getFrameAncetre() != null) {
                try {
                    int row = tblFournisseur.getSelectedRow();
                    Fournisseur fournisseur = FournisseurService.getInstance()
                            .selectionnerFournisseurParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof RegistreFournisseur) {
                        RegistreFournisseur registreFournisseur = (RegistreFournisseur) getFrameAncetre();
                        registreFournisseur.fournisseurSelectionne(fournisseur);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblFournisseurMouseClicked

    private void tfdRechercheEntrepriseFournisseurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheEntrepriseFournisseurKeyReleased
        List<Shop> listeShops = new ArrayList();
        for (Shop shop : shops) {
            if (shop.getNom().toUpperCase()
                    .startsWith(tfdRechercheEntrepriseFournisseur.getText().toUpperCase())) {
                listeShops.add(shop);
            }
        }

        listerShops(listeShops);
    }//GEN-LAST:event_tfdRechercheEntrepriseFournisseurKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreFournisseur;
    private javax.swing.JTable tblFournisseur;
    private javax.swing.JTextField tfdRechercheEntrepriseFournisseur;
    // End of variables declaration//GEN-END:variables
}
