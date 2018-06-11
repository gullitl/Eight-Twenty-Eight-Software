/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ReseauDao;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class ReseauService {

    private static ReseauService uniqueInstance;

    public ReseauService() {
    }

    public static synchronized ReseauService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ReseauService();
        }
        return uniqueInstance;
    }

    public List<Reseau> listerTousLesReseaux() throws ClassNotFoundException, SQLException {
        return ReseauDao.getInstance().listerTousLesReseaus();
    }

    public Reseau selectionnerReseauParCode(int codeReseau) throws ClassNotFoundException, SQLException {
        return ReseauDao.getInstance().selectionnerReseauParCode(codeReseau);
    }

    public boolean enregistrerReseau(Reseau reseau) throws ClassNotFoundException, SQLException {
        return ReseauDao.getInstance().enregistrerReseau(reseau);
    }

    public boolean exclureReseau(int codeReseau) throws ClassNotFoundException, SQLException {
        return ReseauDao.getInstance().exclureReseau(codeReseau);
    }

}
