package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private final int code;
    private final Shop shop;
    private final Client client;
    private final Date dateHeure;
    private final List<ItemSortieStock> sortiesStock;

    public SortieStock(SortieStockBuilder sortieStockBuilder) {
        code = sortieStockBuilder.code;
        shop = sortieStockBuilder.shop;
        client = sortieStockBuilder.client;
        dateHeure = sortieStockBuilder.dateHeure;
        sortiesStock = sortieStockBuilder.sortiesStock;
    }

    public int getCode() {
        return code;
    }

    public Shop getShop() {
        return shop;
    }

    public Client getClient() {
        return client;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public List<ItemSortieStock> getSortiesStock() {
        return sortiesStock;
    }

    public static class SortieStockBuilder {

        private int code;
        private Shop shop;
        private Client client;
        private Date dateHeure;
        private List<ItemSortieStock> sortiesStock;

        public SortieStockBuilder(int code) {
            this.code = code;
        }

        public SortieStockBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public SortieStockBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public SortieStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public SortieStockBuilder sortiesStock(List<ItemSortieStock> sortiesStock) {
            this.sortiesStock = sortiesStock;
            return this;
        }

        public SortieStock build() {
            return new SortieStock(this);
        }

    }

}
