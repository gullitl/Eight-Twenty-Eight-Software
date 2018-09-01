package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.operations.OperationDispatch;
import com.cecilsoftwares.reussoftfrontend.form.operations.OperationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistrePrixVenteProduit;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationProduit extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private Produit produit;
    private final List<Produit> produits;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param produits
     */
    public ConsultationProduit(java.awt.Frame parent, boolean modal, List<Produit> produits) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblProduit.getModel();
        dataRows = new Object[3];

        this.produits = produits;
        listerProduits(this.produits);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreProduit) {
                    RegistreProduit registreProduit = (RegistreProduit) frameAncetre;
                    registreProduit.produitSelectionne(produit);
                } else if (frameAncetre instanceof OperationEntreeStock) {
                    OperationEntreeStock operationEntreeStock = (OperationEntreeStock) frameAncetre;
                    operationEntreeStock.setProduitSelectionne(produit);
                } else if (frameAncetre instanceof RegistrePrixVenteProduit) {
                    RegistrePrixVenteProduit registrePrixVenteProduit = (RegistrePrixVenteProduit) frameAncetre;
                    registrePrixVenteProduit.produitSelectionne(produit);
                } else if (frameAncetre instanceof OperationDispatch) {
                    OperationDispatch operationDispatch = (OperationDispatch) frameAncetre;
                    operationDispatch.setProduitSelectionne(produit);
                }
            }
        });
    }

    private void listerProduits(List<Produit> produits) {
        defaultTableModel.setRowCount(0);
        produits.forEach(p -> {
            dataRows[0] = p.getDescription();
            dataRows[1] = p.getCategorieProduit().getDescription();
            dataRows[2] = p.getReseau().getNom();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = produits.size() > 1 ? "Produits" : "Produit";
        lblNombreProduit.setText(produits.size() + " " + formeNombre);
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
                "Description", "Catégorie", "Réseau"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            tblProduit.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblProduit.getColumnModel().getColumn(1).setResizable(false);
            tblProduit.getColumnModel().getColumn(2).setResizable(false);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(tfdRechercheDescriptionProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
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
            if (frameAncetre != null) {
                int row = tblProduit.getSelectedRow();

                produit = produits.get(row);

//                produit = produits.stream()
//                        .filter(cp -> cp.getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                        .findFirst().orElse(null);
            }
            dispose();

        }
    }//GEN-LAST:event_tblProduitMouseClicked

    private void tfdRechercheDescriptionProduitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDescriptionProduitKeyReleased
        List<Produit> listeProduits = new ArrayList();

        produits.stream().filter((cp) -> (cp.getDescription().toUpperCase()
                .startsWith(tfdRechercheDescriptionProduit.getText().toUpperCase())))
                .forEachOrdered((cp) -> {
                    listeProduits.add(cp);
                });

        listerProduits(listeProduits);
    }//GEN-LAST:event_tfdRechercheDescriptionProduitKeyReleased

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreProduit;
    private javax.swing.JTable tblProduit;
    private javax.swing.JTextField tfdRechercheDescriptionProduit;
    // End of variables declaration//GEN-END:variables
}
