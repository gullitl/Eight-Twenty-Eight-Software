package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.ReseauService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreProduit;
import com.cecilsoftwares.reussoftfrontend.form.RegistreReseau;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
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
public class ConsultationReseau extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<Reseau> reseaux;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationReseau(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblReseau.getModel();
        dataRows = new Object[2];

        try {
            reseaux = ReseauService.getInstance().listerTousLesReseaus();
            listerReseaux(reseaux);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerReseaux(List<Reseau> reseaus) {
        defaultTableModel.setRowCount(0);
        for (Reseau reseau : reseaus) {
            dataRows[0] = reseau.getCode();
            dataRows[1] = reseau.getNom();
            defaultTableModel.addRow(dataRows);
        }
        String formeNombre = reseaus.size() > 1 ? "Reseaus" : "Reseau";
        lblNombreReseau.setText(reseaus.size() + " " + formeNombre);
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

        tfdRechercheNomReseau = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReseau = new javax.swing.JTable();
        lblNombreReseau = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre d'Utilisateur");
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
        tblReseau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReseauMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblReseau);
        if (tblReseau.getColumnModel().getColumnCount() > 0) {
            tblReseau.getColumnModel().getColumn(0).setResizable(false);
            tblReseau.getColumnModel().getColumn(0).setHeaderValue("Code");
            tblReseau.getColumnModel().getColumn(1).setResizable(false);
            tblReseau.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblReseau.getColumnModel().getColumn(1).setHeaderValue("Nom");
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
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheNomReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            if (getFrameAncetre() != null) {
                try {
                    int row = tblReseau.getSelectedRow();
                    Reseau reseau = ReseauService.getInstance()
                            .selectionnerReseauParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof RegistreReseau) {
                        RegistreReseau registreReseau = (RegistreReseau) getFrameAncetre();
                        registreReseau.reseauSelectionne(reseau);
                    } else if (getFrameAncetre() instanceof RegistreProduit) {
                        RegistreProduit registreProduit = (RegistreProduit) getFrameAncetre();
                        registreProduit.reseauSelectionne(reseau);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationReseau.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblReseauMouseClicked

    private void tfdRechercheNomReseauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheNomReseauKeyReleased
        List<Reseau> listeReseaus = new ArrayList();
        for (Reseau reseau : reseaux) {
            if (reseau.getNom().toUpperCase()
                    .startsWith(tfdRechercheNomReseau.getText().toUpperCase())) {
                listeReseaus.add(reseau);
            }
        }

        listerReseaux(listeReseaus);
    }//GEN-LAST:event_tfdRechercheNomReseauKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreReseau;
    private javax.swing.JTable tblReseau;
    private javax.swing.JTextField tfdRechercheNomReseau;
    // End of variables declaration//GEN-END:variables
}
