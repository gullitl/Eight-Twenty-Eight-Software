package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.operations.OperationEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationEntreeStock extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;

    private EntreeStock entreeStock;
    private final List<EntreeStock> entreesStock;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param entreesStock
     */
    public ConsultationEntreeStock(java.awt.Frame parent, boolean modal, List<EntreeStock> entreesStock) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblEntreeStock.getModel();
        dataRows = new Object[3];

        this.entreesStock = entreesStock;
        listerEntreesStock(this.entreesStock);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof OperationEntreeStock) {
                    OperationEntreeStock operationEntreeStock = (OperationEntreeStock) frameAncetre;
                    operationEntreeStock.entreeStockSelectionnee(entreeStock);
                }
            }
        });
    }

    private void listerEntreesStock(List<EntreeStock> sortiesStock) {
        defaultTableModel.setRowCount(0);
        sortiesStock.forEach(ms -> {
            dataRows[0] = ms.getNumeroEntreeStock();
            dataRows[1] = ms.getFournisseur().getEntreprise();
            dataRows[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ms.getDateHeure());
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = sortiesStock.size() > 1 ? "Entrées stock" : "Entrée stock";
        lblNombreEntreeStock.setText(sortiesStock.size() + " " + formeNombre);
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

        tfdRechercheDateEntreeStock = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEntreeStock = new javax.swing.JTable();
        lblNombreEntreeStock = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrées stock");
        setResizable(false);

        tfdRechercheDateEntreeStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDateEntreeStockKeyReleased(evt);
            }
        });

        tblEntreeStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Fournisseur", "Date/Heure"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tblEntreeStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntreeStockMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEntreeStock);
        if (tblEntreeStock.getColumnModel().getColumnCount() > 0) {
            tblEntreeStock.getColumnModel().getColumn(0).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblEntreeStock.getColumnModel().getColumn(1).setResizable(false);
            tblEntreeStock.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblEntreeStock.getColumnModel().getColumn(2).setResizable(false);
        }

        lblNombreEntreeStock.setText("jLabel1");

        jLabel1.setText("Date:");

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
                        .addComponent(tfdRechercheDateEntreeStock)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheDateEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            if (frameAncetre != null) {
                int row = tblEntreeStock.getSelectedRow();
                entreeStock = entreesStock.get(row);
            }
            dispose();
        }
    }//GEN-LAST:event_tblEntreeStockMouseClicked

    private void tfdRechercheDateEntreeStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDateEntreeStockKeyReleased
        List<EntreeStock> listeEntreesStock = new ArrayList();

        entreesStock.stream().filter((ms) -> (ms.getNumeroEntreeStock()
                .startsWith(tfdRechercheDateEntreeStock.getText())))
                .forEachOrdered((ms) -> {
                    listeEntreesStock.add(ms);
                });

        listerEntreesStock(listeEntreesStock);
    }//GEN-LAST:event_tfdRechercheDateEntreeStockKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreEntreeStock;
    private javax.swing.JTable tblEntreeStock;
    private javax.swing.JTextField tfdRechercheDateEntreeStock;
    // End of variables declaration//GEN-END:variables
}
