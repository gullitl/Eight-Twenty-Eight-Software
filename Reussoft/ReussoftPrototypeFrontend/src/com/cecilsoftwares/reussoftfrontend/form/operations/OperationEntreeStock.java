package com.cecilsoftwares.reussoftfrontend.form.operations;

import com.cecilsoftwares.reussoftfrontend.form.registres.RegistrePrixAchatRaccourcis;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreShop;
import com.cecilsoftwares.reussoftfrontend.form.registres.RegistreTaux;
import com.cecilsoftwares.reussoftbackend.service.EntreeStockService;
import com.cecilsoftwares.reussoftbackend.service.FournisseurService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.StockProduitService;
import com.cecilsoftwares.reussoftbackend.service.TauxService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationEntreeStock;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationFournisseur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class OperationEntreeStock extends JInternalFrame {

    private String idEntreeStock;
    private String idFournisseur;
    private boolean modeEdition;
    private boolean modeEditionItemEntreeStock;

    private TauxCarte tauxCarte;

    private boolean btnConsulterEntreeStockClickable;
    private boolean btnConsulterFournisseurClickable;
    private boolean btnConsulterProduitClickable;
    private boolean btnAjouterProduitClickable;
    private boolean btnEffacerChampsProduitsClickable;
    private boolean tblItemsEntreeStockClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    private Produit produitSelectionne;
    private List<ItemEntreeStock> itemsEntreeStock;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    public OperationEntreeStock() {
        initComponents();

        itemsEntreeStock = new ArrayList();

        defaultTableModel = (DefaultTableModel) tblItemsEntreeStock.getModel();
        dataRows = new Object[4];

        effacerFormulaire();
        onInit();
    }

    private void onInit() {
        new Thread() {
            @Override
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                habiliterComposantFormulaire(false);
                try {
                    tauxCarte = TauxService.getInstance()
                            .selectionnerTauxCarteParId(SessionUtilisateurKS.getInstance()
                                    .getSessionUtilisateur().getShop().getTauxCarte().getId());

                    lblTauxCarte.setText("Taux cartes: " + tauxCarte.getValeur().toString());

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegistreTaux.class.getName()).log(Level.SEVERE, null, ex);
                }

                setCursor(Cursor.getDefaultCursor());
                habiliterComposantFormulaire(true);
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tfdDescriptionProduit = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblProduitStockActuel = new javax.swing.JLabel();
        lblPrixAchat = new javax.swing.JLabel();
        btnAjouterProduit = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsEntreeStock = new javax.swing.JTable();
        btnEffacerChampsProduits = new javax.swing.JButton();
        lblNombreItemEntreeStock = new javax.swing.JLabel();
        btnEditarPrixAchat = new javax.swing.JButton();
        lblTotalAPayer = new javax.swing.JLabel();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        tfdNumeroEntreeStock = new javax.swing.JTextField();
        btnConsulterEntreeStock = new javax.swing.JButton();
        lblTauxCarte = new javax.swing.JLabel();
        btnConsulterFournisseur = new javax.swing.JButton();
        tfdEntrepriseFournisseur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfdValeurUSD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfdValeurFC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrée Stock");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        tfdDescriptionProduit.setEditable(false);

        btnConsulterProduit.setText("...");
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        jLabel12.setText("Produit:");

        lblProduitStockActuel.setText("jLabel5");

        lblPrixAchat.setText("labelx");

        btnAjouterProduit.setText("+");
        btnAjouterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterProduitActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantité:");

        tblItemsEntreeStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produit", "Quantité", "Prix d'achat ($)", "Total ($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemsEntreeStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsEntreeStockMouseClicked(evt);
            }
        });
        tblItemsEntreeStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblItemsEntreeStockKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemsEntreeStock);
        if (tblItemsEntreeStock.getColumnModel().getColumnCount() > 0) {
            tblItemsEntreeStock.getColumnModel().getColumn(0).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblItemsEntreeStock.getColumnModel().getColumn(1).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblItemsEntreeStock.getColumnModel().getColumn(2).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(3).setResizable(false);
            tblItemsEntreeStock.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        btnEffacerChampsProduits.setText("<-");
        btnEffacerChampsProduits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerChampsProduitsActionPerformed(evt);
            }
        });

        lblNombreItemEntreeStock.setText("jLabel2");

        btnEditarPrixAchat.setText("s");
        btnEditarPrixAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPrixAchatActionPerformed(evt);
            }
        });

        lblTotalAPayer.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAjouterProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEffacerChampsProduits, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblNombreItemEntreeStock)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotalAPayer))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfdDescriptionProduit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterProduit))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(129, 129, 129)
                                        .addComponent(lblPrixAchat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEditarPrixAchat)
                                        .addGap(49, 49, 49)
                                        .addComponent(lblProduitStockActuel)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescriptionProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProduit))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrixAchat)
                        .addComponent(lblProduitStockActuel)
                        .addComponent(btnEditarPrixAchat))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterProduit)
                    .addComponent(btnEffacerChampsProduits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreItemEntreeStock)
                    .addComponent(lblTotalAPayer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEffacerFormulaire.setText("EFFACER");
        btnEffacerFormulaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerFormulaireActionPerformed(evt);
            }
        });

        btnEnregistrer.setText("ENREGISTRER");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Numéro:");

        tfdNumeroEntreeStock.setEditable(false);

        btnConsulterEntreeStock.setText("...");
        btnConsulterEntreeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterEntreeStockActionPerformed(evt);
            }
        });

        lblTauxCarte.setText("jLabel10");

        btnConsulterFournisseur.setText("...");
        btnConsulterFournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterFournisseurActionPerformed(evt);
            }
        });

        tfdEntrepriseFournisseur.setEditable(false);

        jLabel1.setText("Fournisseur:");

        jLabel2.setText("Valeur payée:");

        tfdValeurUSD.setEditable(false);

        jLabel3.setText("+");

        tfdValeurFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdValeurFCKeyReleased(evt);
            }
        });

        jLabel5.setText("$");

        jLabel6.setText("FC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfdNumeroEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnConsulterEntreeStock))
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTauxCarte)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdEntrepriseFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterFournisseur))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdValeurUSD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdValeurFC, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNumeroEntreeStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterEntreeStock)
                    .addComponent(lblTauxCarte))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdEntrepriseFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterFournisseur))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdValeurUSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tfdValeurFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        if (!btnEnregistrerClickable || !isInformationObligatoiresRemplies()) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        EntreeStock entreeStock = new EntreeStock(idEntreeStock);
        entreeStock.setNumeroEntreeStock(tfdNumeroEntreeStock.getText());
        entreeStock.setFournisseur(new Fournisseur(idFournisseur));
        entreeStock.setValeurTotalCoutUSD(new BigDecimal(tfdValeurUSD.getText()));
        entreeStock.setValeurTotalCoutFC(new BigDecimal(tfdValeurFC.getText()));
        entreeStock.setValeurTauxCarte(tauxCarte.getValeur());
        entreeStock.setItemsEntreeStock(itemsEntreeStock);

        try {
            if (EntreeStockService.getInstance().enregistrerEntreeStock(entreeStock)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Client");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnAjouterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterProduitActionPerformed

        if (spnQuantiteProduit.getValue().toString().equals("0") || spnQuantiteProduit.getValue().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantité incorrect!");
        } else {
            if (modeEditionItemEntreeStock) {
                for (ItemEntreeStock ies : itemsEntreeStock) {
                    if (ies.getProduit().getId().equals(produitSelectionne.getId())) {
                        ies.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));
                        break;
                    }
                }

            } else {

                ItemEntreeStock itemEntreeStock = new ItemEntreeStock(new EntreeStock(idEntreeStock), produitSelectionne);
                itemEntreeStock.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));

                for (ItemEntreeStock ies : itemsEntreeStock) {
                    if (ies.getProduit().getId().equals(itemEntreeStock.getProduit().getId())) {
                        JOptionPane.showMessageDialog(null, "Ce produit a déjà été ajouté");
                        return;
                    }
                }

                itemsEntreeStock.add(itemEntreeStock);
            }

            listerItemsEntreeStock();

            effacerChampsItemStock();
        }

    }//GEN-LAST:event_btnAjouterProduitActionPerformed

    private void btnConsulterEntreeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterEntreeStockActionPerformed
        if (!btnConsulterEntreeStockClickable) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {
            List<EntreeStock> entreesStock = EntreeStockService.getInstance().listerTousLesEntreesStockAvecItems();
            ConsultationEntreeStock consultationEntreeStock = new ConsultationEntreeStock(null, true, entreesStock);
            consultationEntreeStock.setFrameAncetre(this);
            consultationEntreeStock.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterEntreeStockActionPerformed

    public void entreeStockSelectionnee(EntreeStock entreeStock) {
        if (entreeStock == null) {
            return;
        }
        idEntreeStock = entreeStock.getId();
        tfdNumeroEntreeStock.setText(entreeStock.getNumeroEntreeStock());
        tfdEntrepriseFournisseur.setText(new StringBuilder(entreeStock.getFournisseur().getEntreprise()).append(" - ")
                .append(entreeStock.getFournisseur().getResponsable()).toString());

        lblTauxCarte.setText("Taux carte:" + "Valeur");

        idFournisseur = entreeStock.getFournisseur().getId();

        itemsEntreeStock = entreeStock.getItemsEntreeStock();

        //Lister
        BigDecimal totalAPayer = new BigDecimal("0");
        defaultTableModel.setRowCount(0);

        for (ItemEntreeStock ies : itemsEntreeStock) {
            dataRows[0] = ies.getProduit().getDescription();
            dataRows[1] = ies.getQuantiteProduit();
            dataRows[2] = Double.parseDouble(ies.getProduit().getPrixAchatProduit().getValeurUSD().toString());
            dataRows[3] = Double.parseDouble(ies.getProduit().getPrixAchatProduit().getValeurUSD().multiply(ies.getQuantiteProduit()).toString());

            totalAPayer = totalAPayer.add(ies.getProduit().getPrixAchatProduit().getValeurUSD().multiply(ies.getQuantiteProduit()));
            defaultTableModel.addRow(dataRows);
        }

        String formeNombre = itemsEntreeStock.size() > 1 ? "Items" : "Item";
        lblNombreItemEntreeStock.setText(itemsEntreeStock.size() + " " + formeNombre);

        lblTotalAPayer.setText(new StringBuilder("Total à payer: $")
                .append(totalAPayer.toString()).toString());

        tfdValeurUSD.setText(entreeStock.getValeurTotalCoutUSD().toString());
        if (entreeStock.getValeurTotalCoutFC().toString().equals("0.00")) {
            tfdValeurFC.setText("0.00");
            tfdValeurFC.setEditable(false);
        } else {
            tfdValeurFC.setText(entreeStock.getValeurTotalCoutFC().toString());
            tfdValeurFC.setEditable(true);
        }

        modeEdition = true;
        btnEnregistrer.setText("ACTUALISER");

    }

    private void btnConsulterFournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterFournisseurActionPerformed
        if (!btnConsulterFournisseurClickable) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {

            ConsultationFournisseur consultationFournisseur = new ConsultationFournisseur(null, true, FournisseurService.getInstance()
                    .listerTousLesFournisseurs());
            consultationFournisseur.setFrameAncetre(this);
            consultationFournisseur.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterFournisseurActionPerformed

    public void fournisseurSelectionne(Fournisseur fournisseur) {
        if (fournisseur != null) {
            idFournisseur = fournisseur.getId();
            tfdEntrepriseFournisseur.setText(new StringBuilder(fournisseur.getEntreprise())
                    .append(" - ").append(fournisseur.getResponsable()).toString());
        }
    }

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (!btnConsulterProduitClickable) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        try {

            ConsultationProduit consultationProduit = new ConsultationProduit(null, true, ProduitService.getInstance()
                    .listerTousLesProduits());
            consultationProduit.setFrameAncetre(this);
            consultationProduit.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    public void setProduitSelectionne(Produit produit) {
        if (produit == null) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        produitSelectionne = produit;
        tfdDescriptionProduit.setText(produitSelectionne.getDescription());
        lblPrixAchat.setText(new StringBuilder("Prix d'achat: $")
                .append(produitSelectionne.getPrixAchatProduit().getValeurUSD().toString()).toString());

        btnEditarPrixAchat.setVisible(true);

        try {

            lblProduitStockActuel.setText(new StringBuilder("Stock actuel: ")
                    .append(StockProduitService.getInstance()
                            .selectionnerQuantiteStockProduitTousLesShopsParIdProduit(produit.getId()).toString()).toString());

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperationEntreeStock.class.getName()).log(Level.SEVERE, null, ex);
        }

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());

        btnAjouterProduit.setEnabled(true);

        spnQuantiteProduit.requestFocus();

    }

    private void btnEffacerChampsProduitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerChampsProduitsActionPerformed
        effacerChampsItemStock();
    }//GEN-LAST:event_btnEffacerChampsProduitsActionPerformed

    private void tblItemsEntreeStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsEntreeStockMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblItemsEntreeStock.getSelectedRow();

            ItemEntreeStock itemEntreeStock;

            itemEntreeStock = itemsEntreeStock.get(row);

            spnQuantiteProduit.setValue(itemEntreeStock.getQuantiteProduit());
            spnQuantiteProduit.requestFocus();

            modeEditionItemEntreeStock = true;
            setProduitSelectionne(itemEntreeStock.getProduit());
        }
    }//GEN-LAST:event_tblItemsEntreeStockMouseClicked

    private void tblItemsEntreeStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemsEntreeStockKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            boolean exclu = false;

            List<ItemEntreeStock> listeItemsEntreeStock = itemsEntreeStock;
            int row = tblItemsEntreeStock.getSelectedRow();

            for (ItemEntreeStock ies : listeItemsEntreeStock) {
                if (ies.getProduit().getId().equals(itemsEntreeStock.get(row).getProduit().getId())) {
                    itemsEntreeStock.remove(ies);
                    modeEditionItemEntreeStock = false;
                    exclu = true;
                    break;
                }
            }

            if (exclu) {
                listerItemsEntreeStock();
            }
        }
    }//GEN-LAST:event_tblItemsEntreeStockKeyReleased

    private void btnEditarPrixAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPrixAchatActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        RegistrePrixAchatRaccourcis registrePrixAchatRaccourcis = new RegistrePrixAchatRaccourcis(null, true, produitSelectionne);
        registrePrixAchatRaccourcis.setFrameAncetre(this);
        registrePrixAchatRaccourcis.setVisible(true);

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEditarPrixAchatActionPerformed

    public void prixActualise(PrixAchatProduit prixAchatProduit) {
        produitSelectionne.setPrixAchatProduit(prixAchatProduit);
        lblPrixAchat.setText(new StringBuilder("Prix d'achat: $")
                .append(produitSelectionne.getPrixAchatProduit().getValeurUSD().toString()).toString());
    }

    private void tfdValeurFCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdValeurFCKeyReleased

        if (tfdValeurFC.getText().isEmpty() || tfdValeurFC.getText().equals("0.00")) {
            tfdValeurUSD.setText(getTotalValeurItems().toString());
            return;
        }

        BigDecimal convertiUSD = new BigDecimal(tfdValeurFC.getText()).multiply(tauxCarte.getValeur());

        if (convertiUSD.compareTo(getTotalValeurItems()) > 0) {
            evt.consume();
            tfdValeurFC.setText(getTotalValeurItems()
                    .divide(tauxCarte.getValeur(), 2, RoundingMode.HALF_EVEN).toString());
            tfdValeurUSD.setText("0.00");
            return;
        }

        tfdValeurUSD.setText(getTotalValeurItems().subtract(convertiUSD).toString());
    }//GEN-LAST:event_tfdValeurFCKeyReleased

    private void listerItemsEntreeStock() {
        BigDecimal totalAPayer = new BigDecimal("0");
        defaultTableModel.setRowCount(0);

        for (ItemEntreeStock ies : itemsEntreeStock) {
            dataRows[0] = ies.getProduit().getDescription();
            dataRows[1] = Double.parseDouble(ies.getQuantiteProduit().toString());
            dataRows[2] = Double.parseDouble(ies.getProduit().getPrixAchatProduit().getValeurUSD().toString());
            dataRows[3] = Double.parseDouble(ies.getProduit().getPrixAchatProduit().getValeurUSD().multiply(ies.getQuantiteProduit()).toString());

            totalAPayer = totalAPayer.add(ies.getProduit().getPrixAchatProduit().getValeurUSD().multiply(ies.getQuantiteProduit()));
            defaultTableModel.addRow(dataRows);
        }

        String formeNombre = itemsEntreeStock.size() > 1 ? "Items" : "Item";
        lblNombreItemEntreeStock.setText(itemsEntreeStock.size() + " " + formeNombre);

        lblTotalAPayer.setText(new StringBuilder("Total à payer: $")
                .append(totalAPayer.toString()).toString());

        if (!tfdValeurFC.getText().isEmpty() && !tfdValeurFC.getText().equals("0.00")) {
            BigDecimal convertiFC = totalAPayer.divide(tauxCarte.getValeur(), 2, RoundingMode.HALF_EVEN);

            if (convertiFC.compareTo(new BigDecimal(tfdValeurFC.getText())) < 0) {
                tfdValeurUSD.setText(totalAPayer.toString());
                tfdValeurFC.setText("0.00");
            } else {
                tfdValeurUSD.setText((convertiFC
                        .subtract(new BigDecimal(tfdValeurFC.getText())).multiply(tauxCarte.getValeur())).toString());
            }
        } else {
            tfdValeurUSD.setText(totalAPayer.toString());
            tfdValeurFC.setEditable(true);
            tfdValeurFC.setText("0.00");
        }
        if (itemsEntreeStock.isEmpty()) {
            tfdValeurUSD.setText("0.00");
            tfdValeurFC.setEditable(false);
            tfdValeurFC.setText("0.00");
        }

    }

    private BigDecimal getTotalValeurItems() {
        BigDecimal totalAPayer = new BigDecimal("0");

        for (ItemEntreeStock ies : itemsEntreeStock) {
            totalAPayer = totalAPayer.add(ies.getProduit().getPrixAchatProduit().getValeurUSD()
                    .multiply(ies.getQuantiteProduit()));
        }

        return totalAPayer;
    }

    private void effacerFormulaire() {

        idEntreeStock = "";
        idFournisseur = "";

        modeEdition = false;

        produitSelectionne = null;

        tfdNumeroEntreeStock.setText("");
        tfdNumeroEntreeStock.requestFocus();

        lblTauxCarte.setText("");
        tfdEntrepriseFournisseur.setText("");

        tfdDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        lblPrixAchat.setText("");
        btnEditarPrixAchat.setVisible(false);

        lblProduitStockActuel.setText("");

        modeEditionItemEntreeStock = false;
        btnAjouterProduit.setEnabled(false);

        itemsEntreeStock.clear();
        defaultTableModel.setRowCount(0);

        lblTotalAPayer.setText("");
        lblNombreItemEntreeStock.setText("");

        tfdValeurUSD.setText("");
        tfdValeurFC.setText("");
        tfdValeurFC.setEditable(false);

        btnEnregistrer.setText("ENREGISTRER");

        habiliterComposantFormulaire(true);

    }

    private void effacerChampsItemStock() {
        tfdDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        lblPrixAchat.setText("");
        lblProduitStockActuel.setText("");
        modeEditionItemEntreeStock = false;

        btnEditarPrixAchat.setVisible(false);
        btnAjouterProduit.setEnabled(false);

        tfdDescriptionProduit.requestFocus();
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        spnQuantiteProduit.setEnabled(hcf);
        btnConsulterEntreeStockClickable = hcf;
        btnConsulterFournisseurClickable = hcf;
        btnConsulterProduitClickable = hcf;

        btnAjouterProduitClickable = hcf;
        btnEffacerChampsProduitsClickable = hcf;
        tblItemsEntreeStockClickable = hcf;

        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdEntrepriseFournisseur.getText().isEmpty()) {
            notification.append("\nFournisseur");
            nio.add(1);
        }
        if (itemsEntreeStock.isEmpty()) {
            notification.append("\nProduits");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdEntrepriseFournisseur.requestFocus();
                    break;

                case 2:
                    tfdDescriptionProduit.requestFocus();
                    break;
                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterProduit;
    private javax.swing.JButton btnConsulterEntreeStock;
    private javax.swing.JButton btnConsulterFournisseur;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnEditarPrixAchat;
    private javax.swing.JButton btnEffacerChampsProduits;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreItemEntreeStock;
    private javax.swing.JLabel lblPrixAchat;
    private javax.swing.JLabel lblProduitStockActuel;
    private javax.swing.JLabel lblTauxCarte;
    private javax.swing.JLabel lblTotalAPayer;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblItemsEntreeStock;
    private javax.swing.JTextField tfdDescriptionProduit;
    private javax.swing.JTextField tfdEntrepriseFournisseur;
    private javax.swing.JTextField tfdNumeroEntreeStock;
    private javax.swing.JTextField tfdValeurFC;
    private javax.swing.JTextField tfdValeurUSD;
    // End of variables declaration//GEN-END:variables
}
