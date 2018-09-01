package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreProduit;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreReseau;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationReseau extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private Reseau reseau;
    private final List<Reseau> reseaux;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param reseaux
     */
    public ConsultationReseau(java.awt.Frame parent, boolean modal, List<Reseau> reseaux) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblReseau.getModel();
        dataRows = new Object[2];

        this.reseaux = reseaux;
        listerReseaux(this.reseaux);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreReseau) {
                    RegistreReseau registreCategorieProduit = (RegistreReseau) frameAncetre;
                    registreCategorieProduit.reseauSelectionne(reseau);
                } else if (frameAncetre instanceof RegistreProduit) {
                    RegistreProduit registreProduit = (RegistreProduit) frameAncetre;
                    registreProduit.reseauSelectionne(reseau);
                }
            }
        });
    }

    private void listerReseaux(List<Reseau> reseaux) {
        defaultTableModel.setRowCount(0);
        reseaux.forEach(r -> {
            dataRows[0] = r.getNom();
            dataRows[1] = r.getNomAbrege();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = reseaux.size() > 1 ? "Reseaus" : "Reseau";
        lblNombreReseau.setText(reseaux.size() + " " + formeNombre);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdRechercheNomReseau = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReseau = new javax.swing.JTable();
        lblNombreReseau = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reseaux");
        setResizable(false);

        tfdRechercheNomReseau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheNomReseauKeyReleased(evt);
            }
        });

        tblReseau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Nom abrégé"
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
        tblReseau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReseauMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblReseau);
        if (tblReseau.getColumnModel().getColumnCount() > 0) {
            tblReseau.getColumnModel().getColumn(0).setResizable(false);
            tblReseau.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblReseau.getColumnModel().getColumn(1).setResizable(false);
        }

        lblNombreReseau.setText("jLabel1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreReseau)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheNomReseau)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(tfdRechercheNomReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreReseau)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblReseauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReseauMouseClicked
        if (evt.getClickCount() == 2) {
            if (frameAncetre != null) {
                int row = tblReseau.getSelectedRow();
                reseau = reseaux.get(row);

            }
            dispose();
        }

    }//GEN-LAST:event_tblReseauMouseClicked

    private void tfdRechercheNomReseauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheNomReseauKeyReleased
        List<Reseau> listeReseaux = new ArrayList();

        reseaux.stream().filter((r) -> (r.getNom().toUpperCase()
                .startsWith(tfdRechercheNomReseau.getText().toUpperCase())))
                .forEachOrdered((cp) -> {
                    listeReseaux.add(cp);
                });

        listerReseaux(listeReseaux);
    }//GEN-LAST:event_tfdRechercheNomReseauKeyReleased

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreReseau;
    private javax.swing.JTable tblReseau;
    private javax.swing.JTextField tfdRechercheNomReseau;
    // End of variables declaration//GEN-END:variables
}
