package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private int code;
    private Shop shop;
    private Client client;
    private Date dateHeure;
    private List<ItemSortieStock> itemsSortieStock;

    public SortieStock() {

    }

    public SortieStock(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public List<ItemSortieStock> getItemsSortieStock() {
        return itemsSortieStock;
    }

    public void setItemsSortieStock(List<ItemSortieStock> itemsSortieStock) {
        this.itemsSortieStock = itemsSortieStock;
    }

}
