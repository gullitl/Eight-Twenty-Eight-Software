package com.cecilsoftwares.reussoftmiddleend.dto;

import com.cecilsoftwares.reussoftmiddleend.model.*;

/**
 * @author Plamedi L. Lusembo
 */
public class ShopDTO {

    private String shopId;
    private String shopNom;
    private String shopAdresse;
    private TauxCarte shopTauxCarte;
    private boolean shopActive;

    private ShopDTO() {

    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopNom() {
        return shopNom;
    }

    public void setShopNom(String shopNom) {
        this.shopNom = shopNom;
    }

    public String getShopAdresse() {
        return shopAdresse;
    }

    public void setShopAdresse(String shopAdresse) {
        this.shopAdresse = shopAdresse;
    }

    public TauxCarte getShopTauxCarte() {
        return shopTauxCarte;
    }

    public void setShopTauxCarte(TauxCarte shopTauxCarte) {
        this.shopTauxCarte = shopTauxCarte;
    }

    public boolean isShopActive() {
        return shopActive;
    }

    public void setShopActive(boolean shopActive) {
        this.shopActive = shopActive;
    }

    public static class ShopDTOBuilder {

        private final String shopId;
        private String shopNom;
        private String shopAdresse;
        private TauxCarte shopTauxCarte;
        private boolean shopActive;

        public ShopDTOBuilder(String shopId) {
            this.shopId = shopId;
        }

        public ShopDTOBuilder withShopNom(String shopNom) {
            this.shopNom = shopNom;
            return this;
        }

        public ShopDTOBuilder withShopAdresse(String shopAdresse) {
            this.shopAdresse = shopAdresse;
            return this;
        }

        public ShopDTOBuilder withShopTauxCarte(TauxCarte shopTauxCarte) {
            this.shopTauxCarte = shopTauxCarte;
            return this;
        }

        public ShopDTOBuilder withShopActive(boolean shopActive) {
            this.shopActive = shopActive;
            return this;
        }

        public ShopDTO create() {
            ShopDTO shopDTO = new ShopDTO();

            shopDTO.setShopId(this.shopId);
            shopDTO.setShopNom(this.shopNom);
            shopDTO.setShopAdresse(this.shopAdresse);
            shopDTO.setShopTauxCarte(this.shopTauxCarte);
            shopDTO.setShopActive(this.shopActive);

            return shopDTO;
        }

    }

}
