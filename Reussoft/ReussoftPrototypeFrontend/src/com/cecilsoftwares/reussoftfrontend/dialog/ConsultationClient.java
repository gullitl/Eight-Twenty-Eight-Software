package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.ClientService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreClient;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
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
public class ConsultationClient extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private List<Client> clients;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationClient(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        defaultTableModel = (DefaultTableModel) tblClient.getModel();
        dataRows = new Object[2];

        try {
            clients = ClientService.getInstance().listerTousLesClients();
            listerClients(clients);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerClients(List<Client> clients) {
        defaultTableModel.setRowCount(0);
        for (Client client : clients) {
            dataRows[0] = client.getCode();
            dataRows[1] = client.getEntreprise();
            defaultTableModel.addRow(dataRows);
        }
        String formeNombre = clients.size() > 1 ? "Clients" : "Client";
        lblNombreClient.setText(clients.size() + " " + formeNombre);
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

        tfdRechercheEntrepriseClient = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        lblNombreClient = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clients");
        setResizable(false);

        tfdRechercheEntrepriseClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheEntrepriseClientKeyReleased(evt);
            }
        });

        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Entreprise"
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
        tblClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClient);
        if (tblClient.getColumnModel().getColumnCount() > 0) {
            tblClient.getColumnModel().getColumn(0).setResizable(false);
            tblClient.getColumnModel().getColumn(1).setResizable(false);
            tblClient.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        lblNombreClient.setText("jLabel1");

        jLabel1.setText("Nom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreClient)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfdRechercheEntrepriseClient)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheEntrepriseClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreClient)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientMouseClicked
        if (evt.getClickCount() == 2) {
            if (getFrameAncetre() != null) {
                try {
                    int row = tblClient.getSelectedRow();
                    Client client = ClientService.getInstance()
                            .selectionnerClientParCode((int) defaultTableModel.getValueAt(row, 0));
                    if (getFrameAncetre() instanceof RegistreClient) {
                        RegistreClient registreClient = (RegistreClient) getFrameAncetre();
                        registreClient.clientSelectionne(client);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ConsultationClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispose();
        }

    }//GEN-LAST:event_tblClientMouseClicked

    private void tfdRechercheEntrepriseClientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheEntrepriseClientKeyReleased
        List<Client> listeClients = new ArrayList();
        for (Client client : clients) {
            if (client.getEntreprise().toUpperCase()
                    .startsWith(tfdRechercheEntrepriseClient.getText().toUpperCase())) {
                listeClients.add(client);
            }
        }

        listerClients(listeClients);
    }//GEN-LAST:event_tfdRechercheEntrepriseClientKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreClient;
    private javax.swing.JTable tblClient;
    private javax.swing.JTextField tfdRechercheEntrepriseClient;
    // End of variables declaration//GEN-END:variables
}
