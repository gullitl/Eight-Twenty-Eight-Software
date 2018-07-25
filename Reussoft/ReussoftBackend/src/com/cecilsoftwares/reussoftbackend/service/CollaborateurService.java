package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CollaborateurDao;
import com.cecilsoftwares.reussoftbackend.dao.SessionUtilisateurDao;
import static com.cecilsoftwares.reussoftbackend.util.IdGenerator.generateId;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Plamedi L. Lusembo
 */
public class CollaborateurService {

    private static CollaborateurService uniqueInstance;

    public CollaborateurService() {
    }

    public static synchronized CollaborateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollaborateurService();
        }
        return uniqueInstance;
    }

    public Collaborateur rappelToiDeLUtilisateur()
            throws FileNotFoundException, UnknownHostException, SocketException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(MainService.getInstance()
                .getMacAddress() + ".collab"));

        Collaborateur collaborateur = gson.fromJson(br, Collaborateur.class);

        return collaborateur;
    }

    public int login(Shop shopUtilisateur, String nomUtilisateur, String motDePasse, boolean rappelToiDeMoi)
            throws ClassNotFoundException, SQLException, IOException, Exception {

        Collaborateur collaborateur = CollaborateurDao.getInstance().selectionnerUtilisateur(nomUtilisateur, motDePasse);

        if (collaborateur != null) {
            if (collaborateur.getShop().getId().equals(shopUtilisateur.getId())) {

                SessionUtilisateur sessionUtilisateur = new SessionUtilisateur();
                sessionUtilisateur.setId(generateId());
                sessionUtilisateur.setCollaborateur(collaborateur);
                sessionUtilisateur.setShop(shopUtilisateur);
                sessionUtilisateur.setActionEntree(true);
                sessionUtilisateur.setDateHeure(new Date());

                SessionUtilisateurKS.getInstance().setSessionUtilisateur(sessionUtilisateur);

                if (rappelToiDeMoi) {
                    Gson gson = new Gson();
                    String json = gson.toJson(collaborateur);

                    try (FileWriter writer = new FileWriter(MainService.getInstance()
                            .getMacAddress() + ".collab")) {
                        writer.write(json);
                    }
                } else {
                    new File(MainService.getInstance().getMacAddress() + ".collab").delete();
                }

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            SessionUtilisateurDao.getInstance().sauvegarderSessionUtilisateur(sessionUtilisateur);
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(CollaborateurService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }.start();

                return 1;
            } else {
                return 2;
            }
        }

        return 0;
    }

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().listerTousLesCollaborateurs();
    }

    public Collaborateur selectionnerCollaborateurParCode(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().selectionnerCollaborateurParId(codeCollaborateur);
    }

    public boolean enregistrerCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException, Exception {
        if (collaborateur.getId().isEmpty()) {
            collaborateur.setId(generateId());
            return CollaborateurDao.getInstance().enregistrerCollaborateur(collaborateur);
        } else {
            return CollaborateurDao.getInstance().actualiserCollaborateur(collaborateur);
        }
    }

    public boolean exclureCollaborateur(String idCollaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().exclureCollaborateur(idCollaborateur);
    }

}
