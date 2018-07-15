package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftfrontend.form.RegistreCollaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationCollaborateur extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private Collaborateur collaborateur;
    private final List<Collaborateur> collaborateurs;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     * @param collaborateurs
     */
    public ConsultationCollaborateur(java.awt.Frame parent, boolean modal, List<Collaborateur> collaborateurs) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblCollaborateur.getModel();
        dataRows = new Object[2];

        this.collaborateurs = collaborateurs;
        listerCollaborateurs(this.collaborateurs);

    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreCollaborateur) {
                    RegistreCollaborateur registreCollaborateur = (RegistreCollaborateur) frameAncetre;
                    registreCollaborateur.collaborateurSelectionne(collaborateur);
                }
            }
        });
    }

    private void listerCollaborateurs(List<Collaborateur> collaborateurs) {
        defaultTableModel.setRowCount(0);
        collaborateurs.forEach(c -> {
            dataRows[0] = new StringBuilder(c.getPrenom())
                    .append(" ").append(c.getNom())
                    .append(" ").append(c.getPostnom())
                    .append(" ").append(c.getSurnom());
            dataRows[1] = c.getNom();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = collaborateurs.size() > 1 ? "Collaborateurs" : "Collaborateur";
        lblNombreCollaborateur.setText(collaborateurs.size() + " " + formeNombre);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdRechercheNomCollaborateur = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCollaborateur = new javax.swing.JTable();
        lblNombreCollaborateur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Collaborateurs");
        setResizable(false);

        tfdRechercheNomCollaborateur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheNomCollaborateurKeyReleased(evt);
            }
        });

        tblCollaborateur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Nom d'utilisateur"
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
        tblCollaborateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCollaborateurMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCollaborateur);
        if (tblCollaborateur.getColumnModel().getColumnCount() > 0) {
            tblCollaborateur.getColumnModel().getColumn(0).setResizable(false);
            tblCollaborateur.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblCollaborateur.getColumnModel().getColumn(1).setResizable(false);
        }

        lblNombreCollaborateur.setText("jLabel1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreCollaborateur)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheNomCollaborateur)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheNomCollaborateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreCollaborateur)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCollaborateurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCollaborateurMouseClicked
        if (evt.getClickCount() == 2) {
            if (frameAncetre != null) {
                int row = tblCollaborateur.getSelectedRow();

                collaborateur = collaborateurs.get(row);

//                collaborateur = collaborateurs.stream()
//                        .filter(c -> c.getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                        .findFirst().orElse(null);
            }
            dispose();
        }

    }//GEN-LAST:event_tblCollaborateurMouseClicked

    private void tfdRechercheNomCollaborateurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheNomCollaborateurKeyReleased
        List<Collaborateur> listeCollaborateurs = new ArrayList();

        collaborateurs.stream().filter((c) -> (new StringBuilder(c.getPrenom().toUpperCase())
                .append(" ").append(c.getNom().toUpperCase())
                .append(" ").append(c.getPostnom().toUpperCase())
                .append(" ").append(c.getSurnom().toUpperCase()).toString()
                .startsWith(tfdRechercheNomCollaborateur.getText().toUpperCase())))
                .forEachOrdered((c) -> {
                    listeCollaborateurs.add(c);
                });

        listerCollaborateurs(listeCollaborateurs);
    }//GEN-LAST:event_tfdRechercheNomCollaborateurKeyReleased

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreCollaborateur;
    private javax.swing.JTable tblCollaborateur;
    private javax.swing.JTextField tfdRechercheNomCollaborateur;
    // End of variables declaration//GEN-END:variables
}
