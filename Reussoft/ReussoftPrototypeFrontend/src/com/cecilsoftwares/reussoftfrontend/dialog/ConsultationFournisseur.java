package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.OperationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.form.RegistreFournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationFournisseur extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private Fournisseur fournisseur;
    private final List<Fournisseur> fournisseurs;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param fournisseurs
     */
    public ConsultationFournisseur(java.awt.Frame parent, boolean modal, List<Fournisseur> fournisseurs) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblFournisseur.getModel();
        dataRows = new Object[2];

        this.fournisseurs = fournisseurs;
        listerFournisseur(this.fournisseurs);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreFournisseur) {
                    RegistreFournisseur registreFournisseur = (RegistreFournisseur) frameAncetre;
                    registreFournisseur.fournisseurSelectionne(fournisseur);
                } else if (frameAncetre instanceof OperationEntreeStock) {
                    OperationEntreeStock operationEntreeStock = (OperationEntreeStock) frameAncetre;
                    operationEntreeStock.fournisseurSelectionne(fournisseur);
                }
            }
        });
    }

    private void listerFournisseur(List<Fournisseur> fournisseurs) {
        defaultTableModel.setRowCount(0);
        fournisseurs.forEach(f -> {
            dataRows[0] = f.getEntreprise();
            dataRows[1] = f.getResponsable();
            defaultTableModel.addRow(dataRows);
        });
        String formeNombre = fournisseurs.size() > 1 ? "Shops" : "Shop";
        lblNombreFournisseur.setText(fournisseurs.size() + " " + formeNombre);
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
                "Entreprise", "Responsable"
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
        tblFournisseur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFournisseurMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFournisseur);
        if (tblFournisseur.getColumnModel().getColumnCount() > 0) {
            tblFournisseur.getColumnModel().getColumn(0).setResizable(false);
            tblFournisseur.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblFournisseur.getColumnModel().getColumn(1).setResizable(false);
            tblFournisseur.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        lblNombreFournisseur.setText("jLabel1");

        jLabel1.setText("Entreprise:");

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
            if (frameAncetre != null) {
                int row = tblFournisseur.getSelectedRow();
                fournisseur = fournisseurs.get(row);
            }
            dispose();
        }

    }//GEN-LAST:event_tblFournisseurMouseClicked

    private void tfdRechercheEntrepriseFournisseurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheEntrepriseFournisseurKeyReleased
        List<Fournisseur> listeFournisseurs = new ArrayList();

        fournisseurs.stream().filter((cp) -> (cp.getEntreprise().toUpperCase()
                .startsWith(tfdRechercheEntrepriseFournisseur.getText().toUpperCase())))
                .forEachOrdered((cp) -> {
                    listeFournisseurs.add(cp);
                });

        listerFournisseur(listeFournisseurs);
    }//GEN-LAST:event_tfdRechercheEntrepriseFournisseurKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreFournisseur;
    private javax.swing.JTable tblFournisseur;
    private javax.swing.JTextField tfdRechercheEntrepriseFournisseur;
    // End of variables declaration//GEN-END:variables
}
