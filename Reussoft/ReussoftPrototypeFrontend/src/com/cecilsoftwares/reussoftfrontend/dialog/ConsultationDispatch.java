package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.operations.OperationDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationDispatch extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;

    private Dispatch dispatch;
    private final List<Dispatch> dispatchs;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param dispatchs
     */
    public ConsultationDispatch(java.awt.Frame parent, boolean modal, List<Dispatch> dispatchs) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblDispatch.getModel();
        dataRows = new Object[2];

        this.dispatchs = dispatchs;
        listerMouvementsStock(this.dispatchs);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof OperationDispatch) {
                    OperationDispatch operationDispatch = (OperationDispatch) frameAncetre;
                    operationDispatch.dispatchSelectionne(dispatch);
                }
            }
        });
    }

    private void listerMouvementsStock(List<Dispatch> mouvementsStock) {
        defaultTableModel.setRowCount(0);
        mouvementsStock.forEach(ms -> {
            dataRows[0] = ms.getNumeroDispatch();
            dataRows[1] = ms.getDateHeure();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = mouvementsStock.size() > 1 ? "Dispatchs" : "Dispatch";
        lblNombreDispatch.setText(mouvementsStock.size() + " " + formeNombre);
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

        tfdRechercheDateDispatch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDispatch = new javax.swing.JTable();
        lblNombreDispatch = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrées stock");
        setResizable(false);

        tfdRechercheDateDispatch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDateDispatchKeyReleased(evt);
            }
        });

        tblDispatch.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDispatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDispatchMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDispatch);
        if (tblDispatch.getColumnModel().getColumnCount() > 0) {
            tblDispatch.getColumnModel().getColumn(0).setResizable(false);
            tblDispatch.getColumnModel().getColumn(1).setResizable(false);
        }

        lblNombreDispatch.setText("jLabel1");

        jLabel1.setText("Date:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreDispatch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheDateDispatch)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(tfdRechercheDateDispatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreDispatch)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblDispatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDispatchMouseClicked
        if (evt.getClickCount() == 2) {
            if (frameAncetre != null) {
                int row = tblDispatch.getSelectedRow();

                dispatch = dispatchs.get(row);

//                dispatch = dispatchs.stream()
//                        .filter(cp -> cp.getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                        .findFirst().orElse(null);
            }
            dispose();
        }
    }//GEN-LAST:event_tblDispatchMouseClicked

    private void tfdRechercheDateDispatchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDateDispatchKeyReleased
        List<Dispatch> listeMouvementsStock = new ArrayList();

        dispatchs.stream().filter((ms) -> (ms.getDateHeure().equals(tfdRechercheDateDispatch.getText().toUpperCase())))
                .forEachOrdered((ms) -> {
                    listeMouvementsStock.add(ms);
                });

        listerMouvementsStock(listeMouvementsStock);
    }//GEN-LAST:event_tfdRechercheDateDispatchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreDispatch;
    private javax.swing.JTable tblDispatch;
    private javax.swing.JTextField tfdRechercheDateDispatch;
    // End of variables declaration//GEN-END:variables
}
