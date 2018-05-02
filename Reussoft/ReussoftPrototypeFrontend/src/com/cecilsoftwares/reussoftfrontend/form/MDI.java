package com.cecilsoftwares.reussoftfrontend.form;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @author Plamedi L. Lusembo
 */
public class MDI extends javax.swing.JFrame {

    public MDI() {
        initComponents();
    }

    private ConfigurationCompte configurationCompte;
    private OperationDispatch operationDispatch;
    private OperationEntreeStock operationEntreeStock;
    private RapportClient rapportClient;
    private RapportCollaborateur rapportCollaborateur;
    private RapportDispatch rapportDispatch;
    private RapportEntreeStock rapportEntreeStock;
    private RapportFournisseur rapportFournisseur;
    private RapportShop rapportShop;
    private RapportVentes rapportVentes;
    private RegistreCategorieProduit registreCategorieProduit;
    private RegistreClient registreClient;
    private RegistreCollaborateur registreCollaborateur;
    private RegistreFournisseur registreFournisseur;
    private RegistreGroupeUtilisateur registreGroupeUtilisateur;
    private RegistreProduit registreProduit;
    private RegistreReseau registreReseau;
    private RegistreShop registreShop;
    private RegistreUtilisateur registreUtilisateur;
    private AideAPropos aidAPropos;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdp = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiConfigurationMonCompte = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiRegistreShop = new javax.swing.JMenuItem();
        jmiRegistreCategorieProduit = new javax.swing.JMenuItem();
        jmiRegistreProduit = new javax.swing.JMenuItem();
        jmiRegistreFournisseur = new javax.swing.JMenuItem();
        jmiRegistreClient = new javax.swing.JMenuItem();
        jmiRegistreReseau = new javax.swing.JMenuItem();
        jmiRegistreCollaborateur = new javax.swing.JMenuItem();
        jmiRegistreGroupeUtilisateur = new javax.swing.JMenuItem();
        jmiRegistreUtilisateur = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiOperationEntreeStock = new javax.swing.JMenuItem();
        jmiOperationDispatch = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiRapportEntreeStock = new javax.swing.JMenuItem();
        jmiRapportDispatch = new javax.swing.JMenuItem();
        jmiRapportVentes = new javax.swing.JMenuItem();
        jmiRapportShop = new javax.swing.JMenuItem();
        jmiRapportFournisseur = new javax.swing.JMenuItem();
        jmiRapportClient = new javax.swing.JMenuItem();
        jmiRapportCollaborateur = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiAideApropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");

        javax.swing.GroupLayout jdpLayout = new javax.swing.GroupLayout(jdp);
        jdp.setLayout(jdpLayout);
        jdpLayout.setHorizontalGroup(
            jdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jdpLayout.setVerticalGroup(
            jdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        jMenu1.setText("Configurations");

        jmiConfigurationMonCompte.setText("Mon compte");
        jmiConfigurationMonCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConfigurationMonCompteActionPerformed(evt);
            }
        });
        jMenu1.add(jmiConfigurationMonCompte);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Registres");

        jmiRegistreShop.setText("Shop");
        jmiRegistreShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreShopActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreShop);

        jmiRegistreCategorieProduit.setText("Categorie de Produit");
        jMenu3.add(jmiRegistreCategorieProduit);

        jmiRegistreProduit.setText("Produit");
        jMenu3.add(jmiRegistreProduit);

        jmiRegistreFournisseur.setText("Fournisseur");
        jMenu3.add(jmiRegistreFournisseur);

        jmiRegistreClient.setText("Client");
        jMenu3.add(jmiRegistreClient);

        jmiRegistreReseau.setText("Réseau");
        jMenu3.add(jmiRegistreReseau);

        jmiRegistreCollaborateur.setText("Collaborateur");
        jMenu3.add(jmiRegistreCollaborateur);

        jmiRegistreGroupeUtilisateur.setText("Groupe utilisateur");
        jMenu3.add(jmiRegistreGroupeUtilisateur);

        jmiRegistreUtilisateur.setText("Utilisateur");
        jMenu3.add(jmiRegistreUtilisateur);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Operations");

        jmiOperationEntreeStock.setText("Entrée stock");
        jMenu4.add(jmiOperationEntreeStock);

        jmiOperationDispatch.setText("Dispatch");
        jMenu4.add(jmiOperationDispatch);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Rapports");

        jmiRapportEntreeStock.setText("Entrée stock");
        jMenu2.add(jmiRapportEntreeStock);

        jmiRapportDispatch.setText("Dispatch");
        jMenu2.add(jmiRapportDispatch);

        jmiRapportVentes.setText("Ventes");
        jMenu2.add(jmiRapportVentes);

        jmiRapportShop.setText("Shop");
        jMenu2.add(jmiRapportShop);

        jmiRapportFournisseur.setText("Fournisseur");
        jMenu2.add(jmiRapportFournisseur);

        jmiRapportClient.setText("Client");
        jMenu2.add(jmiRapportClient);

        jmiRapportCollaborateur.setText("Collaborateur");
        jMenu2.add(jmiRapportCollaborateur);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Aide");

        jmiAideApropos.setText("À propos");
        jMenu5.add(jmiAideApropos);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiRegistreShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreShopActionPerformed
        if (registreShop == null) {
            registreShop = new RegistreShop();
            jdp.add(registreShop);

            registreShop.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreShop = null;
                }
            });
        }
        registreShop.setLocation(
                jdp.getWidth() / 2 - registreShop.getWidth() / 2,
                jdp.getHeight() / 2 - registreShop.getHeight() / 2);

        registreShop.setVisible(true);
    }//GEN-LAST:event_jmiRegistreShopActionPerformed

    private void jmiConfigurationMonCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfigurationMonCompteActionPerformed
        if (configurationCompte == null) {
            configurationCompte = new ConfigurationCompte();
            jdp.add(configurationCompte);

            configurationCompte.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    configurationCompte = null;
                }
            });
        }
        configurationCompte.setLocation(
                jdp.getWidth() / 2 - configurationCompte.getWidth() / 2,
                jdp.getHeight() / 2 - configurationCompte.getHeight() / 2);

        configurationCompte.setVisible(true);
    }//GEN-LAST:event_jmiConfigurationMonCompteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JDesktopPane jdp;
    private javax.swing.JMenuItem jmiAideApropos;
    private javax.swing.JMenuItem jmiConfigurationMonCompte;
    private javax.swing.JMenuItem jmiOperationDispatch;
    private javax.swing.JMenuItem jmiOperationEntreeStock;
    private javax.swing.JMenuItem jmiRapportClient;
    private javax.swing.JMenuItem jmiRapportCollaborateur;
    private javax.swing.JMenuItem jmiRapportDispatch;
    private javax.swing.JMenuItem jmiRapportEntreeStock;
    private javax.swing.JMenuItem jmiRapportFournisseur;
    private javax.swing.JMenuItem jmiRapportShop;
    private javax.swing.JMenuItem jmiRapportVentes;
    private javax.swing.JMenuItem jmiRegistreCategorieProduit;
    private javax.swing.JMenuItem jmiRegistreClient;
    private javax.swing.JMenuItem jmiRegistreCollaborateur;
    private javax.swing.JMenuItem jmiRegistreFournisseur;
    private javax.swing.JMenuItem jmiRegistreGroupeUtilisateur;
    private javax.swing.JMenuItem jmiRegistreProduit;
    private javax.swing.JMenuItem jmiRegistreReseau;
    private javax.swing.JMenuItem jmiRegistreShop;
    private javax.swing.JMenuItem jmiRegistreUtilisateur;
    // End of variables declaration//GEN-END:variables
}
