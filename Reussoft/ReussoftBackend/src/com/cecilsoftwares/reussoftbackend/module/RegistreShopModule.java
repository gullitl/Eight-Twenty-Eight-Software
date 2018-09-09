package com.cecilsoftwares.reussoftbackend.module;

import com.cecilsoftwares.reussoftbackend.service.ShopService;
import com.cecilsoftwares.reussoftmiddleend.dto.ShopDTO;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftmiddleend.util.Notification;
import com.cecilsoftwares.reussoftmiddleend.util.Notification.NotificationBuilder;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IBB
 */
public class RegistreShopModule {

    private static RegistreShopModule uniqueInstance;

    public RegistreShopModule() {
    }

    public static synchronized RegistreShopModule getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RegistreShopModule();
        }
        return uniqueInstance;
    }

    public Notification saveShop(String dataTransfered) {
        ShopDTO shopDTO = new Gson().fromJson(dataTransfered, ShopDTO.class);

        Shop shop = new ShopBuilder(shopDTO.getShopId())
                .withNom(shopDTO.getShopNom())
                .withAdresse(shopDTO.getShopAdresse())
                .withTauxCarte(shopDTO.getShopTauxCarte())
                .withActive(shopDTO.isShopActive())
                .create();

        Notification notification = new NotificationBuilder(10).create();
        try {

            if (ShopService.getInstance().enregistrerShop(shop)) {
                notification.setSucess(true);
                notification.setMessage(shop.getId().isEmpty() ? "Sauvegarde effectuée avec succès" : "Actualisation effectuée avec succès");

                if (shop.getId().equals(SessionUtilisateurKS.getInstance().getSessionUtilisateur().getCollaborateur().getShop().getId())) {
                    notification.setMessage(notification.getMessage()
                            + "Il est necessaire de quitter le système pour que les alterations soient appliquée");
                    notification.setExitSystemRequest(true);
                }

            }
            return notification;
        } catch (ClassNotFoundException | SQLException ex) {
            notification.setMessage("Une faille est survenue en sauvegardant le nouveau Shop");
            notification.setCause(ex.getCause().toString() + ex.getMessage());
            Logger.getLogger(RegistreShopModule.class.getName()).log(Level.SEVERE, null, ex);
            return notification;
        } catch (Exception ex) {
            notification.setMessage("Une faille est survenue en sauvegardant le nouveau Shop");
            notification.setCause(ex.getCause().toString() + ex.getMessage());
            Logger.getLogger(RegistreShopModule.class.getName()).log(Level.SEVERE, null, ex);
            return notification;
        }

    }

    public Notification deleteShop(String dataTransfered) {

        ShopDTO shopDTO = new Gson().fromJson(dataTransfered, ShopDTO.class);
        Shop shop = new ShopBuilder(shopDTO.getShopId()).create();

        Notification notification = new NotificationBuilder(0).create();

        if (shop.getId().equals("[B@7bb11784652#a6f0bc88e")) {
            notification.setMessage("Ce shop ne peux pas être exclue");
            notification.setCause("Ce shop é un shop par default");
        }

        try {
            ShopService.getInstance().exclureShop(shop);
            notification.setSucess(true);
            notification.setMessage("Exclusion effectuée avec succès");
            return notification;
        } catch (SQLException ex) {
            notification.setMessage("Une faille est survenue lors de l'exclusion du shop :(");
            switch (ex.getErrorCode()) {
                case 1451:
                    notification.setCause("Ce shop est utilisé par un autre registre!\n\n" + ex.getCause().toString() + ex.getMessage());
                    break;
                default:
                    break;
            }
            Logger.getLogger(RegistreShopModule.class.getName()).log(Level.SEVERE, null, ex);
            return notification;
        } catch (ClassNotFoundException ex) {
            notification.setMessage("Une faille est survenue lors de l'exclusion de la Catégorie Produit :(");
            notification.setCause(ex.getCause().toString() + ex.getMessage());
            Logger.getLogger(RegistreShopModule.class.getName()).log(Level.SEVERE, null, ex);
            return notification;
        }
    }

}
