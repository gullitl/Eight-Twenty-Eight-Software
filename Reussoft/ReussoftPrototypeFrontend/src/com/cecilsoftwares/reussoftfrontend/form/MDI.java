package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftfrontend.form.configurations.ConfigurationCompte;
import com.cecilsoftwares.reussoftfrontend.form.operations.OperationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.form.operations.OperationDispatch;
import com.cecilsoftwares.reussoftfrontend.form.operations.OperationAlternationCollaborateurShop;
import com.cecilsoftwares.reussoftfrontend.form.operations.OperationVente;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportShop;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportStockProduit;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportDispatch;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportClient;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportCollaborateur;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportFournisseur;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportVentes;
import com.cecilsoftwares.reussoftfrontend.form.rapports.RapportEntreeStock;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreProduit;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreClient;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreCategorieProduit;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreReseau;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreShop;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreProfilUtilisateur;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreFournisseur;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreCollaborateur;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreTaux;
import com.cecilsoftwares.reussoftfrontend.form.aide.AideAPropos;
import com.cecilsoftwares.reussoftbackend.service.SessionUtilisateurService;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @author Plamedi L. Lusembo
 */
public class MDI extends javax.swing.JFrame {

    private final ScheduledExecutorService scheduler;

    public MDI() {
        initComponents();

        enFermantDialog();

        lblUtilisateur.setText(SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getPrenom()
                + " " + SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getNom()
                + " " + SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getPostnom()
                + " (" + SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getNomUtilisateur() + ")");

        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            lblDateHeure.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        }, 1, 1, TimeUnit.SECONDS);

    }

    private void sortir() {
        Object[] options = {"Quitter", "Annuler"};
        int n = JOptionPane.showOptionDialog(null,
                "Êtes-vous sûr de vouloir quitter le système?",
                "Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title

        if (n == 1) {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        } else {
            setVisible(false);
            try {
                SessionUtilisateurKS.getInstance().getSessionUtilisateur().setDateHeure(new Date());
                SessionUtilisateurKS.getInstance().getSessionUtilisateur().setActionEntree(false);
                SessionUtilisateurService.getInstance()
                        .sauvegarderSessionUtilisateur(SessionUtilisateurKS.getInstance().getSessionUtilisateur());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MDI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sortir();
            }
        }
        );
    }

    private ConfigurationCompte configurationCompte;
    private OperationDispatch operationDispatch;
    private OperationAlternationCollaborateurShop operationAlternationCollaborateurShop;
    private OperationVente operationVente;
    private OperationEntreeStock operationEntreeStock;
    private RapportClient rapportClient;
    private RapportCollaborateur rapportCollaborateur;
    private RapportDispatch rapportDispatch;
    private RapportEntreeStock rapportEntreeStock;
    private RapportStockProduit rapportStockProduit;
    private RapportFournisseur rapportFournisseur;
    private RapportShop rapportShop;
    private RapportVentes rapportVentes;
    private RegistreCategorieProduit registreCategorieProduit;
    private RegistreClient registreClient;
    private RegistreCollaborateur registreCollaborateur;
    private RegistreFournisseur registreFournisseur;
    private RegistreProfilUtilisateur registreProfilUtilisateur;
    private RegistreProduit registreProduit;
    private RegistreTaux registreTaux;
    private RegistreReseau registreReseau;
    private RegistreShop registreShop;
    private AideAPropos aideAPropos;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lblDateHeure = new javax.swing.JLabel();
        lblUtilisateur = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiConfigurationMonCompte = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiRegistreShop = new javax.swing.JMenuItem();
        jmiRegistreReseau = new javax.swing.JMenuItem();
        jmiRegistreCategorieProduit = new javax.swing.JMenuItem();
        jmiRegistreProduit = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jmiRegistreFournisseur = new javax.swing.JMenuItem();
        jmiRegistreClient = new javax.swing.JMenuItem();
        jmiRegistreProfilUtilisateur = new javax.swing.JMenuItem();
        jmiRegistreCollaborateur = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiOperationEntreeStock = new javax.swing.JMenuItem();
        jmiOperationDispatch = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmiOperationVente = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiRapportEntreeStock = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jmiRapportDispatch = new javax.swing.JMenuItem();
        jmiRapportVentes = new javax.swing.JMenuItem();
        jmiRapportShop = new javax.swing.JMenuItem();
        jmiRapportFournisseur = new javax.swing.JMenuItem();
        jmiRapportClient = new javax.swing.JMenuItem();
        jmiRapportCollaborateur = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiAideApropos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reussoft - Makservices");

        lblDateHeure.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(656, Short.MAX_VALUE)
                .addComponent(lblDateHeure)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDateHeure)
        );

        lblUtilisateur.setText("jLabel1");

        jDesktopPane.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane.setLayer(lblUtilisateur, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUtilisateur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 462, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jmiRegistreReseau.setText("Réseau");
        jmiRegistreReseau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreReseauActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreReseau);

        jmiRegistreCategorieProduit.setText("Categorie de Produit");
        jmiRegistreCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreCategorieProduitActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreCategorieProduit);

        jmiRegistreProduit.setText("Produit");
        jmiRegistreProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreProduitActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreProduit);

        jMenuItem3.setText("Taux");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jmiRegistreFournisseur.setText("Fournisseur");
        jmiRegistreFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreFournisseurActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreFournisseur);

        jmiRegistreClient.setText("Client");
        jmiRegistreClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreClientActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreClient);

        jmiRegistreProfilUtilisateur.setText("Profil d'utilisateur");
        jmiRegistreProfilUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreProfilUtilisateurActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreProfilUtilisateur);

        jmiRegistreCollaborateur.setText("Collaborateur");
        jmiRegistreCollaborateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistreCollaborateurActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRegistreCollaborateur);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Operations");

        jmiOperationEntreeStock.setText("Entrée stock");
        jmiOperationEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOperationEntreeStockActionPerformed(evt);
            }
        });
        jMenu4.add(jmiOperationEntreeStock);

        jmiOperationDispatch.setText("Dispatch");
        jmiOperationDispatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOperationDispatchActionPerformed(evt);
            }
        });
        jMenu4.add(jmiOperationDispatch);

        jMenuItem1.setText("Alternation de Collaborateurs aux Shops");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jmiOperationVente.setText("Vente");
        jmiOperationVente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOperationVenteActionPerformed(evt);
            }
        });
        jMenu4.add(jmiOperationVente);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Rapports");

        jmiRapportEntreeStock.setText("Entrée stock");
        jmiRapportEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportEntreeStockActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportEntreeStock);

        jMenuItem4.setText("Stock produits");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jmiRapportDispatch.setText("Dispatch");
        jmiRapportDispatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportDispatchActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportDispatch);

        jmiRapportVentes.setText("Ventes");
        jmiRapportVentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportVentesActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportVentes);

        jmiRapportShop.setText("Shop");
        jmiRapportShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportShopActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportShop);

        jmiRapportFournisseur.setText("Fournisseur");
        jmiRapportFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportFournisseurActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportFournisseur);

        jmiRapportClient.setText("Client");
        jmiRapportClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportClientActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportClient);

        jmiRapportCollaborateur.setText("Collaborateur");
        jmiRapportCollaborateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRapportCollaborateurActionPerformed(evt);
            }
        });
        jMenu2.add(jmiRapportCollaborateur);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Aide");

        jmiAideApropos.setText("À propos");
        jmiAideApropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAideAproposActionPerformed(evt);
            }
        });
        jMenu5.add(jmiAideApropos);

        jMenuItem2.setText("Sortir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiRegistreShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreShopActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreShop == null) {
            registreShop = new RegistreShop();
            jDesktopPane.add(registreShop);

            registreShop.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreShop = null;
                }
            });
        }
        registreShop.setLocation(
                jDesktopPane.getWidth() / 2 - registreShop.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreShop.getHeight() / 2);

        registreShop.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreShopActionPerformed

    private void jmiConfigurationMonCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfigurationMonCompteActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (configurationCompte == null) {
            configurationCompte = new ConfigurationCompte();
            jDesktopPane.add(configurationCompte);

            configurationCompte.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    configurationCompte = null;
                }
            });
        }
        configurationCompte.setLocation(
                jDesktopPane.getWidth() / 2 - configurationCompte.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - configurationCompte.getHeight() / 2);

        configurationCompte.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiConfigurationMonCompteActionPerformed

    private void jmiRegistreCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreCategorieProduitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreCategorieProduit == null) {
            registreCategorieProduit = new RegistreCategorieProduit();
            jDesktopPane.add(registreCategorieProduit);

            registreCategorieProduit.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreCategorieProduit = null;
                }
            });
        }
        registreCategorieProduit.setLocation(
                jDesktopPane.getWidth() / 2 - registreCategorieProduit.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreCategorieProduit.getHeight() / 2);

        registreCategorieProduit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreCategorieProduitActionPerformed

    private void jmiRegistreProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreProduitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreProduit == null) {
            registreProduit = new RegistreProduit();
            jDesktopPane.add(registreProduit);

            registreProduit.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreProduit = null;
                }
            });
        }
        registreProduit.setLocation(
                jDesktopPane.getWidth() / 2 - registreProduit.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreProduit.getHeight() / 2);

        registreProduit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreProduitActionPerformed

    private void jmiRegistreFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreFournisseurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreFournisseur == null) {
            registreFournisseur = new RegistreFournisseur();
            jDesktopPane.add(registreFournisseur);

            registreFournisseur.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreFournisseur = null;
                }
            });
        }
        registreFournisseur.setLocation(
                jDesktopPane.getWidth() / 2 - registreFournisseur.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreFournisseur.getHeight() / 2);

        registreFournisseur.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreFournisseurActionPerformed

    private void jmiRegistreClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreClientActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreClient == null) {
            registreClient = new RegistreClient();
            jDesktopPane.add(registreClient);

            registreClient.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreClient = null;
                }
            });
        }
        registreClient.setLocation(
                jDesktopPane.getWidth() / 2 - registreClient.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreClient.getHeight() / 2);

        registreClient.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_jmiRegistreClientActionPerformed

    private void jmiRegistreReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreReseauActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreReseau == null) {
            registreReseau = new RegistreReseau();
            jDesktopPane.add(registreReseau);

            registreReseau.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreReseau = null;
                }
            });
        }
        registreReseau.setLocation(
                jDesktopPane.getWidth() / 2 - registreReseau.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreReseau.getHeight() / 2);

        registreReseau.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreReseauActionPerformed

    private void jmiRegistreCollaborateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreCollaborateurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreCollaborateur == null) {
            registreCollaborateur = new RegistreCollaborateur();
            jDesktopPane.add(registreCollaborateur);

            registreCollaborateur.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreCollaborateur = null;
                }
            });
        }
        registreCollaborateur.setLocation(
                jDesktopPane.getWidth() / 2 - registreCollaborateur.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreCollaborateur.getHeight() / 2);

        registreCollaborateur.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreCollaborateurActionPerformed

    private void jmiRegistreProfilUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistreProfilUtilisateurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreProfilUtilisateur == null) {
            registreProfilUtilisateur = new RegistreProfilUtilisateur();
            jDesktopPane.add(registreProfilUtilisateur);

            registreProfilUtilisateur.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreProfilUtilisateur = null;
                }
            });
        }
        registreProfilUtilisateur.setLocation(
                jDesktopPane.getWidth() / 2 - registreProfilUtilisateur.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreProfilUtilisateur.getHeight() / 2);

        registreProfilUtilisateur.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRegistreProfilUtilisateurActionPerformed

    private void jmiOperationEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOperationEntreeStockActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (operationEntreeStock == null) {
            operationEntreeStock = new OperationEntreeStock();
            jDesktopPane.add(operationEntreeStock);

            operationEntreeStock.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    operationEntreeStock = null;
                }
            });
        }
        operationEntreeStock.setLocation(
                jDesktopPane.getWidth() / 2 - operationEntreeStock.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - operationEntreeStock.getHeight() / 2);

        operationEntreeStock.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiOperationEntreeStockActionPerformed

    private void jmiOperationDispatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOperationDispatchActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (operationDispatch == null) {
            operationDispatch = new OperationDispatch();
            jDesktopPane.add(operationDispatch);

            operationDispatch.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    operationDispatch = null;
                }
            });
        }
        operationDispatch.setLocation(
                jDesktopPane.getWidth() / 2 - operationDispatch.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - operationDispatch.getHeight() / 2);

        operationDispatch.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiOperationDispatchActionPerformed

    private void jmiRapportEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportEntreeStockActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportEntreeStock == null) {
            rapportEntreeStock = new RapportEntreeStock();
            jDesktopPane.add(rapportEntreeStock);

            rapportEntreeStock.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportEntreeStock = null;
                }
            });
        }
        rapportEntreeStock.setLocation(
                jDesktopPane.getWidth() / 2 - rapportEntreeStock.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportEntreeStock.getHeight() / 2);

        rapportEntreeStock.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportEntreeStockActionPerformed

    private void jmiRapportDispatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportDispatchActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportDispatch == null) {
            rapportDispatch = new RapportDispatch();
            jDesktopPane.add(rapportDispatch);

            rapportDispatch.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportDispatch = null;
                }
            });
        }
        rapportDispatch.setLocation(
                jDesktopPane.getWidth() / 2 - rapportDispatch.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportDispatch.getHeight() / 2);

        rapportDispatch.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportDispatchActionPerformed

    private void jmiRapportVentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportVentesActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportVentes == null) {
            rapportVentes = new RapportVentes();
            jDesktopPane.add(rapportVentes);

            rapportVentes.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportVentes = null;
                }
            });
        }
        rapportVentes.setLocation(
                jDesktopPane.getWidth() / 2 - rapportVentes.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportVentes.getHeight() / 2);

        rapportVentes.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportVentesActionPerformed

    private void jmiRapportShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportShopActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportShop == null) {
            rapportShop = new RapportShop();
            jDesktopPane.add(rapportShop);

            rapportShop.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportShop = null;
                }
            });
        }
        rapportShop.setLocation(
                jDesktopPane.getWidth() / 2 - rapportShop.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportShop.getHeight() / 2);

        rapportShop.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportShopActionPerformed

    private void jmiRapportFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportFournisseurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportFournisseur == null) {
            rapportFournisseur = new RapportFournisseur();
            jDesktopPane.add(rapportFournisseur);

            rapportFournisseur.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportFournisseur = null;
                }
            });
        }
        rapportFournisseur.setLocation(
                jDesktopPane.getWidth() / 2 - rapportFournisseur.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportFournisseur.getHeight() / 2);

        rapportFournisseur.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportFournisseurActionPerformed

    private void jmiRapportClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportClientActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportClient == null) {
            rapportClient = new RapportClient();
            jDesktopPane.add(rapportClient);

            rapportClient.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportClient = null;
                }
            });
        }
        rapportClient.setLocation(
                jDesktopPane.getWidth() / 2 - rapportClient.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportClient.getHeight() / 2);

        rapportClient.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportClientActionPerformed

    private void jmiRapportCollaborateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRapportCollaborateurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportCollaborateur == null) {
            rapportCollaborateur = new RapportCollaborateur();
            jDesktopPane.add(rapportCollaborateur);

            rapportCollaborateur.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportCollaborateur = null;
                }
            });
        }
        rapportCollaborateur.setLocation(
                jDesktopPane.getWidth() / 2 - rapportCollaborateur.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportCollaborateur.getHeight() / 2);

        rapportCollaborateur.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiRapportCollaborateurActionPerformed

    private void jmiAideAproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAideAproposActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (aideAPropos == null) {
            aideAPropos = new AideAPropos();
            jDesktopPane.add(aideAPropos);

            aideAPropos.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    aideAPropos = null;
                }
            });
        }
        aideAPropos.setLocation(
                jDesktopPane.getWidth() / 2 - aideAPropos.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - aideAPropos.getHeight() / 2);

        aideAPropos.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiAideAproposActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (operationAlternationCollaborateurShop == null) {
            operationAlternationCollaborateurShop = new OperationAlternationCollaborateurShop();
            jDesktopPane.add(operationAlternationCollaborateurShop);

            operationAlternationCollaborateurShop.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    operationAlternationCollaborateurShop = null;
                }
            });
        }
        operationAlternationCollaborateurShop.setLocation(
                jDesktopPane.getWidth() / 2 - operationAlternationCollaborateurShop.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - operationAlternationCollaborateurShop.getHeight() / 2);

        operationAlternationCollaborateurShop.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiOperationVenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOperationVenteActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (operationVente == null) {
            operationVente = new OperationVente();
            jDesktopPane.add(operationVente);

            operationVente.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    operationVente = null;
                }
            });
        }
        operationVente.setLocation(
                jDesktopPane.getWidth() / 2 - operationVente.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - operationVente.getHeight() / 2);

        operationVente.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jmiOperationVenteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        sortir();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (registreTaux == null) {
            registreTaux = new RegistreTaux();
            jDesktopPane.add(registreTaux);

            registreTaux.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    registreTaux = null;
                }
            });
        }
        registreTaux.setLocation(
                jDesktopPane.getWidth() / 2 - registreTaux.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - registreTaux.getHeight() / 2);

        registreTaux.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (rapportStockProduit == null) {
            rapportStockProduit = new RapportStockProduit();
            jDesktopPane.add(rapportStockProduit);

            rapportStockProduit.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    rapportStockProduit = null;
                }
            });
        }
        rapportStockProduit.setLocation(
                jDesktopPane.getWidth() / 2 - rapportStockProduit.getWidth() / 2,
                jDesktopPane.getHeight() / 2 - rapportStockProduit.getHeight() / 2);

        rapportStockProduit.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jmiAideApropos;
    private javax.swing.JMenuItem jmiConfigurationMonCompte;
    private javax.swing.JMenuItem jmiOperationDispatch;
    private javax.swing.JMenuItem jmiOperationEntreeStock;
    private javax.swing.JMenuItem jmiOperationVente;
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
    private javax.swing.JMenuItem jmiRegistreProduit;
    private javax.swing.JMenuItem jmiRegistreProfilUtilisateur;
    private javax.swing.JMenuItem jmiRegistreReseau;
    private javax.swing.JMenuItem jmiRegistreShop;
    private javax.swing.JLabel lblDateHeure;
    private javax.swing.JLabel lblUtilisateur;
    // End of variables declaration//GEN-END:variables
}
