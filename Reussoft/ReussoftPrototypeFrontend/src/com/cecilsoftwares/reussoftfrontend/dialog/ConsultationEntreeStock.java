package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.EntreeStockService;
import com.cecilsoftwares.reussoftfrontend.form.OperationEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
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
public class ConsultationEntreeStock extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<EntreeStock> entreeStocks;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationEntreeStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblEntreeStock.getModel();
        dataRows = new Object[2];

        try {
            entreeStocks = EntreeStockService.getInstance().listerTousLesEntreeStocks();
            listerEntreeStocks(entreeStocks);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerEntreeStocks(List<EntreeStock> entreeStocks) {
        defaultTableModel.setRowCount(0);
        for (EntreeStock entreeStock : entreeStocks) {
            dataRows[0] = entreeStock.getCode();
            dataRows[1] = entreeStock.getProduit().getDescription();
            defaultTableModel.addRow(dataRows);
        }
        String formeNombre = entreeStocks.size() > 1 ? "EntreeStocks" : "EntreeStock";
        lblNombreEntreeStock.setText(entreeStocks.size() + " " + formeNombre);
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
        tblEntreeStock = new javax.swing.JTable();
        lblNombreEntreeStock = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrées stock");
        setResizable(false);

        tfdRechercheEntrepriseFournisseur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheEntrepriseFournisseurKeyReleased(evt);
            }
        });

        tblEntreeStock.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEntreeStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntreeStockMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEntreeStock);
        if (tblEntreeStock.getColumnModel().getColumnCount() > 0) {
            tblEntreeStock.getColumnModel().getColumn(0).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(1).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        lblNombreEntreeStock.setText("jLabel1");

        jLabel1.setText("Fournisseur:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreEntreeStock)
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
                .addComponent(lblNombreEntreeStock)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblEntreeStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntreeStockMouseClicked
        if (evt.getClickCount() == 2) {
            if (getFrameAncetre() != null) {
                try {
                    int row = tblEntreeStock.getSelectedRow();
                    EntreeStock entreeStock = EntreeStockService.getInstance()
                            .selectionnerEntreeStockParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof OperationEntreeStock) {
                        OperationEntreeStock operationEntreeStock = (OperationEntreeStock) getFrameAncetre();
                        operationEntreeStock.entreeStockSelectionne(entreeStock);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblEntreeStockMouseClicked

    private void tfdRechercheEntrepriseFournisseurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheEntrepriseFournisseurKeyReleased
        List<EntreeStock> listeEntreeStocks = new ArrayList();
        for (EntreeStock entreeStock : entreeStocks) {
            if (entreeStock.getFournisseur().getEntreprise().toUpperCase()
                    .startsWith(tfdRechercheEntrepriseFournisseur.getText().toUpperCase())) {
                listeEntreeStocks.add(entreeStock);
            }
        }

        listerEntreeStocks(listeEntreeStocks);
    }//GEN-LAST:event_tfdRechercheEntrepriseFournisseurKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreEntreeStock;
    private javax.swing.JTable tblEntreeStock;
    private javax.swing.JTextField tfdRechercheEntrepriseFournisseur;
    // End of variables declaration//GEN-END:variables
}
