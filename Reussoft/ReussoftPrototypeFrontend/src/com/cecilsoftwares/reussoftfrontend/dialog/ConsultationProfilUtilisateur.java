package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.ProfilUtilisateurService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreCollaborateur;
import com.cecilsoftwares.reussoftfrontend.form.RegistreProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class ConsultationProfilUtilisateur extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private ProfilUtilisateur profilUtilisateur;
    private List<ProfilUtilisateur> profilsUtilisateur;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationProfilUtilisateur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblProfilUtilisateur.getModel();
        dataRows = new Object[2];

        chargementProfilsUtilisateur();
    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreProfilUtilisateur) {
                    RegistreProfilUtilisateur registreProfilUtilisateur = (RegistreProfilUtilisateur) frameAncetre;
                    registreProfilUtilisateur.profilUtilisateurSelectionne(profilUtilisateur);
                } else if (frameAncetre instanceof RegistreCollaborateur) {
                    RegistreCollaborateur registreCollaborateur = (RegistreCollaborateur) frameAncetre;
                    registreCollaborateur.profilUtilisateurSelectionne(profilUtilisateur);
                }
            }
        });
    }

    private void chargementProfilsUtilisateur() {
        try {
            profilsUtilisateur = ProfilUtilisateurService.getInstance().listerTousLesProfilUtilisateurs();
            listerProfilsUtilisateur(profilsUtilisateur);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerProfilsUtilisateur(List<ProfilUtilisateur> profilsUtilisateur) {
        defaultTableModel.setRowCount(0);

        profilsUtilisateur.forEach(pu -> {
            dataRows[0] = pu.getCode();
            dataRows[1] = pu.getDescription();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = profilsUtilisateur.size() > 1 ? "Shops" : "Shop";
        lblNombreProfilUtilisateur.setText(profilsUtilisateur.size() + " " + formeNombre);
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

        tfdRechercheDescriptionProfilUtilisateur = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProfilUtilisateur = new javax.swing.JTable();
        lblNombreProfilUtilisateur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profils d'utilisateurs");
        setResizable(false);

        tfdRechercheDescriptionProfilUtilisateur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDescriptionProfilUtilisateurKeyReleased(evt);
            }
        });

        tblProfilUtilisateur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description"
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
        tblProfilUtilisateur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfilUtilisateurMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProfilUtilisateur);
        if (tblProfilUtilisateur.getColumnModel().getColumnCount() > 0) {
            tblProfilUtilisateur.getColumnModel().getColumn(0).setResizable(false);
            tblProfilUtilisateur.getColumnModel().getColumn(1).setResizable(false);
            tblProfilUtilisateur.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        lblNombreProfilUtilisateur.setText("jLabel1");

        jLabel1.setText("Description:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreProfilUtilisateur)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheDescriptionProfilUtilisateur)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheDescriptionProfilUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreProfilUtilisateur)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblProfilUtilisateurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfilUtilisateurMouseClicked
        if (evt.getClickCount() == 2) {
            selectionnerProfilUtilisateur();
            dispose();
        }

    }//GEN-LAST:event_tblProfilUtilisateurMouseClicked

    private void selectionnerProfilUtilisateur() {
        if (frameAncetre != null) {
            int row = tblProfilUtilisateur.getSelectedRow();

            profilUtilisateur = profilsUtilisateur.stream()
                    .filter(cp -> cp.getCode() == (int) defaultTableModel.getValueAt(row, 0))
                    .findFirst().orElse(null);
        }
    }

    private void tfdRechercheDescriptionProfilUtilisateurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDescriptionProfilUtilisateurKeyReleased
        List<ProfilUtilisateur> listeProfilsUtilisateur = new ArrayList();

        profilsUtilisateur.stream().filter((cp) -> (cp.getDescription().toUpperCase()
                .startsWith(tfdRechercheDescriptionProfilUtilisateur.getText().toUpperCase())))
                .forEachOrdered((cp) -> {
                    listeProfilsUtilisateur.add(cp);
                });

        listerProfilsUtilisateur(listeProfilsUtilisateur);
    }//GEN-LAST:event_tfdRechercheDescriptionProfilUtilisateurKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreProfilUtilisateur;
    private javax.swing.JTable tblProfilUtilisateur;
    private javax.swing.JTextField tfdRechercheDescriptionProfilUtilisateur;
    // End of variables declaration//GEN-END:variables
}
