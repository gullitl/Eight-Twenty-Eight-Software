package com.cecilsoftwares.reussoftfrontend.view.dialog;

import com.cecilsoftwares.reussoftfrontend.view.form.operations.OperationVente;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationSortieStock extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;

    private SortieStock sortieStock;
    private final List<SortieStock> sortiesStock;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param sortiesStock
     */
    public ConsultationSortieStock(java.awt.Frame parent, boolean modal, List<SortieStock> sortiesStock) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblSortieStock.getModel();
        dataRows = new Object[2];

        this.sortiesStock = sortiesStock;
        listerSortiesStock(this.sortiesStock);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof OperationVente) {
                    OperationVente operationVente = (OperationVente) frameAncetre;
                    operationVente.sortieStockSelectionnee(sortieStock);
                }
            }
        });
    }

    private void listerSortiesStock(List<SortieStock> sortiesStock) {
        defaultTableModel.setRowCount(0);
        sortiesStock.forEach(ms -> {
            dataRows[0] = ms.getNumeroSortieStock();
            dataRows[1] = ms.getDateHeure();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = sortiesStock.size() > 1 ? "Ventes" : "Vente";
        lblNombreVente.setText(sortiesStock.size() + " " + formeNombre);
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

        tfdRechercheDateVente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSortieStock = new javax.swing.JTable();
        lblNombreVente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrées stock");
        setResizable(false);

        tfdRechercheDateVente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDateVenteKeyReleased(evt);
            }
        });

        tblSortieStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Date/Heure"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
        tblSortieStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSortieStockMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSortieStock);
        if (tblSortieStock.getColumnModel().getColumnCount() > 0) {
            tblSortieStock.getColumnModel().getColumn(0).setResizable(false);
            tblSortieStock.getColumnModel().getColumn(1).setResizable(false);
        }

        lblNombreVente.setText("jLabel1");

        jLabel1.setText("Date:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreVente)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheDateVente)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(tfdRechercheDateVente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreVente)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblSortieStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSortieStockMouseClicked
        if (evt.getClickCount() == 2) {
            if (frameAncetre != null) {
                int row = tblSortieStock.getSelectedRow();
                sortieStock = sortiesStock.get(row);
//                sortieStock = sortiesStock.stream()
//                        .filter(cp -> cp.getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                        .findFirst().orElse(null);
            }
            dispose();
        }
    }//GEN-LAST:event_tblSortieStockMouseClicked

    private void tfdRechercheDateVenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDateVenteKeyReleased
        List<SortieStock> listeSortiesStock = new ArrayList();

        sortiesStock.stream().filter((ms) -> (ms.getDateHeure().equals(tfdRechercheDateVente.getText().toUpperCase())))
                .forEachOrdered((ms) -> {
                    listeSortiesStock.add(ms);
                });

        listerSortiesStock(listeSortiesStock);
    }//GEN-LAST:event_tfdRechercheDateVenteKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreVente;
    private javax.swing.JTable tblSortieStock;
    private javax.swing.JTextField tfdRechercheDateVente;
    // End of variables declaration//GEN-END:variables
}
