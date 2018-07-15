package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.DispatchService;
import com.cecilsoftwares.reussoftbackend.service.ProduitService;
import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationDispatch;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationFournisseur;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProduit;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationShop;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class OperationDispatch extends JInternalFrame {

    private String idDispatch;
    private boolean modeEdition;
    private boolean modeEditionItemDispatch;

    private boolean btnConsulterDispatchClickable;
    private boolean btnConsulterShopClickable;
    private boolean btnConsulterProduitClickable;
    private boolean btnAjouterProduitClickable;
    private boolean btnEffacerChampsProduitsClickable;
    private boolean tblItemsEntreeStockClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnAnnulerClickable;

    private Produit produitSelectionne;
    private List<ItemDispatch> itemsDispatch;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    public OperationDispatch() {
        initComponents();

        itemsDispatch = new ArrayList();

        defaultTableModel = (DefaultTableModel) tblItemsDispatch.getModel();
        dataRows = new Object[5];

        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tfdDescriptionProduit = new javax.swing.JTextField();
        btnConsulterProduit = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnAjouterProduit = new javax.swing.JButton();
        spnQuantiteProduit = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemsDispatch = new javax.swing.JTable();
        btnEffacerChampsProduits = new javax.swing.JButton();
        lblNombreItemDispatch = new javax.swing.JLabel();
        btnConsulterShop = new javax.swing.JButton();
        tfdNomShop = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnEffacerFormulaire = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        tfdNumeroDispatch = new javax.swing.JTextField();
        btnConsulterDispatch = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dispatch");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produits"));

        tfdDescriptionProduit.setEditable(false);

        btnConsulterProduit.setText("...");
        btnConsulterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProduitActionPerformed(evt);
            }
        });

        jLabel12.setText("Code:");

        btnAjouterProduit.setText("+");
        btnAjouterProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterProduitActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantité:");

        tblItemsDispatch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Shop", "Produit", "Qt. anterieur", "Qt. Ajoutée", "Qt. Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemsDispatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsDispatchMouseClicked(evt);
            }
        });
        tblItemsDispatch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblItemsDispatchKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemsDispatch);
        if (tblItemsDispatch.getColumnModel().getColumnCount() > 0) {
            tblItemsDispatch.getColumnModel().getColumn(0).setResizable(false);
            tblItemsDispatch.getColumnModel().getColumn(0).setPreferredWidth(170);
            tblItemsDispatch.getColumnModel().getColumn(1).setResizable(false);
            tblItemsDispatch.getColumnModel().getColumn(1).setPreferredWidth(230);
            tblItemsDispatch.getColumnModel().getColumn(2).setResizable(false);
            tblItemsDispatch.getColumnModel().getColumn(3).setResizable(false);
            tblItemsDispatch.getColumnModel().getColumn(4).setResizable(false);
        }

        btnEffacerChampsProduits.setText("<-");
        btnEffacerChampsProduits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerChampsProduitsActionPerformed(evt);
            }
        });

        lblNombreItemDispatch.setText("jLabel2");

        btnConsulterShop.setText("...");
        btnConsulterShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterShopActionPerformed(evt);
            }
        });

        tfdNomShop.setEditable(false);

        jLabel1.setText("Shop:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAjouterProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEffacerChampsProduits, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblNombreItemDispatch))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfdNomShop, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterShop)))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tfdDescriptionProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulterProduit)))
                        .addContainerGap(336, Short.MAX_VALUE))))
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
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNomShop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterShop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnQuantiteProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterProduit)
                    .addComponent(btnEffacerChampsProduits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreItemDispatch)
                .addContainerGap())
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
        jLabel15.setText("Numero:");

        tfdNumeroDispatch.setEditable(false);

        btnConsulterDispatch.setText("...");
        btnConsulterDispatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterDispatchActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Activer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)))
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfdNumeroDispatch, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulterDispatch)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdNumeroDispatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterDispatch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEffacerFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        if (isInformationObligatoiresRemplies()) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            Dispatch dispatch = new Dispatch(idDispatch);
            dispatch.setNumeroDispatch(tfdNumeroDispatch.getText());
            dispatch.setShop(SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getShop());
            dispatch.setDateHeure(new Date());
            dispatch.setItemsDispatch(itemsDispatch);
            dispatch.setValide(true);

            try {
                if (DispatchService.getInstance().enregistrerDispatch(dispatch)) {
                    String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                    effacerFormulaire();
                    JOptionPane.showMessageDialog(null, notification);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant le Client");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    public void dispatchSelectionne(Dispatch dispatch) {
        if (dispatch != null) {
            tfdNumeroDispatch.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dispatch.getDateHeure()));
            itemsDispatch = dispatch.getItemsDispatch();

            chargerTableauItemDispatch();

        }
    }

    private void btnEffacerFormulaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerFormulaireActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnEffacerFormulaireActionPerformed

    private void btnAjouterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterProduitActionPerformed

        if (spnQuantiteProduit.getValue().toString().equals("0") || spnQuantiteProduit.getValue().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantité incorrect!");
        } else {

            if (modeEditionItemDispatch) {
                for (ItemDispatch ids : itemsDispatch) {
                    if (ids.getProduit().getId().equals(idDispatch)) {
                        ids.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));
                        break;
                    }
                }

            } else {

                Dispatch dispatch = new Dispatch(idDispatch);

                ItemDispatch itemDispatch = new ItemDispatch(dispatch, produitSelectionne);
                itemDispatch.setQuantiteProduit(new BigDecimal(spnQuantiteProduit.getValue().toString()));

                for (ItemDispatch ids : itemsDispatch) {
                    if (ids.getProduit().getId().equals(itemDispatch.getProduit().getId())) {
                        JOptionPane.showMessageDialog(null, "Ce produit a déjà été ajouté");
                        return;
                    }
                }

                itemsDispatch.add(itemDispatch);
            }

            chargerTableauItemDispatch();

            effacerChampsItemStock();
        }

    }//GEN-LAST:event_btnAjouterProduitActionPerformed

    private void btnConsulterDispatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterDispatchActionPerformed
        if (btnConsulterDispatchClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {
                List<Dispatch> dispatchs = DispatchService.getInstance().listerTousLesDispatchsSansItems();
                ConsultationDispatch consultationDispatch = new ConsultationDispatch(null, true, dispatchs);
                consultationDispatch.setFrameAncetre(this);
                consultationDispatch.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OperationDispatch.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterDispatchActionPerformed

    private void btnConsulterShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterShopActionPerformed
        if (btnConsulterShopClickable) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            try {

                ConsultationShop consultationShop = new ConsultationShop(null, true, ShopService.getInstance()
                        .listerTousLesShops());
                consultationShop.setFrameAncetre(this);
                consultationShop.setVisible(true);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConsultationFournisseur.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterShopActionPerformed

    public void shopSelectionne(Shop shop) {
        if (shop != null) {
            tfdNomShop.setText(String.valueOf(shop.getNom()));
        }
    }

    private void btnConsulterProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProduitActionPerformed
        if (btnConsulterProduitClickable) {
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
        }
    }//GEN-LAST:event_btnConsulterProduitActionPerformed

    private void btnEffacerChampsProduitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerChampsProduitsActionPerformed
        effacerChampsItemStock();
    }//GEN-LAST:event_btnEffacerChampsProduitsActionPerformed

    public void effacerChampsItemStock() {
        tfdDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        modeEditionItemDispatch = false;
        tfdDescriptionProduit.requestFocus();
    }

    private void tblItemsDispatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsDispatchMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblItemsDispatch.getSelectedRow();

            ItemDispatch itemDispatch;

            itemDispatch = itemsDispatch.get(row);

//            itemDispatch = itemsDispatch.stream()
//                    .filter(ies -> ies.getProduit().getCode() == (int) defaultTableModel.getValueAt(row, 0))
//                    .findFirst().orElse(null);
            if (itemDispatch != null) {
                tfdNomShop.setText(itemDispatch.getShop().getId());
                tfdDescriptionProduit.setText(itemDispatch.getProduit().getId());
                spnQuantiteProduit.setValue(itemDispatch.getQuantiteProduit());
                modeEditionItemDispatch = true;

            }
        }
    }//GEN-LAST:event_tblItemsDispatchMouseClicked

    private void tblItemsDispatchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemsDispatchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {

            boolean exclu = false;

            List<ItemDispatch> listeItemsDispatch = itemsDispatch;
            int row = tblItemsDispatch.getSelectedRow();

            for (ItemDispatch ids : listeItemsDispatch) {

                if (ids.getProduit().getId().equals(itemsDispatch.get(row).getProduit().getId())) {
                    itemsDispatch.remove(ids);
                    exclu = true;
                    break;
                }
            }

            if (exclu) {
                chargerTableauItemDispatch();
            }

        }
    }//GEN-LAST:event_tblItemsDispatchKeyReleased

    private void chargerTableauItemDispatch() {
        defaultTableModel.setRowCount(0);

        itemsDispatch.forEach(ies -> {
            dataRows[0] = ies.getShop().getNom();
            dataRows[1] = ies.getProduit().getDescription();
            dataRows[2] = ies.getQuantiteProduit();
            dataRows[3] = ies.getQuantiteProduit();
            dataRows[4] = ies.getQuantiteProduit().add(ies.getQuantiteProduit());
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = itemsDispatch.size() > 1 ? "Items" : "Item";
        lblNombreItemDispatch.setText(itemsDispatch.size() + " " + formeNombre);

    }

    public void setProduitSelectionne(Produit produit) {
        if (produit != null) {
            produitSelectionne = produit;
            tfdDescriptionProduit.setText(produitSelectionne.getId());
        }
    }

    private void effacerFormulaire() {

        tfdNumeroDispatch.setText("");
        tfdNumeroDispatch.requestFocus();

        tfdNomShop.setText("");

        tfdDescriptionProduit.setText("");
        spnQuantiteProduit.setValue(0);
        modeEditionItemDispatch = false;

        defaultTableModel.setRowCount(0);
        itemsDispatch.clear();

        habiliterComposantFormulaire(true);

    }

    private void habiliterComposantFormulaire(boolean hcf) {
        spnQuantiteProduit.setEnabled(hcf);
        btnConsulterDispatchClickable = hcf;
        btnConsulterShopClickable = hcf;
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

        if (tfdNomShop.getText().isEmpty()) {
            notification.append("\nFournisseur");
            nio.add(1);
        }
        if (itemsDispatch.isEmpty()) {
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
                    tfdNomShop.requestFocus();
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
    private javax.swing.JButton btnConsulterDispatch;
    private javax.swing.JButton btnConsulterProduit;
    private javax.swing.JButton btnConsulterShop;
    private javax.swing.JButton btnEffacerChampsProduits;
    private javax.swing.JButton btnEffacerFormulaire;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreItemDispatch;
    private javax.swing.JSpinner spnQuantiteProduit;
    private javax.swing.JTable tblItemsDispatch;
    private javax.swing.JTextField tfdDescriptionProduit;
    private javax.swing.JTextField tfdNomShop;
    private javax.swing.JTextField tfdNumeroDispatch;
    // End of variables declaration//GEN-END:variables
}
