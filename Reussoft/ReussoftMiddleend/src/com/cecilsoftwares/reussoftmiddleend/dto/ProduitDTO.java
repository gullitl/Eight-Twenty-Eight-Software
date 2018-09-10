package com.cecilsoftwares.reussoftmiddleend.dto;

import com.cecilsoftwares.reussoftmiddleend.model.*;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ProduitDTO {

    private String produitId;
    private String produitDescription;
    private Reseau produitReseau;
    private CategorieProduit produitCategorie;
    private PrixAchatProduit produitPrixAchat;
    private BigDecimal produitQuantiteStockMininum;
    private BigDecimal produitQuantiteStockToutShopConfondu;
    private boolean produitActive;

    private ProduitDTO() {

    }

    public String getProduitId() {
        return produitId;
    }

    public void setProduitId(String produitId) {
        this.produitId = produitId;
    }

    public String getProduitDescription() {
        return produitDescription;
    }

    public void setProduitDescription(String produitDescription) {
        this.produitDescription = produitDescription;
    }

    public Reseau getProduitReseau() {
        return produitReseau;
    }

    public void setProduitReseau(Reseau produitReseau) {
        this.produitReseau = produitReseau;
    }

    public CategorieProduit getProduitCategorie() {
        return produitCategorie;
    }

    public void setProduitCategorie(CategorieProduit produitCategorie) {
        this.produitCategorie = produitCategorie;
    }

    public PrixAchatProduit getProduitPrixAchat() {
        return produitPrixAchat;
    }

    public void setProduitPrixAchat(PrixAchatProduit produitPrixAchat) {
        this.produitPrixAchat = produitPrixAchat;
    }

    public BigDecimal getProduitQuantiteStockMininum() {
        return produitQuantiteStockMininum;
    }

    public void setProduitQuantiteStockMininum(BigDecimal produitQuantiteStockMininum) {
        this.produitQuantiteStockMininum = produitQuantiteStockMininum;
    }

    public BigDecimal getProduitQuantiteStockToutShopConfondu() {
        return produitQuantiteStockToutShopConfondu;
    }

    public void setProduitQuantiteStockToutShopConfondu(BigDecimal produitQuantiteStockToutShopConfondu) {
        this.produitQuantiteStockToutShopConfondu = produitQuantiteStockToutShopConfondu;
    }

    public boolean isProduitActive() {
        return produitActive;
    }

    public void setProduitActive(boolean produitActive) {
        this.produitActive = produitActive;
    }

    public static class ProduitDTOBuilder {

        private final String produitId;
        private String produitDescription;
        private Reseau produitReseau;
        private CategorieProduit produitCategorie;
        private PrixAchatProduit produitPrixAchat;
        private BigDecimal produitQuantiteStockMininum;
        private BigDecimal produitQuantiteStockToutShopConfondu;
        private boolean produitActive;

        public ProduitDTOBuilder(String produitId) {
            this.produitId = produitId;
        }

        public ProduitDTOBuilder withDescription(String produitDescription) {
            this.produitDescription = produitDescription;
            return this;
        }

        public ProduitDTOBuilder withProduitReseau(Reseau produitReseau) {
            this.produitReseau = produitReseau;
            return this;
        }

        public ProduitDTOBuilder withProduitCategorie(CategorieProduit produitCategorie) {
            this.produitCategorie = produitCategorie;
            return this;
        }

        public ProduitDTOBuilder withProduitPrixAchat(PrixAchatProduit produitPrixAchat) {
            this.produitPrixAchat = produitPrixAchat;
            return this;
        }

        public ProduitDTOBuilder withProduitQuantiteStockMininum(BigDecimal produitQuantiteStockMininum) {
            this.produitQuantiteStockMininum = produitQuantiteStockMininum;
            return this;
        }

        public ProduitDTOBuilder withProduitQuantiteStockToutShopConfondu(BigDecimal produitQuantiteStockToutShopConfondu) {
            this.produitQuantiteStockToutShopConfondu = produitQuantiteStockToutShopConfondu;
            return this;
        }

        public ProduitDTOBuilder withProduitActive(boolean produitActive) {
            this.produitActive = produitActive;
            return this;
        }

        public ProduitDTO create() {
            ProduitDTO produitDTO = new ProduitDTO();

            produitDTO.setProduitId(this.produitId);
            produitDTO.setProduitDescription(this.produitDescription);
            produitDTO.setProduitReseau(this.produitReseau);
            produitDTO.setProduitCategorie(this.produitCategorie);
            produitDTO.setProduitPrixAchat(this.produitPrixAchat);
            produitDTO.setProduitQuantiteStockMininum(this.produitQuantiteStockMininum);
            produitDTO.setProduitQuantiteStockToutShopConfondu(this.produitQuantiteStockToutShopConfondu);
            produitDTO.setProduitActive(this.produitActive);

            return produitDTO;
        }

    }

}
