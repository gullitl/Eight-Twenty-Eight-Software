package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.ConnectionCredentialsKS;
import com.cecilsystems.mistersoftbackend.dao.ConnectionFactory;
import com.cecilsystems.mistersoftbackend.model.ConnectionCredentials;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainService {

    private static MainService uniqueInstance;

    public MainService() {
    }

    public static synchronized MainService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MainService();
        }
        return uniqueInstance;
    }

    public void iniciaBase() {
        try {
            Gson gson = new Gson();
            BufferedReader br;
            br = new BufferedReader(new FileReader("mson.ini"));

            ConnectionCredentials cc = gson.fromJson(br, ConnectionCredentials.class);

            ConnectionCredentialsKS.getInstance().setConnectionCredentials(cc);

            Runtime.getRuntime().exec(ConnectionCredentialsKS.getInstance()
                    .getConnectionCredentials().getMysqldLink());
        } catch (FileNotFoundException fnfe) {
            Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, fnfe);
            JOptionPane.showMessageDialog(null, fnfe);
        } catch (IOException ioe) {
            Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ioe);
            JOptionPane.showMessageDialog(null, ioe);
        }

        boolean conectou = false;
        do {
            try {
                System.out.println(ConnectionFactory.getInstance().abreNovaConexao());
                conectou = true;
                System.out.println("SUCESS");
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Não conectou: " + ex);
            }
        } while (!conectou);
    }

    public String getMacAddress() throws UnknownHostException, SocketException {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] mac = ni.getHardwareAddress();

        String macAdress = "";
        for (int i = 0; i < mac.length; i++) {
            macAdress += mac[i];
        }
        return macAdress;
    }

}
