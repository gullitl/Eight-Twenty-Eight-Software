package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private String id;
    private Shop shop;
    private Client client;
    private TauxMonnaie tauxMonnaie;
    private Date dateHeure;
    private List<ItemSortieStock> itemsSortieStock;

    public SortieStock() {

    }

    public SortieStock(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TauxMonnaie getTauxMonnaie() {
        return tauxMonnaie;
    }

    public void setTauxMonnaie(TauxMonnaie tauxMonnaie) {
        this.tauxMonnaie = tauxMonnaie;
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
